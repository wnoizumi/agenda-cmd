package br.ifpr.paranavai.agenda.nucleo;
import java.io.PrintStream;
import java.util.Scanner;

public abstract class AgendaTelaBase {
	
	private Scanner entrada;
	private PrintStream saida;
	private Agenda agenda;
	
	public AgendaTelaBase(Agenda agenda) {
		this.agenda = agenda;
		this.entrada = new Scanner(System.in);
		this.saida = System.out;
	}
	
	public void inicializar() {
		boolean continuar = true;
		while (continuar) {
			exibirMensagem("----- Sistema de Agenda Eletrônica -----");
			exibirMenu();
			continuar = executarOperacaoSelecionada();
		}
	}
	
	
	protected abstract boolean executarOperacaoSelecionada();

	protected String lerEntrada() {
		return this.entrada.nextLine();
	}

	protected abstract void exibirMenu();

	protected void exibirMensagem(String mensagem) {
		this.saida.println(mensagem);
	}

	public Agenda getAgenda() {
		return this.agenda;
	}
	
	public void setAgenda(Agenda agenda) {
		if (agenda != null)  {
			this.agenda = agenda;
		} 
	}
}
