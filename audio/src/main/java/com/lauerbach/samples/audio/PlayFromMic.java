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
import javax.sound.sampled.TargetDataLine;

public class PlayFromMic {

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
	
	static final int SAMPLE_RATE= 8000;
	
	public static void main(String[] args) {
		AudioFormat format = new AudioFormat( SAMPLE_RATE, 8, 1, true, false);
		
		SourceDataLine line = (SourceDataLine) findSource("Device [plughw:1,0]");
		
		try {
			TargetDataLine mic= AudioSystem.getTargetDataLine(format);
			mic.open(format);
			mic.start();
			
			line.open(format);
			line.start();

			byte[] buf = new byte[1];
			long count=1;
			while (count>0) {
				int i = mic.read(buf, 0, buf.length);
				System.out.println( i);
				line.write(buf, 0, i);
			}
			
			line.flush();
			line.stop();
			line.close();
			mic.stop();
			mic.close();

		} catch (LineUnavailableException e) {
			e.printStackTrace();
		}

	}

}
