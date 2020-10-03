package util;

import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

import org.apache.commons.codec.binary.Hex;

public class AutenticacaoUtil {

	public static String criptografarSenha(String password, String salt)
			throws GeneralSecurityException {
		try {
			int iterations = 5000;
			int keyLength = 512;
			char[] bytePassword = password.toCharArray();
			String pepper = "?UJ?G\\A:EXA2DW+}ABD[";
			salt += pepper;
			byte[] byteSalt = salt.getBytes();
			SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA512");
			PBEKeySpec pbeKeySpec = new PBEKeySpec(bytePassword, byteSalt, iterations, keyLength);
			SecretKey key = secretKeyFactory.generateSecret(pbeKeySpec);
			byte[] bytesCriptografados = key.getEncoded();
			String senhaCriptografada = Hex.encodeHexString(bytesCriptografados);
			return senhaCriptografada;
		} catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
			e.printStackTrace();
			throw e;
		}
	}

	public static String gerarSaltAleatorio() {

		String simbolosAceitos = "ABCDEFGJKLMNPRSTUVWXYZ0123456789!@#$%&*(){}^~;:.>,</[]´`\\'\"";
		SecureRandom secureRandom = new SecureRandom();
		char[] salt = new char[20];

		for (int i = 0; i < salt.length; i++) {
			salt[i] = simbolosAceitos.charAt(secureRandom.nextInt(simbolosAceitos.length()));
		}

		return new String(salt);
	}
}
