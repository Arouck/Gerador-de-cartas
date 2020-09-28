package util;

import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.Test;

import controller.FileController;
import domain.Pessoa;

class Z_PDFUtilTest {

	@Test
	void testGerarDocumento() {
		try {
			List<Pessoa> pessoas = XLSXUtil.lerArquivoXLSX();
			
			String path = FileController.savePDFFile();
			
			PDFUtil.gerarDocumento(pessoas, path);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
