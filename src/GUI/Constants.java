package GUI;

import java.awt.Color;
import java.util.HashMap;

public class Constants {
	public static int screenWidth;
	public static int screenHeight;
	public static int cardWidth;
	public static int cardHeight;
	public static int figureSize;
	public static HashMap<String, Color> colors = new HashMap<>();
	public static HashMap<String, String> fonts = new HashMap<>();
	public static int smallestStreetFontSize;

	public Constants() {
		createColors();
		smallestStreetFontSize = 0;
		
		System.out.println(figureSize);
	}

	public static int getScreenWidth() {
		return screenWidth;
	}

	public static void setScreenWidth(int screenWidth) {
		Constants.screenWidth = screenWidth;
	}

	public static int getScreenHeight() {
		return screenHeight;
	}

	public static void setScreenHeight(int screenHeight) {
		Constants.screenHeight = screenHeight;
	}

	public static int getCardWidth() {
		return cardWidth;
	}

	public static void setCardWidth(int cardWidth) {
		Constants.cardWidth = cardWidth;
	}

	public static int getCardHeight() {
		return cardHeight;
	}

	public static void setCardHeight(int cardHeight) {
		Constants.cardHeight = cardHeight;
	}

	public static int getFigureSize() {
		return figureSize;
	}

	public static void setFigureSize(int figureSize) {
		Constants.figureSize = figureSize;
	}
	
	public static void createFonts() {
		fonts.put("straßenname", String.valueOf((int)(Constants.cardWidth/6)));
		fonts.put("los", String.valueOf((int)(Constants.cardHeight/3)));

	}
	
	public static int getSmallestStreetFontSize() {
		return Constants.smallestStreetFontSize;
	}

	public static void setSmallestStreetFontSize(int size) {
		Constants.smallestStreetFontSize = Math.min(size, Constants.smallestStreetFontSize);
	}

	protected void createColors() {
		colors.put("braun", new Color(0x894900));
		colors.put("blau_hell", new Color(0x56C1FF));
		colors.put("pink", new Color(0xD41876));
		colors.put("orange", new Color(0xFEAE00));
		colors.put("rot", new Color(0xEE220C));
		colors.put("gelb", new Color(0xFFF056));
		colors.put("gruen", new Color(0x61D836));
		colors.put("blau", new Color(0x0076BA));
		colors.put("board", new Color(0xB2DAAD));
		colors.put("hintergrund", new Color(0x92B28E));
		colors.put("rosa", new Color(0xFFCACB));
		colors.put("Erblau", new Color(0xBAF2FF));
		colors.put("grau", new Color(213, 213, 213, 100));
		colors.put("Prot", new Color(0xB51700));
		colors.put("Pblau", new Color(0x004D80));
		colors.put("Pgrün", new Color(0x017100));
		colors.put("Pgelb", new Color(0xF27200));
	}
}
