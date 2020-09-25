package domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Transient;

@SuppressWarnings("serial")
@Entity
public class Pessoa extends GenericDomain {

	@Column
	private String nome;

	@Column
	private String cpf;

	@Column
	private String endereco;

	@Column
	private String cep;

	@Column
	private String numeroendereco;

	@Column
	private String bairro;

	@Column
	private String cidade;

	@Column
	private String complemento;

	@Column
	private String operacao;
	
	@Transient
	private String uf;
	
	@Transient
	private String mensagemErro;

	public Pessoa() {
	}

	public Pessoa(String nome, String cep, String endereco, String complemento, String numeroendereco,
			String bairro, String cidade, String uf) {
		this.nome = nome;
		this.endereco = endereco;
		this.cep = cep;
		this.numeroendereco = numeroendereco;
		this.bairro = bairro;
		this.cidade = cidade;
		this.complemento = complemento;
		this.uf = uf;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}
	
	public String getUf() {
		return uf;
	}
	
	public void setUf(String uf) {
		this.uf = uf;
	}

	public String getMensagemErro() {
		return mensagemErro;
	}

	public void setMensagemErro(String mensagemErro) {
		this.mensagemErro = mensagemErro;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getNumeroendereco() {
		return numeroendereco;
	}

	public void setNumeroendereco(String numeroendereco) {
		this.numeroendereco = numeroendereco;
	}

	public String getNome() {
		return nome;
	}

	public String getEndereco() {
		return endereco;
	}

	public String getCidade() {
		return cidade;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

}
