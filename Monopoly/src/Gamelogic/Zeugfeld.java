package Gamelogic;

import java.util.Random;

public class Zeugfeld {
	private Player[] pla;
	private Random rand;

	public Zeugfeld(Player[] pla) {
		this.pla = pla;
	}
	
	public void ereigniss(Player p) {
		int x = rand.nextInt();
		
		switch (x) {
		case 0:
			break;
		}
	}
	
	public void gemeinschaft(Player p) {
		
	}
	
}
