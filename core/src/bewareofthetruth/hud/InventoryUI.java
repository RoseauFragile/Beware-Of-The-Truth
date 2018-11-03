package bewareofthetruth.hud;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Cell;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Align;
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
import bewareofthetruth.inventory.InventorySlot;
import bewareofthetruth.inventory.InventorySlotObserver;
import bewareofthetruth.inventory.InventorySlotSource;
import bewareofthetruth.inventory.InventorySlotTarget;
import bewareofthetruth.inventory.InventorySlotTooltip;
import bewareofthetruth.inventory.InventorySlotTooltipListener;
import bewareofthetruth.inventory.InventorySubject;
import bewareofthetruth.inventory.InventoryItem.ItemTypeID;
import bewareofthetruth.inventory.InventoryItem.ItemUseType;
import bewareofthetruth.utility.Utility;

public class InventoryUI extends Window implements InventorySubject, InventorySlotObserver{

    public final static int _numSlots = 14;
    public static final String PLAYER_INVENTORY = "Player_Inventory";
    public static final String BAR_INVENTORY = "Bar_Inventory";
    public static final String STORE_INVENTORY = "Store_Inventory";
	private static String TAG = InventoryUI.class.getSimpleName();

    private int _lengthSlotRow = 7;
    private Table _inventorySlotTable;
    private Table _playerSlotsTable;
    private Table _equipSlotsLeft;
    private Table _equipSlotsRight;

    private DragAndDrop _dragAndDrop;
    private Array<Actor> _inventoryActors;

    private Label _DPValLabel;
    private int _DPVal = 0;
    private Label _APValLabel;
    private int _APVal = 0;

    private final int _slotWidth = 52;
    private final int _slotHeight = 52;
    private final int _equipSlotWidth = 102;
    private final int _equipSlotHeight = 89;

    private Array<InventoryObserver> _observers;

    private InventorySlotTooltip _inventorySlotTooltip;

    public InventoryUI(){
        super("", Utility.STATUSUI_SKIN, "MenuInventory");

        this.setSize(this.getBackground().getRightWidth(), this.getBackground().getTopHeight());
       
        _observers = new Array<InventoryObserver>();

        _dragAndDrop = new DragAndDrop();
        _inventoryActors = new Array<Actor>();

        //create
        _inventorySlotTable = new Table();
        _inventorySlotTable.setName("Inventory_Slot_Table");

        _playerSlotsTable = new Table();
        
        _equipSlotsLeft = new Table();
        _equipSlotsLeft.setName("Equipment_Slot_Table_Left");
        _equipSlotsLeft.defaults().space(10);
        
        _equipSlotsRight = new Table();
        _equipSlotsRight.setName("Equipment_Slot_Table_Right");
        //_equipSlotsRight.defaults().space(10);
        
        _inventorySlotTooltip = new InventorySlotTooltip(Utility.STATUSUI_SKIN);

        
        //ok
        Label DPLabel = new Label("Defense: ", Utility.STATUSUI_SKIN);
        _DPValLabel = new Label(String.valueOf(_DPVal), Utility.STATUSUI_SKIN);

        Label APLabel = new Label("Attack : ", Utility.STATUSUI_SKIN);
        _APValLabel = new Label(String.valueOf(_APVal), Utility.STATUSUI_SKIN);

        Table labelTable = new Table();
        labelTable.add(DPLabel).align(Align.left);
        labelTable.add(_DPValLabel).align(Align.center);
        labelTable.row();
        labelTable.row();
        labelTable.add(APLabel).align(Align.left);
        labelTable.add(_APValLabel).align(Align.center);

        InventorySlot headSlot = new InventorySlot(
                ItemUseType.ARMOR_HELMET.getValue(),
                new Image(Utility.ITEMS_TEXTUREATLAS.findRegion("inv_helmet")));
        
        InventorySlot headSlot2 = new InventorySlot(
                ItemUseType.ARMOR_HELMET.getValue(),
                new Image(Utility.ITEMS_TEXTUREATLAS.findRegion("inv_helmet")));
        
        InventorySlot headSlot3 = new InventorySlot(
                ItemUseType.ARMOR_HELMET.getValue(),
                new Image(Utility.ITEMS_TEXTUREATLAS.findRegion("inv_helmet")));
        
        InventorySlot headSlot4 = new InventorySlot(
                ItemUseType.ARMOR_HELMET.getValue(),
                new Image(Utility.ITEMS_TEXTUREATLAS.findRegion("inv_helmet")));

        InventorySlot leftArmSlot = new InventorySlot(
                ItemUseType.WEAPON_ONEHAND.getValue() |
                ItemUseType.WEAPON_TWOHAND.getValue() |
                ItemUseType.ARMOR_SHIELD.getValue() |
                ItemUseType.WAND_ONEHAND.getValue() |
                ItemUseType.WAND_TWOHAND.getValue(),
                new Image(Utility.ITEMS_TEXTUREATLAS.findRegion("inv_weapon"))
        );

        InventorySlot rightArmSlot = new InventorySlot(
                ItemUseType.WEAPON_ONEHAND.getValue() |
                ItemUseType.WEAPON_TWOHAND.getValue() |
                ItemUseType.ARMOR_SHIELD.getValue() |
                ItemUseType.WAND_ONEHAND.getValue() |
                ItemUseType.WAND_TWOHAND.getValue(),
                new Image(Utility.ITEMS_TEXTUREATLAS.findRegion("inv_shield"))
        );

        InventorySlot chestSlot = new InventorySlot(
                ItemUseType.ARMOR_CHEST.getValue(),
                new Image(Utility.ITEMS_TEXTUREATLAS.findRegion("inv_chest")));

        InventorySlot legsSlot = new InventorySlot(
                ItemUseType.ARMOR_FEET.getValue(),
                new Image(Utility.ITEMS_TEXTUREATLAS.findRegion("inv_boot")));

        headSlot.addListener(new InventorySlotTooltipListener(_inventorySlotTooltip));
        headSlot2.addListener(new InventorySlotTooltipListener(_inventorySlotTooltip));
        headSlot3.addListener(new InventorySlotTooltipListener(_inventorySlotTooltip));
        headSlot4.addListener(new InventorySlotTooltipListener(_inventorySlotTooltip));
        leftArmSlot.addListener(new InventorySlotTooltipListener(_inventorySlotTooltip));
        rightArmSlot.addListener(new InventorySlotTooltipListener(_inventorySlotTooltip));
        chestSlot.addListener(new InventorySlotTooltipListener(_inventorySlotTooltip));
        legsSlot.addListener(new InventorySlotTooltipListener(_inventorySlotTooltip));

        headSlot.addObserver(this);
        headSlot2.addObserver(this);
        headSlot3.addObserver(this);
        headSlot4.addObserver(this);
        leftArmSlot.addObserver(this);
        rightArmSlot.addObserver(this);
        chestSlot.addObserver(this);
        legsSlot.addObserver(this);

        _dragAndDrop.addTarget(new InventorySlotTarget(headSlot));
        _dragAndDrop.addTarget(new InventorySlotTarget(headSlot2));
        _dragAndDrop.addTarget(new InventorySlotTarget(headSlot3));
        _dragAndDrop.addTarget(new InventorySlotTarget(headSlot4));
        _dragAndDrop.addTarget(new InventorySlotTarget(leftArmSlot));
        _dragAndDrop.addTarget(new InventorySlotTarget(chestSlot));
        _dragAndDrop.addTarget(new InventorySlotTarget(rightArmSlot));
        _dragAndDrop.addTarget(new InventorySlotTarget(legsSlot));

        //TODO attention
        _playerSlotsTable.setBackground(new Image(Utility.STATUSUI_SKIN, "MenuInventory").getDrawable());

        //layout
        for(int i = 1; i <= _numSlots; i++){
        	
        	
            	InventorySlot inventorySlot = new InventorySlot("right_case");
            inventorySlot.addListener(new InventorySlotTooltipListener(_inventorySlotTooltip));
            _dragAndDrop.addTarget(new InventorySlotTarget(inventorySlot));

            _inventorySlotTable.add(inventorySlot).size(128, 110);
            this.addListener(inventorySlot);


            if(i % _lengthSlotRow == 0){
                _inventorySlotTable.row();
            }
        }
        
        

       // _equipSlotsRight.add();
      
        _equipSlotsRight.row();
        _equipSlotsRight.add(headSlot).size(_equipSlotWidth, _equipSlotHeight).spaceRight(310).spaceTop(500);
        _equipSlotsRight.add(leftArmSlot).size(_equipSlotWidth, _equipSlotHeight).spaceLeft(310).spaceTop(500);
        
        _equipSlotsRight.row();

        _equipSlotsRight.add(chestSlot).size(_equipSlotWidth, _equipSlotHeight).spaceRight(310).spaceTop(30);
        _equipSlotsRight.add(rightArmSlot).size(_equipSlotWidth, _equipSlotHeight).spaceLeft(310).spaceTop(30);
        
        _equipSlotsRight.row();

       // _equipSlotsRight.add();
        _equipSlotsRight.add(legsSlot).size(_equipSlotWidth, _equipSlotHeight).spaceRight(310).spaceTop(30);
        _equipSlotsRight.add(headSlot2).size(_equipSlotWidth, _equipSlotHeight).spaceLeft(310).spaceTop(30);
        
        _equipSlotsRight.row();
        
        _equipSlotsRight.add(headSlot3).size(_equipSlotWidth, _equipSlotHeight).spaceRight(310).spaceTop(30);
        _equipSlotsRight.add(headSlot4).size(_equipSlotWidth, _equipSlotHeight).spaceLeft(310).spaceTop(30);
        
        _equipSlotsRight.row();


        _playerSlotsTable.add(_equipSlotsRight);

        
        
        _inventoryActors.add(_inventorySlotTooltip);


        //_playerSlotsTable.setSize(_playerSlotsTable.getWidth() - 50, _playerSlotsTable.getHeight() - 50);
        //defaults().expand().fill();
        
        this.add(_playerSlotsTable).padBottom(-100).padTop(-100).top();//.padBottom(20);
        
        Pixmap pixmap = new Pixmap(200, 200, Format.RGBA8888);
        Texture texture = new Texture(pixmap);
        Image image = new Image(texture);
        this.setBackground(image.getDrawable());
        
        this.row();
        this.add(_inventorySlotTable)/*.align(Align.bottom)*/;
        this.row();
        this.debug();
        this.pack();
        
    
        
    }

    

	
    public DragAndDrop getDragAndDrop(){
        return _dragAndDrop;
    }

    public Table getInventorySlotTable() {
        return _inventorySlotTable;
    }

    public Table getEquipSlotTable() {
        return _equipSlotsRight;
    }

    public void resetEquipSlots(){
        _DPVal = 0;
        _APVal = 0;

        _DPValLabel.setText(String.valueOf(_DPVal));
        notify(String.valueOf(_DPVal), InventoryObserver.InventoryEvent.UPDATED_DP);

        _APValLabel.setText(String.valueOf(_APVal));
        notify(String.valueOf(_APVal), InventoryObserver.InventoryEvent.UPDATED_AP);
    }

    @SuppressWarnings("rawtypes")
	public static void clearInventoryItems(Table targetTable){
        Array<Cell> cells = targetTable.getCells();
        for( int i = 0; i < cells.size; i++){
            InventorySlot inventorySlot = (InventorySlot)cells.get(i).getActor();
            if( inventorySlot == null ) continue;
            inventorySlot.clearAllInventoryItems(false);
        }
    }

    @SuppressWarnings("rawtypes")
	public static Array<InventoryItemLocation> removeInventoryItems(String name, Table inventoryTable){
        Array<Cell> cells = inventoryTable.getCells();
        Array<InventoryItemLocation> items = new Array<InventoryItemLocation>();
        for(int i = 0; i < cells.size; i++){
            InventorySlot inventorySlot =  ((InventorySlot)cells.get(i).getActor());
            if( inventorySlot == null ) continue;
            inventorySlot.removeAllInventoryItemsWithName(name);
        }
        return items;
    }

    @SuppressWarnings("rawtypes")
	public static void populateInventory(Table targetTable, Array<InventoryItemLocation> inventoryItems, DragAndDrop draganddrop, String defaultName, boolean disableNonDefaultItems){
        clearInventoryItems(targetTable);
        Gdx.app.debug(TAG , "populate Inventory activated");
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

    @SuppressWarnings("rawtypes")
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

    @SuppressWarnings("rawtypes")
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

    @SuppressWarnings("rawtypes")
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

    @SuppressWarnings("rawtypes")
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


    @SuppressWarnings("rawtypes")
	public static void setInventoryItemNames(Table targetTable, String name){
        Array<Cell> cells = targetTable.getCells();
        for(int i = 0; i < cells.size; i++){
            InventorySlot inventorySlot =  ((InventorySlot)cells.get(i).getActor());
            if( inventorySlot == null ) continue;
            inventorySlot.updateAllInventoryItemNames(name);
        }
    }

    @SuppressWarnings("rawtypes")
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

    @SuppressWarnings("rawtypes")
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

    @SuppressWarnings("rawtypes")
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
        _observers.add(inventoryObserver);
    }

    @Override
    public void removeObserver(InventoryObserver inventoryObserver) {
        _observers.removeValue(inventoryObserver, true);
    }

    @Override
    public void removeAllObservers() {
        for(InventoryObserver observer: _observers){
            _observers.removeValue(observer, true);
        }
    }

    @Override
    public void notify(String value, InventoryObserver.InventoryEvent event) {
        for(InventoryObserver observer: _observers){
            observer.onNotify(value, event);
        }
    }

	public InventorySlotTooltip getInventorySlotTooltip() {
		return this._inventorySlotTooltip;
	}
	
	private void addListener( InventorySlot inventorySlot) {
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
                            InventoryUI.this.notify(itemInfo, InventoryObserver.InventoryEvent.ITEM_CONSUMED);
                            slot.removeActor(item);
                            slot.remove(item);
                        }
                    }
                }
            }


         }
);
	}
}