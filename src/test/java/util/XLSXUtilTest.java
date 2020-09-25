package util;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import dao.PessoaDAO;
import domain.Pessoa;

class XLSXUtilTest {

	@ParameterizedTest
	@ValueSource(strings = { "003\r\n004\r\n005\r\n006\r\n007\r\n008\r\n009\r\n010\r\n011" })
	void testGerarTabelaExcel(String cpfs) {
		try {
			LocalDate date = LocalDate.now();

			String listaFormatada = PessoaUtil.formatarListaDeCpf(cpfs);
			PessoaDAO dao = new PessoaDAO();
			List<Pessoa> pessoas = dao.buscarPorListaDeCPF(listaFormatada);
			pessoas = PessoaUtil.formatarEnderecoCompleto(pessoas);
			XLSXUtil.gerarTabelaExcel(pessoas);
			File file1 = new File(
					"C:\\Users\\pvito\\Documents\\Tabela " + date.getYear() + " " + date.getMonth() + ".xlsx");

			assertTrue(file1.exists());
		} catch (IOException e) {
			e.printStackTrace();
			fail("Falha ao tentar gerar o arquivo!");
		}
	}
	
	@Test
	@AfterAll
	static void testLerArquivoXLSX() {
		String[] esperado = { "pedro", "ana", "glauci", "paulo", "igor", "gabriel", "joao" };
		
		try {
			List<Pessoa> pessoas = XLSXUtil.lerArquivoXLSX();
			String[] recebido = new String[pessoas.size()];
			
			for (int i = 0; i < pessoas.size(); i++) {
				recebido[i] = pessoas.get(i).getNome();
			}
			
			assertArrayEquals(esperado, recebido);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
