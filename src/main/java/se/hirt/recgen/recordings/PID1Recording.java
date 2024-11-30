package se.hirt.recgen.recordings;

import java.io.File;
import java.io.IOException;
import java.time.Instant;

import org.openjdk.jmc.flightrecorder.writer.api.Recording;
import org.openjdk.jmc.flightrecorder.writer.api.Type;

import se.hirt.recgen.RecordingGenerator;
import se.hirt.recgen.Utils;

/**
 * Recording with a PID 1 Process.
 */
public class PID1Recording implements RecordingGenerator {
   @Override
   public void generateRecording(File destinationFolder, String recordingName) throws IOException {
      try (Recording defaultRecording = Utils.createDefaultRecording(destinationFolder, recordingName, 1)) {
         Type type = Utils.registerSystemProcess(defaultRecording);
         Utils.writeSystemProcessEvent(defaultRecording, type, "Process with PID 1",
               Instant.now().minusSeconds(5).toEpochMilli(), "1");
      }
   }
}
