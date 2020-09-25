package dao;

import javax.persistence.EntityManager;

import util.EntityManagerUtil;

public class GenericDAO<Entidade> {

	protected EntityManager em;
	
	public GenericDAO() {
		instanciarEm();
	}
	
	public void abrirSessao() {
		em.getTransaction().begin();
	}
	
	public void fecharSessao() {
		em.close();
	}
	
	public void instanciarEm() {
		setEm(EntityManagerUtil.getEntityManager());
	}
	
	public void setEm(EntityManager em) {
		this.em = em;
	}
}
