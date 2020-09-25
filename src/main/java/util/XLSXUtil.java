package util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import domain.Pessoa;

public class XLSXUtil {
	
	private static final String[] columns = { "Nome", "CEP", "Logradouro", "Complemento", "Numero", "Bairro", "Cidade",
			"Estado", "Menssagem de Erro" };
	private static final LocalDate date = LocalDate.now();
	
	public static void gerarTabelaExcel(List<Pessoa> pessoas) throws IOException {
		Workbook workbook = new XSSFWorkbook();
		
		Sheet sheet = workbook.createSheet("Lista de pessoas");
		Font headerFont = workbook.createFont();
		
		headerFont.setBold(true);
		headerFont.setFontHeightInPoints((short) 15);
		headerFont.setColor(IndexedColors.GREY_25_PERCENT.getIndex());
		
		CellStyle headerCellStyle = workbook.createCellStyle();
		headerCellStyle.setFont(headerFont);
		
		Row headerRow = sheet.createRow(0);
		
		for (int i = 0; i < columns.length; i++) {
			Cell cell = headerRow.createCell(i);
			cell.setCellValue(columns[i]);
			cell.setCellStyle(headerCellStyle);
		}
		
		int rowNumber = 1;
		
		 for (Pessoa pessoa : pessoas) {
		 	Row row = sheet.createRow(rowNumber++);
		 	
		 	if (pessoa.getComplemento() == null || pessoa.getComplemento().isEmpty()) {
		 		pessoa.setComplemento(" ");
		 	}
		 	
		 	if (pessoa.getNumeroendereco() == null || pessoa.getNumeroendereco().isEmpty()) {
		 		pessoa.setNumeroendereco(" ");
		 	}
			
		 	row.createCell(0).setCellValue(pessoa.getNome());
		 	row.createCell(1).setCellValue(pessoa.getCep());
		 	row.createCell(2).setCellValue(pessoa.getEndereco());
		 	row.createCell(3).setCellValue(pessoa.getComplemento());
		 	row.createCell(4).setCellValue(pessoa.getNumeroendereco());
		 	row.createCell(5).setCellValue(pessoa.getBairro());
		 	row.createCell(6).setCellValue(pessoa.getCidade());
		 	row.createCell(7).setCellValue(pessoa.getUf());
		 	row.createCell(8).setCellValue(pessoa.getMensagemErro());
		}
		
		for (int j = 0; j < columns.length; j++) {
			sheet.autoSizeColumn(j);
		}
		
		File file = new File("C:\\Users\\pvito\\Documents\\Tabela " + date.getYear() + " " + date.getMonth() + ".xlsx");

		if (file.exists()) {
			FileOutputStream fileOutput = new FileOutputStream(
					"C:\\Users\\pvito\\Documents\\Tabela " + date.getYear() + " " + date.getMonth() + "(1).xlsx");
			workbook.write(fileOutput);
			fileOutput.close();
		} else {
			FileOutputStream fileOutput = new FileOutputStream(file);
			workbook.write(fileOutput);
			fileOutput.close();
		}
		
		workbook.close();
		
	}

}
