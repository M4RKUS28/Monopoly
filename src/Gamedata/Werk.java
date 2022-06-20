


public class Werk extends Feld{
    private int miete;
    private boolean hypo;
    
    public Werk(String name, int pos, int preis, int hauskosten,int hypothekwert,int miete) {
        this.pos = pos;
        this.preis = preis;
        this.hypothekwert = hypothekwert;
        this.miete = miete;
        hypo = false;
        gehoert = -1;
        
        this.type = Feld.TYPE.WERK;
    }
    
    public int getPreis() {
        return preis;
    }
    public int getHypothekwert() {
        return hypothekwert;
    }
    public int getMiete() {
        return miete;
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
