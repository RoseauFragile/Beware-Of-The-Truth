package bewareofthetruth.battle;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;

import java.util.ArrayList;

public class LevelTable {
    private String levelID;
    private int xpMax;
    private int hpMax;
    private int waterMax;

    public String getLevelID() {
        return levelID;
    }

    public void setLevelID(String levelID) {
        this.levelID = levelID;
    }

    public int getXpMax() {
        return xpMax;
    }

    public void setXpMax(int xpMax) {
        this.xpMax = xpMax;
    }

    public int getHpMax() {
        return hpMax;
    }

    public void setHpMax(int hpMax) {
        this.hpMax = hpMax;
    }

    public int getWaterMax() {
        return waterMax;
    }

    public void setWaterMax(int mpMax) {
        this.waterMax = mpMax;
    }

    static public Array<LevelTable> getLevelTables(String configFilePath){
        Json json = new Json();
        Array<LevelTable> levelTable = new Array<LevelTable>();

        @SuppressWarnings("unchecked")
		ArrayList<JsonValue> list = json.fromJson(ArrayList.class, Gdx.files.internal(configFilePath));

        for (JsonValue jsonVal : list) {
            LevelTable table = json.readValue(LevelTable.class, jsonVal);
            levelTable.add(table);
        }

        return levelTable;
    }
}
