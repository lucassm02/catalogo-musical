package br.senai.sp.model;

import br.senai.sp.view.FrmRecuperarSenha;

public class Artista {
	
	private int id;
	private String nome;
	private String cidade;
	private String nascimento;
	private String estilo;
	private String usuario;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	public String getNascimento() {
		return nascimento;
	}
	public void setNascimento(String nascimento) {
		this.nascimento = nascimento;
	}
	public String getEstilo() {
		return estilo;
	}
	public void setEstilo(String esttilo) {
		this.estilo = esttilo;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	
}
