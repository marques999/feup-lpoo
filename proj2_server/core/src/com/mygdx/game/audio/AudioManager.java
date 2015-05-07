package com.mygdx.game.audio;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

enum Song
{
	M_OVERWORLD, M_UNDERGROUNDS, M_LOST, M_CLEAR, M_NONE
}

public class AudioManager
{
	private static Music overworld = Gdx.audio.newMusic(Gdx.files.internal("data/audio/soundtracks/Overworld.ogg"));
	private static Music undergrounds = Gdx.audio.newMusic(Gdx.files.internal("data/audio/soundtracks/Undergrounds.ogg"));
	private static Music lifelost = Gdx.audio.newMusic(Gdx.files.internal("data/audio/soundtracks/Life Lost.ogg"));
	private static Music finish = Gdx.audio.newMusic(Gdx.files.internal("data/audio/soundtracks/Course Clear.ogg"));

	private static Music currentMusic;

	public static Sound jump = Gdx.audio.newSound(Gdx.files.internal("data/audio/sfx/jump.ogg"));
	public static Sound stomp = Gdx.audio.newSound(Gdx.files.internal("data/audio/sfx/stomp.ogg"));
	public static Sound bump = Gdx.audio.newSound(Gdx.files.internal("data/audio/sfx/bump.ogg"));
	public static Sound flag = Gdx.audio.newSound(Gdx.files.internal("data/audio/sfx/flagpole.ogg"));
	public static Sound powerDown = Gdx.audio.newSound(Gdx.files.internal("data/audio/sfx/PowerDown.ogg"));
	public static Sound powerUp = Gdx.audio.newSound(Gdx.files.internal("data/audio/sfx/PowerUp.ogg"));

	public static Song currentSong = Song.M_NONE;

	public static void playSong(Song songName, boolean looping)
	{
		switch (songName)
		{
			case M_OVERWORLD:
				currentSong = Song.M_OVERWORLD;
				currentMusic = overworld;
				break;
			case M_UNDERGROUNDS:
				currentSong = Song.M_UNDERGROUNDS;
				currentMusic = undergrounds;
				break;
			case M_LOST:
				currentSong = Song.M_LOST;
				currentMusic = lifelost;
				break;
			case M_CLEAR:
				currentSong = Song.M_CLEAR;
				currentMusic = finish;
		}

		currentMusic.setLooping(looping);
		currentMusic.play();
	}

	public static Music getSong()
	{
		return currentMusic;
	}

	public static void stopSong()
	{
		if(currentMusic != null)
		{
			currentMusic.stop();
		}
	}

	public static void dispose()
	{
		overworld.dispose();
		undergrounds.dispose();
		currentMusic.dispose();
		jump.dispose();
		stomp.dispose();
	}
}