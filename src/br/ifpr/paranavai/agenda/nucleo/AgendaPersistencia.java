package br.ifpr.paranavai.agenda.nucleo;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class AgendaPersistencia {
	
	private static final String DELIMITADOR_LINHA = "\\R";
	private static final String DELIMITADOR_COLUNA = ";";
	private static final String NOME_ARQUIVO_BD = "agenda.csv";
	
	private Agenda agenda;
	private Path caminhoArquivoBanco;

	public AgendaPersistencia(Agenda agenda) {
		this.agenda = agenda;
		this.caminhoArquivoBanco = Paths.get(NOME_ARQUIVO_BD);
	}
	
	public void carregarDados() {
		if (Files.exists(caminhoArquivoBanco)) {
			try {
				String dadosAgendaStr = Files.readString(caminhoArquivoBanco);
				String[] linhasAgenda = dadosAgendaStr.split(DELIMITADOR_LINHA);
				for (String linha : linhasAgenda) {
					carregarContatoBanco(linha);
				}
				
			} catch (IOException e) {
				System.out.println("Não foi possível ler os dados salvos na agenda.");
			}
		}
		
	}

	private void carregarContatoBanco(String linha) {
		String[] celulas = linha.split(DELIMITADOR_COLUNA);
		if (celulas.length == 2) {
			Contato contato = new Contato(celulas[0], celulas[1]);
			this.agenda.incluirContato(contato);
		} else {
			System.out.println("Arquivo contendo quantidade de colunas incorreta!");
		}
	}

	public void salvarDados() {
		try (BufferedWriter bufferedWriter = Files.newBufferedWriter(caminhoArquivoBanco, StandardOpenOption.CREATE)) {
			for (Contato contato : this.agenda.getContatos()) {
				bufferedWriter.append(contato.getNome() + DELIMITADOR_COLUNA + contato.getNumero());
				bufferedWriter.newLine();
			}
		} catch (IOException e) {
			System.out.println("Erro ao salvar dados da agenda.");
		}
	}
}
