package com.helloword;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Application {
	public static void main(String[] args) {
		ApplicationContext applicationContext = new  ClassPathXmlApplicationContext("job_configuration.xml");
	}
}
