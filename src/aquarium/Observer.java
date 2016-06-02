package aquarium;

/**
 * This interface is designed to be a common platform to inform anything by
 * several entities, as a listener. Entities will update listeners about their
 * situation, and it will act accordingly.
 * 
 * @author Shai Hod, ID: 304800402
 * @see swimmable.Fish
 * @see swimmable.Jellyfish
 */
public interface Observer {
	public void notify(String msg);
}
