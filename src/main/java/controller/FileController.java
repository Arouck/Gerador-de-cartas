package controller;

import java.awt.FileDialog;
import java.awt.Frame;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Workbook;

public class FileController {

	public static void saveXLSXFile(Workbook workbook) {
		FileDialog fileDialog = new FileDialog(new Frame(), "Salvar", FileDialog.SAVE);
		fileDialog.setFile("*.xlsx;*xls");
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
			workbook.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static File openXLSXFile() {
		FileDialog fileDialog = new FileDialog(new Frame(), "Abrir", FileDialog.LOAD);
		fileDialog.setFile("*.xlsx;*xls");
		fileDialog.setVisible(true);
		File file = fileDialog.getFiles()[0];
		
		return file;
	}
	
}
