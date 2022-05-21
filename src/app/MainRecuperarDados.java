package app;

import java.util.Scanner;

import classes.Canal;
import classes.ProgramaDeTV;
import enums.DiasDaSemana;
import enums.TipoDePrograma;
import persistencia.CentralDeInformacoes;
import persistencia.Persistencia;

public class MainRecuperarDados {
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		Persistencia persistencia = new Persistencia();
		CentralDeInformacoes central = new CentralDeInformacoes();
		int qtd = 2;
		for(int i = 0; i < qtd; i++) {
			System.out.println("Digite o nome do programa: ");
			String nome = sc.nextLine();
			System.out.println("Digite o tipo do programa (serie, continuo, reality show):");
			String tipo = sc.nextLine().toUpperCase();
			System.out.println("Digite o dia da semana que é exibido: ");
			String dia = sc.nextLine().toUpperCase();
			System.out.println("Digite o nome do canal: ");
			String nomeDoCanal = sc.nextLine();
			System.out.println("Digite o tipo do canal: ");
			String tipoDoCanal = sc.nextLine();
			Canal canal = new Canal(nomeDoCanal, tipoDoCanal);
			central.adicionarCanal(canal);
			persistencia.salvarCentral(central, "database.xml");
			ProgramaDeTV programa = new ProgramaDeTV(nome, TipoDePrograma.valueOf(tipo), DiasDaSemana.valueOf(dia), canal);
			central.adicionarPrograma(programa);
			persistencia.salvarCentral(central, "database.xml");
		}
		sc.close();
	}
}
