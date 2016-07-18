package hr.aduro.materialstepper;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import hr.aduro.materialstepperlibrary.StepperAdapter;
import hr.aduro.materialstepperlibrary.StepperView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        StepperView stepperView = (StepperView) findViewById(R.id.stepper_view);
        StepperAdapter adapter = new StepperAdapter(this, getFragmentManager());

        for(int i = 0; i < 5; i++){

            adapter.add(new Fragment());

        }

        stepperView.setAdapter(adapter);

    }

}
