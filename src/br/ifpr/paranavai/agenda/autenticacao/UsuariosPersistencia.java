package br.ifpr.paranavai.agenda.autenticacao;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

//TODO Atualmente os dados dos usuários estão sendo salvos sem segurança. Como melhorar isso? dica: pesquisar sobre algoritmos de criptografia e hashing.
public class UsuariosPersistencia {
	
	private static final String DELIMITADOR_LINHA = "\\R";
	private static final String DELIMITADOR_COLUNA = ";";
	private static final String NOME_ARQUIVO_BD = "usuarios.csv";
	
	private ControleUsuarios controleUsuarios;
	private Path caminhoArquivoBanco;
	
	public UsuariosPersistencia(ControleUsuarios controleUsuarios) {
		this.controleUsuarios = controleUsuarios;
		this.caminhoArquivoBanco = Paths.get(NOME_ARQUIVO_BD);
	}
	
	public void carregarDados() {
		if (Files.exists(caminhoArquivoBanco)) {
			try {
				String dadosUsuariosStr = Files.readString(caminhoArquivoBanco);
				String[] linhasUsuarios = dadosUsuariosStr.split(DELIMITADOR_LINHA);
				for (String linha : linhasUsuarios) {
					carregarUsuariosBanco(linha);
				}
				
			} catch (IOException e) {
				System.out.println("Não foi possível ler os dados de usuários.");
			}
		}
		
	}

	private void carregarUsuariosBanco(String linha) {
		String[] celulas = linha.split(DELIMITADOR_COLUNA);
		if (celulas.length == 3) {
			Usuario usuario = new Usuario(celulas[0], celulas[1], celulas[2]);
			this.controleUsuarios.incluirUsuario(usuario);
		} else {
			System.out.println("Arquivo contendo quantidade de colunas incorreta!");
		}
	}

	public void salvarDados() {
		try (BufferedWriter bufferedWriter = Files.newBufferedWriter(caminhoArquivoBanco, StandardOpenOption.CREATE)) {
			for (Usuario usuario : this.controleUsuarios.getUsuarios()) {
				bufferedWriter.append(usuario.getNomeUsuario() + DELIMITADOR_COLUNA + usuario.getSenha() + DELIMITADOR_COLUNA + usuario.getPapel());
				bufferedWriter.newLine();
			}
		} catch (IOException e) {
			System.out.println("Erro ao salvar dados dos usuários.");
		}
	}
}
