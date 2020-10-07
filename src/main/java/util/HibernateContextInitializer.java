package util;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class HibernateContextInitializer implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		EntityManagerUtil.getEntityManager().close();
	}

	@Override
	public void contextInitialized(ServletContextEvent event) {
		EntityManagerUtil.getEntityManager();
	}

}
