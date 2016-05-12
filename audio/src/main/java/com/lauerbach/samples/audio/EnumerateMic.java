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

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Line;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.Mixer;
import javax.sound.sampled.TargetDataLine;

public class EnumerateMic {

	public static void main(String[] args) {
		Mixer.Info[] mixerInfos = AudioSystem.getMixerInfo();
		for (Mixer.Info info : mixerInfos) {
			Mixer mixer = AudioSystem.getMixer(info);
			Line.Info[] lineInfos = mixer.getTargetLineInfo();
			if (lineInfos.length >= 1 && lineInfos[0].getLineClass().equals(TargetDataLine.class)) {
				System.out.println("Line Name: " + info.getName());
				System.out.println("Line Description: " + info.getDescription());
				for (Line.Info lineInfo : lineInfos) {
					System.out.println("\t" + "---" + lineInfo);
					Line line;
					try {
						line = mixer.getLine(lineInfo);
					} catch (LineUnavailableException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						return;
					}
					System.out.println("\t-----" + line);
				}
			}
		}

	}

}
