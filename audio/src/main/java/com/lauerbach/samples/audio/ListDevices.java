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
import javax.sound.sampled.Line;
import javax.sound.sampled.Mixer;
import javax.sound.sampled.Mixer.Info;
import javax.sound.sampled.SourceDataLine;

public class ListDevices {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Info[] list = AudioSystem.getMixerInfo();
		for (int i = 0; i < list.length; i++) {
			System.out.println(list[i].getName());
			System.out.println("  Description: " + list[i].getDescription());
			System.out.println("  Vendor: " + list[i].getVendor());
			System.out.println("  Version: " + list[i].getVersion());

			Mixer m = AudioSystem.getMixer(list[i]);
			javax.sound.sampled.Line.Info[] sourceLines = m.getSourceLineInfo();
			System.out.println("  SourceLines " + sourceLines.length);
			for (int l = 0; l < sourceLines.length; l++) {
				System.out.println("    " + sourceLines[l].getLineClass());
			}

			javax.sound.sampled.Line.Info[] targetLines = m.getTargetLineInfo();
			System.out.println("  TargetLines " + targetLines.length);
			for (int l = 0; l < targetLines.length; l++) {
				System.out.println("    " + targetLines[l].toString());
			}

		}

		
		System.out.println("*************************");
		AudioFormat format = new AudioFormat(44100, 8, 1, true, false);

		javax.sound.sampled.Line.Info[] sources = AudioSystem.getSourceLineInfo( new Line.Info( SourceDataLine.class));
		System.out.println("  Sources " + sources.length);
		for (int l = 0; l < sources.length; l++) {
			javax.sound.sampled.Line.Info[] i = AudioSystem.getSourceLineInfo( sources[l]);
			System.out.println("    " + sources[l].getLineClass());
		}
		

	}

}
