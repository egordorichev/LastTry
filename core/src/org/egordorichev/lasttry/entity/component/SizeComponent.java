package org.egordorichev.lasttry.entity.component;

import org.egordorichev.lasttry.core.io.FileReader;
import org.egordorichev.lasttry.core.io.FileWriter;

import java.io.IOException;

/**
 * Handles size
 */
public class SizeComponent extends Component {
	/**
	 * The width
	 */
	public float width;
	/**
	 * The height
	 */
	public float height;

	/**
	 * Writes component to file
	 *
	 * @param writer File, to write
	 */
	@Override
	public void write(FileWriter writer) {
		try {
			writer.writeFloat(this.width);
			writer.writeFloat(this.height);
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
		try {
			this.width = reader.readFloat();
			this.height = reader.readFloat();
		} catch (IOException exception) {
			exception.printStackTrace();
		}
	}
}