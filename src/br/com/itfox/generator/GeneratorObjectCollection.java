package br.com.itfox.generator;

import br.com.itfox.beans.Gic;
import br.com.itfox.business.BusinessDelegate;
import org.jxls.common.Context;
import org.jxls.util.JxlsHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import org.apache.commons.lang3.text.WordUtils;
//import org.apache.commons.lang.WordUtils;


/**
 * Object collection output demo
 * @author Leonid Vysochyn
 */
public class GeneratorObjectCollection {
    static Logger logger = LoggerFactory.getLogger(GeneratorObjectCollection.class);

    public static void main(String[] args) throws ParseException, IOException {
        logger.info("Running Object Collection demo");
        //List<Employee> employees = generateSampleEmployeeData();
       // List<Cliente> clientes = generateSampleClienteData();
        String[] tableColumns = new String[]{"CHASSI","DATA_COMPLETA","MODELO","MARCA_COMPLETA","SEGMENTO","SUBSEGMENTO","PLACA","RAZAO_SOCIAL","MUNICIPIO","DEALER_AOP","ESTADO","ANOFABRICACAO","ANOMODELO","C_CPFCNPJPROPRIETARIO","C_TIPOCNPJPROPRIETARIO","C_NOMEPROPRIETARIO","C_NO_LOGR","C_NU_LOGR","C_NO_COMPL","C_NO_BAIRRO","C_NO_CIDADE","C_SG_ESTADO","C_NU_CEP","C_DDD1","C_TELEFONE1","C_DDD2","C_TELEFONE2","C_DDD3","C_TELEFONE3","C_DDD4","C_TELEFONE4","C_DDD5","C_TELEFONE5","C_DDD_CELULAR1","C_CELULAR1","C_DDD_CELULAR2","C_CELULAR2","C_EMAIL","C_AGENTEFINANCEIRO","f_TP_LOGR","f_SG_SEXO","f_DT_NASC","f_ESTADO_CIVIL","f_NO_MAE","f_DDD_CELULAR3","f_CELULAR3","f_SITUACAO","f_IDADE","f_NO_SOBRENOME","f_SIGNO","f_CNPJ_PART_SOC","f_CARGO_PART_SOC","f_RG","f_UF_RG","f_EMISSOR_RG","J_ATIVIDADE_ECONOMICA","J_NATUREZA_JURIDICA","J_CO_ATIVIDADE_ECONOMICA","J_CO_NATUREZA_JURIDICA","J_NOME_FANTASIA","J_DT_ABERTURA","J_SITUACAO_RECEITA","J_NU_CPF1","J_NOME_SOCIO_DIRETOR1","J_CARGO1","J_EMAIL_SOCIO1","J_TP_LOGR_CONTATO1","J_NO_LOGR_CONTATO1","J_NU_LOGR_CONTATO1","J_NO_COMPL_CONTATO1","J_NO_BAIRRO_CONTATO1","J_NO_CIDADE_CONTATO1","J_SG_ESTADO_CONTATO1","J_NU_CEP_CONTATO1","J_DDD1_FIXO_SOCIO1","J_TEL1_FIXO_SOCIO1","J_DDD2_FIXO_SOCIO1","J_TEL2_FIXO_SOCIO1","J_DDD3_FIXO_SOCIO1","J_TEL3_FIXO_SOCIO1","J_DDD1_CEL_SOCIO1","J_TEL1_CEL_SOCIO1","J_DDD2_CEL_SOCIO1","J_TEL2_CEL_SOCIO1","J_NU_CPF2","J_NOME_SOCIO_DIRETOR2","J_CARGO2","J_EMAIL_SOCIO2","J_TP_LOGR_CONTATO2","J_NO_LOGR_CONTATO2","J_NU_LOGR_CONTATO2","J_NO_COMPL_CONTATO2","J_NO_BAIRRO_CONTATO2","J_SG_ESTADO_CONTATO2","J_NU_CEP_CONTATO2","J_DDD1_FIXO_SOCIO2","J_TEL1_FIXO_SOCIO2","J_DDD2_FIXO_SOCIO2","J_TEL2_FIXO_SOCIO2","J_DDD3_FIXO_SOCIO2","J_TEL3_FIXO_SOCIO2","J_DDD1_CEL_SOCIO2","J_TEL1_CEL_SOCIO2","J_DDD2_CEL_SOCIO2","J_TEL2_CEL_SOCIO2","J_NU_CPF3","J_NOME_SOCIO_DIRETOR3","J_CARGO3","J_EMAIL_SOCIO3","J_TP_LOGR_CONTATO3","J_NO_LOGR_CONTATO3","J_NU_LOGR_CONTATO3","J_NO_COMPL_CONTATO3","J_NO_BAIRRO_CONTATO3","J_NO_CIDADE_CONTATO3","J_SG_ESTADO_CONTATO3","J_NU_CEP_CONTATO3","J_DDD1_FIXO_SOCIO3","J_TEL1_FIXO_SOCIO3","J_DDD2_FIXO_SOCIO3","J_TEL2_FIXO_SOCIO3","J_DDD3_FIXO_SOCIO3","J_TEL3_FIXO_SOCIO3","J_DDD1_CEL_SOCIO3","J_TEL1_CEL_SOCIO3","J_DDD2_CEL_SOCIO3","J_TEL2_CEL_SOCIO3","J_SG_SEXO_SOCIO1","J_SG_SEXO_SOCIO2","J_SG_SEXO_SOCIO3","J_DT_NASC_SOCIO1","J_DT_NASC_SOCIO2","J_DT_NASC_SOCIO3","J_CNPJ_QSA1","J_DT_ENTRADA_SOC1","J_NU_PART_CAP_SOCIAL1","J_CNPJ_QSA2","J_DT_ENTRADA_SOC2","J_NU_PART_CAP_SOCIAL2","J_CNPJ_QSA3","J_DT_ENTRADA_SOC3","J_NU_PART_CAP_SOCIAL3","J_RESTRICAO_BANCO","J_RESTRICAO_AGENCIA","J_RESTRICAO_MOTIVO","J_EMAIL1","J_NU_CPF_CNPJ1","J_NU_CPF_CNPJ2","J_NU_CPF_CNPJ3","J_NO_CIDADE_CONTATO2", "ATIVIDADE_ECONOMICA_SECUNDARIA", "CO_ATIVIDADE_ECONOMICA_SEC","SITE"};
        String tablename= "Gic";
        /*
        try(InputStream is = ObjectCollectionDemo.class.getResourceAsStream("object_collection_template.xls")) {
            try (OutputStream os = new FileOutputStream("target/object_collection_output.xls")) {
                Context context = new Context();
                context.putVar("employees", employees);
                JxlsHelper.getInstance().processTemplate(is, os, context);
            }
            * 
        }*/
        //GeneratorObjectCollection.generateBeans(tablename, tableColumns);
       // ObjectCollectionDemo.generateTemplateXls(tableColumns);
        /*
        try(InputStream is = ObjectCollectionDemo.class.getResourceAsStream("template.xls")) {
            try (OutputStream os = new FileOutputStream("target/cliente_output.xls")) {
                Context context = new Context();
                context.putVar("clientes", clientes);
                JxlsHelper.getInstance().processTemplate(is, os, context);
            }
        }
        **/
        try(InputStream is = GeneratorObjectCollection.class.getResourceAsStream("TemplateGic.xlsx")) {
            try (OutputStream os = new FileOutputStream("WebContent/relatorio_enriquecimento.xls")) {
                Context context = new Context();
                
                BusinessDelegate bd = new BusinessDelegate();
                List<Gic> gics = bd.selectGic("2015-11-01", "2015-11-07", "3", "270");
                context.putVar("gics", gics);
                JxlsHelper.getInstance().processTemplate(is, os, context);
               // System.out.println("tamanho da lista:"+gics.size());
               
            }
             
        }
    }
    
    /*
    public static List<Cliente> generateSampleClienteData() throws ParseException{
        List<Cliente> clientes = new ArrayList<Cliente>();
        clientes.add(new Cliente("Belchior", "belchiorpalma@gmail.com"));
        clientes.add(new Cliente("Fernanda","fernandamatessa@gmail.com"));
        return clientes;
    }

    public static List<Employee> generateSampleEmployeeData() throws ParseException {
        List<Employee> employees = new ArrayList<Employee>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MMM-dd", Locale.US);
        employees.add( new Employee("Elsa", dateFormat.parse("1970-Jul-10"), 1500, 0.15) );
        employees.add( new Employee("Oleg", dateFormat.parse("1973-Apr-30"), 2300, 0.25) );
        employees.add( new Employee("Neil", dateFormat.parse("1975-Oct-05"), 2500, 0.00) );
        employees.add( new Employee("Maria", dateFormat.parse("1978-Jan-07"), 1700, 0.15) );
        employees.add( new Employee("John", dateFormat.parse("1969-May-30"), 2800, 0.20) );
        employees.add( new Employee("Belchior", dateFormat.parse("1984-Sep-14"),9999,0.99));
        return employees;
    }*/
    public static void generateTemplateXls(String[] tableColumns){
        StringBuilder s = new StringBuilder();
        // attributes
        int i=0;
        for(String col: tableColumns){
            if(i<tableColumns.length-1){
                s.append("\"${gic."+col.toLowerCase()+"}\", ");
            }else{
                s.append("\"${gic."+col.toLowerCase()+"}\"");
            }
            i++;
        }
        System.out.println(s);
        
    }
    public static void generateBeans(String tablename, String[] tableColumns){
        
        //String[] tableGic = new String[]{"CHASSI","DATA_COMPLETA","MODELO","MARCA_COMPLETA","SEGMENTO","SUBSEGMENTO","PLACA","RAZAO_SOCIAL","MUNICIPIO","DEALER_AOP","ESTADO","ANOFABRICACAO","ANOMODELO","C_CPFCNPJPROPRIETARIO","C_TIPOCNPJPROPRIETARIO","C_NOMEPROPRIETARIO","C_NO_LOGR","C_NU_LOGR","C_NO_COMPL","C_NO_BAIRRO","C_NO_CIDADE","C_SG_ESTADO","C_NU_CEP","C_DDD1","C_TELEFONE1","C_DDD2","C_TELEFONE2","C_DDD3","C_TELEFONE3","C_DDD4","C_TELEFONE4","C_DDD5","C_TELEFONE5","C_DDD_CELULAR1","C_CELULAR1","C_DDD_CELULAR2","C_CELULAR2","C_EMAIL","C_AGENTEFINANCEIRO","f_TP_LOGR","f_SG_SEXO","f_DT_NASC","f_ESTADO_CIVIL","f_NO_MAE","f_DDD_CELULAR3","f_CELULAR3","f_SITUACAO","f_IDADE","f_NO_SOBRENOME","f_SIGNO","f_CNPJ_PART_SOC","f_CARGO_PART_SOC","f_RG","f_UF_RG","f_EMISSOR_RG","J_ATIVIDADE_ECONOMICA","J_NATUREZA_JURIDICA","J_CO_ATIVIDADE_ECONOMICA","J_CO_NATUREZA_JURIDICA","J_NOME_FANTASIA","J_DT_ABERTURA","J_SITUACAO_RECEITA","J_NU_CPF1","J_NOME_SOCIO_DIRETOR1","J_CARGO1","J_EMAIL_SOCIO1","J_TP_LOGR_CONTATO1","J_NO_LOGR_CONTATO1","J_NU_LOGR_CONTATO1","J_NO_COMPL_CONTATO1","J_NO_BAIRRO_CONTATO1","J_NO_CIDADE_CONTATO1","J_SG_ESTADO_CONTATO1","J_NU_CEP_CONTATO1","J_DDD1_FIXO_SOCIO1","J_TEL1_FIXO_SOCIO1","J_DDD2_FIXO_SOCIO1","J_TEL2_FIXO_SOCIO1","J_DDD3_FIXO_SOCIO1","J_TEL3_FIXO_SOCIO1","J_DDD1_CEL_SOCIO1","J_TEL1_CEL_SOCIO1","J_DDD2_CEL_SOCIO1","J_TEL2_CEL_SOCIO1","J_NU_CPF2","J_NOME_SOCIO_DIRETOR2","J_CARGO2","J_EMAIL_SOCIO2","J_TP_LOGR_CONTATO2","J_NO_LOGR_CONTATO2","J_NU_LOGR_CONTATO2","J_NO_COMPL_CONTATO2","J_NO_BAIRRO_CONTATO2","J_SG_ESTADO_CONTATO2","J_NU_CEP_CONTATO2","J_DDD1_FIXO_SOCIO2","J_TEL1_FIXO_SOCIO2","J_DDD2_FIXO_SOCIO2","J_TEL2_FIXO_SOCIO2","J_DDD3_FIXO_SOCIO2","J_TEL3_FIXO_SOCIO2","J_DDD1_CEL_SOCIO2","J_TEL1_CEL_SOCIO2","J_DDD2_CEL_SOCIO2","J_TEL2_CEL_SOCIO2","J_NU_CPF3","J_NOME_SOCIO_DIRETOR3","J_CARGO3","J_EMAIL_SOCIO3","J_TP_LOGR_CONTATO3","J_NO_LOGR_CONTATO3","J_NU_LOGR_CONTATO3","J_NO_COMPL_CONTATO3","J_NO_BAIRRO_CONTATO3","J_NO_CIDADE_CONTATO3","J_SG_ESTADO_CONTATO3","J_NU_CEP_CONTATO3","J_DDD1_FIXO_SOCIO3","J_TEL1_FIXO_SOCIO3","J_DDD2_FIXO_SOCIO3","J_TEL2_FIXO_SOCIO3","J_DDD3_FIXO_SOCIO3","J_TEL3_FIXO_SOCIO3","J_DDD1_CEL_SOCIO3","J_TEL1_CEL_SOCIO3","J_DDD2_CEL_SOCIO3","J_TEL2_CEL_SOCIO3","J_SG_SEXO_SOCIO1","J_SG_SEXO_SOCIO2","J_SG_SEXO_SOCIO3","J_DT_NASC_SOCIO1","J_DT_NASC_SOCIO2","J_DT_NASC_SOCIO3","J_CNPJ_QSA1","J_DT_ENTRADA_SOC1","J_NU_PART_CAP_SOCIAL1","J_CNPJ_QSA2","J_DT_ENTRADA_SOC2","J_NU_PART_CAP_SOCIAL2","J_CNPJ_QSA3","J_DT_ENTRADA_SOC3","J_NU_PART_CAP_SOCIAL3","J_RESTRICAO_BANCO","J_RESTRICAO_AGENCIA","J_RESTRICAO_MOTIVO","J_EMAIL1","J_NU_CPF_CNPJ1","J_NU_CPF_CNPJ2","J_NU_CPF_CNPJ3","J_NO_CIDADE_CONTATO2"};
        //String tablename= "Gic";
        StringBuilder s = new StringBuilder();
        s.append("public class "+tablename+"{\n");
        // attributes
        for(String col:tableColumns){
            s.append("  private String "+col.toLowerCase()+"; \n");
        }
        // public
        s.append("  public "+tablename+"(");
         int i=0;
         for(String col:tableColumns){
            if(i<tableColumns.length-1){
                s.append("String "+col.toLowerCase()+", ");
            }else{
                s.append("String "+col.toLowerCase()+" ");
            }
            i++;
        }
        s.append("){\n");
         for(String col:tableColumns){
            s.append("  this."+col.toLowerCase()+"="+col.toLowerCase()+";\n");
        }
        s.append("}\n\n");
        // getters
        for(String col:tableColumns){
            s.append("  public String get"+WordUtils.capitalize(col.toLowerCase())+"(){ return this."+col.toLowerCase()+";}\n");
        }
        // setters
        for(String col:tableColumns){
            s.append("  public void set"+WordUtils.capitalize(col.toLowerCase())+"(String "+col.toLowerCase()+"){ this."+col.toLowerCase()+"="+col.toLowerCase()+";}\n");
        }
        s.append("} ");
        
        System.out.println(s);
    }
}
