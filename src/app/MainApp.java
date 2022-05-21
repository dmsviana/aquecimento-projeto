package app;

import java.util.Scanner;

import classes.Canal;
import classes.ProgramaDeTV;
import email.Mensageiro;
import enums.DiasDaSemana;
import enums.TipoDePrograma;
import persistencia.CentralDeInformacoes;
import persistencia.Persistencia;
import relatorios.GeradorDeRelatorios;

public class MainApp {

	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		Persistencia persistencia = new Persistencia();
		CentralDeInformacoes central = persistencia.recuperarCentral("database.xml");
		
		String exibirMenu = "-----MENU-----\nEscolha uma opção: " + "\n1 - Adicionar novo programa"
				+ "\n2 - Listar todos os programas" + "\n3 - Listar todos os programas de um mesmo tipo" 
				+ "\n4 - Adicionar novo canal"
				+ "\n5 - Listar todos os canais"
				+ "\n6 - Gerar Programaçao de um canal"
				+ "\n7 - Enviar programação de hoje por email"
				+ "\nS - Sair"; 
		
		
		boolean op = true;
		Canal canal = null;
		do {
			System.out.println(exibirMenu);
			String opcao = sc.nextLine().toUpperCase();
			switch (opcao) {
			case "1":
				if (central.getTodosOsCanais().isEmpty()) {
					System.out.println(
							"Não é possível adicionar programas antes de adicionar um canal. Escolha a opcao de adicionar um canal!");
					System.out.println(exibirMenu);
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
				ProgramaDeTV programa = new ProgramaDeTV(nome, TipoDePrograma.valueOf(tipo), DiasDaSemana.valueOf(dia),
						canal);
				System.out.println(programa.getCanal());
				if (central.adicionarPrograma(programa)) {
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

			case "4":
				System.out.println("Digite o nome do canal: ");
				String nomeDoCanal = sc.nextLine();
				System.out.println("Digite o tipo de canal (streaming, tv aberta, etc):");
				String tipoDoCanal = sc.nextLine();
				canal = new Canal(nomeDoCanal, tipoDoCanal);
				if (central.adicionarCanal(canal)) {
					System.out.println("Canal adicionado com sucesso!");
					persistencia.salvarCentral(central, "database.xml");
					break;
				}
			case "5":
				System.out.println(central.getTodosOsCanais().toString());
				break;

			case "6":
				System.out.println("Qual canal deseja obter a programaçao? \nCanais disponiveis: "
						+ central.getTodosOsCanais().toString());
				String canalEscolhido = sc.nextLine();
				for (int j = 0; j < central.getTodosOsCanais().size(); j++) {
					if (central.getTodosOsCanais().get(j).getNomeDoCanal().equals(canalEscolhido)) {
						canal = central.getTodosOsCanais().get(j);
					}
				}
				GeradorDeRelatorios.obterProgramacaoDeUmCanal(canal);
				break;
			case "7":
				GeradorDeRelatorios.gerarProgramacaoDeHoje(central);
				System.out.println("Qual o email do destinatario? ");
				String email = sc.nextLine();
				if(Mensageiro.enviarProgramacaoDeHoje(email).contains("sucesso")) {
					System.out.println("Email enviado!");
				} else {
					System.out.println("Falha ao enviar o email!");
				}
				break;
			case "S":
				op = false;
				System.out.println("Fim do programa.");
				break;
			}
		} while (op);
		sc.close();
	}
}
