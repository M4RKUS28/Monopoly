package Gamelogic;
import java.util.Random;

import Gamedata.*;

public class Game {
	private Random rand = new Random();
	private Player[] pla = new Player[4];
	private Player geradeAmZug;
	private int geradeAmZugIndex;
	private boolean[] imSpiel;
	private String[] felder = new String[38];
	
	private int[] felderMap = new int[40];
	
	private Strasse[] strassen;
	private Bahn[] bahnen;
	private Werk[] werke;
	
	private Feld[] alleFelder;
	
	private Zeugfeld zeugfeld;
	
	private boolean pasch;
	private int paschZahl;
	
	public Game() {
		
		for (int i = 0; i < 4; i++) {
			pla[i] = new Player(this, i);
			imSpiel[i] = true;
		}
		
		zeugfeld = new Zeugfeld(pla);
		
		//hier die arrays befüllen
	}
	
	public void start() { //Ui callt das hier mit Startbutton
		geradeAmZug = pla[0];
		geradeAmZugIndex = 0;
		//notify UI
	}
	
	public void naechster() {
		if (pasch) {
			pasch = false;
			//Info Ui
		}
		else {
			paschZahl = 0;
			geradeAmZugIndex = (geradeAmZugIndex + 1) % 4;
			while (imSpiel[geradeAmZugIndex] == false) {
				geradeAmZugIndex = (geradeAmZugIndex + 1) % 4;
			}
			geradeAmZug = geradeAmZug = pla[geradeAmZugIndex];
			//Notify UI (auch über Gefängniss)
		}

	}
	
	private void wuerfeln() //Ui callt das hier mit Würfelbutton
	{
		if (geradeAmZug.getGef()) {
			//was im gefägniss passiert
		}
		int wurf1 = rand.nextInt(6) + 1;
		int wurf2 = rand.nextInt(6) + 1;
		int wurf = wurf1 + wurf2;
		if (wurf1 == wurf2) {
			pasch = true;
			paschZahl++;
		}
		if (geradeAmZug.getGef()) {
			if (geradeAmZug.getGefZahl() == 0) {
				//nur freikauf möglich an UI
			}
			if (pasch) {
				pasch = false;
				paschZahl = 0;
			}
			else {
				return;
			}
		}
		if (paschZahl > 2) {
			insGef();
			//UI info
			pasch = false;
			paschZahl = 0;
			return;
		}
		geradeAmZug.bewegen(wurf);
		if (geradeAmZug.getPos() - wurf > 39) {
			geradeAmZug.zahlen(4000, -1); 
			geradeAmZug.bewegen(-40);
		}
		//notify UI über Wurf und neue Pos sowie über Los

		switch (felder[geradeAmZug.getPos()]) {
		case "Strasse" :
			strasse();
			break;
		case "Bahn" :
			bahn();
			break;	
		case "Werk" :
			werk();
			break;
		case "Steuer":
			if (geradeAmZug.getPos() == 4) {
				geradeAmZug.zahlen(-4000, -1);
			}
			else if (geradeAmZug.getPos() == 38) {
				geradeAmZug.zahlen(-2000, -1);
			}
			break;
		case "Ereigniss" :
			zeugfeld.ereigniss(geradeAmZug);
			break;
		case "Gemeinschaft":
			zeugfeld.ereigniss(geradeAmZug);
			break;
		case "Sonder" :
			//notify UI über keine extra möglichkeiten
			break;		
		case "InsGefaengniss":
			//sonder für ins Gefaengniss
			break;
		}
	}
	
	public void strasse() {
		Strasse s = strassen[felderMap[geradeAmZug.getPos()]];
		if (s.getGehoert() > -1 && s.getHypo() == false) {
			pla[s.getGehoert()].zahlen(s.getMiete(s.getHauszahl()), geradeAmZugIndex);
			geradeAmZug.zahlen(-1 * s.getMiete(s.getHauszahl()), s.getGehoert());
			//update Konten in UI
		}
		//gibt Möglichkeitsinfo zu Ui
	}
	
	public void bahn() {
		Bahn b = bahnen[felderMap[geradeAmZug.getPos()]];
		if (b.getGehoert() > -1 && b.getHypo() == false) {
			pla[b.getGehoert()].zahlen(b.getMiete(pla[b.getGehoert()].getBahnZahl()), geradeAmZugIndex);
			geradeAmZug.zahlen(-1 * b.getMiete(pla[b.getGehoert()].getBahnZahl()), b.getGehoert());
			//update Konten in UI
		}
		//gibt Möglichkeitsinfo zu Ui
	}
	
	public void werk() {
		Werk w = werke[felderMap[geradeAmZug.getPos()]];
		if (w.getGehoert() > -1 && w.getHypo() == false) {			
			int wurf = rand.nextInt(6) + rand.nextInt(6) + 2;
			//call UI für Würfelevent
			pla[w.getGehoert()].zahlen(w.getMiete() * wurf, geradeAmZugIndex);
			geradeAmZug.zahlen(-1 * w.getMiete() * wurf, w.getGehoert());
		}
		//gibt Möglichkeitsinfo zu Ui
	}
	
	public void bankrott(int von, int zu) {
		//irgendwie alles eingetum rüber schieben
		imSpiel[zu] = false;
	}
	
	public void kaufen() {
			Feld f = alleFelder[geradeAmZug.getPos()];
			if (geradeAmZug.getGeld() - f.getPreis() > -1) {
				geradeAmZug.erhalten(geradeAmZug.getPos());
				geradeAmZug.zahlen(-1 * f.getPreis(), -1);
				f.setGehoert(geradeAmZugIndex);
			}
	}
	
	public void hypothekAufnehmen() {
		Feld f = alleFelder[geradeAmZug.getPos()];
		if (f.getHypo() == false) {
			f.setHypo(true);
			geradeAmZug.zahlen(f.getHypothekwert(), -1);
		}
		else {
			//error an Ui
		}
	}
	
	public void hypothekBezahlen() {
		Feld f = alleFelder[geradeAmZug.getPos()];
		if (f.getHypo() == true && geradeAmZug.getGeld() - f.getHypothekwert() > -1) {
			f.setHypo(false);
			geradeAmZug.zahlen(-1 * f.getHypothekwert(), -1);
		}
		else {
			//error an Ui
		}
	}
	
	public void tauschen() {
		//ok jetzt müssen wir über die logik reden
	}
	
	public void hausBauen(int pos) {
		Strasse s = strassen[felderMap[pos]];
		if (s.hausbauCheck(strassen, felderMap) && geradeAmZug.getGeld() - s.getHauskosten() * 1000 < -1) {
			s.hausBauen();
			geradeAmZug.zahlen(-1 * s.getHauskosten() * 1000, -1);
			//Notify UI
		}
		else {
			//error an UI
		}
	}
	
	public void hausVerkaufen(int pos) {
		Strasse s = strassen[felderMap[pos]];
		if (s.hausbauCheck(strassen, felderMap) && s.getHauszahl() > 0) {
			s.hausVerkaufen();
			geradeAmZug.zahlen(s.getHauskosten() * 1000, -1);
			//Notify UI
		}
		else {
			//error an UI
		}
	}
	
	public void insGef() {
		geradeAmZug.setGef(true);
		geradeAmZug.setGefZahl(3);
	}
	
	public void freikaufen() {
		if (geradeAmZug.getGef()) {
			geradeAmZug.zahlen(-1 * 1000, -1);
			geradeAmZug.setGef(false);
			//notify UI
		}
		else {
			//error an UI
		}
	}
	
	
	
	
	
	
	
}
