package com.lms;

import com.lms.dao.CustomerDao;
import com.lms.dao.LibrarianDao;
import com.lms.dto.Book;
import com.lms.dto.Customer;
import com.lms.dto.IssuedBook;
import com.lms.dto.Librarian;
import com.lms.utils.HibernateUtil;
import com.lms.utils.ReadJson;
import com.lms.views.MainView;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class App {
    public static void main(String[] args) {


        MainView mv = new MainView();
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        //Session session = HibernateUtil.getSession();
        Session session = sessionFactory.openSession();
        LibrarianDao librarianDao = new LibrarianDao();
        //CustomerDao customerDao = new CustomerDao();
        Librarian librarian = new Librarian();
        // Customer customer = new Customer();

        /* Run Once to add Books to DB */
        ReadJson.getJson(session);

        librarian.setUsername("lib1");
        librarian.setPassword("password");

        // customer.setName("cust1");

        librarianDao.insertLibrarian(librarian, session);
        // customerDao.insertCustomer(customer,session);

        mv.getMainView(mv, session);

        session.close();
        sessionFactory.close();
    }
}
