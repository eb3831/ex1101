package com.example.ex1101;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity implements View.OnCreateContextMenuListener
{
    Intent gi;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
}