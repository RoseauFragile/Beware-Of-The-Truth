package bewareofthetruth.hud;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.Array;

import bewareofthetruth.inventory.InventoryObserver;
import bewareofthetruth.inventory.InventoryObserver.InventoryEvent;
import bewareofthetruth.utility.Utility;
import bewareofthetruth.inventory.InventorySlot;
import bewareofthetruth.inventory.InventorySlotObserver;
import bewareofthetruth.inventory.InventorySubject;
import bewareofthetruth.inventory.StatusObserver;
import bewareofthetruth.inventory.StatusObserver.StatusEvent;
import bewareofthetruth.inventory.StatusSubject;

public class InventoryInGameUI extends Window implements InventorySubject, InventorySlotObserver, StatusSubject {

	private ImageButton _testButton;
	private Array<StatusObserver> _observers;
	private Array<InventoryObserver> _observersInventory;

	 
	public InventoryInGameUI() {
		super("", Utility.STATUSUI_SKIN, "InventoryInGame");
		
			this.setReductedBackground();
			_observers = new Array<StatusObserver>();
			_testButton = new ImageButton(Utility.STATUSUI_SKIN, "inventory-button");

		    defaults().expand().fill();

		    this.add(_testButton);
		    this.debug();
		    this.pack();
		 }
		 
	public ImageButton getTestButton() {
		return this._testButton;
	}
	
	public void setReductedBackground() {
		Image image = new Image(Utility.STATUSUI_SKIN, "ReductInventoryInGame");
		Drawable drawable = image.getDrawable();
		this.setBackground(drawable);
	}
	
	public void setNormalBackground() {
		Image image = new Image(Utility.STATUSUI_SKIN, "InventoryInGame");
		Drawable drawable = image.getDrawable();
		this.setBackground(drawable);
	}

	@Override
	public void onNotify(InventorySlot slot, SlotEvent event) {
	}

	@Override
	public void addObserver(InventoryObserver inventoryObserver) {
		_observersInventory.add(inventoryObserver);
	}

	@Override
	public void removeObserver(InventoryObserver inventoryObserver) {
		_observersInventory.removeValue(inventoryObserver, true);
	}

	@Override
	public void removeAllObservers() {
	       for(InventoryObserver observer: _observersInventory){
	            _observersInventory.removeValue(observer, true);
	        }
        for(StatusObserver observer: _observers){
            _observers.removeValue(observer, true);
        }
	}

	@Override
	public void notify(String value, InventoryEvent event) {
        for(InventoryObserver observer: _observersInventory){
            observer.onNotify(value, event);
        }
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
	public void notify(int value, StatusEvent event) {
        for(StatusObserver observer: _observers){
            observer.onNotify(value, event);
        }
	}
	
	

}
