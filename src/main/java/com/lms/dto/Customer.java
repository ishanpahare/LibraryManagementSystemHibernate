package com.lms.dto;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cid;
    private String name;

    @ManyToMany
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinTable(name = "CUSTOMER_ISSUED",
            joinColumns = @JoinColumn(name = "CUSTOMER_ID"),
            inverseJoinColumns = @JoinColumn(name = "ISSUED_ID"))
    private Collection<IssuedBook> issuedBooks = new ArrayList<IssuedBook>();

    public Collection<IssuedBook> getIssuedBooks() {
        return issuedBooks;
    }

    public void setIssuedBooks(Collection<IssuedBook> issuedBooks) {
        this.issuedBooks = issuedBooks;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
