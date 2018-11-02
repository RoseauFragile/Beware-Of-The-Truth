package bewareofthetruth.hud;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Cell;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.Array;

import bewareofthetruth.entity.Entity;
import bewareofthetruth.entity.components.Component;
import bewareofthetruth.inventory.InventoryItem;
import bewareofthetruth.inventory.InventoryItemFactory;
import bewareofthetruth.inventory.InventoryItemLocation;
import bewareofthetruth.inventory.InventoryObserver;
import bewareofthetruth.inventory.InventoryObserver.InventoryEvent;
import bewareofthetruth.utility.Utility;
import bewareofthetruth.inventory.InventorySlot;
import bewareofthetruth.inventory.InventorySlotObserver;
import bewareofthetruth.inventory.InventorySlotSource;
import bewareofthetruth.inventory.InventorySlotTarget;
import bewareofthetruth.inventory.InventorySlotTooltip;
import bewareofthetruth.inventory.InventorySlotTooltipListener;
import bewareofthetruth.inventory.InventorySubject;
import bewareofthetruth.inventory.StatusObserver;
import bewareofthetruth.inventory.StatusObserver.StatusEvent;
import bewareofthetruth.inventory.StatusSubject;
import bewareofthetruth.inventory.InventoryItem.ItemTypeID;

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
    private Label _DPValLabel;
    private int _DPVal = 0;
    private Label _APValLabel;
    private int _APVal = 0;
    private Array<Actor> _inventoryActors;
    public static final String PLAYER_INVENTORY = "Player_Inventory";

	 
	public InventoryInGameUI() {
		super("", Utility.STATUSUI_SKIN, "InventoryInGame");
		
		_inventorySlotTable = new Table();
        _inventorySlotTable.setName("Inventory_Slot_Table");
        _inventorySlotTooltip = new InventorySlotTooltip(Utility.STATUSUI_SKIN);
        _dragAndDrop = new DragAndDrop();
        _inventoryActors = new Array<Actor>();

		
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
        switch(event)
        {
            case ADDED_ITEM:
                InventoryItem addItem = slot.getTopInventoryItem();
                if( addItem == null ) return;
                if( addItem.isInventoryItemOffensive() ){
                    _APVal += addItem.getItemUseTypeValue();
                    _APValLabel.setText(String.valueOf(_APVal));
                    notify(String.valueOf(_APVal), InventoryObserver.InventoryEvent.UPDATED_AP);

                    if( addItem.isInventoryItemOffensiveWand() ){
                        notify(String.valueOf(addItem.getItemUseTypeValue()), InventoryObserver.InventoryEvent.ADD_WAND_AP);
                    }

                }else if( addItem.isInventoryItemDefensive() ){
                    _DPVal += addItem.getItemUseTypeValue();
                    _DPValLabel.setText(String.valueOf(_DPVal));
                    notify(String.valueOf(_DPVal), InventoryObserver.InventoryEvent.UPDATED_DP);
                }
                break;
            case REMOVED_ITEM:
                InventoryItem removeItem = slot.getTopInventoryItem();
                if( removeItem == null ) return;
                if( removeItem.isInventoryItemOffensive() ){
                    _APVal -= removeItem.getItemUseTypeValue();
                    _APValLabel.setText(String.valueOf(_APVal));
                    notify(String.valueOf(_APVal), InventoryObserver.InventoryEvent.UPDATED_AP);

                    if( removeItem.isInventoryItemOffensiveWand() ){
                        notify(String.valueOf(removeItem.getItemUseTypeValue()), InventoryObserver.InventoryEvent.REMOVE_WAND_AP);
                    }

                }else if( removeItem.isInventoryItemDefensive() ){
                    _DPVal -= removeItem.getItemUseTypeValue();
                    _DPValLabel.setText(String.valueOf(_DPVal));
                    notify(String.valueOf(_DPVal), InventoryObserver.InventoryEvent.UPDATED_DP);
                }
                break;
            default:
                break;
        }
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

        

            inventorySlot1.addListener(new ClickListener() {
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
                                     });
            

            inventorySlot2.addListener(new ClickListener() {
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
                                     });
            

            inventorySlot3.addListener(new ClickListener() {
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
                                     });
            

            inventorySlot4.addListener(new ClickListener() {
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
                                     });
            

            inventorySlot5.addListener(new ClickListener() {
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
                                     });
            

            inventorySlot6.addListener(new ClickListener() {
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
                                     });
            }
	
    public DragAndDrop getDragAndDrop(){
        return _dragAndDrop;
    }

    public Table getInventorySlotTable() {
        return _inventorySlotTable;
    }
    
    public static void populateInventory(Table targetTable, Array<InventoryItemLocation> inventoryItems, DragAndDrop draganddrop, String defaultName, boolean disableNonDefaultItems){
        clearInventoryItems(targetTable);

        Array<Cell> cells = targetTable.getCells();
        for(int i = 0; i < inventoryItems.size; i++){
            InventoryItemLocation itemLocation = inventoryItems.get(i);
            ItemTypeID itemTypeID = ItemTypeID.valueOf(itemLocation.getItemTypeAtLocation());
            InventorySlot inventorySlot =  ((InventorySlot)cells.get(itemLocation.getLocationIndex()).getActor());

            for( int index = 0; index < itemLocation.getNumberItemsAtLocation(); index++ ){
                InventoryItem item = InventoryItemFactory.getInstance().getInventoryItem(itemTypeID);
                String itemName =  itemLocation.getItemNameProperty();
                if( itemName == null || itemName.isEmpty() ){
                    item.setName(defaultName);
                }else{
                    item.setName(itemName);
                }

                inventorySlot.add(item);
                if( item.getName().equalsIgnoreCase(defaultName) ){
                    draganddrop.addSource(new InventorySlotSource(inventorySlot, draganddrop));
                }else if( disableNonDefaultItems == false ){
                    draganddrop.addSource(new InventorySlotSource(inventorySlot, draganddrop));
                }
            }
        }
    }
    
    public static Array<InventoryItemLocation> getInventory(Table targetTable){
        Array<Cell> cells = targetTable.getCells();
        Array<InventoryItemLocation> items = new Array<InventoryItemLocation>();
        for(int i = 0; i < cells.size; i++){
            InventorySlot inventorySlot =  ((InventorySlot)cells.get(i).getActor());
            if( inventorySlot == null ) continue;
            int numItems = inventorySlot.getNumItems();
            if( numItems > 0 ){
                items.add(new InventoryItemLocation(
                        i,
                        inventorySlot.getTopInventoryItem().getItemTypeID().toString(),
                        numItems,
                        inventorySlot.getTopInventoryItem().getName()));
            }
        }
        return items;
    }

    public static Array<InventoryItemLocation> getInventoryFiltered(Table targetTable, String filterOutName){
        Array<Cell> cells = targetTable.getCells();
        Array<InventoryItemLocation> items = new Array<InventoryItemLocation>();
        for(int i = 0; i < cells.size; i++){
            InventorySlot inventorySlot =  ((InventorySlot)cells.get(i).getActor());
            if( inventorySlot == null ) continue;
            int numItems = inventorySlot.getNumItems();
            if( numItems > 0 ){
                String topItemName = inventorySlot.getTopInventoryItem().getName();
                if( topItemName.equalsIgnoreCase(filterOutName)) continue;
                //System.out.println("[i] " + i + " itemtype: " + inventorySlot.getTopInventoryItem().getItemTypeID().toString() + " numItems " + numItems);
                items.add(new InventoryItemLocation(
                        i,
                        inventorySlot.getTopInventoryItem().getItemTypeID().toString(),
                        numItems,
                        inventorySlot.getTopInventoryItem().getName()));
            }
        }
        return items;
    }

    public static Array<InventoryItemLocation> getInventory(Table targetTable, String name){
        Array<Cell> cells = targetTable.getCells();
        Array<InventoryItemLocation> items = new Array<InventoryItemLocation>();
        for(int i = 0; i < cells.size; i++){
            InventorySlot inventorySlot =  ((InventorySlot)cells.get(i).getActor());
            if( inventorySlot == null ) continue;
            int numItems = inventorySlot.getNumItems(name);
            if( numItems > 0 ){
                //System.out.println("[i] " + i + " itemtype: " + inventorySlot.getTopInventoryItem().getItemTypeID().toString() + " numItems " + numItems);
                items.add(new InventoryItemLocation(
                        i,
                        inventorySlot.getTopInventoryItem().getItemTypeID().toString(),
                        numItems,
                        name));
            }
        }
        return items;
    }

    public static Array<InventoryItemLocation> getInventoryFiltered(Table sourceTable, Table targetTable, String filterOutName){
        Array<InventoryItemLocation> items = getInventoryFiltered(targetTable, filterOutName);
        Array<Cell> sourceCells = sourceTable.getCells();
        int index = 0;
        for( InventoryItemLocation item : items ) {
            for (; index < sourceCells.size; index++) {
                InventorySlot inventorySlot = ((InventorySlot) sourceCells.get(index).getActor());
                if (inventorySlot == null) continue;
                int numItems = inventorySlot.getNumItems();
                if (numItems == 0) {
                    item.setLocationIndex(index);
                    //System.out.println("[index] " + index + " itemtype: " + item.getItemTypeAtLocation() + " numItems " + numItems);
                    index++;
                    break;
                }
            }
            if( index == sourceCells.size ){
                //System.out.println("[index] " + index + " itemtype: " + item.getItemTypeAtLocation() + " numItems " + item.getNumberItemsAtLocation());
                item.setLocationIndex(index-1);
            }
        }
        return items;
    }


    public static void setInventoryItemNames(Table targetTable, String name){
        Array<Cell> cells = targetTable.getCells();
        for(int i = 0; i < cells.size; i++){
            InventorySlot inventorySlot =  ((InventorySlot)cells.get(i).getActor());
            if( inventorySlot == null ) continue;
            inventorySlot.updateAllInventoryItemNames(name);
        }
    }

    public boolean doesInventoryHaveSpace(){
        Array<Cell> sourceCells = _inventorySlotTable.getCells();
        int index = 0;

        for (; index < sourceCells.size; index++) {
            InventorySlot inventorySlot = ((InventorySlot) sourceCells.get(index).getActor());
            if (inventorySlot == null) continue;
            int numItems = inventorySlot.getNumItems();
            if (numItems == 0) {
                return true;
            }else{
                index++;
            }
        }
        return false;
    }

    public void addEntityToInventory(Entity entity, String itemName){
        Array<Cell> sourceCells = _inventorySlotTable.getCells();
        int index = 0;

            for (; index < sourceCells.size; index++) {
                InventorySlot inventorySlot = ((InventorySlot) sourceCells.get(index).getActor());
                if (inventorySlot == null) continue;
                int numItems = inventorySlot.getNumItems();
                if (numItems == 0) {
                    InventoryItem inventoryItem = InventoryItemFactory.getInstance().getInventoryItem(ItemTypeID.valueOf(entity.getEntityConfig().getItemTypeID()));
                    inventoryItem.setName(itemName);
                    inventorySlot.add(inventoryItem);
                    _dragAndDrop.addSource(new InventorySlotSource(inventorySlot, _dragAndDrop));
                    break;
                }
            }
    }

    public void removeQuestItemFromInventory(String questID){
        Array<Cell> sourceCells = _inventorySlotTable.getCells();
        for (int index = 0; index < sourceCells.size; index++) {
            InventorySlot inventorySlot = ((InventorySlot) sourceCells.get(index).getActor());
            if (inventorySlot == null) continue;
            InventoryItem item = inventorySlot.getTopInventoryItem();
            if( item == null ) continue;
            String inventoryItemName = item.getName();
            if (inventoryItemName != null && inventoryItemName.equals(questID) ) {
                inventorySlot.clearAllInventoryItems(false);
            }
        }
    }

    public Array<Actor> getInventoryActors(){
        return _inventoryActors;
    }
    
    public static void clearInventoryItems(Table targetTable){
        Array<Cell> cells = targetTable.getCells();
        for( int i = 0; i < cells.size; i++){
            InventorySlot inventorySlot = (InventorySlot)cells.get(i).getActor();
            if( inventorySlot == null ) continue;
            inventorySlot.clearAllInventoryItems(false);
        }
    }
}
                                         


                                      
                     
        
	
	


