package com.iqmsoft.springboot.hibernate.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.iqmsoft.springboot.hibernate.model.Client;

import java.util.List;

@Repository
public class ClientDAO {
    private static final Logger logger = LoggerFactory.getLogger(ClientDAO.class);

    @Autowired
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void addCustomer(Client customer) {
        Session session = this.sessionFactory.getCurrentSession();
        session.persist(customer);
        logger.info("Customer successfully saved. Customer details: " + customer);
    }

    public void updateCustomer(Client customer) {
        Session session = this.sessionFactory.getCurrentSession();
        session.update(customer);
        logger.info("Customer successfully update. Customer details: " + customer);
    }

    public void removeCustomer(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        Client customer = (Client) session.load(Client.class, new Integer(id));
        if (customer != null) {
            session.delete(customer);
        }
        logger.info("Customer successfully removed. Customer details: " + customer);
    }

    public Client getCustomerById(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        Client customer = (Client) session.load(Client.class, new Integer(id));
        logger.info("Customer successfully loaded. Customer details: " + customer);
        return customer;
    }

    public List<Client> listCustomers() {
        Session session = this.sessionFactory.getCurrentSession();
        List<Client> customerList = session.createQuery("from Client").list();
        for (Client customer : customerList) {
            logger.info("Customer list: " + customer);
        }
        return customerList;
    }
}
