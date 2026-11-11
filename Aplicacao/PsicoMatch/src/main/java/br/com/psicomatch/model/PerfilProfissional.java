package br.com.psicomatch.model;

public class PerfilProfissional {
    private String cpf, formacao, especialidades, abordagem, modalidade, descricao, interesses;
    public PerfilProfissional(String cpf, String formacao, String especialidades, String abordagem, String modalidade, String descricao, String interesses) {
        this.cpf=cpf; this.formacao=formacao; this.especialidades=especialidades; this.abordagem=abordagem; this.modalidade=modalidade; this.descricao=descricao; this.interesses=interesses;
    }
    public String getCpf(){return cpf;} public String getFormacao(){return formacao;} public String getEspecialidades(){return especialidades;} public String getAbordagem(){return abordagem;} public String getModalidade(){return modalidade;} public String getDescricao(){return descricao;} public String getInteresses(){return interesses;}
}
