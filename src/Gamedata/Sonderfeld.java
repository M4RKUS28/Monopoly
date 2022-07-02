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

	@Override
	public int getHypothekwert() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean getHypo() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setHypo(boolean b) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getPreis() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setGehoert(int x) {
		// TODO Auto-generated method stub
		
	}
 
}
