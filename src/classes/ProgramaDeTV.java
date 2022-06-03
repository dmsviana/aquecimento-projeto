package classes;

import java.time.DayOfWeek;

import enums.TipoDePrograma;

public class ProgramaDeTV {
	private long id;
	private String nome;
	private TipoDePrograma tipoDePrograma;
	private DayOfWeek diasDaSemana;
	private Canal canal;

	public ProgramaDeTV(String nome, TipoDePrograma tipoDePrograma, DayOfWeek diasDaSemana, Canal canal) {
		this.id = System.currentTimeMillis();
		this.nome = nome;
		this.tipoDePrograma = tipoDePrograma;
		this.diasDaSemana = diasDaSemana;
		this.canal = canal;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public TipoDePrograma getTipoDePrograma() {
		return tipoDePrograma;
	}

	public void setTipoDePrograma(TipoDePrograma tipoDePrograma) {
		this.tipoDePrograma = tipoDePrograma;
	}

	public DayOfWeek getDiasDaSemana() {
		return diasDaSemana;
	}

	public void setDiasDaSemana(DayOfWeek diasDaSemana) {
		this.diasDaSemana = diasDaSemana;
	}

	public long getId() {
		return id;
	}

	public Canal getCanal() {
		return canal;
	}

	public void setCanal(Canal canal) {
		this.canal = canal;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String toString() {
		return "Nome do programa: " + nome;
	}


}
