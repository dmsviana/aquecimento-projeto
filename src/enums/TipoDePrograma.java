package enums;

public enum TipoDePrograma {
	SERIE("serie"),
	REALITY("reality show"),
	CONTINUO("continuos");
	
	private String descricao;
	
	private TipoDePrograma(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}
}
