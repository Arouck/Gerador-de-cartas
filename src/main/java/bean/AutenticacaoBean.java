package bean;

import java.io.IOException;
import java.io.Serializable;
import java.security.GeneralSecurityException;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;

import dao.UsuarioDAO;
import domain.Usuario;
import util.AutenticacaoUtil;

@SuppressWarnings("serial")
@ManagedBean
@SessionScoped
public class AutenticacaoBean implements Serializable {

	private Usuario usuario;
	private Usuario usuarioLogado;

	@PostConstruct
	public void iniciar() {
		usuario = new Usuario();
	}

	public void autenticar() {
		try {
			UsuarioDAO dao = new UsuarioDAO();
			usuarioLogado = dao.buscarPorUsuario(usuario.getUsername());

			if (usuarioLogado == null) {
				Messages.addGlobalError("Nome de usuário e/ou senha incorretos");
				iniciar();
				return;
			}

			String senhaCriptografada = AutenticacaoUtil.criptografarSenha(usuario.getSenha(), usuarioLogado.getSalt());
			if (!usuarioLogado.getSenha().equals(senhaCriptografada)) {
				Messages.addGlobalError("Nome de usuário e/ou senha incorretos");
				iniciar();
				return;
			}

			Faces.redirect("./pages/gerarTabela.xhtml");
		} catch (IOException | GeneralSecurityException e) {
			e.printStackTrace();
		}
	}
	
	public void logout() {
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		Faces.navigate("/pages/login.xhtml?faces-redirect=true");
	}

	public boolean temPermissao() {
		if (usuarioLogado.getPapel().equals('A')) {
			return true;
		} else {
			return false;
		}
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Usuario getUsuarioLogado() {
		return usuarioLogado;
	}

	public void setUsuarioLogado(Usuario usuarioLogado) {
		this.usuarioLogado = usuarioLogado;
	}
}
