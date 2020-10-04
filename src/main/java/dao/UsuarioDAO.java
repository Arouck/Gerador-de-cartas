package dao;

import java.util.ArrayList;
import java.util.List;

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

	@SuppressWarnings("unchecked")
	public List<String> buscarNomesDeUsuario() {
		try {
			if (!em.isOpen()) {
				instanciarEm();
			}
			abrirSessao();
			List<Usuario> listaDeUsuarios = em.createQuery("FROM " + Usuario.class.getName()).getResultList();
			List<String> listaDeNomesDeUsuarios = new ArrayList<>();
			for(Usuario usuario : listaDeUsuarios) {
				listaDeNomesDeUsuarios.add(usuario.getUsername());
			}
			return listaDeNomesDeUsuarios;
		} catch (RuntimeException ex) {
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
			ex.printStackTrace();
			return null;
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
