package org.madbit.soap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.xml.bind.JAXBException;
import javax.xml.namespace.QName;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.endpoint.Endpoint;
import org.apache.cxf.endpoint.Server;
import org.apache.cxf.frontend.ClientProxy;
import org.apache.cxf.frontend.ServerFactoryBean;
import org.apache.cxf.headers.Header;
import org.apache.cxf.jaxb.JAXBDataBinding;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.madbit.myservice.MyService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.apache.cxf.ws.security.wss4j.WSS4JInInterceptor;
import org.apache.cxf.ws.security.wss4j.WSS4JOutInterceptor;
import org.apache.ws.security.WSConstants;
import org.apache.ws.security.handler.WSHandlerConstants;


@Configuration
public class MyServiceClient {
	
	
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
			Endpoint cxfEndpoint=	proxy.getEndpoint();
			 Map<String,Object> inProps = new HashMap<String,Object>();
			 inProps.put(WSHandlerConstants.ACTION, WSHandlerConstants.USERNAME_TOKEN);	
			 inProps.put(WSHandlerConstants.PW_CALLBACK_CLASS, 
					    ClientPasswordCallback.class.getName());
			 inProps.put(WSHandlerConstants.USER, "preeti");
			 WSS4JInInterceptor wssIn = new WSS4JInInterceptor(inProps);			
			// cxfEndpoint.getInInterceptors().add(wssIn);			
			 Map<String,Object> outProps = new HashMap<String,Object>();
			 outProps.put(WSHandlerConstants.ACTION, WSHandlerConstants.USERNAME_TOKEN);
			// Specify our username
			outProps.put(WSHandlerConstants.USER, "preeti");
			// Password type : plain text
			outProps.put(WSHandlerConstants.PASSWORD_TYPE, WSConstants.PW_TEXT);
			// for hashed password use:
			//properties.put(WSHandlerConstants.PASSWORD_TYPE, WSConstants.PW_DIGEST);
			// Callback used to retrieve password for given user.
			outProps.put(WSHandlerConstants.PW_CALLBACK_CLASS, 
		    ClientPasswordCallback.class.getName());
			 WSS4JOutInterceptor wssOut = new WSS4JOutInterceptor(outProps);
			 cxfEndpoint.getOutInterceptors().add(wssOut);	 
			return bookService;
	 }

		 
}

