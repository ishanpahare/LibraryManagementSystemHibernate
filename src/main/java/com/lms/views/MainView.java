package com.lms.views;

import org.hibernate.Session;

import java.util.Scanner;

public class MainView {
    Scanner sc=new Scanner(System.in);
    public void getMainView(MainView mv, Session session){
        System.out.println("------------Welcome to Library Management System-------");
        System.out.println("1.Librarian\n2.User\n3.Exit");
        int choice = sc.nextInt();
        switch (choice) {
            case 1: {
                // Librarian View
                View.librarianLoginView(session,mv);
            }
            break;

            case 2: {
                // CustomerView
               View.customerLoginView(session,mv);
            }
            break;
            case 3:
                System.exit(0);
            default: {
                System.out.println("Enter a valid choice!");
            }
            getMainView(mv,session);
            break;
        }
    }
    }
