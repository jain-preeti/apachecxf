package org.madbit.soap;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Home {
	
	@RequestMapping("/test")
	public String jj()
	{
		return "jj";
	}

}
