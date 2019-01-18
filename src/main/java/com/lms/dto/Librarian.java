package com.lms.dto;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
public class Librarian {

   @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int lid;
    private String username;
    private String password;
    @ManyToMany
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinTable(name = "LIBRARIAN_ISSUED",
    joinColumns = @JoinColumn(name = "LIBRARIAN_ID"),
    inverseJoinColumns = @JoinColumn(name="ISSUED_ID"))
    private Collection<IssuedBook> issuedBooks = new ArrayList<IssuedBook>();

    public Collection<IssuedBook> getIssuedBooks() {
        return issuedBooks;
    }

    public void setIssuedBooks(Collection<IssuedBook> issuedBooks) {
        this.issuedBooks = issuedBooks;
    }

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
