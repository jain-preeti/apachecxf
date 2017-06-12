package org.madbit.soap;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigInteger;
import java.net.Socket;
import java.net.UnknownHostException;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author rmittal1
 */
public class EFTSendReceive
{
    private static BufferedReader inputStream = null;
    public static Socket clientSock;
    public static OutputStream out;
    public static BufferedOutputStream wr;
    public static InputStream in;
    public static BufferedInputStream rd;
    

    public static void main(String [] str)
    {
    	System.out.println(Integer.parseInt("11", 16));
    	System.out.println(Integer.toString(11, 16));

       /* if(str.length < 2)
        {
            Usage();
            System.exit(0);
        }*/
        String temp;

        try
        {
          

            inputStream = new BufferedReader(new FileReader("D:\\Preeti\\Misc\\InputFile.txt"));
            String inLine = inputStream.readLine();
		System.out.println("************************************************");
            System.out.println("Read Value From File: " + inLine);
            int k[] = new int[(inLine.length())/2];
            byte b[] = new byte[(inLine.length())/2];
            byte header[] = new byte[6];            //EFT Header
            byte[] length = new byte[2];            //Length of Data 

            for(int i=0, l=0;i<inLine.length();i=i+2, l++)
            {
                temp = inLine.substring(i, i+2);
                k[l] = Integer.parseInt(temp, 16);
                b[l] =  (byte) k[l];
                //System.out.println("fileArray[" + l + "] = "  +b[l] );
            }
            
               String hexString = Integer.toHexString(b.length);
            if (b.length<=255)
            {
               length[0] = 0x00;
               length[1] = new BigInteger(hexString, 16).byteValue();
            }
            else
            {
               length = new BigInteger(hexString, 16).toByteArray();
            }

            header[0] = 0x01;
            header[1] = 0x01;
            header[2] = 0x00;
            header[3] = 0x00;
            header[4] = length[0];
            header[5] = length[1];

            System.out.println("Number of Data BYTES : " +(b.length));
            System.out.println("Length of the Request: " +hexString);
            //System.out.println("Hex Value of Input Stream: " +bytesToHexStr(b));

            /* Concatenating EFT Header and File Data */

            byte[] temp_abc = new byte[header.length + b.length];

            System.arraycopy(header,0,temp_abc,0, header.length);
            System.arraycopy(b,0,temp_abc,header.length,b.length);

            header = temp_abc;

            //System.out.println("Input to EFT: " +bytesToHexStr(header));

            inputStream.close();
            
                //clientSock = new Socket(str[0], Integer.parseInt(str[1], 10));
                clientSock = new Socket("192.168.64.2", 1000);
                write_sock(header);
                read_sock();
                out.close();
                wr.close();
                in.close();
                rd.close();
                clientSock.close();
               
        }

        catch(UnknownHostException e)
        {
            System.out.println((new StringBuilder()).append("Error :").append(e).toString());
        }
        catch (IOException e)
        {
            System.out.println("IOException:");
            System.out.println((new StringBuilder()).append("Error :").append(e).toString());
            e.printStackTrace();
        }

    }


        static void Usage()
    {
        System.out.println("Usage: java EFTSendReceive <IP> <PORT>");
    }


         public static void write_sock(byte bt_req[])
    {
        try
        {
            out = clientSock.getOutputStream();
            wr = new BufferedOutputStream(out);

            System.out.println((new StringBuilder()).append("Request being sent to EFT is: ").append(bytesToHexStr(bt_req)).toString());
            wr.write(bt_req);
            wr.flush();
        }
        catch(IOException e)
        {
            System.out.println((new StringBuilder()).append("Error :").append(e).toString());
        }
    }


    public static void read_sock()
    {
        try
        {
            int rv = 0;
            byte eracom_header[] = new byte[6];
            byte len_prefix[] = new byte[2];
            int length_of_response = 0;
            in = clientSock.getInputStream();
            rd = new BufferedInputStream(in);
            rd.read(eracom_header, 0, 6);
            System.out.println((new StringBuilder()).append("EFT Header is: ").append(bytesToHexStr(eracom_header)).toString());
            len_prefix[0] = eracom_header[4];
            len_prefix[1] = eracom_header[5];
            length_of_response = byteToInt(len_prefix);
            System.out.println((new StringBuilder()).append("Lenght of the Response: ").append(length_of_response).toString());
            byte read_buf[] = new byte[length_of_response];
            rd.read(read_buf, 0, length_of_response);
            String resp_str = bytesToHexStr(read_buf);
            System.out.println((new StringBuilder()).append("Output Received from the EFT: ").append(resp_str).toString());
		System.out.println("************************************************");
        }
        catch(IOException e)
        {
            System.out.println((new StringBuilder()).append("Error :").append(e).toString());
        }
    }


        public static final String bytesToHexStr(byte[] bArray)
    {
        String lookup = "0123456789ABCDEF";
        StringBuffer s = new StringBuffer(bArray.length * 2);

        for (int i = 0; i < bArray.length; i++)
        {
            s.append(lookup.charAt((bArray[i] >>> 4) & 0x0f));
            s.append(lookup.charAt(bArray[i] & 0x0f));
        }

        return s.toString();
    }


       static int byteToInt(byte b[])
    {
        int val = 0;
        int i = b.length - 1;
        for(int j = 0; i >= 0; j++)
        {
            val += (b[i] & 0xff) << 8 * j;
            i--;
        }

        return val;
    }

       
	 public static byte[] fromHexString(String in){

	       BigInteger temp = new BigInteger(in, 16);

	       return temp.toByteArray();

	       }

    }
