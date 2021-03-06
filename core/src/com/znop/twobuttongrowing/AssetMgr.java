package com.znop.twobuttongrowing;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by @paruthidotexe on 12-12-2015.
 * Note: Use this code as u wish, but dont sell the code.
 *      If any optimizations/ bugs ping me @paruthidotexe
 *      If you become rich by making game try like me
 *      donating 10% to those people who are in need.
 *      Happy Gaming !!!
 */
public class AssetMgr {

    Map<String, String> AssetMap;
    Map<String, String> SoundAssetMap;
    Map<String, String> MusicAssetMap;

    Map<String, Texture> AssetMapData;
    Map<String, Sound> SoundAssetMapData;
    Map<String, Music> MusicAssetMapData;

    // Folders path
    private static final String FOLDER_IMAGES_UI = "ponzres" + File.separator
            + "images" + File.separator + "ui"  + File.separator;
    private static final String FOLDER_IMAGES_GAME = "ponzres" + File.separator
            + "images" + File.separator + "game"  + File.separator;
    private static final String FOLDER_FONTS = "ponzres" + File.separator
            + "fonts" + File.separator;
    private static final String FOLDER_SOUNDS = "ponzres" + File.separator
            + "sounds" + File.separator;

    // font path
    private static final String ASSET_FONT_DEBUGFONT = FOLDER_FONTS
            + "creativeblock15_black.fnt";
    private static final String ASSET_FONT_GAME = FOLDER_FONTS
            + "creativeblock30_white.fnt";

    // Images
    private Texture Play_Btn;
    private Texture Settings_Btn;
    private Texture SoundOn_Btn;
    private Texture SoundOff_Btn;
    private Texture Achivement_Btn;
    private Texture Leaderboard_Btn;
    private Texture MoreGames_Btn;
    private Texture Share_Btn;
    //private Texture Facebook_Btn;
    //private Texture Twitter_Btn;
    //private Texture GooglePlus_Btn;

    private Sound Touch_SFX;
    private Music BG_01_Music;

    // fonts
    public static BitmapFont debugFont;
    public static BitmapFont HeadingFont;


    private static AssetMgr instance = null;
    private AssetMgr()
    {
    }
    public static AssetMgr Inst() {
        if(instance == null) {
            instance = new AssetMgr();
        }
        return instance;
    }

    public void Init() {
        AssetMap = new HashMap<String, String>();
        SoundAssetMap = new HashMap<String, String>();
        MusicAssetMap = new HashMap<String, String>();

        // Texture Assets
        // UI
        AssetMap.put("Achievement", FOLDER_IMAGES_UI + "Achievement.png");
        AssetMap.put("Facebook", FOLDER_IMAGES_UI + "Facebook.png");
        AssetMap.put("Gift", FOLDER_IMAGES_UI + "Gift.png");
        AssetMap.put("Health", FOLDER_IMAGES_UI + "Health.png");
        AssetMap.put("HighScore", FOLDER_IMAGES_UI + "HighScore.png");
        AssetMap.put("Home", FOLDER_IMAGES_UI + "Home.png");
        AssetMap.put("Pause", FOLDER_IMAGES_UI + "Pause.png");
        AssetMap.put("Play", FOLDER_IMAGES_UI + "Play.png");
        AssetMap.put("RateGame", FOLDER_IMAGES_UI + "RateGame.png");
        AssetMap.put("Restart", FOLDER_IMAGES_UI + "Restart.png");
        AssetMap.put("SoundOff", FOLDER_IMAGES_UI + "SoundOff.png");
        AssetMap.put("SoundOn", FOLDER_IMAGES_UI + "SoundOn.png");
        AssetMap.put("Twitter", FOLDER_IMAGES_UI + "Twitter.png");
        AssetMap.put("Video", FOLDER_IMAGES_UI + "Video.png");

        AssetMap.put("BG_0", FOLDER_IMAGES_GAME + "BG_BW.png");
        AssetMap.put("BG_1", FOLDER_IMAGES_GAME + "BG_Brown.png");
        AssetMap.put("BG_2", FOLDER_IMAGES_GAME + "BG_Sepia.png");

        // In Game
        AssetMap.put("Yin", FOLDER_IMAGES_GAME + "Yin.png");
        AssetMap.put("Yang", FOLDER_IMAGES_GAME + "Yang.png");
        AssetMap.put("Black", FOLDER_IMAGES_GAME + "Black.png");
        AssetMap.put("White", FOLDER_IMAGES_GAME + "White.png");
        AssetMap.put("White3", FOLDER_IMAGES_GAME + "White3.png");
        AssetMap.put("BlackBg1", FOLDER_IMAGES_GAME + "BlackBg1.png");
        AssetMap.put("WhiteBg1", FOLDER_IMAGES_GAME + "WhiteBg1.png");
        AssetMap.put("BlackBg2", FOLDER_IMAGES_GAME + "BlackBg2.jpg");
        AssetMap.put("WhiteBg2", FOLDER_IMAGES_GAME + "WhiteBg2.jpg");

        AssetMap.put("parallax_05", FOLDER_IMAGES_GAME + "parallax_05.png");

        // SFX Assets
        SoundAssetMap.put("TouchSFX", FOLDER_SOUNDS + "sfx_shot.wav");
        SoundAssetMap.put("WaterdropSFX", FOLDER_SOUNDS + "sfx_waterdrop24.wav");

        // Music Assets
        MusicAssetMap.put("Bg_music", FOLDER_SOUNDS + "bg_8.12.mp3");

        LoadAllAssets();
    }


    public void LoadAllAssets()
    {
        LoadImages();
        LoadFonts();
        LoadSounds();
    }


    public static void LoadFonts()
    {
        debugFont = new BitmapFont(Gdx.files.internal(ASSET_FONT_DEBUGFONT),
                false);
        HeadingFont = new BitmapFont(Gdx.files.internal(ASSET_FONT_GAME),
                false);
    }


    public void LoadImages()
    {
        AssetMapData = new HashMap<String, Texture>();

        for(Map.Entry<String, String> curEntry: AssetMap.entrySet()) {
            Texture curTexture = new Texture(Gdx.files.internal(curEntry.getValue()));
            AssetMapData.put(curEntry.getKey(), curTexture);
            Gdx.app.log("Znop", "AssetMap: " + curEntry.getKey() + ", " + curEntry.getValue());
        }
    }


    public void LoadSounds()
    {
        SoundAssetMapData = new HashMap<String, Sound>();
        MusicAssetMapData = new HashMap<String, Music>();

        for(Map.Entry<String, String> curEntry: SoundAssetMap.entrySet()) {
            Sound curSound = Gdx.audio.newSound( Gdx.files.internal(curEntry.getValue()));
            SoundAssetMapData.put(curEntry.getKey(), curSound);
            Gdx.app.log("Znop", "SoundAssetMap: " + curEntry.getKey() + ", " + curEntry.getValue());
        }

        for(Map.Entry<String, String> curEntry: MusicAssetMap.entrySet()) {
            Music curMusic = Gdx.audio.newMusic(Gdx.files.internal(curEntry.getValue()));
            MusicAssetMapData.put(curEntry.getKey(), curMusic);
            Gdx.app.log("Znop", "MusicAssetMap: " + curEntry.getKey() + ", " + curEntry.getValue());
        }
    }


    public void dispose()
    {
        // dispose fonts
        debugFont.dispose();
        HeadingFont.dispose();

        for(Map.Entry<String, Texture> curEntry: AssetMapData.entrySet()) {
            curEntry.getValue().dispose();
        }

        for(Map.Entry<String, Sound> curEntry: SoundAssetMapData.entrySet()) {
            curEntry.getValue().dispose();
        }

        for(Map.Entry<String, Music> curEntry: MusicAssetMapData.entrySet()) {
            curEntry.getValue().dispose();
        }
    }


    public Texture GetTexture(String textureKey)
    {
        if(AssetMapData.containsKey(textureKey))
        {
            return AssetMapData.get(textureKey);
        }
        return null;
    }


    public Sound GetSound(String soundKey)
    {
        if(SoundAssetMapData.containsKey(soundKey))
        {
            return SoundAssetMapData.get(soundKey);
        }
        return null;
    }


    public Music GetMusic(String musicKey)
    {
        if(MusicAssetMapData.containsKey(musicKey))
        {
            return MusicAssetMapData.get(musicKey);
        }
        return null;
    }

}
