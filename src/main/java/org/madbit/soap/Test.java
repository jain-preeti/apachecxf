package org.madbit.soap;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class Test {
	
	@ResponseBody
	@RequestMapping("/jjj")
	public String dd()
	{
		return "jdjdjdjdj";
	}

}
