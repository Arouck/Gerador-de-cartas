package util;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.omnifaces.util.Faces;

import domain.Pessoa;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;

public class PDFUtil {

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void gerarDocumento(List<Pessoa> pessoas) {
		try {
			
			URL jasperPath = PDFUtil.class.getClassLoader().getResource("Modelo_Cartas.jasper");
			File file = new File(jasperPath.getFile());

			JasperReport jasperReport = (JasperReport) JRLoader.loadObjectFromFile(file.getAbsolutePath());

			JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(pessoas);

			Map parameters = new HashMap<>();
			parameters.put("ds", ds);

			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, ds);

			//JasperExportManager.exportReportToPdfFile(jasperPrint);
			byte[] pdf = JasperExportManager.exportReportToPdf(jasperPrint);
			
			LocalDate date = LocalDate.now();
			String defaultFileName = "Cartas-" + date.getMonth().getDisplayName(TextStyle.FULL, new Locale("pt")) + "-"
					+ date.getYear() + ".pdf";
			InputStream inputStream = new ByteArrayInputStream(pdf);
			Faces.sendFile(inputStream, defaultFileName, true);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
