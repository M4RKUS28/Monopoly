import org.json.*;
import java.util.ArrayList;

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
    private ArrayList<Ereigniskarte> ecards;
    private ArrayList<Gemeinschaftskarte> gcards;

    public JSON_READER(String path)
    {
        strassen = new ArrayList<Strasse>();
        bahnen = new ArrayList<Bahn>(); 
        werke = new ArrayList<Werk>();
        ecards = new ArrayList<Ereigniskarte>();
        gcards = new ArrayList<Gemeinschaftskarte>();

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

            //            System.out.println("\n" );
            //   System.out.println( "Name: " +   strasse.getString("Name")    );
            //   System.out.println( "Position: " +   strasse.getInt("Position")    );
            //    System.out.println( "Preis: " +   strasse.getInt("Preis")    );
            //    System.out.println( "Hauskosten: " +   strasse.getInt("Hauskosten")    );
            //    System.out.println( "Hypothekenwert: " +   strasse.getInt("Hypothekenwert")    );

            for( int m = 0; m < mieten.length(); m++) {
                mietenList[m] = (int) mieten.get(m);
                //        System.out.println( "miete: " +  mietenList[m]    );

            }

            //int pos, int preis, int hauskosten, int hypothekwert, int miete[], ArrayList<Integer> nachbarn
            strassen.add( new Strasse(  strasse.getString("Name") , strasse.getInt("Position"), strasse.getInt("Preis"), strasse.getInt("Hauskosten"), strasse.getInt("Hypothekenwert"), mietenList, new ArrayList<Integer>() ) );

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

            //  System.out.println("\n" );
            //  System.out.println( "Name: " +   hof.getString("Name")    );
            //  System.out.println( "Position: " +   hof.getInt("Position")    );
            //   System.out.println( "Preis: " +   hof.getInt("Preis")    );
            //   System.out.println( "Hypothekenwert: " +   hof.getInt("Hypothekenwert")    );

            for( int m = 0; m < mieten.length(); m++) {
                mietenList[m] = (int) mieten.get(m);
                //      System.out.println( "miete: " +  mietenList[m]    );

            }

            // public Bahn(String name, int pos, int preis, int hypothekwert,int miete[]) {
            bahnen.add( new Bahn(  hof.getString("Name") , hof.getInt("Position"), hof.getInt("Preis"), hof.getInt("Hypothekenwert"), mietenList ) );
        }

    }

    private void loadInfrastrukturkarten(JSONObject obj)
    {
        JSONArray infrastrukturkarten = obj.getJSONArray("Infrastrukturkarten");
        for (int i = 0; i < infrastrukturkarten.length(); i++) {
            JSONObject bahn = ( JSONObject ) infrastrukturkarten.get(i);

            // System.out.println("\n" );
            // System.out.println( "Name: " +   bahn.getString("Name")    );
            // System.out.println( "Position: " +   bahn.getInt("Position")    );
            //System.out.println( "Preis: " +   bahn.getInt("Preis")    );
            //System.out.println( "Hypothekenwert: " +   bahn.getInt("Hypothekenwert")    );
            //System.out.println( "Mietkosten: " +   bahn.getInt("Mietkosten")    );

            //public Werk(String name, int pos, int preis, int hauskosten,int hypothekwert,int miete) {
            werke.add( new Werk(  bahn.getString("Name") , bahn.getInt("Position"), bahn.getInt("Preis"), -1  , bahn.getInt("Hypothekenwert"), bahn.getInt("Mietkosten") ) );

        }

    }

    private void loadEreigniskarten(JSONObject obj)
    {
        JSONArray ereigniskarten = obj.getJSONArray("Ereigniskarten");
        for (int i = 0; i < ereigniskarten.length(); i++) {
            JSONObject ecard = ( JSONObject ) ereigniskarten.get(i);

            // System.out.println("\nE-Karte" );
            //  System.out.println( "Text: " +   ecard.getString("Text")    );
            //  System.out.println( "ID: " +   ecard.getInt("ID")    );

            ecards.add( new Ereigniskarte( ecard.getInt("ID"), ecard.getString("Text") ) );
        }

    }

    private void loadGemeinschaftskarten(JSONObject obj)
    {
        JSONArray gemeinschaftskarten = obj.getJSONArray("Gemeinschaftskarten");
        for (int i = 0; i < gemeinschaftskarten.length(); i++) {
            JSONObject gcard = ( JSONObject ) gemeinschaftskarten.get(i);

            //  System.out.println("\nG-Karte" );
            //   System.out.println( "Text: " +   gcard.getString("Text")    );
            //   System.out.println( "ID: " +   gcard.getInt("ID")    );

            gcards.add( new Gemeinschaftskarte( gcard.getInt("ID"), gcard.getString("Text") ) );
        }

    }

}
