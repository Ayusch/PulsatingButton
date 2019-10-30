# PulsatingButton
A simple Android UI library to create a Growing and Shrinking button. It's pulse rate and size of pulse offset can be controlled.

Usage:

XML:
```
    <com.ayusch.pulsatingbutton.PulsatingButton
        android:id="@+id/pulsating_button"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_centerInParent="true"
        app:buttonColor="@color/colorAccent"
        app:horizontalOffset="40"
        app:pulseDuration="1000"
        app:text="Submit"
        app:textColor="@android:color/white"
        app:verticalOffset="40" />
```

Kotlin:

```
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        pulsating_button.startAnimation()
    }
}
```


# Download

For Maven:
```
<dependency>
  <groupId>com.ayusch.pulsatingbutton</groupId>
  <artifactId>pulsatingbutton</artifactId>
  <version>1.0.0</version>
  <type>pom</type>
</dependency>
```
For Gradle:
```
implementation 'com.ayusch.pulsatingbutton:pulsatingbutton:1.0.0'
```


# Developed By

Ayusch: https://ayusch.com
email: contact@ayusch.com
