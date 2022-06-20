import org.json.*;
import java.util.ArrayList;

 package Gamedata;

import java.io.FileReader;
import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Paths;

public class JSON_READER
{
    // Instanzvariablen - ersetzen Sie das folgende Beispiel mit Ihren Variablen

    /*private Strasse[] strassen;
    private Bahn[] bahnen;
    private Werk[] werke;
    private ArrayList<Ereigniskarte> ecards;
    */private ArrayList<Gemeinschaftskarte> gcards;
	
	private Strasse [] strassen;
    private Bahn[] bahnen;
    private Werk[] werke;
    private Ereigniskarte[] ecards;
    private Gemeinschaftskarte[] gcards;
	private int [] felderMap;

    public JSON_READER(String path)
    {
        /*strassen = new Strasse[22];
        bahnen = new Bahn[4]; 
        werke = new Werk[2];
        ecards = new ArrayList<Ereigniskarte>();
        gcards = new Gemeinschaftskarte[24];*/
		
		strassen = new Strasse[22];
        bahnen = new Bahn[4]; 
        werke = new Werk[2];
        ecards = new Ereigniskarte[16];
        gcards = new Gemeinschaftskarte[16];
		felderMap = new int[40];
		
		
		int zeiger_strassen = 0;
		int zeiger_bahnen = 0;
		int zeiger_werke = 0;
		int zeiger_ecards = 0;
		int zeiger_gcards = 0;



        int startGeld = 0;
        int ueberlosGeld = 0;

        path = "E:/INFO ZEUG/Test/json/cards.json";
        String content = "";

        try {
            content = new String( Files.readAllBytes( Paths.get( path )  ) );

        } catch(IOException e) {
            e.printStackTrace();
        }

        try {
            JSONObject obj = new JSONObject(content);
            System.out.println("Titel: " + obj.getString("Titel")) ;

            startGeld = obj.getInt("Startgeld");
            ueberlosGeld = obj.getInt("Ueberlosgeld");

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

    public ArrayList<Strasse> getStrassenList()
    {
        return strassen;
    }

    public ArrayList<Bahn> getBahnenList()
    {
        return bahnen;
    }

    public ArrayList<Werk> getWerkeList()
    {
        return werke;
    }

    public ArrayList<Ereigniskarte> getEcardsList()
    {
        return ecards;
    }

    public ArrayList<Gemeinschaftskarte> getGcardsList()
    {
        return gcards;
    }

    private void loadStrassenkarten(JSONObject obj)
    {
        JSONArray strassenkarten = obj.getJSONArray("Strassenkarten");
        for (int i = 0; i < strassenkarten.length(); i++) {
            JSONObject strasse = ( JSONObject ) strassenkarten.get(i);
            int mietenList[] = new int[6];

            JSONArray mieten = strasse.getJSONArray("Mietkosten");
            if( mieten.length() != 6 ) {
                System.out.println( "ERROR: Ungültiger Eintrag: " +  strasse.getString("Name")  );
                return;
            }

            /*            System.out.println("\n" );
               System.out.println( "Name: " +   strasse.getString("Name")    );
               System.out.println( "Position: " +   strasse.getInt("Position")    );
                System.out.println( "Preis: " +   strasse.getInt("Preis")    );
                System.out.println( "Hauskosten: " +   strasse.getInt("Hauskosten")    );
            */    System.out.println( "Hypothekenwert: " +   strasse.getInt("Hypothekenwert")    );

            for( int m = 0; m < mieten.length(); m++) {
                mietenList[m] = (int) mieten.get(m);
                //        System.out.println( "miete: " +  mietenList[m]    );

            }

            //int pos, int preis, int hauskosten, int hypothekwert, int miete[], ArrayList<Integer> nachbarn
			int pos = getInt("Position") /*OPERATION um aus pos index zu machen*/;
			
			/*if( pos < 0 || pos >= strassen.length() ) {
				System.out.println( "ERROR: "  );
				continue;
			}
			strassen[pos] = new Strasse(  strasse.getString("Name") , pos, strasse.getInt("Preis"), strasse.getInt("Hauskosten"), strasse.getInt("Hypothekenwert"), mietenList, new ArrayList<Integer>() );
			
			*/
			
			//
			if( zeiger_strassen < strassen.length && pos >= 0 && pos < felderMap.length )
				felderMap[pos] = zeiger_strassen;
				strassen[zeiger_strassen++] = ( new Strasse(  strasse.getString("Name") , pos, strasse.getInt("Preis"), strasse.getInt("Hauskosten"), strasse.getInt("Hypothekenwert"), mietenList, new ArrayList<Integer>() ) );
			else {
				
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

            /*  System.out.println("\n" );
              System.out.println( "Name: " +   hof.getString("Name")    );
              System.out.println( "Position: " +   hof.getInt("Position")    );
               System.out.println( "Preis: " +   hof.getInt("Preis")    );
            */   System.out.println( "Hypothekenwert: " +   hof.getInt("Hypothekenwert")    );

            for( int m = 0; m < mieten.length(); m++) {
                mietenList[m] = (int) mieten.get(m);
                //      System.out.println( "miete: " +  mietenList[m]    );

            }

            // public Bahn(String name, int pos, int preis, int hypothekwert,int miete[]) {
			if( zeiger_bahnen < bahnen.length && hof.getInt("Position") >= 0 && hof.getInt("Position") < felderMap.length )
				felderMap[hof.getInt("Position")] = zeiger_strassen;
				bahnen[zeiger_bahnen++] = ( new Bahn(  hof.getString("Name") , hof.getInt("Position"), hof.getInt("Preis"), hof.getInt("Hypothekenwert"), mietenList ) );
			else {
				
			}
        }

    }

    private void loadInfrastrukturkarten(JSONObject obj)
    {
        JSONArray infrastrukturkarten = obj.getJSONArray("Infrastrukturkarten");
        for (int i = 0; i < infrastrukturkarten.length(); i++) {
            JSONObject werk = ( JSONObject ) infrastrukturkarten.get(i);

            /* System.out.println("\n" );
             System.out.println( "Name: " +   werk.getString("Name")    );
             System.out.println( "Position: " +   werk.getInt("Position")    );
            System.out.println( "Preis: " +   werk.getInt("Preis")    );
            System.out.println( "Hypothekenwert: " +   werk.getInt("Hypothekenwert")    );
            */System.out.println( "Mietkosten: " +   werk.getInt("Mietkosten")    );

            //public werk(String name, int pos, int preis, int hauskosten,int hypothekwert,int miete) {
			if( zeiger_werke < werke.length && v.getInt("Position") >= 0 && werk.getInt("Position") < felderMap.length )
				felderMap[werk.getInt("Position")] = zeiger_strassen;
				werke[zeiger_werke++] = ( new Werk(  werk.getString("Name") , werk.getInt("Position"), werk.getInt("Preis"), -1  , werk.getInt("Hypothekenwert"), werk.getInt("Mietkosten") ) );
			else {
				
			}

        }

    }

    private void loadEreigniskarten(JSONObject obj)
    {
        JSONArray ereigniskarten = obj.getJSONArray("Ereigniskarten");
        for (int i = 0; i < ereigniskarten.length(); i++) {
            JSONObject ecard = ( JSONObject ) ereigniskarten.get(i);

            /* System.out.println("\nE-Karte" );
              System.out.println( "Text: " +   ecard.getString("Text")    );
            */  System.out.println( "ID: " +   ecard.getInt("ID")    );
			if( zeiger_werke < ecards.length )
				ecards[zeiger_werke++] = ( new Ereigniskarte( ecard.getInt("ID"), ecard.getString("Text") ) );
			else {
				
			}
        }

    }

    private void loadGemeinschaftskarten(JSONObject obj)
    {
        JSONArray gemeinschaftskarten = obj.getJSONArray("Gemeinschaftskarten");
        for (int i = 0; i < gemeinschaftskarten.length(); i++) {
            JSONObject gcard = ( JSONObject ) gemeinschaftskarten.get(i);

            /*  System.out.println("\nG-Karte" );
               System.out.println( "Text: " +   gcard.getString("Text")    );
            */   System.out.println( "ID: " +   gcard.getInt("ID")    );
			if( zeiger_werke < werke.length )
				gcards[zeiger_werke++] = ( new Gemeinschaftskarte( gcard.getInt("ID"), gcard.getString("Text") ) );
			else {
				
			}
        }

    }

}
