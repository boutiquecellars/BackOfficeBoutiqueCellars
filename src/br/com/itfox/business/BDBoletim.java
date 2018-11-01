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
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author belchiorpalma
 */
public class BDBoletim {
    
     public List<CollectionColumns> selectResumoEmplacamentosMensal(String ini, String fim, String seg, String areaOper){
        Connection conn = new DBase(true).getConnection();
        List<CollectionColumns> list = new ArrayList<CollectionColumns>();
        String count="";
        String desc = "";
        if(conn!=null){
            try {
                
                String sql = ""; 
                       /*     
                       sql= "SELECT COUNT(*) AS TOTAL, MA.DESCRICAO \n " +
                            "FROM EMPLACAMENTOS E, MODELOS M, MARCAS MA, SEGMENTO S, MUNICIPIOS MU, AREAOPER A, GIC G \n " +
                            "WHERE E.MODELO=M.MODELO AND\n " +
                            "G.CHASSI=E.CHASSI AND \n" +
                           // "G.C_CPFCNPJPROPRIETARIO IS NOT NULL AND \n"+
                            "E.MUNICIPIO=MU.MUNICIPIO AND \n" +
                            "MU.AREA_OPERACIONAL = A.AREA_OPERACIONAL AND \n" +
                            "A.AREA_OPERACIONAL IN ("+areaOper+") AND "+
                            "M.FABRICANTE=MA.MARCA AND\n " +
                            "M.SEGMENTO=S.SEGMENTO AND\n " +
                            "E.DATA BETWEEN ? AND ? AND\n "+
                            "S.SEGMENTO=?  "+
                            "GROUP BY MA.MARCA "+
                            "ORDER BY TOTAL DESC";*/
                sql = "SELECT COUNT(*) AS TOTAL, G.MARCA_COMPLETA AS DESCRICAO \n" +
                        "FROM GIC G \n" +
                        "WHERE \n" +
                        "G.AREA_OPERACIONAL IN ("+areaOper+") AND \n" +
                        "G.DATA_COMPLETA BETWEEN ? AND ? AND \n" +
                        "G.SEG= ? \n" +
                        "GROUP BY G.MARCA_COMPLETA \n" +
                        "ORDER BY TOTAL DESC";
                PreparedStatement ps = conn.prepareStatement(sql);
               
                ps.setString(1, ini);
                ps.setString(2, fim);
                ps.setString(3, seg);
                ResultSet rs = ps.executeQuery();
                while(rs.next()){
                    count=rs.getString("TOTAL");
                    desc= rs.getString("DESCRICAO");
                    CollectionColumns c = new CollectionColumns(new String[]{count,desc});
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
     
     public List<CollectionColumns> selectAnaliseSeg(String ini, String fim, String seg, String areaOper){
        Connection conn = new DBase(true).getConnection();
        List<CollectionColumns> list = new ArrayList<CollectionColumns>();
        String count="";
        String desc = "";
        String marcaCompleta, leves, levesPerc, medios, mediosPerc, semipesados, semipesadosPerc, extrapesados, extrapesadosPerc, total, totalPerc = "";
        if(conn!=null){
            try {
                String sql = "-- ============================ ANALISE POR SEGMENTO PARA CAMINHOES  ============================ \n" +
                            "SELECT 	MARCA_COMPLETA,\n" +
                            "		LEVES,\n" +
                            "		CONCAT(ROUND(LEVES/LEVES_*100,2), '') AS LEVES_PERC,\n" +
                            "		MEDIOS,\n" +
                            "		CONCAT(ROUND(MEDIOS/MEDIOS_*100,2), '') AS MEDIOS_PERC,\n" +
                            "		SEMIPESADOS,\n" +
                            "		CONCAT(ROUND(SEMIPESADOS/SEMIPESADOS_*100,2), '') AS SEMIPESADOS_PERC,\n" +
                            "		EXTRAPESADOS,\n" +
                            "		CONCAT(ROUND(EXTRAPESADOS/EXTRAPESADOS_*100,2), '') AS EXTRAPESADOS_PERC,\n" +
                            "		TOTAL,\n" +
                            "		CONCAT(ROUND(TOTAL/TOTAL_*100,2), '') AS TOTAL_PERC\n" +
                            "FROM (\n" +
                            "SELECT \n" +
                            "	MARCA_COMPLETA, \n" +
                            "	SUM(SUBSEGMENTO = '1.1-CAM. LEVES' OR 0) AS LEVES,\n" +
                            "	SUM(SUBSEGMENTO = '1.2-CAM. MEDIOS' OR 0) AS MEDIOS, \n" +
                            "	SUM(SUBSEGMENTO = '1.3-CAM. SEMIPESADOS' OR 0) AS SEMIPESADOS, \n" +
                            "	SUM(SUBSEGMENTO = '1.5-CAM. EXTRAPESADOS' OR 0) AS EXTRAPESADOS,\n" +
                            "    SUM(SUBSEGMENTO = '1.1-CAM. LEVES' OR SUBSEGMENTO = '1.2-CAM. MEDIOS' OR SUBSEGMENTO = '1.3-CAM. SEMIPESADOS' OR SUBSEGMENTO = '1.5-CAM. EXTRAPESADOS' OR NULL ) AS TOTAL\n" +
                            "FROM\n" +
                            "	 QUEST.GIC AS G\n" +
                            "	 WHERE G.DATA_COMPLETA BETWEEN ? AND ? \n" +
                            "	 AND G.SEGMENTO = 'CAMINHOES' AND \n" +
                            "    G.AREA_OPERACIONAL IN ("+areaOper+") "+
                            "	 GROUP BY G.MARCA_COMPLETA\n" +
                            "ORDER BY TOTAL DESC) TBL, \n" +
                            "(SELECT \n" +
                            "	SUM(SUBSEGMENTO = '1.1-CAM. LEVES' OR 0) AS LEVES_,\n" +
                            "	SUM(SUBSEGMENTO = '1.2-CAM. MEDIOS' OR 0) AS MEDIOS_, \n" +
                            "	SUM(SUBSEGMENTO = '1.3-CAM. SEMIPESADOS' OR 0) AS SEMIPESADOS_, \n" +
                            "	SUM(SUBSEGMENTO = '1.5-CAM. EXTRAPESADOS' OR 0) AS EXTRAPESADOS_,\n" +
                            "    SUM(SUBSEGMENTO = '1.1-CAM. LEVES' OR SUBSEGMENTO = '1.2-CAM. MEDIOS' OR SUBSEGMENTO = '1.3-CAM. SEMIPESADOS' OR SUBSEGMENTO = '1.5-CAM. EXTRAPESADOS' OR NULL ) AS TOTAL_\n" +
                            "\n" +
                            "FROM\n" +
                            "	 QUEST.GIC AS G\n" +
                            "	 WHERE G.DATA_COMPLETA BETWEEN ? AND ? AND \n" +
                            "    G.AREA_OPERACIONAL IN ("+areaOper+") "+
                            "	 AND G.SEGMENTO = 'CAMINHOES')TBL2";
                            
                            
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setString(1, ini);
                ps.setString(2, fim);
                ps.setString(3, ini);
                ps.setString(4, fim);
               // ps.setString(3, seg);
                ResultSet rs = ps.executeQuery();
                while(rs.next()){
                    marcaCompleta=rs.getString("MARCA_COMPLETA");
                    leves= rs.getString("LEVES");
                    levesPerc= rs.getString("LEVES_PERC");
                    medios= rs.getString("MEDIOS");
                    mediosPerc= rs.getString("MEDIOS_PERC");
                    semipesados= rs.getString("SEMIPESADOS");
                    semipesadosPerc= rs.getString("SEMIPESADOS_PERC");
                    extrapesados= rs.getString("EXTRAPESADOS");
                    extrapesadosPerc= rs.getString("EXTRAPESADOS_PERC");
                    total= rs.getString("TOTAL");
                    totalPerc= rs.getString("TOTAL_PERC");
                    CollectionColumns c = new CollectionColumns(new String[]{marcaCompleta,leves,levesPerc,medios,mediosPerc,semipesados, semipesadosPerc,extrapesados,extrapesadosPerc, total, totalPerc});
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
     
     public List<CollectionColumns> selectAnaliseSegLargeVans(String ini, String fim, String seg, String areaOper){
        Connection conn = new DBase(true).getConnection();
        List<CollectionColumns> list = new ArrayList<CollectionColumns>();
        String count="";
        String desc = "";
        
        if(conn!=null){
            try {
                String sql = "-- ============================ ANALISE POR SEGMENTO PARA LARGE VANS  ============================ \n" +
                            "SELECT 	MARCA_COMPLETA,\n" +
                            "		CHASSI,\n" +
                            "		CONCAT(ROUND(CHASSI/CHASSI_*100,2), '') AS CHASSI_PERC,\n" +
                            "		FURGAO,\n" +
                            "		CONCAT(ROUND(FURGAO/FURGAO_*100,2), '') AS FURGAO_PERC,\n" +
                            "		VAN,\n" +
                            "		CONCAT(ROUND(VAN/VAN_*100,2), '') AS VAN_PERC,\n" +
                            "		TOTAL,\n" +
                            "		CONCAT(ROUND(TOTAL/TOTAL_*100,2), '') AS TOTAL_PERC \n" +
                            "FROM (\n" +
                            "SELECT \n" +
                            "	MARCA_COMPLETA, \n" +
                            "	SUM(SUBSEGMENTO = 'CHASSI' OR 0) AS CHASSI,\n" +
                            "	SUM(SUBSEGMENTO = 'FURGAO' OR 0) AS FURGAO, \n" +
                            "	SUM(SUBSEGMENTO = 'VAN' OR 0) AS VAN, \n" +
                            "   SUM(SUBSEGMENTO = 'CHASSI' OR SUBSEGMENTO = 'FURGAO' OR SUBSEGMENTO = 'VAN' OR NULL ) AS TOTAL \n" +
                            "FROM\n" +
                            "	 QUEST.GIC AS G\n" +
                            "	 WHERE G.DATA_COMPLETA BETWEEN ? AND ? AND \n" +
                            "    G.AREA_OPERACIONAL IN ("+areaOper+") "+
                            "	 AND G.SEGMENTO = 'LARGE VANS'\n" +
                            "	 GROUP BY G.MARCA_COMPLETA\n" +
                            "ORDER BY TOTAL DESC) TBL, \n" +
                            "(SELECT \n" +
                            "	SUM(SUBSEGMENTO = 'CHASSI' OR 0) AS CHASSI_,\n" +
                            "	SUM(SUBSEGMENTO = 'FURGAO' OR 0) AS FURGAO_, \n" +
                            "	SUM(SUBSEGMENTO = 'VAN' OR 0) AS VAN_, \n" +
                            "    SUM(SUBSEGMENTO = 'CHASSI' OR SUBSEGMENTO = 'FURGAO' OR SUBSEGMENTO = 'VAN' OR NULL ) AS TOTAL_\n" +
                            "\n" +
                            "FROM\n" +
                            "	 QUEST.GIC AS G\n" +
                            "	 WHERE G.DATA_COMPLETA BETWEEN ? AND ? AND \n" +
                            "    G.AREA_OPERACIONAL IN ("+areaOper+") "+
                            "	 AND G.SEGMENTO = 'LARGE VANS')TBL2";
                            
                            
                            
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setString(1, ini);
                ps.setString(2, fim);
                ps.setString(3, ini);
                ps.setString(4, fim);
               // ps.setString(3, seg);
                ResultSet rs = ps.executeQuery();
                while(rs.next()){
                    String[] arrayCols= new String[9] ;
                    Arrays.fill(arrayCols, "");
                     arrayCols[0]=rs.getString("MARCA_COMPLETA");
                     arrayCols[1]= rs.getString("CHASSI");
                     arrayCols[2]= rs.getString("CHASSI_PERC");
                     arrayCols[3]= rs.getString("FURGAO");
                     arrayCols[4]= rs.getString("FURGAO_PERC");
                     arrayCols[5]= rs.getString("VAN");
                     arrayCols[6]= rs.getString("VAN_PERC");
                     arrayCols[7]= rs.getString("TOTAL");
                     arrayCols[8]= rs.getString("TOTAL_PERC");
                    CollectionColumns c = new CollectionColumns(arrayCols);
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
     
     public List<CollectionColumns> selectAnaliseSegMideSizeVan(String ini, String fim, String seg, String areaOper){
        Connection conn = new DBase(true).getConnection();
        List<CollectionColumns> list = new ArrayList<CollectionColumns>();
        String count="";
        String desc = "";
        
        if(conn!=null){
            try {
                String sql = "-- ============================ ANALISE POR SEGMENTO PARA MIDE SIZE VAN  ============================ \n" +
                            "SELECT 	MARCA_COMPLETA,\n" +
                            "		FURGAO,\n" +
                            "		CONCAT(ROUND(FURGAO/FURGAO_*100,2), '') AS FURGAO_PERC,\n" +
                            "		VAN,\n" +
                            "		CONCAT(ROUND(VAN/VAN_*100,2), '') AS VAN_PERC,\n" +
                            "		TOTAL,\n" +
                            "		CONCAT(ROUND(TOTAL/TOTAL_*100,2), '') AS TOTAL_PERC \n" +
                            "FROM (\n" +
                            "SELECT \n" +
                            "	MARCA_COMPLETA, \n" +
                            "	SUM(SUBSEGMENTO = 'FURGAO' OR 0) AS FURGAO, \n" +
                            "	SUM(SUBSEGMENTO = 'VAN' OR 0) AS VAN, \n" +
                            "   SUM(SUBSEGMENTO = 'FURGAO' OR SUBSEGMENTO = 'VAN' OR NULL ) AS TOTAL \n" +
                            "FROM\n" +
                            "	 QUEST.GIC AS G\n" +
                            "	 WHERE G.DATA_COMPLETA BETWEEN ? AND ? AND \n" +
                            "    G.AREA_OPERACIONAL IN ("+areaOper+") "+
                            "	 AND G.SEGMENTO = 'MIDE SIZE VAN'\n" +
                            "	 GROUP BY G.MARCA_COMPLETA\n" +
                            "ORDER BY TOTAL DESC) TBL, \n" +
                            "(SELECT \n" +
                            "	SUM(SUBSEGMENTO = 'FURGAO' OR 0) AS FURGAO_, \n" +
                            "	SUM(SUBSEGMENTO = 'VAN' OR 0) AS VAN_, \n" +
                            "    SUM(SUBSEGMENTO = 'FURGAO' OR SUBSEGMENTO = 'VAN' OR NULL ) AS TOTAL_\n" +
                            "\n" +
                            "FROM\n" +
                            "	 QUEST.GIC AS G\n" +
                            "	 WHERE G.DATA_COMPLETA BETWEEN ? AND ? AND \n" +
                            "    G.AREA_OPERACIONAL IN ("+areaOper+") "+
                            "	 AND G.SEGMENTO = 'MIDE SIZE VAN')TBL2";
                            
                            
                            
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setString(1, ini);
                ps.setString(2, fim);
                ps.setString(3, ini);
                ps.setString(4, fim);
               // ps.setString(3, seg);
                ResultSet rs = ps.executeQuery();
                while(rs.next()){
                    String[] arrayCols= new String[7] ;
                    Arrays.fill(arrayCols, "");
                     arrayCols[0]=rs.getString("MARCA_COMPLETA");
                     arrayCols[1]= rs.getString("FURGAO");
                     arrayCols[2]= rs.getString("FURGAO_PERC");
                     arrayCols[3]= rs.getString("VAN");
                     arrayCols[4]= rs.getString("VAN_PERC");
                     arrayCols[5]= rs.getString("TOTAL");
                     arrayCols[6]= rs.getString("TOTAL_PERC");
                    CollectionColumns c = new CollectionColumns(arrayCols);
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
     
      public List<CollectionColumns> selectAnaliseSegOnibus(String ini, String fim, String seg, String areaOper){
        Connection conn = new DBase(true).getConnection();
        List<CollectionColumns> list = new ArrayList<CollectionColumns>();
        String count="";
        String desc = "";
        
        if(conn!=null){
            try {
                String sql = "-- ============================ ANALISE POR SEGMENTO PARA ONIBUS  ============================ \n" +
                            "SELECT 	MARCA_COMPLETA,\n" +
                            "		MICRO,\n" +
                            "		CONCAT(ROUND(MICRO/MICRO_*100,2), '') AS MICRO_PERC,\n" +
                            "		MINI,\n" +
                            "		CONCAT(ROUND(MINI/MINI_*100,2), '') AS MINI_PERC,\n" +
                            "		ROD,\n" +
                            "		CONCAT(ROUND(ROD/ROD_*100,2), '') AS ROD_PERC,\n" +
                            "		URB,\n" +
                            "		CONCAT(ROUND(URB/URB_*100,2), '') AS URB_PERC,\n" +
                            "		ESCOLA_MICRO,\n" +
                            "		CONCAT(ROUND(ESCOLA_MICRO/ESCOLA_MICRO_*100,2), '') AS ESCOLA_MICRO_PERC,\n" +
                            "		ESCOLA_OF,\n" +
                            "		CONCAT(ROUND(ESCOLA_OF/ESCOLA_OF_*100,2), '') AS ESCOLA_OF_PERC,\n" +
                            "		TOTAL,\n" +
                            "		CONCAT(ROUND(TOTAL/TOTAL_*100,2), '') AS TOTAL_PERC\n" +
                            "FROM (\n" +
                            "SELECT\n" +
                            "	MARCA_COMPLETA, \n" +
                            "	SUM(SUBSEGMENTO = '2.1-ONIBUS MICRO' OR 0) AS MICRO,\n" +
                            "	SUM(SUBSEGMENTO = '2.2-ONIBUS MINI' OR 0) AS MINI, \n" +
                            "	SUM(SUBSEGMENTO = '2.3-ONIBUS ROD' OR 0) AS ROD, \n" +
                            "	SUM(SUBSEGMENTO = '2.4-ONIBUS URB' OR 0) AS URB, \n" +
                            "	SUM(SUBSEGMENTO = 'CAMINHO DA ESCOLA (MICRO)' OR 0) AS ESCOLA_MICRO, \n" +
                            "	SUM(SUBSEGMENTO = 'CAMINHO DA ESCOLA (OF)') AS ESCOLA_OF, \n" +
                            "    SUM(SUBSEGMENTO = '2.1-ONIBUS MICRO' OR SUBSEGMENTO = '2.2-ONIBUS MINI' OR SUBSEGMENTO = '2.3-ONIBUS ROD' OR SUBSEGMENTO = '2.4-ONIBUS URB' OR SUBSEGMENTO = 'CAMINHO DA ESCOLA (MICRO)' OR SUBSEGMENTO = 'CAMINHO DA ESCOLA (OF)' OR NULL ) AS TOTAL \n" +
                            "\n" +
                            "FROM\n" +
                            "	 QUEST.GIC AS G\n" +
                            "	 WHERE G.DATA_COMPLETA BETWEEN ? AND ? AND \n" +
                            "    G.AREA_OPERACIONAL IN ("+areaOper+") "+
                            "	 AND G.SEGMENTO = 'ONIBUS'\n" +
                            "	 GROUP BY G.MARCA_COMPLETA \n" +
                            "ORDER BY TOTAL DESC) TBL, \n" +
                            "(SELECT \n" +
                            "	SUM(SUBSEGMENTO = '2.1-ONIBUS MICRO' OR 0) AS MICRO_,\n" +
                            "	SUM(SUBSEGMENTO = '2.2-ONIBUS MINI' OR 0) AS MINI_, \n" +
                            "	SUM(SUBSEGMENTO = '2.3-ONIBUS ROD' OR 0) AS ROD_, \n" +
                            "	SUM(SUBSEGMENTO = '2.4-ONIBUS URB' OR 0) AS URB_, \n" +
                            "	SUM(SUBSEGMENTO = 'CAMINHO DA ESCOLA (MICRO)' OR 0) AS ESCOLA_MICRO_, \n" +
                            "	SUM(SUBSEGMENTO = 'CAMINHO DA ESCOLA (OF)') AS ESCOLA_OF_, \n" +
                            "    SUM(SUBSEGMENTO = '2.1-ONIBUS MICRO' OR SUBSEGMENTO = '2.2-ONIBUS MINI' OR SUBSEGMENTO = '2.3-ONIBUS ROD' OR SUBSEGMENTO = '2.4-ONIBUS URB' OR SUBSEGMENTO = 'CAMINHO DA ESCOLA (MICRO)' OR SUBSEGMENTO = 'CAMINHO DA ESCOLA (OF)' OR NULL ) AS TOTAL_\n" +
                            " \n" +
                            "FROM\n" +
                            "	 QUEST.GIC AS G\n" +
                            "	 WHERE G.DATA_COMPLETA BETWEEN ? AND ? AND \n" +
                            "    G.AREA_OPERACIONAL IN ("+areaOper+") "+
                            "	 AND G.SEGMENTO = 'ONIBUS')TBL2";
                            
                            
                            
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setString(1, ini);
                ps.setString(2, fim);
                ps.setString(3, ini);
                ps.setString(4, fim);
               // ps.setString(3, seg);
                ResultSet rs = ps.executeQuery();
                while(rs.next()){
                    String[] arrayCols= new String[15] ;
                    Arrays.fill(arrayCols, "");
                     arrayCols[0]=rs.getString("MARCA_COMPLETA");
                     arrayCols[1]= rs.getString("MICRO");
                     arrayCols[2]= rs.getString("MICRO_PERC");
                     arrayCols[3]= rs.getString("MINI");
                     arrayCols[4]= rs.getString("MINI_PERC");
                     arrayCols[5]= rs.getString("ROD");
                     arrayCols[6]= rs.getString("ROD_PERC");
                     arrayCols[7]= rs.getString("URB");
                     arrayCols[8]= rs.getString("URB_PERC");
                     arrayCols[9]= rs.getString("ESCOLA_MICRO");
                     arrayCols[10]= rs.getString("ESCOLA_MICRO_PERC");
                     arrayCols[11]= rs.getString("ESCOLA_OF");
                     arrayCols[12]= rs.getString("ESCOLA_OF_PERC");
                     arrayCols[13]= rs.getString("TOTAL");
                     arrayCols[14]= rs.getString("TOTAL_PERC");
                    CollectionColumns c = new CollectionColumns(arrayCols);
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
      
      public List<CollectionColumns> selectTopSubSeg(String ini, String fim, String seg, String subSeg, String areaOper){
        Connection conn = new DBase(true).getConnection();
        List<CollectionColumns> list = new ArrayList<CollectionColumns>();
        
        if(conn!=null){
            if(subSeg.equalsIgnoreCase("todos")){
                try {
                    String sql = "SELECT \n" +
                                "	count(*) AS TOTAL, MARCA_COMPLETA, MODELO \n" +
                                "FROM\n" +
                                "	 QUEST.GIC AS G\n" +
                                "	 WHERE G.DATA_COMPLETA BETWEEN ? AND ? \n" +
                                "	 AND G.SEG = ? AND \n" +
                               // "    AND G.SUBSEG = ? AND \n" +
                                "    G.AREA_OPERACIONAL IN ("+areaOper+") "+
                                "	 GROUP BY G.SUBSEG, G.MODELO\n" +
                                "	 ORDER BY G.SUBSEG,TOTAL DESC";



                    PreparedStatement ps = conn.prepareStatement(sql);
                    ps.setString(1, ini);
                    ps.setString(2, fim);
                    ps.setString(3, seg);
                  //  ps.setString(4, subSeg);
                   // ps.setString(3, seg);
                    ResultSet rs = ps.executeQuery();
                    while(rs.next()){
                        String[] arrayCols= new String[4] ;
                        Arrays.fill(arrayCols, "");
                         arrayCols[0]=rs.getString("MARCA_COMPLETA");
                         arrayCols[1]= rs.getString("MODELO");
                         arrayCols[2]= rs.getString("TOTAL");
                         arrayCols[3]="0";
                        CollectionColumns c = new CollectionColumns(arrayCols);
                        list.add(c);
                    }
                    conn.close();

                } catch (SQLException ex) {
                    br.com.itfox.utils.Logger.getLogger(ex, BusinessDelegate.class.getName(),ex.getMessage());
                    Logger.getLogger(BusinessDelegate.class.getName()).log(Level.SEVERE, null, ex);
                }
            }else{
                try {
                    String sql = "SELECT \n" +
                                "	count(*) AS TOTAL, MARCA_COMPLETA, MODELO \n" +
                                "FROM\n" +
                                "	 QUEST.GIC AS G\n" +
                                "	 WHERE G.DATA_COMPLETA BETWEEN ? AND ? \n" +
                                "	 AND G.SEG = ? \n" +
                                "    AND G.SUBSEG = ? AND \n" +
                                "    G.AREA_OPERACIONAL IN ("+areaOper+") "+
                                "	 GROUP BY G.SUBSEG, G.MODELO\n" +
                                "	 ORDER BY G.SUBSEG,TOTAL DESC";



                    PreparedStatement ps = conn.prepareStatement(sql);
                    ps.setString(1, ini);
                    ps.setString(2, fim);
                    ps.setString(3, seg);
                    ps.setString(4, subSeg);
                   // ps.setString(3, seg);
                    ResultSet rs = ps.executeQuery();
                    while(rs.next()){
                        String[] arrayCols= new String[4] ;
                        Arrays.fill(arrayCols, "");
                         arrayCols[0]=rs.getString("MARCA_COMPLETA");
                         arrayCols[1]= rs.getString("MODELO");
                         arrayCols[2]= rs.getString("TOTAL");
                         arrayCols[3]="0";
                        CollectionColumns c = new CollectionColumns(arrayCols);
                        list.add(c);
                    }
                    conn.close();

                } catch (SQLException ex) {
                    br.com.itfox.utils.Logger.getLogger(ex, BusinessDelegate.class.getName(),ex.getMessage());
                    Logger.getLogger(BusinessDelegate.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return list;
    }
      public List<CollectionColumns> selectTopSubSegNacional(String ini, String fim, String seg, String subSeg, String areaOper){
        Connection conn = new DBase(true).getConnection();
        List<CollectionColumns> list = new ArrayList<CollectionColumns>();
        
        if(conn!=null){
            if(subSeg.equalsIgnoreCase("todos")){
                try {
                    String sql = "SELECT \n" +
                                "	count(*) AS TOTAL, MARCA_COMPLETA, MODELO \n" +
                                "FROM\n" +
                                "	 QUEST.GIC AS G\n" +
                                "	 WHERE G.DATA_COMPLETA BETWEEN ? AND ? \n" +
                                "	 AND G.SEG = ?  \n" +
                               // "    AND G.SUBSEG = ? AND \n" +
                               // "    G.AREA_OPERACIONAL IN ("+areaOper+") "+
                                "	 GROUP BY G.SUBSEG, G.MODELO\n" +
                                "	 ORDER BY G.SUBSEG,TOTAL DESC";



                    PreparedStatement ps = conn.prepareStatement(sql);
                    ps.setString(1, ini);
                    ps.setString(2, fim);
                    ps.setString(3, seg);
                  //  ps.setString(4, subSeg);
                   // ps.setString(3, seg);
                    ResultSet rs = ps.executeQuery();
                    while(rs.next()){
                        String[] arrayCols= new String[4] ;
                        Arrays.fill(arrayCols, "");
                         arrayCols[0]=rs.getString("MARCA_COMPLETA");
                         arrayCols[1]= rs.getString("MODELO");
                         arrayCols[2]= rs.getString("TOTAL");
                         arrayCols[3]="0";
                        CollectionColumns c = new CollectionColumns(arrayCols);
                        list.add(c);
                    }
                    conn.close();

                } catch (SQLException ex) {
                    br.com.itfox.utils.Logger.getLogger(ex, BusinessDelegate.class.getName(),ex.getMessage());
                    Logger.getLogger(BusinessDelegate.class.getName()).log(Level.SEVERE, null, ex);
                }
            }else{
                try {
                    String sql = "SELECT \n" +
                                "	count(*) AS TOTAL, MARCA_COMPLETA, MODELO \n" +
                                "FROM\n" +
                                "	 QUEST.GIC AS G\n" +
                                "	 WHERE G.DATA_COMPLETA BETWEEN ? AND ? \n" +
                                "	 AND G.SEG = ? \n" +
                                "    AND G.SUBSEG = ?  \n" +
                              //  "    G.AREA_OPERACIONAL IN ("+areaOper+") "+
                                "	 GROUP BY G.SUBSEG, G.MODELO\n" +
                                "	 ORDER BY G.SUBSEG,TOTAL DESC";



                    PreparedStatement ps = conn.prepareStatement(sql);
                    ps.setString(1, ini);
                    ps.setString(2, fim);
                    ps.setString(3, seg);
                    ps.setString(4, subSeg);
                   // ps.setString(3, seg);
                    ResultSet rs = ps.executeQuery();
                    while(rs.next()){
                        String[] arrayCols= new String[4] ;
                        Arrays.fill(arrayCols, "");
                         arrayCols[0]=rs.getString("MARCA_COMPLETA");
                         arrayCols[1]= rs.getString("MODELO");
                         arrayCols[2]= rs.getString("TOTAL");
                         arrayCols[3]="0";
                        CollectionColumns c = new CollectionColumns(arrayCols);
                        list.add(c);
                    }
                    conn.close();

                } catch (SQLException ex) {
                    br.com.itfox.utils.Logger.getLogger(ex, BusinessDelegate.class.getName(),ex.getMessage());
                    Logger.getLogger(BusinessDelegate.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return list;
    }
      
    public List<CollectionColumns> selectTopRankingRegionalAreaOperacional(String ini, String fim, String seg, String subSeg, String areaOper){
        Connection conn = new DBase(true).getConnection();
        List<CollectionColumns> list = new ArrayList<CollectionColumns>();
        
        if(conn!=null){
            if(subSeg.equalsIgnoreCase("todos")){
                try {
                    String sql = "SELECT \n" +
"                                	count(*) AS TOTAL, AREA_OPERACIONAL, DEALER_AOP\n" +
"                                FROM\n" +
"                                	 QUEST.GIC AS G\n" +
"                                	 WHERE G.DATA_COMPLETA BETWEEN ? AND ? \n" +
"                                	 AND G.SEG = ? \n" +
//"                                     AND G.SUBSEG = ? \n" +
"				      AND G.AREA_OPERACIONAL IN (SELECT DISTINCT(AREA_OPERACIONAL) FROM QUEST.MUNICIPIOS WHERE REGIAO_MBB=\n" +
"                                         (SELECT REGIAO_MBB FROM QUEST.MUNICIPIOS WHERE AREA_OPERACIONAL=? LIMIT 1))\n" +
"                                     GROUP BY G.SUBSEG, G.AREA_OPERACIONAL \n" +
"                                     ORDER BY G.SUBSEG,TOTAL DESC";



                    PreparedStatement ps = conn.prepareStatement(sql);
                    ps.setString(1, ini);
                    ps.setString(2, fim);
                    ps.setString(3, seg);
                    ps.setString(4, areaOper);
                   // ps.setString(3, seg);
                    ResultSet rs = ps.executeQuery();
                    while(rs.next()){
                        String[] arrayCols= new String[4] ;
                        Arrays.fill(arrayCols, "");
                         arrayCols[0]=rs.getString("AREA_OPERACIONAL");
                         arrayCols[1]= rs.getString("DEALER_AOP");
                         arrayCols[2]= rs.getString("TOTAL");
                         arrayCols[3]="0";
                        CollectionColumns c = new CollectionColumns(arrayCols);
                        list.add(c);
                    }
                    conn.close();

                } catch (SQLException ex) {
                    br.com.itfox.utils.Logger.getLogger(ex, BusinessDelegate.class.getName(),ex.getMessage());
                    Logger.getLogger(BusinessDelegate.class.getName()).log(Level.SEVERE, null, ex);
                }
            }else{
                try {
                    String sql = "SELECT TOTAL, AOP, DEALER_AOP, MERCEDES, ROUND((MERCEDES/TOTAL)*100,2) AS MKT_SHARE,  @row := @row + 1 as row  FROM ( \n" +
"								SELECT \n" +
"                                	count(*) AS TOTAL,  AREA_OPERACIONAL AS AOP, DEALER_AOP, ( \n" +
"								SELECT COUNT(*) AS MERCEDES \n" +
"								FROM \n" +
"									 QUEST.GIC AS G \n" +
"									 WHERE G.DATA_COMPLETA BETWEEN ? AND ? \n" +
"									 AND G.SEG = ? \n" +
"									  AND G.SUBSEG = ? \n" +
"									 AND G.MARCA_COMPLETA = 'M.BENZ' \n" +
"									 AND G.AREA_OPERACIONAL = AOP \n" +
"									 GROUP BY G.MARCA_COMPLETA \n" +
"									 ORDER BY G.SUBSEG DESC) AS MERCEDES \n" +
"                                FROM \n" +
"                                	 QUEST.GIC AS G\n" +
"                                	 WHERE G.DATA_COMPLETA BETWEEN ? AND ? \n" +
"                                	 AND G.SEG = ? \n" +
"                                      AND G.SUBSEG = ? \n" +
"									 AND G.AREA_OPERACIONAL IN (SELECT DISTINCT(AREA_OPERACIONAL) FROM QUEST.MUNICIPIOS WHERE REGIAO_MBB=\n" +
"(SELECT REGIAO_MBB FROM QUEST.MUNICIPIOS WHERE AREA_OPERACIONAL=? LIMIT 1))\n" +
"                                	 GROUP BY G.SUBSEG, G.AREA_OPERACIONAL\n" +
"                                	 ORDER BY G.SUBSEG,TOTAL DESC) AS TBL, (SELECT @row := 0) r \n" +
"ORDER BY MKT_SHARE DESC, MERCEDES DESC, TOTAL DESC, DEALER_AOP ASC;";



                    PreparedStatement ps = conn.prepareStatement(sql);
                    ps.setString(1, ini);
                    ps.setString(2, fim);
                    ps.setString(3, seg);
                    ps.setString(4, subSeg);
                    ps.setString(5, ini);
                    ps.setString(6, fim);
                    ps.setString(7, seg);
                    ps.setString(8, subSeg);
                    ps.setString(9, areaOper);
                   // ps.setString(3, seg);
                    ResultSet rs = ps.executeQuery();
                    while(rs.next()){
                        String[] arrayCols= new String[6] ;
                        Arrays.fill(arrayCols, "");
                         arrayCols[0]=rs.getString("MERCEDES");
                         arrayCols[1]= rs.getString("DEALER_AOP");
                         arrayCols[2]= rs.getString("TOTAL");
                         arrayCols[3]=rs.getString("MKT_SHARE");
                         arrayCols[4]=rs.getString("row");
                         arrayCols[5]=rs.getString("AOP");
                        CollectionColumns c = new CollectionColumns(arrayCols);
                        list.add(c);
                    }
                    conn.close();

                } catch (SQLException ex) {
                    br.com.itfox.utils.Logger.getLogger(ex, BusinessDelegate.class.getName(),ex.getMessage());
                    Logger.getLogger(BusinessDelegate.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return list;
    }  
    
}
