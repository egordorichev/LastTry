package org.egordorichev.lasttry.core.io;

import java.io.*;

/**
 * A wrapper for DataOutputStream.
 */
public class FileWriter implements AutoCloseable {
	private DataOutputStream stream;

	public FileWriter(String path) throws IOException {
		this.stream = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(path), 32768));
	}

	/**
	 * Writes given byte to file
	 *
	 * @param value Byte to write
	 * @throws IOException If something went wrong
	 */
	public void writeByte(byte value) throws IOException {
		this.stream.writeByte(value);
	}

	/**
	 * Writes given boolean to file
	 *
	 * @param value Boolean to write
	 * @throws IOException If something went wrong
	 */
	public void writeBoolean(boolean value) throws IOException {
		this.stream.writeBoolean(value);
	}

	/**
	 * Writes given int16 to file
	 *
	 * @param value Int16 to write
	 * @throws IOException If something went wrong
	 */
	public void writeInt16(short value) throws IOException {
		this.stream.writeShort(value);
	}

	/**
	 * Writes given int32 to file
	 *
	 * @param value Int32 to write
	 * @throws IOException If something went wrong
	 */
	public void writeInt32(int value) throws IOException {
		this.stream.writeInt(value);
	}

	/**
	 * Writes given string to file
	 *
	 * @param string String to write
	 * @throws IOException If something went wrong
	 */
	public void writeString(String string) throws IOException {
		if (string == null) {
			this.stream.writeByte((byte) 0);
		} else {
			// Max length is 255 chars
			// First byte is length of the string
			this.stream.writeByte(string.length());
			// Write the string
			this.stream.writeChars(string);
		}
	}

	/**
	 * Writes given double to file
	 *
	 * @param value Double to write
	 * @throws IOException If something went wrong
	 */
	public void writeDouble(double value) throws IOException {
		this.stream.writeDouble(value);
	}

	/**
	 * Writes given float to file
	 *
	 * @param value Float to write
	 * @throws IOException If something went wrong
	 */
	public void writeFloat(float value) throws IOException {
		this.stream.writeFloat(value);
	}

	@Override
	public void close() throws IOException {
		this.stream.close();
	}
}