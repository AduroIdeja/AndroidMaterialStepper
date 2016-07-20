package hr.aduro.materialstepper;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;

import hr.aduro.materialstepperlibrary.StepperAdapter;
import hr.aduro.materialstepperlibrary.StepperColorScheme;
import hr.aduro.materialstepperlibrary.StepperView;

public class MainActivity extends AppCompatActivity {

    private StepperView stepperView;
    private StepperAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        stepperView = (StepperView) findViewById(R.id.stepper_view);
        adapter = new StepperAdapter(getFragmentManager());

        adapter.add("First step", new BlankFragment1(), "next", null);
        adapter.add("Second title", new BlankFragment2(), "dalje", "preskoči");
        adapter.add("Just playing", new BlankFragment3(), "tutto finito", null);

        StepperColorScheme colorScheme = new StepperColorScheme();
        colorScheme.setStepLineColor(Color.RED);
        colorScheme.setStepTitleColor(Color.BLUE);
        colorScheme.setStepNumberTextColor(Color.YELLOW);
        colorScheme.setSkipBtnBackgroundColor(Color.GREEN);
        colorScheme.setNextBtnBackgroundColor(Color.BLACK);
        colorScheme.setSkipBtnTextColor(Color.MAGENTA);
        colorScheme.setNextBtnTextColor(Color.WHITE);

        adapter.setStepperColorScheme(colorScheme);

        stepperView.setAdapter(adapter);

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK && stepperView.getCurrentStep() > 0) {

            stepperView.previousStep();
            return true;

        } else
            return super.onKeyDown(keyCode, event);

    }

}
