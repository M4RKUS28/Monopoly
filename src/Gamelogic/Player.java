package Gamelogic;

import java.util.ArrayList;

public class Player {
	private int pos, geld, bahnZahl, werkZahl, index, gefZahl, gesammtscore, hausCounter, hotelCounter, gefFreiKarte;
	private ArrayList<Integer> besitz = new ArrayList<>();
	private Game game;
	private boolean imGef, bankrott;
	
	public Player(Game g, int i) {
		bahnZahl = 0;
		geld = 30000;
		this.game = g;
		this.index = i;
		
	}
	public ArrayList<Integer> getBesitz() {
		return besitz;
	}
	
	public void bewegen(int x) {
		pos += x;
	}
	public void setPos(int x) {
		pos = x;
	}
	public int getPos() {
		return pos;
	}

	public void zahlen(int x, int p) {
		geld += x;
		game.geldNotify();
		if (geld < 0 && !bankrott) {
			bankrott = true;
			game.toggleBankrott(p);
		}
		if (bankrott && geld >= 0) {
			bankrott = false;
			game.toggleBankrott(p);
		}
		
	}
	public void setGeld(int x) {
		geld = x;
	}
	public int getGeld() {
		return geld;
	}
	public int getBahnZahl() {
		return bahnZahl;
	}
	public void bahnZahlaendern(int x) {
		bahnZahl += x;
	}
	public int getWerkZahl() {
		return werkZahl;
	}
	public void werkZahlaendern(int x) {
		werkZahl += x;
	}
	public void erhalten(int pos) {
		besitz.add(pos);
	}
	public void hergeben(int pos) {
		besitz.remove(besitz.indexOf(pos));
	}
	
	public void setGef(boolean b) {
		imGef = b;
	}
	public boolean getGef() {
		return imGef;
	}
	public void rundeImGef() {
		gefZahl--;
	}
	public void setGefZahl(int x) {
		gefZahl = x;
	}
	public int getGefZahl() {
		return gefZahl;
	}
	
	public int getGesammtscore() {
		return gesammtscore;
	}
	public void setzeGesammtscore(int x) {
		gesammtscore = x;
	}
	public void aendereGesammtscore(int x) {
		gesammtscore += x;
	}
	
	public int getHotelCounter() {
		return hotelCounter;
	}
	public void addToHotelCounter(int x) {
		hotelCounter += x;
	}
	public void setHotelCounter(int x) {
		hotelCounter = x;
	}
	
	public int getHausCounter() {
		return hausCounter;
	}
	public void addToHausCounter(int x) {
		hausCounter += x;
	}
	public void setHausCounter(int x) {
		hausCounter = x;
	}
	
	public void addToGefFreiKarte(int x) {
		gefFreiKarte += x;
	}
	public int gefFreiKrate() {
		return gefFreiKarte;
	}
}
