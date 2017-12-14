package org.egordorichev.lasttry.core.io;

import java.io.*;

/**
 * A wrapper for DataInputStream.
 */
public class FileReader implements AutoCloseable {
	private DataInputStream stream;

	public FileReader(String path) throws FileNotFoundException {
		this.stream = new DataInputStream(new BufferedInputStream(new FileInputStream(path), 32768));
	}

	/**
	 * Reads a byte from file
	 *
	 * @return A byte
	 * @throws IOException If something went wrong
	 */
	public byte readByte() throws IOException {
		return this.stream.readByte();
	}

	/**
	 * Reads a boolean from file
	 *
	 * @return A boolean
	 * @throws IOException If something went wrong
	 */
	public boolean readBoolean() throws IOException {
		return this.stream.readBoolean();
	}

	/**
	 * Reads an int16 from file
	 *
	 * @return An int16
	 * @throws IOException If something went wrong
	 */
	public short readInt16() throws IOException {
		return this.stream.readShort();
	}

	/**
	 * Reads an int32 from file
	 *
	 * @return An int32
	 * @throws IOException If something went wrong
	 */
	public int readInt32() throws IOException {
		return this.stream.readInt();
	}

	/**
	 * Reads a string from file
	 *
	 * @return A string
	 * @throws IOException If something went wrong
	 */
	public String readString() throws IOException {
		byte length = this.stream.readByte();

		if (length == 0) {
			return null;
		}

		StringBuilder result = new StringBuilder();

		for (int i = 0; i < length; i++) {
			result.append(this.stream.readChar());
		}

		return result.toString();
	}

	/**
	 * Reads a double from file
	 *
	 * @return A double
	 * @throws IOException If something went wrong
	 */
	public double readDouble() throws IOException {
		return this.stream.readDouble();
	}

	/**
	 * Reads a float from file
	 *
	 * @return A float
	 * @throws IOException If something went wrong
	 */
	public float readFloat() throws IOException {
		return this.stream.readFloat();
	}


	@Override
	public void close() throws IOException {
		this.stream.close();
	}
}