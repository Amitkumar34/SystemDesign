package LLD.MusicPlayer.devices.adapter.impl;

import LLD.MusicPlayer.devices.adapter.IAudioOutputDevice;
import LLD.MusicPlayer.devices.api.HeadphoneAPI;
import LLD.MusicPlayer.models.Song;

public class HeadphoneAdapter implements IAudioOutputDevice {
    private final HeadphoneAPI headphoneAPI;

    public HeadphoneAdapter() {
        headphoneAPI = new HeadphoneAPI();
    }

    @Override
    public void playAudio(Song song) {
        headphoneAPI.playAudioViaHeadphone(song.getPath());
    }
}
