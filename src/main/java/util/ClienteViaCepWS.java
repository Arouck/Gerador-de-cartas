package util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class ClienteViaCepWS {
	
	public static List<String> buscarCep(List<String> ceps, List<Boolean> validador) {
		try {
			List<String> enderecos = new ArrayList<>();
			
			for (int i = 0; i < ceps.size(); i++) {
				if(validador.get(i)) {
					URL url = new URL("http://viacep.com.br/ws/" + ceps.get(i) + "/json");
					URLConnection urlConnection = url.openConnection();
					InputStream is = urlConnection.getInputStream();
					BufferedReader br = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
					
					StringBuilder jsonSb = new StringBuilder();
					
					br.lines().forEach(l -> jsonSb.append(l.trim()));
					
					String json = jsonSb.toString();
					
					enderecos.add(json);
				} else {
					enderecos.add(null);
				}
			}
			
			return enderecos;
		} catch (RuntimeException | IOException e) {
			e.printStackTrace();
			return null;
		}
	}
}
