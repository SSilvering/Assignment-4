package aquarium;

import swimmable.Swimmable;

/**
 * This class represents an animal's hunger state.
 * 
 * @author Shai Hod, ID: 304800402
 */

public class Hungry implements HungerState {

	@Override
	public void action(Swimmable animal) {
		animal.setHungerState(this);		
	}
	
	public String toString(){
		return "Hungry";
	}
}
