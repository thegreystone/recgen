/*
 * Copyright (C) 2024 Marcus Hirt
 *                    www.hirt.se
 *
 * This software is free:
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 * 1. Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in the
 *    documentation and/or other materials provided with the distribution.
 * 3. The name of the author may not be used to endorse or promote products
 *    derived from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE AUTHOR ``AS IS'' AND ANY EXPRESSED OR
 * IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 * OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED.
 * IN NO EVENT SHALL THE AUTHOR BE LIABLE FOR ANY DIRECT, INDIRECT,
 * INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT
 * NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
 * THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF
 * THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 * Copyright (C) Marcus Hirt, 2024
 */
package se.hirt.recgen;

import java.io.File;
import java.io.IOException;
import java.time.Instant;

import org.openjdk.jmc.flightrecorder.writer.TypesImpl;
import org.openjdk.jmc.flightrecorder.writer.api.Recording;
import org.openjdk.jmc.flightrecorder.writer.api.Recordings;
import org.openjdk.jmc.flightrecorder.writer.api.Type;

/**
 * Utilities for creating recordings.
 */
public class Utils {
	/**
	 * Creates a {@link Recording} pre-populated with common example data.
	 * 
	 * @param dir
	 *            the target directory of the recording.
	 * @param name
	 *            the file name of the recording.
	 * @param pid
	 *            the pid of the process creating the recording. Not used yet.
	 * @return a {@link Recording}.
	 * @throws IOException
	 *             if something failed.
	 */
	public static Recording createDefaultRecording(File dir, String name, long pid) throws IOException {
		File location = new File(dir, name);
		return createDefaultRecording(location, pid);
	}

	/**
	 * Creates a {@link Recording} pre-populated with common example data.
	 * 
	 * @param location
	 *            the destination location for the recording (full file path, including file name).
	 * @param pid
	 *            the pid of the process creating the recording. Not used yet.
	 * @return a {@link Recording}.
	 * @throws IOException
	 *             if something failed.
	 */
	public static Recording createDefaultRecording(File location, long pid) throws IOException {
		Instant now = Instant.now();

		Recording newRecording = Recordings.newRecording(location);
		Type type = registerSystemProcess(newRecording);
		writeSystemProcessEvent(newRecording, type, "System Process 1", now.minusSeconds(34).toEpochMilli(), String.valueOf(4711L));
		return newRecording;
	}

	/**
	 * Writes a jdk.SystemProcess event.
	 * 
	 * @param newRecording
	 *            the {@link Recording} to write to.
	 * @param type
	 *            the system process type.
	 * @param commandLine
	 *            the commanLine field entry.
	 * @param startTime
	 *            the startTime of the event.
	 * @param pid
	 *            the pid of the process.
	 */
	public static void writeSystemProcessEvent(
		Recording newRecording, Type type, String commandLine, long startTime, String pid) {
		newRecording.writeEvent(type.asValue(b -> {
			b.putField("startTime", startTime).putField("commandLine", commandLine).putField("pid", pid);
		}));
	}

	/**
	 * Registers the jdk.SystemProcess event with the recording.
	 * 
	 * @param newRecording
	 *            the {@link Recording} to register the system process event type to. Will return
	 *            the existing one if already registered.
	 * @return the {@link Type} for the jdk.SystemProcess event.
	 */
	public static Type registerSystemProcess(Recording newRecording) {
		return newRecording.registerEventType("jdk.SystemProcess", builder -> {
			builder.addField("commandLine", TypesImpl.Builtin.STRING);
			builder.addField("pid", TypesImpl.Builtin.STRING);
		});
	}
}
