package util;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import dao.PessoaDAO;
import domain.Pessoa;

class StringUtilsTest {

	@ParameterizedTest
	@ValueSource(strings = { "\n\n\n\n003\n005\n006\n007\n\n\n\n", "003\n005\n006\n007\n\n\n\n",
			"\n\n\n\n003\n005\n006\n007", "003\n005\n006\n007" })
	public void testFormatarListaDeCpf(String listaDeCpf) {
		String novaLista = StringUtils.formatarListaDeCpf(listaDeCpf);
		assertTrue("'003','005','006','007'".equals(novaLista));
	}

	@ParameterizedTest
	@ValueSource(strings = { " 66085311 ", "\n\n66085311", "66085311", "\n333-333  ", "   1234567890  " })
	public void testFormatarCep(String cep) {
		Pessoa pessoa = new Pessoa();
		pessoa.setCep(cep);
		List<Pessoa> pessoas = new ArrayList<>();
		pessoas.add(pessoa);
		pessoas = StringUtils.formatarCep(pessoas);
		if (pessoas.get(0).getMensagemErro() != null) {
			assertTrue(cep.replaceAll("\\R", "").trim().equals(pessoas.get(0).getCep()));
		} else {
			assertTrue("66085-311".equals(pessoas.get(0).getCep()));
		}
	}
	
	@ParameterizedTest
	@ValueSource(strings = { "\n\n\n\n003\n005\n006\n007\n\n\n\n", "003\n005\n006\n007\n\n\n\n",
			"\n\n\n\n003\n005\n006\n007", "003\n005\n006\n007" })
	public void testFormatarCepECpf(String listaDeCpf) {
		PessoaDAO dao = new PessoaDAO();
		List<Pessoa> pessoas = dao.buscarPorListaDeCPF(StringUtils.formatarListaDeCpf(listaDeCpf));
		pessoas = StringUtils.formatarCep(pessoas);
		String[] esperado = { "66085-311", "69900-776", "04321-002", "02467-090" };
		String[] arrayCeps = new String[pessoas.size()];
		for(int i = 0; i < pessoas.size(); i++) {
			arrayCeps[i] = pessoas.get(i).getCep();
		}
		assertArrayEquals(esperado, arrayCeps);
	}

}
