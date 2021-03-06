package adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.iteso.desarrollo.sesion9_2.FragmentElectronics;
import com.iteso.desarrollo.sesion9_2.FragmentHome;
import com.iteso.desarrollo.sesion9_2.FragmentTechnology;
import com.iteso.desarrollo.sesion9_2.R;

/**
 * Created by Desarrollo on 24/09/2017.
 */

public class SectionsPagerAdapter extends FragmentPagerAdapter {
    public FragmentTechnology fragmentTechnology;
    public FragmentHome fragmentHome;
    public FragmentElectronics fragmentElectronics;

    protected Context context;


    public SectionsPagerAdapter(FragmentManager fm, Context context){
        super(fm);
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {
        switch(position){
            case 0:
                if(fragmentTechnology == null)
                    fragmentTechnology = new FragmentTechnology();
                return fragmentTechnology;
            case 1:
                if(fragmentHome == null)
                    fragmentHome = new FragmentHome();
                return fragmentHome;
            case 2:
                if(fragmentElectronics == null)
                    fragmentElectronics = new FragmentElectronics();
                return fragmentElectronics;
            default:
                return new FragmentTechnology();
        }

    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0: return context.getResources().getString(R.string.fragment_tab1);
            case 1: return context.getResources().getString(R.string.fragment_tab2);
            case 2: return context.getResources().getString(R.string.fragment_tab3);
        }
        return super.getPageTitle(position);
    }
}