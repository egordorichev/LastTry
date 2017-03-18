package org.egordorichev.lasttry.util;

import java.io.*;

/**
 * A wrapper for DataOutputStream.
 */
public class FileWriter implements AutoCloseable {
	private DataOutputStream stream;

	public FileWriter(String path) throws IOException {
		File file = new File(path);
		if (!file.exists()) {
			File parentDir = file.getParentFile();
			if (parentDir != null && !parentDir.exists()) {
				parentDir.mkdirs();
			}
			file.createNewFile();
		}
		this.stream = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(path), 32768));
	}

	public void writeByte(byte value) throws IOException {
		this.stream.writeByte(value);
	}

	public void writeBoolean(boolean value) throws IOException {
		this.stream.writeBoolean(value);
	}

	public void writeInt16(short value) throws IOException {
		this.stream.writeShort(value);
	}

	public void writeInt32(int value) throws IOException {
		this.stream.writeInt(value);
	}

	public void writeString(String string) throws IOException {
		// Max length is 255 chars
		// First byte is length of the string
		this.stream.writeByte(string.length());
		// Write the string
		this.stream.writeChars(string);

	}

	public void writeDouble(double value) throws IOException {
		this.stream.writeDouble(value);
	}

	@Override
	public void close() throws IOException {
		this.stream.close();
	}
}