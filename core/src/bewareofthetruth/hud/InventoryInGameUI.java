package bewareofthetruth.hud;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.Array;

import bewareofthetruth.entity.components.Component;
import bewareofthetruth.inventory.InventoryItem;
import bewareofthetruth.inventory.InventoryObserver;
import bewareofthetruth.inventory.InventoryObserver.InventoryEvent;
import bewareofthetruth.utility.Utility;
import bewareofthetruth.inventory.InventorySlot;
import bewareofthetruth.inventory.InventorySlotObserver;
import bewareofthetruth.inventory.InventorySlotTarget;
import bewareofthetruth.inventory.InventorySlotTooltip;
import bewareofthetruth.inventory.InventorySlotTooltipListener;
import bewareofthetruth.inventory.InventorySubject;
import bewareofthetruth.inventory.StatusObserver;
import bewareofthetruth.inventory.StatusObserver.StatusEvent;
import bewareofthetruth.inventory.StatusSubject;

public class InventoryInGameUI extends Window implements InventorySubject, InventorySlotObserver, StatusSubject {

	private ImageButton _testButton;
	private Array<StatusObserver> _observers;
	private Array<InventoryObserver> _observersInventory;
	public final static int _numSlots = 6;
	private Table _inventorySlotTable;
	private InventorySlotTooltip _inventorySlotTooltip;
	private DragAndDrop _dragAndDrop;
    private final int _slotWidth = 104;
    private final int _slotHeight = 91;

	 
	public InventoryInGameUI() {
		super("", Utility.STATUSUI_SKIN, "InventoryInGame");
		
		_inventorySlotTable = new Table();
        _inventorySlotTable.setName("Inventory_Slot_Table");
        _inventorySlotTooltip = new InventorySlotTooltip(Utility.STATUSUI_SKIN);
        _dragAndDrop = new DragAndDrop();

		
			this.setReductedBackground();
			_observers = new Array<StatusObserver>();
			_testButton = new ImageButton(Utility.STATUSUI_SKIN, "inventory-button");

		    defaults().expand().fill();

	        //layout
		    this.initInventorySlots();
	        
		    //this.add(_testButton);
	        this.add(_inventorySlotTable).colspan(2);
	        this._inventorySlotTable.setVisible(false);
		    //this.debug();
		    this.pack();
		 }
		 
	public ImageButton getTestButton() {
		return this._testButton;
	}
	
	public void setReductedBackground() {
		Image image = new Image(Utility.STATUSUI_SKIN, "ReductInventoryInGame");
		Drawable drawable = image.getDrawable();
		this.setBackground(drawable);
        this._inventorySlotTable.setVisible(false);
	}
	
	public void setNormalBackground() {
		Image image = new Image(Utility.STATUSUI_SKIN, "InventoryInGame");
		Drawable drawable = image.getDrawable();
		this.setBackground(drawable);
        this._inventorySlotTable.setVisible(true);
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
	
	public void initInventorySlots() {
       
        InventorySlot inventorySlot1 = new InventorySlot();
        inventorySlot1.addListener(new InventorySlotTooltipListener(_inventorySlotTooltip));
        _dragAndDrop.addTarget(new InventorySlotTarget(inventorySlot1));
        
        InventorySlot inventorySlot2 = new InventorySlot();
        inventorySlot2.addListener(new InventorySlotTooltipListener(_inventorySlotTooltip));
        _dragAndDrop.addTarget(new InventorySlotTarget(inventorySlot2));
        
        InventorySlot inventorySlot3 = new InventorySlot();
        inventorySlot3.addListener(new InventorySlotTooltipListener(_inventorySlotTooltip));
        _dragAndDrop.addTarget(new InventorySlotTarget(inventorySlot3));
        
        InventorySlot inventorySlot4 = new InventorySlot();
        inventorySlot4.addListener(new InventorySlotTooltipListener(_inventorySlotTooltip));
        _dragAndDrop.addTarget(new InventorySlotTarget(inventorySlot4));
        
        InventorySlot inventorySlot5 = new InventorySlot();
        inventorySlot5.addListener(new InventorySlotTooltipListener(_inventorySlotTooltip));
        _dragAndDrop.addTarget(new InventorySlotTarget(inventorySlot5));
        
        InventorySlot inventorySlot6 = new InventorySlot();
        inventorySlot6.addListener(new InventorySlotTooltipListener(_inventorySlotTooltip));
        _dragAndDrop.addTarget(new InventorySlotTarget(inventorySlot6));
		
        _inventorySlotTable.add(inventorySlot1).size(_slotWidth, _slotHeight).spaceRight(12).spaceLeft(0);
        _inventorySlotTable.add(inventorySlot2).size(_slotWidth, _slotHeight).spaceRight(12).spaceLeft(13);
        _inventorySlotTable.add(inventorySlot3).size(_slotWidth, _slotHeight).spaceRight(130).spaceLeft(13);
        _inventorySlotTable.add(inventorySlot4).size(_slotWidth, _slotHeight).spaceRight(12).spaceLeft(130);
        _inventorySlotTable.add(inventorySlot5).size(_slotWidth, _slotHeight).spaceRight(12).spaceLeft(13);
        _inventorySlotTable.add(inventorySlot6).size(_slotWidth, _slotHeight).spaceRight(0).spaceLeft(13);

        
		/*for(int i = 1; i <= _numSlots; i++){
            InventorySlot inventorySlot = new InventorySlot();
            inventorySlot.addListener(new InventorySlotTooltipListener(_inventorySlotTooltip));
            _dragAndDrop.addTarget(new InventorySlotTarget(inventorySlot));

            _inventorySlotTable.add(inventorySlot).size(_slotWidth, _slotHeight).spaceRight(100);

            inventorySlot.addListener(new ClickListener() {
                                         @Override
                                         public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                                             super.touchUp(event, x, y, pointer, button);
                                             if( getTapCount() == 2 ){
                                                 InventorySlot slot = (InventorySlot)event.getListenerActor();
                                                 if( slot.hasItem() ){
                                                     InventoryItem item = slot.getTopInventoryItem();
                                                     if( item.isConsumable() ){
                                                         String itemInfo = item.getItemUseType() + Component.MESSAGE_TOKEN + item.getItemUseTypeValue();
                                                         InventoryInGameUI.this.notify(itemInfo, InventoryObserver.InventoryEvent.ITEM_CONSUMED);
                                                         slot.removeActor(item);
                                                         slot.remove(item);
                                                     }
                                                 }
                                             }
                                         }


                                      }
            );          
        }*/
	}
	

}
