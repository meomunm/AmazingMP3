package techkids.vn.zingmp3.networks.json_topsong_models;

import com.google.gson.annotations.SerializedName;

import techkids.vn.zingmp3.networks.json_topsong_models.Feed;

/**
 * Created by ADMIN on 7/22/2017.
 */

public class TopSongJsonModel {
    @SerializedName("feed")
    private Feed feed;

    public TopSongJsonModel(Feed feed) {
        this.feed = feed;
    }

    public Feed getFeed() {
        return feed;
    }

    public void setFeed(Feed feed) {
        this.feed = feed;
    }
}
