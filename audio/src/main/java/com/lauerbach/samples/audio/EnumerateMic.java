package com.lauerbach.samples.audio;

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
