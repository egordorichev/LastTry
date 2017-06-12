package org.egordorichev.lasttry.world;

public class WorldTime {
	/**
	 * Indicates hour
	 */
	private byte hour;

	/**
	 * Indicates minute
	 */
	private byte minute;

	/**
	 * Indicates second
	 */
	private byte second;

	public WorldTime(byte hour, byte minute) {
		this.hour = hour;
		this.minute = minute;
		this.second = 0;
	}

	/**
	 * Updates the time
	 */
	public void update() {
		this.second++;

		if (this.second == 60) {
			this.second = 0;
			this.minute++;
		}

		if (this.minute == 60) {
			this.minute = 0;
			this.hour++;
		}

		if (this.hour == 24) {
			this.hour = 0;
		}
	}

	/**
	 * Returns true, if current time is day time
	 *
	 * @return if current time is day time
	 */
	public boolean isDay() {
		return (this.hour >= 4 && this.hour < 19); // TODO: move half hour
	}

	public boolean isNight() {
		return !this.isDay();
	}

	/**
	 * Returns current hour
	 *
	 * @return current hour
	 */
	public byte getHour() {
		return this.hour;
	}

	/**
	 * Sets hour
	 *
	 * @param hour
	 */
	public void setHour(byte hour) {
		this.hour = hour;
	}

	/**
	 * Returns current minute
	 *
	 * @return current minute
	 */
	public byte getMinute() {
		return this.minute;
	}

	/**
	 * Sets minute
	 *
	 * @param minute
	 */
	public void setMinute(byte minute) {
		this.minute = minute;
	}

	/**
	 * Converts time to string
	 *
	 * @param use12hoursFormat if true, am/pm will be used instead of 24-hours time
	 * @return time string
	 */
	public String toString(boolean use12hoursFormat) {
		Byte minuteByte = minute;
		String minuteString = minuteByte.toString();
		StringBuilder minuteBuilder = new StringBuilder(minuteString);
		if (minuteString.length() != 2) {
			minuteBuilder.insert(0, "0");
		}

		if (use12hoursFormat) {
			String postfix = (this.hour >= 12) ? " pm" : " am";
			int hour = (this.hour > 12) ? this.hour - 12 : this.hour;

			return hour + ":" + minuteBuilder + postfix;
		} else {
			return this.hour + ":" + minuteBuilder;
		}
	}
}