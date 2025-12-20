package LLD.MusicPlayer.devices.adapter.impl;

import LLD.MusicPlayer.devices.adapter.IAudioOutputDevice;
import LLD.MusicPlayer.devices.api.WiredSpeakerAPI;
import LLD.MusicPlayer.models.Song;

public class WiredSpeakerAdapter implements IAudioOutputDevice {
    private final WiredSpeakerAPI wiredSpeakerAPI;


    public WiredSpeakerAdapter() {
        wiredSpeakerAPI = new WiredSpeakerAPI();
    }

    @Override
    public void playAudio(Song song) {
        wiredSpeakerAPI.playAudioViaWired(song.getPath());
    }
}
