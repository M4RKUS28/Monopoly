package Main;

import GUI.Constants;
import GUI.Spielfeld;
import GUI.StartScreen;
import Gamelogic.Game;

public class Main {
	static StartScreen startScreen;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*
		Constants con = new Constants();
		Spielfeld spielfeld = new Spielfeld();
		Game game = new Game("src/json/cards.json", spielfeld);
		spielfeld.initGame(game);
		System.out.println("Spielfeld gestartet Git");
		*/
		
		startScreen = new StartScreen();
	}
	
	public static void start() {
		Constants con = new Constants();
		Spielfeld spielfeld = new Spielfeld();
		Game game = new Game("src/json/cards.json", spielfeld);
		spielfeld.initGame(game);
		System.out.println("Spielfeld gestartet Git");
	}
}
