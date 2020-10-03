package bean;

import java.io.IOException;
import java.io.Serializable;
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
			pessoas = PessoaUtil.formatarEnderecoCompleto(pessoas);
			XLSXUtil.gerarTabelaExcel(pessoas);
			Messages.addGlobalInfo("Tabela gerada com suceso.");
		} catch (IOException | RuntimeException e) {
			Messages.addGlobalError("Ocorreu um erro ao tentar gerar a tabela.");
			e.printStackTrace();
		}
	}

	public String getListaDeCpf() {
		return listaDeCpf;
	}

	public void setListaDeCpf(String listaDeCpf) {
		this.listaDeCpf = listaDeCpf;
	}
}
