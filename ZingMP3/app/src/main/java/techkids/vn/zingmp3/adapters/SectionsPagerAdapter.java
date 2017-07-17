package techkids.vn.zingmp3.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import techkids.vn.zingmp3.fragments.DownloadFragment;
import techkids.vn.zingmp3.fragments.FavoriteFragment;
import techkids.vn.zingmp3.fragments.LibraryMusicFragment;

/**
 * Created by ADMIN on 7/17/2017.
 */

public class SectionsPagerAdapter extends FragmentStatePagerAdapter{
    public SectionsPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position){
            case 0:
                fragment = new LibraryMusicFragment();
                break;
            case 1:
                fragment = new FavoriteFragment();
                break;
            case 2:
                fragment = new DownloadFragment();
                break;
        }

        return fragment;

    }

    @Override
    public int getCount() {
        return 3;
    }


}
