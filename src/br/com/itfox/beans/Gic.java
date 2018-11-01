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
public class Gic{
  private String chassi; 
  private String data_completa; 
  private String modelo; 
  private String marca_completa; 
  private String segmento; 
  private String subsegmento; 
  private String placa; 
  private String razao_social; 
  private String municipio; 
  private String dealer_aop; 
  private String estado; 
  private String anofabricacao; 
  private String anomodelo; 
  private String c_cpfcnpjproprietario; 
  private String c_tipocnpjproprietario; 
  private String c_nomeproprietario=""; 
  private String c_no_logr; 
  private String c_nu_logr; 
  private String c_no_compl; 
  private String c_no_bairro; 
  private String c_no_cidade; 
  private String c_sg_estado; 
  private String c_nu_cep; 
  private String c_ddd1; 
  private String c_telefone1; 
  private String c_ddd2; 
  private String c_telefone2; 
  private String c_ddd3; 
  private String c_telefone3; 
  private String c_ddd4; 
  private String c_telefone4; 
  private String c_ddd5; 
  private String c_telefone5; 
  private String c_ddd_celular1; 
  private String c_celular1; 
  private String c_ddd_celular2; 
  private String c_celular2; 
  private String c_email; 
  private String c_agentefinanceiro; 
  private String f_tp_logr; 
  private String f_sg_sexo; 
  private String f_dt_nasc; 
  private String f_estado_civil; 
  private String f_no_mae; 
  private String f_ddd_celular3; 
  private String f_celular3; 
  private String f_situacao; 
  private String f_idade; 
  private String f_no_sobrenome; 
  private String f_signo; 
  private String f_cnpj_part_soc; 
  private String f_cargo_part_soc; 
  private String f_rg; 
  private String f_uf_rg; 
  private String f_emissor_rg; 
  private String j_atividade_economica; 
  private String j_natureza_juridica; 
  private String j_co_atividade_economica; 
  private String j_co_natureza_juridica; 
  private String j_nome_fantasia; 
  private String j_dt_abertura; 
  private String j_situacao_receita; 
  private String j_nu_cpf1; 
  private String j_nome_socio_diretor1; 
  private String j_cargo1; 
  private String j_email_socio1; 
  private String j_tp_logr_contato1; 
  private String j_no_logr_contato1; 
  private String j_nu_logr_contato1; 
  private String j_no_compl_contato1; 
  private String j_no_bairro_contato1; 
  private String j_no_cidade_contato1; 
  private String j_sg_estado_contato1; 
  private String j_nu_cep_contato1; 
  private String j_ddd1_fixo_socio1; 
  private String j_tel1_fixo_socio1; 
  private String j_ddd2_fixo_socio1; 
  private String j_tel2_fixo_socio1; 
  private String j_ddd3_fixo_socio1; 
  private String j_tel3_fixo_socio1; 
  private String j_ddd1_cel_socio1; 
  private String j_tel1_cel_socio1; 
  private String j_ddd2_cel_socio1; 
  private String j_tel2_cel_socio1; 
  private String j_nu_cpf2; 
  private String j_nome_socio_diretor2; 
  private String j_cargo2; 
  private String j_email_socio2; 
  private String j_tp_logr_contato2; 
  private String j_no_logr_contato2; 
  private String j_nu_logr_contato2; 
  private String j_no_compl_contato2; 
  private String j_no_bairro_contato2; 
  private String j_sg_estado_contato2; 
  private String j_nu_cep_contato2; 
  private String j_ddd1_fixo_socio2; 
  private String j_tel1_fixo_socio2; 
  private String j_ddd2_fixo_socio2; 
  private String j_tel2_fixo_socio2; 
  private String j_ddd3_fixo_socio2; 
  private String j_tel3_fixo_socio2; 
  private String j_ddd1_cel_socio2; 
  private String j_tel1_cel_socio2; 
  private String j_ddd2_cel_socio2; 
  private String j_tel2_cel_socio2; 
  private String j_nu_cpf3; 
  private String j_nome_socio_diretor3; 
  private String j_cargo3; 
  private String j_email_socio3; 
  private String j_tp_logr_contato3; 
  private String j_no_logr_contato3; 
  private String j_nu_logr_contato3; 
  private String j_no_compl_contato3; 
  private String j_no_bairro_contato3; 
  private String j_no_cidade_contato3; 
  private String j_sg_estado_contato3; 
  private String j_nu_cep_contato3; 
  private String j_ddd1_fixo_socio3; 
  private String j_tel1_fixo_socio3; 
  private String j_ddd2_fixo_socio3; 
  private String j_tel2_fixo_socio3; 
  private String j_ddd3_fixo_socio3; 
  private String j_tel3_fixo_socio3; 
  private String j_ddd1_cel_socio3; 
  private String j_tel1_cel_socio3; 
  private String j_ddd2_cel_socio3; 
  private String j_tel2_cel_socio3; 
  private String j_sg_sexo_socio1; 
  private String j_sg_sexo_socio2; 
  private String j_sg_sexo_socio3; 
  private String j_dt_nasc_socio1; 
  private String j_dt_nasc_socio2; 
  private String j_dt_nasc_socio3; 
  private String j_cnpj_qsa1; 
  private String j_dt_entrada_soc1; 
  private String j_nu_part_cap_social1; 
  private String j_cnpj_qsa2; 
  private String j_dt_entrada_soc2; 
  private String j_nu_part_cap_social2; 
  private String j_cnpj_qsa3; 
  private String j_dt_entrada_soc3; 
  private String j_nu_part_cap_social3; 
  private String j_restricao_banco; 
  private String j_restricao_agencia; 
  private String j_restricao_motivo; 
  private String j_email1; 
  private String j_nu_cpf_cnpj1; 
  private String j_nu_cpf_cnpj2; 
  private String j_nu_cpf_cnpj3; 
  private String j_no_cidade_contato2; 
  private String atividade_economica_secundaria; 
  private String co_atividade_economica_sec; 
  private String site; 
  private String ref_ini;
  private String ref_fim;
  private String ref_seg;
  private String ref_aop;
  private String area_operacional;
  private String seg;
  private String subseg;
  private String tipo_terreno;
  
  public Gic(){}
  public Gic(String chassi, String data_completa, String modelo, String marca_completa, String segmento, String subsegmento, String placa, String razao_social, String municipio, String dealer_aop, String estado, String anofabricacao, String anomodelo, String c_cpfcnpjproprietario, String c_tipocnpjproprietario, String c_nomeproprietario, String c_no_logr, String c_nu_logr, String c_no_compl, String c_no_bairro, String c_no_cidade, String c_sg_estado, String c_nu_cep, String c_ddd1, String c_telefone1, String c_ddd2, String c_telefone2, String c_ddd3, String c_telefone3, String c_ddd4, String c_telefone4, String c_ddd5, String c_telefone5, String c_ddd_celular1, String c_celular1, String c_ddd_celular2, String c_celular2, String c_email, String c_agentefinanceiro, String f_tp_logr, String f_sg_sexo, String f_dt_nasc, String f_estado_civil, String f_no_mae, String f_ddd_celular3, String f_celular3, String f_situacao, String f_idade, String f_no_sobrenome, String f_signo, String f_cnpj_part_soc, String f_cargo_part_soc, String f_rg, String f_uf_rg, String f_emissor_rg, String j_atividade_economica, String j_natureza_juridica, String j_co_atividade_economica, String j_co_natureza_juridica, String j_nome_fantasia, String j_dt_abertura, String j_situacao_receita, String j_nu_cpf1, String j_nome_socio_diretor1, String j_cargo1, String j_email_socio1, String j_tp_logr_contato1, String j_no_logr_contato1, String j_nu_logr_contato1, String j_no_compl_contato1, String j_no_bairro_contato1, String j_no_cidade_contato1, String j_sg_estado_contato1, String j_nu_cep_contato1, String j_ddd1_fixo_socio1, String j_tel1_fixo_socio1, String j_ddd2_fixo_socio1, String j_tel2_fixo_socio1, String j_ddd3_fixo_socio1, String j_tel3_fixo_socio1, String j_ddd1_cel_socio1, String j_tel1_cel_socio1, String j_ddd2_cel_socio1, String j_tel2_cel_socio1, String j_nu_cpf2, String j_nome_socio_diretor2, String j_cargo2, String j_email_socio2, String j_tp_logr_contato2, String j_no_logr_contato2, String j_nu_logr_contato2, String j_no_compl_contato2, String j_no_bairro_contato2, String j_sg_estado_contato2, String j_nu_cep_contato2, String j_ddd1_fixo_socio2, String j_tel1_fixo_socio2, String j_ddd2_fixo_socio2, String j_tel2_fixo_socio2, String j_ddd3_fixo_socio2, String j_tel3_fixo_socio2, String j_ddd1_cel_socio2, String j_tel1_cel_socio2, String j_ddd2_cel_socio2, String j_tel2_cel_socio2, String j_nu_cpf3, String j_nome_socio_diretor3, String j_cargo3, String j_email_socio3, String j_tp_logr_contato3, String j_no_logr_contato3, String j_nu_logr_contato3, String j_no_compl_contato3, String j_no_bairro_contato3, String j_no_cidade_contato3, String j_sg_estado_contato3, String j_nu_cep_contato3, String j_ddd1_fixo_socio3, String j_tel1_fixo_socio3, String j_ddd2_fixo_socio3, String j_tel2_fixo_socio3, String j_ddd3_fixo_socio3, String j_tel3_fixo_socio3, String j_ddd1_cel_socio3, String j_tel1_cel_socio3, String j_ddd2_cel_socio3, String j_tel2_cel_socio3, String j_sg_sexo_socio1, String j_sg_sexo_socio2, String j_sg_sexo_socio3, String j_dt_nasc_socio1, String j_dt_nasc_socio2, String j_dt_nasc_socio3, String j_cnpj_qsa1, String j_dt_entrada_soc1, String j_nu_part_cap_social1, String j_cnpj_qsa2, String j_dt_entrada_soc2, String j_nu_part_cap_social2, String j_cnpj_qsa3, String j_dt_entrada_soc3, String j_nu_part_cap_social3, String j_restricao_banco, String j_restricao_agencia, String j_restricao_motivo, String j_email1, String j_nu_cpf_cnpj1, String j_nu_cpf_cnpj2, String j_nu_cpf_cnpj3, String j_no_cidade_contato2, String atividade_economica_secundaria, String co_atividade_economica_sec, String site, String area_operacional, String seg, String subseg, String tipo_terreno ){
  this.chassi=chassi;
  this.data_completa=data_completa;
  this.modelo=modelo;
  this.marca_completa=marca_completa;
  this.segmento=segmento;
  this.subsegmento=subsegmento;
  this.placa=placa;
  this.razao_social=razao_social;
  this.municipio=municipio;
  this.dealer_aop=dealer_aop;
  this.estado=estado;
  this.anofabricacao=anofabricacao;
  this.anomodelo=anomodelo;
  this.c_cpfcnpjproprietario=c_cpfcnpjproprietario;
  this.c_tipocnpjproprietario=c_tipocnpjproprietario;
  this.c_nomeproprietario=c_nomeproprietario;
  this.c_no_logr=c_no_logr;
  this.c_nu_logr=c_nu_logr;
  this.c_no_compl=c_no_compl;
  this.c_no_bairro=c_no_bairro;
  this.c_no_cidade=c_no_cidade;
  this.c_sg_estado=c_sg_estado;
  this.c_nu_cep=c_nu_cep;
  this.c_ddd1=c_ddd1;
  this.c_telefone1=c_telefone1;
  this.c_ddd2=c_ddd2;
  this.c_telefone2=c_telefone2;
  this.c_ddd3=c_ddd3;
  this.c_telefone3=c_telefone3;
  this.c_ddd4=c_ddd4;
  this.c_telefone4=c_telefone4;
  this.c_ddd5=c_ddd5;
  this.c_telefone5=c_telefone5;
  this.c_ddd_celular1=c_ddd_celular1;
  this.c_celular1=c_celular1;
  this.c_ddd_celular2=c_ddd_celular2;
  this.c_celular2=c_celular2;
  this.c_email=c_email;
  this.c_agentefinanceiro=c_agentefinanceiro;
  this.f_tp_logr=f_tp_logr;
  this.f_sg_sexo=f_sg_sexo;
  this.f_dt_nasc=f_dt_nasc;
  this.f_estado_civil=f_estado_civil;
  this.f_no_mae=f_no_mae;
  this.f_ddd_celular3=f_ddd_celular3;
  this.f_celular3=f_celular3;
  this.f_situacao=f_situacao;
  this.f_idade=f_idade;
  this.f_no_sobrenome=f_no_sobrenome;
  this.f_signo=f_signo;
  this.f_cnpj_part_soc=f_cnpj_part_soc;
  this.f_cargo_part_soc=f_cargo_part_soc;
  this.f_rg=f_rg;
  this.f_uf_rg=f_uf_rg;
  this.f_emissor_rg=f_emissor_rg;
  this.j_atividade_economica=j_atividade_economica;
  this.j_natureza_juridica=j_natureza_juridica;
  this.j_co_atividade_economica=j_co_atividade_economica;
  this.j_co_natureza_juridica=j_co_natureza_juridica;
  this.j_nome_fantasia=j_nome_fantasia;
  this.j_dt_abertura=j_dt_abertura;
  this.j_situacao_receita=j_situacao_receita;
  this.j_nu_cpf1=j_nu_cpf1;
  this.j_nome_socio_diretor1=j_nome_socio_diretor1;
  this.j_cargo1=j_cargo1;
  this.j_email_socio1=j_email_socio1;
  this.j_tp_logr_contato1=j_tp_logr_contato1;
  this.j_no_logr_contato1=j_no_logr_contato1;
  this.j_nu_logr_contato1=j_nu_logr_contato1;
  this.j_no_compl_contato1=j_no_compl_contato1;
  this.j_no_bairro_contato1=j_no_bairro_contato1;
  this.j_no_cidade_contato1=j_no_cidade_contato1;
  this.j_sg_estado_contato1=j_sg_estado_contato1;
  this.j_nu_cep_contato1=j_nu_cep_contato1;
  this.j_ddd1_fixo_socio1=j_ddd1_fixo_socio1;
  this.j_tel1_fixo_socio1=j_tel1_fixo_socio1;
  this.j_ddd2_fixo_socio1=j_ddd2_fixo_socio1;
  this.j_tel2_fixo_socio1=j_tel2_fixo_socio1;
  this.j_ddd3_fixo_socio1=j_ddd3_fixo_socio1;
  this.j_tel3_fixo_socio1=j_tel3_fixo_socio1;
  this.j_ddd1_cel_socio1=j_ddd1_cel_socio1;
  this.j_tel1_cel_socio1=j_tel1_cel_socio1;
  this.j_ddd2_cel_socio1=j_ddd2_cel_socio1;
  this.j_tel2_cel_socio1=j_tel2_cel_socio1;
  this.j_nu_cpf2=j_nu_cpf2;
  this.j_nome_socio_diretor2=j_nome_socio_diretor2;
  this.j_cargo2=j_cargo2;
  this.j_email_socio2=j_email_socio2;
  this.j_tp_logr_contato2=j_tp_logr_contato2;
  this.j_no_logr_contato2=j_no_logr_contato2;
  this.j_nu_logr_contato2=j_nu_logr_contato2;
  this.j_no_compl_contato2=j_no_compl_contato2;
  this.j_no_bairro_contato2=j_no_bairro_contato2;
  this.j_sg_estado_contato2=j_sg_estado_contato2;
  this.j_nu_cep_contato2=j_nu_cep_contato2;
  this.j_ddd1_fixo_socio2=j_ddd1_fixo_socio2;
  this.j_tel1_fixo_socio2=j_tel1_fixo_socio2;
  this.j_ddd2_fixo_socio2=j_ddd2_fixo_socio2;
  this.j_tel2_fixo_socio2=j_tel2_fixo_socio2;
  this.j_ddd3_fixo_socio2=j_ddd3_fixo_socio2;
  this.j_tel3_fixo_socio2=j_tel3_fixo_socio2;
  this.j_ddd1_cel_socio2=j_ddd1_cel_socio2;
  this.j_tel1_cel_socio2=j_tel1_cel_socio2;
  this.j_ddd2_cel_socio2=j_ddd2_cel_socio2;
  this.j_tel2_cel_socio2=j_tel2_cel_socio2;
  this.j_nu_cpf3=j_nu_cpf3;
  this.j_nome_socio_diretor3=j_nome_socio_diretor3;
  this.j_cargo3=j_cargo3;
  this.j_email_socio3=j_email_socio3;
  this.j_tp_logr_contato3=j_tp_logr_contato3;
  this.j_no_logr_contato3=j_no_logr_contato3;
  this.j_nu_logr_contato3=j_nu_logr_contato3;
  this.j_no_compl_contato3=j_no_compl_contato3;
  this.j_no_bairro_contato3=j_no_bairro_contato3;
  this.j_no_cidade_contato3=j_no_cidade_contato3;
  this.j_sg_estado_contato3=j_sg_estado_contato3;
  this.j_nu_cep_contato3=j_nu_cep_contato3;
  this.j_ddd1_fixo_socio3=j_ddd1_fixo_socio3;
  this.j_tel1_fixo_socio3=j_tel1_fixo_socio3;
  this.j_ddd2_fixo_socio3=j_ddd2_fixo_socio3;
  this.j_tel2_fixo_socio3=j_tel2_fixo_socio3;
  this.j_ddd3_fixo_socio3=j_ddd3_fixo_socio3;
  this.j_tel3_fixo_socio3=j_tel3_fixo_socio3;
  this.j_ddd1_cel_socio3=j_ddd1_cel_socio3;
  this.j_tel1_cel_socio3=j_tel1_cel_socio3;
  this.j_ddd2_cel_socio3=j_ddd2_cel_socio3;
  this.j_tel2_cel_socio3=j_tel2_cel_socio3;
  this.j_sg_sexo_socio1=j_sg_sexo_socio1;
  this.j_sg_sexo_socio2=j_sg_sexo_socio2;
  this.j_sg_sexo_socio3=j_sg_sexo_socio3;
  this.j_dt_nasc_socio1=j_dt_nasc_socio1;
  this.j_dt_nasc_socio2=j_dt_nasc_socio2;
  this.j_dt_nasc_socio3=j_dt_nasc_socio3;
  this.j_cnpj_qsa1=j_cnpj_qsa1;
  this.j_dt_entrada_soc1=j_dt_entrada_soc1;
  this.j_nu_part_cap_social1=j_nu_part_cap_social1;
  this.j_cnpj_qsa2=j_cnpj_qsa2;
  this.j_dt_entrada_soc2=j_dt_entrada_soc2;
  this.j_nu_part_cap_social2=j_nu_part_cap_social2;
  this.j_cnpj_qsa3=j_cnpj_qsa3;
  this.j_dt_entrada_soc3=j_dt_entrada_soc3;
  this.j_nu_part_cap_social3=j_nu_part_cap_social3;
  this.j_restricao_banco=j_restricao_banco;
  this.j_restricao_agencia=j_restricao_agencia;
  this.j_restricao_motivo=j_restricao_motivo;
  this.j_email1=j_email1;
  this.j_nu_cpf_cnpj1=j_nu_cpf_cnpj1;
  this.j_nu_cpf_cnpj2=j_nu_cpf_cnpj2;
  this.j_nu_cpf_cnpj3=j_nu_cpf_cnpj3;
  this.j_no_cidade_contato2=j_no_cidade_contato2;
  this.atividade_economica_secundaria=atividade_economica_secundaria;
  this.co_atividade_economica_sec=co_atividade_economica_sec;
  this.site=site;
  this.area_operacional=area_operacional;
  this.seg=seg;
  this.subseg=subseg;
  this.tipo_terreno=tipo_terreno;
}

  public String getChassi(){ return this.chassi;}
  public String getData_completa(){ return this.data_completa;}
  public String getModelo(){ return this.modelo;}
  public String getMarca_completa(){ return this.marca_completa;}
  public String getSegmento(){ return this.segmento;}
  public String getSubsegmento(){ return this.subsegmento;}
  public String getPlaca(){ return this.placa;}
  public String getRazao_social(){ return this.razao_social;}
  public String getMunicipio(){ return this.municipio;}
  public String getDealer_aop(){ return this.dealer_aop;}
  public String getEstado(){ return this.estado;}
  public String getAnofabricacao(){ return this.anofabricacao;}
  public String getAnomodelo(){ return this.anomodelo;}
  public String getC_cpfcnpjproprietario(){ return this.c_cpfcnpjproprietario;}
  public String getC_tipocnpjproprietario(){ return this.c_tipocnpjproprietario;}
  public String getC_nomeproprietario(){ return this.c_nomeproprietario;}
  public String getC_no_logr(){ return this.c_no_logr;}
  public String getC_nu_logr(){ return this.c_nu_logr;}
  public String getC_no_compl(){ return this.c_no_compl;}
  public String getC_no_bairro(){ return this.c_no_bairro;}
  public String getC_no_cidade(){ return this.c_no_cidade;}
  public String getC_sg_estado(){ return this.c_sg_estado;}
  public String getC_nu_cep(){ return this.c_nu_cep;}
  public String getC_ddd1(){ return this.c_ddd1;}
  public String getC_telefone1(){ return this.c_telefone1;}
  public String getC_ddd2(){ return this.c_ddd2;}
  public String getC_telefone2(){ return this.c_telefone2;}
  public String getC_ddd3(){ return this.c_ddd3;}
  public String getC_telefone3(){ return this.c_telefone3;}
  public String getC_ddd4(){ return this.c_ddd4;}
  public String getC_telefone4(){ return this.c_telefone4;}
  public String getC_ddd5(){ return this.c_ddd5;}
  public String getC_telefone5(){ return this.c_telefone5;}
  public String getC_ddd_celular1(){ return this.c_ddd_celular1;}
  public String getC_celular1(){ return this.c_celular1;}
  public String getC_ddd_celular2(){ return this.c_ddd_celular2;}
  public String getC_celular2(){ return this.c_celular2;}
  public String getC_email(){ return this.c_email;}
  public String getC_agentefinanceiro(){ return this.c_agentefinanceiro;}
  public String getF_tp_logr(){ return this.f_tp_logr;}
  public String getF_sg_sexo(){ return this.f_sg_sexo;}
  public String getF_dt_nasc(){ return this.f_dt_nasc;}
  public String getF_estado_civil(){ return this.f_estado_civil;}
  public String getF_no_mae(){ return this.f_no_mae;}
  public String getF_ddd_celular3(){ return this.f_ddd_celular3;}
  public String getF_celular3(){ return this.f_celular3;}
  public String getF_situacao(){ return this.f_situacao;}
  public String getF_idade(){ return this.f_idade;}
  public String getF_no_sobrenome(){ return this.f_no_sobrenome;}
  public String getF_signo(){ return this.f_signo;}
  public String getF_cnpj_part_soc(){ return this.f_cnpj_part_soc;}
  public String getF_cargo_part_soc(){ return this.f_cargo_part_soc;}
  public String getF_rg(){ return this.f_rg;}
  public String getF_uf_rg(){ return this.f_uf_rg;}
  public String getF_emissor_rg(){ return this.f_emissor_rg;}
  public String getJ_atividade_economica(){ return this.j_atividade_economica;}
  public String getJ_natureza_juridica(){ return this.j_natureza_juridica;}
  public String getJ_co_atividade_economica(){ return this.j_co_atividade_economica;}
  public String getJ_co_natureza_juridica(){ return this.j_co_natureza_juridica;}
  public String getJ_nome_fantasia(){ return this.j_nome_fantasia;}
  public String getJ_dt_abertura(){ return this.j_dt_abertura;}
  public String getJ_situacao_receita(){ return this.j_situacao_receita;}
  public String getJ_nu_cpf1(){ return this.j_nu_cpf1;}
  public String getJ_nome_socio_diretor1(){ return this.j_nome_socio_diretor1;}
  public String getJ_cargo1(){ return this.j_cargo1;}
  public String getJ_email_socio1(){ return this.j_email_socio1;}
  public String getJ_tp_logr_contato1(){ return this.j_tp_logr_contato1;}
  public String getJ_no_logr_contato1(){ return this.j_no_logr_contato1;}
  public String getJ_nu_logr_contato1(){ return this.j_nu_logr_contato1;}
  public String getJ_no_compl_contato1(){ return this.j_no_compl_contato1;}
  public String getJ_no_bairro_contato1(){ return this.j_no_bairro_contato1;}
  public String getJ_no_cidade_contato1(){ return this.j_no_cidade_contato1;}
  public String getJ_sg_estado_contato1(){ return this.j_sg_estado_contato1;}
  public String getJ_nu_cep_contato1(){ return this.j_nu_cep_contato1;}
  public String getJ_ddd1_fixo_socio1(){ return this.j_ddd1_fixo_socio1;}
  public String getJ_tel1_fixo_socio1(){ return this.j_tel1_fixo_socio1;}
  public String getJ_ddd2_fixo_socio1(){ return this.j_ddd2_fixo_socio1;}
  public String getJ_tel2_fixo_socio1(){ return this.j_tel2_fixo_socio1;}
  public String getJ_ddd3_fixo_socio1(){ return this.j_ddd3_fixo_socio1;}
  public String getJ_tel3_fixo_socio1(){ return this.j_tel3_fixo_socio1;}
  public String getJ_ddd1_cel_socio1(){ return this.j_ddd1_cel_socio1;}
  public String getJ_tel1_cel_socio1(){ return this.j_tel1_cel_socio1;}
  public String getJ_ddd2_cel_socio1(){ return this.j_ddd2_cel_socio1;}
  public String getJ_tel2_cel_socio1(){ return this.j_tel2_cel_socio1;}
  public String getJ_nu_cpf2(){ return this.j_nu_cpf2;}
  public String getJ_nome_socio_diretor2(){ return this.j_nome_socio_diretor2;}
  public String getJ_cargo2(){ return this.j_cargo2;}
  public String getJ_email_socio2(){ return this.j_email_socio2;}
  public String getJ_tp_logr_contato2(){ return this.j_tp_logr_contato2;}
  public String getJ_no_logr_contato2(){ return this.j_no_logr_contato2;}
  public String getJ_nu_logr_contato2(){ return this.j_nu_logr_contato2;}
  public String getJ_no_compl_contato2(){ return this.j_no_compl_contato2;}
  public String getJ_no_bairro_contato2(){ return this.j_no_bairro_contato2;}
  public String getJ_sg_estado_contato2(){ return this.j_sg_estado_contato2;}
  public String getJ_nu_cep_contato2(){ return this.j_nu_cep_contato2;}
  public String getJ_ddd1_fixo_socio2(){ return this.j_ddd1_fixo_socio2;}
  public String getJ_tel1_fixo_socio2(){ return this.j_tel1_fixo_socio2;}
  public String getJ_ddd2_fixo_socio2(){ return this.j_ddd2_fixo_socio2;}
  public String getJ_tel2_fixo_socio2(){ return this.j_tel2_fixo_socio2;}
  public String getJ_ddd3_fixo_socio2(){ return this.j_ddd3_fixo_socio2;}
  public String getJ_tel3_fixo_socio2(){ return this.j_tel3_fixo_socio2;}
  public String getJ_ddd1_cel_socio2(){ return this.j_ddd1_cel_socio2;}
  public String getJ_tel1_cel_socio2(){ return this.j_tel1_cel_socio2;}
  public String getJ_ddd2_cel_socio2(){ return this.j_ddd2_cel_socio2;}
  public String getJ_tel2_cel_socio2(){ return this.j_tel2_cel_socio2;}
  public String getJ_nu_cpf3(){ return this.j_nu_cpf3;}
  public String getJ_nome_socio_diretor3(){ return this.j_nome_socio_diretor3;}
  public String getJ_cargo3(){ return this.j_cargo3;}
  public String getJ_email_socio3(){ return this.j_email_socio3;}
  public String getJ_tp_logr_contato3(){ return this.j_tp_logr_contato3;}
  public String getJ_no_logr_contato3(){ return this.j_no_logr_contato3;}
  public String getJ_nu_logr_contato3(){ return this.j_nu_logr_contato3;}
  public String getJ_no_compl_contato3(){ return this.j_no_compl_contato3;}
  public String getJ_no_bairro_contato3(){ return this.j_no_bairro_contato3;}
  public String getJ_no_cidade_contato3(){ return this.j_no_cidade_contato3;}
  public String getJ_sg_estado_contato3(){ return this.j_sg_estado_contato3;}
  public String getJ_nu_cep_contato3(){ return this.j_nu_cep_contato3;}
  public String getJ_ddd1_fixo_socio3(){ return this.j_ddd1_fixo_socio3;}
  public String getJ_tel1_fixo_socio3(){ return this.j_tel1_fixo_socio3;}
  public String getJ_ddd2_fixo_socio3(){ return this.j_ddd2_fixo_socio3;}
  public String getJ_tel2_fixo_socio3(){ return this.j_tel2_fixo_socio3;}
  public String getJ_ddd3_fixo_socio3(){ return this.j_ddd3_fixo_socio3;}
  public String getJ_tel3_fixo_socio3(){ return this.j_tel3_fixo_socio3;}
  public String getJ_ddd1_cel_socio3(){ return this.j_ddd1_cel_socio3;}
  public String getJ_tel1_cel_socio3(){ return this.j_tel1_cel_socio3;}
  public String getJ_ddd2_cel_socio3(){ return this.j_ddd2_cel_socio3;}
  public String getJ_tel2_cel_socio3(){ return this.j_tel2_cel_socio3;}
  public String getJ_sg_sexo_socio1(){ return this.j_sg_sexo_socio1;}
  public String getJ_sg_sexo_socio2(){ return this.j_sg_sexo_socio2;}
  public String getJ_sg_sexo_socio3(){ return this.j_sg_sexo_socio3;}
  public String getJ_dt_nasc_socio1(){ return this.j_dt_nasc_socio1;}
  public String getJ_dt_nasc_socio2(){ return this.j_dt_nasc_socio2;}
  public String getJ_dt_nasc_socio3(){ return this.j_dt_nasc_socio3;}
  public String getJ_cnpj_qsa1(){ return this.j_cnpj_qsa1;}
  public String getJ_dt_entrada_soc1(){ return this.j_dt_entrada_soc1;}
  public String getJ_nu_part_cap_social1(){ return this.j_nu_part_cap_social1;}
  public String getJ_cnpj_qsa2(){ return this.j_cnpj_qsa2;}
  public String getJ_dt_entrada_soc2(){ return this.j_dt_entrada_soc2;}
  public String getJ_nu_part_cap_social2(){ return this.j_nu_part_cap_social2;}
  public String getJ_cnpj_qsa3(){ return this.j_cnpj_qsa3;}
  public String getJ_dt_entrada_soc3(){ return this.j_dt_entrada_soc3;}
  public String getJ_nu_part_cap_social3(){ return this.j_nu_part_cap_social3;}
  public String getJ_restricao_banco(){ return this.j_restricao_banco;}
  public String getJ_restricao_agencia(){ return this.j_restricao_agencia;}
  public String getJ_restricao_motivo(){ return this.j_restricao_motivo;}
  public String getJ_email1(){ return this.j_email1;}
  public String getJ_nu_cpf_cnpj1(){ return this.j_nu_cpf_cnpj1;}
  public String getJ_nu_cpf_cnpj2(){ return this.j_nu_cpf_cnpj2;}
  public String getJ_nu_cpf_cnpj3(){ return this.j_nu_cpf_cnpj3;}
  public String getJ_no_cidade_contato2(){ return this.j_no_cidade_contato2;}
  public String getAtividade_economica_secundaria(){ return this.atividade_economica_secundaria;}
  public String getCo_atividade_economica_sec(){ return this.co_atividade_economica_sec;}
  public String getSite(){ return this.site;}
  public void setChassi(String chassi){ this.chassi=chassi;}
  public void setData_completa(String data_completa){ this.data_completa=data_completa;}
  public void setModelo(String modelo){ this.modelo=modelo;}
  public void setMarca_completa(String marca_completa){ this.marca_completa=marca_completa;}
  public void setSegmento(String segmento){ this.segmento=segmento;}
  public void setSubsegmento(String subsegmento){ this.subsegmento=subsegmento;}
  public void setPlaca(String placa){ this.placa=placa;}
  public void setRazao_social(String razao_social){ this.razao_social=razao_social;}
  public void setMunicipio(String municipio){ this.municipio=municipio;}
  public void setDealer_aop(String dealer_aop){ this.dealer_aop=dealer_aop;}
  public void setEstado(String estado){ this.estado=estado;}
  public void setAnofabricacao(String anofabricacao){ this.anofabricacao=anofabricacao;}
  public void setAnomodelo(String anomodelo){ this.anomodelo=anomodelo;}
  public void setC_cpfcnpjproprietario(String c_cpfcnpjproprietario){ this.c_cpfcnpjproprietario=c_cpfcnpjproprietario;}
  public void setC_tipocnpjproprietario(String c_tipocnpjproprietario){ this.c_tipocnpjproprietario=c_tipocnpjproprietario;}
  public void setC_nomeproprietario(String c_nomeproprietario){ this.c_nomeproprietario=c_nomeproprietario;}
  public void setC_no_logr(String c_no_logr){ this.c_no_logr=c_no_logr;}
  public void setC_nu_logr(String c_nu_logr){ this.c_nu_logr=c_nu_logr;}
  public void setC_no_compl(String c_no_compl){ this.c_no_compl=c_no_compl;}
  public void setC_no_bairro(String c_no_bairro){ this.c_no_bairro=c_no_bairro;}
  public void setC_no_cidade(String c_no_cidade){ this.c_no_cidade=c_no_cidade;}
  public void setC_sg_estado(String c_sg_estado){ this.c_sg_estado=c_sg_estado;}
  public void setC_nu_cep(String c_nu_cep){ this.c_nu_cep=c_nu_cep;}
  public void setC_ddd1(String c_ddd1){ this.c_ddd1=c_ddd1;}
  public void setC_telefone1(String c_telefone1){ this.c_telefone1=c_telefone1;}
  public void setC_ddd2(String c_ddd2){ this.c_ddd2=c_ddd2;}
  public void setC_telefone2(String c_telefone2){ this.c_telefone2=c_telefone2;}
  public void setC_ddd3(String c_ddd3){ this.c_ddd3=c_ddd3;}
  public void setC_telefone3(String c_telefone3){ this.c_telefone3=c_telefone3;}
  public void setC_ddd4(String c_ddd4){ this.c_ddd4=c_ddd4;}
  public void setC_telefone4(String c_telefone4){ this.c_telefone4=c_telefone4;}
  public void setC_ddd5(String c_ddd5){ this.c_ddd5=c_ddd5;}
  public void setC_telefone5(String c_telefone5){ this.c_telefone5=c_telefone5;}
  public void setC_ddd_celular1(String c_ddd_celular1){ this.c_ddd_celular1=c_ddd_celular1;}
  public void setC_celular1(String c_celular1){ this.c_celular1=c_celular1;}
  public void setC_ddd_celular2(String c_ddd_celular2){ this.c_ddd_celular2=c_ddd_celular2;}
  public void setC_celular2(String c_celular2){ this.c_celular2=c_celular2;}
  public void setC_email(String c_email){ this.c_email=c_email;}
  public void setC_agentefinanceiro(String c_agentefinanceiro){ this.c_agentefinanceiro=c_agentefinanceiro;}
  public void setF_tp_logr(String f_tp_logr){ this.f_tp_logr=f_tp_logr;}
  public void setF_sg_sexo(String f_sg_sexo){ this.f_sg_sexo=f_sg_sexo;}
  public void setF_dt_nasc(String f_dt_nasc){ this.f_dt_nasc=f_dt_nasc;}
  public void setF_estado_civil(String f_estado_civil){ this.f_estado_civil=f_estado_civil;}
  public void setF_no_mae(String f_no_mae){ this.f_no_mae=f_no_mae;}
  public void setF_ddd_celular3(String f_ddd_celular3){ this.f_ddd_celular3=f_ddd_celular3;}
  public void setF_celular3(String f_celular3){ this.f_celular3=f_celular3;}
  public void setF_situacao(String f_situacao){ this.f_situacao=f_situacao;}
  public void setF_idade(String f_idade){ this.f_idade=f_idade;}
  public void setF_no_sobrenome(String f_no_sobrenome){ this.f_no_sobrenome=f_no_sobrenome;}
  public void setF_signo(String f_signo){ this.f_signo=f_signo;}
  public void setF_cnpj_part_soc(String f_cnpj_part_soc){ this.f_cnpj_part_soc=f_cnpj_part_soc;}
  public void setF_cargo_part_soc(String f_cargo_part_soc){ this.f_cargo_part_soc=f_cargo_part_soc;}
  public void setF_rg(String f_rg){ this.f_rg=f_rg;}
  public void setF_uf_rg(String f_uf_rg){ this.f_uf_rg=f_uf_rg;}
  public void setF_emissor_rg(String f_emissor_rg){ this.f_emissor_rg=f_emissor_rg;}
  public void setJ_atividade_economica(String j_atividade_economica){ this.j_atividade_economica=j_atividade_economica;}
  public void setJ_natureza_juridica(String j_natureza_juridica){ this.j_natureza_juridica=j_natureza_juridica;}
  public void setJ_co_atividade_economica(String j_co_atividade_economica){ this.j_co_atividade_economica=j_co_atividade_economica;}
  public void setJ_co_natureza_juridica(String j_co_natureza_juridica){ this.j_co_natureza_juridica=j_co_natureza_juridica;}
  public void setJ_nome_fantasia(String j_nome_fantasia){ this.j_nome_fantasia=j_nome_fantasia;}
  public void setJ_dt_abertura(String j_dt_abertura){ this.j_dt_abertura=j_dt_abertura;}
  public void setJ_situacao_receita(String j_situacao_receita){ this.j_situacao_receita=j_situacao_receita;}
  public void setJ_nu_cpf1(String j_nu_cpf1){ this.j_nu_cpf1=j_nu_cpf1;}
  public void setJ_nome_socio_diretor1(String j_nome_socio_diretor1){ this.j_nome_socio_diretor1=j_nome_socio_diretor1;}
  public void setJ_cargo1(String j_cargo1){ this.j_cargo1=j_cargo1;}
  public void setJ_email_socio1(String j_email_socio1){ this.j_email_socio1=j_email_socio1;}
  public void setJ_tp_logr_contato1(String j_tp_logr_contato1){ this.j_tp_logr_contato1=j_tp_logr_contato1;}
  public void setJ_no_logr_contato1(String j_no_logr_contato1){ this.j_no_logr_contato1=j_no_logr_contato1;}
  public void setJ_nu_logr_contato1(String j_nu_logr_contato1){ this.j_nu_logr_contato1=j_nu_logr_contato1;}
  public void setJ_no_compl_contato1(String j_no_compl_contato1){ this.j_no_compl_contato1=j_no_compl_contato1;}
  public void setJ_no_bairro_contato1(String j_no_bairro_contato1){ this.j_no_bairro_contato1=j_no_bairro_contato1;}
  public void setJ_no_cidade_contato1(String j_no_cidade_contato1){ this.j_no_cidade_contato1=j_no_cidade_contato1;}
  public void setJ_sg_estado_contato1(String j_sg_estado_contato1){ this.j_sg_estado_contato1=j_sg_estado_contato1;}
  public void setJ_nu_cep_contato1(String j_nu_cep_contato1){ this.j_nu_cep_contato1=j_nu_cep_contato1;}
  public void setJ_ddd1_fixo_socio1(String j_ddd1_fixo_socio1){ this.j_ddd1_fixo_socio1=j_ddd1_fixo_socio1;}
  public void setJ_tel1_fixo_socio1(String j_tel1_fixo_socio1){ this.j_tel1_fixo_socio1=j_tel1_fixo_socio1;}
  public void setJ_ddd2_fixo_socio1(String j_ddd2_fixo_socio1){ this.j_ddd2_fixo_socio1=j_ddd2_fixo_socio1;}
  public void setJ_tel2_fixo_socio1(String j_tel2_fixo_socio1){ this.j_tel2_fixo_socio1=j_tel2_fixo_socio1;}
  public void setJ_ddd3_fixo_socio1(String j_ddd3_fixo_socio1){ this.j_ddd3_fixo_socio1=j_ddd3_fixo_socio1;}
  public void setJ_tel3_fixo_socio1(String j_tel3_fixo_socio1){ this.j_tel3_fixo_socio1=j_tel3_fixo_socio1;}
  public void setJ_ddd1_cel_socio1(String j_ddd1_cel_socio1){ this.j_ddd1_cel_socio1=j_ddd1_cel_socio1;}
  public void setJ_tel1_cel_socio1(String j_tel1_cel_socio1){ this.j_tel1_cel_socio1=j_tel1_cel_socio1;}
  public void setJ_ddd2_cel_socio1(String j_ddd2_cel_socio1){ this.j_ddd2_cel_socio1=j_ddd2_cel_socio1;}
  public void setJ_tel2_cel_socio1(String j_tel2_cel_socio1){ this.j_tel2_cel_socio1=j_tel2_cel_socio1;}
  public void setJ_nu_cpf2(String j_nu_cpf2){ this.j_nu_cpf2=j_nu_cpf2;}
  public void setJ_nome_socio_diretor2(String j_nome_socio_diretor2){ this.j_nome_socio_diretor2=j_nome_socio_diretor2;}
  public void setJ_cargo2(String j_cargo2){ this.j_cargo2=j_cargo2;}
  public void setJ_email_socio2(String j_email_socio2){ this.j_email_socio2=j_email_socio2;}
  public void setJ_tp_logr_contato2(String j_tp_logr_contato2){ this.j_tp_logr_contato2=j_tp_logr_contato2;}
  public void setJ_no_logr_contato2(String j_no_logr_contato2){ this.j_no_logr_contato2=j_no_logr_contato2;}
  public void setJ_nu_logr_contato2(String j_nu_logr_contato2){ this.j_nu_logr_contato2=j_nu_logr_contato2;}
  public void setJ_no_compl_contato2(String j_no_compl_contato2){ this.j_no_compl_contato2=j_no_compl_contato2;}
  public void setJ_no_bairro_contato2(String j_no_bairro_contato2){ this.j_no_bairro_contato2=j_no_bairro_contato2;}
  public void setJ_sg_estado_contato2(String j_sg_estado_contato2){ this.j_sg_estado_contato2=j_sg_estado_contato2;}
  public void setJ_nu_cep_contato2(String j_nu_cep_contato2){ this.j_nu_cep_contato2=j_nu_cep_contato2;}
  public void setJ_ddd1_fixo_socio2(String j_ddd1_fixo_socio2){ this.j_ddd1_fixo_socio2=j_ddd1_fixo_socio2;}
  public void setJ_tel1_fixo_socio2(String j_tel1_fixo_socio2){ this.j_tel1_fixo_socio2=j_tel1_fixo_socio2;}
  public void setJ_ddd2_fixo_socio2(String j_ddd2_fixo_socio2){ this.j_ddd2_fixo_socio2=j_ddd2_fixo_socio2;}
  public void setJ_tel2_fixo_socio2(String j_tel2_fixo_socio2){ this.j_tel2_fixo_socio2=j_tel2_fixo_socio2;}
  public void setJ_ddd3_fixo_socio2(String j_ddd3_fixo_socio2){ this.j_ddd3_fixo_socio2=j_ddd3_fixo_socio2;}
  public void setJ_tel3_fixo_socio2(String j_tel3_fixo_socio2){ this.j_tel3_fixo_socio2=j_tel3_fixo_socio2;}
  public void setJ_ddd1_cel_socio2(String j_ddd1_cel_socio2){ this.j_ddd1_cel_socio2=j_ddd1_cel_socio2;}
  public void setJ_tel1_cel_socio2(String j_tel1_cel_socio2){ this.j_tel1_cel_socio2=j_tel1_cel_socio2;}
  public void setJ_ddd2_cel_socio2(String j_ddd2_cel_socio2){ this.j_ddd2_cel_socio2=j_ddd2_cel_socio2;}
  public void setJ_tel2_cel_socio2(String j_tel2_cel_socio2){ this.j_tel2_cel_socio2=j_tel2_cel_socio2;}
  public void setJ_nu_cpf3(String j_nu_cpf3){ this.j_nu_cpf3=j_nu_cpf3;}
  public void setJ_nome_socio_diretor3(String j_nome_socio_diretor3){ this.j_nome_socio_diretor3=j_nome_socio_diretor3;}
  public void setJ_cargo3(String j_cargo3){ this.j_cargo3=j_cargo3;}
  public void setJ_email_socio3(String j_email_socio3){ this.j_email_socio3=j_email_socio3;}
  public void setJ_tp_logr_contato3(String j_tp_logr_contato3){ this.j_tp_logr_contato3=j_tp_logr_contato3;}
  public void setJ_no_logr_contato3(String j_no_logr_contato3){ this.j_no_logr_contato3=j_no_logr_contato3;}
  public void setJ_nu_logr_contato3(String j_nu_logr_contato3){ this.j_nu_logr_contato3=j_nu_logr_contato3;}
  public void setJ_no_compl_contato3(String j_no_compl_contato3){ this.j_no_compl_contato3=j_no_compl_contato3;}
  public void setJ_no_bairro_contato3(String j_no_bairro_contato3){ this.j_no_bairro_contato3=j_no_bairro_contato3;}
  public void setJ_no_cidade_contato3(String j_no_cidade_contato3){ this.j_no_cidade_contato3=j_no_cidade_contato3;}
  public void setJ_sg_estado_contato3(String j_sg_estado_contato3){ this.j_sg_estado_contato3=j_sg_estado_contato3;}
  public void setJ_nu_cep_contato3(String j_nu_cep_contato3){ this.j_nu_cep_contato3=j_nu_cep_contato3;}
  public void setJ_ddd1_fixo_socio3(String j_ddd1_fixo_socio3){ this.j_ddd1_fixo_socio3=j_ddd1_fixo_socio3;}
  public void setJ_tel1_fixo_socio3(String j_tel1_fixo_socio3){ this.j_tel1_fixo_socio3=j_tel1_fixo_socio3;}
  public void setJ_ddd2_fixo_socio3(String j_ddd2_fixo_socio3){ this.j_ddd2_fixo_socio3=j_ddd2_fixo_socio3;}
  public void setJ_tel2_fixo_socio3(String j_tel2_fixo_socio3){ this.j_tel2_fixo_socio3=j_tel2_fixo_socio3;}
  public void setJ_ddd3_fixo_socio3(String j_ddd3_fixo_socio3){ this.j_ddd3_fixo_socio3=j_ddd3_fixo_socio3;}
  public void setJ_tel3_fixo_socio3(String j_tel3_fixo_socio3){ this.j_tel3_fixo_socio3=j_tel3_fixo_socio3;}
  public void setJ_ddd1_cel_socio3(String j_ddd1_cel_socio3){ this.j_ddd1_cel_socio3=j_ddd1_cel_socio3;}
  public void setJ_tel1_cel_socio3(String j_tel1_cel_socio3){ this.j_tel1_cel_socio3=j_tel1_cel_socio3;}
  public void setJ_ddd2_cel_socio3(String j_ddd2_cel_socio3){ this.j_ddd2_cel_socio3=j_ddd2_cel_socio3;}
  public void setJ_tel2_cel_socio3(String j_tel2_cel_socio3){ this.j_tel2_cel_socio3=j_tel2_cel_socio3;}
  public void setJ_sg_sexo_socio1(String j_sg_sexo_socio1){ this.j_sg_sexo_socio1=j_sg_sexo_socio1;}
  public void setJ_sg_sexo_socio2(String j_sg_sexo_socio2){ this.j_sg_sexo_socio2=j_sg_sexo_socio2;}
  public void setJ_sg_sexo_socio3(String j_sg_sexo_socio3){ this.j_sg_sexo_socio3=j_sg_sexo_socio3;}
  public void setJ_dt_nasc_socio1(String j_dt_nasc_socio1){ this.j_dt_nasc_socio1=j_dt_nasc_socio1;}
  public void setJ_dt_nasc_socio2(String j_dt_nasc_socio2){ this.j_dt_nasc_socio2=j_dt_nasc_socio2;}
  public void setJ_dt_nasc_socio3(String j_dt_nasc_socio3){ this.j_dt_nasc_socio3=j_dt_nasc_socio3;}
  public void setJ_cnpj_qsa1(String j_cnpj_qsa1){ this.j_cnpj_qsa1=j_cnpj_qsa1;}
  public void setJ_dt_entrada_soc1(String j_dt_entrada_soc1){ this.j_dt_entrada_soc1=j_dt_entrada_soc1;}
  public void setJ_nu_part_cap_social1(String j_nu_part_cap_social1){ this.j_nu_part_cap_social1=j_nu_part_cap_social1;}
  public void setJ_cnpj_qsa2(String j_cnpj_qsa2){ this.j_cnpj_qsa2=j_cnpj_qsa2;}
  public void setJ_dt_entrada_soc2(String j_dt_entrada_soc2){ this.j_dt_entrada_soc2=j_dt_entrada_soc2;}
  public void setJ_nu_part_cap_social2(String j_nu_part_cap_social2){ this.j_nu_part_cap_social2=j_nu_part_cap_social2;}
  public void setJ_cnpj_qsa3(String j_cnpj_qsa3){ this.j_cnpj_qsa3=j_cnpj_qsa3;}
  public void setJ_dt_entrada_soc3(String j_dt_entrada_soc3){ this.j_dt_entrada_soc3=j_dt_entrada_soc3;}
  public void setJ_nu_part_cap_social3(String j_nu_part_cap_social3){ this.j_nu_part_cap_social3=j_nu_part_cap_social3;}
  public void setJ_restricao_banco(String j_restricao_banco){ this.j_restricao_banco=j_restricao_banco;}
  public void setJ_restricao_agencia(String j_restricao_agencia){ this.j_restricao_agencia=j_restricao_agencia;}
  public void setJ_restricao_motivo(String j_restricao_motivo){ this.j_restricao_motivo=j_restricao_motivo;}
  public void setJ_email1(String j_email1){ this.j_email1=j_email1;}
  public void setJ_nu_cpf_cnpj1(String j_nu_cpf_cnpj1){ this.j_nu_cpf_cnpj1=j_nu_cpf_cnpj1;}
  public void setJ_nu_cpf_cnpj2(String j_nu_cpf_cnpj2){ this.j_nu_cpf_cnpj2=j_nu_cpf_cnpj2;}
  public void setJ_nu_cpf_cnpj3(String j_nu_cpf_cnpj3){ this.j_nu_cpf_cnpj3=j_nu_cpf_cnpj3;}
  public void setJ_no_cidade_contato2(String j_no_cidade_contato2){ this.j_no_cidade_contato2=j_no_cidade_contato2;}
  public void setAtividade_economica_secundaria(String atividade_economica_secundaria){ this.atividade_economica_secundaria=atividade_economica_secundaria;}
  public void setCo_atividade_economica_sec(String co_atividade_economica_sec){ this.co_atividade_economica_sec=co_atividade_economica_sec;}
  public void setSite(String site){ this.site=site;}

    public String getRef_ini() {
        return ref_ini;
    }

    public void setRef_ini(String ref_ini) {
        this.ref_ini = ref_ini;
    }

    public String getRef_fim() {
        return ref_fim;
    }

    public void setRef_fim(String ref_fim) {
        this.ref_fim = ref_fim;
    }

    public String getRef_seg() {
        return ref_seg;
    }

    public void setRef_seg(String ref_seg) {
        this.ref_seg = ref_seg;
    }

    public String getRef_aop() {
        return ref_aop;
    }

    public void setRef_aop(String ref_aop) {
        this.ref_aop = ref_aop;
    }

    public String getArea_operacional() {
        return area_operacional;
    }

    public void setArea_operacional(String area_operacional) {
        this.area_operacional = area_operacional;
    }

    public String getSeg() {
        return seg;
    }

    public void setSeg(String seg) {
        this.seg = seg;
    }

    public String getSubseg() {
        return subseg;
    }

    public void setSubseg(String subseg) {
        this.subseg = subseg;
    }

    public String getTipo_terreno() {
        return tipo_terreno;
    }

    public void setTipo_terreno(String tipo_terreno) {
        this.tipo_terreno = tipo_terreno;
    }
  
} 
