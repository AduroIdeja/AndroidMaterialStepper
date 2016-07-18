package hr.aduro.materialstepperlibrary;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;
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
class VerticalStep extends RelativeLayout {

    private Context context;
    private OnClickListener nextListener = new OnClickListener() {

        @Override
        public void onClick(View view) {

            completeStep();
            StepperView.nextStep();

        }

    },
            skipListener = new OnClickListener() {

                @Override
                public void onClick(View view) {

                    skipStep();
                    StepperView.nextStep();

                }

            };

    private TextView titleLabel, stepNumber;
    private FrameLayout cardFrameLayout;
    private Button nextBtn, skipBtn;
    private LinearLayout contentLayout, connectorLine;

    private int stepIndex;

    ////////////////////
    //  CONSTRUCTORS  //
    ////////////////////

    public VerticalStep(Context context) {

        super(context);
        initialize(context);

    }

    public VerticalStep(Context context, AttributeSet attributeSet) {

        super(context, attributeSet);
        initialize(context);

    }

    private void initialize(Context context) {

        inflate(context, R.layout.vertical_step_container, this);
        this.context = context;

        titleLabel = (TextView) this.findViewById(R.id.step_title);
        cardFrameLayout = (FrameLayout) this.findViewById(R.id.step_content_frame);
        stepNumber = (TextView) this.findViewById(R.id.step_number);
        nextBtn = (Button) this.findViewById(R.id.step_next_btn);
        skipBtn = (Button) this.findViewById(R.id.step_skip_btn);
        contentLayout = (LinearLayout) this.findViewById(R.id.step_content_layout);
        connectorLine = (LinearLayout) this.findViewById(R.id.step_connector_line);

        // set default listeners
        nextBtn.setOnClickListener(nextListener);
        skipBtn.setOnClickListener(skipListener);

    }

    ///////////////
    //  GETTERS  //
    ///////////////

    protected int getStepIndex() {

        return stepIndex;

    }

    ///////////////
    //  SETTERS  //
    ///////////////

    /**
     * Sets custom colors for various visual elements. If not set the widget uses colors defined in @style
     *
     * @param stepperColorScheme - a color scheme with defined colors
     */
    public void setCustomColors(StepperColorScheme stepperColorScheme) {

    }

    /**
     * Sets a custom action to NEXT button if necessary
     *
     * @param nextListener - a custom listener implementing onNext
     */
    public void setOnNextListener(final StepperButtonListener nextListener) {

        this.nextListener = new OnClickListener() {
            @Override
            public void onClick(View view) {

                if (nextListener.onNext()) {

                    completeStep();
                    StepperView.nextStep();

                }

            }

        };

    }

    /**
     * Sets a custom action to SKIP button if necessary
     *
     * @param skipListener - a custom listener implementing onSkip
     */
    public void setOnSkipListener(final StepperButtonListener skipListener) {

        nextListener = new OnClickListener() {
            @Override
            public void onClick(View view) {

                skipListener.onSkip();
                skipStep();
                StepperView.nextStep();

            }

        };

    }

    protected void setStepNumber(int step) {

        stepNumber.setText(String.format(Locale.getDefault(), "%d", step));
        stepIndex = step - 1;

    }

    ///////////////
    //  HELPERS  //
    ///////////////

    public void hideConnector() {

        connectorLine.setVisibility(GONE);

    }

    public void activateStep() {

        contentLayout.setVisibility(VISIBLE);
        stepNumber.setBackground(ContextCompat.getDrawable(context, R.drawable.step_background_active));
        titleLabel.setTypeface(null, Typeface.BOLD);

        if (stepNumber.getText().toString().equals(""))
            stepNumber.setText(String.format(Locale.getDefault(), "%d", stepIndex + 1));

    }

    public void revertStep() {

        contentLayout.setVisibility(GONE);
        stepNumber.setBackground(ContextCompat.getDrawable(context, R.drawable.step_background_inactive));
        titleLabel.setTypeface(null, Typeface.NORMAL);

    }

    public void completeStep() {

        contentLayout.setVisibility(GONE);
        stepNumber.setText(null);
        stepNumber.setBackground(ContextCompat.getDrawable(context, R.drawable.step_background_completed));
        titleLabel.setTypeface(null, Typeface.NORMAL);

    }

    public void skipStep() {

        revertStep();

    }

}
