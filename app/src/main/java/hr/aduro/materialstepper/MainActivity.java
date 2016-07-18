package hr.aduro.materialstepper;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;

import hr.aduro.materialstepperlibrary.StepperAdapter;
import hr.aduro.materialstepperlibrary.StepperView;

public class MainActivity extends AppCompatActivity {

    private StepperView stepperView;
    private StepperAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        stepperView = (StepperView) findViewById(R.id.stepper_view);
        adapter = new StepperAdapter(this, getFragmentManager());

        for (int i = 0; i < 5; i++) {

            adapter.add(new Fragment());

        }

        stepperView.setAdapter(adapter);

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK && stepperView.getCurrentStep() > 0) {

            StepperView.previousStep();
            return true;

        }else
            return super.onKeyDown(keyCode, event);

    }

}
