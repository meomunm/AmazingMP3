package techkids.vn.zingmp3.networks.json_topsong_models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by ADMIN on 7/22/2017.
 */

public class Feed {
    @SerializedName("entry")
    List<Entry> entries;

    public Feed(List<Entry> entries) {
        this.entries = entries;
    }

    public List<Entry> getEntries() {
        return entries;
    }

    public void setEntries(List<Entry> entries) {
        this.entries = entries;
    }
}
