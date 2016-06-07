package aquarium;

import swimmable.Swimmable;

/**
 * This interface represents a common factor to updates animal's hunger state.
 * 
 * @author Shai Hod, ID: 304800402
 */

public interface HungerState {
	public void action(Swimmable animal);
}
