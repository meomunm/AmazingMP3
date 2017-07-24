package techkids.vn.zingmp3.networks.json_topsong_models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by ADMIN on 7/22/2017.
 */

public class Entry {
    @SerializedName("im:name")
    Name name;

    @SerializedName("im:image")
    List<Image> images;

    @SerializedName("im:artist")
    Artist artist;

    public Entry(Name name, List<Image> images, Artist artist) {
        this.name = name;
        this.images = images;
        this.artist = artist;
    }

    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }
}
