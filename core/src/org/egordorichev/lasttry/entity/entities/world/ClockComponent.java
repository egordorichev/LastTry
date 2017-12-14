package org.egordorichev.lasttry.entity.entities.world;

import org.egordorichev.lasttry.core.io.FileReader;
import org.egordorichev.lasttry.core.io.FileWriter;
import org.egordorichev.lasttry.entity.component.Component;
import org.egordorichev.lasttry.util.log.Log;

/**
 * Handles time
 */
public class ClockComponent extends Component {
	/**
	 * Current second
	 */
	public float second;
	/**
	 * Current minute
	 */
	public float minute;
	/**
	 * Current hour
	 */
	public byte hour;
	/**
	 * Speed modifier (1.0 normal speed, 0.0 stop, 2.0 x2 speed)
	 */
	public float speed = 1.0f;

	/**
	 * @return True, if the night is active by this clock
	 */
	public boolean isNight() {
		return (this.hour > 19 && this.hour < 4);
	}

	@Override
	public void load(FileReader reader) {
		try {
			this.second = reader.readByte();
			this.minute = reader.readByte();
			this.hour = reader.readByte();
			this.speed = reader.readFloat();
		} catch (Exception exception) {
			exception.printStackTrace();
			Log.error("Failed to load ClockComponent");
		}
	}

	@Override
	public void write(FileWriter writer) {
		try {
			writer.writeByte((byte) this.second);
			writer.writeByte((byte) this.minute);
			writer.writeByte(this.hour);
			writer.writeFloat(this.speed);
		} catch (Exception exception) {
			exception.printStackTrace();
			Log.error("Failed to save ClockComponent");
		}
	}
}