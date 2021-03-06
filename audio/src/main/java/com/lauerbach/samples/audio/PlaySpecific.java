package com.lauerbach.samples.audio;
/**
 * Copyright 2016 Thomas Lauerbach
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.Line;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.Mixer;
import javax.sound.sampled.SourceDataLine;

public class PlaySpecific {
	static final int SAMPLE_RATE = 44100;
	static final double HZ = 440;
	static final double VOL = 100;

	public static Line findSource(String name) {
		Mixer.Info[] mixerInfos = AudioSystem.getMixerInfo();
		for (Mixer.Info info : mixerInfos) {
			Mixer mixer = AudioSystem.getMixer(info);
			Line.Info[] lineInfos = mixer.getSourceLineInfo();
			if (lineInfos.length >= 1 && lineInfos[0].getLineClass().equals(SourceDataLine.class)
					&& info.getName().equals(name)) {
				System.out.println("Line Name: " + info.getName());
				System.out.println("Line Description: " + info);
				for (Line.Info lineInfo : lineInfos) {
					System.out.println("\t" + lineInfo);
					Line line;
					try {
						line = mixer.getLine(lineInfo);
						System.out.println("\t" + line.toString());
						if (line instanceof Clip) {
							System.out.println("\tClip");
						}
						if (line instanceof SourceDataLine) {
							System.out.println("\tSourceDataLine");
							return line;
						}
						System.out.println("");
					} catch (LineUnavailableException e) {
						e.printStackTrace();
						return null;
					}
				}
			}
		}
		return null;
	}

	public static void main(String[] args) {
		AudioFormat format = new AudioFormat(SAMPLE_RATE, 8, 2, true, false);

		SourceDataLine line = (SourceDataLine) findSource("Device [plughw:1,0]");
		try {
			
			line.open(format);
			line.start();

			System.out.println("Output to: " + line.toString());
			System.out.println("  Format: " + line.getFormat().toString());
			
			byte[] buf = new byte[2];
			for (int i = 0; i < SAMPLE_RATE; i++) {
				double angle = i / (SAMPLE_RATE / (double)(440*2)) * 2.0 * Math.PI;
				buf[0] = (byte) (Math.sin(angle) * 127.0);
				buf[1] = (byte) (Math.sin(angle) * 127.0);
				line.write(buf, 0, buf.length);
			}
			line.flush();
			for (int i = 0; i < SAMPLE_RATE; i++) {
				double angle = i / (SAMPLE_RATE / (double)440) * 2.0 * Math.PI;
				buf[0] = (byte) (Math.sin(angle) * 127.0);
				buf[1] = (byte) (Math.sin(angle) * 127.0);
				line.write(buf, 0, buf.length);
			}
			line.flush();
			line.stop();
			line.close();

		} catch (LineUnavailableException e) {
			e.printStackTrace();
		}

	}

}
