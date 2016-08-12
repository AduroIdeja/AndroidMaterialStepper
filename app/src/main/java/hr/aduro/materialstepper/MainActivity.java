package hr.aduro.materialstepper;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.widget.Toast;

import hr.aduro.materialstepperlibrary.StepperAdapter;
import hr.aduro.materialstepperlibrary.StepperButtonListener;
import hr.aduro.materialstepperlibrary.StepperButtonListenerDefault;
import hr.aduro.materialstepperlibrary.StepperColorScheme;
import hr.aduro.materialstepperlibrary.StepperView;

public class MainActivity extends AppCompatActivity implements BlankFragment2.OnFragmentInteractionListener{

    private StepperView stepperView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle("Stepper Demo");

        stepperView = (StepperView) findViewById(R.id.stepper_view);
        final StepperAdapter adapter = new StepperAdapter(getFragmentManager());

        StepperButtonListener nextListener = new StepperButtonListener("next", "skip") {

            @Override
            public boolean onNext() {

                BlankFragment1 fragment1 = (BlankFragment1) adapter.getFragmentAt(0);

                String text = fragment1.getEditTextEntry();

                if(text.toCharArray().length > 0) {

                    return super.onNext();

                }else{

                    Toast.makeText(MainActivity.this, "enter some text", Toast.LENGTH_SHORT).show();
                    return false;

                }

            }

            @Override
            public void onSkip() {

                super.onSkip();

                stepperView.jumpToStep(2);

            }

        };

        adapter.add("First step", new BlankFragment1(), nextListener);
        adapter.add("Second title", new BlankFragment2(), new StepperButtonListenerDefault("go ahead", null));
        adapter.add(null, new BlankFragment3(), new StepperButtonListenerDefault("tutto finito"));

        StepperColorScheme colorScheme = new StepperColorScheme();
        colorScheme.setStepLineColor(Color.BLACK);
        colorScheme.setStepTitleColor(Color.DKGRAY);
        colorScheme.setStepNumberTextColor(Color.WHITE);
        colorScheme.setNextBtnBackgroundColor(ContextCompat.getColor(this, R.color.colorAccent));
        colorScheme.setSkipBtnBackgroundColor(Color.WHITE);
        colorScheme.setSkipBtnTextColor(Color.DKGRAY);
        colorScheme.setNextBtnTextColor(Color.WHITE);

        adapter.setStepperColorScheme(colorScheme);

        stepperView.setAdapter(adapter);

        stepperView.enableNextForStep(1, false);

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK && stepperView.getCurrentStepIndex() > 0) {

            stepperView.previousStep();
            return true;

        } else
            return super.onKeyDown(keyCode, event);

    }

    @Override
    public void onFragmentInteraction(boolean checked) {

        stepperView.enableNextForStep(1, checked);

    }
}
