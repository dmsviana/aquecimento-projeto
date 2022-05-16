package persistencia;

import java.util.ArrayList;

import classes.Canal;
import classes.ProgramaDeTV;

public class CentralDeInformacoes {
	
	//Inicializando as listas 
	private ArrayList<ProgramaDeTV> todosOsProgramas = new ArrayList<ProgramaDeTV>();
	private ArrayList<Canal> todosOsCanais = new ArrayList<Canal>();
	
	public ArrayList<Canal> getTodosOsCanais(){
		return todosOsCanais;
	}
	
	public ArrayList<ProgramaDeTV> getTodosOsProgramas() {
		return todosOsProgramas;
	}
	
	//Esse metodo é usado para adicionar um canal
	public boolean adicionarCanal(Canal canal) {
		if(recuperarCanalPeloNome(canal.getNomeDoCanal()) == null) {
			todosOsCanais.add(canal);
			return true;
		}
		return false;
	}
	
	//Esse método é usado para recuperar um canal pelo nome
	public Canal recuperarCanalPeloNome(String nomeDoCanal) {
		for(int i = 0; i < todosOsCanais.size(); i++) {
			if(todosOsCanais.get(i).equals(nomeDoCanal)) {
				return todosOsCanais.get(i);
			}
		}
		return null;
	}
	
	//Esse metodo é usado para adicionar um Programa de TV
	public boolean adicionarPrograma(ProgramaDeTV programa) {
		if (recuperarProgramaPorId(programa.getId()) == null) {
			todosOsProgramas.add(programa);
			return true;
		}
		return false;
	}
	
	
	//Exibir menu para usuario no main
	public String exibirMenu() {
		return "-----MENU-----\nEscolha uma op��o: " + "\n1 - Adicionar novo programa"
				+ "\n2 - Listar todos os programas" + "\n3 - Listar todos os programas de um mesmo tipo" 
				+ "\n4 - Adicionar novo canal"
				+ "\n5 - Listar todos os canais"
				+ "\nS - Sair"; 
	}

	public ProgramaDeTV recuperarProgramaPorId(long id) {
		for (int i = 0; i < todosOsProgramas.size(); i++) {
			if (id == todosOsProgramas.get(i).getId()) {
				return todosOsProgramas.get(i);
			}
		}
		return null;
	}
	
	//Listar programa de um mesmo tipo
	public String listarProgramasDeTipoIguais(String tipo) {
		ArrayList<String> nomeDosProgramas = new ArrayList<String>();;
		for(int i = 0; i < todosOsProgramas.size(); i++) {
			if(todosOsProgramas.get(i).getTipoDePrograma().getDescricao().equals(tipo)) {
				nomeDosProgramas.add(todosOsProgramas.get(i).getNome());
			}
		}
		return nomeDosProgramas.toString();
			
			
		
	}

}
