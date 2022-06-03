package relatorios;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.pdf.PdfWriter;

import classes.Canal;
import classes.ProgramaDeTV;
import persistencia.CentralDeInformacoes;
import persistencia.Persistencia;

public class GeradorDeRelatorios {
	
	private static File arquivo = new File(System.getProperty("user.dir") + "/relatorios.pdf");
	
	
	public static void gerarProgramacaoDeHoje(CentralDeInformacoes central) throws Exception {
		Document programacao = new Document(PageSize.A4);
		PdfWriter.getInstance(programacao, new FileOutputStream("relatorios-hoje.pdf"));
		programacao.open();

		Image logoDoProjeto = Image.getInstance("imagens/mySeries.png");
		logoDoProjeto.setAlignment(1);
		programacao.add(logoDoProjeto);

		Font fonteTitulo = new Font(FontFamily.HELVETICA, 16, Font.BOLD);
		Paragraph titulo = new Paragraph("- - - - PROGRAMAÇÃO - - - -", fonteTitulo);
		titulo.setAlignment(1);
		programacao.add(titulo);

		LocalDate dataAtual = LocalDate.now();

		int isHoje = DayOfWeek.from(dataAtual).getValue();

		Paragraph dadosDataAtual = new Paragraph(dataAtual.toString());
		dadosDataAtual.setAlignment(1);
		programacao.add(dadosDataAtual);
		ArrayList<ProgramaDeTV> todosOsProgramas = central.getTodosOsProgramas();
		for (ProgramaDeTV programa : todosOsProgramas) {
			if (programa.getCanal() != null) {
				if (programa.getDiasDaSemana().getValue() == isHoje) {
					Paragraph nomeDoPrograma = new Paragraph(
							"\nPrograma: " + programa.getNome() + "\nCanal: " + programa.getCanal().getNomeDoCanal());
					programacao.add(nomeDoPrograma);		
				}
			}
		}
		
		programacao.close();
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