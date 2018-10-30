package bewareofthetruth.hud;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.graphics.Color;
import bewareofthetruth.inventory.StatusObserver;
import bewareofthetruth.inventory.StatusObserver.StatusEvent;
import bewareofthetruth.utility.Utility;
import bewareofthetruth.inventory.StatusSubject;

public class TestHud/* extends Window implements StatusSubject*/ {

	/* private ImageButton _testButton;
	 private Array<StatusObserver> _observers;*/
	 
	 //public TestHud() {
		/* super("test", Utility.STATUSUI_SKIN);
		 
		 _observers = new Array<StatusObserver>();
		 
		 _testButton = new ImageButton(Utility.STATUSUI_SKIN_TEST, "inventory-test-button");
		 
		//Add to layout
	        defaults().expand().fill();

	        //account for the title padding
	        //this.pad(this.getPadTop() + 10, 10, 10, 10);
	        //this.setColor(Color.CLEAR);
	        //this.setBackground(background);
	       // this.row();
	        this.add(_testButton)/*.align(Align.bottomRight)*///;
	        //this.debug();
	       // this.pack();
	/* }
	 
	 public ImageButton getTestButton() {
		 return this._testButton;
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
	    public void notify(int value, StatusObserver.StatusEvent event) {
	        for(StatusObserver observer: _observers){
	            observer.onNotify(value, event);
	        }
	    }
*/
}
