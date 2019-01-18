package com.lms.utils;


import com.lms.dao.BookDao;
import com.lms.dto.Book;
import org.hibernate.Session;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ReadJson {
    public static void getJson(Session session) {
        JSONParser parser = new JSONParser();
        try (FileReader reader = new FileReader("books.json")) {
            Object obj = parser.parse(reader);
            JSONArray booklist = (JSONArray) obj;
            booklist.forEach(book -> parseBookObject((JSONObject) book,session));
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private static void parseBookObject(JSONObject book,Session session) {

        JSONObject bookObject = (JSONObject) book.get("book");
        BookDao bookDao = new BookDao();
        Book newBook = new Book();
        String name = (String) bookObject.get("name");
        Integer isbn = (int) ((long) bookObject.get("isbn"));
        String author = (String) bookObject.get("author");
        Integer price = (int) ((long) bookObject.get("price"));
        String publisher = (String) bookObject.get("publisher");

        newBook.setName(name);
        newBook.setPrice(price);
        newBook.setPublisher(publisher);
        newBook.setIsbn(isbn);
        newBook.setAuthor(author);

        bookDao.insertBook(newBook,session);
    }
}
