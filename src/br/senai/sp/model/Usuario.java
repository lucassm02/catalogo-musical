package br.senai.sp.model;

public class Usuario {
	
	private String nome;
	private String senha;
	private String avatar;
	private int questao;
	private String resposta;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	public int getQuestao() {
		return questao;
	}
	public void setQuestao(int questao) {
		this.questao = questao;
	}
	public String getResposta() {
		return resposta;
	}
	public void setResposta(String resposta) {
		this.resposta = resposta;
	}

}
