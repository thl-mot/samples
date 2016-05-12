package com.lauerbach.samples.audio.helper;

import java.util.ArrayList;
import java.util.List;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Line;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.Mixer;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.TargetDataLine;

public class AudioHelper {

	public static List<String> listMics() {
		List<String> l=new ArrayList<String>();

		Mixer.Info[] mixerInfos = AudioSystem.getMixerInfo();
		for (Mixer.Info info : mixerInfos) {
			Mixer mixer = AudioSystem.getMixer(info);
			Line.Info[] lineInfos = mixer.getSourceLineInfo();
			if (lineInfos.length >= 1 && lineInfos[0].getLineClass().equals(TargetDataLine.class)) {
				l.add( info.getName());
			}
		}
		return l;
	}
	
	public static List<String> listSpeakers() {
		List<String> l=new ArrayList<String>();

		Mixer.Info[] mixerInfos = AudioSystem.getMixerInfo();
		for (Mixer.Info info : mixerInfos) {
			Mixer mixer = AudioSystem.getMixer(info);
			Line.Info[] lineInfos = mixer.getSourceLineInfo();
			if (lineInfos.length >= 1 && lineInfos[0].getLineClass().equals(SourceDataLine.class)) {
				l.add( info.getName());
			}
		}
		return l;
	}
	
	public static TargetDataLine findMic( String name) {
		Mixer.Info[] mixerInfos = AudioSystem.getMixerInfo();
		for (Mixer.Info info : mixerInfos) {
			Mixer mixer = AudioSystem.getMixer(info);
			Line.Info[] lineInfos = mixer.getSourceLineInfo();
			if (lineInfos.length >= 1 && lineInfos[0].getLineClass().equals(TargetDataLine.class)
					&& info.getName().equals(name)) {
				for (Line.Info lineInfo : lineInfos) {
					Line line;
					try {
						line = mixer.getLine(lineInfo);
						if (line instanceof TargetDataLine) {
							return (TargetDataLine) line;
						}
					} catch (LineUnavailableException e) {
						e.printStackTrace();
					}
				}
			}
		}
		return null;
	}

	public static SourceDataLine findSource(String name) {
		Mixer.Info[] mixerInfos = AudioSystem.getMixerInfo();
		for (Mixer.Info info : mixerInfos) {
			Mixer mixer = AudioSystem.getMixer(info);
			Line.Info[] lineInfos = mixer.getSourceLineInfo();
			if (lineInfos.length >= 1 && lineInfos[0].getLineClass().equals(SourceDataLine.class)
					&& info.getName().equals(name)) {
				for (Line.Info lineInfo : lineInfos) {
					System.out.println("\t" + lineInfo);
					Line line;
					try {
						line = mixer.getLine(lineInfo);
						if (line instanceof SourceDataLine) {
							return (SourceDataLine) line;
						}
					} catch (LineUnavailableException e) {
						e.printStackTrace();
					}
				}
			}
		}
		return null;
	}

	
}
