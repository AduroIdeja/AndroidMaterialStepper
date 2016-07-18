package hr.aduro.materialstepperlibrary;

import android.app.FragmentManager;
import android.content.Context;
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

        for (int i = 0; i < count; i++) {

            VerticalStep step = new VerticalStep(context);

            if (i == 0)
                step.activateStep();

            if (i == (count - 1))
                step.hideConnector();

            step.setStepNumber(i + 1);

            step.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
            container.addView(step);

        }

    }

    ///////////////
    //  HELPERS  //
    ///////////////

    public static void nextStep() {

        if (currentStep < adapter.getCount() - 1)
            currentStep++;
        ((VerticalStep) container.getChildAt(currentStep)).activateStep();

    }

    public static void previousStep() {

        if (currentStep > 0) {

            currentStep--;
            ((VerticalStep) container.getChildAt(currentStep)).activateStep();

        }

    }

    public static void jumpToStep(int stepIndex) {

        currentStep = stepIndex;
        ((VerticalStep) container.getChildAt(currentStep)).activateStep();

    }

}
