package com.pro.extension;

import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Player {

	public static void main(String[] args) throws Exception {
		File file = new File("d:\\11.midi");
		AudioInputStream auIn = AudioSystem.getAudioInputStream(file);
		Clip clip = (Clip) AudioSystem.getClip();
		clip.open(auIn);
		clip.start();
		while (clip.available() > 0) {
			Thread.sleep(2000);
		}
		clip.close();
	}
}
