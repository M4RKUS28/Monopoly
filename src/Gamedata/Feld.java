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
}
