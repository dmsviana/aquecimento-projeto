package persistencia;

import java.util.ArrayList;

import classes.Canal;
import classes.ProgramaDeTV;

public class CentralDeInformacoes {
	private ArrayList<ProgramaDeTV> todosOsProgramas = new ArrayList<ProgramaDeTV>();
	private ArrayList<Canal> todosOsCanais = new ArrayList<Canal>();
	
	public ArrayList<Canal> getTodosOsCanais(){
		return todosOsCanais;
	}
	
	public ArrayList<ProgramaDeTV> getTodosOsProgramas() {
		return todosOsProgramas;
	}
	
	public boolean adicionarCanal(Canal canal) {
		if(recuperarCanalPeloNome(canal.getNomeDoCanal()) == null) {
			todosOsCanais.add(canal);
			return true;
		}
		return false;
	}
	
	public Canal recuperarCanalPeloNome(String nomeDoCanal) {
		for(int i = 0; i < todosOsCanais.size(); i++) {
			if(todosOsCanais.get(i).equals(nomeDoCanal)) {
				return todosOsCanais.get(i);
			}
		}
		return null;
	}

	public boolean adicionarPrograma(ProgramaDeTV programa) {
		if (recuperarProgramaPorId(programa.getId()) == null) {
			todosOsProgramas.add(programa);
			return true;
		}
		return false;
	}


	public ProgramaDeTV recuperarProgramaPorId(long id) {
		for (int i = 0; i < todosOsProgramas.size(); i++) {
			if (id == todosOsProgramas.get(i).getId()) {
				return todosOsProgramas.get(i);
			}
		}
		return null;
	}
	
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
