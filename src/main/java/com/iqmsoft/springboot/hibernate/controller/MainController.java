package com.iqmsoft.springboot.hibernate.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.iqmsoft.springboot.hibernate.model.Client;
import com.iqmsoft.springboot.hibernate.service.ClientService;

@Controller
public class MainController {

    @Autowired
    private ClientService clientService;

    @Autowired
    public void setClientService(ClientService clientService) {
        this.clientService = clientService;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String initializeClient(Model model) {
        return "index";
    }

    @RequestMapping(value = "customers", method = RequestMethod.GET)
    public String listCustomers(Model model) {
        model.addAttribute("customer", new Client());
        model.addAttribute("listCustomers", this.clientService.listCustomers());
        return "customers";
    }

    @RequestMapping(value = "/customer/add", method = RequestMethod.POST)
    public String addCustomer(@ModelAttribute("customer") Client customer) {
        if (customer.getId() == 0) {
            this.clientService.addCustomer(customer);
        } else {
            this.clientService.updateCustomer(customer);
        }
        return "redirect:/customers";
    }

    @RequestMapping("/remove/{id}")
    public String removeCustomer(@PathVariable("id") int id) {
        this.clientService.removeCustomer(id);
        return "redirect:/customers";
    }

    @RequestMapping("/edit/{id}")
    public String editCustomer(@PathVariable("id") int id, Model model) {
        model.addAttribute("customer", this.clientService.getCustomerById(id));
        model.addAttribute("listCustomers", this.clientService.listCustomers());
        return "customers";
    }

    @RequestMapping("customerdata/{id}")
    public String customerData(@PathVariable("id") int id, Model model) {
        model.addAttribute("customer", this.clientService.getCustomerById(id));
        return "customerdata";
    }
}
