[ ![Download](https://api.bintray.com/packages/aduroideja/maven/material-stepper-library/images/download.svg) ](https://bintray.com/aduroideja/maven/material-stepper-library/_latestVersion)

# **Material Android Stepper Library** 1.3.2

A lightweight library for use with stepped processes.

> **NOTE:** Currnetly the library contains *ONLY* a **vertical stepper**. There are plans to develop a complete horizontal stepper to make the library complete

----

## **General**

A simple, easy-to-use library that aims to take the majority of development of a stepper off the developer.

You can focus your work on developing single steps and simply adding them to the stepper without implementing
functions such as ***back***, ***next*** or ***skip***. 

The visual design is inspired by Googles' Material Design guidelines and is supportet for **API 17 and up**.

## **Installation**

The library is available for download from `jcenter` and `Maven Central`. Use the following lines of code to include the library in your project.

Gradle:
```gradle
compile 'hr.aduro:materialstepper:1.3.2'
```

Or download the repository and copy the `materialstepper` folder to your projects' folder.
After that add this to your *app-level* `build.gradle`. 

```gradle
dependencies {
    ...
    compile project(":materialstepper")
}
```

## **How it works**

As any widget place the StepperView in a container:

```xml
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    xmlns:app="http://schemas.android.com/apk/res-auto">

    .
    .
    .

    <hr.aduro.materialstepperlibrary.StepperView
        android:id="@+id/stepper_view"
        android:layout_width="match_parent"
        android:layout_below="@id/toolbar_layout"
        android:layout_height="match_parent"/>

</RelativeLayout>
```

Reference it in your activity or fragment:
```java
stepperView = (StepperView) findViewById(R.id.stepper_view);
```

Initialize the adapter:
```java
StepperAdapter adapter = new StepperAdapter(getFragmentManager());
```

Choose between two types of callbacks for NEXT and/or SKIP buttons, and add fragments to the adapter:

```java
adapter.add("Step 1", new BlankFragment1(), new StepperButtonListenerDefault("go ahead"));
```
After you have added all your steps (fragments) set the adapter to `stepperView`:

```java
stepperView.setAdapter(adapter);
```

And there you go. A simple steper should appear with step numbers and a functional flow of elements.
Scroll down to see the more advanced functionality implementations.

# ADVANCED

This section will go in depth of additional functionalities and implementations.

## Button listeners

There are two types of button listeners that come with this library.

One is a default one, implementing predefined behaviour for NEXT and SKIP.
The other one is a custom listener that gives you the ability to supplement the stepping behaviour with
your own functionality.

For both listeners a single `String` parameter is required on initialization. This will be the NEXT button
label. The reason this is mandatory is that according to the Material Design Guidelines there should always be a 
button present that the user can press indicating he has copmleted a step. Also, I didn't want to hardcode a "NEXT" 
string because I think that if you want to do a non-english or multilingual app you should provide your own strings.
Both listeners also take a second, optional, `String` that will be used as the SKIP button label. If this string is not defined
the skip button will not be displayed.

### StepperButtonListenerDefault
The `StepperButtonListenerDefault` is a button listener that does a simple operation of finishing or 
skipping a step in a `FINISHED` or `SKIPPED` state.
In essence it only adjusts the visual representation of a step. To initialize it simply call:
```java
// INITIALIZE THE LISTENER WITH ONLY THE NEXT BUTTON, this is also a mandatory value
StepperButtonListenerDefault stepperListener = new StepperButtonListenerDefault(nextLbl);
// This will have the same result as the first call, not showing the SKIP button
StepperButtonListenerDefault stepperListenerWithoutSkip = new StepperButtonListenerDefault(nextLbl, null);
// INITIALIZE THE LISTENER WITH NEXT AND SKIP BUTTONS
StepperButtonListenerDefault stepperListenerWithSkip = new StepperButtonListenerDefault(nextLbl, skipLbl);
```

### StepperButtonListener
The `StepperButtonListener` is the base from whitch the `StepperButtonListenerDefault` is derived. 
This listener alows you to add functionality to the NEXT and SKIP buttons for each step. It provides two
methods for overwriting, both self explanatory.

```java
StepperButtonListener nextListener = new StepperButtonListener("next", "skip") {

    @Override
    public boolean onNext() {

        // Do operations here, like display mesaages

        // If you need to perform a check of some sort (like verify a text input) replace the super.onNext()
        return super.onNext();

    }

    @Override
    public void onSkip() {

        super.onSkip();
                
        // Do operations here, like display messages

    }

};
```

The `onNext()` method has a **return** value that is either `true` or `false`. This is intended for 
performing checks that depend on user input in a step. The logic is that if the **return** value is `true` the steper
will proceed to the next step, otherwise it will do nothing.

The `onSkip()` method has no return value since it *will skip* regardless of the added functionality. The default skip is 
**one step**, meaning that it will skip to the next step in the chain unless otherwise defined 
(covered in [this](### Jump to certain step) section).

## Working with SteperView

I have provided convenience methods for certain aspects of flow control. 

### Toggling NEXT and STEP buttons enabled

In case you need to enable/disable either buttons there are two methods to do so, one for each button.
The `enableNextForStep(stepNumber, enable)` and `enableSkipForStep(stepNumber, enable)` control the button behaviour. 
The step number indicates the step for whitch you wish to perform this action (*steps are 0 based*) and the boolean value 
indicates the desired state (*both buttons are enabled by default*).

Example:
```java
stepperView.setAdapter(adapter);
// add AFTER adapter is set to stepperView
stepperView.enableNextForStep(1, false);
```

Also both buttons have corresponding getters for enabled state like:
```java
stepperView.getIsNextEnabled();
```

### Getting current step index
If you want to retrieve the current stepper position a.k.a. the index of the current step in use:
```java
// returns a 0 based step index
int index = stepperView.getCurrentStepIndex();
```

### Implementing *onBackPress()* behaviour for StepperView

This should be implemented if you want to enable a *returning to previous step* behaviour on hardware keyboard BACK press.
The `previousStep()` method returns to the previously active step.
Simply add the following to your activity:

```java
@Override
public boolean onKeyDown(int keyCode, KeyEvent event) {

    if (keyCode == KeyEvent.KEYCODE_BACK && stepperView.getCurrentStepIndex() > 0) {

        stepperView.previousStep();             // <- step back
        return true;                            // must be true

    } else
        return super.onKeyDown(keyCode, event);

}
```

### Jump to certain step
This is useful for skipping certain steps. One example would be to jump to a certain step when the user presses the
SKIP button. The library won't mark the skipped steps as completed, rather, they will be marked as inactive. If 
you implemented the *onBackPress()* behaviour and the user presses the back button, the stepper will return to
the step from whitch it skipped and NOT the first previous step in the chain.

Example:
```java
@Override
public void onSkip() {

    super.onSkip();

    stepperView.jumpToStep(2);      // <- zero based index of step to jump to

}
```

## Working with StepperAdapter

With the obligatory `add(stepTitle, stepFragment, buttonListener);` there are a few more convenience
methods.

*Retrieving the item count in the adapter*
```java
adapter.getCount()
```

*Getting title of certain step*
```java
adapter.getTitleAt(stepIndex);
```

*Getting fragment from step*
```java
adapter.getFragmentAt(stepIndex);
```
> **NOTE:** this method returns the `Fragment` class and needs to be cast to your custom fragment

## Customizing the visual appearance
The library will use the default color scheme as devined in your `styles.xml` file. I reccomend you set your own color scheme for the stepper, the guidelines are [here](https://material.google.com/components/steppers.html#steppers-types-of-steps).

> **NOTE:** As of version 1.1 of the library there is no need to color the *NEXT* and *SKIP* button if you wish to retain a default theme.

> **NOTE:** As of version 1.1 of the library I fixed a crash for all *pre-Lollipop* devices using this library

```java
StepperColorScheme colorScheme = new StepperColorScheme();
        
colorScheme.setStepLineColor(Color.BLACK);
colorScheme.setStepTitleColor(Color.DKGRAY);
colorScheme.setStepNumberTextColor(Color.WHITE);
colorScheme.setNextBtnBackgroundColor(ContextCompat.getColor(this, R.color.colorAccent));
colorScheme.setSkipBtnBackgroundColor(Color.WHITE);
colorScheme.setSkipBtnTextColor(Color.DKGRAY);
colorScheme.setNextBtnTextColor(Color.WHITE);

adapter.setStepperColorScheme(colorScheme);
```

## EXAMPLE APP

For a detailed example take a look at the example app. It features three fragments and includes all relevant functionality.
