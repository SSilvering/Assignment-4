package aquarium;
import java.awt.Color;

import swimmable.Fish;
import swimmable.Jellyfish;
import swimmable.Swimmable;

/**
 * This class is part of the design patterns "Decorator" which represents easy
 * access to the function that changes the color of animal. This would be
 * possible when objects implement the interface MarineAnimal.
 * 
 * @author Shai Hod, ID: 304800402
 * 
 * @see MarineAnimalDecorator
 * @see Swimmable
 * @see Fish
 * @see Jellyfish
 */

public class MarineAnimalDecorator implements MarineAnimal {

	MarineAnimal animal;
	
	public MarineAnimalDecorator(MarineAnimal animal){
		this.animal = animal;
	}
	
	@Override
	public void PaintFish(Color col) {
		animal.PaintFish(col);
	}
}
