/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.itfox.config;

import java.math.RoundingMode;

/**
 *
 * @author belchiorpalma
 */
public class Preferences {
    private static final String USER="itfox";//"quest"
    private static final String PASS="ITFOX@1";//"www.quest.com.br@1"
    private static final String DATABASE="jdbc:mysql://104.155.239.197:3306/boutiquecellars?generateSimpleParameterMetadata=true"; //201.30.93.1
    public static final String copyright="<strong>Copyright</strong> BOUTIQUE CELLARS";
    public static final String copyrightYear="© 2016";
    public static final String title="BOUTIQUE CELLARS";
    public static final String copyrightRight="";
    public static final String h1="BOUTIQUE CELLARS";
    public static final String SECURITY_KEY="www.itfox.com.br.security.key-";//CONSTANT
    public static final float COMBINE_LIMIT = 0.03f;
    public static final int BOLETIM_TOP_LIMIT = 5;
    public static final int MAXIMUM_FRACTION_DIGITS=1;
    public static final int MINIMUM_FRACTION_DIGITS=1;
    public static final int MIMIMUM_INTEGER_DIGITS=1;
    public static final RoundingMode ROUNDING_MODE = RoundingMode.HALF_UP;

    public static String getUSER() {
        return USER;
    }

    public static String getPASS() {
        return PASS;
    }

    public static String getDATABASE() {
        return DATABASE;
    }
    public static float getCOMBINELIMIT(){
        return COMBINE_LIMIT;
    }
    
}
