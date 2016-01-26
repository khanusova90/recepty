package cz.ppro.recepty.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class StartController {
	
	private static final Log logger = LogFactory.getLog(StartController.class);
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String start(){
		logger.info("App started");
		return "home";
	}
	//@RequestMapping(value = "/home", method = RequestMethod.GET)
	//public String start(){
	//	return "start";
	//}
}
