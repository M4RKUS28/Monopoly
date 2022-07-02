package Gamelogic;

public class Tausch {
	private int playerVon, playerZu, geldVon, geldZu, gefFreiKartenVon, gefFreiKartenZu;
	private int[] haeuserVon, haeuserZu;
	private int gefFreiKarte;
	
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
}
