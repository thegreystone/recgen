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
package se.hirt.recgen.recordings;

import java.io.File;
import java.io.IOException;
import java.time.Instant;

import org.openjdk.jmc.flightrecorder.writer.api.Recording;
import org.openjdk.jmc.flightrecorder.writer.api.Type;

import se.hirt.recgen.RecordingGenerator;
import se.hirt.recgen.Utils;

/**
 * Recording with a large PID Process.
 */
public class LargePIDRecording implements RecordingGenerator {

	@Override
	public void generateRecording(File destinationFolder, String recordingName) throws IOException {
		try (Recording defaultRecording = Utils.createDefaultRecording(destinationFolder, recordingName, 32434212L)) {
			Type type = Utils.registerSystemProcess(defaultRecording);
			Utils.writeSystemProcessEvent(defaultRecording, type, "Very long command line for the process running JMC...",
					Instant.now().minusSeconds(493).toEpochMilli(), String.valueOf(32434212L));
			Utils.writeSystemProcessEvent(defaultRecording, type, "Large PID Process",
					Instant.now().minusSeconds(493).toEpochMilli(), String.valueOf(32434213L));
		}
	}
}
