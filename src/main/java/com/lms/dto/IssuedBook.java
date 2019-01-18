package com.lms.dto;

import com.lms.utils.DateUtil;

import javax.persistence.*;
import java.util.Date;

@Entity
public class IssuedBook extends Book{

    @Temporal(TemporalType.DATE)
    private Date issueDate = new Date();
    @Temporal(TemporalType.DATE)
    private Date returnDate = DateUtil.addDays(this.issueDate,14);

    /*
    @ManyToOne
    @JoinColumn(name = "CID")
    private Customer customer;
    */

    /*
    public Collection<Librarian> getLibrarians() {
        return librarians;
    }*/

    /*
    public void setLibrarians(Collection<Librarian> librarians) {
        this.librarians = librarians;
    }*/

    public Date getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
       this.returnDate = returnDate;
    }

    /*
    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }*/
}
