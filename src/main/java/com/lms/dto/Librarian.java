package com.lms.dto;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
public class Librarian {

   @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int lid;
    private String username;
    private String password;
    /*
    @ManyToMany
    @JoinTable(
            name = "ISSUED_BOOKS",
            joinColumns = @JoinColumn(name="librarian_id"),
            inverseJoinColumns = @JoinColumn(name = "issued_id")
    )
    private Collection<IssuedBook> issuedBooks=new ArrayList<>();

    public Collection<IssuedBook> getIssuedBooks() {
        return issuedBooks;
    }

    public void setIssuedBooks(Collection<IssuedBook> issuedBooks) {
        this.issuedBooks = issuedBooks;
    } */

    public int getLid() {
        return lid;
    }

    public void setLid(int lid) {
        this.lid = lid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
