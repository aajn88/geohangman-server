package com.doers.geohangman.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author @author <a href="mailto:aajn88@gmail.com">Antonio Jimenez</a>
 *
 */
@Controller
public class HelloWorldController {
	
	@RequestMapping("/hello")
	public String hello(@RequestParam(value = "name", required = false, defaultValue = "World") String name) {
		return "Hello, " + name + "!";
	}
	
}