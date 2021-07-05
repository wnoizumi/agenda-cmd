package br.ifpr.paranavai.agenda.nucleo;

//TODO Quais m�todos que est�o aqui mas que devem existir na tela de operadores tamb�m? Para onde podemos mover tais m�todos?
public class AgendaTelaGerente extends AgendaTelaBase {

	public AgendaTelaGerente(Agenda agenda) {
		super(agenda);
	}

	@Override
	protected boolean executarOperacaoSelecionada() {
		String dadoEntrada = lerEntrada();
		if (dadoEntrada != null && !dadoEntrada.isEmpty()) {

			if (dadoEntrada.equals("1")) {

				operacaoIncluirContato();

			} else if (dadoEntrada.equals("2")) {

				operacaoListarContatos();

			} else if (dadoEntrada.equals("0")) {
				return false;
			} else {
				exibirMensagem("Op��o Incorreta!");
			}
		}
		return true;
	}

	private void operacaoListarContatos() {
		exibirMensagem("Lista de Contatos:");
		for (Contato contato : this.getAgenda().getContatos()) {
			exibirMensagem(contato.toString());
		}
	}

	@Override
	protected void exibirMenu() {
		exibirMensagem("Opera��es:");
		exibirMensagem("1- Incluir Contato");
		exibirMensagem("2- Listar Contatos");
		exibirMensagem("3- Buscar Contato");
		exibirMensagem("0- Sair do Sistema");
		exibirMensagem("Digite o n�mero da op��o desejada:");
	}

	private void operacaoIncluirContato() {
		exibirMensagem("--- Incluindo Contato ---");
		exibirMensagem("Digite o nome do contato:");
		String nomeContato = lerEntrada();
		exibirMensagem("Digite o n�mero do contato:");
		String numeroContato = lerEntrada();

		Contato novoContato = new Contato(nomeContato, numeroContato);
		this.getAgenda().incluirContato(novoContato);
	}

}
