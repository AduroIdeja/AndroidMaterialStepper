package hr.aduro.materialstepperlibrary;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

/**
 * Created by Tomislav Horvat on 7/14/16.
 */
public class VerticalStep extends RelativeLayout{

    public VerticalStep(Context context) {

        super(context);
        initialize(context);

    }

    public VerticalStep(Context context, AttributeSet attributeSet) {

        super(context, attributeSet);
        initialize(context);

    }

    private void initialize(Context context){

        inflate(context, R.layout.single_step_container, this);

    }

}
