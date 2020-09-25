package util;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import dao.PessoaDAO;
import domain.Pessoa;

class PessoaUtilTest {

	@ParameterizedTest
	@ValueSource(strings = { "\n\n003\n005\n006\n007\n009\n010", "003\n005\n006\n007\n009\n010\n\n\n",
			"003\n005\n006\n007\n009\n010", "\n\n\n\n003\n005\n006\n007\n009\n010\n\n\n\n\n" })
	public void testPreencherUF(String listaDeCpf) {
		String listaFormatada = PessoaUtil.formatarListaDeCpf(listaDeCpf);
		PessoaDAO dao = new PessoaDAO();
		List<Pessoa> pessoas = dao.buscarPorListaDeCPF(listaFormatada);

		pessoas = PessoaUtil.formatarCep(pessoas);
		List<Boolean> validador = new ArrayList<>();
		List<String> ceps = new ArrayList<>();

		for (Pessoa pessoa : pessoas) {
			if (pessoa.getMensagemErro() != null) {
				validador.add(false);
			} else {
				validador.add(true);
			}
			ceps.add(pessoa.getCep());
		}

		List<String> enderecosJson = ClienteViaCepWS.buscarCep(ceps, validador);
		List<Map<String, String>> enderecosFormatados = PessoaUtil.formatarJsonViaCep(enderecosJson);

		pessoas = PessoaUtil.preencherUF(pessoas, enderecosFormatados);
		String[] esperado = { "PA", "AC", "SP", "SP", null };
		String[] recebido = new String[pessoas.size()];
		for (int i = 0; i < pessoas.size(); i++) {
			recebido[i] = pessoas.get(i).getUf();
		}
		assertArrayEquals(esperado, recebido);
	}
	
	@ParameterizedTest
	@ValueSource(strings = { "\n\n\n\n003\n005\n006\n007\n\n\n\n", "003\n005\n006\n007\n\n\n\n",
			"\n\n\n\n003\n005\n006\n007", "003\n005\n006\n007" })
	public void testFormatarListaDeCpf(String listaDeCpf) {
		String novaLista = PessoaUtil.formatarListaDeCpf(listaDeCpf);
		assertTrue("'003','005','006','007'".equals(novaLista));
	}
	
	@ParameterizedTest
	@ValueSource(strings = { "003\r\n004\r\n005\r\n006\r\n007\r\n008\r\n009\r\n010\r\n011" })
	public void testFormatarListaDeCpf2(String listaDeCpf) {
		String novaLista = PessoaUtil.formatarListaDeCpf(listaDeCpf);
		assertTrue("'003','004','005','006','007','008','009','010','011'".equals(novaLista));
	}

	@ParameterizedTest
	@ValueSource(strings = { " 66085311 ", "\n\n66085311", "66085311", "\n333-333  ", "   1234567890  " })
	public void testFormatarCep(String cep) {
		Pessoa pessoa = new Pessoa();
		pessoa.setCep(cep);
		List<Pessoa> pessoas = new ArrayList<>();
		pessoas.add(pessoa);
		pessoas = PessoaUtil.formatarCep(pessoas);
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
		List<Pessoa> pessoas = dao.buscarPorListaDeCPF(PessoaUtil.formatarListaDeCpf(listaDeCpf));
		pessoas = PessoaUtil.formatarCep(pessoas);
		String[] esperado = { "66085-311", "69900-776", "04321-002", "02467-090" };
		String[] arrayCeps = new String[pessoas.size()];
		for (int i = 0; i < pessoas.size(); i++) {
			arrayCeps[i] = pessoas.get(i).getCep();
		}
		assertArrayEquals(esperado, arrayCeps);
	}

	@ParameterizedTest
	@ValueSource(strings = { "003\r\n004\r\n005\r\n006\r\n007\r\n008\r\n009\r\n010\r\n011" })
	void testPreencherEnderecoCompleto(String cpfs) {
		String listaFormatada = PessoaUtil.formatarListaDeCpf(cpfs);
		PessoaDAO dao = new PessoaDAO();
		List<Pessoa> pessoas = dao.buscarPorListaDeCPF(listaFormatada);
		pessoas = PessoaUtil.formatarEnderecoCompleto(pessoas);

		String[] esperadoBairro = { "Pedreira", "José Augusto", "Jardim Oriental", "Imirim", "Vila Maria Baixa", "a",
				"Jardim Marco Zero" };
		String[] esperadoUF = { "PA", "AC", "SP", "SP", "SP", null, "AP" };
		
		String[] recebidoBairro = new String[pessoas.size()];
		String[] recebidoUF = new String[pessoas.size()];
		
		for (int i = 0; i < pessoas.size(); i++) {
			recebidoBairro[i] = pessoas.get(i).getBairro();
			recebidoUF[i] = pessoas.get(i).getUf();
		}
		
		assertArrayEquals(esperadoBairro, recebidoBairro);
		assertArrayEquals(esperadoUF, recebidoUF);
	}

}
