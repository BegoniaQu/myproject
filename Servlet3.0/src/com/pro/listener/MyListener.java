package com.pro.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener(value="myListener3.0")
public class MyListener implements ServletContextListener{

	
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		System.out.println(">>>>>>>> contextInitialized");
		
	}
	
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		System.out.println(">>>>>>>> contextDestroyed");
		
	}
}
