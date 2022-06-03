package relatorios;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Calendar;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import classes.Canal;
import classes.ProgramaDeTV;
import persistencia.CentralDeInformacoes;
import persistencia.Persistencia;

public class GeradorDeRelatorios {
	private static File arquivo = new File(System.getProperty("user.dir") + "/relatorios.pdf");
	
	private static File programacaoDeHoje = new File(System.getProperty("user.dir") + "/relatorios-hoje.pdf");
	
	
	
	
	public static void gerarProgramacaoDeHoje(CentralDeInformacoes central) throws Exception {
			
		String dados = "Programas exibidos hoje: \n";
		
		Document doc = new Document(PageSize.A4);
		
		Paragraph paragraph = new Paragraph();
		
		LocalDate localDate = LocalDate.now();
		
		int hoje = DayOfWeek.from(localDate).getValue();
		
		
		
		try {

			FileOutputStream out;
			if (programacaoDeHoje.exists()) {
				out = new FileOutputStream(programacaoDeHoje);
			} else {
				out = new FileOutputStream("relatorios-hoje.pdf");

			}

			PdfWriter.getInstance(doc, out);
			doc.open();
			
			for (ProgramaDeTV programas : central.getTodosOsProgramas()) {
				if (programas.getCanal() != null) {
					if(programas.getDiasDaSemana().getValue() == hoje) {
						dados += "\n" + programas.getNome();
					}
				}
			}

		} catch (FileNotFoundException e) {

			e.printStackTrace();
		} catch (DocumentException e) {

			e.printStackTrace();
		}
		Paragraph dadoP = new Paragraph(dados);
		doc.add(paragraph);
		doc.add(dadoP);
		doc.close();
		
	}
	
	

	public static void obterProgramacaoDeUmCanal(Canal canal) throws Exception {

		String dados = "";

		Persistencia persistencia = new Persistencia();

		CentralDeInformacoes central = persistencia.recuperarCentral("database.xml");

		Document doc = new Document(PageSize.A4);

		Paragraph paragraphName = new Paragraph(canal.getNomeDoCanal());
		try {

			FileOutputStream out;
			if (arquivo.exists()) {
				out = new FileOutputStream(arquivo);
			} else {
				out = new FileOutputStream("relatorios.pdf");

			}

			PdfWriter.getInstance(doc, out);
			doc.open();
			for (ProgramaDeTV programas : central.getTodosOsProgramas()) {
				if (programas.getCanal() != null) {
					if (programas.getCanal().equals(canal) && programas != null) {
						dados += ("\n" + programas.getNome() + " - " + programas.getDiasDaSemana());
					}
				}
			}

		} catch (FileNotFoundException e) {

			e.printStackTrace();
		} catch (DocumentException e) {

			e.printStackTrace();
		}
		Paragraph dadoP = new Paragraph(dados);
		doc.add(paragraphName);
		doc.add(dadoP);
		doc.close();

	}


}