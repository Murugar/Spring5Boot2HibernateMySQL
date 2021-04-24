package com.iqmsoft.springboot.hibernate.dao;

import org.hibernate.FlushMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.iqmsoft.springboot.hibernate.model.Client;

import java.util.List;




@Repository
public class ClientDAO {
    private static final Logger logger = LoggerFactory.getLogger(ClientDAO.class);

    
    
   
    @Autowired
    private SessionFactory sessionFactory;
    
   
	
    @Transactional(propagation = Propagation.REQUIRED)
    public void addCustomer(Client customer) {
    	
        Session session = this.sessionFactory.getCurrentSession();
        
        Transaction tx = session.beginTransaction();
        session.setHibernateFlushMode(FlushMode.MANUAL);
        session.persist(customer);
        session.saveOrUpdate(customer);
        tx.commit();
        
        logger.info("Customer successfully saved. Customer details: " + customer);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void updateCustomer(Client customer) {
        Session session = this.sessionFactory.getCurrentSession();
        
        Transaction tx = session.beginTransaction();
        session.setHibernateFlushMode(FlushMode.MANUAL);
        
        session.update(customer);
        session.flush();
        tx.commit();
        logger.info("Customer successfully update. Customer details: " + customer);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void removeCustomer(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        Client customer = (Client) session.load(Client.class, Integer.valueOf(id));
        if (customer != null) {
        	Transaction tx = session.beginTransaction();
            session.setHibernateFlushMode(FlushMode.MANUAL);
            session.delete(customer);
            session.flush();
            tx.commit();
            logger.info("Customer successfully removed. Customer details: " + customer);
        }
        
    }

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Client getCustomerById(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        Client customer = (Client) session.load(Client.class, new Integer(id));
        logger.info("Customer successfully loaded. Customer details: " + customer);
        
        return customer;
    }

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<Client> listCustomers() {
       Session session = this.sessionFactory.getCurrentSession();
       List<Client> customerList = session.createQuery("from Client").list();
        for (Client customer : customerList) {
            logger.info("Customer list: " + customer);
        }
       
                   
        return customerList;
    }
}
