package hr.aduro.materialstepperlibrary;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Locale;

/**
 * View representing a single, vertically orientated step
 *
 * @author Tomislav Horvat
 */
class VerticalStepView extends RelativeLayout {

    private Context context;
    private OnClickListener nextListener = new OnClickListener() {

        @Override
        public void onClick(View view) {

            completeStep();
            parent.nextStep();

        }

    },
            skipListener = new OnClickListener() {

                @Override
                public void onClick(View view) {

                    skipStep();
                    parent.nextStep();

                }

            };

    private StepperView parent;
    private TextView titleLabel, stepNumber;
    private Button nextBtn, skipBtn;
    private LinearLayout contentLayout, connectorLine;
    private CardView cardView;

    private int stepIndex;

    ////////////////////
    //  CONSTRUCTORS  //
    ////////////////////

    public VerticalStepView(Context context, StepperView parent) {

        super(context);
        this.parent = parent;
        initialize(context);

    }

    public VerticalStepView(Context context, AttributeSet attributeSet, StepperView parent) {

        super(context, attributeSet);
        this.parent = parent;
        initialize(context);

    }

    private void initialize(Context context) {

        inflate(context, R.layout.vertical_step_container, this);
        this.context = context;

        titleLabel = (TextView) this.findViewById(R.id.step_title);
        stepNumber = (TextView) this.findViewById(R.id.step_number);
        nextBtn = (Button) this.findViewById(R.id.step_next_btn);
        skipBtn = (Button) this.findViewById(R.id.step_skip_btn);
        contentLayout = (LinearLayout) this.findViewById(R.id.step_content_layout);
        connectorLine = (LinearLayout) this.findViewById(R.id.step_connector_line);
        cardView = (CardView) findViewById(R.id.step_card_view);

        // set default listeners
        nextBtn.setOnClickListener(nextListener);
        skipBtn.setOnClickListener(skipListener);

    }

    ///////////////
    //  GETTERS  //
    ///////////////

    public TextView getTitleLabel() {

        return titleLabel;

    }

    public boolean getIsNextEnabled(){

        return nextBtn.isEnabled();

    }

    public boolean getIsSkipEnabled(){

        return skipBtn.isEnabled();

    }

    ///////////////
    //  SETTERS  //
    ///////////////

    /**
     * Sets custom colors for various visual elements. If not set the widget uses colors defined in styles.xml
     *
     * @param stepperColorScheme - a color scheme with defined colors
     */
    public void setCustomColors(StepperColorScheme stepperColorScheme) {

        if (stepperColorScheme.getStepNumberColor() != 0)
            stepNumber.setTextColor(stepperColorScheme.getStepNumberColor());

        if (stepperColorScheme.getNextBtnBackgroundColor() != 0)
            nextBtn.getBackground().setColorFilter(stepperColorScheme.getNextBtnBackgroundColor(), PorterDuff.Mode.MULTIPLY);

        if (stepperColorScheme.getSkipBtnBackgroundColor() != 0)
            skipBtn.getBackground().setColorFilter(stepperColorScheme.getSkipBtnBackgroundColor(), PorterDuff.Mode.MULTIPLY);

        if (stepperColorScheme.getNextBtnTextColor() != 0)
            nextBtn.setTextColor(stepperColorScheme.getNextBtnTextColor());

        if (stepperColorScheme.getSkipBtnTextColor() != 0)
            skipBtn.setTextColor(stepperColorScheme.getSkipBtnTextColor());

        if (stepperColorScheme.getStepLineColor() != 0)
            connectorLine.setBackgroundColor(stepperColorScheme.getStepLineColor());

        if (stepperColorScheme.getStepTitleColor() != 0)
            titleLabel.setTextColor(stepperColorScheme.getStepTitleColor());

    }

    /**
     * Sets a custom action to NEXT button if necessary
     *
     * @param nextListener - a custom listener implementing onNext
     */
    public void setOnNextListener(final StepperButtonListener nextListener) {

        if (nextListener != null)
            this.nextListener = new OnClickListener() {
                @Override
                public void onClick(View view) {

                    if (nextListener.onNext()) {

                        completeStep();
                        parent.nextStep();

                    }

                }

            };

        nextBtn.setOnClickListener(this.nextListener);

    }

    /**
     * Sets a custom action to SKIP button if necessary
     *
     * @param skipListener - a custom listener implementing onSkip
     */
    public void setOnSkipListener(final StepperButtonListener skipListener) {

        if (skipListener != null)
            this.skipListener = new OnClickListener() {
                @Override
                public void onClick(View view) {

                    skipListener.onSkip();
                    skipStep();
                    parent.nextStep();

                }

            };

        skipBtn.setOnClickListener(this.skipListener);

    }

    protected void setTitleLabel(String title) {

        titleLabel.setText(title);

    }

    protected void setNextBtnText(String text) {

        nextBtn.setText(text);

    }

    protected void setSkipBtnText(String text) {

        if (text != null)
            skipBtn.setText(text);
        else
            skipBtn.setVisibility(View.GONE);

    }

    /**
     * Sets the step number, a 1 based integer
     *
     * @param step - integer
     */
    protected void setStepNumber(int step) {

        stepNumber.setText(String.format(Locale.getDefault(), "%d", step));
        stepIndex = step - 1;

    }

    protected void setContentView(FragmentManager fragmentManager, Fragment fragment) {

        if (fragmentManager == null) {

            throw new NullPointerException("FragmentManager must not be null!");

        } else if (fragment == null) {

            throw new NullPointerException("Fragment must not be null!");

        } else {

            FrameLayout frameLayout = new FrameLayout(context);
            frameLayout.setLayoutParams(new CardView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            frameLayout.setId(View.generateViewId());

            fragmentManager.beginTransaction().add(frameLayout.getId(), fragment).commit();

            cardView.addView(frameLayout);

        }

    }

    ///////////////
    //  HELPERS  //
    ///////////////

    /**
     * Hides the connector line, used for hiding connector line of last element in stepper
     */
    public void hideConnector() {

        connectorLine.setVisibility(GONE);

    }

    /**
     * Sets step in an active visual state
     */
    public void activateStep() {

        contentLayout.setVisibility(VISIBLE);
        stepNumber.setBackground(ContextCompat.getDrawable(context, R.drawable.step_background_active));
        titleLabel.setTypeface(null, Typeface.BOLD);

        if (stepNumber.getText().toString().equals(""))
            stepNumber.setText(String.format(Locale.getDefault(), "%d", stepIndex + 1));

    }

    /**
     * Sets step in an inactive visual state
     */
    public void revertStep() {

        contentLayout.setVisibility(GONE);
        stepNumber.setBackground(ContextCompat.getDrawable(context, R.drawable.step_background_inactive));
        stepNumber.setText(String.format(Locale.getDefault(), "%d", stepIndex + 1));
        titleLabel.setTypeface(null, Typeface.NORMAL);

    }

    /**
     * Sets step in an completed visual state
     */
    public void completeStep() {

        contentLayout.setVisibility(GONE);
        stepNumber.setText(null);
        stepNumber.setBackground(ContextCompat.getDrawable(context, R.drawable.step_background_completed));
        titleLabel.setTypeface(null, Typeface.NORMAL);

    }

    /**
     * Sets step in an inactive visual state
     */
    public void skipStep() {

        revertStep();

    }

    /**
     * Enable/disable the NEXT button
     * @param enable - boolean
     */
    public void enableNextBtn(boolean enable) {

        nextBtn.setEnabled(enable);
        nextBtn.setClickable(enable);
        if(enable)
            nextBtn.setAlpha(1);
        else
            nextBtn.setAlpha(0.5f);

    }

    /**
     * Enable/disable the SKIP button
     * @param enable - boolean
     */
    public void enableSkipBtn(boolean enable) {

        skipBtn.setEnabled(enable);
        skipBtn.setClickable(enable);
        if(enable)
            skipBtn.setAlpha(1);
        else
            skipBtn.setAlpha(0.5f);

    }

}
