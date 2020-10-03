package util;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.security.GeneralSecurityException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class AutenticacaoUtilTest {

	@ParameterizedTest
	@ValueSource(strings = { "q1w2e3r4", "Q1W2E3R4" })
	void testCriptografarSenha(String senha) {
		try {
			String senhaCriptografada = AutenticacaoUtil.criptografarSenha(senha, "12345", 5000, 512);
			String senhaEsperada1 = "db4d7a155fa1fc0195f79b9a0fa30134a989c496d841f379fbe2ffa3a4050a10ef63f2289186e2c5b01a8f8b656ce5fc9acc8758b41df4cb8e88044088e5c79f";
			String senhaEsperada2 = "5adf739d46ad1af90919bf333380291657c3e7e9776489cee2c889b43e18965adb73399d55ce304ad01bc40b76d0069d47a4d6d5d5a2c1ea73ec3c47ce3ce732";

			assertTrue(senhaEsperada1.equals(senhaCriptografada) || senhaEsperada2.equals(senhaCriptografada));
		} catch (GeneralSecurityException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	void testGerarSaltAleatorio() {
		String salt = AutenticacaoUtil.gerarSaltAleatorio();
		assertTrue(salt.length() == 20);
	}

}
