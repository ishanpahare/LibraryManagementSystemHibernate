package com.lms;

import com.lms.dao.LibrarianDao;
import com.lms.dto.*;
import com.lms.utils.ReadJson;
import com.lms.utils.VendorUtil;
import com.lms.views.MainView;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class App {
    public static void main(String[] args) {


        MainView mv = new MainView();
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        LibrarianDao librarianDao = new LibrarianDao();

        /*Adding new Librarians to the DB */
        Librarian librarian1 = new Librarian();
        Librarian librarian2 = new Librarian();
        Librarian librarian3 = new Librarian();
        Librarian librarian4 = new Librarian();
        Librarian librarian5 = new Librarian();
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

        VendorUtil.addBook(v1, session);
        VendorUtil.addBook(v2, session);
        VendorUtil.addBook(v3, session);
        VendorUtil.addBook(v4, session);
        VendorUtil.addBook(v5, session);


        librarian1.setUsername("lib1");
        librarian1.setPassword("password");
        librarianDao.insertLibrarian(librarian1, session);

        librarian2.setUsername("lib2");
        librarian2.setPassword("password");
        librarianDao.insertLibrarian(librarian2, session);

        librarian3.setUsername("lib3");
        librarian3.setPassword("password");
        librarianDao.insertLibrarian(librarian3, session);

        librarian4.setUsername("lib4");
        librarian4.setPassword("password");
        librarianDao.insertLibrarian(librarian4, session);

        librarian5.setUsername("lib5");
        librarian5.setPassword("password");
        librarianDao.insertLibrarian(librarian5, session);


        mv.getMainView(mv, session);

        session.close();
        sessionFactory.close();
    }
}
