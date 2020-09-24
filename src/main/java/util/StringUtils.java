package util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import domain.Pessoa;

public class StringUtils {

	public static String formatarListaDeCpf(String cpfs) {
		String listaDeCpf = cpfs.trim();
		return "'" + listaDeCpf.replaceAll("\\R", "','") + "'";
	}

	public static List<Map<String, String>> formatarEndereco(List<String> enderecosJson) {
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
