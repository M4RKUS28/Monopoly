package Gamedata;       
  


public class Bahn extends Feld{
    private int[] miete = new int[4];

    public Bahn(String name, int pos, int preis, int hypothekwert,int miete[]) {
        this.name = name;
    	this.pos = pos;
        this.preis = preis;
        this.hypothekwert = hypothekwert;
        this.miete = miete;
        this.name = name;

        gehoert = -1;
        hypo = false;
        
        this.my_type = Feld.TYPE.BAHN;

    }
    
    public int getPreis() {
        return preis;
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
    public boolean getHypo() {
        return hypo;
    }
    public void setGehoert(int i) {
        gehoert = i;
    }
    public void setHypo(boolean b) {
        hypo = b;
    }
}
