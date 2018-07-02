package bewareofthetruth.main;

import java.util.ArrayList;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

import bewareofthetruth.model.util.Sound;

public class MainTestAudio implements ApplicationListener {

    private Sound msc;
    private int track;

    @Override
    public void create () {
        ArrayList<String> list = new ArrayList<String>();
        list.add("bar1.wav");
        setMsc(new Sound(list));
    }

    @Override
    public void render () {
        this.update();
    }

    public void dispose () {
        getMsc().dispose();
    }

    public void update() {
        if(Gdx.input.isKeyJustPressed(Input.Keys.LEFT)) { 
            this.getMsc().playPreviousMusic();
        }

        if(Gdx.input.isKeyJustPressed(Input.Keys.RIGHT)) { 
            this.getMsc().playNextMusic();
        }
    }


    public Sound getMsc() {
        return msc;
    }

    public void setMsc(Sound msc) {
        this.msc = msc;
    }


    public int getTrack() {
        return track;
    }

    public void setTrack(int track) {
        this.track = track;
    }

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}

}
