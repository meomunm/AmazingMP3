package techkids.vn.zingmp3.networks;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import techkids.vn.zingmp3.networks.json_topsong_models.TopSongJsonModel;

/**
 * Created by ADMIN on 7/22/2017.
 */

public interface GetTopSong {
    @GET("https://itunes.apple.com/us/rss/topsongs/limit=50/genre={id}/explicit=true/json")
    Call<TopSongJsonModel> getTopSongModel(@Path("id")String id);
}
