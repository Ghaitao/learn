package init;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class FFSDataInit {
	private EntityManagerFactory entityManagerFactory;
	private EntityManager entityManager;
	private EntityTransaction transaction;
	@Before
	public void init(){
		entityManagerFactory=Persistence.createEntityManagerFactory("learn");
		
		entityManager=entityManagerFactory.createEntityManager();
		transaction=entityManager.getTransaction();
		transaction.begin();
	}
	@After
	public void destory(){
		transaction.commit();
		entityManager.close();
		entityManagerFactory.close();
	}
	@Test
	public void queryFwb(){
		System.out.println("初始化");
	}
	

}
