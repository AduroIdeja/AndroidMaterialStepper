package hr.aduro.materialstepperlibrary;

import android.support.annotation.NonNull;

/**
 * A default class used to initialize the NEXT and SKIP buttons respectfully with no additional
 * functionality added to the base one.
 */
public class StepperButtonListenerDefault extends StepperButtonListener {

    /**
     * Initializes only the next button with entered label.
     *
     * @param nextBtnText - String
     */
    public StepperButtonListenerDefault(@NonNull String nextBtnText) {

        super(nextBtnText);

    }

    /**
     * Initializes the next and skip button labels.
     *
     * @param nextBtnText - String
     * @param skipBtnText - String
     */
    public StepperButtonListenerDefault(@NonNull String nextBtnText, @NonNull String skipBtnText) {

        super(nextBtnText, skipBtnText);

    }

}
