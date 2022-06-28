   package Gamedata;   

import java.util.ArrayList;

public class Strasse extends Feld{

    public Strasse(String name, int pos) {
        this.name = name;
        this.pos = pos;        
        this.type = Feld.TYPE.SONDERFELD;

    }
    
    public String getName() {
        return name;
    }
    
    public int getPosition() {
        return pos;
    }
 
    
    
}
