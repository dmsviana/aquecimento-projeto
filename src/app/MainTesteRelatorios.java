package app;

import classes.Canal;
import persistencia.CentralDeInformacoes;
import persistencia.Persistencia;
import relatorios.GeradorDeRelatorios;

public class MainTesteRelatorios {
	public static void main(String[] args) throws Exception {
		
		Persistencia persistencia = new Persistencia();
		
		CentralDeInformacoes central = persistencia.recuperarCentral("database.xml");
		
		
		for(Canal canais : central.getTodosOsCanais()) {
			GeradorDeRelatorios.obterProgramacaoDeUmCanal(canais);
		}
		
		GeradorDeRelatorios.gerarProgramacaoDeHoje(central);
		
		
		
	}
}
