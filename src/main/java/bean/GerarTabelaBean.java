package bean;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import org.omnifaces.util.Messages;

import dao.PessoaDAO;
import domain.Pessoa;
import util.PessoaUtil;
import util.XLSXUtil;

@SuppressWarnings("serial")
@ManagedBean
@RequestScoped
public class GerarTabelaBean implements Serializable {

	private String listaDeCpf;

	public void gerarTabela() {
		try {
			String listaFormatada = PessoaUtil.formatarListaDeCpf(listaDeCpf);
			PessoaDAO dao = new PessoaDAO();
			List<Pessoa> pessoas = dao.buscarPorListaDeCPF(listaFormatada);

			pessoas = listarCpfsNaoEncontrados(listaFormatada, pessoas);
			pessoas = removerPessoasComOperacaoDelete(pessoas);
			pessoas = PessoaUtil.formatarEnderecoCompleto(pessoas);
			XLSXUtil.gerarTabelaExcel(pessoas);
			Messages.addGlobalInfo("Tabela gerada com suceso.");
		} catch (IOException | RuntimeException e) {
			Messages.addGlobalError("Ocorreu um erro ao tentar gerar a tabela.");
			e.printStackTrace();
		}
	}

	public List<Pessoa> listarCpfsNaoEncontrados(String listaDeCpfs, List<Pessoa> pessoas) {
		List<String> listaDeCpfsOriginal = PessoaUtil.toList(listaDeCpfs);
		List<String> listaDeCpfsEncontrados = new ArrayList<>();
		for (Pessoa pessoa : pessoas) {
			listaDeCpfsEncontrados.add(pessoa.getCpf());
		}
		for (String cpf : listaDeCpfsOriginal) {
			if (!listaDeCpfsEncontrados.contains(cpf)) {
				pessoas.add(new Pessoa("ERRO CPF", "ERRO CPF", "ERRO CPF", "ERRO CPF", "ERRO CPF", "ERRO CPF",
						"ERRO CPF", "ERRO CPF", "ERRO CPF", "CPF não encontrado " + cpf));
			}
		}

		return pessoas;
	}
	
	public List<Pessoa> removerPessoasComOperacaoDelete(List<Pessoa> pessoas) {
		List<Pessoa> novaLista = new ArrayList<>();
		for(Pessoa pessoa : pessoas) {
			if(!pessoa.getOperacao().equals("delete")) {
				novaLista.add(pessoa);
			}
		}
		
		return novaLista;
	}

	public String gerarPlaceholder() {
		return "xxx.xxx.xxx-xx\nxxx.xxx.xxx-xx";
	}

	public String getListaDeCpf() {
		return listaDeCpf;
	}

	public void setListaDeCpf(String listaDeCpf) {
		this.listaDeCpf = listaDeCpf;
	}
}
