package hr.aduro.materialstepperlibrary;

/**
 * A Listener class for use with custom operations onNext and onSkip with VerticalStepView
 */
public abstract class StepperButtonListener {

    /*
    Requires return value for additional operations (i.e. checking input, awaiting result from
    network operation, loading resources etc.)
    */
    public boolean onNext() {
        return true;
    }

    /*
    Used to extend functionality for skipping a step (i.e. displaying dialogs or Toast messages)
     */
    public void onSkip() {}

}
