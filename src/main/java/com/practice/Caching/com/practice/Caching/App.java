package com.practice.Caching.com.practice.Caching;

import java.util.ArrayList;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;



/**
 * Hello world!
 *
 */
public class App 
{
	public static void main( String[] args ) 
	{
		Alien a =null;
		
		Configuration config = new Configuration().configure().addAnnotatedClass(Alien.class);
		
		ServiceRegistry reg=new ServiceRegistryBuilder().applySettings(config.getProperties()).buildServiceRegistry();
		
		SessionFactory sessionFactory=config.buildSessionFactory(reg);
		
		Session session =sessionFactory.openSession();

		try {
			Transaction tx=session.beginTransaction();
			a=(Alien)session.get(Alien.class,101);
			System.out.println(a);
			tx.commit();

		}
		catch(Exception e){
			e.printStackTrace();   
			System.out.println("Catch"+e);
		}

		session.close();
	}
}