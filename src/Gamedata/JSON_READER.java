package Gamedata;

import java.util.ArrayList;

import org.json.*;

import java.io.FileReader;
import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Paths;



public class JSON_READER
{
    // Instanzvariablen - ersetzen Sie das folgende Beispiel mit Ihren Variablen

	private ArrayList<Strasse> strassen;
	private ArrayList<Bahn> bahnen;
	private ArrayList<Werk> werke;
	private ArrayList<Feld> alleFelder;
	
    /**
     * Konstruktor f�r Objekte der Klasse Test
     */
    public JSON_READER(String path)
    {
        path = "./json/cards.json";
        String content = "";
        

        try {
            content = new String( Files.readAllBytes( Paths.get( path )  ) );

        } catch(IOException e) {
            e.printStackTrace();
        }

        try {
            JSONObject obj = new JSONObject(content);
            System.out.println("Titel: " + obj.getString("Titel")) ;
            
            JSONArray strassenkarten = obj.getJSONArray("Strassenkarten");
            for (int i = 0; i < strassenkarten.length(); i++) {
				JSONObject strasse = ( JSONObject ) strassenkarten.get(i);
                System.out.println("Name: " + strasse.getString("Name")  );
				
				//MIETE:....
				int mietenList[] = new int[6];
				
				JSONArray mieten = obj.getJSONArray("Mietkosten");
				if( mieten.length() != 6 ) {
					System.out.println( "ERROR: Ungültiger Eintrag: " +  strasse.getString("Name")  );
					continue;
					//exit
				}
				
				for( int m = 0; m < mieten.length(); m++) {
					mietenList[m] = mieten[m];
				}
				
				//REST

				//int pos, int preis, int hauskosten, int hypothekwert, int miete[], ArrayList<Integer> nachbarn
                strassen.add( new Strasse(	strasse.getString("Position"), strasse.getString("Preis"), strasse.getString("Hauskosten"), strasse.getString("Hypothekenwert"), mietenList,) );
				
				
				
            }
            
            JSONArray bahnhofkarten = obj.getJSONArray("Bahnhofkarten");
            JSONArray infrastrukturkarten = obj.getJSONArray("Infrastrukturkarten");
            JSONArray ereigniskarten = obj.getJSONArray("Ereigniskarten");
            JSONArray gemeinschaftskarten = obj.getJSONArray("Gemeinschaftskarten");

            for (int i = 0; i < bahnhofkarten.length(); i++) {

            }

        } catch(JSONException e) {
            e.printStackTrace();
        }

    }

}
