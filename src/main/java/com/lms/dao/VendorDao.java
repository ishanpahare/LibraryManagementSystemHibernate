package com.lms.dao;

import com.lms.dto.Vendor;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.Query;
import java.util.List;

public class VendorDao {
    public List<Vendor> getVendorList(Session session) {

        List<Vendor> vendorList = null;
        try {
            String queryStr = "select vendor from Vendor vendor";
            Query query = session.createQuery(queryStr);
            vendorList = query.getResultList();
        } catch (Exception ex) {
            ex.printStackTrace();
            // handle exception here
        }
        return vendorList;
    }

    public Vendor getVendorById(int vid, Session session) {

        Vendor vendor = null;
        try {
            String queryStr = "select customer from Customer customer";
            vendor = session.get(Vendor.class, vid);

        } catch (Exception ex) {
            ex.printStackTrace();
            // handle exception here
        }
        return vendor;
    }

    public void insertVendor(Vendor vendor, Session session) {

        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(vendor);
            System.out.println("inserted employee: " + vendor.getName());
            transaction.commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            // handle exception here
            if (transaction != null) transaction.rollback();
        }
    }

    public void deleteVendor(Vendor vendor, Session session) {

        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.delete(vendor);
            transaction.commit();
            System.out.println("deleted vendor: " + vendor.getName());
        } catch (Exception ex) {
            ex.printStackTrace();
            // handle exception here
            if (transaction != null) transaction.rollback();
        }
    }
}
