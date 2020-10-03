package bean;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import org.primefaces.model.file.UploadedFile;

import domain.Pessoa;
import util.PDFUtil;
import util.XLSXUtil;

@SuppressWarnings("serial")
@ManagedBean
@RequestScoped
public class GerarCartasBean implements Serializable {
	private UploadedFile file;
	
	public void gerarCartas() throws IOException {
		List<Pessoa> pessoas = XLSXUtil.lerArquivoXLSX(file);
		PDFUtil.gerarDocumento(pessoas);
	}

	public UploadedFile getFile() {
		return file;
	}

	public void setFile(UploadedFile file) {
		this.file = file;
	}
}
