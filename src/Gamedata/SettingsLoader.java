import org.json.*;
import java.util.ArrayList;


import java.io.FileReader;
import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Paths;

public class SettingsLoader
{
    // Instanzvariablen - ersetzen Sie das folgende Beispiel mit Ihren Variablen

    /*private Strasse[] strassen;
    private Bahn[] bahnen;
    private Werk[] werke;
    private ArrayList<Ereigniskarte> ecards;
    private ArrayList<Gemeinschaftskarte> gcards; */
    
    private Strasse [] strassen;
    private Bahn[] bahnen;
    private Werk[] werke;
    private Ereigniskarte[] ecards;
    private Gemeinschaftskarte[] gcards;
    private int [] felderMap;
    
    private int zeiger_strassen = 0;
    private int zeiger_bahnen = 0;
    private int zeiger_werke = 0;
    private int zeiger_ecards = 0;
    private int zeiger_gcards = 0;
    
    
    
    private Feld [] felder;
    
    

    public SettingsLoader(String path)
    {
        strassen = new Strasse[22];
        bahnen = new Bahn[4]; 
        werke = new Werk[2];
        ecards = new Ereigniskarte[16];
        gcards = new Gemeinschaftskarte[16];
        felderMap = new int[40];
        felder = new Feld[40];
        
        zeiger_strassen = 0;
        zeiger_bahnen = 0;
        zeiger_werke = 0;
        zeiger_ecards = 0;
        zeiger_gcards = 0;

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

    public Strasse [] getStrassenList()
    {
        return strassen;
    }

    public Bahn [] getBahnenList()
    {
        return bahnen;
    }

    public Werk [] getWerkeList()
    {
        return werke;
    }

    public Ereigniskarte [] getEcardsList()
    {
        return ecards;
    }

    public Gemeinschaftskarte [] getGcardsList()
    {
        return gcards;
    }
    
    public Feld [] getFelderList()
    {
        return felder;
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
                continue;
            }
            for( int m = 0; m < mieten.length(); m++)
                mietenList[m] = (int) mieten.get(m);
            
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
