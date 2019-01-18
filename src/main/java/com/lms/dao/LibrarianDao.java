package com.lms.dao;

import com.lms.dto.Librarian;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.Query;
import java.util.List;

public class LibrarianDao {
    public List<Librarian> getLibrarianList(Session session) {

        List<Librarian> librarianList = null;
        try {
            String queryStr = "select librarian from Librarian librarian";
            Query query = session.createQuery(queryStr);
            librarianList = query.getResultList();
        } catch (Exception ex) {
            ex.printStackTrace();
            // handle exception here
        }
        return librarianList;
    }

    public Librarian getLibrarianById(int lid, Session session) {

        Librarian librarian = null;
        try {
            String queryStr = "select librarian from Librarian librarian";
            librarian = session.get(Librarian.class, lid);

        } catch (Exception ex) {
            ex.printStackTrace();
            // handle exception here
        }
        return librarian;
    }

    public void insertLibrarian(Librarian librarian, Session session) {

        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(librarian);
            System.out.println("inserted employee: " + librarian.getUsername());
            transaction.commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            // handle exception here
            if (transaction != null) transaction.rollback();
        }
    }

    public void deleteLibrarian(Librarian librarian, Session session) {

        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.delete(librarian);
            transaction.commit();
            System.out.println("deleted librarian: " + librarian.getUsername());
        } catch (Exception ex) {
            ex.printStackTrace();
            // handle exception here
            if (transaction != null) transaction.rollback();
        }
    }
}
