package org.egordorichev.lasttry.entity.entities.creature;

import org.egordorichev.lasttry.core.io.FileReader;
import org.egordorichev.lasttry.core.io.FileWriter;
import org.egordorichev.lasttry.entity.component.Component;

import java.io.IOException;

/**
 * Handles health
 */
public class HealthComponent extends Component {
	/**
	 * Current health
	 */
	public short health = 0;
	/**
	 * Max health
	 */
	public short healthMax = 0;

	/**
	 * Writes component to file
	 *
	 * @param writer File, to write
	 */
	@Override
	public void write(FileWriter writer) {
		try {
			writer.writeInt16(this.health);
			writer.writeInt16(this.healthMax);
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
			this.health = reader.readInt16();
			this.healthMax = reader.readInt16();
		} catch (IOException exception) {
			exception.printStackTrace();
		}
	}
}