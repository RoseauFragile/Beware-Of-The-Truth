package bewareofthetruth.hud;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;
import bewareofthetruth.battle.LevelTable;
import bewareofthetruth.inventory.StatusObserver;
import bewareofthetruth.inventory.StatusSubject;
import bewareofthetruth.utility.Utility;

public class StatusUI extends Window implements StatusSubject {
    private Image _hpBar;
    private Image _waterBar;
    private Image _xpBar;
    @SuppressWarnings("unused")
	private String TAG = StatusUI.class.getSimpleName();

    private ImageButton _inventoryButton;
    private ImageButton _questButton;
   // private ImageButton _testButton;
    private Array<StatusObserver> _observers;

    private Array<LevelTable> _levelTables;
    private static final String LEVEL_TABLE_CONFIG = "scripts/level_tables.json";

    //Attributes
    private int _levelVal = 0;
    private int _goldVal = 0;
    private int _hpVal = 0;
    private int _waterVal = 0;
    private int _xpVal = 0;

    private int _xpCurrentMax = 0;
    private int _hpCurrentMax = 0;
    private int _waterCurrentMax = 0;

    private Label _hpValLabel;
    private Label _waterValLabel;
    private Label _xpValLabel;
    private Label _levelValLabel;
    private Label _goldValLabel;

    private float _barWidth = 0;
    private float _barHeight = 0;
    private float _barWaterWidth = 0;
    private float _barWaterHeight = 0;

	

    public StatusUI(){
        super("stats", Utility.STATUSUI_SKIN);

        _levelTables = LevelTable.getLevelTables(LEVEL_TABLE_CONFIG);
        _observers = new Array<StatusObserver>();

        //test Widget
        WidgetGroup group4 = new WidgetGroup();
        WidgetGroup group5 = new WidgetGroup();
        
        //Test Images
        _hpBar = new Image(Utility.STATUSUI_TEXTUREATLAS.findRegion("ThreeBar"));
        Image lifeBar = new Image(Utility.STATUSUI_TEXTUREATLAS.findRegion("LifeBar"));
        _waterBar = new Image(Utility.STATUSUI_TEXTUREATLAS.findRegion("FourWaterBar"));
        Image waterBar = new Image(Utility.STATUSUI_TEXTUREATLAS.findRegion("WaterBar"));

        
        _barWidth = _hpBar.getWidth();
        _barHeight = _hpBar.getHeight();
        
        _barWaterWidth = _waterBar.getWidth();
        _barWaterHeight = _waterBar.getHeight();


        //labels
        Label hpLabel = new Label(" hp: ", Utility.STATUSUI_SKIN);
        _hpValLabel = new Label(String.valueOf(_hpVal), Utility.STATUSUI_SKIN);
        Label waterLabel = new Label(" water: ", Utility.STATUSUI_SKIN);
        _waterValLabel = new Label(String.valueOf(_waterVal), Utility.STATUSUI_SKIN);
        Label xpLabel = new Label(" xp: ", Utility.STATUSUI_SKIN);
        _xpValLabel = new Label(String.valueOf(_xpVal), Utility.STATUSUI_SKIN);
        Label levelLabel = new Label(" lv: ", Utility.STATUSUI_SKIN);
        _levelValLabel = new Label(String.valueOf(_levelVal), Utility.STATUSUI_SKIN);
        Label goldLabel = new Label(" gp: ", Utility.STATUSUI_SKIN);
        _goldValLabel = new Label(String.valueOf(_goldVal), Utility.STATUSUI_SKIN);

        //buttons
        _inventoryButton= new ImageButton(Utility.STATUSUI_SKIN, "chest-button");
        _inventoryButton.getImageCell().size(200, 200);

        _questButton = new ImageButton(Utility.STATUSUI_SKIN, "quest-button");
        _questButton.getImageCell().size(200,200);

        
        //Align images
        _waterBar.setPosition(((waterBar.getX() + waterBar.getWidth()) / 2 - ((_waterBar.getX() +_waterBar.getWidth())) + ((_waterBar.getX() +_waterBar.getWidth()) / 2)) +1, waterBar.getY() + ((waterBar.getHeight() - _waterBar.getHeight())/2));
        _hpBar.setPosition(((lifeBar.getX() + lifeBar.getWidth()) / 2 - ((_hpBar.getX() +_hpBar.getWidth())) + ((_hpBar.getX() +_hpBar.getWidth()) / 2)) +1, lifeBar.getY() + ((lifeBar.getHeight() - _hpBar.getHeight())/2));

        group4.addActor(lifeBar);
        group4.addActor(_hpBar);
        group5.addActor(waterBar);
        group5.addActor(_waterBar);
        

        //Add to layout
        defaults().expand().fill();

        //account for the title padding
        this.pad(this.getPadTop() + 50, 30, 10, 10);
        
        this.add(group4).size(lifeBar.getWidth(), lifeBar.getHeight()).padRight(10).align(Align.left);
        this.add(hpLabel);
        this.add(_hpValLabel).align(Align.left);
        this.row();
        
        this.add(group5).size(waterBar.getWidth(), waterBar.getHeight()).padRight(10).align(Align.left);
        this.add(waterLabel);
        this.add(_waterValLabel).align(Align.left);
        this.row();
        
        this.add(levelLabel).align(Align.left);
        this.add(_levelValLabel).align(Align.left);
        this.row();
        
        this.add(goldLabel);
        this.add(_goldValLabel).align(Align.left);
        this.add(xpLabel);
        this.add(_xpValLabel).align(Align.left).padRight(20);
        this.row();
        

        this.add(_questButton).align(Align.center);
        this.add(_inventoryButton).align(Align.right);
        this.row();
        
        this.pack();
        this.debug();
        
        
    }

    public ImageButton getInventoryButton() {
        return _inventoryButton;
    }

    public ImageButton getQuestButton() {
        return _questButton;
   }

    public int getLevelValue(){
        return _levelVal;
    }
    public void setLevelValue(int levelValue){
        this._levelVal = levelValue;
        _levelValLabel.setText(String.valueOf(_levelVal));
        notify(_levelVal, StatusObserver.StatusEvent.UPDATED_LEVEL);
    }

    public int getGoldValue(){
        return _goldVal;
    }
    public void setGoldValue(int goldValue){
        this._goldVal = goldValue;
        _goldValLabel.setText(String.valueOf(_goldVal));
        notify(_goldVal, StatusObserver.StatusEvent.UPDATED_GP);
    }

    public void addGoldValue(int goldValue){
        this._goldVal += goldValue;
        _goldValLabel.setText(String.valueOf(_goldVal));
        notify(_goldVal, StatusObserver.StatusEvent.UPDATED_GP);
    }

    public int getXPValue(){
        return _xpVal;
    }

    public void addXPValue(int xpValue){
        this._xpVal += xpValue;

        if( _xpVal > _xpCurrentMax ){
            updateToNewLevel();
        }

        _xpValLabel.setText(String.valueOf(_xpVal));

        updateBar(_xpBar, _xpVal, _xpCurrentMax);

        notify(_xpVal, StatusObserver.StatusEvent.UPDATED_XP);
    }

    public void setXPValue(int xpValue){
        this._xpVal = xpValue;

        if( _xpVal > _xpCurrentMax ){
            updateToNewLevel();
        }

        //_xpValLabel.setText(String.valueOf(_xpVal));

       // updateBar(_xpBar, _xpVal, _xpCurrentMax);

        notify(_xpVal, StatusObserver.StatusEvent.UPDATED_XP);
    }

    public void setXPValueMax(int maxXPValue){
        this._xpCurrentMax = maxXPValue;
    }

    public void setStatusForLevel(int level){
        for( LevelTable table: _levelTables ){
            if( Integer.parseInt(table.getLevelID()) == level ){
                setXPValueMax(table.getXpMax());
                setXPValue(0);

                setHPValueMax(table.getHpMax());
                setHPValue(table.getHpMax());

                setWaterValueMax(table.getWaterMax());
                setWaterValue(table.getWaterMax());

                setLevelValue(Integer.parseInt(table.getLevelID()));
                return;
            }
        }
    }

    public void updateToNewLevel(){
        for( LevelTable table: _levelTables ){
            //System.out.println("XPVAL " + _xpVal + " table XPMAX " + table.getXpMax() );
            if( _xpVal > table.getXpMax() ){
                continue;
            }else{
                setXPValueMax(table.getXpMax());

                setHPValueMax(table.getHpMax());
                setHPValue(table.getHpMax());

                setWaterValueMax(table.getWaterMax());
                setWaterValue(table.getWaterMax());

                setLevelValue(Integer.parseInt(table.getLevelID()));
                notify(_levelVal, StatusObserver.StatusEvent.LEVELED_UP);
                return;
            }
        }
    }

    public int getXPValueMax(){
        return _xpCurrentMax;
    }

    //HP
    public int getHPValue(){
        return _hpVal;
    }

    public void removeHPValue(int hpValue){
        _hpVal = MathUtils.clamp(_hpVal - hpValue, 0, _hpCurrentMax);
        _hpValLabel.setText(String.valueOf(_hpVal));

        updateBar(_hpBar, _hpVal, _hpCurrentMax);

        notify(_hpVal, StatusObserver.StatusEvent.UPDATED_HP);
    }

    public void addHPValue(int hpValue){
        _hpVal = MathUtils.clamp(_hpVal + hpValue, 0, _hpCurrentMax);
        _hpValLabel.setText(String.valueOf(_hpVal));

        updateBar(_hpBar, _hpVal, _hpCurrentMax);

        notify(_hpVal, StatusObserver.StatusEvent.UPDATED_HP);
    }

    public void setHPValue(int hpValue){
        this._hpVal = hpValue;
        _hpValLabel.setText(String.valueOf(_hpVal));

        updateBar(_hpBar, _hpVal, _hpCurrentMax);

        notify(_hpVal, StatusObserver.StatusEvent.UPDATED_HP);
    }

    public void setHPValueMax(int maxHPValue){
        this._hpCurrentMax = maxHPValue;
    }

    public int getHPValueMax(){
        return _hpCurrentMax;
    }

    //Water
    public int getWaterValue(){
        return _waterVal;
    }

    public void removeWaterValue(int waterValue){
        _waterVal = MathUtils.clamp(_waterVal - waterValue, 0, _waterCurrentMax);
        _waterValLabel.setText(String.valueOf(_waterVal));

        updateWaterBar(_waterBar, _waterVal, _waterCurrentMax);

        notify(_waterVal, StatusObserver.StatusEvent.UPDATED_WATER);
    }

    public void addWaterValue(int waterValue){
        _waterVal = MathUtils.clamp(_waterVal + waterValue, 0, _waterCurrentMax);
        _waterValLabel.setText(String.valueOf(_waterVal));

        updateWaterBar(_waterBar, _waterVal, _waterCurrentMax);

        notify(_waterVal, StatusObserver.StatusEvent.UPDATED_WATER);
    }

    public void setWaterValue(int waterValue){
        this._waterVal = waterValue;
        _waterValLabel.setText(String.valueOf(_waterVal));

        updateWaterBar(_waterBar, _waterVal, _waterCurrentMax);

        notify(_waterVal, StatusObserver.StatusEvent.UPDATED_WATER);
    }

    public void setWaterValueMax(int maxWaterValue){
        this._waterCurrentMax = maxWaterValue;
    }

    public int getWaterValueMax(){
        return _waterCurrentMax;
    }

    public void updateBar(Image bar, int currentVal, int maxVal){
        int val = MathUtils.clamp(currentVal, 0, maxVal);
        float tempPercent = (float) val / (float) maxVal;
        float percentage = MathUtils.clamp(tempPercent, 0, 100);
        bar.setSize(_barWidth*percentage, _barHeight);
    }
    
    public void updateWaterBar(Image bar, int currentVal, int maxVal){
        int val = MathUtils.clamp(currentVal, 0, maxVal);
        float tempPercent = (float) val / (float) maxVal;
        float percentage = MathUtils.clamp(tempPercent, 0, 100);
        bar.setSize(_barWaterWidth*percentage, _barWaterHeight);
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

}