package LLD.MusicPlayer.models;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class Playlist {
    private String name;
    private List<Song> songs;

    public Playlist(String name) {
        this.name = name;
        songs = new ArrayList<>();
    }

    public void addSong(Song song) {
        songs.add(song);
    }
}
