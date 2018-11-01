package bewareofthetruth.hud;

import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
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
		super("Inventory", Utility.STATUSUI_SKIN, "solidbackground");
		 
			_observers = new Array<StatusObserver>();
			_testButton = new ImageButton(Utility.STATUSUI_SKIN, "inventory-button");
			 
			//Add to layout
		    defaults().expand().fill();

		    //account for the title padding
		    this.pad(this.getPadTop() + 10, 10, 10, 10);
		    //  this.setColor(Color.CLEAR);
		    //  this.setBackground(background);
		    //  this.row();
		    this.add(_testButton);/*.align(Align.bottomRight)*///;
		    this.debug();
		    this.pack();
		 }
		 
		 public ImageButton getTestButton() {
			 return this._testButton;
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
