package br.ifpr.paranavai.agenda;
import br.ifpr.paranavai.agenda.autenticacao.AutenticacaoTela;
import br.ifpr.paranavai.agenda.autenticacao.ControleUsuarios;
import br.ifpr.paranavai.agenda.autenticacao.UsuariosPersistencia;
import br.ifpr.paranavai.agenda.nucleo.Agenda;
import br.ifpr.paranavai.agenda.nucleo.AgendaPersistencia;
import br.ifpr.paranavai.agenda.nucleo.AgendaTelaBase;
import br.ifpr.paranavai.agenda.nucleo.AgendaTelaGerente;

public class App {
	
	private ControleUsuarios controleUsuarios;
	private Agenda agenda;
	private AgendaPersistencia agendaPersistencia;
	private UsuariosPersistencia usuariosPersistencia;
	
	public App() {
		this.controleUsuarios = new ControleUsuarios();
		this.agenda = new Agenda();
		this.usuariosPersistencia = new UsuariosPersistencia(controleUsuarios);
		this.agendaPersistencia = new AgendaPersistencia(agenda);
	}
	
	public void executar() {
		carregarDados();
		tentarAutenticar();
		if (existeUsuarioAutenticado()) {
			//TODO E se quisermos uma funcionalidade para permitir que gerentes incluam novos usuários? Como implementar?
			executarAgenda();
		}
	}

	private boolean existeUsuarioAutenticado() {
		return controleUsuarios.getUsuarioAutenticado() != null;
	}

	private void executarAgenda() {
		AgendaTelaBase tela = criarTelaParaUsuarioLogado();
		tela.inicializar();
		agendaPersistencia.salvarDados();
	}

	private AgendaTelaBase criarTelaParaUsuarioLogado() {
		if (controleUsuarios.getUsuarioAutenticado().getPapel().equals("GERENTE")) {
			return new AgendaTelaGerente(agenda);
		} else if (controleUsuarios.getUsuarioAutenticado().getPapel().equals("OPERADOR")) {
			//TODO Implementar nova classe para tela dos operadores (e.g., AgendaTelaOperador). 
			// Considerar que os operadores só podem visualizar os dados do sistema.
			// Ou seja, não podem fazer operações de inclusão de usuários, por exemplo.
		}
		
		//TODO como melhorar?
		return null;
	}

	private void tentarAutenticar() {
		AutenticacaoTela autenticacaoTela = new AutenticacaoTela(controleUsuarios);
		autenticacaoTela.inicializar();
	}

	private void carregarDados() {
		usuariosPersistencia.carregarDados();
		agendaPersistencia.carregarDados();
	}

	public static void main(String[] args) {
		new App().executar();
	}
}
