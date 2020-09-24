package util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import domain.Pessoa;

public class PessoaUtil {

	public static List<Pessoa> preencherUF(List<Pessoa> pessoas) {
		pessoas = StringUtils.formatarCep(pessoas);
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
		
		List<Map<String, String>> enderecosFormatados = StringUtils.formatarEndereco(enderecosJson);
		
		for (int i = 0; i < pessoas.size(); i++) {
			if (!enderecosFormatados.get(i).isEmpty()) {
				pessoas.get(i).setUf(enderecosFormatados.get(i).get("uf"));
			}
		}
		
		return pessoas;
	}
}
