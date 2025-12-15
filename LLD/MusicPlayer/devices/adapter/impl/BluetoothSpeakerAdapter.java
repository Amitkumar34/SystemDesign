package LLD.MusicPlayer.devices.adapter.impl;

import LLD.MusicPlayer.devices.adapter.IAudioOutputDevice;
import LLD.MusicPlayer.devices.api.BluetoothSpeakerAPI;
import LLD.MusicPlayer.models.Song;

public class BluetoothSpeakerAdapter implements IAudioOutputDevice {
    private final BluetoothSpeakerAPI bluetoothSpeakerAPI;

    public BluetoothSpeakerAdapter() {
        bluetoothSpeakerAPI = new BluetoothSpeakerAPI();
    }

    @Override
    public void playAudio(Song song) {
        bluetoothSpeakerAPI.playAudioViaBluetooth(song.getPath());
    }
}
