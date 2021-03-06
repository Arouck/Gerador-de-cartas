package domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Usuario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Column
	private String username;
	
	@Column
	private String senha;
	
	@Column
	private Character papel;
	
	@Column
	private String salt;
	
	public Usuario() {
	}

	public Usuario(String username, String senha, Character papel, String salt) {
		this.username = username;
		this.senha = senha;
		this.papel = papel;
		this.salt = salt;
	}

	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getSenha() {
		return senha;
	}
	
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public Character getPapel() {
		return papel;
	}
	
	public void setPapel(Character papel) {
		this.papel = papel;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Usuario [username=" + username + ", senha=" + senha + ", papel=" + papel + "]";
	}
	
	
}
