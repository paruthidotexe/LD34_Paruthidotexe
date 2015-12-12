package com.znop.twobuttongrowing;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

/**
 * Created by user on 12-12-2015.
 */
public class AudioMgr {
    int Volume = 100;
    float Speed = 1.0f;
    boolean isMuted = false;

    public AudioMgr(){
    }


    public void PlaySFX(Sound curSFX){
        if(!isMuted)
            curSFX.play();
    }


    public void PlayMusic(Music curMusic){
        if(!isMuted) {
            curMusic.setLooping(true);
            curMusic.play();
        }
    }


    public String GetPath(String id) {
        String retVal = "";
        if(id.equalsIgnoreCase("bgMusic")){
            retVal = "Audio/8.12.mp3";
        }
        return retVal;
    }
}
