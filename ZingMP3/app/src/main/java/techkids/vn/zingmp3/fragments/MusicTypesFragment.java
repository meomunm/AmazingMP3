package techkids.vn.zingmp3.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import techkids.vn.zingmp3.R;
import techkids.vn.zingmp3.adapters.MusicTypeAdapter;
import techkids.vn.zingmp3.databases.MusicTypeModel;
import techkids.vn.zingmp3.events.OnClickMusicType;
import techkids.vn.zingmp3.managers.ScreenManager;
import techkids.vn.zingmp3.networks.GetMusicTypes;
import techkids.vn.zingmp3.networks.RetrofitFactory;
import techkids.vn.zingmp3.networks.json_models.AllMusicTypesJSONModel;
import techkids.vn.zingmp3.networks.json_models.MusicTypeJSONModel;

/**
 * Created by ADMIN on 7/17/2017.
 */

public class MusicTypesFragment extends Fragment implements View.OnClickListener {
    @BindView(R.id.rv_music_types)
    RecyclerView rvRecycleview;

    private List<MusicTypeModel> musicTypeModelList = new ArrayList<>();
    private final String TAG = MusicTypesFragment.class.toString();
    private MusicTypeAdapter musicTypeAdapter;

    public MusicTypesFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_music_types, container, false);

        this.setupUI(view);
        this.loadData();
        return view;
    }

    private void setupUI(View view) {
        ButterKnife.bind(this, view);
        musicTypeAdapter = new MusicTypeAdapter(this.musicTypeModelList, getContext());

        rvRecycleview.setAdapter(musicTypeAdapter);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2, LinearLayoutManager.VERTICAL, false);
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return position % 3 == 0 ? 2 : 1; //chiến bao nhiêu cột, nếu chia hết cho 3 thì chiếm 2, còn ko thì 1
            }
        });
        rvRecycleview.setLayoutManager(gridLayoutManager);
        musicTypeAdapter.setOnItemClick(this);
    }

    private void loadData() {
        GetMusicTypes getMusicTypes = RetrofitFactory.getInstance().create(GetMusicTypes.class);

        getMusicTypes.getMusicType().enqueue(new Callback<AllMusicTypesJSONModel>() {
            @Override
            public void onResponse(Call<AllMusicTypesJSONModel> call, Response<AllMusicTypesJSONModel> response) {
                for (MusicTypeJSONModel musicTypeJSONModel : response.body().getSubgenres()) {
                    MusicTypeModel musicTypeModel = new MusicTypeModel();
                    musicTypeModel.setId(musicTypeJSONModel.getId());
                    musicTypeModel.setKey(musicTypeJSONModel.getTranslation_key());
                    musicTypeModel.setImageID(getContext().getResources()
                            .getIdentifier("genre_x2_"
                                            + musicTypeJSONModel.getId(),
                                    "raw",
                                    getContext().getPackageName()));
                    musicTypeModelList.add(musicTypeModel);
                }
                musicTypeAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<AllMusicTypesJSONModel> call, Throwable t) {
                Toast.makeText(getContext(), "No connection", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public void onClick(View v) {
        MusicTypeModel musicTypeModel = (MusicTypeModel) v.getTag();
//        //TODO: create class TopSongFragment để chuyển fragment
        EventBus.getDefault().postSticky(new OnClickMusicType(musicTypeModel));
        ScreenManager.openFragment(getActivity().getSupportFragmentManager(), new TopSongFragment(), R.id.layout_container);
    }
}
