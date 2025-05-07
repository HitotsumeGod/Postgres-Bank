package server.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Logger {

	private final FileWriter writer;

	public Logger(File logfile) throws IOException { writer = new FileWriter(logfile); }

	public void write(String toWrite) throws IOException {

		writer.write(toWrite, 0, toWrite.length());

	}

	public void close() throws IOException {

		writer.close();

	}

}