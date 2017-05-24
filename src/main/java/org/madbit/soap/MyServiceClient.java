package org.madbit.soap;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.xml.bind.JAXBException;
import javax.xml.namespace.QName;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.frontend.ClientProxy;
import org.apache.cxf.headers.Header;
import org.apache.cxf.jaxb.JAXBDataBinding;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.madbit.myservice.MyService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class MyServiceClient  implements SOAPHandler<SOAPMessageContext> {
	
	/* public static void main(String[] args) throws MalformedURLException{
	        String serviceUrl = "http://localhost:8080/Apachecxf/soap/MyService";
	        JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
			factory.setServiceClass(MyService.class);
			factory.setAddress(serviceUrl);
			MyService bookService = (MyService) factory.create();
			SumRequest parameters=new SumRequest();
			parameters.getAddend().add(2);
		SumResponse response=	bookService.sum(parameters);		
		System.out.println(response);
	        
	        
	    		 
		
	 
	 }
	 */
	 @Bean
	 MyService getfactory() throws JAXBException
	 {
		 String serviceUrl = "http://localhost:8080/Apachecxf/soap/MyService";
		 JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
			factory.setServiceClass(MyService.class);
			factory.setAddress(serviceUrl);
			MyService bookService = (MyService) factory.create();
			// Get the underlying Client object from the proxy object of service interface
			Client proxy = ClientProxy.getClient(bookService);

			// Creating SOAP headers to the web service request

			// Create a list for holding all SOAP headers
			List<Header> headersList = new ArrayList<Header>();

			Header testSoapHeader1 = new Header(new QName("uri:singz.ws.sample", "soapheader1"), "SOAP Header Message 1", new JAXBDataBinding(String.class));
			Header testSoapHeader2 = new Header(new QName("uri:singz.ws.sample", "soapheader2"), "SOAP Header Message 2", new JAXBDataBinding(String.class));

			headersList.add(testSoapHeader1);
			headersList.add(testSoapHeader2);

			// Add SOAP headers to the web service request
			proxy.getRequestContext().put(Header.HEADER_LIST, headersList);
			
			/*ServerFactoryBean serverFactoryBean=new ServerFactoryBean();
			serverFactoryBean.setServiceClass(MyService.class);
			serverFactoryBean.setAddress(serviceUrl);
			Server server=serverFactoryBean.create();
			Endpoint cxfEndpoint =	server.getEndpoint();
			 Map<String,Object> inProps = new HashMap<String,Object>();
				
			 WSS4JInInterceptor wssIn = new WSS4JInInterceptor(inProps);
			 cxfEndpoint.getInInterceptors().add(wssIn);
			
			 Map<String,Object> outProps = new HashMap<String,Object>();
			 
			 WSS4JOutInterceptor wssOut = new WSS4JOutInterceptor(outProps);
			 cxfEndpoint.getOutInterceptors().add(wssOut);	 
			 
			*/
			return bookService;
	 }

	public void close(MessageContext arg0) {
		// TODO Auto-generated method stub
		
	}

	public boolean handleFault(SOAPMessageContext arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean handleMessage(SOAPMessageContext arg0) {
		System.out.println(arg0);	
		return false;
	}

	 
	 
	 
	 
    public Set<QName> getHeaders() { 
        QName securityHeader = new QName("http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd", 
                "Security"); 
        HashSet<QName> headers = new HashSet<QName>(); 
        headers.add(securityHeader);         
        return headers; 
    }
	 
	 
	 
	 
	 
}

