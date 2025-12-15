package LLD.MusicPlayer.engine;

import LLD.MusicPlayer.devices.adapter.IAudioOutputDevice;
import LLD.MusicPlayer.models.Song;

public class AudioEngine {
    private Song currentPlayingSong;

    public void play(IAudioOutputDevice device,Song song) {
        currentPlayingSong = song;
        device.playAudio(song);
    }

    public void pause(Song song){
        if(currentPlayingSong != null && currentPlayingSong.getName() == song.getName()){

        }
    }
}
