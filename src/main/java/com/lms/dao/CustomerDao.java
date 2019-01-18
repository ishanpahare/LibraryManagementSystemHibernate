package com.lms.dao;

import com.lms.dto.Customer;
import com.lms.dto.Librarian;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.Query;
import java.util.List;

public class CustomerDao {
    public List<Customer> getCustomerList(Session session) {

        List<Customer> customerList = null;
        try {
            String queryStr = "select customer from Customer customer";
            Query query = session.createQuery(queryStr);
            customerList = query.getResultList();
        } catch (Exception ex) {
            ex.printStackTrace();
            // handle exception here
        }
        return customerList;
    }

    public Customer getCustomerById(int cid, Session session) {

        Customer customer = null;
        try {
            String queryStr = "select customer from Customer customer";
            customer = session.get(Customer.class, cid);

        } catch (Exception ex) {
            ex.printStackTrace();
            // handle exception here
        }
        return customer;
    }

    public void insertCustomer(Customer customer, Session session) {

        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(customer);
            System.out.println("inserted employee: " + customer.getName());
            transaction.commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            // handle exception here
            if (transaction != null) transaction.rollback();
        }
    }

    public void deleteCustomer(Customer customer, Session session) {

        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.delete(customer);
            transaction.commit();
            System.out.println("deleted customer: " + customer.getName());
        } catch (Exception ex) {
            ex.printStackTrace();
            // handle exception here
            if (transaction != null) transaction.rollback();
        }
    }
}
