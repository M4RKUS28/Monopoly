package mm;

import java.util.Random;

public class test {
	Random rand = new Random();
	
	String[] felder = new String[38];
	private void neuesFeld(int pos) {

	}
	private int wuerfel()
	{
		return rand.nextInt(6) + rand.nextInt(6) + 2;		
	}
	
	private void strassenkarte(int pos) {
		
		//aus der Strassenarray den richtigen Eintrag raussuchen mit Map für laufzeit
		//fertig
	}
	
	private void Ereignisskarte() {
		//Ereigniss
	}
}
