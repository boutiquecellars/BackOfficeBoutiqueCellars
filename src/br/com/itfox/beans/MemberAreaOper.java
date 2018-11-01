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
public class MemberAreaOper {
    private Member member;
    private AreaOper areaoper;

    public MemberAreaOper(Member member, AreaOper areaoper) {
        this.member = member;
        this.areaoper = areaoper;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public AreaOper getAreaoper() {
        return areaoper;
    }

    public void setAreaoper(AreaOper areaoper) {
        this.areaoper = areaoper;
    }
    
    
}
