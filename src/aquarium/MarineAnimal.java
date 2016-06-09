package aquarium;

import java.awt.Color;

import swimmable.Fish;
import swimmable.Jellyfish;
import swimmable.Swimmable;

/**
 * This interface allows access to a shared animal color change to the classes
 * which implements it.
 * 
 * @author Shai Hod, ID: 304800402
 *
 * @see MarineAnimalDecorator
 * @see Swimmable
 * @see Fish
 * @see Jellyfish
 */

public interface MarineAnimal {
	public void PaintFish(Color col);
}
