package techkids.vn.zingmp3.adapters;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

import butterknife.BindView;
import butterknife.ButterKnife;
import jp.wasabeef.picasso.transformations.CropCircleTransformation;
import techkids.vn.zingmp3.R;
import techkids.vn.zingmp3.databases.MusicTypeModel;
import techkids.vn.zingmp3.databases.TopSongModel;

/**
 * Created by ADMIN on 7/22/2017.
 */

public class TopSongAdapter extends RecyclerView.Adapter<TopSongAdapter.TopSongViewHolder> {
    private Context context;
    private List<TopSongModel> topSongModelList = new ArrayList<>();
    private View.OnClickListener onClickListener;

    public TopSongAdapter(List<TopSongModel> topSongModelList, Context context) {
        this.topSongModelList = topSongModelList;
        this.context = context;
    }

    public void setOnItemClick(View.OnClickListener onClickListener){
        this.onClickListener = onClickListener;
    }

    @Override
    public TopSongViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_list_top_song, parent, false);

        view.setOnClickListener(onClickListener);

        return new TopSongViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TopSongViewHolder holder, int position) {
            holder.setData(topSongModelList.get(position));
    }

    @Override
    public int getItemCount() {
        return topSongModelList.size();
    }

    public class TopSongViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_top_song)
        ImageView ivTopSong;

        @BindView(R.id.tv_name_item_top_song)
        TextView tvNameItemTopSong;

        @BindView(R.id.tv_artist_item_top_song)
        TextView tvArtistItemTopSong;

        public TopSongViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);

        }

        private void setData(TopSongModel topSongModel) {
            if (topSongModel !=  null){
                Picasso.with(context).load(topSongModel.getImage()).transform(new CropCircleTransformation()).into(ivTopSong);
                // ivItemMusic.setImageURI(Uri.parse(topSongModel.getImage()));
                tvNameItemTopSong.setText(topSongModel.getName());
                tvArtistItemTopSong.setText(topSongModel.getArtist());
                itemView.setTag(topSongModel);
            }
        }
    }
}
