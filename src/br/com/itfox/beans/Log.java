/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.itfox.beans;

import java.sql.Blob;
import java.util.Date;

/**
 *
 * @author belchiorpalma
 */
public class Log {
    private int logId;
    private Blob log;
    private String name;
    private String description;
    private Date date;
    
    public Log(){}

    public Log(int logId, Blob log, String name, String description, Date date) {
        this.logId = logId;
        this.log = log;
        this.name = name;
        this.description = description;
        this.date = date;
    }

    public Log(Blob log, String name, String description) {
        this.log = log;
        this.name = name;
        this.description = description;
    }
    

    public int getLogId() {
        return logId;
    }

    public void setLogId(int logId) {
        this.logId = logId;
    }

    public Blob getLog() {
        return log;
    }

    public void setLog(Blob log) {
        this.log = log;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    
}
