package util;

public class CpfUtil {
	
	public static String ajustarListaDeCpf(String cpfs) {
		String listaDeCpf = cpfs.trim();
		return "'" + listaDeCpf.replaceAll("\\R", "','") + "'";
	}

}
