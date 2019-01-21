package com.lms;

import com.lms.dao.CustomerDao;
import com.lms.dao.LibrarianDao;
import com.lms.dto.*;
import com.lms.utils.HibernateUtil;
import com.lms.utils.ReadJson;
import com.lms.utils.VendorUtil;
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

        /*Adding books to list of vendors*/
        Vendor v1 = new Vendor();
        v1.setName("vendor 1");
        Vendor v2 = new Vendor();
        v2.setName("vendor 2");
        Vendor v3 = new Vendor();
        v3.setName("vendor 3");
        Vendor v4 = new Vendor();
        v4.setName("vendor 4");
        Vendor v5 = new Vendor();
        v5.setName("vendor 5");

        VendorUtil.addBook(v1,session);
        VendorUtil.addBook(v2,session);
        VendorUtil.addBook(v3,session);
        VendorUtil.addBook(v4,session);
        VendorUtil.addBook(v5,session);


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
