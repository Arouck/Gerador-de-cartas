package util;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import dao.PessoaDAO;
import domain.Pessoa;

class XLSXUtilTest {

	@ParameterizedTest
	@ValueSource(strings = { "\n\n003\n005\n006\n007\n009\n010",
			"003\r\n004\r\n005\r\n006\r\n007\r\n008\r\n009\r\n010\r\n011" })
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
			File file2 = new File(
					"C:\\Users\\pvito\\Documents\\Tabela " + date.getYear() + " " + date.getMonth() + "(1).xlsx");

			assertTrue(file1.exists() || file2.exists());
		} catch (IOException e) {
			e.printStackTrace();
			fail("Falha ao tentar gerar o arquivo!");
		}
	}

}
