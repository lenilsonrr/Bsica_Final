package br.com.unisales.BiscaX;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Partida {

	private Baralho baralho;
	private Carta cartaNipe;
	private Jogador jogador1;
	private Jogador jogador2;
	private int pJ1 = 0;
	private int pJ2 = 0;

	private Scanner sc = new Scanner(System.in);

	public Partida(Baralho baralho) {
		this.baralho = baralho;
	}

	public Jogador getJogador1() {
		return this.jogador1 = jogador1;
	}

	public void setjogador1(Jogador jogador) {
		this.jogador1 = jogador;
	}

	public Jogador getJogador2() {
		return this.jogador2 = jogador2;
	}

	public void setjogador2(Jogador jogador) {
		this.jogador2 = jogador;
	}

	public Baralho getBaralho() {
		return baralho;
	}

	public Carta getCartaNipe() {
		return cartaNipe;
	}

	public void setCartaNipe(Carta cartaNipe) {
		this.cartaNipe = cartaNipe;
	}

	// adicionando metodos

	public int getpJ1() {
		return pJ1;
	}

	public void setpJ1(int pJ1) {
		this.pJ1 = pJ1;
	}

	public int getpJ2() {
		return pJ2;
	}

	public void setpJ2(int pJ2) {
		this.pJ2 = pJ2;
	}

	// Adicinonando jogadores

	public void addJogadores() {
		jogador1 = new Jogador("Jog1", 0, cartasJogadores());
		jogador2 = new Jogador("Jog2", 0, cartasJogadores());
		jogador1.setVez("vez");
		jogador2.setVez(null);
	}

	public List<Carta> cartasJogadores() {
		List<Carta> cartasJogador = new ArrayList<Carta>();

		for (int i = 0; i < 3; i++) {
			cartasJogador.add(baralho.comprarUmaCarta());
		}
		return cartasJogador;

	}

	public void nipeDoJogo() {
		Carta c_trunfo = baralho.cartaParaTrunfo();
		setCartaNipe(c_trunfo);
	}

	public void trocandoDoisPorTrunfoMaior() {
		for (int i = 0; i < jogador1.getCartasJogador().size(); i++) {
			if (jogador1.getCartasJogador().get(i).getFaces().equals("2")
					&& jogador1.getCartasJogador().get(i).getNipe().equals(cartaNipe.getNipe())) {
				jogador1.getCartasJogador().set(i, cartaNipe);
			}
			if (jogador2.getCartasJogador().get(i).getFaces().equals("2")
					&& jogador2.getCartasJogador().get(i).getNipe().equals(cartaNipe.getNipe())) {
				jogador2.getCartasJogador().set(i, cartaNipe);
			}
		}

	}

	public void regraSeteDeRoda(Jogador j, int n, Jogador j2, int n2) {
		if (j.getCartasJogador().get(n).getNipe() == cartaNipe.getNipe()
				&& j.getCartasJogador().get(n).getFaces() == "7" && j2.getCartasJogador().get(n2).getFaces() != "As"
				&& j2.getCartasJogador().get(n2).getNipe() != cartaNipe.getNipe() && baralho.getCartas().size() == 34) {
			System.out.println("Sete na primeira rodada ja é um ponto");
		}
	}
	
	public boolean temAsNoBaralho() {

		for (int i = 0; i < baralho.getCartas().size(); i++) {
			if (baralho.getCartas().get(i).getNipe() == cartaNipe.getNipe()
					&& baralho.getCartas().get(i).getFaces() == "As") {
				return true;
			}
		}
		return false;
	}

	public boolean temSeteNoBaralho() {

		for (int i = 0; i < baralho.getCartas().size(); i++) {
			if (baralho.getCartas().get(i).getNipe() == cartaNipe.getNipe()
					&& baralho.getCartas().get(i).getFaces() == "7") {
				System.out.println(i + " " + baralho.getCartas().get(i));
				return true;
			}
		}
		return false;
	}

	public boolean temAsNasCartasOponete(Jogador j) {

		for (int i = 0; i < j.getCartasJogador().size(); i++) {
			if (jogador1.getCartasJogador().get(i).getNipe() == cartaNipe.getNipe()
					&& jogador1.getCartasJogador().get(i).getFaces() == "As") {
				return true;
			}
		}
		return false;
	}

	public boolean temSeteNasCartasOponete(Jogador j) {

		for (int i = 0; i < j.getCartasJogador().size(); i++) {
			if (j.getCartasJogador().get(i).getNipe() == cartaNipe.getNipe()
					&& j.getCartasJogador().get(i).getFaces() == "7") {
				System.out.println(i + " " + j.getCartasJogador().get(i));
				return true;
			}

		}
		return false;
	}
	

	public void regraAsNaoSaiAntesDoSete(Jogador j, int n, Jogador j2) {

		while (j.getCartasJogador().get(n).getNipe() == cartaNipe.getNipe()
				&& j.getCartasJogador().get(n).getFaces() == "As") {
			if (temSeteNoBaralho() == true) {
				System.out.println("\n\n************");
				System.out.println("essa nao******* ESTAMOS AQUI ******\nEscolha outra: ");
				System.out.print("\n" + j);
				System.out.println("\nTrunfo: " + cartaNipe + "Nipe: " + cartaNipe.getNipe() + "\n");
				n = sc.nextInt() - 1;
				if (j.getNome() == jogador1.getNome()) {
					setpJ1(n);
				}
				if (j.getNome() == jogador2.getNome()) {

					setpJ2(n);
				}
			}
			if (temSeteNasCartasOponete(j2) == true && j.getCartasJogador().size() > 0) {
				System.out.println("\n\n************");
				System.out.println("essa nao******* ESTAMOS AQUI ******\nEscolha outra: ");
				System.out.print("\n" + j);
				System.out.println("\nTrunfo: " + cartaNipe + "Nipe: " + cartaNipe.getNipe() + "\n");
				n = sc.nextInt() - 1;
				if (j.getNome() == jogador1.getNome()) {
					setpJ1(n);
				}
				if (j.getNome() == jogador2.getNome()) {

					setpJ2(n);
				}
			}
		}

	}

	public void regraSeteNaoPodeSairDeFundoSoNaUltimaRodada(Jogador j, int n) {
		while (j.getCartasJogador().get(n).getNipe() == cartaNipe.getNipe()
				&& j.getCartasJogador().get(n).getFaces() == "7") {

			System.out.println("\n\n******");
			System.out.println("Essa Carta no pode ser solta de fundo só pode nessa condição na ultima rodada");
			System.out.println();
			System.out.println("essa nao\nEscolha outra: ");
			System.out.print("\n" + j);
			System.out.println("\nTrunfo: " + cartaNipe + "Nipe: " + cartaNipe.getNipe() + "\n");
			n = sc.nextInt() - 1;
			if (j.getNome() == jogador1.getNome()) {

				System.out.print(jogador1.getNome());
				setpJ1(n);
			}

		}
	}

	public void regraMesmoNipeSemSerTrunfo(Jogador j1, int n1, Jogador j2, int n2) {

		if (j1.getCartasJogador().get(n1).getNipe().equals(j2.getCartasJogador().get(n2).getNipe())
				&& j1.getCartasJogador().get(n1).getNipe() != cartaNipe.getNipe()
				&& j2.getCartasJogador().get(n2).getNipe() != cartaNipe.getNipe()) {
			if (j1.getCartasJogador().get(n1).GetPeso() > j2.getCartasJogador().get(n2).GetPeso()) {
				j1.somarPontos(j1.getCartasJogador().get(n1).getValor() + j2.getCartasJogador().get(n2).getValor());
				System.out.println("Vitoria jog1 agora é sua vez @@@@@ cartas do mesmo naipe sem ser trunfo\n");
				j1.setVez("vez");
				j2.setVez(null);

			} else {
				j2.somarPontos(j1.getCartasJogador().get(n1).getValor() + j2.getCartasJogador().get(n2).getValor());
				System.out.println("Vitoria jog2 agora é sua vez @@@@@ cartas do mesmo naipe sem ser trunfo\n");
				j2.setVez("vez");
				j1.setVez(null);
			}
		}

	}

	public void regraCartaTrunfoEOutraNaoTrunfo(Jogador j1, int n1, Jogador j2, int n2) {
		if (j1.getCartasJogador().get(n1).getNipe().equals(cartaNipe.getNipe())
				&& j2.getCartasJogador().get(n2).getNipe() != cartaNipe.getNipe()) {

			j1.somarPontos(j1.getCartasJogador().get(n1).getValor() + j2.getCartasJogador().get(n2).getValor());
			System.out.println("Vitoria jog1 agora é sua vez ***** Cartas trunfo e outro não\n");
			j1.setVez("vez");
			j2.setVez(null);

		}
		if (j2.getCartasJogador().get(n2).getNipe().equals(cartaNipe.getNipe())
				&& j1.getCartasJogador().get(n1).getNipe() != cartaNipe.getNipe()) {
			j2.somarPontos(j1.getCartasJogador().get(n1).getValor() + j2.getCartasJogador().get(n2).getValor());
			System.out.println("Vitoria jog2 agora é sua vez ******** Cartas trunfo e outro não\n");
			j2.setVez("vez");
			j1.setVez(null);
		}

	}

	public void regraCartasMesmoNipeTrunfo(Jogador j1, int n1, Jogador j2, int n2) {

		if (j1.getCartasJogador().get(n1).getNipe().equals(cartaNipe.getNipe())
				&& j2.getCartasJogador().get(n2).getNipe().equals(cartaNipe.getNipe())) {
			if (j1.getCartasJogador().get(n1).GetPeso() > j2.getCartasJogador().get(n2).GetPeso()) {
				j1.somarPontos(j1.getCartasJogador().get(n1).getValor() + j2.getCartasJogador().get(n2).getValor());
				System.out.println("Vitoria jog1 agora é sua vez  %%%%%%% cartas mesmo naipe trunfo\n");
				j1.setVez("vez");
				j2.setVez(null);
			} else {
				j2.somarPontos(j1.getCartasJogador().get(n1).getValor() + j2.getCartasJogador().get(n2).getValor());
				System.out.println("Vitoria jog2 agora é sua vez %%%%%%%% cartas mesmo naipe trunfo\n");
				j2.setVez("vez");
				j1.setVez(null);
			}
		}
	}

	public void regraCartasNipesDiferentes(Jogador j1, int n1, Jogador j2, int n2) {

		if (j1.getCartasJogador().get(n1).getNipe() != j2.getCartasJogador().get(n2).getNipe()
				&& j1.getCartasJogador().get(n1).getNipe() != cartaNipe.getNipe()
				&& j2.getCartasJogador().get(n2).getNipe() != cartaNipe.getNipe()
				&& j2.getCartasJogador().get(n2).getNipe() != cartaNipe.getNipe()) {
			j1.somarPontos(j1.getCartasJogador().get(n1).getValor() + j2.getCartasJogador().get(n2).getValor());
			System.out.println("Vitoria continua a sua vez  ######## Cartas naipes diferentes\n");
			j1.setVez("vez");
			j2.setVez(null);
		}

	}

	public void regraReles(Jogador j1, int n1, Jogador j2, int n2) {

		if (j1.getCartasJogador().get(n1).getFaces() == "7"
				&& j1.getCartasJogador().get(n1).getNipe() == cartaNipe.getNipe()
				&& j2.getCartasJogador().get(n2).getFaces() == "As"
				&& j2.getCartasJogador().get(n2).getNipe() == cartaNipe.getNipe()) {
			System.out.println("Reles\n");
		}
	}

	public void rodadas() {
		int cont = 0;
		while (baralho.getCartas().size() > 0) {
			cont++;
			System.out.println("Rodada " + cont + "ª");
			if (jogador1.getVez() == "vez") {
				System.out.print("\n" + getJogador1() + "\n" + getJogador2());
				System.out.println("\nTrunfo: " + cartaNipe + "Nipe: " + cartaNipe.getNipe() + "\n");
				System.out.println("Qual Carta Jog1: ");
				pJ1 = sc.nextInt() - 1;
				regraAsNaoSaiAntesDoSete(jogador1, getpJ1(), jogador2);
				System.out.println("Qual Carta Jog2: ");
				pJ2 = sc.nextInt() - 1;
				regraAsNaoSaiAntesDoSete(jogador2, getpJ2(), jogador1);
				System.out.println();
				regraReles(jogador1, pJ1, jogador2, pJ2);
				regraSeteDeRoda(jogador1, pJ1, jogador2, pJ2);

				regraMesmoNipeSemSerTrunfo(jogador1, pJ1, jogador2, pJ2);
				regraCartaTrunfoEOutraNaoTrunfo(jogador1, pJ1, jogador2, pJ2);
				regraCartasMesmoNipeTrunfo(jogador1, pJ1, jogador2, pJ2);
				regraCartasNipesDiferentes(jogador1, pJ1, jogador2, pJ2);

			} else {

				System.out.print("\n" + getJogador2() + "\n" + getJogador1());
				System.out.println("\nTrunfo: " + cartaNipe + "Nipe: " + cartaNipe.getNipe() + "\n");
				System.out.println("Qual Carta Jog2: ");
				pJ2 = sc.nextInt() - 1;
				regraAsNaoSaiAntesDoSete(jogador2, getpJ2(), jogador1);
				System.out.println("Qual Carta Jog1: ");
				pJ1 = sc.nextInt() - 1;
				regraAsNaoSaiAntesDoSete(jogador1, getpJ1(), jogador2);
				regraReles(jogador2, pJ2, jogador1, pJ1);
				System.out.println();

				regraMesmoNipeSemSerTrunfo(jogador1, pJ1, jogador2, pJ2);
				regraCartaTrunfoEOutraNaoTrunfo(jogador1, pJ1, jogador2, pJ2);
				regraCartasMesmoNipeTrunfo(jogador1, pJ1, jogador2, pJ2);
				regraCartasNipesDiferentes(jogador2, pJ2, jogador1, pJ1);

			}

			System.out.println("Mão");
			System.out.println(
					getJogador2().getCartasJogador().get(pJ2) + "    X \n" + getJogador1().getCartasJogador().get(pJ1));

			jogador1.getCartasJogador().remove(pJ1);
			jogador1.getCartasJogador().add(baralho.comprarUmaCarta());
			jogador2.getCartasJogador().remove(pJ2);
			jogador2.getCartasJogador().add(baralho.comprarUmaCarta());
			System.out.println();
		}
		cont = 17;
		while (jogador1.getCartasJogador().size() > 0) {
			cont++;
			System.out.println("Rodada " + cont + "ª");
			if (jogador1.getVez() == "vez") {
				System.out.print("\n" + getJogador1() + "\n" + getJogador2());
				System.out.println("\nTrunfo: " + cartaNipe + "Nipe: " + cartaNipe.getNipe() + "\n");
				System.out.println("Qual Carta Jog1: ");
				pJ1 = sc.nextInt() - 1;
				regraAsNaoSaiAntesDoSete(jogador1, getpJ1(), jogador2);
				System.out.println("Qual Carta Jog2: ");
				pJ2 = sc.nextInt() - 1;
				regraAsNaoSaiAntesDoSete(jogador2, getpJ2(), jogador1);
				System.out.println();

				regraReles(jogador1, pJ1, jogador2, pJ2);
				regraMesmoNipeSemSerTrunfo(jogador1, pJ1, jogador2, pJ2);
				regraCartaTrunfoEOutraNaoTrunfo(jogador1, pJ1, jogador2, pJ2);
				regraCartasMesmoNipeTrunfo(jogador1, pJ1, jogador2, pJ2);
				regraCartasNipesDiferentes(jogador1, pJ1, jogador2, pJ2);

			} else {

				System.out.print("\n" + getJogador2() + "\n" + getJogador1());
				System.out.println("\nTrunfo: " + cartaNipe + "Nipe: " + cartaNipe.getNipe() + "\n");
				System.out.println("Qual Carta Jog2: ");
				pJ2 = sc.nextInt() - 1;
				regraAsNaoSaiAntesDoSete(jogador2, getpJ2(), jogador1);
				System.out.println("Qual Carta Jog1: ");
				pJ1 = sc.nextInt() - 1;
				regraAsNaoSaiAntesDoSete(jogador1, getpJ1(), jogador2);
				regraReles(jogador2, pJ2, jogador1, pJ1);
				System.out.println();

				regraMesmoNipeSemSerTrunfo(jogador1, pJ1, jogador2, pJ2);
				regraCartaTrunfoEOutraNaoTrunfo(jogador1, pJ1, jogador2, pJ2);
				regraCartasMesmoNipeTrunfo(jogador1, pJ1, jogador2, pJ2);
				regraCartasNipesDiferentes(jogador2, pJ2, jogador1, pJ1);

			}

			System.out.println("Mão");
			System.out.println(
					getJogador2().getCartasJogador().get(pJ2) + "    X \n" + getJogador1().getCartasJogador().get(pJ1));
			System.out.println(jogador1.getCartasJogador().size() + "   " + jogador2.getCartasJogador().size());

			jogador1.getCartasJogador().remove(pJ1);

			jogador2.getCartasJogador().remove(pJ2);
		}
		System.out.println(jogador1.getNome() + " Pontos: " + jogador1.getPontos() + " \n" + jogador2.getNome()
				+ " Pontos: " + jogador2.getPontos());
	}



	public void resetarJogo() {
		getJogador1().setPontos(0);
		getJogador2().setPontos(0);
		getBaralho();
	}

	@Override
	public String toString() {

		return jogador1 + "\n" + jogador2 + "" + "\ntrunfo: " + cartaNipe + "" + "Naipe: " + cartaNipe.getNipe() + "\n";
	}

}