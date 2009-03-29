package de.jakusys.settler.event;

import java.util.ArrayList;
import java.util.EventListener;
import java.util.EventObject;
import java.util.Iterator;
import java.util.List;

/**
 * Abstract base class for event support classes. This class provides services
 * for registering, unregistering and notifying event listeners. To use this
 * create a concrete subclass for your type of event and add a private instance
 * of it to the object that needs to provide event handling services and
 * delegate all calls to this instance.
 * 
 * 
 * @author Jakob Külzer
 * 
 * @param <EVENT>
 *            The Event class. Must implement {@link EventObject}.
 * @param <LISTENER>
 *            The event listener class. Must implement {@link EventListener}.
 */
public abstract class AbstractEventSupport<EVENT extends EventObject, LISTENER extends EventListener> {

	private final List<LISTENER> listeners = new ArrayList<LISTENER>();

	/**
	 * Adds the given listener to the list of listeners.
	 * 
	 * @param listener
	 */
	public void addListener(LISTENER listener) {
		listeners.add(listener);
	}

	/**
	 * Fire an event. This will notify all registered event listeners.
	 * 
	 * @param event
	 */
	public void fire(EVENT event) {
		for (Iterator<LISTENER> i = listeners.iterator(); i.hasNext();) {
			fireEvent(i.next(), event);
		}
	}

	/**
	 * Called to actually fire an event. Overwrite this to adapt to your needs.
	 * 
	 * @param listener
	 * @param event
	 */
	protected abstract void fireEvent(LISTENER listener, EVENT event);
}
