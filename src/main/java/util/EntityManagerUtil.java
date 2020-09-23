package util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityManagerUtil {
	
	private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("CartasPU");

	public static EntityManager getEntityManager() {
		
		return emf.createEntityManager();
		
	}

}