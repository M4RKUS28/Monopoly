package Gamedata;          

import java.util.ArrayList;

public class Sonderfeld extends Feld{

    public Sonderfeld(String name, int pos) {
        this.name = name;
        this.pos = pos;        
        this.my_type = Feld.TYPE.SONDERFELD;

    }
    
    public String getName() {
        return name;
    }
    
    public int getPosition() {
        return pos;
    }
 
    
    
}
