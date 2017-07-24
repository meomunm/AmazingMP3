package techkids.vn.zingmp3.events;

import techkids.vn.zingmp3.databases.MusicTypeModel;

/**
 * Created by ADMIN on 7/22/2017.
 */

public class OnClickMusicType {
    private MusicTypeModel musicTypeModel;

    public OnClickMusicType(MusicTypeModel musicTypeModel) {
        this.musicTypeModel = musicTypeModel;
    }

    public MusicTypeModel getMusicTypeModel() {
        return musicTypeModel;
    }

    public void setMusicTypeModel(MusicTypeModel musicTypeModel) {
        this.musicTypeModel = musicTypeModel;
    }
}
