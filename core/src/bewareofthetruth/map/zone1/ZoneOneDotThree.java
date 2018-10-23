package bewareofthetruth.map.zone1;

import bewareofthetruth.audio.AudioObserver;
import bewareofthetruth.audio.*;
import bewareofthetruth.map.Map;
import bewareofthetruth.map.MapFactory;
import bewareofthetruth.map.MapFactory.MapType;

public class ZoneOneDotThree extends Map{
    private static String _mapPath = "maps/castle_of_doom.tmx";
  //TODO Soit on garde cette architecture et on créer une map pour chaque Level ce qui est en soit efficaces pour un rpg mais LOURD soit on fais une factory plus élaboré avec la bdd
    public ZoneOneDotThree(){
        super(MapFactory.MapType.CASTLE_OF_DOOM, _mapPath);

       /* Array<Vector2> candleEffectPositions = getParticleEffectSpawnPositions(ParticleEffectFactory.ParticleEffectType.CANDLE_FIRE);
        for( Vector2 position: candleEffectPositions ){
            _mapParticleEffects.add(ParticleEffectFactory.getParticleEffect(ParticleEffectFactory.ParticleEffectType.CANDLE_FIRE, position));
        }

        Array<Vector2> lavaSmokeEffectPositions = getParticleEffectSpawnPositions(ParticleEffectFactory.ParticleEffectType.LAVA_SMOKE);
        for( Vector2 position: lavaSmokeEffectPositions ){
            _mapParticleEffects.add(ParticleEffectFactory.getParticleEffect(ParticleEffectFactory.ParticleEffectType.LAVA_SMOKE, position));
        }*/
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
