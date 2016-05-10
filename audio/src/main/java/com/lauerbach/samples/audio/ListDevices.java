package com.lauerbach.samples.audio;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Mixer;
import javax.sound.sampled.Mixer.Info;

public class ListDevices {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Info[] list = AudioSystem.getMixerInfo();
		for (int i=0; i<list.length; i++) {
			System.out.println( list[i].getName());
			System.out.println( "  Description: "+list[i].getDescription());
			System.out.println( "  Vendor: "+list[i].getVendor());
			System.out.println( "  Version: "+list[i].getVersion());
			
			Mixer m = AudioSystem.getMixer( list[i]);
			javax.sound.sampled.Line.Info[] sourceLines = m.getSourceLineInfo();
			System.out.println( "  open SourceLines "+sourceLines.length);
			for (int l=0; l<sourceLines.length; l++) {
				System.out.println("    "+sourceLines[l].getLineClass());
			}
			
			javax.sound.sampled.Line.Info[] targetLines = m.getTargetLineInfo();
			System.out.println( "  open TargetLines "+sourceLines.length);
			for (int l=0; l<targetLines.length; l++) {
				System.out.println("    "+targetLines[l].getLineClass());
			}

		}
		
	}

}
