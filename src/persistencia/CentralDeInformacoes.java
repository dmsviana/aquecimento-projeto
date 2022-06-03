package persistencia;

import java.util.ArrayList;

import classes.Canal;
import classes.ProgramaDeTV;
import classes.Usuario;

public class CentralDeInformacoes {
	private ArrayList<Usuario> todosOsUsuarios = new ArrayList<Usuario>();
	private ArrayList<ProgramaDeTV> todosOsProgramas = new ArrayList<ProgramaDeTV>();
	private ArrayList<Canal> todosOsCanais = new ArrayList<Canal>();
	
	
	public ArrayList<Usuario> getTodosOsUsuarios() {
		return todosOsUsuarios;
	}


	public ArrayList<Canal> getTodosOsCanais(){
		return todosOsCanais;
	}
	
	public ArrayList<ProgramaDeTV> getTodosOsProgramas() {
		return todosOsProgramas;
	}
	

	public void adicionarUsuario(Usuario usuario) throws Exception {
		if(!todosOsUsuarios.isEmpty()) {
			for(Usuario u : todosOsUsuarios) {
				if(u.getCPF().equals(usuario.getCPF())) {
					throw new Exception("USUÁRIO JÁ CADASTRADO!");
				} else if(u.getEmail().equals(usuario.getEmail())) {
					throw new Exception("E-MAIL JÁ CADASTRADO!");
				} 
			}
		}
		todosOsUsuarios.add(usuario);
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
