package techkids.vn.zingmp3.events;

import techkids.vn.zingmp3.databases.TopSongModel;

/**
 * Created by ADMIN on 7/28/2017.
 */

public class OnClickTopSong {
    private TopSongModel topSongModel;

    public OnClickTopSong(TopSongModel topSongModel) {
        this.topSongModel = topSongModel;
    }

    public TopSongModel getTopSongModel() {
        return topSongModel;
    }

    public void setTopSongModel(TopSongModel topSongModel) {
        this.topSongModel = topSongModel;
    }
}
