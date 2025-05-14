package org.peter.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalTime;

public class Logger {

	private final FileWriter writer;
	private StringBuilder logStr;

	public Logger(File logfile) throws IOException { 
		
		logfile.createNewFile();	
		writer = new FileWriter(logfile);
       
	}

	public void write(String toWrite) throws IOException {

		logStr = new StringBuilder(LocalTime.now().toString());
		logStr.append(" : ");
		logStr.append(toWrite);
		writer.write(logStr.toString(), 0, logStr.length());
		writer.write((int) '\n');
		writer.flush();

	}

	public void close() throws IOException {

		writer.close();

	}

}
