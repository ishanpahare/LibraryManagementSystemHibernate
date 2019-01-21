package com.lms.dto;

import com.lms.utils.DateUtil;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

@Entity
public class IssuedBook extends Book {

    @Temporal(TemporalType.DATE)
    private Date issueDate = new Date();
    @Temporal(TemporalType.DATE)
    private Date returnDate = DateUtil.addDays(this.issueDate, 14);
    @ManyToMany(mappedBy = "issuedBooks")
    @NotFound(action = NotFoundAction.IGNORE)
    private Collection<Customer> customers = new ArrayList<Customer>();

    @ManyToMany(mappedBy = "issuedBooks")
    @NotFound(action = NotFoundAction.IGNORE)
    private Collection<Librarian> librarians = new ArrayList<Librarian>();

    public Collection<Librarian> getLibrarians() {
        return librarians;
    }

    public void setLibrarians(Collection<Librarian> librarians) {
        this.librarians = librarians;
    }

    public Collection<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(Collection<Customer> customers) {
        this.customers = customers;
    }

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

}
