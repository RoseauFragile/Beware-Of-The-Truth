package bewareofthetruth.hud;

import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.utils.Array;
import bewareofthetruth.inventory.StatusObserver;
import bewareofthetruth.inventory.StatusObserver.StatusEvent;
import bewareofthetruth.inventory.StatusSubject;
import bewareofthetruth.utility.Utility;

public class PauseUI extends Window implements StatusSubject{

    private ImageButton _resumeButton;
    private ImageButton _saveButton;
    private ImageButton _optionsButton;
    private ImageButton _quitButton;
    private Array<StatusObserver> _observers;
    
	public PauseUI() {
		super("", Utility.STATUSUI_SKIN, "pauseMenu");
		
		_observers = new Array<StatusObserver>();
        _resumeButton= new ImageButton(Utility.STATUSUI_SKIN, "menu-reprendre-button");
        _saveButton = new ImageButton(Utility.STATUSUI_SKIN, "menu-sauvegarder-button");
        _optionsButton= new ImageButton(Utility.STATUSUI_SKIN, "menu-options-button");
        _quitButton= new ImageButton(Utility.STATUSUI_SKIN, "menu-quitter-button");
        this.add();
        this.row(); 
        this.add(_resumeButton).spaceBottom(50).padTop(100);
        this.row();
        this.add(_saveButton).spaceBottom(50);
        this.row();
        this.add(_optionsButton).spaceBottom(50);
        this.row();
        this.add(_quitButton);
        this.pack();
       // this.debug();
	}

	@Override
	public void addObserver(StatusObserver statusObserver) {
		_observers.add(statusObserver);
	}

	@Override
	public void removeObserver(StatusObserver statusObserver) {
		_observers.removeValue(statusObserver, true);
	}

	@Override
	public void removeAllObservers() {
        for(StatusObserver observer: _observers){
            _observers.removeValue(observer, true);
        }
	}

	@Override
	public void notify(int value, StatusEvent event) {
        for(StatusObserver observer: _observers){
            observer.onNotify(value, event);
        }
	}

	public ImageButton get_resumeButton() {
		return this._resumeButton;
	}

	public ImageButton get_saveButton() {
		return this._saveButton;
	}

	public ImageButton get_optionsButton() {
		return this._optionsButton;
	}

	public ImageButton get_quitButton() {
		return this._quitButton;
	}
}
