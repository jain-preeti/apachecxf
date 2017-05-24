package org.madbit.soap;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.xml.namespace.QName;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;

import org.apache.cxf.binding.soap.interceptor.SoapHeaderInterceptor;
import org.apache.cxf.configuration.security.AuthorizationPolicy;
import org.apache.cxf.endpoint.Endpoint;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.message.Exchange;
import org.apache.cxf.message.Message;
import org.apache.cxf.transport.Conduit;
import org.apache.cxf.ws.addressing.EndpointReferenceType;
import org.springframework.beans.factory.annotation.Required;

/**
 * CXF Interceptor that provides HTTP Basic Authentication validation.
 * 
 * Based on the concepts outline here:
 *    http://chrisdail.com/2008/03/31/apache-cxf-with-http-basic-authentication
 *
 * @author CDail
 */
public class BasicAuthAuthorizationInterceptor extends SoapHeaderInterceptor {

    
    /** Map of allowed users to this system with their corresponding passwords. */
    private Map<String,String> users;
    
    @Required
    public void setUsers(Map<String, String> users) {
        this.users = users;
    }
    
    @Override public void handleMessage(Message message) throws Fault {
        // This is set by CXF
    	
    	         AuthorizationPolicy policy = message.get(AuthorizationPolicy.class);
        
        // If the policy is not set, the user did not specify credentials
        // A 401 is sent to the client to indicate that authentication is required
        if (policy == null) {
                System.out.println("User attempted to log in with no credentials");
            
            sendErrorResponse(message, HttpURLConnection.HTTP_UNAUTHORIZED);
            return;
        }
        
            System.out.println("Logging in use: " + policy.getUserName());
        
          
        // Verify the password
        String realPassword = users.get(policy.getUserName());
        if (realPassword == null || !realPassword.equals(policy.getPassword())) {
            System.out.println("Invalid username or password for user: " + policy.getUserName());
            sendErrorResponse(message, HttpURLConnection.HTTP_FORBIDDEN);
        }
    }
    
    private void sendErrorResponse(Message message, int responseCode) {
        Message outMessage = getOutMessage(message);
        outMessage.put(Message.RESPONSE_CODE, responseCode);
        
        // Set the response headers
        Map<String, List<String>> responseHeaders =
            (Map<String, List<String>>)message.get(Message.PROTOCOL_HEADERS);
        if (responseHeaders != null) {
            responseHeaders.put("WWW-Authenticate", Arrays.asList(new String[]{"Basic realm=realm"}));
            responseHeaders.put("Content-Length", Arrays.asList(new String[]{"0"}));
        }
        message.getInterceptorChain().abort();
        try {
            getConduit(message).prepare(outMessage);
            close(outMessage);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    
    private Message getOutMessage(Message inMessage) {
        Exchange exchange = inMessage.getExchange();
        Message outMessage = exchange.getOutMessage();
        if (outMessage == null) {
            Endpoint endpoint = exchange.get(Endpoint.class);
            outMessage = endpoint.getBinding().createMessage();
            exchange.setOutMessage(outMessage);
        }
        outMessage.putAll(inMessage);
        return outMessage;
    }
    
    private Conduit getConduit(Message inMessage) throws IOException {
        Exchange exchange = inMessage.getExchange();
        EndpointReferenceType target = exchange.get(EndpointReferenceType.class);
        Conduit conduit =
            exchange.getDestination().getBackChannel(inMessage, null, target);
        exchange.setConduit(conduit);
        return conduit;
    }
    
    private void close(Message outMessage) throws IOException {
        OutputStream os = outMessage.getContent(OutputStream.class);
        os.flush();
        os.close();
    }
    
    
    

/*	public void close(MessageContext arg0) {
		// TODO Auto-generated method stub
		
	}

	public boolean handleFault(SOAPMessageContext arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean handleMessage(SOAPMessageContext arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Set<QName> getHeaders() {
		 QName securityHeader = new QName("http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd", 
	                "Security"); 
	        HashSet<QName> headers = new HashSet<QName>(); 
	        headers.add(securityHeader);         
	        return headers; 
	}*/
}