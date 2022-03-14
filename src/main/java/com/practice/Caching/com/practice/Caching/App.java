package com.practice.Caching.com.practice.Caching;

import java.util.ArrayList;

import org.hibernate.HibernateException;
import org.hibernate.Query;
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
	public static void main( String[] args ) throws HibernateException 
	{
		Alien a =null;
		
		Configuration config = new Configuration().configure().addAnnotatedClass(Alien.class);
		
		ServiceRegistry reg=new ServiceRegistryBuilder().applySettings(config.getProperties()).buildServiceRegistry();
		
		SessionFactory sessionFactory=config.buildSessionFactory(reg);
		
		Session session1 =sessionFactory.openSession();
		Query q1=session1.createQuery("from Alien where aid=101");
		q1.setCacheable(true);
		try {
			Transaction tx=session1.beginTransaction();
			a=(Alien)q1.uniqueResult();
			System.out.println("1111111111"+a);
			tx.commit();

		}
		catch(Exception e){
			e.printStackTrace();   
			System.out.println("Catch"+e);
		}
		Session session2=sessionFactory.openSession();
		try {
			
			Transaction tx=session1.beginTransaction();
			Query q2=session2.createQuery("from Alien where aid=101");
			q2.setCacheable(true);
			a=(Alien)q2.uniqueResult();
			System.out.println("222222222222222"+a);
			tx.commit();
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		session1.close();
		session2.close();
	}
}