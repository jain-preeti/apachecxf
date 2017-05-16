package org.madbit.soap;

import java.net.MalformedURLException;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.madbit.myservice.MyService;
import org.madbit.myservice.SumRequest;
import org.madbit.myservice.SumResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class MyServiceClient {
	
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
	 MyService getfactory()
	 {
		 String serviceUrl = "http://localhost:8080/Apachecxf/soap/MyService";
		 JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
			factory.setServiceClass(MyService.class);
			factory.setAddress(serviceUrl);
			MyService bookService = (MyService) factory.create();
			return bookService;
	 }
}

