package hr.aduro.materialstepperlibrary;

import android.app.FragmentManager;
import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.ScrollView;

/**
 * A View representing the scroll view container of all inserted steps
 *
 * @author Tomislav Horvat
 */
public class StepperView extends ScrollView {

    private Context context;
    private static LinearLayout container;
    private static int currentStep = 0;
    private static StepperAdapter adapter;
    private static StepperView stepperView;

    ////////////////////
    //  CONSTRUCTORS  //
    ////////////////////

    public StepperView(Context context) {

        super(context);
        initialize(context);

    }

    public StepperView(Context context, AttributeSet attributeSet) {

        super(context, attributeSet);
        initialize(context);

    }

    private void initialize(Context context) {

        this.context = context;
        inflate(context, R.layout.stepper, this);

        stepperView = this;
        container = (LinearLayout) this.findViewById(R.id.stepper_container);

    }

    ///////////////
    //  GETTERS  //
    ///////////////

    public int getCurrentStep() {

        return currentStep;

    }

    ///////////////
    //  SETTERS  //
    ///////////////

    public void setAdapter(StepperAdapter stepperAdapter) {

        adapter = stepperAdapter;
        int count = adapter.getCount();
        FragmentManager fragmentManager = adapter.getFragmentManager();
        StepperColorScheme stepperColorScheme = adapter.getStepperColorScheme();

        for (int i = 0; i < count; i++) {

            VerticalStepView step = new VerticalStepView(context);
            StepperButtonListener listener = adapter.getBtnListenersAt(i);

            if (i == 0)
                step.activateStep();

            if (i == (count - 1))
                step.hideConnector();

            step.setStepNumber(i + 1);
            step.setTitleLabel(adapter.getTitleAt(i));
            step.setContentView(fragmentManager, adapter.getFragmentAt(i));

            step.setNextBtnText(listener.getNextBtnText());
            step.setSkipBtnText(listener.getSkipBtnText());
            step.setOnNextListener(listener);
            step.setOnSkipListener(listener);

            if (stepperColorScheme != null)
                step.setCustomColors(stepperColorScheme);

            step.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
            container.addView(step);

        }

    }

    ///////////////
    //  HELPERS  //
    ///////////////

    /**
     * Performs a full action required for displaying the next step
     */
    protected static void nextStep() {

        if (currentStep < adapter.getCount() - 1) {

            currentStep++;
            VerticalStepView step = (VerticalStepView) container.getChildAt(currentStep);

            step.activateStep();

            scrollToActiveElement(step);

        }

    }

    /**
     * Performs a full action required for displaying the previous step
     */
    public void previousStep() {

        if (currentStep > 0) {

            VerticalStepView step = (VerticalStepView) container.getChildAt(currentStep);
            step.revertStep();

            currentStep--;

            step = (VerticalStepView) container.getChildAt(currentStep);
            step.activateStep();

            scrollToActiveElement(step);

        }

    }

    /**
     * Performs a full action required for displaying the desired step
     *
     * @param stepIndex - integer, desired step to jump to
     */
    public void jumpToStep(int stepIndex) {

        currentStep = stepIndex;
        VerticalStepView step = (VerticalStepView) container.getChildAt(currentStep);

        step.activateStep();

        scrollToActiveElement(step);

    }

    /**
     * Scrolls to top of step if the target steps' title is not visible on screen
     *
     * @param step - VerticalStep, target step to display
     */
    private static void scrollToActiveElement(final VerticalStepView step) {

        Rect scrollBounds = new Rect();

        stepperView.getHitRect(scrollBounds);

        if (!step.getTitleLabel().getLocalVisibleRect(scrollBounds)) {

            stepperView.post(new Runnable() {

                @Override
                public void run() {

                    stepperView.smoothScrollTo(0, step.getTop());

                }

            });

        }

    }

}
