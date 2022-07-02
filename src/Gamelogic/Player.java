package Gamelogic;

import java.util.ArrayList;

public class Player {
	private int pos, geld, bahnZahl, index, gefZahl, gesammtscore;
	private ArrayList<Integer> besitz = new ArrayList<>();
	private Game game;
	private boolean imGef;
	
	public Player(Game g, int i) {
		bahnZahl = 0;
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
		if (geld < 0) {
			
			//game.bankrott(p, index);
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
}
