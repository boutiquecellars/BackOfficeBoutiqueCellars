/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.itfox.business;

import br.com.itfox.beans.MemberAreaOper;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author belchiorpalma
 */
public class BusinessService {
     public static String getMemberAreaOper(HttpServletRequest request, BusinessDelegate bd){
        int memberId = (int) request.getSession().getAttribute("userid");
        List<MemberAreaOper> areas = bd.selectMemberAreaOper(memberId);
        StringBuilder strAreas= new StringBuilder();
        for(MemberAreaOper m:areas){
            strAreas.append(""+m.getAreaoper().getArea_operacional()+",");
        }
        return "'"+strAreas.substring(0, strAreas.length()-1).toString()+"'";
    }
    public static String getMemberAreaOperSeparated(HttpServletRequest request, BusinessDelegate bd){
        int memberId = (int) request.getSession().getAttribute("userid");
        List<MemberAreaOper> areas = bd.selectMemberAreaOper(memberId);
        StringBuilder strAreas= new StringBuilder();
        for(MemberAreaOper m:areas){
            strAreas.append("'"+m.getAreaoper().getArea_operacional()+"',");
        }
        return strAreas.substring(0, strAreas.length()-1).toString();
    }
    
    public static String getMemberAreaOperSeparated(HttpServletRequest request, BusinessDelegate bd, String regional){
        int memberId = (int) request.getSession().getAttribute("userid");
        List<MemberAreaOper> areas = bd.selectMemberAreaOper(memberId,regional);
        StringBuilder strAreas= new StringBuilder();
        for(MemberAreaOper m:areas){
            strAreas.append("'"+m.getAreaoper().getArea_operacional()+"',");
        }
        return strAreas.substring(0, strAreas.length()-1).toString();
    }
    
}
