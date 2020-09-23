package dao;

import java.util.List;

import domain.Pessoa;

public class PessoaDAO extends GenericDAO<Pessoa> {

	@SuppressWarnings("unchecked")
	public List<Pessoa> buscarPorListaDeCPF(String listaDeCPF) {
		try {
			if (!em.isOpen()) {
				instanciarEm();
			}
			abrirSessao();
			List<Pessoa> pessoas = em.createQuery(
					"FROM " + Pessoa.class.getName() + " WHERE operacao <> 'delete' AND cpf IN(" + listaDeCPF + ")")
					.getResultList();
			return pessoas;
		} catch (RuntimeException ex) {
			throw ex;
		} finally {
			fecharSessao();
		}
	}

}
