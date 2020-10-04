package bean;

import java.io.Serializable;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.omnifaces.util.Messages;

import dao.UsuarioDAO;
import domain.Usuario;
import util.AutenticacaoUtil;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class CadastroBean implements Serializable {

	private Usuario usuario;
	private UsuarioDAO dao;
	private List<String> nomesDeUsuario;

	@PostConstruct
	public void instanciarUsuario() {
		usuario = new Usuario();

		if (dao == null) {
			dao = new UsuarioDAO();
		}

		if (nomesDeUsuario == null) {
			nomesDeUsuario = new ArrayList<>();
			nomesDeUsuario = dao.buscarNomesDeUsuario();
		}

	}

	public boolean verificarExistenciaDeNomeDeUsuario(String nomeDeUsuario) {
		return nomesDeUsuario.contains(nomeDeUsuario);
	}

	public void cadastrarNovoUsuario() {
		if (verificarExistenciaDeNomeDeUsuario(usuario.getUsername())) {
			Messages.addGlobalError("Nome de usuário já existente, favor escolher outro.");
		} else {
			try {
				UsuarioDAO dao = new UsuarioDAO();
				usuario.setSalt(AutenticacaoUtil.gerarSaltAleatorio());
				usuario.setSenha(AutenticacaoUtil.criptografarSenha(usuario.getSenha(), usuario.getSalt()));
				dao.salvar(usuario);
				Messages.addGlobalInfo("Usuário salvo com sucesso.");
			} catch (GeneralSecurityException e) {
				e.printStackTrace();
				Messages.addGlobalInfo("Erro ao tentar salvar o usuário.");
			}

			instanciarUsuario();
		}
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<String> getNomesDeUsuario() {
		return nomesDeUsuario;
	}

	public void setNomesDeUsuario(List<String> nomesDeUsuario) {
		this.nomesDeUsuario = nomesDeUsuario;
	}

	public UsuarioDAO getDao() {
		return dao;
	}

	public void setDao(UsuarioDAO dao) {
		this.dao = dao;
	}
}
