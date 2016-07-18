package hr.aduro.materialstepperlibrary;

import android.app.Fragment;
import android.content.Context;
import android.app.FragmentManager;

import java.util.ArrayList;

/**
 * An adapter class for populating the StepperView
 */
public class StepperAdapter {

    private FragmentManager fragmentManager;
    private Context context;

    private ArrayList<Fragment> items = new ArrayList<>();

    ///////////////////
    //  CONSTRUCTOR  //
    ///////////////////

    public StepperAdapter(Context context, FragmentManager fragmentManager){

        this.context = context;
        this.fragmentManager = fragmentManager;

    }

    ///////////////
    //  GETTERS  //
    ///////////////

    public int getCount(){

        return items.size();

    }

    public FragmentManager getFragmentManager(){

        return fragmentManager;

    }

    public Fragment getItemAt(int position){

        return items.get(position);

    }

    ///////////////
    //  HELPERS  //
    ///////////////

    public void add(Fragment fragment){

        items.add(fragment);

    }

}
