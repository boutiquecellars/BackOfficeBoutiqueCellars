/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.itfox.beans;

/**
 *
 * @author belchiorpalma
 */
public class AreaOper {
    private String area_operacional;
    private String descricao;

    public AreaOper(String area_operacional, String descricao) {
        this.area_operacional = area_operacional;
        this.descricao = descricao;
    }
    public AreaOper(){}

    public String getArea_operacional() {
        return area_operacional;
    }

    public void setArea_operacional(String area_operacional) {
        this.area_operacional = area_operacional;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
    
}
