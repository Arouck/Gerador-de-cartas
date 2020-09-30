package dao;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import domain.Pessoa;
import util.PessoaUtil;

class PessoaDAOTest {

	@ParameterizedTest
	@ValueSource(strings = { "\n\n326.377.490-59\n573.091.250-10\n711.490.570-09\n690.627.240-35\n435.608.320-20\n158.502.990-41",
			"326.377.490-59\n573.091.250-10\n711.490.570-09\n690.627.240-35\n435.608.320-20\n158.502.990-41\n\n\n",
			"326.377.490-59\n573.091.250-10\n711.490.570-09\n690.627.240-35\n435.608.320-20\n158.502.990-41",
			"\n\n\n\n326.377.490-59\n573.091.250-10\n711.490.570-09\n690.627.240-35\n435.608.320-20\n158.502.990-41\n\n\n\n\n" })
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
	
	
	@ParameterizedTest
	@ValueSource(strings = {
			"326.377.490-59\r\n844.930.270-61\r\n573.091.250-10\r\n711.490.570-09\r\n690.627.240-35\r\n604.421.100-22\r\n435.608.320-20\r\n158.502.990-41\r\n059.424.960-05" })
	void testBuscarPorListaDeCPF2(String listaDeCpf) {
		String listaFormatada = PessoaUtil.formatarListaDeCpf(listaDeCpf);
		PessoaDAO dao = new PessoaDAO();
		List<Pessoa> pessoas = dao.buscarPorListaDeCPF(listaFormatada);
		Integer[] arrayIds = new Integer[pessoas.size()];
		for(int i = 0; i < pessoas.size(); i++) {
			arrayIds[i] = pessoas.get(i).getIdent();
		}
		Integer[] esperado = {1, 3, 4, 5, 6, 8, 9};
		assertArrayEquals(esperado, arrayIds);
	}

}
