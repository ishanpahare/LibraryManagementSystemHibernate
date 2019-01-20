package com.lms.dao;

import com.lms.dto.Book;
import com.lms.dto.IssuedBook;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.Query;
import java.util.List;

public class IssuedBookDao {
    public List<IssuedBook> getIssuedBookList(Session session) {

        List<IssuedBook> issuedBookList = null;
        try {
            String queryStr = "select issuedBook from IssuedBook issuedBook";
            Query query = session.createQuery(queryStr);
            issuedBookList = query.getResultList();
        } catch (Exception ex) {
            ex.printStackTrace();
            // handle exception here
        }
        return issuedBookList;
    }

    public IssuedBook getIssuedBookById(int uid, Session session) {

        IssuedBook issuedBook = null;
        try {
            String queryStr = "select issuedBook from IssuedBook issuedBook";
            issuedBook = session.get(IssuedBook.class, uid);

        } catch (Exception ex) {
            ex.printStackTrace();
            // handle exception here
        }
        return issuedBook;
    }

    public void insertIssuedBook(IssuedBook issuedBook, Session session) {

        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(issuedBook);
            System.out.println("inserted IssuedBook: " + issuedBook.getName());
            transaction.commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            // handle exception here
            if (transaction != null) transaction.rollback();
        }
    }

    public void deleteIssuedBook(IssuedBook issuedBook, Session session) {

        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.delete(issuedBook);
            transaction.commit();
            System.out.println("deleted issuedBook: " + issuedBook.getName());
        } catch (Exception ex) {
            ex.printStackTrace();
            // handle exception here
            if (transaction != null) transaction.rollback();
        }
    }
}
