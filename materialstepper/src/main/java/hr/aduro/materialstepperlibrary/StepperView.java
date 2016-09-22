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
    private LinearLayout container;
    private int currentStep = 0;
    private StepperAdapter adapter;
    private StepperView stepperView;
    private int skippedFromStep = -1;

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

    public int getCurrentStepIndex() {

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

            VerticalStepView step = new VerticalStepView(context, stepperView);
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
    protected void nextStep() {

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

            if (skippedFromStep == -1)
                currentStep--;
            else {

                currentStep = skippedFromStep;
                skippedFromStep = -1;

            }

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

        skippedFromStep = currentStep;
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
    private void scrollToActiveElement(final VerticalStepView step) {

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

    /**
     * Convenience method for enabling or disabling the NEXT button of desired step. This method
     * should be called AFTER the adapter is set on the StepperView, otherwise the desired button
     * will not be modified.
     *
     * @param stepIndex - int, desired step
     * @param enable    - boolean
     */
    public void enableNextForStep(int stepIndex, boolean enable) {

        if (container.getChildCount() >= stepIndex + 1)
            ((VerticalStepView) container.getChildAt(stepIndex)).enableNextBtn(enable);
        else
            throw new IndexOutOfBoundsException("Step not found at index " + Integer.toString(stepIndex) +
                    " (step count: " + Integer.toString(container.getChildCount()) + ").\nPOSSIBLE FIX: Set " +
                    "adapter (containing all steps) to " + this.getClass().getSimpleName() +
                    " BEFORE calling this method.");


    }

    /**
     * Convenience method for enabling or disabling the SKIP button of desired step. This method
     * should be called AFTER the adapter is set on the StepperView, otherwise the desired button
     * will not be modified.
     *
     * @param stepIndex - int, desired step
     * @param enable    - boolean
     */
    public void enableSkipForStep(int stepIndex, boolean enable) {

        if (container.getChildCount() >= stepIndex + 1)
            ((VerticalStepView) container.getChildAt(stepIndex)).enableSkipBtn(enable);
        else
            throw new IndexOutOfBoundsException("Step not found at index " + Integer.toString(stepIndex) +
                    " (step count: " + Integer.toString(container.getChildCount()) + ").\nPOSSIBLE FIX: Set " +
                    "adapter (containing all steps) to " + this.getClass().getSimpleName() +
                    " BEFORE calling this method.");

    }

    /**
     * Convenience method for getting NEXT button enabled status. This method
     * should be called AFTER the adapter is set on the StepperView, otherwise the desired button
     * will not be modified.
     *
     * @param stepIndex - int, desired step
     * @return - is NEXT button enabled
     */
    public boolean getIsNextEnabled(int stepIndex) {

        boolean enabled;

        if (container.getChildCount() >= stepIndex + 1)
            enabled = ((VerticalStepView) container.getChildAt(stepIndex)).getIsNextEnabled();
        else
            throw new IndexOutOfBoundsException("Step not found at index " + Integer.toString(stepIndex) +
                    " (step count: " + Integer.toString(container.getChildCount()) + ").\nPOSSIBLE FIX: Set " +
                    "adapter (containing all steps) to " + this.getClass().getSimpleName() +
                    " BEFORE calling this method.");

        return enabled;

    }

    /**
     * Convenience method for getting SKIP button enabled status. This method
     * should be called AFTER the adapter is set on the StepperView, otherwise the desired button
     * will not be modified.
     *
     * @param stepIndex - int, desired step
     * @return - is SKIP button enabled
     */
    public boolean getIsSkipEnabled(int stepIndex) {

        boolean enabled = false;

        if (container.getChildCount() >= stepIndex + 1)
            enabled = ((VerticalStepView) container.getChildAt(stepIndex)).getIsSkipEnabled();
        else
            throw new IndexOutOfBoundsException("Step not found at index " + Integer.toString(stepIndex) +
                    " (step count: " + Integer.toString(container.getChildCount()) + ").\nPOSSIBLE FIX: Set " +
                    "adapter (containing all steps) to " + this.getClass().getSimpleName() +
                    " BEFORE calling this method.");

        return enabled;

    }

}
