package br.com.unisales.BiscaX;

public class Programa {

	public static void main(String[] args) {
		Partida partidaTeste = new Partida(new Baralho());
		partidaTeste.getBaralho().embaralhar();
		partidaTeste.nipeDoJogo();
		partidaTeste.addJogadores();
		partidaTeste.trocandoDoisPorTrunfoMaior();
		partidaTeste.rodadas();

	}

}
