package com.example.niot.deliveryshipperandroidapp.Adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.niot.deliveryshipperandroidapp.R;

import java.util.ArrayList;
import java.util.List;

public class PagerAdapter extends FragmentPagerAdapter {
    private List<Fragment> mFragmentList = new ArrayList<>();
    private List<String> mFragmentTitleList = new ArrayList<>();

    public PagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        return mFragmentList.get(i);
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }

    public void addFragment(Fragment fragment,String title){
        mFragmentList.add(fragment);
        mFragmentTitleList.add(title);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mFragmentTitleList.get(position);
    }

    public Fragment getFragment(int position){
        return mFragmentList.get(position);
    }

    public int[] imageResId = {R.drawable.ic_list_black_24dp,R.drawable.ic_directions_bike_black_24dp,
    R.drawable.ic_done_all_black_24dp,R.drawable.ic_supervisor_account_black_24dp};

    public View getTabView(Context context,int position) {
        // Given you have a custom layout in `res/layout/custom_tab.xml` with a TextView and ImageView
        View v = LayoutInflater.from(context).inflate(R.layout.tab_custom_layout, null);
        TextView tv = (TextView) v.findViewById(R.id.tab_custom_text_view);
        tv.setText(mFragmentTitleList.get(position));
        ImageView img = (ImageView) v.findViewById(R.id.tab_custom_image_view);
        img.setImageResource(imageResId[position]);
        return v;
    }
}
