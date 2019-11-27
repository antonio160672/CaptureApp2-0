package com.example.captureapp2_0.Vistas.ui_vistas.cap_segnals;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class Viewpager_adaptador_view extends FragmentPagerAdapter {
    List<Fragment>fragments=new ArrayList<>();

    public Viewpager_adaptador_view(FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "WI-FI";
            case 1:
                return "Bluetooth";
            case 2:
                return "Ambos";
        }
        return null;
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
    public  void clearFragemen(){
        fragments.clear();
    }
    public void addFRagmen(Fragment fragment){
        fragments.add(fragment);
    }
}
