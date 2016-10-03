package br.com.agenda.http;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Contato {

	private Integer id;
	private String nome;
	private Integer telefone;
	private Date dataCadastro;

	public Contato() {
	}

	public Contato(Integer id, String nome, Integer telefone, Date dataCadastro) {
		this.id = id;
		this.nome = nome;
		this.telefone = telefone;
		this.dataCadastro = dataCadastro;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getTelefone() {
		return telefone;
	}

	public void setTelefone(Integer telefone) {
		this.telefone = telefone;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}
}