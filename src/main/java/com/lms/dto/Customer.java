package com.lms.dto;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
public class Customer {

    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cid;
    private String name;

  /*  @OneToMany(mappedBy = "customer",cascade = CascadeType.ALL)
    private Collection<IssuedBook> issuedBooks = new ArrayList<IssuedBook>();*/


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
/*
    public Collection<IssuedBook> getIssuedBooks() {
        return issuedBooks;
    }

    public void setIssuedBooks(Collection<IssuedBook> issuedBooks) {
        this.issuedBooks = issuedBooks;
    } */
}
