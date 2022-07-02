package Gamedata;          

public abstract class Feld {
    public int pos, preis, hypothekwert, gehoert;
    public boolean hypo;
    public String name;
    
    public enum TYPE {
        BAHN,
        STRASSE,
        WERK,
        SONDERFELD,
        UNKNOWN
    }
    public TYPE my_type = Feld.TYPE.UNKNOWN;
    
    public Bahn toBahnHof()
    {
        if(my_type == TYPE.BAHN)
            return (Bahn)this;
        else
            return null;
    }
    
    public Strasse toStrasse()
    {
        if(my_type == TYPE.STRASSE)
            return (Strasse)this;
        else
            return null;
    }
    
    public Werk toWerk()
    {
        if(my_type == TYPE.WERK)
            return (Werk)this;
        else
            return null;
    }
    
    public Sonderfeld toSonderFeld()
    {
        if(my_type == TYPE.SONDERFELD)
            return (Sonderfeld)this;
        else
            return null;
    }
    
    public TYPE type() {
        return my_type;
    }
    
    public abstract int getHypothekwert();
    public abstract boolean getHypo();
    public abstract int getPreis();
    
    public abstract void setHypo(boolean b);
    public abstract void setGehoert(int x);
    
    
}
