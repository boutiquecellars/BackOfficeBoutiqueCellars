/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.itfox.beans;

import java.sql.Blob;

/**
 *
 * @author belchiorpalma
 */
public class EmailMkt {
    private int emailMktId;
    private String name;
    private Blob description;
    private String metaTagTitle;
    private String metaTagDescription;
    private String metaTagKeywords;
    private String metaTagUrl;
   
    
    public EmailMkt(){}

    public EmailMkt(int emailMktId, String name, Blob description, String metaTagTitle, String metaTagDescription, String metaTagKeywords, String metaTagUrl) {
        this.emailMktId = emailMktId;
        this.name = name;
        this.description = description;
        this.metaTagTitle = metaTagTitle;
        this.metaTagDescription = metaTagDescription;
        this.metaTagKeywords = metaTagKeywords;
        this.metaTagUrl = metaTagUrl;
    }

    public int getEmailMktId() {
        return emailMktId;
    }

    public void setEmailMktId(int emailMktId) {
        this.emailMktId = emailMktId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Blob getDescription() {
        return description;
    }

    public void setDescription(Blob description) {
        this.description = description;
    }

    public String getMetaTagTitle() {
        return metaTagTitle;
    }

    public void setMetaTagTitle(String metaTagTitle) {
        this.metaTagTitle = metaTagTitle;
    }

    public String getMetaTagDescription() {
        return metaTagDescription;
    }

    public void setMetaTagDescription(String metaTagDescription) {
        this.metaTagDescription = metaTagDescription;
    }

    public String getMetaTagKeywords() {
        return metaTagKeywords;
    }

    public void setMetaTagKeywords(String metaTagKeywords) {
        this.metaTagKeywords = metaTagKeywords;
    }

    public String getMetaTagUrl() {
        return metaTagUrl;
    }

    public void setMetaTagUrl(String metaTagUrl) {
        this.metaTagUrl = metaTagUrl;
    }

   
    
    
    
}
