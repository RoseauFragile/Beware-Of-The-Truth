package bewareofthetruth.map.zone1;

import bewareofthetruth.audio.AudioObserver;
import bewareofthetruth.map.Map;
import bewareofthetruth.map.MapFactory;

public class ZoneOneDotThree extends Map{
    private static String _mapPath = "maps/zone1.3.tmx";
  //TODO Soit on garde cette architecture et on cr�er une map pour chaque Level ce qui est en soit efficaces pour un rpg mais LOURD soit on fais une factory plus �labor� avec la bdd
    public ZoneOneDotThree(){
        super(MapFactory.MapType.ZONE_1_3, _mapPath);
    }

    @Override
    public void unloadMusic() {
        notify(AudioObserver.AudioCommand.MUSIC_STOP, AudioObserver.AudioTypeEvent.MUSIC_CASTLEDOOM);
    }

    @Override
    public void loadMusic() {
        notify(AudioObserver.AudioCommand.MUSIC_LOAD, AudioObserver.AudioTypeEvent.MUSIC_CASTLEDOOM);
        notify(AudioObserver.AudioCommand.MUSIC_PLAY_LOOP, AudioObserver.AudioTypeEvent.MUSIC_CASTLEDOOM);
    }

}
