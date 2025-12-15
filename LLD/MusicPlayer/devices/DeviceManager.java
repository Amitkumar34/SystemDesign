package LLD.MusicPlayer.devices;

import LLD.MusicPlayer.devices.adapter.IAudioOutputDevice;
import LLD.MusicPlayer.devices.adapter.impl.BluetoothSpeakerAdapter;
import LLD.MusicPlayer.devices.adapter.impl.HeadphoneAdapter;
import LLD.MusicPlayer.devices.adapter.impl.WiredSpeakerAdapter;
import LLD.MusicPlayer.models.DeviceType;

public class DeviceManager {
    private IAudioOutputDevice device;

    public void connect(DeviceType dt) {
        switch (dt) {
            case HEADPHONES:
                device = new HeadphoneAdapter();
                break;
            case WIRED:
                device = new WiredSpeakerAdapter();
                break;
            case BLUETOOTH:
                device = new BluetoothSpeakerAdapter();
                break;
        }
    }

    public IAudioOutputDevice getDevice() {
        return device;
    }
}
