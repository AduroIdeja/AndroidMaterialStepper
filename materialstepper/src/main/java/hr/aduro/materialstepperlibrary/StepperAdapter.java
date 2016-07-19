package hr.aduro.materialstepperlibrary;

import android.app.Fragment;
import android.app.FragmentManager;

import java.util.ArrayList;

/**
 * An adapter class for populating the StepperView
 */
public class StepperAdapter {

    protected static final String STEP_TITLE = "step_title",
            STEP_CONTENT = "step_content";

    private FragmentManager fragmentManager;
    private StepperColorScheme stepperColorScheme = null;

    private ArrayList<Fragment> contents = new ArrayList<>();
    private ArrayList<String> titles = new ArrayList<>();

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

    public void add(String title, Fragment fragment) {

        contents.add(fragment);
        titles.add(title);

    }

}
