package dao;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import domain.Pessoa;
import util.PessoaUtil;

class PessoaDAOTest {

	@ParameterizedTest
	@ValueSource(strings = { "\n\n003\n005\n006\n007\n009\n010", "003\n005\n006\n007\n009\n010\n\n\n",
			"003\n005\n006\n007\n009\n010", "\n\n\n\n003\n005\n006\n007\n009\n010\n\n\n\n\n" })
	void testBuscarPorListaDeCPF(String listaDeCpf) {
		String listaFormatada = PessoaUtil.formatarListaDeCpf(listaDeCpf);
		PessoaDAO dao = new PessoaDAO();
		List<Pessoa> pessoas = dao.buscarPorListaDeCPF(listaFormatada);
		Integer[] arrayIds = new Integer[pessoas.size()];
		for(int i = 0; i < pessoas.size(); i++) {
			arrayIds[i] = pessoas.get(i).getIdent();
		}
		Integer[] esperado = {1, 3, 4, 5, 8};
		assertArrayEquals(esperado, arrayIds);
	}

}
