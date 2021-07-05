package br.ifpr.paranavai.agenda.autenticacao;

import java.security.InvalidParameterException;

public class Usuario {

	private static final int NOME_USUARIO_MINIMO = 3;
	private static final int SENHA_MINIMO = 6;
	
	//TODO como melhorar o controle dos papeis? dica: pesquisem sobre Enum em Java 
	private static final String[] PAPEIS = { "OPERADOR", "GERENTE" };

	private String nomeUsuario;
	private String senha;
	private String papel;

	public Usuario(String nomeUsuario, String senha, String papel) {
		this.setNomeUsuario(nomeUsuario);
		this.setSenha(senha);
		this.setPapel(papel);
	}

	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario) {
		if (nomeUsuario == null || nomeUsuario.length() < NOME_USUARIO_MINIMO) {
			throw new InvalidParameterException("O nome de usuário deve possuir ao menos " + NOME_USUARIO_MINIMO + " caracteres.");
			//TODO onde e como essas exceções devem ser tratadas?
			//Essa é a melhor forma para validar os dados de usuário? Como seria uma forma alternativa?
		}

		this.nomeUsuario = nomeUsuario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		if (senha == null || senha.length() < SENHA_MINIMO) {
			throw new InvalidParameterException("A senha deve possuir ao menos " + SENHA_MINIMO + " caracteres.");
		}

		this.senha = senha;
	}

	public String getPapel() {
		return papel;
	}

	public void setPapel(String papel) {
		if (papel == null || papelInvalido(papel)) {
			throw new InvalidParameterException("O usuário deve possui um papel válido.");
		}

		this.papel = papel;
	}

	private boolean papelInvalido(String papelVerificado) {
		for (String p : PAPEIS) {
			if (papelVerificado.equals(p)) {
				return false;
			}
		}
		return true;
	}
}
