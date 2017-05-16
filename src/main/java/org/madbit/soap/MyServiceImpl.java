package org.madbit.soap;

import org.madbit.myservice.MyService;
import org.madbit.myservice.SumRequest;
import org.madbit.myservice.SumResponse;

public class MyServiceImpl implements MyService{

	public SumResponse sum(SumRequest parameters) {
		  int sum = 0;
	        for(Integer i: parameters.getAddend()){
	            sum += i;
	        }
	        SumResponse response = new SumResponse();
	        response.setSum(sum);
	        return response;
	    }
	//http://localhost:8080/Apachecxf/soap/MyService?wsdl
}
