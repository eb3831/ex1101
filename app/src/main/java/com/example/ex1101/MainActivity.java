package com.example.ex1101;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

/**
 * The main activity
 * This activity allows users to increment a counter, reset it, and save the data using SharedPreferences.
 * It also provides navigation to a credits screen via the options menu.
 *
 * @author eliya bitton eb3831@bs.amalnet.k12.il
 * @version 2.0
 * @since 23/2/2025
 */
public class MainActivity extends AppCompatActivity implements View.OnCreateContextMenuListener
{
    Intent gi;
    int counter;
    String name;
    TextView tvCounter;
    EditText etName;
    SharedPreferences settings;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvCounter = findViewById(R.id.tvCounter);
        etName = findViewById(R.id.etName);

        settings = getSharedPreferences("PREFS_NAME", MODE_PRIVATE);

        int lastCount = settings.getInt("lastCount",0);
        String lastName = settings.getString("lastName", "");

        tvCounter.setText(String.valueOf(lastCount));
        if (lastName.isEmpty())
        {
            etName.setHint("Name");
        }
        else
        {
            etName.setText(lastName);
        }
    }

    /**
     * Creates the options menu.
     * @param menu The menu to be created.
     * @return true if the menu is created successfully.
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    /**
     * Handles menu item selections.
     * @param menuItem The selected menu item.
     * @return true if the menu item is handled successfully.
     */
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem menuItem)
    {
        String st = menuItem.getTitle().toString();
        if (st.charAt(0) == 'C')
        {
            gi = new Intent(this, Credits.class);
            startActivity(gi);
        }
        return super.onOptionsItemSelected(menuItem);
    }

    /**
     * Increments the counter and updates the displayed value.
     *
     * @param view The view that triggered the click event.
     */
    public void clickedCount (View view)
    {
        counter++;
        tvCounter.setText(String.valueOf(counter));
    }

    /**
     * Handles the reset button click event.
     * Resets all data to default values.
     * Sets the counter to 0, clears the name input, and resets the hint.
     *
     * @param view The view that triggered the click event
     */
    public void clickedReset(View view)
    {
        counter = 0;
        tvCounter.setText("0");
        etName.setText("");
        etName.setHint("Name");
    }

    /**
     * Handles the exit button click event.
     * Saves the current counter and name to SharedPreferences before exiting.
     *
     * @param view The view that triggered the click event
     */
    public void clickedExit(View view)
    {
        name = etName.getText().toString();

        SharedPreferences.Editor editor = settings.edit();
        editor.putInt("lastCount", counter);
        editor.putString("lastName", name);
        editor.commit();

        finish();
    }
}