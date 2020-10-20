package com.timytookatimber.resistorcalc;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.NumberFormat;

public class ScrollingActivity extends AppCompatActivity {

    Button bValue1;
    Button bValue2;
    Button bValue3;
    Button bTolarance;
    Button bCalculate;
    EditText ohmsText;
    TextView maxMin;
    //used to check what band button was pressed
    int button = 0;

    //default color bands
    int band1Color = 2;
    int band2Color = 2;
    int band3Color = 2;
    int toleranceColor = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        CollapsingToolbarLayout toolBarLayout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
        toolBarLayout.setTitle(getTitle());

        bValue1 = findViewById(R.id.value1);
        bValue2 = findViewById(R.id.value2);
        bValue3 = findViewById(R.id.value3);
        bTolarance = findViewById(R.id.tolerance);
        bCalculate = findViewById(R.id.calculate);
        ohmsText = (EditText)findViewById(R.id.ohms);
        maxMin = (TextView) findViewById(R.id.maxMin);

        registerForContextMenu(findViewById(R.id.value1));
        registerForContextMenu(findViewById(R.id.value2));
        registerForContextMenu(findViewById(R.id.value3));
        registerForContextMenu(findViewById(R.id.tolerance));

        bValue1.setOnLongClickListener(new View.OnLongClickListener() {
            public boolean onLongClick(View v) {
                button = 1;
                return false;
            }
        });
        bValue2.setOnLongClickListener(new View.OnLongClickListener() {
            public boolean onLongClick(View v) {
                button = 2;
                return false;
            }
        });
        bValue3.setOnLongClickListener(new View.OnLongClickListener() {
            public boolean onLongClick(View v) {
                button = 3;
                return false;
            }
        });
        bTolarance.setOnLongClickListener(new View.OnLongClickListener() {
            public boolean onLongClick(View v) {
                button = 4;
                return false;
            }
        });bCalculate.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //get colors
                ohmsText.setText(calculateValue(band1Color, band2Color, band3Color, toleranceColor));

                maxMin.setText(CalculateTolerance(band1Color, band2Color, band3Color, toleranceColor));

            }
        });


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Un-fuck Yourself Recruit", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    public String calculateValue(int band1, int band2, int band3, int band4){
        String value = "";
        String tolerance = "";
        String temp = "";
        int number = band1 * 10;
        number += band2;
        number *= Math.pow(10, band3);

        if(band4 ==10)
            tolerance = " ±5%";
        else if(band4 ==11)
            tolerance = " ±10%";
        else if(band4 ==12)
            tolerance = " ±20%";

        NumberFormat myFormat = NumberFormat.getInstance();
        myFormat.setGroupingUsed(true);
        temp = myFormat.format(number);
        value = temp + "Ω" + tolerance;
        return value;
    }

    public String CalculateTolerance(int band1, int band2, int band3, int band4){
        String value = "";
        String tolerance = "";
        String sMin = "";
        String sMax = "";
        double max = 0, min = 0;
        int number = band1 * 10;
        number += band2;
        number *= Math.pow(10, band3);

        if(band4 == 10){
            max = number + (number * .05);
            min = number - (number * .05);
        }else if(band4 == 11){
            max = number + (number * .1);
            min = number - (number * .1);
        }else if(band4 == 12){
            max = number + (number * .2);
            min = number - (number * .2);
        }

        NumberFormat myFormat = NumberFormat.getInstance();
        myFormat.setGroupingUsed(true);
        sMin = myFormat.format(min);
        sMax = myFormat.format(max);
        value = "Min: " + sMin + "Ω" + " Max: " + sMax + "Ω";
        return value;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_color_bands, menu);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch (item.getItemId()) {
            case R.id.color_black:
                // change color to black
                if(button == 1) {
                    bValue1.setBackgroundColor(Color.BLACK);
                    band1Color = 0;
                }else if(button == 2) {
                    bValue2.setBackgroundColor(Color.BLACK);
                    band2Color = 0;
                }else if(button == 3) {
                    bValue3.setBackgroundColor(Color.BLACK);
                    band3Color = 0;
                }else if(button == 4) {
                    bTolarance.setBackgroundColor(Color.BLACK);
                    toleranceColor = 0;
                }
                    return true;
            case R.id.color_brown:
                if(button == 1) {
                    bValue1.setBackgroundColor(Color.rgb(165, 42, 42));
                    band1Color = 1;
                }else if(button == 2) {
                    bValue2.setBackgroundColor(Color.rgb(165, 42, 42));
                    band2Color = 1;
                }else if(button == 3) {
                    bValue3.setBackgroundColor(Color.rgb(165, 42, 42));
                    band3Color = 1;
                }else if(button == 4) {
                    bTolarance.setBackgroundColor(Color.rgb(165, 42, 42));
                    toleranceColor = 1;
                }
                return true;
            case R.id.color_red:
                if(button == 1) {
                    bValue1.setBackgroundColor(Color.RED);
                    band1Color = 2;
                }else if(button == 2) {
                    bValue2.setBackgroundColor(Color.RED);
                    band2Color = 2;
                }else if(button == 3) {
                    bValue3.setBackgroundColor(Color.RED);
                    band3Color = 2;
                }else if(button == 4) {
                    bTolarance.setBackgroundColor(Color.RED);
                    toleranceColor = 2;
                }
                return true;
            case R.id.color_orange:
                if(button == 1) {
                    bValue1.setBackgroundColor(Color.rgb(255, 165, 0));
                    band1Color = 3;
                }else if(button == 2) {
                    bValue2.setBackgroundColor(Color.rgb(255, 165, 0));
                    band2Color = 3;
                }else if(button == 3) {
                    bValue3.setBackgroundColor(Color.rgb(255, 165, 0));
                    band3Color = 3;
                }else if(button == 4) {
                    bTolarance.setBackgroundColor(Color.rgb(255, 165, 0));
                    toleranceColor = 3;
                }
                return true;
            case R.id.color_yellow:
                if(button == 1) {
                    bValue1.setBackgroundColor(Color.YELLOW);
                    band1Color = 4;
                }else if(button == 2) {
                    bValue2.setBackgroundColor(Color.YELLOW);
                    band2Color = 4;
                }else if(button == 3) {
                    bValue3.setBackgroundColor(Color.YELLOW);
                    band3Color = 4;
                }else if(button == 4) {
                    bTolarance.setBackgroundColor(Color.YELLOW);
                    toleranceColor = 4;
                }
                return true;
            case R.id.color_green:
                if(button == 1) {
                    bValue1.setBackgroundColor(Color.GREEN);
                    band1Color = 5;
                }
                else if(button == 2) {
                    bValue2.setBackgroundColor(Color.GREEN);
                    band2Color = 5;
                }else if(button == 3) {
                    bValue3.setBackgroundColor(Color.GREEN);
                    band3Color = 5;
                }else if(button == 4) {
                    bTolarance.setBackgroundColor(Color.GREEN);
                    toleranceColor = 5;
                }
                return true;
            case R.id.color_blue:
                if(button == 1) {
                    bValue1.setBackgroundColor(Color.BLUE);
                    band1Color = 6;
                }else if(button == 2) {
                    bValue2.setBackgroundColor(Color.BLUE);
                    band2Color = 6;
                }else if(button == 3) {
                    bValue3.setBackgroundColor(Color.BLUE);
                    band3Color = 6;
                }else if(button == 4) {
                    bTolarance.setBackgroundColor(Color.BLUE);
                    toleranceColor = 6;
                }
                return true;
            case R.id.color_violet:
                if(button == 1) {
                    bValue1.setBackgroundColor(Color.rgb(238, 130, 238));
                    band1Color = 7;
                }else if(button == 2) {
                    bValue2.setBackgroundColor(Color.rgb(238, 130, 238));
                    band2Color = 7;
                }else if(button == 3) {
                    bValue3.setBackgroundColor(Color.rgb(238, 130, 238));
                    band3Color = 7;
                }else if(button == 4) {
                    bTolarance.setBackgroundColor(Color.rgb(238, 130, 238));
                    toleranceColor = 7;
                }
                return true;
            case R.id.color_grey:
                if(button == 1) {
                    bValue1.setBackgroundColor(Color.GRAY);
                    band1Color = 8;
                }else if(button == 2) {
                    bValue2.setBackgroundColor(Color.GRAY);
                    band2Color = 8;
                }else if(button == 3) {
                    bValue3.setBackgroundColor(Color.GRAY);
                    band3Color = 8;
                }else if(button == 4) {
                    bTolarance.setBackgroundColor(Color.GRAY);
                    toleranceColor = 8;
                }
                return true;
            case R.id.color_white:
                if(button == 1) {
                    bValue1.setBackgroundColor(Color.WHITE);
                    band1Color = 9;
                }else if(button == 2) {
                    bValue2.setBackgroundColor(Color.WHITE);
                    band2Color = 9;
                }else if(button == 3) {
                    bValue3.setBackgroundColor(Color.WHITE);
                    band3Color = 9;
                }else if(button == 4) {
                    bTolarance.setBackgroundColor(Color.WHITE);
                    toleranceColor = 9;
                }
                return true;
            case R.id.color_gold:
                if(button == 4) {
                    bTolarance.setBackgroundColor(Color.rgb(255, 215, 00));
                    toleranceColor = 10;
                }
                return true;
            case R.id.color_silver:
                if(button == 4) {
                    bTolarance.setBackgroundColor(Color.rgb(192, 192, 192));
                    toleranceColor = 11;
                }
                return true;
            case R.id.color_none:
                if(button == 4) {
                    bTolarance.setBackgroundColor(Color.rgb(209, 193, 159));
                    toleranceColor = 12;
                }
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_scrolling, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}