package hr.aduro.materialstepperlibrary;

import android.app.Fragment;
import android.app.FragmentManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.ArrayList;

/**
 * An adapter class for populating the StepperView.
 * <p/>
 * The adapter takes in a title, fragment, and a custom button listener.
 * It then creates the stepper view with added fragments and associated attributes.
 *
 * @author Tomislav Horvat
 */
public class StepperAdapter {

    /////////////////
    //  VARIABLES  //
    /////////////////

    private FragmentManager fragmentManager;
    private StepperColorScheme stepperColorScheme = null;

    private ArrayList<Fragment> fragments = new ArrayList<>();
    private ArrayList<String> titles = new ArrayList<>();
    private ArrayList<StepperButtonListener> btnListeners = new ArrayList<>();

    ///////////////////
    //  CONSTRUCTOR  //
    ///////////////////

    public StepperAdapter(FragmentManager fragmentManager) {

        this.fragmentManager = fragmentManager;

    }

    ///////////////
    //  GETTERS  //
    ///////////////

    /**
     * Returns the item count in the adapter
     *
     * @return - int
     */
    public int getCount() {

        return fragments.size();

    }

    /**
     * Returns the associated fragment manager. For use in StepperView
     *
     * @return - FragmentManager
     */
    protected FragmentManager getFragmentManager() {

        return fragmentManager;

    }

    /**
     * Returns a fragment from a position in the adapter.
     *
     * @param position - int
     * @return - Fragment, needs to be cast if custom implementation of Fragment class
     */
    public Fragment getFragmentAt(int position) {

        return fragments.get(position);

    }

    /**
     * Returns step title associated with fragment at given position in adapter. Mainly used in
     * StepperView for setting up the view.
     *
     * @param position - int
     * @return - String
     */
    public String getTitleAt(int position) {

        return titles.get(position);

    }

    /**
     * Returns the custom color scheme for the StepperView
     *
     * @return - StepperColorScheme
     */
    protected StepperColorScheme getStepperColorScheme() {

        return stepperColorScheme;

    }

    /**
     * Returns the next and skip button listeners from given position in adapter.
     *
     * @param position - int
     * @return - StepperButtonListener
     */
    protected StepperButtonListener getBtnListenersAt(int position) {

        return btnListeners.get(position);

    }

    ///////////////
    //  SETTERS  //
    ///////////////

    /**
     * Sets the color scheme to be applied to the StepperView.
     *
     * @param stepperColorScheme - StepperColorScheme
     */
    public void setStepperColorScheme(StepperColorScheme stepperColorScheme) {

        this.stepperColorScheme = stepperColorScheme;

    }

    ///////////////
    //  HELPERS  //
    ///////////////

    /**
     * Add a fragment with title, and next and skip buttons. The fragment and button listener are mandatory.
     * If the frag title is null it will not be displayed.
     * If the next button listener is a StepperButtonListenerDefault the Stepper will
     * perform a default action respectfully. If it is a StepperButtonListener, the added functionality will be run before
     * the default action is performed (a default action is considered to be the display of the next step
     * and changing the status of the current one by modifying colors, font weights, visibility etc.).
     *
     * @param fragTitle - String
     * @param fragment  - extended Fragment
     * @param listener  - StepperButtonListener
     */
    public void add(@Nullable String fragTitle,
                    @NonNull Fragment fragment,
                    @NonNull StepperButtonListener listener) {

        fragments.add(fragment);
        titles.add(fragTitle);
        btnListeners.add(listener);

    }

}
