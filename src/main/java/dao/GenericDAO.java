package dao;

import java.lang.reflect.ParameterizedType;

import javax.persistence.EntityManager;

import util.EntityManagerUtil;

public class GenericDAO<Entidade> {

	protected EntityManager em;
	private Class<Entidade> classe;
	
	@SuppressWarnings("unchecked")
	public GenericDAO() {
		this.classe = (Class<Entidade>) ((ParameterizedType) getClass().getGenericSuperclass())
				.getActualTypeArguments()[0];
		instanciarEm();
	}
	
	public void abrirSessao() {
		em.getTransaction().begin();
	}
	
	public void fazerCommmit() {
		em.getTransaction().commit();
	}
	
	public void fecharSessao() {
		em.close();
	}
	
	public void fazerRollback() {
		em.getTransaction().rollback();
	}
	
	public void instanciarEm() {
		setEm(EntityManagerUtil.getEntityManager());
	}
	
	public EntityManager getEm() {
		return em;
	}
	public void setEm(EntityManager em) {
		this.em = em;
	}
	public Class<Entidade> getClasse() {
		return classe;
	}
	public void setClasse(Class<Entidade> classe) {
		this.classe = classe;
	}
}
