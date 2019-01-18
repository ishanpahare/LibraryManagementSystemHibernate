package com.lms.dao;

import com.lms.dto.Book;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.Query;
import java.util.List;

public class BookDao {
    public List<Book> getBookList(Session session) {

        List<Book> bookList = null;
        try {
            String queryStr = "select book from Book book";
            Query query = session.createQuery(queryStr);
            bookList = query.getResultList();
        } catch (Exception ex) {
            ex.printStackTrace();
            // handle exception here
        }
        return bookList;
    }

    public Book getBookById(int uid, Session session) {

        Book book = null;
        try {
            String queryStr = "select book from Book book";
            book = session.get(Book.class, uid);

        } catch (Exception ex) {
            ex.printStackTrace();
            // handle exception here
        }
        return book;
    }

    public void insertBook(Book book, Session session) {

        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(book);
            System.out.println("inserted book: " + book.getName());
            transaction.commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            // handle exception here
            if (transaction != null) transaction.rollback();
        }
    }

    public void deleteBook(Book book, Session session) {

        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.delete(book);
            transaction.commit();
            System.out.println("deleted book: " + book.getName());
        } catch (Exception ex) {
            ex.printStackTrace();
            // handle exception here
            if (transaction != null) transaction.rollback();
        }
    }
}
