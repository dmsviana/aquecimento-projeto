package app;

import java.util.Scanner;

import classes.Canal;
import classes.ProgramaDeTV;
import enums.DiasDaSemana;
import enums.TipoDePrograma;
import persistencia.CentralDeInformacoes;
import persistencia.Persistencia;

public class MainApp {

	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		Persistencia persistencia = new Persistencia();
		CentralDeInformacoes central = persistencia.recuperarCentral("database.xml");

		boolean op = true;
		Canal canal = null;
		do {
			System.out.println(central.exibirMenu());
			String opcao = sc.nextLine().toUpperCase();
			switch (opcao) {
			case "4":
				System.out.println("Digite o nome do canal: ");
				String nomeDoCanal = sc.nextLine();
				System.out.println("Digite o tipo de canal (streaming, tv aberta, etc):");
				String tipoDoCanal = sc.nextLine();
				canal = new Canal(nomeDoCanal, tipoDoCanal);
				if(central.adicionarCanal(canal)) {
					System.out.println("Canal adicionado com sucesso!");
					persistencia.salvarCentral(central, "database.xml");
					break;
				}
				
			case "1":
				if(central.getTodosOsCanais().isEmpty()) {
					System.out.println("Não é possível adicionar programas antes de adicionar um canal. Escolha a opcao de adicionar um canal!");
					System.out.println(central.exibirMenu());
					opcao = sc.nextLine();			
				}
				System.out.println("Digite o nome do programa: ");
				String nome = sc.nextLine();
				System.out.println("Digite o tipo do programa (serie, continuo, reality show):");
				String tipo = sc.nextLine().toUpperCase();
				System.out.println("Digite o dia da semana que é exibido: ");
				String dia = sc.nextLine().toUpperCase();
				System.out.println("Esses são os canais disponiveis: " + central.getTodosOsCanais().toString());
				System.out.println("Em qual deseja inserir? ");
				String inserirCanal = sc.nextLine();
				canal = new Canal();
				canal.setNomeDoCanal(inserirCanal);
				ProgramaDeTV programa = new ProgramaDeTV(nome, TipoDePrograma.valueOf(tipo), DiasDaSemana.valueOf(dia), canal);
				System.out.println(programa.getCanal());
				if(central.adicionarPrograma(programa)) {
					System.out.println("Programa adicionado!");
				} else {
					System.out.println("Não foi possível adicionar o programa!");
				}
				persistencia.salvarCentral(central, "database.xml");
				break;
				
			case "2":
				System.out.println(central.getTodosOsProgramas().toString());
				break;
				
			case "3":
				System.out.println("Digite o tipo: ");
				String tipoASerImpresso = sc.nextLine();
				System.out.println(central.listarProgramasDeTipoIguais(tipoASerImpresso));
				break;
			case "5":
				System.out.println(central.getTodosOsCanais().toString());
				break;
			case "S":
				op = false;
				System.out.println("Fim do programa.");
				break;
			}		
		} while(op);
		sc.close();
	}
}
