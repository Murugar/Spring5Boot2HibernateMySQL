package com.iqmsoft.springboot.hibernate.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.iqmsoft.springboot.hibernate.dao.ClientDAO;
import com.iqmsoft.springboot.hibernate.model.Client;

import java.util.List;

@Service
public class ClientService {

    @Autowired
    private ClientDAO customerDao;

    public void setCustomerDao(ClientDAO customerDao) {
        this.customerDao = customerDao;
    }

    @Transactional
    public void addCustomer(Client customer) {
        this.customerDao.addCustomer(customer);
    }

    @Transactional
    public void updateCustomer(Client customer) {
        this.customerDao.updateCustomer(customer);
    }

    @Transactional
    public void removeCustomer(int id) {
        this.customerDao.removeCustomer(id);
    }

    @Transactional
    public Client getCustomerById(int id) {
        return this.customerDao.getCustomerById(id);
    }

    @Transactional
    public List<Client> listCustomers() {
        return this.customerDao.listCustomers();
    }
}
