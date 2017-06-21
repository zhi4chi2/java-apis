package org.example.demo.java;

import java.util.ArrayList;
import java.util.List;

public abstract class MusicChapter {
    protected final List<Artist> artists;
    protected final List<Album> albums;

    public MusicChapter() {
        artists = new ArrayList<>();
        albums = new ArrayList<>();
        loadData("");
    }

    private void loadData(String file) {
    }
}