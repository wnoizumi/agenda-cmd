package br.ifpr.paranavai.agenda.nucleo;
import java.util.ArrayList;

public class Agenda {
	
	private ArrayList<Contato> contatos;
	
	public Agenda() {
		this.contatos = new ArrayList<Contato>();
	}
	
	public void incluirContato(Contato contato) {
		this.contatos.add(contato);
	}
	
	public Contato buscarPorNumero(String numero) {
		for (Contato contato : contatos) {
			if (contato.getNumero().equals(numero)) {
				return contato;
			}
		}
		
		return null;
	}
	
	public Contato buscarPorNumero(int numero) {
		String numeroStr = Integer.toString(numero);
		return buscarPorNumero(numeroStr);
	}

	public ArrayList<Contato> getContatos() {
		return this.contatos;
	}
}
