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
import javax.sound.sampled.SourceDataLine;

public class PlayDefault {

	static final int SAMPLE_RATE = 44100;
	static final double HZ = 1000;
	static final double VOL = 100;
	
	public static void main(String[] args) {
		AudioFormat format= new AudioFormat( SAMPLE_RATE, 8, 1, true, false);
		
		try {
			SourceDataLine source= AudioSystem.getSourceDataLine( format);
			System.out.println("Output to: "+source.toString());
			source.open( format);
			source.start();
			
			byte[] buf = new byte[1];
			for (int i = 0; i < SAMPLE_RATE; i++) {
			    double angle = i / (SAMPLE_RATE / HZ) * 2.0 * Math.PI;
			    buf[0] = (byte) (Math.sin(angle) * 127.0);
			    source.write(buf, 0, 1);
			  }
			  source.drain();
			  source.stop();
			  source.close();			
			
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		}
		
	}

}
