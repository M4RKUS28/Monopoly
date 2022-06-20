   package Gamedata;   

import java.util.ArrayList;

public class Strasse extends Feld{
    private int hauskosten, hauszahl;
    private int[] miete = new int[6];
    private String farbe;
    private ArrayList<Integer> nachbarn = new ArrayList<>();

    
    public Strasse(String name, int pos, int preis, int hauskosten, int hypothekwert, int miete[], ArrayList<Integer> nachbarn, String farbe) {
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
        
        this.type = Feld.TYPE.STRASSE;

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
    public boolean hausbauCheck(Strasse[] strassen, int[] felderMap) {
        int x = nachbarn.size();
        int zaehler = 0;
        for (int i = 0; i < x; i++) {
            if (strassen[felderMap[nachbarn.get(i)]].hauszahl == this.hauszahl || strassen[felderMap[nachbarn.get(i)]].hauszahl == this.hauszahl - 1) {
                zaehler++;
            }
        }
        if (zaehler == x && hauszahl < 6) {
            return true;
        }
        return false;
    }
    public boolean hausVerkaufCheck(Strasse[] strassen, int[] felderMap) {
        int x = nachbarn.size();
        int zaehler = 0;
        for (int i = 0; i < x; i++) {
            if (strassen[felderMap[nachbarn.get(i)]].hauszahl == this.hauszahl || strassen[felderMap[nachbarn.get(i)]].hauszahl == this.hauszahl + 1) {
                zaehler++;
            }
        }
        if (zaehler == x && hauszahl < 6) {
            return true;
        }
        return false;
    }
}
