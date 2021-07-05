package br.ifpr.paranavai.agenda.nucleo;

public class Contato {
	
	private String nome;
	private String numero;
	
	
	public Contato(String nome, String numero) {
		this.nome = nome;
		this.numero = numero;
	}
	
	public String getNome() {
		if (this.nome == null)
			return "";
		return this.nome;
	}
	
	public String getNumero() {
		return this.numero;
	}

	@Override
	public String toString() {
		return "[nome=" + nome + ", numero=" + numero + "]";
	}
}

