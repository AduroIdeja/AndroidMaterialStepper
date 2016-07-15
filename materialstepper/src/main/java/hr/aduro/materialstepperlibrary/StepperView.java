package hr.aduro.materialstepperlibrary;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;

/**
 * A View representing the scroll view container of all inserted steps
 *
 * @author Tomislav Horvat
 */
public class StepperView extends ScrollView {


    public StepperView(Context context) {

        super(context);
        initialze(context);

    }

    public StepperView(Context context, AttributeSet attributeSet) {

        super(context, attributeSet);
        initialze(context);

    }

    private void initialze(Context context){

        inflate(context, R.layout.stepper, this);

    }

}
