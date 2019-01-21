package com.lms.views;


import com.lms.dao.*;
import com.lms.dto.*;
import com.lms.utils.DateUtil;
import org.hibernate.Session;

import javax.persistence.Query;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class View {

    public static void librarianLoginView(Session session, MainView mv) {
        Scanner sc = new Scanner(System.in);
        LibrarianDao librarianDao = new LibrarianDao();
        System.out.println("-------------LIBRARIAN LOGIN-------------");

        sc.nextLine();
        System.out.println("USERNAME:");
        String username = sc.nextLine();

        System.out.println("PASSWORD: ");
        String password = sc.nextLine();

        System.out.println("ID:");
        int lid = sc.nextInt();

        Librarian lib = librarianDao.getLibrarianById(lid, session);

        if (lib.getUsername().equals(username) && lib.getPassword().equals(password)) {
            //go to librarian main menu
            librarianView(lid, session, mv);
            //System.out.println("Correct Credentials!");
        } else {
            System.out.println("INVALID CREDENTIALS!");
            System.out.println("1.Try Again\n2.Exit");
            int choice = sc.nextInt();

            if (choice == 1)
                librarianLoginView(session, mv);
            else
                System.exit(0);
        }
    }

    public static void customerLoginView(Session session, MainView mv) {
        Scanner sc = new Scanner(System.in);
        CustomerDao customerDao = new CustomerDao();
        System.out.println("Enter Customer Id(0 to Exit): ");
        int id = sc.nextInt();

        if (id == 0) {
            System.exit(0);
        }
        Customer customer = customerDao.getCustomerById(id, session);
        if (customer != null) {
            userView(session, customer.getCid(), mv);
            //System.out.println("Yes, we Exists!");
        } else {
            System.out.println("Invalid Id, Try Again!");
            customerLoginView(session, mv);
        }
    }

    public static void userView(Session session, int cid, MainView mv) {
        Scanner sc = new Scanner(System.in);
        CustomerDao customerDao = new CustomerDao();
        System.out.println("--------WELCOME CUSTOMER " + cid + "-------------");
        System.out.println("1.View Information\n2.Available Books in Library\n3.Check Fine\n4.Logout");
        int choice = sc.nextInt();
        switch (choice) {
            case 1: {
                Customer customer = customerDao.getCustomerById(cid, session);
                System.out.println("ID: " + customer.getCid());
                System.out.println("Name: " + customer.getName());
                System.out.println("-------LIST OF BOOKS ISSUED-------");
                Collection<IssuedBook> issuedBooks = customer.getIssuedBooks();
                for (IssuedBook issuedBook : issuedBooks) {
                    System.out.println("Name: " + issuedBook.getName());
                    System.out.println("ISBN: " + issuedBook.getIsbn());
                    System.out.println("Author: " + issuedBook.getAuthor());
                    System.out.println();
                }
                System.out.println();
            }
            break;
            case 2: {
                BookDao bookDao = new BookDao();
                System.out.println("----------Library Stock------------");
                System.out.println();
                for (Book book : bookDao.getBookList(session)) {
                    System.out.println("UID: " + book.getUid());
                    System.out.println("Name: " + book.getName());
                    System.out.println("ISBN: " + book.getIsbn());
                    System.out.println("Author: " + book.getAuthor());
                    System.out.println("Publisher: " + book.getPublisher());
                    System.out.println("Price: " + book.getPrice());
                    System.out.println();
                }

            }
            break;
            case 3: {
                System.out.println("----------Fine---------");
                //int totalFine = ReturnBook.calculateFine(cid, connect);
                //System.out.println("Total Fine: " + totalFine);
            }
            break;
            case 4: {
                mv.getMainView(mv, session);
            }
            break;
            default: {
                System.out.println("Enter a valid choice!");
            }
            break;
        }
        userView(session, cid, mv);
    }

    public static void librarianView(int lid, Session session, MainView mv) {
        Scanner sc = new Scanner(System.in);
        CustomerDao customerDao = new CustomerDao();

        System.out.println("----------------Welcome Librarian---------------");
        System.out.println();
        System.out.println("Operation to perform: ");
        System.out.println("1.Issue Book\n" + "2.Return Book\n" + "3.Add Book\n" + "4.Search Book\n"
                + "5.Add Customer\n" + "6.View Customers\n" + "7.Check Stock\n" + "8.Request Vendor\n" + "9.LogOut");
        int choice = sc.nextInt();

        switch (choice) {
            case 1: {
                getIssueView(lid, session, mv);
            }
            break;
            case 2: {
                returnView(lid, session, mv);
            }
            break;

            case 3: {

                addBookView(lid, session, mv);
            }
            break;
            case 4: {
                BookDao bookDao = new BookDao();
                System.out.println("Enter uid of book: ");
                int uid = sc.nextInt();
                Book book = bookDao.getBookById(uid, session);
                if (book == null) {
                    System.out.println("Book Not in Library Stock");
                } else {
                    System.out.println("Book Details: ");
                    System.out.println("UID: " + book.getUid());
                    System.out.println("Name: " + book.getName());
                    System.out.println("ISBN: " + book.getIsbn());
                    System.out.println("Author: " + book.getAuthor());
                    System.out.println("Publisher: " + book.getPublisher());
                    System.out.println("Price: " + book.getPrice());
                    System.out.println();
                }
            }
            break;

            case 5: {
                System.out.println("Enter Name of Customer: ");
                String name = sc.next();
                Customer customer = new Customer();
                customer.setName(name);
                customerDao.insertCustomer(customer, session);
                System.out.println("Customer added successfully!");
            }
            break;

            case 6: {
                //List of Customers
                System.out.println("----------Customer List----------");
                for (Customer customer : customerDao.getCustomerList(session)) {
                    System.out.println("Name: " + customer.getName());
                    System.out.println("ID: " + customer.getCid());
                    System.out.println("Issued Books: ");
                    Collection<IssuedBook> issuedBooks = customer.getIssuedBooks();
                    for (IssuedBook issuedBook : issuedBooks) {
                        System.out.println("Name: " + issuedBook.getName());
                        System.out.println("ISBN: " + issuedBook.getIsbn());
                        System.out.println("Author: " + issuedBook.getAuthor());
                        System.out.println();
                    }
                    System.out.println();
                }

            }
            break;
            case 7: {
                // Check Stock
                BookDao bookDao = new BookDao();
                System.out.println("----------Library Stock------------");
                System.out.println();
                for (Book book : bookDao.getBookList(session)) {
                    System.out.println("UID: " + book.getUid());
                    System.out.println("Name: " + book.getName());
                    System.out.println("ISBN: " + book.getIsbn());
                    System.out.println("Author: " + book.getAuthor());
                    System.out.println("Publisher: " + book.getPublisher());
                    System.out.println("Price: " + book.getPrice());
                    System.out.println();
                }

            }
            break;

            case 8: {
                getVendorView(lid, session, mv);
            }
            break;
            case 9: {

                mv.getMainView(mv, session);
            }
            break;
            default: {
                System.out.println("Enter A valid Option!");

            }
            break;
        }
        librarianView(lid, session, mv);
    }

    public static void getIssueView(int lid, Session session, MainView mv) {
        Scanner sc = new Scanner(System.in);
        CustomerDao customerDao = new CustomerDao();
        IssuedBookDao issuedBookDao = new IssuedBookDao();
        BookDao bookDao = new BookDao();
        LibrarianDao librarianDao = new LibrarianDao();

        System.out.println("----------Issue Book----------");
        System.out.println("Enter Id of Customer: ");
        int cid = sc.nextInt();
        System.out.println("Enter uid of book to be issued: ");
        int uid = sc.nextInt();

        Customer customer = customerDao.getCustomerById(cid, session);
        Book book = bookDao.getBookById(uid, session);
        Librarian librarian = librarianDao.getLibrarianById(lid, session);

        if (customer == null) {
            System.out.println("Customer does not exist! Try with a different customer id");
            return;
        }

        if (book == null) {
            System.out.println("Book not in stock. Try a different isbn!");
            return;
        }

        if (customer.getIssuedBooks().size() >= 3) {
            System.out.println("Already 3 books issued! Cannot issue more books!");
            return;
        }

        IssuedBook issuedBook = new IssuedBook();

        issuedBook.setName(book.getName());
        issuedBook.setIsbn(book.getIsbn());
        issuedBook.setPrice(book.getPrice());
        issuedBook.setAuthor(book.getAuthor());
        issuedBook.setPublisher(book.getPublisher());

        issuedBook.getCustomers().add(customer);
        customer.getIssuedBooks().add(issuedBook);

        issuedBook.getLibrarians().add(librarian);
        librarian.getIssuedBooks().add(issuedBook);

        issuedBookDao.insertIssuedBook(issuedBook, session);
        bookDao.deleteBook(book,session);
    }

    public static void returnView(int lid, Session session, MainView mv) {

        Scanner sc = new Scanner(System.in);
        IssuedBookDao issuedBookDao = new IssuedBookDao();
        IssuedBook issuedBook;
        CustomerDao customerDao = new CustomerDao();
        Customer customer;
        LibrarianDao librarianDao = new LibrarianDao();
        Librarian librarian;
        BookDao bookDao = new BookDao();
        Book book = new Book();
        System.out.println("---------Return Book-----------");
        System.out.println("Enter the UID of the book: ");
        int uid = sc.nextInt();
        System.out.println("Enter the Id of the customer: ");
        int cid = sc.nextInt();
        issuedBook = issuedBookDao.getIssuedBookById(uid, session);
        customer = customerDao.getCustomerById(cid, session);
        librarian = librarianDao.getLibrarianById(lid, session);

        if (issuedBook == null) {
            System.out.println("Book with given uid not issued! Check and try again!");
            return;
        }

        if (customer == null) {
            System.out.println("Customer does not exist! Check and try again!");
            return;
        }
        long difference = DateUtil.differenceInDays(issuedBook.getReturnDate(), new Date());
        long fine = difference * 3;
        librarian.getIssuedBooks().remove(issuedBook);
        issuedBook.getLibrarians().remove(librarian);

        customer.getIssuedBooks().remove(issuedBook);
        issuedBook.getCustomers().remove(customer);

        book.setName(issuedBook.getName());
        book.setAuthor(issuedBook.getAuthor());
        book.setPrice(issuedBook.getPrice());
        book.setPublisher(issuedBook.getPublisher());
        book.setIsbn(issuedBook.getIsbn());

        issuedBookDao.deleteIssuedBook(issuedBook, session);
        bookDao.insertBook(book,session);

        //calculating fine
        if (fine < 0) {
            fine = 0;
        }
        System.out.println("Fine to be paid: " + fine);

    }

    public static void addBookView(int lid, Session session, MainView mv) {
        Scanner sc = new Scanner(System.in);
        BookDao bookDao = new BookDao();

        System.out.println("-------ADD BOOK--------");
        System.out.println("Enter ISBN of Book: ");
        int isbn = sc.nextInt();
        sc.nextLine();
        System.out.println("Enter Name of book: ");
        String name = sc.nextLine();
        System.out.println("Enter Price of Book: ");
        int price = sc.nextInt();
        sc.nextLine();
        System.out.println("Enter Author: ");
        String author = sc.nextLine();
        System.out.println("Enter Publisher: ");
        String publisher = sc.nextLine();

        Book book = new Book();
        book.setName(name);
        book.setIsbn(isbn);
        book.setAuthor(author);
        book.setPublisher(publisher);
        book.setPrice(price);
        bookDao.insertBook(book, session);
    }

    /*
        public static void addCustomerView(int lid, DBConnect connect, MainView mv) {
            Scanner sc = new Scanner(System.in);
            System.out.println("-----------ADD NEW USER-----------");
            System.out.println("Enter customer Name: ");
            String name = sc.nextLine();
            System.out.println("Enter customer id: ");
            int id = sc.nextInt();
            int cid = VerifyUtil.isCustomer(id, connect);
            if (cid == 0) {
                Customer customer = new Customer(name, id);
                CustomerDaoImpl newCustomer = new CustomerDaoImpl();
                newCustomer.save(customer, connect);
                System.out.println("Customer added successfully!");
            } else {
                System.out.println("User already exists!");
            }

        }
    */
    public static void getVendorView(int lid, Session session, MainView mv) {
        Scanner sc = new Scanner(System.in);
        VendorDao vendorDao = new VendorDao();
        System.out.println("--------List of Vendors--------");
        System.out.println("1.List of vendors and books\n2.Check Vendor Inventory\n3.Order Books from vendor\n4.Back");
        int choice = sc.nextInt();
        switch (choice) {
            case 1: {
                System.out.println("LIST OF VENDORS: ");
                vendorDao.getVendorList(session);
            }
            break;
            case 2: {
                System.out.println("Enter vendor Id to get a list of books: ");
                int vid = sc.nextInt();
                Vendor vendor = vendorDao.getVendorById(vid, session);
                if (vendor == null) {
                    System.out.println("Vendor with the specified ID does not exist!");
                    break;
                }
                for (VendorBook vendorBook : vendor.getVendorBooks()) {
                    System.out.println("Name: " + vendorBook.getName());
                    System.out.println("ISBN: " + vendorBook.getIsbn());
                    System.out.println("Author: " + vendorBook.getAuthor());
                    System.out.println("Publisher: " + vendorBook.getPublisher());
                    System.out.println("Price: " + vendorBook.getPrice());
                    System.out.println();
                }
            }
            break;
            case 3: {
                BookDao bookDao = new BookDao();
                System.out.println("Enter id of Vendor: ");
                int vid = sc.nextInt();
                Vendor vendor = vendorDao.getVendorById(vid, session);
                if (vendor == null) {
                    System.out.println("Vendor does not exist!");
                    break;
                }
                System.out.println("Enter the isbn of book you wish to purchase: ");
                int isbn = sc.nextInt();
                System.out.println("Enter the quantity you wish to purchase: ");
                int quantity = sc.nextInt();
                for (VendorBook vendorBook : vendor.getVendorBooks()) {
                    if (vendorBook.getIsbn() == isbn) {
                        for (int i = 0; i < quantity; i++) {
                            Book book = new Book();
                            book.setName(vendorBook.getName());
                            book.setAuthor(vendorBook.getAuthor());
                            book.setIsbn(vendorBook.getIsbn());
                            book.setPrice(vendorBook.getIsbn());
                            book.setPublisher(vendorBook.getPublisher());

                            bookDao.insertBook(book, session);
                        }
                    }
                }
            }
                break;
                case 4: {
                    librarianView(lid, session, mv);
                }
                default: {
                    System.out.println("Enter a Valid choice!");
                }
                break;
            }
            getVendorView(lid, session, mv);

        }
}



