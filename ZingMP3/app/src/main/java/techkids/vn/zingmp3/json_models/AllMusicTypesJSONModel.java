package techkids.vn.zingmp3.json_models;

import java.util.List;

/**
 * Created by ADMIN on 7/15/2017.
 */

public class AllMusicTypesJSONModel {
    private List<MusicTypeJSONModel> subgenres;

    public AllMusicTypesJSONModel(List<MusicTypeJSONModel> subgenres) {
        this.subgenres = subgenres;
    }

    public List<MusicTypeJSONModel> getSubgenres() {
        return subgenres;
    }

    public void setSubgenres(List<MusicTypeJSONModel> subgenres) {
        this.subgenres = subgenres;
    }
}
