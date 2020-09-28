package util;

import java.io.File;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import domain.Pessoa;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;

public class PDFUtil {

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void gerarDocumento(List<Pessoa> pessoas, String path) {
		try {
			
			URL jasperPath = PDFUtil.class.getClassLoader().getResource("Modelo_Cartas.jasper");
			File file = new File(jasperPath.toURI());

			JasperReport jasperReport = (JasperReport) JRLoader.loadObjectFromFile(file.getAbsolutePath());

			JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(pessoas);

			Map parameters = new HashMap<>();
			parameters.put("ds", ds);

			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, ds);

			JasperExportManager.exportReportToPdfFile(jasperPrint, path);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
