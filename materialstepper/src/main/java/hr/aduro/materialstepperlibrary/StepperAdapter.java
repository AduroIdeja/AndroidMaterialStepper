package hr.aduro.materialstepperlibrary;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.ArrayList;

/**
 * An adapter class for populating the StepperView
 */
public class StepperAdapter {

    protected static final String NEXT_TXT = "next_btn_txt",
            SKIP_TXT = "skip_btn_txt";

    private FragmentManager fragmentManager;
    private StepperColorScheme stepperColorScheme = null;

    private ArrayList<Fragment> contents = new ArrayList<>();
    private ArrayList<String> titles = new ArrayList<>();
    private ArrayList<Bundle> buttons = new ArrayList<>();

    ///////////////////
    //  CONSTRUCTOR  //
    ///////////////////

    public StepperAdapter(FragmentManager fragmentManager) {

        this.fragmentManager = fragmentManager;

    }

    ///////////////
    //  GETTERS  //
    ///////////////

    public int getCount() {

        return contents.size();

    }

    public FragmentManager getFragmentManager() {

        return fragmentManager;

    }

    public Fragment getContentAt(int position) {

        return contents.get(position);

    }

    public String getTitleAt(int position) {

        return titles.get(position);

    }

    public Bundle getButtonsAt(int position){

        return buttons.get(position);

    }

    public StepperColorScheme getStepperColorScheme() {

        return stepperColorScheme;

    }

    ///////////////
    //  SETTERS  //
    ///////////////

    public void setStepperColorScheme(StepperColorScheme stepperColorScheme) {

        this.stepperColorScheme = stepperColorScheme;

    }

    ///////////////
    //  HELPERS  //
    ///////////////

    public void add(String title, Fragment fragment, @NonNull String nextBtnText, @Nullable String skipBtnText) {

        contents.add(fragment);
        titles.add(title);

        Bundle bundle = new Bundle();
        bundle.putString(NEXT_TXT, nextBtnText);
        bundle.putString(SKIP_TXT, skipBtnText);
        buttons.add(bundle);

    }

}
