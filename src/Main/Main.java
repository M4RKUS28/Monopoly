package Main;

import GUI.Constants;
import GUI.EndScreen;
import GUI.Spielfeld;
import GUI.StartScreen;
import Gamedata.SettingsLoader;
import Gamelogic.Game;

public class Main {
	static EndScreen startScreen;
	
	public static void main(String[] args) {		
		startScreen = new EndScreen();
	}
	
	public static void start(int x) {
		String version = "";
		switch (x) {
		case 0:
			version = "src/gamemodes/classic_de.json";
			break;
		case 1:
			version = "src/gamemodes/rosenheim_de.json";
			break;
		case 2:
			version = "src/gamemodes/classic_uk.json";
			break;
		}
		
		SettingsLoader loader = new SettingsLoader("");
		Constants con = new Constants();
		Spielfeld spielfeld = new Spielfeld(loader);
		Game game = new Game(version, spielfeld, loader);
		spielfeld.initGame(game, version);
		System.out.println("Spielfeld gestartet Git");
	}
}
