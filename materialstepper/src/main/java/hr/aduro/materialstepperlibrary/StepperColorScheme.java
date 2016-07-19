package hr.aduro.materialstepperlibrary;

/**
 * Utility class for setting colors of various widgets in VerticalStep
 *
 * @author Tomislav Horvat
 */
public class StepperColorScheme {

    private static int STEP_NUMBER_COLOR = -1,
            STEP_LINE_COLOR = -1,
            STEP_TITLE_COLOR = -1,
            NEXT_BTN_BACKGROUND_COLOR = -1,
            NEXT_BTN_TEXT_COLOR = -1,
            SKIP_BTN_BACKGROUND_COLOR = -1,
            SKIP_BTN_TEXT_COLOR = -1;

    ///////////////
    //  SETTERS  //
    ///////////////

    public void setStepNumberTextColor(int stepNumberColor) {
        STEP_NUMBER_COLOR = stepNumberColor;
    }

    public void setStepLineColor(int stepLineColor) {
        STEP_LINE_COLOR = stepLineColor;
    }

    public void setStepTitleColor(int stepTitleColor) {
        STEP_TITLE_COLOR = stepTitleColor;
    }

    public void setNextBtnBackgroundColor(int nextBtnBackgroundColor) {
        NEXT_BTN_BACKGROUND_COLOR = nextBtnBackgroundColor;
    }

    public void setNextBtnTextColor(int nextBtnTextColor) {
        NEXT_BTN_TEXT_COLOR = nextBtnTextColor;
    }

    public void setSkipBtnBackgroundColor(int skipBtnBackgroundColor) {
        SKIP_BTN_BACKGROUND_COLOR = skipBtnBackgroundColor;
    }

    public void setSkipBtnTextColor(int skipBtnTextColor) {
        SKIP_BTN_TEXT_COLOR = skipBtnTextColor;
    }

    ///////////////
    //  GETTERS  //
    ///////////////

    public int getStepNumberColor() {
        return STEP_NUMBER_COLOR;
    }

    public int getStepLineColor() {
        return STEP_LINE_COLOR;
    }

    public int getStepTitleColor() {
        return STEP_TITLE_COLOR;
    }

    public int getNextBtnBackgroundColor() {
        return NEXT_BTN_BACKGROUND_COLOR;
    }

    public int getNextBtnTextColor() {
        return NEXT_BTN_TEXT_COLOR;
    }

    public int getSkipBtnBackgroundColor() {
        return SKIP_BTN_BACKGROUND_COLOR;
    }

    public int getSkipBtnTextColor() {
        return SKIP_BTN_TEXT_COLOR;
    }

}
