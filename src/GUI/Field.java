package GUI;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.HashMap;

import javax.swing.JPanel;

public abstract class Field extends JPanel {
	public String name;
	public int position;
	protected HashMap<String, Color> colors = new HashMap<>();

	public Field(String name, int position) {
		this.name = name;
		this.position = position;
		this.setUpColors();
	}

	private void setUpColors() {
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
		colors.put("grau", new Color(213, 213, 213, 100));
		colors.put("Prot", new Color(0xB51700));
		colors.put("Pblau", new Color(0x004D80));
		colors.put("Pgrün", new Color(0x017100));
		colors.put("Pgelb", new Color(0xF27200));

	}
	
	public abstract void addPlayer(String color);
	public abstract void removePlayer(String color);

	protected String transformText(String text, int index) {
		String[] slices = { "<html><body><center>", "", "-<br>", "", "</center></body></html>" };
		String placeholder = "";

		if (index < text.length()) {
			slices[1] = text.substring(0, text.length() - 7);
			slices[3] = text.substring(text.length() - 7);
			;
		}
		placeholder = "";
		for (int i = 0; i < 5; i++) {
			placeholder += slices[i];
		}
		return placeholder;

	}

	protected String transformText(String text) {

		String[] slices = { "<html><body><center>", "", "-<br>", "", "</center></body></html>" };
		String placeholder = "";
		if (position == -2) {
			text = text.replace("/n", "<br>");

			placeholder = slices[0] + text + slices[4];
			return placeholder;
		}
		if (text.contains("strasse")) {
			placeholder = text.substring(text.length() - 7);

			placeholder.replace("ss", "ß");
			slices[1] = text.substring(0, text.length() - 7);
			slices[3] = placeholder;
		} else if (text.contains("bahnhof")) {
			placeholder = text.substring(text.length() - 7);

			slices[1] = text.substring(0, text.length() - 7);
			slices[3] = placeholder;
		} else if (text.contains("Strasse")) {
			placeholder = text.substring(text.length() - 7);

			placeholder.replace("ss", "ß");
			slices[1] = text.substring(0, text.length() - 7);
			slices[2] = "<br>";
			slices[3] = placeholder;
		} else if (text.contains("platz")) {
			placeholder = text.substring(text.length() - 5);

			placeholder.replace("ss", "ß");
			slices[1] = text.substring(0, text.length() - 5);
			slices[3] = placeholder;
		} else if (text.contains("allee")) {
			placeholder = text.substring(text.length() - 5);

			placeholder.replace("ss", "ß");
			slices[1] = text.substring(0, text.length() - 5);
			slices[3] = placeholder;
		} else if (text.contains("meinschaft")) {
			placeholder = text.substring(text.length() - 11);

			slices[1] = text.substring(0, text.length() - 11);
			slices[3] = placeholder;
		} else if (text.contains("feld")) {
			placeholder = text.substring(text.length() - 4);

			slices[1] = text.substring(0, text.length() - 4);
			slices[3] = placeholder;
		} else if (text.contains("lektri") || text.contains("Wasser")) {
			text = text.replace("ae", "ä");
			text = text.replace("-", "<br>-");
			placeholder = slices[0] + text + slices[4];
			return placeholder;
		} else if (text.contains("steuer")) {
			placeholder = text.substring(text.length() - 6);

			slices[1] = text.substring(0, text.length() - 6);
			slices[3] = placeholder;
		}
		placeholder = "";
		for (int i = 0; i < 5; i++) {
			placeholder += slices[i];
		}
		return placeholder;
	}
}
