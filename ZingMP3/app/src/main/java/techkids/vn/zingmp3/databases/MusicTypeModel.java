package techkids.vn.zingmp3.databases;

/**
 * Created by ADMIN on 7/19/2017.
 */

public class MusicTypeModel {
    private String id;
    private String key;
    private int imageID;

    public MusicTypeModel() {
    }

    public MusicTypeModel(String id, String key, int imageID) {
        this.id = id;
        this.key = key;
        this.imageID = imageID;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public int getImageID() {
        return imageID;
    }

    public void setImageID(int imageID) {
        this.imageID = imageID;
    }
}
