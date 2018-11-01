/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.itfox.beans;

import java.util.Date;

/**
 *
 * @author belchiorpalma
 */
public class Member {
    private int memberId;
    private String name;
    private String email;
    private String pass;
    private Date regdate;
    private String terms;
    private String permission;
    private Date date;

    public Member(int memberId, String name, String email, String pass, Date regdate, String terms) {
        this.memberId = memberId;
        this.name = name;
        this.email = email;
        this.pass = pass;
        this.regdate = regdate;
        this.terms=terms;
    }
    
    public Member(String name, String email, String pass, String terms, Date date){
        this.name=name;
        this.email=email;
        this.pass=pass;
        this.terms=terms;
        this.date=date;
    }
    public Member(){}

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public Date getRegdate() {
        return regdate;
    }

    public void setRegdate(Date regdate) {
        this.regdate = regdate;
    }

    public String getTerms() {
        return terms;
    }

    public void setTerms(String terms) {
        this.terms = terms;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    
    
}
