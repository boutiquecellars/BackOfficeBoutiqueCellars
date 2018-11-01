/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.itfox.business;

import br.com.itfox.beans.CollectionColumns;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author belchiorpalma
 */
public class BusinessDelegateBoletim {
    
    public List<CollectionColumns> clienteAByMarca(String ini, String fim, String seg, String areaOper){
        Connection conn = new DBase(true).getConnection();
        List<CollectionColumns> list = new ArrayList<CollectionColumns>();
        String marca="";
        String total = "";
        if(conn!=null){
            try {
                String sql = "SELECT G.MARCA_COMPLETA, COUNT(DISTINCT G.C_CPFCNPJPROPRIETARIO) TOTAL FROM GIC AS G\n" +
                            "WHERE \n" +
                            "G.DATA_COMPLETA BETWEEN ? AND ? \n" +
                            "AND G.C_CPFCNPJPROPRIETARIO IN (\n" +
                            "	SELECT C_CPFCNPJPROPRIETARIO FROM GIC AS Gi\n" +
                            "	WHERE\n" +
                            //"	Gi.DATA_COMPLETA > DATE_SUB(?, INTERVAL 12 MONTH)\n" +
                            "     gi.SEG in("+seg+")\n" +
                            "    AND Gi.AREA_OPERACIONAL in ("+areaOper+")\n" +
                            "	GROUP BY Gi.C_CPFCNPJPROPRIETARIO\n" +
                            "	HAVING COUNT(*) > 10\n" +
                            "	)\n" +
                            "AND G.SEG in("+seg+")\n" +
                            "AND G.AREA_OPERACIONAL in ("+areaOper+")\n" +
                            "GROUP BY G.MARCA_COMPLETA\n" +
                            "ORDER BY TOTAL DESC";
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setString(1, ini);
                ps.setString(2, fim);
               // ps.setString(3, fim);
               // ps.setString(3, seg);
                ResultSet rs = ps.executeQuery();
                while(rs.next()){
                    marca=rs.getString("MARCA_COMPLETA");
                    total= rs.getString("TOTAL");
                    CollectionColumns c = new CollectionColumns(new String[]{marca,total});
                    list.add(c);
                }
                conn.close();
                
            } catch (SQLException ex) {
                br.com.itfox.utils.Logger.getLogger(ex, BusinessDelegate.class.getName(),ex.getMessage());
                Logger.getLogger(BusinessDelegate.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return list;
    }
    
    public List<CollectionColumns> clienteBByMarca(String ini, String fim, String seg, String areaOper){
        Connection conn = new DBase(true).getConnection();
        List<CollectionColumns> list = new ArrayList<CollectionColumns>();
        String marca="";
        String total = "";
        if(conn!=null){
            try {
                String sql = "SELECT G.MARCA_COMPLETA, COUNT(DISTINCT G.C_CPFCNPJPROPRIETARIO) TOTAL FROM GIC AS G\n" +
                            "WHERE \n" +
                            "G.DATA_COMPLETA BETWEEN ? AND ? \n" +
                            "AND G.C_CPFCNPJPROPRIETARIO IN (\n" +
                            "	SELECT C_CPFCNPJPROPRIETARIO FROM GIC AS Gi\n" +
                            "	WHERE\n" +
                            //"	Gi.DATA_COMPLETA > DATE_SUB(?, INTERVAL 12 MONTH)\n" +
                            "     gi.SEG in("+seg+")\n" +
                            "    AND Gi.AREA_OPERACIONAL in ("+areaOper+")\n" +
                            "	GROUP BY Gi.C_CPFCNPJPROPRIETARIO\n" +
                            "	HAVING COUNT(*) BETWEEN 3 AND 10 \n" +
                            "	)\n" +
                            "AND G.SEG in("+seg+")\n" +
                            "AND G.AREA_OPERACIONAL in ("+areaOper+")\n" +
                            "GROUP BY G.MARCA_COMPLETA\n" +
                            "ORDER BY TOTAL DESC";
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setString(1, ini);
                ps.setString(2, fim);
               // ps.setString(3, fim);
               // ps.setString(3, seg);
                ResultSet rs = ps.executeQuery();
                while(rs.next()){
                    marca=rs.getString("MARCA_COMPLETA");
                    total= rs.getString("TOTAL");
                    CollectionColumns c = new CollectionColumns(new String[]{marca,total});
                    list.add(c);
                }
                conn.close();
                
            } catch (SQLException ex) {
                br.com.itfox.utils.Logger.getLogger(ex, BusinessDelegate.class.getName(),ex.getMessage());
                Logger.getLogger(BusinessDelegate.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return list;
    }
    
    public List<CollectionColumns> clienteCByMarca(String ini, String fim, String seg, String areaOper){
        Connection conn = new DBase(true).getConnection();
        List<CollectionColumns> list = new ArrayList<CollectionColumns>();
        String marca="";
        String total = "";
        if(conn!=null){
            try {
                String sql = "SELECT G.MARCA_COMPLETA, COUNT(DISTINCT G.C_CPFCNPJPROPRIETARIO) TOTAL FROM GIC AS G\n" +
                            "WHERE \n" +
                            "G.DATA_COMPLETA BETWEEN ? AND ? \n" +
                            "AND G.C_CPFCNPJPROPRIETARIO IN (\n" +
                            "	SELECT C_CPFCNPJPROPRIETARIO FROM GIC AS Gi\n" +
                            "	WHERE\n" +
                            //"	Gi.DATA_COMPLETA > DATE_SUB(?, INTERVAL 12 MONTH)\n" +
                            "     gi.SEG in("+seg+")\n" +
                            "    AND Gi.AREA_OPERACIONAL in ("+areaOper+")\n" +
                            "	GROUP BY Gi.C_CPFCNPJPROPRIETARIO\n" +
                            "	HAVING COUNT(*) < 3\n" +
                            "	)\n" +
                            "AND G.SEG in("+seg+")\n" +
                            "AND G.AREA_OPERACIONAL in ("+areaOper+")\n" +
                            "GROUP BY G.MARCA_COMPLETA\n" +
                            "ORDER BY TOTAL DESC";
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setString(1, ini);
                ps.setString(2, fim);
               // ps.setString(3, fim);
               // ps.setString(3, seg);
                ResultSet rs = ps.executeQuery();
                while(rs.next()){
                    marca=rs.getString("MARCA_COMPLETA");
                    total= rs.getString("TOTAL");
                    CollectionColumns c = new CollectionColumns(new String[]{marca,total});
                    list.add(c);
                }
                conn.close();
                
            } catch (SQLException ex) {
                br.com.itfox.utils.Logger.getLogger(ex, BusinessDelegate.class.getName(),ex.getMessage());
                Logger.getLogger(BusinessDelegate.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return list;
    }
    
    public int clienteA(String ini, String fim, String seg, String areaOper){
        Connection conn = new DBase(true).getConnection();
        int total = 0;
        if(conn!=null){
            try {
                String sql = "SELECT G.MARCA_COMPLETA, COUNT(DISTINCT G.C_CPFCNPJPROPRIETARIO) TOTAL FROM GIC AS G\n" +
                            "WHERE \n" +
                            "G.DATA_COMPLETA BETWEEN ? AND ? \n" +
                            "AND G.C_CPFCNPJPROPRIETARIO IN (\n" +
                            "	SELECT C_CPFCNPJPROPRIETARIO FROM GIC AS Gi\n" +
                            "	WHERE\n" +
                            //"	Gi.DATA_COMPLETA > DATE_SUB(?, INTERVAL 12 MONTH)\n" +
                            "     gi.SEG in("+seg+")\n" +
                            "    AND Gi.AREA_OPERACIONAL in ("+areaOper+")\n" +
                            "	GROUP BY Gi.C_CPFCNPJPROPRIETARIO\n" +
                            "	HAVING COUNT(*) > 10\n" +
                            "	)\n" +
                            "AND G.SEG in("+seg+")\n" +
                            "AND G.AREA_OPERACIONAL in ("+areaOper+")\n" +
                            //"GROUP BY G.MARCA_COMPLETA\n" +
                            "ORDER BY TOTAL DESC";
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setString(1, ini);
                ps.setString(2, fim);
               // ps.setString(3, fim);
               // ps.setString(3, seg);
                ResultSet rs = ps.executeQuery();
                while(rs.next()){
                    total= rs.getInt("TOTAL");
                    return total;
                    
                }
                conn.close();
                
            } catch (SQLException ex) {
                br.com.itfox.utils.Logger.getLogger(ex, BusinessDelegate.class.getName(),ex.getMessage());
                Logger.getLogger(BusinessDelegate.class.getName()).log(Level.SEVERE, null, ex);
            }finally{
                try { 
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(BusinessDelegateBoletim.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return total;
    }
    
    public int clienteB(String ini, String fim, String seg, String areaOper){
        Connection conn = new DBase(true).getConnection();
        int total = 0;
        if(conn!=null){
            try {
                String sql = "SELECT G.MARCA_COMPLETA, COUNT(DISTINCT G.C_CPFCNPJPROPRIETARIO) TOTAL FROM GIC AS G\n" +
                            "WHERE \n" +
                            "G.DATA_COMPLETA BETWEEN ? AND ? \n" +
                            "AND G.C_CPFCNPJPROPRIETARIO IN (\n" +
                            "	SELECT C_CPFCNPJPROPRIETARIO FROM GIC AS Gi\n" +
                            "	WHERE\n" +
                            //"	Gi.DATA_COMPLETA > DATE_SUB(?, INTERVAL 12 MONTH)\n" +
                            "     gi.SEG in("+seg+")\n" +
                            "    AND Gi.AREA_OPERACIONAL in ("+areaOper+")\n" +
                            "	GROUP BY Gi.C_CPFCNPJPROPRIETARIO\n" +
                            "	HAVING COUNT(*) BETWEEN 3 AND 10 \n" +
                            "	)\n" +
                            "AND G.SEG in("+seg+")\n" +
                            "AND G.AREA_OPERACIONAL in ("+areaOper+")\n" +
                            //"GROUP BY G.MARCA_COMPLETA\n" +
                            "ORDER BY TOTAL DESC";
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setString(1, ini);
                ps.setString(2, fim);
               // ps.setString(3, fim);
               // ps.setString(3, seg);
                ResultSet rs = ps.executeQuery();
                while(rs.next()){
                    total= rs.getInt("TOTAL");
                    return total;
                    
                }
                conn.close();
                
            } catch (SQLException ex) {
                br.com.itfox.utils.Logger.getLogger(ex, BusinessDelegate.class.getName(),ex.getMessage());
                Logger.getLogger(BusinessDelegate.class.getName()).log(Level.SEVERE, null, ex);
            }finally{
                try { 
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(BusinessDelegateBoletim.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return total;
    }
    
    public int clienteC(String ini, String fim, String seg, String areaOper){
        Connection conn = new DBase(true).getConnection();
        int total = 0;
        if(conn!=null){
            try {
                String sql = "SELECT G.MARCA_COMPLETA, COUNT(DISTINCT G.C_CPFCNPJPROPRIETARIO) TOTAL FROM GIC AS G\n" +
                            "WHERE \n" +
                            "G.DATA_COMPLETA BETWEEN ? AND ? \n" +
                            "AND G.C_CPFCNPJPROPRIETARIO IN (\n" +
                            "	SELECT C_CPFCNPJPROPRIETARIO FROM GIC AS Gi\n" +
                            "	WHERE\n" +
                            //"	Gi.DATA_COMPLETA > DATE_SUB(?, INTERVAL 12 MONTH)\n" +
                            "     gi.SEG in("+seg+")\n" +
                            "    AND Gi.AREA_OPERACIONAL in ("+areaOper+")\n" +
                            "	GROUP BY Gi.C_CPFCNPJPROPRIETARIO\n" +
                            "	HAVING COUNT(*) < 3\n" +
                            "	)\n" +
                            "AND G.SEG in("+seg+")\n" +
                            "AND G.AREA_OPERACIONAL in ("+areaOper+")\n" +
                            //"GROUP BY G.MARCA_COMPLETA\n" +
                            "ORDER BY TOTAL DESC";
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setString(1, ini);
                ps.setString(2, fim);
               // ps.setString(3, fim);
               // ps.setString(3, seg);
                ResultSet rs = ps.executeQuery();
                while(rs.next()){
                    total= rs.getInt("TOTAL");
                    return total;
                    
                }
                conn.close();
                
            } catch (SQLException ex) {
                br.com.itfox.utils.Logger.getLogger(ex, BusinessDelegate.class.getName(),ex.getMessage());
                Logger.getLogger(BusinessDelegate.class.getName()).log(Level.SEVERE, null, ex);
            }finally{
                try { 
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(BusinessDelegateBoletim.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return total;
    }
    
    
    public List<CollectionColumns> clienteABCByGama(String ini, String fim, String seg, String areaOper){
        Connection conn = new DBase(true).getConnection();
        List<CollectionColumns> list = new ArrayList<CollectionColumns>();
        String clientes="";
        String leves = "";
        String medios = "";
        String semipesados = "";
        String pesados = "";
        String total = "";
        if(conn!=null){
            try {
                String sql = "-- CLIENTE TYPE A, >10 IN LAST 12 MONTHS\n" +
                    "SELECT\n" +
                    "	'CLIENTE A' AS CLIENTES,\n" +
                    "    COUNT(DISTINCT(CASE WHEN G.SUBSEGMENTO = 'LEVES' THEN G.C_CPFCNPJPROPRIETARIO END)) AS LEVES,\n" +
                    "	 COUNT(DISTINCT(CASE WHEN G.SUBSEGMENTO = 'MEDIOS' THEN G.C_CPFCNPJPROPRIETARIO END)) AS MEDIOS,\n" +
                    "	 COUNT(DISTINCT(CASE WHEN G.SUBSEGMENTO = 'SEMIPESADOS' THEN G.C_CPFCNPJPROPRIETARIO END)) AS SEMIPESADOS,\n" +
                    "	 COUNT(DISTINCT(CASE WHEN G.SUBSEGMENTO = 'PESADOS' THEN G.C_CPFCNPJPROPRIETARIO END)) AS PESADOS \n" +
                   // "	 COUNT(DISTINCT(CASE WHEN G.SUBSEGMENTO = 'LEVES' OR G.SUBSEGMENTO = 'MEDIOS' OR G.SUBSEGMENTO = 'SEMIPESADOS' OR G.SUBSEGMENTO = 'PESADOS' OR 0 THEN G.C_CPFCNPJPROPRIETARIO END)   ) AS TOTAL \n"+
                    "FROM GIC AS G\n" +
                    "WHERE \n" +
                    "G.DATA_COMPLETA BETWEEN ? AND ? \n" +
                    "AND G.C_CPFCNPJPROPRIETARIO IN (\n" +
                    "	SELECT C_CPFCNPJPROPRIETARIO FROM GIC AS Gi\n" +
                    "	WHERE\n" +
                    //"	Gi.DATA_COMPLETA > DATE_SUB(?, INTERVAL 12 MONTH)\n" +
                   // "	-- Gi.DATA_COMPLETA between ? and ?\n" +
                    "     gi.SEG in("+seg+")\n" +
                    "    AND Gi.AREA_OPERACIONAL in ("+areaOper+")\n" +
                    "	GROUP BY Gi.C_CPFCNPJPROPRIETARIO\n" +
                    "	HAVING COUNT(*) > 10\n" +
                    "	)\n" +
                    "AND G.SEG in("+seg+")\n" +
                    "AND G.AREA_OPERACIONAL in ("+areaOper+")\n" +
                    "-- UNION\n" +
                    "UNION ALL\n" +
                    "-- CLIENTE TYPE B, >3 <10 IN LAST 12 MONTHS\n" +
                    "SELECT\n" +
                    "	'CLIENTE B' AS CLIENTES,\n" +
                    "    COUNT(DISTINCT(CASE WHEN G.SUBSEGMENTO = 'LEVES' THEN G.C_CPFCNPJPROPRIETARIO END)) AS LEVES,\n" +
                    "	 COUNT(DISTINCT(CASE WHEN G.SUBSEGMENTO = 'MEDIOS' THEN G.C_CPFCNPJPROPRIETARIO END)) AS MEDIOS,\n" +
                    "	 COUNT(DISTINCT(CASE WHEN G.SUBSEGMENTO = 'SEMIPESADOS' THEN G.C_CPFCNPJPROPRIETARIO END)) AS SEMIPESADOS,\n" +
                    "	 COUNT(DISTINCT(CASE WHEN G.SUBSEGMENTO = 'PESADOS' THEN G.C_CPFCNPJPROPRIETARIO END)) AS PESADOS \n" +
                   // "	 COUNT(DISTINCT(CASE WHEN G.SUBSEGMENTO = 'LEVES' OR G.SUBSEGMENTO = 'MEDIOS' OR G.SUBSEGMENTO = 'SEMIPESADOS' OR G.SUBSEGMENTO = 'PESADOS' OR 0 THEN G.C_CPFCNPJPROPRIETARIO END)   ) AS TOTAL \n"+
                    "FROM GIC AS G\n" +
                    "WHERE \n" +
                    "G.DATA_COMPLETA BETWEEN ? AND ? \n" +
                    "AND G.C_CPFCNPJPROPRIETARIO IN (\n" +
                    "	SELECT C_CPFCNPJPROPRIETARIO FROM GIC AS Gi\n" +
                    "	WHERE\n" +
                    //"	Gi.DATA_COMPLETA > DATE_SUB(?, INTERVAL 12 MONTH)\n" +
                   // "	-- Gi.DATA_COMPLETA between '2015-04-30' and '2016-04-30'\n" +
                    "     gi.SEG in("+seg+")\n" +
                    "    AND Gi.AREA_OPERACIONAL in ("+areaOper+")\n" +
                    "	GROUP BY Gi.C_CPFCNPJPROPRIETARIO\n" +
                    "	HAVING COUNT(*) BETWEEN 3 AND 10\n" +
                    "	)\n" +
                    "AND G.SEG in("+seg+")\n" +
                    "AND G.AREA_OPERACIONAL in ("+areaOper+")\n" +
                    "-- UNION\n" +
                    "UNION ALL\n" +
                    "-- CLIENTE TYPE C, <3 IN LAST 12 MONTHS\n" +
                    "SELECT\n" +
                    "	'CLIENTE C' AS CLIENTES,\n" +
                    "    COUNT(DISTINCT(CASE WHEN G.SUBSEGMENTO = 'LEVES' THEN G.C_CPFCNPJPROPRIETARIO END)) AS LEVES,\n" +
                    "	 COUNT(DISTINCT(CASE WHEN G.SUBSEGMENTO = 'MEDIOS' THEN G.C_CPFCNPJPROPRIETARIO END)) AS MEDIOS,\n" +
                    "	 COUNT(DISTINCT(CASE WHEN G.SUBSEGMENTO = 'SEMIPESADOS' THEN G.C_CPFCNPJPROPRIETARIO END)) AS SEMIPESADOS,\n" +
                    "	 COUNT(DISTINCT(CASE WHEN G.SUBSEGMENTO = 'PESADOS' THEN G.C_CPFCNPJPROPRIETARIO END)) AS PESADOS \n" +
                    //"	 COUNT(DISTINCT(CASE WHEN G.SUBSEGMENTO = 'LEVES' OR G.SUBSEGMENTO = 'MEDIOS' OR G.SUBSEGMENTO = 'SEMIPESADOS' OR G.SUBSEGMENTO = 'PESADOS' OR 0 THEN G.C_CPFCNPJPROPRIETARIO END)   ) AS TOTAL \n"+
                    "FROM GIC AS G\n" +
                    "WHERE \n" +
                    "G.DATA_COMPLETA BETWEEN ? AND ?\n" +
                    "AND G.C_CPFCNPJPROPRIETARIO IN (\n" +
                    "	SELECT C_CPFCNPJPROPRIETARIO FROM GIC AS Gi\n" +
                    "	WHERE\n" +
                    //"	Gi.DATA_COMPLETA > DATE_SUB(?, INTERVAL 12 MONTH)\n" +
                   // "	-- Gi.DATA_COMPLETA between '2015-04-30' and '2016-04-30'\n" +
                    "     gi.SEG in("+seg+")\n" +
                    "    AND Gi.AREA_OPERACIONAL in ("+areaOper+")\n" +
                    "	GROUP BY Gi.C_CPFCNPJPROPRIETARIO\n" +
                    "	HAVING COUNT(*) < 3\n" +
                    "	)\n" +
                    "AND G.SEG in("+seg+")\n" +
                    "AND G.AREA_OPERACIONAL in ("+areaOper+")\n" ;
                   // "-- TOTAIS\n" ;
                   /*
                    "UNION ALL\n" +
                    "-- CLIENTE A+B+C IN LAST 12 MONTHS\n" +
                    "SELECT\n" +
                    "	'TOTAL' AS CLIENTES,\n" +
                    "    COUNT(DISTINCT(CASE WHEN G.SUBSEGMENTO = 'LEVES' THEN G.C_CPFCNPJPROPRIETARIO END)) AS LEVES,\n" +
                    "	 COUNT(DISTINCT(CASE WHEN G.SUBSEGMENTO = 'MEDIOS' THEN G.C_CPFCNPJPROPRIETARIO END)) AS MEDIOS,\n" +
                    "	 COUNT(DISTINCT(CASE WHEN G.SUBSEGMENTO = 'SEMIPESADOS' THEN G.C_CPFCNPJPROPRIETARIO END)) AS SEMIPESADOS,\n" +
                    "	 COUNT(DISTINCT(CASE WHEN G.SUBSEGMENTO = 'PESADOS' THEN G.C_CPFCNPJPROPRIETARIO END)) AS PESADOS,\n" +
                    "	 COUNT(DISTINCT(CASE WHEN G.SUBSEGMENTO = 'LEVES' OR G.SUBSEGMENTO = 'MEDIOS' OR G.SUBSEGMENTO = 'SEMIPESADOS' OR G.SUBSEGMENTO = 'PESADOS' OR 0 THEN G.C_CPFCNPJPROPRIETARIO END)   ) AS TOTAL \n"+
                    "FROM GIC AS G\n" +
                    "WHERE \n" +
                    "G.DATA_COMPLETA BETWEEN ? AND ? \n" +
                    "AND G.SEG in("+seg+")\n" +
                    "AND G.AREA_OPERACIONAL in ("+areaOper+")";
                    */
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setString(1, ini);
                ps.setString(2, fim);
                //ps.setString(3, fim);
                ps.setString(3, ini);
                ps.setString(4, fim);
                //ps.setString(6, fim);
                ps.setString(5, ini);
                ps.setString(6, fim);
                //ps.setString(9, fim);
                //ps.setString(10, ini);
                //ps.setString(11, fim);
                ResultSet rs = ps.executeQuery();
                while(rs.next()){
                    clientes=rs.getString("CLIENTES");
                    leves= rs.getString("LEVES");
                    medios= rs.getString("MEDIOS");
                    semipesados =rs.getString("SEMIPESADOS");
                    pesados= rs.getString("PESADOS");
                    //total= rs.getString("TOTAL");
                    CollectionColumns c = new CollectionColumns(new String[]{clientes, leves, medios, semipesados, pesados});
                    list.add(c);
                }
                conn.close();
                
            } catch (SQLException ex) {
                br.com.itfox.utils.Logger.getLogger(ex, BusinessDelegate.class.getName(),ex.getMessage());
                Logger.getLogger(BusinessDelegate.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return list;
    }
    
    public List<CollectionColumns> novosClientes(String ini, String fim, String seg, String areaOper){
        Connection conn = new DBase(true).getConnection();
        List<CollectionColumns> list = new ArrayList<CollectionColumns>();
        String cnpj,nome, ford, mercedes, scania, volvo, vw, iveco, renault, fiat,outros, total,clientes;
        cnpj=nome=ford=mercedes=scania=volvo=vw=iveco=renault=fiat=outros=total=clientes="";
        if(conn!=null){
            try {
                String sql = "SELECT \n" +
                            "	Gi.C_CPFCNPJPROPRIETARIO AS CNPJ,\n" +
                            "	Gi.C_NOMEPROPRIETARIO AS NOME,\n" +
                            "	SUM(GI.MARCA_COMPLETA = 'FORD' OR 0) AS FORD,\n" +
                            "	SUM(GI.MARCA_COMPLETA = 'MERCEDES-BENZ' OR 0) AS MBENZ,\n" +
                            "	SUM(GI.MARCA_COMPLETA = 'SCANIA' OR 0) AS SCANIA,\n" +
                            "	SUM(GI.MARCA_COMPLETA = 'VOLVO' OR 0) AS VOLVO,\n" +
                            "	SUM(GI.MARCA_COMPLETA = 'VOLKSWAGEN' OR 0) AS VW,\n" +
                            "    SUM(GI.MARCA_COMPLETA = 'IVECO' OR 0) AS IVECO,\n" +
                            "    SUM(GI.MARCA_COMPLETA = 'RENAULT' OR 0) AS RENAULT,\n" +
                            "	SUM(GI.MARCA_COMPLETA = 'FIAT' OR 0) AS FIAT,\n" +
                            "	COUNT(CHASSI) - SUM(GI.MARCA_COMPLETA = 'FORD' OR GI.MARCA_COMPLETA = 'MERCEDES-BENZ' OR GI.MARCA_COMPLETA = 'SCANIA' OR GI.MARCA_COMPLETA = 'FIAT' OR GI.MARCA_COMPLETA = 'VOLVO' OR GI.MARCA_COMPLETA = 'VOLKSWAGEN' OR GI.MARCA_COMPLETA = 'IVECO' OR GI.MARCA_COMPLETA = 'RENAULT' OR GI.MARCA_COMPLETA = 'FIAT' OR 0) AS OUTROS,\n" +
                            "	COUNT(CHASSI) AS TOTAL,\n" +
                            "    COUNT(distinct Gi.C_CPFCNPJPROPRIETARIO) AS CLIENTES\n" +
                            "FROM GIC Gi\n" +
                            "WHERE \n" +
                            "Gi.DATA_COMPLETA BETWEEN ? AND ? \n" +
                            "AND Gi.C_CPFCNPJPROPRIETARIO IN(\n" +
                            "	-- FILTRO\n" +
                            "	SELECT DISTINCT A.CPFCNPJ FROM (\n" +
                            "		SELECT \n" +
                            "			DISTINCT(Ga.C_CPFCNPJPROPRIETARIO) AS CPFCNPJ\n" +
                            "		FROM GIC AS Ga\n" +
                            "		WHERE Ga.DATA_COMPLETA BETWEEN ? AND ? \n" +
                            "		AND Ga.SEG in("+seg+")\n" +
                            "		AND Ga.AREA_OPERACIONAL in("+areaOper+")\n" +
                            "		) AS A\n" +
                            "	LEFT OUTER JOIN \n" +
                            "	 (\n" +
                            "		SELECT \n" +
                            "			DISTINCT(Gb.C_CPFCNPJPROPRIETARIO) AS CPFCNPJ\n" +
                            "		FROM GIC AS Gb\n" +
                            "		WHERE\n" +
                            "			Gb.DATA_COMPLETA < ?\n" +
                            "			AND Gb.SEG in("+seg+")\n" +
                            "			AND Gb.AREA_OPERACIONAL in("+areaOper+")\n" +
                            "	  ) AS B\n" +
                            "	ON A.CPFCNPJ = B.CPFCNPJ\n" +
                            "	WHERE B.CPFCNPJ IS null)\n" +
                            "AND Gi.SEG in("+seg+")\n" +
                            "AND Gi.AREA_OPERACIONAL in("+areaOper+")\n" +
                            "GROUP BY Gi.C_CPFCNPJPROPRIETARIO\n" +
                            "ORDER BY COUNT(Gi.chassi) DESC\n" +
                            "LIMIT 19\n" +
                            "";
                    
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setString(1, ini);
                ps.setString(2, fim);
                ps.setString(3, ini);
                ps.setString(4, fim);
                ps.setString(5, ini);
                ResultSet rs = ps.executeQuery();
                while(rs.next()){
                    cnpj=rs.getString("CNPJ");
                    nome= rs.getString("NOME");
                    ford= rs.getString("FORD");
                    mercedes =rs.getString("MBENZ");
                    scania= rs.getString("SCANIA");
                    volvo= rs.getString("VOLVO");
                    vw= rs.getString("VW");
                    outros= rs.getString("OUTROS");
                    iveco= rs.getString("IVECO");
                    renault= rs.getString("RENAULT");
                    fiat= rs.getString("FIAT");
                    total= rs.getString("TOTAL");
                    clientes= rs.getString("CLIENTES");
                    
                    
                    //total= rs.getString("TOTAL");
                    CollectionColumns c = new CollectionColumns(new String[]{cnpj, nome, ford, mercedes, scania, volvo, vw, iveco, renault, fiat, outros, total, clientes});
                    list.add(c);
                }
                conn.close();
                
            } catch (SQLException ex) {
                br.com.itfox.utils.Logger.getLogger(ex, BusinessDelegate.class.getName(),ex.getMessage());
                Logger.getLogger(BusinessDelegate.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return list;
    }
    
    public List<CollectionColumns> novosClientes6Meses(String ini, String fim, String seg, String areaOper){
        Connection conn = new DBase(true).getConnection();
        List<CollectionColumns> list = new ArrayList<CollectionColumns>();
        String cnpj,nome, ford, mercedes, scania, volvo, vw, iveco, renault, fiat,outros, total,clientes;
        cnpj=nome=ford=mercedes=scania=volvo=vw=iveco=renault=fiat=outros=total=clientes="";
        if(conn!=null){
            try {
                String sql = "-- Iveco r4 - novos clientes com data de abertura da empresa de até 6 meses\n" +
                            "SELECT \n" +
                            "	Gi.C_CPFCNPJPROPRIETARIO  AS CNPJ,\n" +
                            "	Gi.C_NOMEPROPRIETARIO AS NOME,\n" +
                            "	SUM(GI.MARCA_COMPLETA = 'FORD' OR 0) AS FORD,\n" +
                            "	SUM(GI.MARCA_COMPLETA = 'MERCEDES-BENZ' OR 0) AS MBENZ,\n" +
                            "	SUM(GI.MARCA_COMPLETA = 'SCANIA' OR 0) AS SCANIA,\n" +
                            "	SUM(GI.MARCA_COMPLETA = 'VOLVO' OR 0) AS VOLVO,\n" +
                            "	SUM(GI.MARCA_COMPLETA = 'VOLKSWAGEN' OR 0) AS VW,\n" +
                            "    SUM(GI.MARCA_COMPLETA = 'IVECO' OR 0) AS IVECO,\n" +
                            "    SUM(GI.MARCA_COMPLETA = 'RENAULT' OR 0) AS RENAULT,\n" +
                            "	SUM(GI.MARCA_COMPLETA = 'FIAT' OR 0) AS FIAT,\n" +
                            "	COUNT(Gi.CHASSI) - SUM(GI.MARCA_COMPLETA = 'FORD' OR GI.MARCA_COMPLETA = 'MERCEDES-BENZ' OR GI.MARCA_COMPLETA = 'SCANIA' OR GI.MARCA_COMPLETA = 'FIAT' OR GI.MARCA_COMPLETA = 'VOLVO' OR GI.MARCA_COMPLETA = 'VOLKSWAGEN' OR GI.MARCA_COMPLETA = 'IVECO' OR GI.MARCA_COMPLETA = 'RENAULT' OR GI.MARCA_COMPLETA = 'FIAT' OR 0) AS OUTROS,\n" +
                            "	COUNT(Gi.CHASSI) AS TOTAL,\n" +
                            "	COUNT(DISTINCT Gi.C_CPFCNPJPROPRIETARIO) AS CLIENTES\n" +
                            "FROM GIC Gi\n" +
                            "WHERE \n" +
                            "Gi.DATA_COMPLETA BETWEEN ? AND ?\n" +
                            "AND Gi.J_DT_ABERTURA > DATE_SUB(?, INTERVAL 6 MONTH)\n" +
                            "AND Gi.C_TIPOCNPJPROPRIETARIO = 'JURIDICA'\n" +
                            "AND Gi.SEG in("+seg+")\n" +
                            "AND Gi.AREA_OPERACIONAL in("+areaOper+")\n" +
                            "GROUP BY Gi.C_CPFCNPJPROPRIETARIO\n" +
                            "ORDER BY COUNT(Gi.chassi) DESC \n" +
                            "LIMIT 19\n" +
                            "";
                    
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setString(1, ini);
                ps.setString(2, fim);
                ps.setString(3, fim);
                ResultSet rs = ps.executeQuery();
                while(rs.next()){
                    cnpj=rs.getString("CNPJ");
                    nome= rs.getString("NOME");
                    ford= rs.getString("FORD");
                    mercedes =rs.getString("MBENZ");
                    scania= rs.getString("SCANIA");
                    volvo= rs.getString("VOLVO");
                    vw= rs.getString("VW");
                    outros= rs.getString("OUTROS");
                    iveco= rs.getString("IVECO");
                    renault= rs.getString("RENAULT");
                    fiat= rs.getString("FIAT");
                    total= rs.getString("TOTAL");
                    clientes= rs.getString("CLIENTES");
                    
                    
                    //total= rs.getString("TOTAL");
                    CollectionColumns c = new CollectionColumns(new String[]{cnpj, nome, ford, mercedes, scania, volvo, vw, iveco, renault, fiat, outros, total, clientes});
                    list.add(c);
                }
                conn.close();
                
            } catch (SQLException ex) {
                br.com.itfox.utils.Logger.getLogger(ex, BusinessDelegate.class.getName(),ex.getMessage());
                Logger.getLogger(BusinessDelegate.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return list;
    }
    
    public List<CollectionColumns> atividadeEconomica(String ini, String fim, String seg, String areaOper){
        Connection conn = new DBase(true).getConnection();
        List<CollectionColumns> list = new ArrayList<CollectionColumns>();
        String atividade,nome, ford, mercedes, scania, volvo, vw, iveco, renault, fiat,outros, total,clientes, mktshare;
        atividade=nome=ford=mercedes=scania=volvo=vw=iveco=renault=fiat=outros=total=clientes=mktshare="";
        if(conn!=null){
            try {
                String sql = "-- Iveco r5 - atividade economica\n" +
                            "SELECT \n" +
                            "	LEFT(Gi.J_ATIVIDADE_ECONOMICA , 50)  AS ATIVIDADE_ECONOMICA,\n" +
                            "	SUM(GI.MARCA_COMPLETA = 'FORD' OR 0) AS FORD,\n" +
                            "	SUM(GI.MARCA_COMPLETA = 'MERCEDES-BENZ' OR 0) AS MBENZ,\n" +
                            "	SUM(GI.MARCA_COMPLETA = 'SCANIA' OR 0) AS SCANIA,\n" +
                            "	SUM(GI.MARCA_COMPLETA = 'VOLVO' OR 0) AS VOLVO,\n" +
                            "	SUM(GI.MARCA_COMPLETA = 'VOLKSWAGEN' OR 0) AS VW,\n" +
                            "    SUM(GI.MARCA_COMPLETA = 'IVECO' OR 0) AS IVECO,\n" +
                            "    SUM(GI.MARCA_COMPLETA = 'RENAULT' OR 0) AS RENAULT,\n" +
                            "	SUM(GI.MARCA_COMPLETA = 'FIAT' OR 0) AS FIAT,\n" +
                            "	COUNT(Gi.CHASSI) - SUM(GI.MARCA_COMPLETA = 'FORD' OR GI.MARCA_COMPLETA = 'MERCEDES-BENZ' OR GI.MARCA_COMPLETA = 'SCANIA' OR GI.MARCA_COMPLETA = 'FIAT' OR GI.MARCA_COMPLETA = 'VOLVO' OR GI.MARCA_COMPLETA = 'VOLKSWAGEN' OR GI.MARCA_COMPLETA = 'IVECO' OR GI.MARCA_COMPLETA = 'RENAULT' OR GI.MARCA_COMPLETA = 'FIAT' OR 0) AS OUTROS,\n" +
                            "	COUNT(Gi.CHASSI) AS TOTAL,\n" +
                            "	COUNT(DISTINCT Gi.C_CPFCNPJPROPRIETARIO) AS CLIENTES, \n" +
                            "   COALESCE(ROUND((SUM(GI.MARCA_COMPLETA = 'IVECO' OR 0) / COUNT(Gi.CHASSI))*100,2),0) AS MKTSHARE \n"+
                            "FROM GIC Gi\n" +
                            "WHERE \n" +
                            "Gi.DATA_COMPLETA BETWEEN ? AND ? \n" +
                           // "-- AND Gi.J_DT_ABERTURA > DATE_SUB('2016-04-30', INTERVAL 6 MONTH)\n" +
                           // "-- AND Gi.C_TIPOCNPJPROPRIETARIO = 'JURIDICA'\n" +
                            "AND Gi.SEG in("+seg+")\n" +
                            "AND Gi.AREA_OPERACIONAL in("+areaOper+")\n" +
                            "AND Gi.J_ATIVIDADE_ECONOMICA IS NOT NULL \n" +
                            "GROUP BY Gi.J_ATIVIDADE_ECONOMICA\n" +
                            "ORDER BY COUNT(Gi.chassi) DESC \n" +
                            "LIMIT 20\n" +
                            "";
                    
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setString(1, ini);
                ps.setString(2, fim);
                //ps.setString(3, fim);
                ResultSet rs = ps.executeQuery();
                while(rs.next()){
                    atividade=rs.getString("ATIVIDADE_ECONOMICA");
                    ford= rs.getString("FORD");
                    mercedes =rs.getString("MBENZ");
                    scania= rs.getString("SCANIA");
                    volvo= rs.getString("VOLVO");
                    vw= rs.getString("VW");
                    outros= rs.getString("OUTROS");
                    iveco= rs.getString("IVECO");
                    renault= rs.getString("RENAULT");
                    fiat= rs.getString("FIAT");
                    total= rs.getString("TOTAL");
                    clientes= rs.getString("CLIENTES");
                    mktshare= rs.getString("MKTSHARE");
                    
                    //total= rs.getString("TOTAL");
                    CollectionColumns c = new CollectionColumns(new String[]{atividade, ford, mercedes, scania, volvo, vw, iveco, renault, fiat, outros, total, clientes, mktshare});
                    list.add(c);
                }
                conn.close();
                
            } catch (SQLException ex) {
                br.com.itfox.utils.Logger.getLogger(ex, BusinessDelegate.class.getName(),ex.getMessage());
                Logger.getLogger(BusinessDelegate.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return list;
    }
    
    public List<CollectionColumns> concentracaoCliente(String ini, String fim, String seg, String areaOper){
        Connection conn = new DBase(true).getConnection();
        List<CollectionColumns> list = new ArrayList<CollectionColumns>();
        String atividade,nome, ford, mercedes, scania, volvo, vw, iveco, renault, fiat,outros, total,clientes, mktshare;
        atividade=nome=ford=mercedes=scania=volvo=vw=iveco=renault=fiat=outros=total=clientes=mktshare="";
        String marca,sp, bh, curitiba, recife;
        marca=sp=bh=curitiba=recife="";
        if(conn!=null){
            try {
                String sql = "set @StartDate := ? \n" ;
                               // "set @EndDate := '2016-06-30';\n" +
                                //"select * from view_regional_marca;\n" +
                                //"select * from view_regional_marca_pivot;" +
                                
                    
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setString(1, ini);
                boolean result = ps.execute();
                sql = "set @EndDate := ?";
                ps = conn.prepareStatement(sql);
                ps.setString(1, fim);
                result = ps.execute();
                areaOper = areaOper.replace('\'', ' ').replaceAll(" ","");
                sql = "set @AreaOper := '"+areaOper+"'";
                ps = conn.prepareStatement(sql);
                br.com.itfox.utils.Logger.getLogger("areaOper:"+areaOper);
                //ps.setString(1, fim);
                result = ps.execute();
                seg = seg.replace('\'', ' ').replaceAll(" ","");
                sql = "set @Seg := '"+seg+"'";
                ps = conn.prepareStatement(sql);
                //ps.setString(1, fim);
                result = ps.execute();
                sql = "select * from view_regional_marca_pivot";
                ps = conn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery();
                while(rs.next()){
                    marca=rs.getString("MARCA");
                    sp= rs.getString("SP_");
                    curitiba =rs.getString("CURITIBA_");
                    bh= rs.getString("BH_");
                    recife= rs.getString("RECIFE_");
                    
                    
                    //total= rs.getString("TOTAL");
                    CollectionColumns c = new CollectionColumns(new String[]{marca,sp,curitiba,bh,recife});
                    list.add(c);
                }
                conn.close();
                
            } catch (SQLException ex) {
                br.com.itfox.utils.Logger.getLogger(ex, BusinessDelegate.class.getName(),ex.getMessage());
                Logger.getLogger(BusinessDelegate.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return list;
    }
    
     public List<CollectionColumns> penetracaoClientes(String ini, String fim, String seg, String subSeg, String areaOper){
        Connection conn = new DBase(true).getConnection();
        List<CollectionColumns> list = new ArrayList<CollectionColumns>();
        String clientes, penetrados;
        clientes=penetrados="";
        if(conn!=null){
            try {
                String sql = "SELECT (COUNT(CLIENTES) - SUM(t)) AS CLIENTES, SUM(t) AS PENETRADOS FROM (\n" +
                "	SELECT DISTINCT G.C_CPFCNPJPROPRIETARIO CLIENTES,\n" +
                "	(SELECT COUNT(DISTINCT Gi.C_CPFCNPJPROPRIETARIO) CLIENTES_PENETRADOS\n" +
                "	FROM GIC AS Gi\n" +
                "	WHERE \n" +
                "	 Gi.MARCA_COMPLETA='IVECO'\n" +
                "	AND Gi.C_CPFCNPJPROPRIETARIO IN(G.C_CPFCNPJPROPRIETARIO)\n" +
                "	AND Gi.SEG IN("+seg+")\n" +
                "	AND Gi.SUBSEG IN("+subSeg+")\n" +
                "       AND Gi.AREA_OPERACIONAL in("+areaOper+")\n" +               
                "	 )as t\n" +
                "	FROM GIC AS G\n" +
                "	WHERE G.DATA_COMPLETA BETWEEN ? AND ?\n" +
                "	AND G.C_CPFCNPJPROPRIETARIO IS NOT NULL\n" +
                "	AND G.SEG IN("+seg+")\n" +
                "	AND G.SUBSEG IN("+subSeg+")\n" +
                "       AND G.AREA_OPERACIONAL in("+areaOper+")\n" +        
                "	-- GROUP BY g.c_cpfcnpjproprietario\n" +
                "  )as TBL";
                    
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setString(1, ini);
                ps.setString(2, fim);
                //ps.setString(3, fim);
                ResultSet rs = ps.executeQuery();
                while(rs.next()){
                    clientes=rs.getString("CLIENTES");
                    penetrados= rs.getString("PENETRADOS");
                   
                    
                    //total= rs.getString("TOTAL");
                    CollectionColumns c = new CollectionColumns(new String[]{clientes,penetrados});
                    list.add(c);
                }
                conn.close();
                
            } catch (SQLException ex) {
                br.com.itfox.utils.Logger.getLogger(ex, BusinessDelegate.class.getName(),ex.getMessage());
                Logger.getLogger(BusinessDelegate.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return list;
    }
   
}
