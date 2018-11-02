package bewareofthetruth.map.zone1;

import bewareofthetruth.audio.AudioObserver;
import bewareofthetruth.map.Map;
import bewareofthetruth.map.MapFactory;

public class ZoneOneDotTwo extends Map{
    private static String _mapPath = "maps/zone1.2.tmx";
  //TODO Soit on garde cette architecture et on créer une map pour chaque Level ce qui est en soit efficaces pour un rpg mais LOURD soit on fais une factory plus élaboré avec la bdd
    public ZoneOneDotTwo(){
        super(MapFactory.MapType.ZONE_1_2, _mapPath);
    }

    @Override
    public void unloadMusic() {
        notify(AudioObserver.AudioCommand.MUSIC_STOP, AudioObserver.AudioTypeEvent.MUSIC_TOPWORLD);
    }

    @Override
    public void loadMusic() {
        notify(AudioObserver.AudioCommand.MUSIC_LOAD, AudioObserver.AudioTypeEvent.MUSIC_TOPWORLD);
        notify(AudioObserver.AudioCommand.MUSIC_PLAY_LOOP, AudioObserver.AudioTypeEvent.MUSIC_TOPWORLD);
    }
}