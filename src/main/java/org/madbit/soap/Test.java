package org.madbit.soap;

import org.madbit.myservice.MyService;
import org.madbit.myservice.SumRequest;
import org.madbit.myservice.SumResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class Test {
	
	@Autowired	
	MyService getfactory;
	
	
	@ResponseBody
	@RequestMapping("/jjj")
	public String dd()
	{
		SumRequest sum=new SumRequest();
		sum.getAddend().add(1);
	SumResponse sumResponse=	getfactory.sum(sum)	;
	System.out.println("sum is:"+sumResponse.getSum());
		return "jdjdjdjdj";
	}

}
