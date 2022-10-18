package old;

public interface Subject {
	// tell the old.Subject you are interested in changes
	public void registerInterest(Observer obs);
}
