package techkids.vn.zingmp3.networks.json_search_songs;

import com.google.gson.annotations.SerializedName;

/**
 * Created by ADMIN on 7/22/2017.
 */

public class SourceObject {
    @SerializedName("128")
    private String linkSource;

    public String getLinkSource() {
        return linkSource;
    }

    public void setLinkSource(String linkSource) {
        this.linkSource = linkSource;
    }
}
