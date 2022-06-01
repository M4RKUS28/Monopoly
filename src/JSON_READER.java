import org.json.*;
import java.util.ArrayList;

import java.io.FileReader;
import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Paths;

public class JSON_READER
{
    // Instanzvariablen - ersetzen Sie das folgende Beispiel mit Ihren Variablen
    private ArrayList<String> x;

    /**
     * Konstruktor für Objekte der Klasse Test
     */
    public JSON_READER()
    {
        String path = "./json/cards.json";
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
                System.out.println("Name: " + arr.get(i).getString("Name"));
                x.add( arr.get(i).getString("Name") );
            }
            
            JSONArray bahnhofkarten = obj.getJSONArray("Bahnhofkarten");
            JSONArray infrastrukturkarten = obj.getJSONArray("Infrastrukturkarten");
            JSONArray ereigniskarten = obj.getJSONArray("Ereigniskarten");
            JSONArray gemeinschaftskarten = obj.getJSONArray("Gemeinschaftskarten");

            for (int i = 0; i < arr.length(); i++) {

            }

        } catch(JSONException e) {
            e.printStackTrace();
        }

    }

}
