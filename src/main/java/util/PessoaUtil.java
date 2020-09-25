package util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import domain.Pessoa;

public class PessoaUtil {

	public static List<Pessoa> preencherUF(List<Pessoa> pessoas) {
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

		for (int i = 0; i < pessoas.size(); i++) {
			if (enderecosFormatados.get(i) != null && !enderecosFormatados.get(i).isEmpty()) {
				pessoas.get(i).setUf(enderecosFormatados.get(i).get("uf"));
			}
		}

		return pessoas;
	}

	public static List<Pessoa> formatarBairro(List<Pessoa> pessoas, List<Map<String, String>> enderecosViaCep) {
		for (int i = 0; i < pessoas.size(); i++) {
			if (pessoas.get(i).getBairro().equalsIgnoreCase("AEROPORTO") || pessoas.get(i).getBairro().equalsIgnoreCase("CENTRO")
					|| pessoas.get(i).getBairro().equalsIgnoreCase("OUTROS") || pessoas.get(i).getBairro() == null
					|| pessoas.get(i).getBairro().isEmpty()) {
				pessoas.get(i).setBairro(enderecosViaCep.get(i).get("bairro"));
			}	
		}
		
		return pessoas;
	}

	public static String formatarListaDeCpf(String cpfs) {
		String listaDeCpf = cpfs.trim();
		return "'" + listaDeCpf.replaceAll("\\R", "','") + "'";
	}

	public static List<Map<String, String>> formatarJsonViaCep(List<String> enderecosJson) {
		List<Map<String, String>> enderecosFormatados = new ArrayList<>();
		
		for(String jsonEndereco : enderecosJson) {
			if(jsonEndereco == null) {
				enderecosFormatados.add(null);
			} else {
				Map<String, String> endereco = new HashMap<>();
				
				Matcher matcher = Pattern.compile("\"\\D.*?\": \".*?\"").matcher(jsonEndereco);
				
				while (matcher.find()) {
					String[] group = matcher.group().split(":");
					endereco.put(group[0].replaceAll("\"", "").trim(), group[1].replaceAll("\"", "").trim());
				}
				
				enderecosFormatados.add(endereco);			
			}
		}
		
		return enderecosFormatados;
	}

	public static List<Pessoa> formatarCep(List<Pessoa> pessoas) {
		for (Pessoa pessoa : pessoas) {
			pessoa.setCep(pessoa.getCep().replaceAll("\\R", "").trim());
			if (pessoa.getCep().replace("-", "").length() >= 7 && pessoa.getCep().replace("-", "").length() < 10) {
				String cep = pessoa.getCep().replace("-", "");
				if (cep.length() == 7) {
					cep = "0" + cep;
				}
				pessoa.setCep(cep.substring(0, 5) + "-" + cep.substring(5));
			} else {
				pessoa.setMensagemErro("Cep inválido!");
			}
		}
		return pessoas;
	}
}
