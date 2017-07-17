package techkids.vn.zingmp3.networks;

import retrofit2.Call;
import retrofit2.http.GET;
import techkids.vn.zingmp3.json_models.AllMusicTypesJSONModel;

/**
 * Created by ADMIN on 7/15/2017.
 */

public interface GetMusicTypes {
    @GET ("api")
    Call<AllMusicTypesJSONModel> getMusicType();
}
