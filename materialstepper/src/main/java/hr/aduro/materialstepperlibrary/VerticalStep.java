package hr.aduro.materialstepperlibrary;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;

/**
 * View representing a single, vertically orientated step
 *
 * @author Tomislav Horvat
 */
public class VerticalStep extends RelativeLayout{

    private Activity activity;
    private OnClickListener nextListener = null,
            skipListener = null;

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

    private void initialize(Context context){

        inflate(context, R.layout.vertical_step_container, this);

    }

    ///////////////
    //  SETTERS  //
    ///////////////

    /**
     * Sets custom colors for various visual elements. If not set the widget uses colors defined in @style
     * @param stepperColorScheme - a color scheme with defined colors
     */
    public void setCustomColors(StepperColorScheme stepperColorScheme){

    }

    /**
     * Sets a custom action to NEXT button if necessary
     * @param nextListener - a custom listener implementing onNext
     */
    public void setOnNextListener(final StepperButtonListener nextListener){

        this.nextListener = new OnClickListener() {
            @Override
            public void onClick(View view) {

                nextListener.onNext();

                // TODO perform next click

            }

        };

    }

    /**
     * Sets a custom action to SKIP button if necessary
     * @param skipListener - a custom listener implementing onSkip
     */
    public void setOnSkipListener(final StepperButtonListener skipListener){

        nextListener = new OnClickListener() {
            @Override
            public void onClick(View view) {

                skipListener.onSkip();

                // TODO perform skip click

            }

        };

    }

}
