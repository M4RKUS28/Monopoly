import org.json.*;
import java.util.ArrayList;


import java.io.FileReader;
import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Paths;


// Kartenlader: Läd aus Json-Datei die Spielkarten und speichert diese in Java Objekten
// Zugriff durch zwei Möglichkeiten:
// 	- Durch einzelne Listen, z.B. bahnen, mit den 4 Bahnhöfen
// 	  Zuordnung auf Spielfeld durch "Map"-Liste: Liste, die zu jedem Feld die Arrayposition der Einzelliste speichert
// 	- Durch abstrakte Spielfeld-Liste, die alle Karten ( außer Ereignis und Gemeinschaftskarten ) enthält
//	  Basis Feldklasse enthält Attribut Kartentype & cast-Methoden, z.B. Karte.toBahnHof()

public class SettingsLoader
{
	//Attribute - Kartenlisten
    private Strasse [] strassen;
    private Bahn[] bahnen;
    private Werk[] werke;
    private Ereigniskarte[] ecards;
    private Gemeinschaftskarte[] gcards;
    private int [] felderMap;
    
	// Zeiger für Arrays
    private int zeiger_strassen = 0;
    private int zeiger_bahnen = 0;
    private int zeiger_werke = 0;
    private int zeiger_ecards = 0;
    private int zeiger_gcards = 0;
    
    // Abstrakte Liste -> ohne Zeiger -> gefüllt mit von Basis Klasse Erbenden Karten
    private Feld [] felder;
    
    

    public SettingsLoader(String path)
    {
		// Einzellisten für Methode_1:
        strassen = new Strasse[22];
        bahnen = new Bahn[4]; 
        werke = new Werk[2];
		
		// Zeiger für Einzellisten
        zeiger_strassen = 0;
        zeiger_bahnen = 0;
        zeiger_werke = 0;
		
		// Map für Zuordnung
		felderMap = new int[40];

		// Listen für Gemeinschafts- und Ereigniskarten
		ecards = new Ereigniskarte[16];
        gcards = new Gemeinschaftskarte[16];
		
		// Zeiger für Gemeinschafts- und Ereigniskarten
        zeiger_ecards = 0;
        zeiger_gcards = 0;
		
		// Methode_2:
		// Abstrakte Liste, gefüllt mit allen Karten -> Zugriff mit integrirtem Typecast
		felder = new Feld[40];

		// Spiel Einstellungen
        int startGeld = 0;
        int ueberlosGeld = 0;
        String content = "";

		// Versuche das JSon File zu öffnen und Inhalt auszulesen
        try {
            content = new String( Files.readAllBytes( Paths.get( path )  ) );

        } catch(IOException e) {
            e.printStackTrace();
        }

		// Versuche JSON Objekt zu extrahieren und Titel auszulesen
        try {
            JSONObject obj = new JSONObject(content);
            System.out.println("Titel: " + obj.getString("Titel")) ;

			// Lade Spieleinstellungen
            startGeld = obj.getInt("Startgeld");
            ueberlosGeld = obj.getInt("Ueberlosgeld");

			// Lade die Karten
            loadStrassenkarten(obj);
            loadBahnhofkarten(obj);
            loadInfrastrukturkarten(obj);
            loadEreigniskarten(obj);
            loadGemeinschaftskarten(obj);

        } catch(JSONException e) {
            System.out.println( "ERRRORRRRRRRRR: "     );
            e.printStackTrace();
        }

    }

    public Strasse [] getStrassenList()
    {
		// Gib Straßenliste zurück
        return strassen;
    }

    public Bahn [] getBahnenList()
    {
		// Gib Bahnhöfeliste zurück
        return bahnen;
    }

    public Werk [] getWerkeList()
    {
		// Gib Infrastrukturkartenliste zurück
        return werke;
    }

    public Ereigniskarte [] getEcardsList()
    {
		// Gib Ereigniskartenliste zurück
        return ecards;
    }

    public Gemeinschaftskarte [] getGcardsList()
    {
		// Gib Gemeinschaftskarteliste zurück
        return gcards;
    }
    
    public Feld [] getFelderList()
    {
		// Gib abstrakte FelderListe zurück
        return felder;
    }
	
	public int [] getFelderMap()
    {
		// Gib abstrakte FelderListe zurück
        return felder;
    }
	
    private void loadStrassenkarten(JSONObject obj)
    {
		// JSON_OBJECT Strassenkarten {
		//		int Miete[6]
		//		String Name
		//		int Position
		//		int Preis
		//		int Hauskosten
		//		int Hypothekenwert
		//		String Farbe
		//}
		
		// Jsava Objekt Kunstruktor:
		// Strasse(String name, int pos, int preis, int hauskosten, int hypothekwert, int miete[], ArrayList<Integer> nachbarn, String farbe)

		
        JSONArray strassenkarten = obj.getJSONArray("Strassenkarten");
        for (int i = 0; i < strassenkarten.length(); i++) {
            JSONObject strasse = ( JSONObject ) strassenkarten.get(i);
			
			// Miete:
            int mietenList[] = new int[6];

            JSONArray mieten = strasse.getJSONArray("Mietkosten");
            if( mieten.length() != 6 ) {
                System.out.println( "ERROR: Ungültiger Eintrag: " +  strasse.getString("Name")  );
                continue;
            } else
				for( int m = 0; m < mieten.length(); m++)
					mietenList[m] = (int) mieten.get(m);
            
			//Position -> überprüfe, ob diese in Array-Range ist
            int pos = strasse.getInt("Position");
            if( zeiger_strassen < strassen.length && pos >= 0 && pos < felderMap.length ) {
                felderMap[pos] = zeiger_strassen;
				
                strassen[zeiger_strassen++] = ( new Strasse(  strasse.getString("Name") , pos, strasse.getInt("Preis"), strasse.getInt("Hauskosten"), strasse.getInt("Hypothekenwert"), mietenList, new ArrayList<Integer>(), strasse.getString("Farbe")  ) );
                felder[pos] = new Strasse(  strasse.getString("Name") , pos, strasse.getInt("Preis"), strasse.getInt("Hauskosten"), strasse.getInt("Hypothekenwert"), mietenList, new ArrayList<Integer>(), strasse.getString("Farbe") );
            } else {
                System.out.println( "ERROR: Ungültiger Eintrag: " +  strasse.getString("Name")  );
                continue;
            }
                
        }
    }

    private void loadBahnhofkarten(JSONObject obj)
    {
        JSONArray bahnhofkarten = obj.getJSONArray("Bahnhofkarten");
        for (int i = 0; i < bahnhofkarten.length(); i++) {
            JSONObject hof = ( JSONObject ) bahnhofkarten.get(i);
            int mietenList[] = new int[4];

            JSONArray mieten = hof.getJSONArray("Mietkosten");
            if( mieten.length() != 4 ) {
                System.out.println( "ERROR: Ungültiger Eintrag: " +  hof.getString("Name")  );
                return;
            }
            for( int m = 0; m < mieten.length(); m++)
                mietenList[m] = (int) mieten.get(m);
            
            int pos = hof.getInt("Position");
            if( zeiger_bahnen < bahnen.length && pos >= 0 && pos < felderMap.length ) {
                felderMap[pos] = zeiger_strassen;
                bahnen[zeiger_bahnen++] = ( new Bahn(  hof.getString("Name") , pos, hof.getInt("Preis"), hof.getInt("Hypothekenwert"), mietenList ) );
                felder[pos] = new Bahn(  hof.getString("Name") , pos, hof.getInt("Preis"), hof.getInt("Hypothekenwert"), mietenList );
            } else {
                System.out.println( "ERROR: Ungültiger Eintrag: " +  hof.getString("Name")  );
                continue;
            }
        }

    }

    private void loadInfrastrukturkarten(JSONObject obj)
    {
        JSONArray infrastrukturkarten = obj.getJSONArray("Infrastrukturkarten");
        for (int i = 0; i < infrastrukturkarten.length(); i++) {
            JSONObject werk = ( JSONObject ) infrastrukturkarten.get(i);

            int pos = werk.getInt("Position");
            if( zeiger_werke < werke.length && pos >= 0 && pos< felderMap.length ) {
                felderMap[pos] = zeiger_strassen;
                werke[zeiger_werke++] = ( new Werk(  werk.getString("Name") , pos, werk.getInt("Preis"), -1  , werk.getInt("Hypothekenwert"), werk.getInt("Mietkosten") ) );
                felder[pos] = new Werk(  werk.getString("Name") , pos, werk.getInt("Preis"), -1  , werk.getInt("Hypothekenwert"), werk.getInt("Mietkosten") );
            } else {
                System.out.println( "ERROR: Ungültiger Eintrag: " +  werk.getString("Name")  );
                continue;
            }

        }

    }

    private void loadEreigniskarten(JSONObject obj)
    {
        JSONArray ereigniskarten = obj.getJSONArray("Ereigniskarten");
        for (int i = 0; i < ereigniskarten.length(); i++) {
            JSONObject ecard = ( JSONObject ) ereigniskarten.get(i);

            if( zeiger_ecards < ecards.length )
                ecards[zeiger_ecards++] = ( new Ereigniskarte( ecard.getInt("ID"), ecard.getString("Text") ) );
            else {
                System.out.println( "ERROR: Ungültiger Eintrag ECARD_ID: " +  ecard.getInt("ID")  );
                continue;
            }
        }

    }

    private void loadGemeinschaftskarten(JSONObject obj)
    {
        JSONArray gemeinschaftskarten = obj.getJSONArray("Gemeinschaftskarten");
        for (int i = 0; i < gemeinschaftskarten.length(); i++) {
            JSONObject gcard = ( JSONObject ) gemeinschaftskarten.get(i);

            if( zeiger_gcards < werke.length )
                gcards[zeiger_gcards++] = ( new Gemeinschaftskarte( gcard.getInt("ID"), gcard.getString("Text") ) );
            else {
                System.out.println( "ERROR: Ungültiger Eintrag GCARD_ID: " +  gcard.getInt("ID")  );
                continue;
            }
        }

    }

}
