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

import se.hirt.recgen.recordings.LargePIDRecording;
import se.hirt.recgen.recordings.PID1Recording;

/**
 * Entry point.
 */
public class Main {
	/**
	 * Will generate the various JFR files to the provided destination directory.
	 * 
	 * @param args
	 *            must contain one argument; the destination directory.
	 */
	public static void main(String[] args) throws IOException {
		if (args.length != 1) {
			System.out.println(
					"Please provide exactly one argument - the destination folder for the generated JFR files.\nExiting...");
			System.exit(2);
		}
		File dir = new File(args[0]);
		if (!dir.isDirectory()) {
			System.out.println("The provided argument must be an existing folder.\nExiting...");
			System.exit(3);
		}
		new LargePIDRecording().generateRecording(dir, "largepid.jfr");
		new PID1Recording().generateRecording(dir, "pid1.jfr");
	}
}
