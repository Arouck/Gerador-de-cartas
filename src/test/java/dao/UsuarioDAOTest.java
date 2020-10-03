package dao;

import static org.junit.Assert.assertTrue;

import java.security.GeneralSecurityException;
import java.util.Scanner;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import dao.UsuarioDAO;
import domain.Usuario;
import util.AutenticacaoUtil;

class UsuarioDAOTest {

	@Test
	void testSalvar() {
		try {
			int i = 1;
			UsuarioDAO dao = new UsuarioDAO();
			Usuario usuario;
			Scanner scanner = new Scanner(System.in);
			String username = "";
			switch (i) {
			case 1:
				System.out.println("Insira o nome de usuario: ");
				username = scanner.next();
				usuario = new Usuario(username, "q1w2e3r4", 'C', null);
				usuario.setSalt(AutenticacaoUtil.gerarSaltAleatorio());
				usuario.setSenha(AutenticacaoUtil.criptografarSenha(usuario.getSenha(), usuario.getSalt(), 5000, 512));
				dao.salvar(usuario);
			case 2:
				System.out.println("Insira o nome de usuario: ");
				username = scanner.next();
				usuario = new Usuario("def", "123456789", 'C', null);
				usuario.setSalt(AutenticacaoUtil.gerarSaltAleatorio());
				usuario.setSenha(AutenticacaoUtil.criptografarSenha(usuario.getSenha(), usuario.getSalt(), 5000, 512));
				dao.salvar(usuario);
			case 3:
				System.out.println("Insira o nome de usuario: ");
				username = scanner.next();
				usuario = new Usuario("ghi", "nucabnruh", 'C', null);
				usuario.setSalt(AutenticacaoUtil.gerarSaltAleatorio());
				usuario.setSenha(AutenticacaoUtil.criptografarSenha(usuario.getSenha(), usuario.getSalt(), 5000, 512));
				dao.salvar(usuario);
			}
			scanner.close();
		} catch (GeneralSecurityException e) { // TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@ParameterizedTest
	@ValueSource(strings = { "matheusmelo", "progep-ti", "def" })
	void testBuscarPorUsuario(String username) {
		UsuarioDAO dao = new UsuarioDAO();
		Usuario usuario = dao.buscarPorUsuario(username);
		assertTrue(usuario.getId() == 17 || usuario.getId() == 18 || usuario.getId() == 27);
	}

	@Test
	void testAutenticar() {
		int i = 1;
		UsuarioDAO dao = new UsuarioDAO();
		Usuario usuario = null;
		String senha = "";
		try {
			switch (i) {
			case 1:
				usuario = dao.buscarPorUsuarioAutenticar("progep-ti");
				senha = "nucabnruh";

				assertTrue(usuario.getSenha()
						.equals(AutenticacaoUtil.criptografarSenha(senha, usuario.getSalt(), 5000, 512)));

			case 2:
				usuario = dao.buscarPorUsuarioAutenticar("p-arouck");
				senha = "q1w2e3r4";

				assertTrue(usuario.getSenha()
						.equals(AutenticacaoUtil.criptografarSenha(senha, usuario.getSalt(), 5000, 512)));

			case 3:
				usuario = dao.buscarPorUsuarioAutenticar("usuario2");
				senha = "123456789";

				assertTrue(usuario.getSenha()
						.equals(AutenticacaoUtil.criptografarSenha(senha, usuario.getSalt(), 5000, 512)));
			}
		} catch (GeneralSecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
