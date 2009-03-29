package de.jakusys.settler.ui.event;

import java.util.EventListener;

public interface HexagonEventListener extends EventListener {

	void hexagonSelected(HexagonEvent hexagonEvent);

	void hexagonHovered(HexagonEvent hexagonEvent);

}
