   

public abstract class Feld {
    protected int pos, preis, hypothekwert, gehoert;
    protected boolean hypo;
    
    public abstract int getPreis();
    public abstract int getHypothekwert();
    public abstract int getGehoert();
    public abstract void setGehoert(int i);
    public abstract boolean getHypo();
    public abstract void setHypo(boolean b);
    
    public enum TYPE {
        BAHN,
        STRASSE,
        WERK,
        UNKNOWN
    }
    TYPE type = Feld.TYPE.UNKNOWN;
    
    public Bahn toBahnHof()
    {
        if(type == TYPE.BAHN)
            return (Bahn)this;
        else
            return null;
    }
    
    public Strasse toStrasse()
    {
        if(type == TYPE.BAHN)
            return (Strasse)this;
        else
            return null;
    }
    
    public Werk toWerk()
    {
        if(type == TYPE.BAHN)
            return (Werk)this;
        else
            return null;
    }
    
    
    
}
