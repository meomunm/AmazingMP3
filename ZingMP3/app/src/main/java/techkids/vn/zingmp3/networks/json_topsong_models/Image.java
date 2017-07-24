package techkids.vn.zingmp3.networks.json_topsong_models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by ADMIN on 7/22/2017.
 */

public class Image {
   @SerializedName("label")
    String label;

    public Image(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
