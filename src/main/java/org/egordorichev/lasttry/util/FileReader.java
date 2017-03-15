package org.egordorichev.lasttry.util;

import java.io.*;

public class FileReader {
	private DataInputStream stream;

	public FileReader(String path) throws FileNotFoundException {
		this.stream = new DataInputStream(new BufferedInputStream(new FileInputStream(path), 32768));
	}

	public byte readByte() {
		try {
			return this.stream.readByte();
		} catch(IOException exception) {
			exception.printStackTrace();
		}

		return 0;
	}

	public boolean readBoolean() {
		try {
			return this.stream.readBoolean();
		} catch(IOException exception) {
			exception.printStackTrace();
		}

		return false;
	}

	public short readInt16() {
		try {
			return this.stream.readShort();
		} catch(IOException exception) {
			exception.printStackTrace();
		}

		return 0;
	}

	public int readInt32() {
		try {
			return this.stream.readInt();
		} catch(IOException exception) {
			exception.printStackTrace();
		}

		return 0;
	}

	public String readString() {
		try {
			byte length = this.stream.readByte();
			String result = "";

			for(int i = 0; i < length; i++) {
				result += this.stream.readChar();
			}

			return result;
		} catch(IOException exception) {
			exception.printStackTrace();
		}

		return "";
	}

	public double readDouble() {
		try {
			return this.stream.readDouble();
		} catch(IOException exception) {
			exception.printStackTrace();
		}

		return 0;
	}

	public void close() {
		try {
			this.stream.close();
		} catch(IOException exception) {
			exception.printStackTrace();
		}
	}
}