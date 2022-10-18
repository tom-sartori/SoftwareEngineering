package generalized;

import java.util.Observable;

public class WatchObservable extends Observable {
	@Override
	public void notifyObservers(Object arg) {
		setChanged();
		super.notifyObservers(arg);
	}
}
