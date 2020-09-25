package util;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import dao.PessoaDAO;
import domain.Pessoa;

class PessoaUtilTest {

	@ParameterizedTest
	@ValueSource(strings = { "\n\n003\n005\n006\n007\n009\n010", "003\n005\n006\n007\n009\n010\n\n\n",
			"003\n005\n006\n007\n009\n010", "\n\n\n\n003\n005\n006\n007\n009\n010\n\n\n\n\n" })
	public void testBuscarCep(String listaDeCpf) {
		String listaFormatada = StringUtils.formatarListaDeCpf(listaDeCpf);
		PessoaDAO dao = new PessoaDAO();
		List<Pessoa> pessoas = dao.buscarPorListaDeCPF(listaFormatada);
		pessoas = PessoaUtil.preencherUF(pessoas);
		String[] esperado = { "PA", "AC", "SP", "SP", null };
		String[] recebido = new String[pessoas.size()];
		for (int i = 0; i < pessoas.size(); i++) {
			recebido[i] = pessoas.get(i).getUf();
		}
		assertArrayEquals(esperado, recebido);
	}

}
