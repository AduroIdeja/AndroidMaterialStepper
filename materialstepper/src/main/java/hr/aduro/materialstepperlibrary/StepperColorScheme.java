package hr.aduro.materialstepperlibrary;

import android.graphics.Color;

/**
 * Created by Tomislav Horvat on 7/15/16.
 */
public class StepperColorScheme {

    private static Color STEP_NUMBER_COLOR,
            STEP_LINE_COLOR,
            STEP_TITLE_COLOR,
            STEP_CARD_BACKGROUND_COLOR,
            NEXT_BTN_BACKGROUND_COLOR,
            NEXT_BTN_TEXT_COLOR,
            SKIP_BTN_BACKGROUND_COLOR,
            SKIP_BTN_TEXT_COLOR;

    ///////////////
    //  SETTERS  //
    ///////////////

    public void setStepNumberColor(Color stepNumberColor) {
        STEP_NUMBER_COLOR = stepNumberColor;
    }

    public void setStepLineColor(Color stepLineColor) {
        STEP_LINE_COLOR = stepLineColor;
    }

    public void setStepTitleColor(Color stepTitleColor) {
        STEP_TITLE_COLOR = stepTitleColor;
    }

    public void setStepCardBackgroundColor(Color stepCaredBackgroundColor) {
        STEP_CARD_BACKGROUND_COLOR = stepCaredBackgroundColor;
    }

    public void setNextBtnBackgroundColor(Color nextBtnBackgroundColor) {
        NEXT_BTN_BACKGROUND_COLOR = nextBtnBackgroundColor;
    }

    public void setNextBtnTextColor(Color nextBtnTextColor) {
        NEXT_BTN_TEXT_COLOR = nextBtnTextColor;
    }

    public void setSkipBtnBackgroundColor(Color skipBtnBackgroundColor) {
        SKIP_BTN_BACKGROUND_COLOR = skipBtnBackgroundColor;
    }

    public void setSkipBtnTextColor(Color skipBtnTextColor) {
        SKIP_BTN_TEXT_COLOR = skipBtnTextColor;
    }

    ///////////////
    //  GETTERS  //
    ///////////////

    public Color getStepNumberColor() {
        return STEP_NUMBER_COLOR;
    }

    public static Color getStepLineColor() {
        return STEP_LINE_COLOR;
    }

    public static Color getStepTitleColor() {
        return STEP_TITLE_COLOR;
    }

    public static Color getStepCardBackgroundColor() {
        return STEP_CARD_BACKGROUND_COLOR;
    }

    public static Color getNextBtnBackgroundColor() {
        return NEXT_BTN_BACKGROUND_COLOR;
    }

    public static Color getNextBtnTextColor() {
        return NEXT_BTN_TEXT_COLOR;
    }

    public static Color getSkipBtnBackgroundColor() {
        return SKIP_BTN_BACKGROUND_COLOR;
    }

    public static Color getSkipBtnTextColor() {
        return SKIP_BTN_TEXT_COLOR;
    }

}
