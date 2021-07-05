package br.ifpr.paranavai.agenda.autenticacao;

import java.io.PrintStream;
import java.util.Scanner;

//TODO existe algo em comum entre a tela de autenticacao e as demais telas?
public class AutenticacaoTela {
	
	private Scanner entrada;
	private PrintStream saida;
	private ControleUsuarios controleUsuarios;
	
	public AutenticacaoTela(ControleUsuarios controleUsuarios) {
		this.controleUsuarios = controleUsuarios;
		this.entrada = new Scanner(System.in);
		this.saida = System.out;
	}
	
	//TODO o nome deste m�todo est� adequado?
	public void inicializar() {
		boolean continuar = true;
		while (continuar) {
			exibirMensagem("----- Sistema de Agenda Eletr�nica -----");
			exibirMenu();
			continuar = executarOperacaoSelecionada();
		}
	}
	
	//TODO Este m�todo parece dif�cil de entender. Como podemos melhorar o design deste m�todo? 
	private boolean executarOperacaoSelecionada() {
		String dadoEntrada = lerEntrada();
		if (dadoEntrada != null && !dadoEntrada.isEmpty()) {
			
			if (dadoEntrada.equals("0")) {
				return false;
			}
			
			if (dadoEntrada.equals("1")) {
				tentarAutentiticar();
			} else {
				exibirMensagem("Op��o Incorreta!");
			}
		}
		
		return autenticacaoFalhou();
	}

	private void tentarAutentiticar() {
		exibirMensagem("Nome de usu�rio:");
		String nomeUsuario = lerEntrada();
		exibirMensagem("Senha:");
		String senha = lerEntrada();
		controleUsuarios.tentarAutenticar(nomeUsuario, senha);
		if (autenticacaoFalhou()) {
			exibirMensagem("Nome de usu�rio e/ou senha incorretos.");
		}
	}

	private boolean autenticacaoFalhou() {
		return controleUsuarios.getUsuarioAutenticado() == null;
	}

	private void exibirMensagem(String mensagem) {
		this.saida.println(mensagem);
	}
	
	private String lerEntrada() {
		return this.entrada.nextLine();
	}
	
	private void exibirMenu() {
		exibirMensagem("Voc� precisa estar autenticado pra utilizar o sistema. Selecione uma das seguintes op��es:");
		exibirMensagem("1- Realizar autentica��o");
		exibirMensagem("0- Sair do Sistema");
		exibirMensagem("Digite o n�mero da op��o desejada:");
	}

}
