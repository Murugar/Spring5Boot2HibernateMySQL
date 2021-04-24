package com.iqmsoft.springboot.hibernate.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
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
    
   
	
    @Transactional(propagation = Propagation.SUPPORTS)
    public void addCustomer(Client customer) {
        Session session = this.sessionFactory.getCurrentSession();
        session.persist(customer);
        logger.info("Customer successfully saved. Customer details: " + customer);
    }

    @Transactional(propagation = Propagation.MANDATORY)
    public void updateCustomer(Client customer) {
        Session session = this.sessionFactory.getCurrentSession();
        session.update(customer);
        logger.info("Customer successfully update. Customer details: " + customer);
    }

    @Transactional(propagation = Propagation.MANDATORY)
    public void removeCustomer(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        Client customer = (Client) session.load(Client.class, new Integer(id));
        if (customer != null) {
            session.delete(customer);
        }
        logger.info("Customer successfully removed. Customer details: " + customer);
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
