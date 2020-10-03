package dao;

import domain.Usuario;

public class UsuarioDAO extends GenericDAO<Usuario> {

	public void salvar(Usuario usuario) {
		try {
			if (!em.isOpen()) {
				instanciarEm();
			}
			abrirSessao();
			em.persist(usuario);
			fazerCommmit();
		} catch (RuntimeException ex) {
			if (em.getTransaction() != null) {
				fazerRollback();
			}
			throw ex;
		} finally {
			fecharSessao();
		}
	}

	public Usuario buscarPorUsuario(String username) {
		try {
			if (!em.isOpen()) {
				instanciarEm();
			}
			abrirSessao();
			Usuario usuario = (Usuario) em
					.createQuery("FROM " + Usuario.class.getName() + " WHERE username = '" + username + "'")
					.getSingleResult();
			return usuario;
		} catch (RuntimeException ex) {
			if (em.getTransaction() != null) {
				fazerRollback();
			}
			throw ex;
		} finally {
			fecharSessao();
		}
	}

	public Usuario buscarPorUsuarioAutenticar(String username) {
		try {
			if (!em.isOpen()) {
				instanciarEm();
			}
			abrirSessao();
			Usuario usuario = (Usuario) em
					.createQuery("FROM " + Usuario.class.getName() + " WHERE username = '" + username + "'")
					.getSingleResult();
			Usuario newUsuario = new Usuario(usuario.getUsername(), usuario.getSenha(), null, usuario.getSalt());
			return newUsuario;
		} catch (RuntimeException ex) {
			if (em.getTransaction() != null) {
				fazerRollback();
			}
			throw ex;
		} finally {
			fecharSessao();
		}
	}

	public void fazerCommmit() {
		em.getTransaction().commit();
	}

	public void fazerRollback() {
		em.getTransaction().rollback();
	}

}
