package bewareofthetruth.contract.model.utils;

import java.util.ArrayList;
import com.badlogic.gdx.audio.Music;

public interface ISound {
    public void playNextMusic();

    public void playPreviousMusic();

    public void stopCurrentMusic();

    public void dispose();
    
    public String getMusicPath();
    
    public void setMusicPath(String musicPath);
    
    public int getnMusic();

    public void setnMusic(int nMusic);

    public int getIndex();

    public void setIndex(int index);

    public ArrayList<Music> getMusicList();

    public void setMusicList(ArrayList<Music> musicList);
}
