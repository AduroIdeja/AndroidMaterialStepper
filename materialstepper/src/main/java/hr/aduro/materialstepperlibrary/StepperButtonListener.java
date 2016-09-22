package hr.aduro.materialstepperlibrary;

/**
 * A Listener class for use with custom operations onNext and onSkip with VerticalStepView
 */
public abstract class StepperButtonListener {

    private String nextBtnText,
            skipBtnText;

    public StepperButtonListener(String nextBtnText) {

        this.nextBtnText = nextBtnText;

    }

    public StepperButtonListener(String nextBtnText, String skipBtnText) {

        this.nextBtnText = nextBtnText;
        this.skipBtnText = skipBtnText;

    }

    /*
    Requires return value for additional operations (i.e. checking input, awaiting result from
    network operation, loading resources etc.)
    */

    /**
     * Extend onNext functionality with own implementation.
     *
     * @return - boolean
     */
    public boolean onNext() {

        return true;

    }

    /*
    Used to extend functionality for skipping a step (i.e. displaying dialogs or Toast messages)
     */

    /**
     * Extend onSkip functionality with own implementation.
     */
    public void onSkip() {}

    // PACKAGE LOCAL

    String getNextBtnText() {

        return nextBtnText;

    }

    String getSkipBtnText() {

        return skipBtnText;

    }

}
