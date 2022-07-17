package Gamedata;          

import java.util.ArrayList;

public class Strasse extends Feld{
    private int hauskosten, hauszahl;
    private int[] miete = new int[6];
    private String farbe;
    private ArrayList<Integer> nachbarn = new ArrayList<>();
    
    public Strasse(String name, int pos, int preis, int hauskosten, int hypothekwert, int miete[], ArrayList<Integer> nachbarn, String farbe) {
        this.name = name;
    	this.farbe = farbe;
        this.pos = pos;
        this.preis = preis;
        this.hauskosten = hauskosten;
        this.hypothekwert = hypothekwert;
        this.miete = miete;
        this.nachbarn = nachbarn;
        gehoert = -1;
        hauszahl = 0;
        hypo = false;
        
        this.my_type = Feld.TYPE.STRASSE;

    }
    
    public String getFarbe() {
        return farbe;
    }
    
    public int getPreis() {
        return preis;
    }
    public int getHauskosten() {
        return hauskosten;
    }
    public int getHypothekwert() {
        return hypothekwert;
    }
    public int getMiete(int i) {
        return miete[i];
    }
    public int getGehoert() {
        return gehoert;
    }
    public void setGehoert(int i) {
        gehoert = i;
    }
    public int getHauszahl() {
        return hauszahl;
    }
    public void hausBauen() {
        hauszahl++;
    }
    public void hausVerkaufen() {
        hauszahl--;
    }
    public boolean getHypo() {
        return hypo;
    }
    public void setHypo(boolean b) {
        hypo = b;
    }
    public boolean hausbauCheck(Feld[] felder) {
    	
    	ArrayList<Feld> gleichfarbig = new ArrayList<Feld>();
    	int kleinsteHauszahl = 100;
    	for (int i = 0; i < felder.length; i++) {
    		if ((felder[i].type()==Feld.TYPE.STRASSE)&&(felder[i].toStrasse().getFarbe().equals(this.farbe) && felder[i].getPos() != this.pos && felder[i].gehoert == this.gehoert)) {
    			gleichfarbig.add(felder[i]);
    		}
    	}
    	System.out.println("Haus " + gleichfarbig.size());
    	for (int i = 0; i < gleichfarbig.size(); i++) {
    		if (gleichfarbig.get(i).toStrasse().getHauszahl() < kleinsteHauszahl) {
    			kleinsteHauszahl = gleichfarbig.get(i).toStrasse().getHauszahl();
    		}
    	}
    	
    	if (this.getHauszahl() <= kleinsteHauszahl) {
    		return true;
    	}
    	
    	return false;
    	
        
    }
    public boolean hausVerkaufCheck(Feld[] felder) {
    	int haeuser = 0, nachbarn = 0;
        for (int i = 0; i < 40; i++) {
        	if (felder[i].type() == Feld.TYPE.STRASSE && felder[i].toStrasse().getFarbe() == this.farbe && felder[i].pos != this.pos) {
        		nachbarn++;
        		haeuser += felder[i].toStrasse().getHauszahl();
        	}
        }
        
        if ((nachbarn == 0 || hauszahl >= haeuser/nachbarn) && hauszahl > 0) {
            return true;
        }
        return false;
    }
}
