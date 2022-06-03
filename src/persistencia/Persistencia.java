package persistencia;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import javax.swing.JOptionPane;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class Persistencia {

	private XStream xStream = new XStream(new DomDriver());
	private File arquivo = new File("database.xml");
	
	
	public void salvarCentral(Object obj, String nomeDoArquivo) throws Exception {
		
		File arquivo = new File(nomeDoArquivo);
		arquivo.createNewFile();
		
		PrintWriter pw = new PrintWriter(arquivo);
		String xml = "<?xml version=\"1.0\" enconding=\"UTF-8\" ?>\n";
		xml = xStream.toXML(obj);
		pw.print(xml);
		pw.close();
		
	}
	
	public CentralDeInformacoes recuperarCentral(String nomeDoArquivo) {
		try {
			if(arquivo.exists()) {
				FileInputStream file = new FileInputStream(arquivo);
				return (CentralDeInformacoes) xStream.fromXML(file);
			}
		} catch(FileNotFoundException erro) {
			JOptionPane.showMessageDialog(null, "Não há banco de dados", "Atenção!", JOptionPane.ERROR_MESSAGE);
		}
		return new CentralDeInformacoes();
	}
}