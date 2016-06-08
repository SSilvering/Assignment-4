package aquarium;
import java.awt.Color;

import swimmable.Swimmable;

/**
 * 
 * @author Shai Hod, ID: 304800402
 *
 */

public class MarineAnimalDecorator implements MarineAnimal {

	MarineAnimal animal;
	
	public MarineAnimalDecorator(MarineAnimal animal){
		this.animal = animal;
	}
	
	@Override
	public void PaintFish(Color col) {
		((Swimmable)animal).setColor(col);
	}
}
