package app;

import java.util.Date;

import classes.Usuario;
import persistencia.CentralDeInformacoes;
import persistencia.Persistencia;

public class MainAdicionarUsuario {
	public static void main(String[] args) throws Exception {
		Persistencia persistencia = new Persistencia();
		CentralDeInformacoes central = new CentralDeInformacoes();
		
		Usuario user = new Usuario("Diogo", "10380036444", "83996586204", new Date(), "marcello.razer@gmail.com", "vidaloka00");
		
		central.adicionarUsuario(user);
		persistencia.salvarCentral(central, "database.xml");
		
	}
}
