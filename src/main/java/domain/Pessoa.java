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
}
