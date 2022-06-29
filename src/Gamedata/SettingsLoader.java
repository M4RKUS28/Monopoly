package Gamedata;   

import org.json.*;
import java.util.ArrayList;

import java.util.TreeMap;
import java.util.Map;

import java.io.FileReader;
import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Paths;


// Kartenlader: Läd aus Json-Datei die Spielkarten und speichert diese in Java Objekten
//     - Durch abstrakte Spielfeld-Liste, die alle Karten ( außer Ereignis und Gemeinschaftskarten ) enthält
//      Basis Feldklasse enthält Attribut Kartentype & cast-Methoden, z.B. Karte.toBahnHof()

public class SettingsLoader
{	
    private Ereigniskarte[] ecards;
    private Gemeinschaftskarte[] gcards;
    
    // Zeiger für Arrays
    private int zeiger_ecards = 0;
    private int zeiger_gcards = 0;
    
    // Abstrakte Liste -> ohne Zeiger -> gefüllt mit von Basis Klasse Erbenden Karten
    private Feld [] felder;
    
    // Für Anzahl einer Kartenfarbe
    Map<String, Integer> colourMap;
	
	private int startGeld = 0;
	private int ueberLosGeld = 0;
	private int zusatzueberlosgeld = 0;
	private int einkommenssteuer = 0;
	private int zusatzssteuer = 0;
	private int gefaengniskosten = 0;

    private String packageName;


    public SettingsLoader(String path)
    {
        // Für Anzahl der Karten einer Farbe
        colourMap = new TreeMap<>();

        // Listen für Gemeinschafts- und Ereigniskarten
        ecards = new Ereigniskarte[16];
        gcards = new Gemeinschaftskarte[16];
        
        // Zeiger für Gemeinschafts- und Ereigniskarten
        zeiger_ecards = 0;
        zeiger_gcards = 0;
        
        // Methode_2:
        // Abstrakte Liste, gefüllt mit allen Karten -> Zugriff mit integrirtem Typecast
        felder = new Feld[40];

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
            ueberLosGeld = obj.getInt("Ueberlosgeld");
			zusatzueberlosgeld = obj.getInt("Zusaetzliches_auf_los_geld");
			einkommenssteuer = obj.getInt("Einkommensteuer");
			zusatzssteuer = obj.getInt("Zusatzsteuer");
			gefaengniskosten = obj.getInt("Gefaengniskosten");
			
			packageName = obj.getString("Titel");


            // Lade die Karten
            loadStrassenkarten(obj);
            loadBahnhofkarten(obj);
            loadInfrastrukturkarten(obj);
            loadEreigniskarten(obj);
            loadGemeinschaftskarten(obj);
            loadSonderfeldkarten(obj);
            
            for ( int i = 0; i < felder.length; i++ )
                if ( felder[i] == null ) {
                    System.out.println( "ERROR: Ungültige Liste: Feldender Eintrag für Feld " + String.valueOf(i)  );
                    
                } else if( felder[i].type() == Feld.TYPE.STRASSE && felder[i].toStrasse() != null ) {
                    String colour = felder[i].toStrasse().getFarbe();
                    if(colourMap.containsKey(colour))
                        colourMap.put( colour, colourMap.get(colour) + 1  );
                    else
                        colourMap.put( colour, 1  );
                }


        } catch(JSONException e) {
            System.out.println( "Error: " );
            e.printStackTrace();
            
        }

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
    
    private void loadStrassenkarten(JSONObject obj)
    {
        // JSON_OBJECT Strassenkarten {
        //        int Miete[6]
        //        String Name
        //        int Position
        //        int Preis
        //        int Hauskosten
        //        int Hypothekenwert
        //        String Farbe
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
            if( pos >= 0 && pos < felder.length ) {                
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
            if( pos >= 0 && pos < felder.length ) {
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
            if( pos >= 0 && pos< felder.length ) {
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

            if( zeiger_gcards < gcards.length )
                gcards[zeiger_gcards++] = ( new Gemeinschaftskarte( gcard.getInt("ID"), gcard.getString("Text") ) );
            else {
                System.out.println( "ERROR: Ungültiger Eintrag GCARD_ID: " +  gcard.getInt("ID")  );
                continue;
            }
        }

    }
    
    private void loadSonderfeldkarten(JSONObject obj)
    {
        JSONArray sonderfelder = obj.getJSONArray("Sonderfeldkarten");
        for (int i = 0; i < sonderfelder.length(); i++) {
            JSONObject sonder_card = ( JSONObject ) sonderfelder.get(i);

            int pos = sonder_card.getInt("Position");
            if( pos >= 0 && pos< felder.length ) {
                felder[pos] = new Sonderfeld(  sonder_card.getString("Name") , pos );

            } else {
                System.out.println( "ERROR: Ungültiger Eintrag: " +  sonder_card.getString("Name")  );
                continue;
            }
        }

    }
    
   public int getUeberlosGeld() {
	   return ueberLosGeld;
   }
   
   public int getZusaetzlichesAufLosGeld() {
	   return zusatzueberlosgeld;
   }
   
   public int getEinkommensteuer() {
	   return einkommenssteuer;
   }
   
   public int getZusatzsteuer() {
	   return zusatzssteuer;
   }
   
   public int getGefaengniskosten() {
	   return gefaengniskosten;
   }
   
   public String getPackageName() {
	   return packageName;
   }
   
   public Map<String, Integer> getColourMap (){
	   return colourMap;
   }
    
}

