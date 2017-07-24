package techkids.vn.zingmp3.databases;

/**
 * Created by ADMIN on 7/22/2017.
 */

public class TopSongModel {
    private String name;
    private String image;
    private String artist;
    private String linkSourece;

    public TopSongModel() {
    }

    public String getLinkSourece() {
        return linkSourece;
    }

    public void setLinkSourece(String linkSourece) {
        this.linkSourece = linkSourece;
    }

    public TopSongModel(String name, String image, String artist, String linkSourece) {

        this.name = name;
        this.image = image;
        this.artist = artist;
        this.linkSourece = linkSourece;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }
}
