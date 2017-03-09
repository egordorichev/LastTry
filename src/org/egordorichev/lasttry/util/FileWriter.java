package org.egordorichev.lasttry.util;

import java.io.*;

public class FileWriter {
	private DataOutputStream stream;

	public FileWriter(String path) {
		try {
			File file = new File(path);
			file.createNewFile();

			this.stream = new DataOutputStream(new FileOutputStream(file));
		} catch(FileNotFoundException exception) {
			exception.printStackTrace();
		} catch(IOException exception) {
			exception.printStackTrace();
		}
	}

	public void writeByte(byte value) {
		try {
			this.stream.writeByte(value);
		} catch(IOException exception) {
			exception.printStackTrace();
		}
	}

	public void writeBoolean(boolean value) {
		try {
			this.stream.writeBoolean(value);
		} catch(IOException exception) {
			exception.printStackTrace();
		}
	}

	public void writeInt16(short value) {
		try {
			this.stream.writeShort(value);
		} catch(IOException exception) {
			exception.printStackTrace();
		}
	}

	public void writeInt32(int value) {
		try {
			this.stream.writeInt(value);
		} catch(IOException exception) {
			exception.printStackTrace();
		}
	}

	public void writeString(String string) { // Max length is 255 chars
		try {
			this.stream.writeByte(string.length()); // First byte is length of the string
			this.stream.writeChars(string);
		} catch(IOException exception) {
			exception.printStackTrace();
		}
	}

	public void writeDouble(double value) {
		try {
			this.stream.writeDouble(value);
		} catch(IOException exception) {
			exception.printStackTrace();
		}
	}

	public void close() {
		try {
			this.stream.close();
		} catch(IOException exception) {
			exception.printStackTrace();
		}
	}
}