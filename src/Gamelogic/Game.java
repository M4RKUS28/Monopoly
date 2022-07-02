package Gamelogic;

import java.util.ArrayList;
import java.util.Random;

import Gamedata.*;

public class Game {
	private Random rand = new Random();
	private Player[] pla = new Player[4];
	private Player geradeAmZug;
	private int geradeAmZugIndex;
	private boolean[] imSpiel;
	private Feld[] felder;
	
	private SettingsLoader loader;
	
	private boolean pasch;
	private int paschZahl;
	
	private ArrayList<Feld> alleFelder;

<<<<<<< HEAD
	public Game(String path) {
		loader = new SettingsLoader();
		int ret = 0;
		if ( (ret = loader.loadData(path)) > 0) { //"../json/cards.json"
			 System.exit(ret); 
		}
=======
	public Game() {
		loader = new SettingsLoader(null);
		//if (loader.load()) {
			//exit
		//}
>>>>>>> 38a668c7162c084cfe1f036326b70ae8bcf2993c
		felder = loader.getFelderList();
		
		for (int i = 0; i < 4; i++) {
			pla[i] = new Player(this, i);
			imSpiel[i] = true;
		}
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
		if (geradeAmZug.getPos() > 39) {
			geradeAmZug.zahlen(loader.getUeberlosGeld(), -1); 
			geradeAmZug.bewegen(-40);
		}
		//notify UI über Wurf und neue Pos sowie über Los

		switch (felder[geradeAmZug.getPos()].type()) {
		case STRASSE :
			strasse();
			break;
		case BAHN :
			bahn();
			break;	
		case WERK :
			werk();
			break;
		case SONDERFELD:
			Sonderfeld f = felder[geradeAmZug.getPos()].toSonderFeld();
			if (f == null) {
				
			}
			switch (f.getPosition()) {
			case 2: //Gemeinschafts
			case 17:
			case 33:
				break;
			case 7: //Ereigniss
			case 22:
			case 36:
				break;
			case 4: //Einkommens
				geradeAmZug.zahlen(-1 * loader.getEinkommensteuer(), -1);
				break;
			case 38: //Zusatz
				geradeAmZug.zahlen(-1 * loader.getZusatzsteuer(), -1);
				break;
			case 0: //Los
				geradeAmZug.zahlen(loader.getZusaetzlichesAufLosGeld(), -1); 
				break;
			case 10: //Besuch/Freiparken
			case 20:
				//nix
				break; 
			case 30:
				insGef();
				break;	
			}
			break;
		}
	}
	
	public void strasse() {
		Strasse s = felder[geradeAmZug.getPos()].toStrasse();
		if (s == null) {
			//error
		}
		if (s.getGehoert() > -1 && s.getHypo() == false) {
			pla[s.getGehoert()].zahlen(s.getMiete(s.getHauszahl()), geradeAmZugIndex);
			geradeAmZug.zahlen(-1 * s.getMiete(s.getHauszahl()), s.getGehoert());
			//update Konten in UI
		}
		//gibt Möglichkeitsinfo zu Ui
	}
	
	public void bahn() {
		Bahn b = felder[geradeAmZug.getPos()].toBahnHof();
		if (b == null) {
			//error
		}
		if (b.getGehoert() > -1 && b.getHypo() == false) {
			pla[b.getGehoert()].zahlen(b.getMiete(pla[b.getGehoert()].getBahnZahl()), geradeAmZugIndex);
			geradeAmZug.zahlen(-1 * b.getMiete(pla[b.getGehoert()].getBahnZahl()), b.getGehoert());
			//update Konten in UI
		}
		//gibt Möglichkeitsinfo zu Ui
	}
	
	public void werk() {
		Werk w = felder[geradeAmZug.getPos()].toWerk();
		if (w == null) {
			//error
		}
		if (w.getGehoert() > -1 && w.getHypo() == false) {			
			int wurf = rand.nextInt(6) + rand.nextInt(6) + 2;
			//call UI für Würfelevent
			pla[w.getGehoert()].zahlen(w.getMiete() * wurf, geradeAmZugIndex);
			geradeAmZug.zahlen(-1 * w.getMiete() * wurf, w.getGehoert());
		}
		//gibt Möglichkeitsinfo zu Ui
	}
	
	public void bankrott(int gewinnenderSpieler, int bankrotterSpieler) {
		pla[gewinnenderSpieler].zahlen(pla[bankrotterSpieler].getGeld(), bankrotterSpieler);
		pla[gewinnenderSpieler].getBesitz().addAll(pla[bankrotterSpieler].getBesitz()); 
		imSpiel[bankrotterSpieler] = false;
		pla[bankrotterSpieler] = new Player(this, bankrotterSpieler);
		//notify Ui
		naechster();
	}
	
	public void kaufen() {
			Feld f = felder[geradeAmZug.getPos()];
			if (geradeAmZug.getGeld() - f.getPreis() > -1) {
				geradeAmZug.erhalten(geradeAmZug.getPos());
				geradeAmZug.zahlen(-1 * f.getPreis(), -1);
				f.setGehoert(geradeAmZugIndex);
				geradeAmZug.aendereGesammtscore(f.getPreis());
			}
	}
	
	public void hypothekAufnehmen() {
		Feld f = felder[geradeAmZug.getPos()];
		
		if (f.getHypo() == false) {
			f.setHypo(true);
			geradeAmZug.zahlen(f.getHypothekwert(), -1);
		}
		else {
			//error an Ui
		}
	}
	
	public void hypothekBezahlen() {
		Feld f = felder[geradeAmZug.getPos()];
		if (f.getHypo() == true && geradeAmZug.getGeld() - f.getHypothekwert() > -1) {
			f.setHypo(false);
			geradeAmZug.zahlen(-1 * f.getHypothekwert(), -1);
		}
		else {
			//error an Ui
		}
	}
	
	/*public boolean tauscheKarten(Player tauschpatner, int[] geben, int[] nehmen) {
		int feld =  -1;
		for (int i = 0; i < geben.length; i++) {
			if ( (feld = geradeAmZug.getBesitz().indexOf(geben[i])) > 0) {
				
			} else {
				return false;
			}
			
		}
		
	}*/
	
	public void hausBauen(int pos) {
		Strasse s = felder[pos].toStrasse();
		if (s.hausbauCheck(felder) && geradeAmZug.getGeld() - s.getHauskosten() * 1000 < -1) {
			s.hausBauen();
			geradeAmZug.zahlen(-1 * s.getHauskosten() * 1000, -1);
			geradeAmZug.aendereGesammtscore(s.getHauskosten());
			//Notify UI
		}
		else {
			//error an UI
		}
	}
	
	public void hausVerkaufen(int pos) {
		Strasse s = felder[pos].toStrasse();
		if (s.hausbauCheck(felder) && s.getHauszahl() > 0) {
			s.hausVerkaufen();
			geradeAmZug.zahlen(s.getHauskosten() * 1000, -1);
			geradeAmZug.aendereGesammtscore(-1 * s.getHauskosten());
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
			geradeAmZug.zahlen(-1 * loader.getGefaengniskosten(), -1);
			geradeAmZug.setGef(false);
			//notify UI
		}
		else {
			//error an UI
		}
	}
	
    public boolean hasAllCards(Player p, String colour)
    {
        int count = 0;
        ArrayList<Integer> cardsList = p.getBesitz();
        
        if( ! loader.getColourMap().containsKey(colour)  ) {
            System.out.println( "ERROR: Unbekannte Farbe: " +  colour  );
            return false;
        }
        for ( int i = 0; i < cardsList.size(); i++) {
            int index = cardsList.get(i);
            if ( index >= 0 &&  index < felder.length &&  felder[index].type() == Feld.TYPE.STRASSE ) {
                if( felder[index].toStrasse().getFarbe().equals( colour ) ) {
                    count = count + 1;
                }
            }
              
        }
        
        return loader.getColourMap().get( colour ) == count;
    }
<<<<<<< HEAD
    
    //
    //UI notify
    //
    
    private void spielerwechselNotify() {
    	
    }
    private void geldNotify() {
    	int max = 0, p1 = -1, p2 = -1, p3 = -1, p4 = -1;
    	for (int i = 0; i < pla.length; i ++) {
    		if (max < pla[i].getGeld()) {
    			max = pla[i].getGeld();
    			p1 = i;    			
    		}
    	}
    	max = 0;
    	for (int i = 0; i < pla.length; i ++) {
    		if (max < pla[i].getGeld() && (i != p1)) {
    			max = pla[i].getGeld();
    			p2 = i;    			
    		}
    	}
    	max = 0;
    	for (int i = 0; i < pla.length; i ++) {
    		if (max < pla[i].getGeld()&& (i != p1 && i != p2)) {
    			max = pla[i].getGeld();
    			p3 = i;    			
    		}
    	}
    	max = 0;
    	for (int i = 0; i < pla.length; i ++) {
    		if (max < pla[i].getGeld()&& (i != p1 && i != p2 && i != p3)) {
    			max = pla[i].getGeld();
    			p4 = i;    			
    		}
    	}
    	
    	
    	
    }
=======
		
>>>>>>> 38a668c7162c084cfe1f036326b70ae8bcf2993c
}
