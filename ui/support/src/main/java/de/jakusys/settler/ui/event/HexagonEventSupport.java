package de.jakusys.settler.ui.event;

import de.jakusys.util.event.AbstractEventSupport;

/**
 * 
 * 
 * @author Jakob Külzer
 * 
 */
public class HexagonEventSupport extends
		AbstractEventSupport<HexagonEvent, HexagonEventListener> {

	@Override
	protected void fireEvent(HexagonEventListener listener, HexagonEvent event) {
		switch (event.getType()) {
		case CLICK:
			listener.hexagonSelected(event);
			break;
		case HOVER:
			listener.hexagonHovered(event);
			break;
		}
	}

}
