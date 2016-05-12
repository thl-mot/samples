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
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.TargetDataLine;

public class RecordDefault {
	static final int SAMPLE_RATE = 8000;
	static final double HZ = 1000;
	static final double VOL = 100;

	public static void main(String[] args) {
		AudioFormat format = new AudioFormat(SAMPLE_RATE, 8, 1, true, false);

		try {
			TargetDataLine mic = AudioSystem.getTargetDataLine(format);
			System.out.println("Input from: " + mic.toString());
			mic.open(format);
			mic.start();

			byte[] buf = new byte[1000];
			long count=10;
			while (count-->0) {
				int i = mic.read(buf, 0, 1000);
				System.out.println( i+" "+buf[0]);
			}

			mic.stop();
			mic.close();

		} catch (LineUnavailableException e) {
			e.printStackTrace();
		}

	}

}
