package hr.aduro.materialstepperlibrary;

/**
 * Utility class for setting colors of various widgets in VerticalStep
 *
 * @author Tomislav Horvat
 */
public class StepperColorScheme {

    private static int STEP_NUMBER_COLOR = 0,
            STEP_LINE_COLOR = 0,
            STEP_TITLE_COLOR = 0,
            NEXT_BTN_BACKGROUND_COLOR = 0,
            NEXT_BTN_TEXT_COLOR = 0,
            SKIP_BTN_BACKGROUND_COLOR = 0,
            SKIP_BTN_TEXT_COLOR = 0;

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

    int getStepNumberColor() {
        return STEP_NUMBER_COLOR;
    }

    int getStepLineColor() {
        return STEP_LINE_COLOR;
    }

    int getStepTitleColor() {
        return STEP_TITLE_COLOR;
    }

    int getNextBtnBackgroundColor() {
        return NEXT_BTN_BACKGROUND_COLOR;
    }

    int getNextBtnTextColor() {
        return NEXT_BTN_TEXT_COLOR;
    }

    int getSkipBtnBackgroundColor() {
        return SKIP_BTN_BACKGROUND_COLOR;
    }

    int getSkipBtnTextColor() {
        return SKIP_BTN_TEXT_COLOR;
    }

}
