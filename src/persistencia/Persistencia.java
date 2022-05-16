package persistencia;

import java.io.File;
import java.io.FileInputStream;
import java.io.PrintWriter;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class Persistencia {

	private XStream xStream = new XStream(new DomDriver());
	
	
	
	public void salvarCentral(Object obj, String nomeDoArquivo) throws Exception {
		
		File arquivo = new File(nomeDoArquivo);
		arquivo.createNewFile();
		
		PrintWriter pw = new PrintWriter(arquivo);
		String xml = "<?xml version=\"1.0\" enconding=\"UTF-8\" ?>\n";
		xml = xStream.toXML(obj);
		pw.print(xml);
		pw.close();
		
	}
	
	public CentralDeInformacoes recuperarCentral(String nomeDoArquivo) throws Exception{
		File arquivo = new File(nomeDoArquivo);
		
		if(arquivo.exists()) {
			FileInputStream fis = new FileInputStream(arquivo);
			return (CentralDeInformacoes) xStream.fromXML(fis);
		} else {
			throw new Exception("Nao ha banco de dados.");
		}
		
	}
}