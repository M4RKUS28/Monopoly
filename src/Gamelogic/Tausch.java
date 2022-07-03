package Gamelogic;

public class Tausch {
	private int playerVon, playerZu, geldVon, geldZu, gefFreiKartenVon, gefFreiKartenZu;
	private int[] haeuserVon, haeuserZu;
	
	public Tausch(int playerVon, int playerZu, int geldVon, int geldZu, int gefFreiKartenVon, int gefFreiKartenZu, int[] haeuserVon, int[] haeuserZu) {
		this.playerVon = playerVon;
		this.playerZu = playerZu;
		this.geldVon = geldVon;
		this.geldZu = geldZu;
		this.gefFreiKartenVon = gefFreiKartenVon;
		this.gefFreiKartenZu = gefFreiKartenZu;
		this.haeuserVon = haeuserVon;
		this.haeuserZu = haeuserZu;
	}
	
	public int getPlayerVon () {
		return playerVon;
	}
	public int getPlayerZu () {
		return playerZu;
	}
	public int getGeldVon () {
		return geldVon;
	}
	public int getGeldZu () {
		return geldZu;
	}
	public int getGefFreiKartenVon () {
		return gefFreiKartenVon;
	}
	public int getGefFreiKartenZu () {
		return gefFreiKartenZu;
	}
	public int[] getHaeuserVon () {
		return haeuserVon;
	}
	public int[] getHaeuserZu () {
		return haeuserZu;
	}
}
