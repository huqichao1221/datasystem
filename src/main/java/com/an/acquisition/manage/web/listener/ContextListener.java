package com.an.acquisition.manage.web.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.an.acquisition.util.SpringUtil;



@WebListener()
public class ContextListener implements ServletContextListener{
	private static final Logger logger = LogManager.getLogger();

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		logger.info("ContextListener contextInitialized");
		SpringUtil.setApplicationContext(
			WebApplicationContextUtils.getRequiredWebApplicationContext(sce.getServletContext())
		);
		//TimerManager.getInstance().run();
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		SpringUtil.setApplicationContext(null);
	}
}