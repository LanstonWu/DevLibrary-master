package com.sywu.SpringSchedulerTask;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
public class ScheduledApp {
	public static void main(String[] args) {		
		AbstractApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
	}
}