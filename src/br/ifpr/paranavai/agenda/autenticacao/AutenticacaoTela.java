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
	
	//TODO o nome deste método está adequado?
	public void inicializar() {
		boolean continuar = true;
		while (continuar) {
			exibirMensagem("----- Sistema de Agenda Eletrônica -----");
			exibirMenu();
			continuar = executarOperacaoSelecionada();
		}
	}
	
	//TODO Este método parece difícil de entender. Como podemos melhorar o design deste método? 
	private boolean executarOperacaoSelecionada() {
		String dadoEntrada = lerEntrada();
		if (dadoEntrada != null && !dadoEntrada.isEmpty()) {
			
			if (dadoEntrada.equals("0")) {
				return false;
			}
			
			if (dadoEntrada.equals("1")) {
				tentarAutentiticar();
			} else {
				exibirMensagem("Opção Incorreta!");
			}
		}
		
		return autenticacaoFalhou();
	}

	private void tentarAutentiticar() {
		exibirMensagem("Nome de usuário:");
		String nomeUsuario = lerEntrada();
		exibirMensagem("Senha:");
		String senha = lerEntrada();
		controleUsuarios.tentarAutenticar(nomeUsuario, senha);
		if (autenticacaoFalhou()) {
			exibirMensagem("Nome de usuário e/ou senha incorretos.");
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
		exibirMensagem("Você precisa estar autenticado pra utilizar o sistema. Selecione uma das seguintes opções:");
		exibirMensagem("1- Realizar autenticação");
		exibirMensagem("0- Sair do Sistema");
		exibirMensagem("Digite o número da opção desejada:");
	}

}
