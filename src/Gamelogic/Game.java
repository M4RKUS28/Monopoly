package Gamelogic;

import java.util.ArrayList;
import java.util.Random;

import GUI.Spielfeld;
import Gamedata.Bahn;
import Gamedata.Feld;
import Gamedata.Feld.TYPE;
import Gamedata.SettingsLoader;
import Gamedata.Sonderfeld;
import Gamedata.Strasse;
import Gamedata.Werk;

public class Game {
	private Spielfeld spielfeld;
	private Random rand = new Random();
	private Player[] pla = new Player[4];
	private Player geradeAmZug;
	private int geradeAmZugIndex;
	private boolean[] imSpiel;
	private Feld[] felder;
	private boolean hausBauen;
	
	private SettingsLoader loader;
	
	private boolean pasch;
	private int paschZahl;
	
	private ArrayList<Integer> benutzteEreigniskarten;
	private ArrayList<Integer> benutzteGemeinschaftskarten;
	
	private boolean bankrott;
	private int bankrottPlayer;
	
	private int cardId;

	public Game(String path, Spielfeld spielfeld, SettingsLoader loader) {
		benutzteEreigniskarten = new ArrayList<Integer>();
		benutzteGemeinschaftskarten = new ArrayList<Integer>();
		this.spielfeld = spielfeld;
		this.loader = loader;
		imSpiel = new boolean[4];
		int ret = 0;
		if ( (ret = loader.loadData(path)) > 0) {
			 System.exit(ret); 
		}

		felder = loader.getFelderList();
		
		for (int i = 0; i < 4; i++) {
			pla[i] = new Player(this, i);
			imSpiel[i] = true;
		}
	}
	
	public void start() { //Ui callt das hier mit Startbutton
		geradeAmZug = pla[0];
		geradeAmZugIndex = 0;
		positionsNotify();
		pla[3].erhalten(12);
		felder[12].setGehoert(3);
		pla[3].erhalten(11);
		felder[11].setGehoert(3);
		geradeAmZug.erhalten(1);
		felder[1].setGehoert(0);
		geradeAmZug.erhalten(13);
		felder[13].setGehoert(0);
		geradeAmZug.erhalten(14);
		felder[14].setGehoert(0);
		geradeAmZug.erhalten(6);
		felder[6].setGehoert(0);
		geradeAmZug.erhalten(8);
		felder[8].setGehoert(0);
		geradeAmZug.erhalten(9);
		felder[9].setGehoert(0);

		spielfeld.updateKartenbehaelter(geradeAmZug.getBesitz());
	}
	
	public void ende() { //Ui callt das hier mit Endbutton
		
	}
	
	public void naechster() {
		if (pasch) {
			pasch = false;
		}
		else {
			paschZahl = 0;
			geradeAmZugIndex = (geradeAmZugIndex + 1) % 4;
			while (imSpiel[geradeAmZugIndex] == false) {
				geradeAmZugIndex = (geradeAmZugIndex + 1) % 4;
			}
			geradeAmZug = pla[geradeAmZugIndex];
			if(geradeAmZug.getGef()) {
				spielfeld.imGefaegnis();
			}
			spielfeld.updateKartenbehaelter(getBesitz());
			spielfeld.updateCurPlayer(geradeAmZugIndex);
		}
		
	}
	
	public void wuerfeln() //Ui callt das hier mit Würfelbutton
	{		
		int wurf1 = rand.nextInt(6) + 1;
		int wurf2 = rand.nextInt(6) + 1;
		spielfeld.updateDice(wurf1, wurf2);
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
				ausGef();
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
		
		positionsNotify();
		//notify UI über Wurf

		neuesFeld();		
	}
	
	public void neuesFeld() {
		switch (felder[geradeAmZug.getPos()].type()) {
		case STRASSE :
			strasse();
			break;
		case BAHN :
			bahn(1);
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
				Gemeinschaftskarte();
				break;
			case 7: //Ereigniss
			case 22:
			case 36:
				Ereigniskarte();
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
		case UNKNOWN:
			//error
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
			spielfeld.zahleMiete(s.getGehoert(), s.getMiete(s.getHauszahl()));
		}
		if (s.getGehoert()==-1) {
			spielfeld.grundstueckKaufen(geradeAmZug.getPos());
		}
		
	}
	
	public void bahn(int multiplier) {
		Bahn b = felder[geradeAmZug.getPos()].toBahnHof();
		if (b == null) {
			//error
		}
		if (b.getGehoert() > -1 && b.getHypo() == false) {
			pla[b.getGehoert()].zahlen(b.getMiete(pla[b.getGehoert()].getBahnZahl()) * multiplier, geradeAmZugIndex);
			geradeAmZug.zahlen(-1 * b.getMiete(pla[b.getGehoert()].getBahnZahl()) * multiplier, b.getGehoert());
			spielfeld.zahleMiete(b.getGehoert(), b.getMiete(pla[b.getGehoert()].getBahnZahl()) * multiplier);

		}
		if (b.getGehoert()==-1) {
			spielfeld.grundstueckKaufen(geradeAmZug.getPos());
		}
	}
	
	public void werk() {
		Werk w = felder[geradeAmZug.getPos()].toWerk();

		if (w == null) {
			//error
		}
		if (w.getGehoert() > -1 && w.getHypo() == false) {			
			
			spielfeld.zahleMieteWerk(w.getGehoert(), 80);
		}
		if (w.getGehoert()==-1) {
			spielfeld.grundstueckKaufen(geradeAmZug.getPos());
		}
	}
	
	public void werkMiete() {
		int w1 = rand.nextInt(6) + 1;
		int w2 = rand.nextInt(6) + 1;
		spielfeld.updateDice(w1, w2);
		int wurf = w1 + w2;
		Werk w = felder[geradeAmZug.getPos()].toWerk();
		spielfeld.zahleMiete(w.getGehoert(), (int) (w.getMiete() * (0.5 + (pla[w.getGehoert()].getWerkZahl()/2))) * wurf);
		
 		pla[w.getGehoert()].zahlen((int) (w.getMiete() * (0.5 + (pla[w.getGehoert()].getWerkZahl()/2))) * wurf, geradeAmZugIndex);
		geradeAmZug.zahlen(-1 * (int) (w.getMiete() * (0.5 + (pla[w.getGehoert()].getWerkZahl()/2))) * wurf, w.getGehoert());
	}
	
	public void kaufen() {
			Feld f = felder[geradeAmZug.getPos()];
			if (geradeAmZug.getGeld() - f.getPreis() > -1) {
				geradeAmZug.erhalten(geradeAmZug.getPos());
				geradeAmZug.zahlen(-1 * f.getPreis(), -1);
				f.setGehoert(geradeAmZugIndex);
				geradeAmZug.aendereGesammtscore(f.getPreis());
				
				if (f.type() == TYPE.BAHN) {
					geradeAmZug.bahnZahlaendern(1);
				} else if (f.type() == TYPE.WERK) {
					geradeAmZug.werkZahlaendern(1);
				}
				
				spielfeld.updateKartenbehaelter(geradeAmZug.getBesitz());
			} else {
				//Info du hast zu wenig geld
			}
	}
	
	public boolean hypothekAufnehmen(int position) {
		Feld f = felder[position];
		
		if (f.getHypo() == false) {
			f.setHypo(true);
			geradeAmZug.zahlen(f.getHypothekwert(), -1);
			return true;
		}
		else {
			//error an Ui
			return false;
		}
	}
	
	public boolean hypothekBezahlen(int position) {
		Feld f = felder[position];
		if (f.getHypo() == true && geradeAmZug.getGeld() - f.getHypothekwert() * 1.1 > -1) {
			f.setHypo(false);
			geradeAmZug.zahlen((int) (-1 * f.getHypothekwert()*1.1), -1);
			return true;
		}
		else {
			//error an Ui
			return false;
		}
	}
	
	public boolean hausBauen(int pos) {
		if (pos < 0) {
			System.out.println("Hier kann kein Haus gebaut werden");
			return false;
		}
		Strasse s = felder[pos].toStrasse();
		if (s.hausbauCheck(felder) && geradeAmZug.getGeld() - s.getHauskosten() * 1000 > -1 && geradeAmZug.getBesitz().contains(pos) && hausBauen) {
			s.hausBauen();
			System.out.println("Hauskosten" + s.getHauskosten());
			geradeAmZug.zahlen(-1 * (s.getHauskosten()+1) * 1000, -1);
			geradeAmZug.aendereGesammtscore(s.getHauskosten());
			if (s.getHauszahl() == 4) {
				geradeAmZug.addToHausCounter(-4);
				geradeAmZug.addToHotelCounter(1);
			} else {
				geradeAmZug.addToHausCounter(1);
			}
			
			return true;
		}
		else {

			return false;
		}
	}
	
	public void hausVerkaufen(int pos) {
		if (pos < 0) {
			System.out.println("Hier kann kein Haus verkauft werden");
			return;
		}
		Strasse s = felder[pos].toStrasse();
		if (s.hausbauCheck(felder) && s.getHauszahl() > 0&& geradeAmZug.getBesitz().contains(pos) && hausBauen) {
			s.hausVerkaufen();
			geradeAmZug.zahlen(s.getHauskosten() * 1000, -1);
			geradeAmZug.aendereGesammtscore(-1 * s.getHauskosten());
			if (s.getHauszahl() == 5) {
				geradeAmZug.addToHausCounter(4);
				geradeAmZug.addToHotelCounter(-1);
			} else {
				geradeAmZug.addToHausCounter(-1);
			}
			//Notify UI
		}
		else {
			//error an UI
		}
	}
	
	public void insGef() {
		geradeAmZug.setGef(true);
		geradeAmZug.setGefZahl(3);
		spielfeld.insGef(geradeAmZugIndex);
		geradeAmZug.setPos(-1);
		positionsNotify();
	}
	
	public void freikaufen() {

		if (geradeAmZug.getGef()) {
			geradeAmZug.zahlen(-1 * loader.getGefaengniskosten(), -1);
			ausGef();
			spielfeld.enableDice();

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
    
    public void ausGefFreiKarte() {
    	if (geradeAmZug.gefFreiKrate() > 0) {
    		if (geradeAmZug.getGef()) {
    			geradeAmZug.addToGefFreiKarte(-1);
       			ausGef();
    		} else {
    			//error (nicht im Gef)
    		}
    	} else {
    		//error (besitzt Karte nicht)
    	}
    }
    
    private void ausGef() {
    	geradeAmZug.setGef(false);
    	geradeAmZug.setPos(10);
    	spielfeld.ausGef(geradeAmZugIndex);
    	positionsNotify();
    }
    
    //
    //Tausch
    //
    
	
    public void tauschen(Tausch t) {
		if (tauschCheck(t)) {
			pla[t.getPlayerVon()].zahlen(t.getGeldZu(), t.getPlayerZu());
			pla[t.getPlayerZu()].zahlen(t.getGeldVon(), t.getPlayerVon());
			
			pla[t.getPlayerVon()].addToGefFreiKarte(t.getGefFreiKartenZu());
			pla[t.getPlayerZu()].addToGefFreiKarte(t.getGefFreiKartenVon());
			
			for ( int x : t.getHaeuserZu()) {
				pla[t.getPlayerVon()].erhalten(x);
				pla[t.getPlayerZu()].hergeben(x);
				
				felder[x].setGehoert(t.getPlayerVon());
				
				pla[t.getPlayerVon()].aendereGesammtscore(felder[x].getPreis());
				pla[t.getPlayerZu()].aendereGesammtscore(-1 * felder[x].getPreis());
				
				if (felder[x].type() == TYPE.BAHN) {
					pla[t.getPlayerVon()].bahnZahlaendern(1);
					pla[t.getPlayerZu()].bahnZahlaendern(-1);
				} else if (felder[x].type() == TYPE.WERK) {
					pla[t.getPlayerVon()].werkZahlaendern(1);
					pla[t.getPlayerZu()].werkZahlaendern(-1);
				}
			}
			
			for ( int x : t.getHaeuserVon()) {
				pla[t.getPlayerZu()].erhalten(x);
				pla[t.getPlayerVon()].hergeben(x);
				
				felder[x].setGehoert(t.getPlayerZu());
				
				pla[t.getPlayerZu()].aendereGesammtscore(felder[x].getPreis());
				pla[t.getPlayerVon()].aendereGesammtscore(-1 * felder[x].getPreis());
				
				if (felder[x].type() == TYPE.BAHN) {
					pla[t.getPlayerZu()].bahnZahlaendern(1);
					pla[t.getPlayerVon()].bahnZahlaendern(-1);
				} else if (felder[x].type() == TYPE.WERK) {
					pla[t.getPlayerZu()].werkZahlaendern(1);
					pla[t.getPlayerVon()].werkZahlaendern(-1);
				}
			}			
		} else {
			//tausch Fehlerhaft
		}
	}
	
	public boolean tauschCheck(Tausch t) {
		if (pla[t.getPlayerVon()].getGeld() < t.getGeldVon()) {
			return false;
		}
		if (pla[t.getPlayerZu()].getGeld() < t.getGeldZu()) {
			return false;
		}
		if (pla[t.getPlayerVon()].gefFreiKrate() < t.getGefFreiKartenVon()) {
			return false;
		}
		if (pla[t.getPlayerZu()].gefFreiKrate() < t.getGefFreiKartenZu()) {
			return false;
		}
		
		for (int i = 0; i < t.getHaeuserVon().length; i++) {
			if (pla[t.getPlayerVon()].getBesitz().contains(t.getHaeuserVon()[i]) == false) {
				return false;
			}
		}
		for (int i = 0; i < t.getHaeuserZu().length; i++) {
			if (pla[t.getPlayerZu()].getBesitz().contains(t.getHaeuserZu()[i]) == false) {
				return false;
			}
		}
		return true;
	}
    
    //
    //Ereigniskarten
    //
    
    private void Ereigniskarte() {
    	int kartenID = rand.nextInt(16);
    	if (benutzteEreigniskarten.size() < 16) {
    		while (benutzteEreigniskarten.contains(kartenID)) {
    			kartenID = rand.nextInt(16);
        	}
    	} else {
    		benutzteEreigniskarten.clear();
    	}
    	
    	benutzteEreigniskarten.add(kartenID);
    	
    	cardId = kartenID;
    	
    	spielfeld.showEKarte(kartenID);
    }
    
    public void resumeECard() {
     	switch (cardId) {
    	case 0:   //"Gehe in das Gefängnis. Begib dich dorthin. Gehe nicht über Los./nZiehe nicht DM 4000 ein.	"	
    		insGef();
    		break;
    	case 1: //"Du kommst aus dem Gefängnis./nDiese Karte muss behalten werden, bis sie gebraucht oder verkauft wird."
    		geradeAmZug.addToGefFreiKarte(1);
    		//Notfiy UI
    		break;
    	case 2: //"Rücke vor bis zum Opernplatz./nWenn Du über Los kommst ziehe DM 4000 ein."
    		if (geradeAmZug.getPos() > 23) {
    			geradeAmZug.zahlen(loader.getUeberlosGeld(), -1);
    		}
    		geradeAmZug.setPos(24);
    		neuesFeld();
    		positionsNotify();
    		break;
    	case 3: //"Gehe 3 Felder zurück"
    		geradeAmZug.bewegen(-3);
    		neuesFeld();
    		positionsNotify();
    		break;
    	case 4: //"Rücke vor bist zum nächsten Bahnhof. Der Eigentümer erhält das doppelte der normalen Miete./nHat noch kein Spieler einen Bahnhof, so kann er ihn von der Bank kaufen."
    		if (geradeAmZug.getPos() < 35) {
    			if (geradeAmZug.getPos() < 25) {
        			if (geradeAmZug.getPos() < 15) {
            			if (geradeAmZug.getPos() < 5) {
            				geradeAmZug.setPos(5);
                			bahn(2);
            			} else {
            				geradeAmZug.setPos(15);
                			bahn(2);
            			}
        			} else {
        				geradeAmZug.setPos(25);
            			bahn(2);
        			}
    			} else {
    				geradeAmZug.setPos(35);
        			bahn(2);
    			}
    		} else {
    			geradeAmZug.setPos(5);
    			bahn(2);
    		}
    		positionsNotify();
    		break;
    	case 5: //"Mache einen Ausflug nach dem Süd-Bahnhof,/nund wenn Du über Los kommst, ziehe DM 4000 ein."
    		if (geradeAmZug.getPos() > 4) {
    			geradeAmZug.zahlen(loader.getUeberlosGeld(), -1);
    		}
      		geradeAmZug.setPos(5);
    		neuesFeld();
    		positionsNotify();
    		break;
    	case 6: //"Rücke vor bis zur Seestrasse./nWenn Du über Los kommst, ziehe DM 4000 ein."
    		if (geradeAmZug.getPos() > 10) {
    			geradeAmZug.zahlen(loader.getUeberlosGeld(), -1);
    		}
    		geradeAmZug.setPos(11);
    		neuesFeld();
    		positionsNotify();
    		break;
    	case 7: //"Rücke bis auf Los vor."
    		geradeAmZug.setPos(0);
    		geradeAmZug.zahlen(loader.getUeberlosGeld(), -1);
    		geradeAmZug.zahlen(loader.getZusaetzlichesAufLosGeld(), -1);
    		positionsNotify();
    		break;
    	case 8: //"Gehe zurück nach der Badstrasse."
    		geradeAmZug.setPos(1);
    		neuesFeld();
    		positionsNotify();
    		break;
    	case 9: //"Rücke vor bis zur Schlossallee."
    		geradeAmZug.setPos(39);
    		neuesFeld();
    		positionsNotify();
    		break;
    	case 10: //"Lasse alle Deine Häuser renovieren./nZahle an die Bank/nfür jedes Haus DM 500/nfür jedes Hotel DM2000"
    		geradeAmZug.zahlen(-1 * ((geradeAmZug.getHausCounter() * 500) + (geradeAmZug.getHotelCounter() * 2000)), -1);
    		break;
    	case 11: //"Du wurdest zum Vorstand gewählt. Zahle jedem Spieler/nDM 1000"
    		for (int i = 0; i < 4; i++) {
    			if (i != geradeAmZugIndex) {
    				pla[i].zahlen(1000, geradeAmZugIndex);
    			}
    		}
    		geradeAmZug.zahlen(-3000, 5);
    		break;
    	case 12: //"Strafe für zu schnelles fahren./nDM 300"
    		geradeAmZug.zahlen(-300, -1);
    		break;
    	case 13: //"Zahle eine Strafe von DM 200/noder nimm eine Gemeinschaftskarte."
    		//noch nicht vollständig Implementiert
    		Ereigniskarte();
    		return;
    		//später:
    		//notify UI
    		//break;
    	case 14: // "Die Bank zahlt Dir eine Dividende von/nDM 1000"
    		geradeAmZug.zahlen(1000, -1);
    		break;
    	case 15: //"Miete und Anleihzinsen werden fällig./ndie Bank zahlt Dir DM 3000"
    		geradeAmZug.zahlen(3000, -1);
    		break;
    	}
    }
    
    public void Ereigniskarte13Zahlen() {
    	geradeAmZug.zahlen(-200, -1);
    }
    public void Ereigniskarte13Karte() {
    	Gemeinschaftskarte();
    }
    
    //
    //Gemeinschaftskarten
    //
    
    private void Gemeinschaftskarte() {
    	int kartenID = rand.nextInt(16);
    	if (benutzteGemeinschaftskarten.size() < 16) {
    		while (benutzteGemeinschaftskarten.contains(kartenID)) {
    			kartenID = rand.nextInt(16);
        	}
    	} else {
    		benutzteGemeinschaftskarten.clear();
    	}
    	
    	benutzteGemeinschaftskarten.add(kartenID);
    	
    	cardId = kartenID;
    	
    	spielfeld.showGKarte(kartenID);
    }
    
    public void resumeGCard() {
    	switch (cardId) {
    	case 0: //"Zahle Schulgeld DM 3000."
    		geradeAmZug.zahlen(-3000, -1);
    		break;
    	case 1: //"Zahle an das Krankenhaus DM 2000."
    		geradeAmZug.zahlen(-2000, -1);
    		break;
    	case 2: //"Artzt-Kosten Zahle DM 1000."
    		geradeAmZug.zahlen(-1000, -1);
    		break;
    	case 3: //"Du wirst zu Strassenausbesserungsarbeiten herangezogen. Zahle für Deine Häuser und Hotels/nDM 800 je Haus/nDM 2300 je Hotel/nan die Bank."
    		geradeAmZug.zahlen(-1 * ((geradeAmZug.getHausCounter() * 800) + (geradeAmZug.getHotelCounter() * 2300)), -1);
    		break;
    	case 4: //"Gehe in das Gefängnis. Begib Dich direkt dorthin. Gehe nicht über Los./nZiehe nicht DM 4000 ein."
    		insGef();
    		break;
    	case 5: //"Du kommst aus dem Gefängnis frei./nDiese Karte muss behalten werden, bis sie gebraucht oder verkauft wird."
    		geradeAmZug.addToGefFreiKarte(1);
    		//Notfiy UI
    		break;
    	case 6: //"Die Jahresrente wird fällig Ziehe DM 2000 ein."
    		geradeAmZug.zahlen(2000, -1);
    		break;
    	case 7: //"Du erhältst auf Vorzugs-Aktien 7% Dividende/nDM 900."
    		geradeAmZug.zahlen(900, -1);
    		break;
    	case 8: //"Du hast in einem Kreuzworträtsel-Wettbewerb gewonnen/nZiehe DM 2000 ein."
    		geradeAmZug.zahlen(2000, -1);
    		break;
    	case 9: //"Rücke vor bis auf Los."
    		geradeAmZug.setPos(0);
    		geradeAmZug.zahlen(loader.getUeberlosGeld(), -1);
    		geradeAmZug.zahlen(loader.getZusaetzlichesAufLosGeld(), -1);
    		positionsNotify();
    		break;
    	case 10: //"Aus Lagerverkäufen erhältst Du/nDM 500."
    		geradeAmZug.zahlen(500, -1);
    		break;
    	case 11: //"Du hast den 2. Preis in einer Schönheitskonkurrenz gewonnen./nZiehe DM 200 ein."
    		geradeAmZug.zahlen(200, -1);
    		break;
    	case 12: //"Es ist dein Geburtstag.Ziehe von jedem Spieler DM 1000 ein."
    		for (int i = 0; i < 4; i++) {
    			if (i != geradeAmZugIndex) {
    				pla[i].zahlen(-1000, geradeAmZugIndex);
    			}
    		}
    		geradeAmZug.zahlen(3000, 5);
    		break;
    	case 13: //"Bank-Irrtum zu Deinen Gunsten/nZiehe DM 4000 ein."
    		geradeAmZug.zahlen(4000, 5);
    		break;
    	case 14: //"Einkommensteuer-Rückzahlung/nZiehe DM 400 ein."
    		geradeAmZug.zahlen(400, 5);
    		break;
    	case 15: //"Du erbst DM 2000."
    		geradeAmZug.zahlen(2000, 5);
    		break;
    	}
    }
    //
    //UI notify
    //
    
    private void spielerwechselNotify() {
    	
    }
    public void geldNotify() {
    	int max = -999999, p1 = -1, p2 = -1, p3 = -1, p4 = -1;
    	for (int i = 0; i < pla.length; i ++) {
    		if (max < pla[i].getGeld()) {
    			max = pla[i].getGeld();
    			p1 = i;    			
    		}
    	}
    	max = -999999;
    	for (int i = 0; i < pla.length; i ++) {
    		if (max < pla[i].getGeld() && (i != p1)) {
    			max = pla[i].getGeld();
    			p2 = i;    			
    		}
    	}
    	max = -999999;
    	for (int i = 0; i < pla.length; i ++) {
    		if (max < pla[i].getGeld()&& (i != p1 && i != p2)) {
    			max = pla[i].getGeld();
    			p3 = i;    			
    		}
    	}
    	max = -999999;
    	for (int i = 0; i < pla.length; i ++) {
    		if (max < pla[i].getGeld() && (i != p1 && i != p2 && i != p3)) {
    			max = pla[i].getGeld();
    			p4 = i;    			
    		}
    	}
    	
    	int[] update = new int[8];
    	update[0] = p1; 
    	update[1] = p2; 
    	update[2] = p3; 
    	update[3] = p4; 
    	
    	update[4] = pla[p1].getGeld(); 
    	update[5] = pla[p2].getGeld(); 
    	update[6] = pla[p3].getGeld(); 
    	update[7] = pla[p4].getGeld(); 
    	  	
    	spielfeld.updateStats(update);
    }
    
    public void toggleHausBauen() {
    	if (hausBauen) {
    		hausBauen = false;
    	} else {
    		hausBauen = true;
    	}
    }
    
    public boolean getHausBauen() {
    	return hausBauen;
    }
    
    private void positionsNotify() {
    	int[] i = { pla[0].getPos(), pla[1].getPos(), pla[2].getPos(), pla[3].getPos() };
    	spielfeld.figurenUpdate(i);
    }
    
    //
    //Bankrottmanagement
    //
    
    public void toggleBankrott(int pId) {
    	if (bankrott) {
    		bankrott = false;
    		//update UI
    	} else {
    		bankrott = true;
    		bankrottPlayer = pId;
    		//update UI
    	}
    }
    
    public void bankrott() {
    	for (int i : geradeAmZug.getBesitz()) {
    		pla[bankrottPlayer].erhalten(i);
    		geradeAmZug.hergeben(i);
    	}
    	pla[bankrottPlayer].zahlen(geradeAmZug.getGeld(), geradeAmZugIndex);
    	geradeAmZug.setGeld(-999999999);
    	pla[bankrottPlayer].addToGefFreiKarte(geradeAmZug.gefFreiKrate());
    	geradeAmZug.addToGefFreiKarte(-1 * geradeAmZug.gefFreiKrate());
    	imSpiel[geradeAmZugIndex] = false;
    	geldNotify();
    }
    
    public ArrayList<Integer> getBesitz() {
    	return geradeAmZug.getBesitz();
    }
    
    public int getGefZahl() {
    	return geradeAmZug.getGefZahl();
    }
}
