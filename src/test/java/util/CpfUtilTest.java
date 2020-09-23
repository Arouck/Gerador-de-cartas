package util;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class CpfUtilTest {

	@ParameterizedTest
	@ValueSource(strings = { "\n\n\n\n003\n005\n006\n007\n\n\n\n", "003\n005\n006\n007\n\n\n\n",
			"\n\n\n\n003\n005\n006\n007", "003\n005\n006\n007"})
	public void testAjustarListaDeCpf(String listaDeCpf) {
		String novaLista = CpfUtil.ajustarListaDeCpf(listaDeCpf);
		assertTrue("'003','005','006','007'".equals(novaLista));
	}

}
