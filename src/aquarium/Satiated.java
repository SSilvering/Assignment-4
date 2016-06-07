package aquarium;

import swimmable.Swimmable;

/**
 * This class represents an animal's satiated state.
 * 
 * @author Shai Hod, ID: 304800402
 */

public class Satiated implements HungerState {

	@Override
	public void action(Swimmable animal) {
		animal.setHungerState(this);		
	}
	
	public String toString(){
		return "Satiated";
	}
}
