package bewareofthetruth.model.util;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;

import bewareofthetruth.contract.model.utils.ISound;

public class Sound implements ISound {
	   private String musicPath;
	    private int nMusic;
	    private int index;
	    private ArrayList<Music> musicList;



	    public Sound(ArrayList<String> musicPathList) {
	        super();
	        this.setIndex(0);
	        this.setMusicList(new ArrayList<Music>());
	        this.loadMusic(musicPathList);
	        this.setnMusic(this.getMusicList().size());
	        this.play();
	    }

	    public void playNextMusic(){
	        this.stopCurrentMusic();
	        this.setIndex(getIndex()+1);
	        this.setIndex(getIndex() % getnMusic());
	        this.play();
	    }

	    public void playPreviousMusic() {
	        this.stopCurrentMusic();
	        this.setIndex(getIndex()-1);
	        if(this.getIndex() < 0) {
	            this.setIndex(getnMusic() - 1);
	        }
	        this.play();
	    }

	    private void play() {
	        this.getMusicList().get(getIndex()).play();
	    }

	    private void loadMusic(final ArrayList<String> list) {
	        for(String path : list) {
	            this.getMusicList().add(Gdx.audio.newMusic(Gdx.files.internal(path)));
	        }
	    }

	    public void stopCurrentMusic() {
	        this.getMusicList().get(getIndex()).stop();
	    }

	    public void dispose() {
	        for(Music music : musicList) {
	            music.dispose();
	        }
	    }		
	    public String getMusicPath() {
	        return musicPath;
	    }

	    public void setMusicPath(String musicPath) {
	        this.musicPath = musicPath;
	    }

	    public int getnMusic() {
	        return nMusic;
	    }

	    public void setnMusic(int nMusic) {
	        this.nMusic = nMusic;
	    }

	    public int getIndex() {
	        return index;
	    }

	    public void setIndex(int index) {
	        this.index = index;
	    }

	    public ArrayList<Music> getMusicList() {
	        return musicList;
	    }

	    public void setMusicList(ArrayList<Music> musicList) {
	        this.musicList = musicList;
	    }

	}
	    
