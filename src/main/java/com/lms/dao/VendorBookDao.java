package com.lms.dao;

import com.lms.dto.VendorBook;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.Query;
import java.util.List;

public class VendorBookDao {
    public List<VendorBook> getVendorBookList(Session session) {

        List<VendorBook> vendorBookList = null;
        try {
            String queryStr = "select vendorBook from VendorBook vendorBook";
            Query query = session.createQuery(queryStr);
            vendorBookList = query.getResultList();
        } catch (Exception ex) {
            ex.printStackTrace();
            // handle exception here
        }
        return vendorBookList;
    }

    public VendorBook getVendorBookById(int uid, Session session) {

        VendorBook vendorBook = null;
        try {
            String queryStr = "select vendorBook from VendorBook vendorBook";
            vendorBook = session.get(VendorBook.class, uid);

        } catch (Exception ex) {
            ex.printStackTrace();
            // handle exception here
        }
        return vendorBook;
    }

    public void insertVendorBook(VendorBook vendorBook, Session session) {

        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(vendorBook);
            System.out.println("inserted book: " + vendorBook.getName());
            transaction.commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            // handle exception here
            if (transaction != null) transaction.rollback();
        }
    }

    public void deleteBook(VendorBook vendorBook, Session session) {

        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.delete(vendorBook);
            transaction.commit();
            System.out.println("deleted book: " + vendorBook.getName());
        } catch (Exception ex) {
            ex.printStackTrace();
            // handle exception here
            if (transaction != null) transaction.rollback();
        }
    }
}
