/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.itfox.beans;

import java.math.BigDecimal;

/**
 *
 * @author belchiorpalma
 */
public class Mensagem {
    private BigDecimal mensagemId;
    private String mensagem;
    private String tag;
    private BigDecimal tipoId;
    private String assunto;

    public BigDecimal getMensagemId() {
        return mensagemId;
    }

    public void setMensagemId(BigDecimal mensagemId) {
        this.mensagemId = mensagemId;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public BigDecimal getTipoId() {
        return tipoId;
    }

    public void setTipoId(BigDecimal tipoId) {
        this.tipoId = tipoId;
    }

    public String getAssunto() {
        return assunto;
    }

    public void setAssunto(String assunto) {
        this.assunto = assunto;
    }
    
}
