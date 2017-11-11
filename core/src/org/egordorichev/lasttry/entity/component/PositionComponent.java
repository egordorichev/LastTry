package org.egordorichev.lasttry.entity.component;

import org.egordorichev.lasttry.core.io.FileReader;
import org.egordorichev.lasttry.core.io.FileWriter;

import java.io.IOException;

/**
 * Simple position component
 */
public class PositionComponent extends Component {
	/**
	 * X coordinate
	 */
	public float x;
	/**
	 * Y coordinate
	 */
	public float y;

	/**
	 * Writes component to file
	 *
	 * @param writer File, to write
	 */
	@Override
	public void write(FileWriter writer) {
		try {
			writer.writeFloat(this.x);
			writer.writeFloat(this.y);
		} catch (IOException exception) {
			exception.printStackTrace();
		}
	}

	/**
	 * Loads component from a file
	 *
	 * @param reader File with component
	 */
	@Override
	public void load(FileReader reader) {

	}
}