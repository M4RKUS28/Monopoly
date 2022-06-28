   package Gamedata;   

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
		SONDERFELD,
        UNKNOWN
    }
    TYPE my_type = Feld.TYPE.UNKNOWN;
    
    public Bahn toBahnHof()
    {
        if(my_type == TYPE.BAHN)
            return (Bahn)this;
        else
            return null;
    }
    
    public Strasse toStrasse()
    {
        if(my_type == TYPE.BAHN)
            return (Strasse)this;
        else
            return null;
    }
    
    public Werk toWerk()
    {
        if(my_type == TYPE.BAHN)
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
	
	
    
    
}
