package br.ifpr.paranavai.agenda.autenticacao;

import java.util.Collection;
import java.util.HashMap;

public class ControleUsuarios {
	
	private HashMap<String, Usuario> mapaUsuarios;
	private Usuario usuarioAutenticado;
	
	public ControleUsuarios() {
		this.mapaUsuarios = new HashMap<>();
		this.usuarioAutenticado = null;
	}

	public void incluirUsuario(Usuario usuario) {
		this.mapaUsuarios.put(usuario.getNomeUsuario(), usuario);
	}

	public Collection<Usuario> getUsuarios() {
		return this.mapaUsuarios.values();
	}
	
	public boolean existemUsuarios() {
		return this.mapaUsuarios.size() > 0;
	}
	
	public Usuario getUsuarioAutenticado() {
		return this.usuarioAutenticado;
	}
	
	public void tentarAutenticar(String nomeUsuario, String senha) {
		Usuario usuarioAutenticado = this.mapaUsuarios.get(nomeUsuario);
		
		if (usuarioAutenticado != null && usuarioAutenticado.getSenha().equals(senha)) {
			this.usuarioAutenticado = usuarioAutenticado;
		}
	}

}
