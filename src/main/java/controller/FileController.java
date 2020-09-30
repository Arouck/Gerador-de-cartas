package controller;

import java.awt.FileDialog;
import java.awt.Frame;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;

import org.apache.poi.ss.usermodel.Workbook;
import org.omnifaces.util.Faces;

public class FileController {
	
	public static void saveXLSXFileDialog(Workbook workbook) throws IOException {
		FileDialog fileDialog = new FileDialog(new Frame(), "Salvar", FileDialog.SAVE);
		fileDialog.setFile("*.xlsx;*.xls");
		fileDialog.setVisible(true);
		
		File file = null;
		String path = "";
		
		if (!fileDialog.getFiles()[0].getAbsolutePath().endsWith(".xlsx")) {		
			path = fileDialog.getFiles()[0].getAbsolutePath() + ".xlsx";
		} else {
			path = fileDialog.getFiles()[0].getAbsolutePath();
		}
		
		file = new File(path);
		
		try {
			FileOutputStream fileOutput = new FileOutputStream(file.getAbsolutePath());
			workbook.write(fileOutput);
			fileOutput.close();
			workbook.write(fileOutput);
			workbook.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void saveXLSXFile(Workbook workbook) throws IOException {
		LocalDate date = LocalDate.now();
		//File file = null;
		String defaultFileName = "Tabela " + date.getMonth().getDisplayName(TextStyle.FULL, new Locale("pt")) + " " + date.getYear() + ".xlsx";
		//File file = new File("C:\\Users\\pvito\\Documents\\" + defaultFileName);
		//String path = "%USERPROFILE%\\Documents\\Cartas\\" + date.getMonth().getDisplayName(TextStyle.FULL, new Locale("pt")) + "\\Tabela.xlsx";
		
		/**/

		//
		
		/*FacesContext facesContext = FacesContext.getCurrentInstance();
		ExternalContext externalContext = facesContext.getExternalContext();
		
		externalContext.responseReset();
		externalContext.setResponseContentType("application/vnd.ms-excel");
	    externalContext.setResponseHeader("Content-Disposition", "attachment; filename=\"" + defaultFileName + "\"");

		*/
		
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
        workbook.write(bos);
        byte[] barray = bos.toByteArray();
        InputStream is = new ByteArrayInputStream(barray);
		
		Faces.sendFile(is, defaultFileName, true);
	}
	
	public static String savePDFFile() {
		FileDialog fileDialog = new FileDialog(new Frame(), "Salvar", FileDialog.SAVE);
		fileDialog.setFile("*.pdf");
		fileDialog.setVisible(true);
		String path = "";
		
		if (!fileDialog.getFiles()[0].getAbsolutePath().endsWith(".pdf")) {
			path = fileDialog.getFiles()[0].getAbsolutePath() + ".pdf";
		} else {
			path = fileDialog.getFiles()[0].getAbsolutePath();
		}

		return path;
	}
	
	public static File openXLSXFile() {
		FileDialog fileDialog = new FileDialog(new Frame(), "Abrir", FileDialog.LOAD);
		fileDialog.setFile("*.xlsx;*xls");
		fileDialog.setVisible(true);
		File file = fileDialog.getFiles()[0];
		
		return file;
	}
	
}
