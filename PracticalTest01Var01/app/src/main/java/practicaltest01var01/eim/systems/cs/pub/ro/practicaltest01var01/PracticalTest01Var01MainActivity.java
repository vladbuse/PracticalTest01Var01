package practicaltest01var01.eim.systems.cs.pub.ro.practicaltest01var01;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class PracticalTest01Var01MainActivity extends AppCompatActivity {

    private final static int SECONDARY_ACTIVITY_REQUEST_CODE = 1;

    Button north = null;
    Button south = null;
    Button west = null;
    Button east = null;
    Button switch1 = null;
    TextView result = null;
    TextView counts = null;
    int count;
    boolean serviceStatus = false;

    String theText;
    private class bListener implements View.OnClickListener{

        @Override
        public void onClick(View view) {

            if(view.getId() == R.id.switch1)
            {
                Intent intent = new Intent(getApplicationContext(), PracticalTest01Var01SecondaryActivity.class);
                intent.putExtra("res",theText);
                startActivityForResult(intent, SECONDARY_ACTIVITY_REQUEST_CODE);


            }
            else {

                Button clicked = null;
                boolean switching = false;
                switch (view.getId()) {
                    case R.id.north:
                        clicked = north;
                        break;
                    case R.id.south:
                        clicked = south;
                        break;
                    case R.id.east:
                        clicked = east;
                        break;
                    case R.id.west:
                        clicked = west;
                        break;
                }


                if (count == 0)
                    theText = clicked.getText().toString();
                else
                    theText += ", " + clicked.getText().toString();
                count++;


                counts.setText(String.valueOf(count) + " counts");
                result.setText(theText);
                if(count == 4)
                {
                    Intent intent = new Intent(getApplicationContext(), PracticalTest01Var01Service.class);
                    intent.putExtra("res",theText);
                    getApplicationContext().startService(intent);
                    serviceStatus = true;
                }
            }
        }
    }

    bListener bList = new bListener();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test01_var01_main);

        if(savedInstanceState != null)
        {
            if(savedInstanceState.containsKey("pressed"))
                count = savedInstanceState.getInt("pressed");
            else
                count = 0;

            if (savedInstanceState.containsKey("text"))
                theText = savedInstanceState.getString("text");
            else
                theText = "";
        }
        else {
            count = 0;
            theText = "";
        }



        north= (Button)findViewById(R.id.north);
        south= (Button)findViewById(R.id.south);
        east= (Button)findViewById(R.id.east);
        west= (Button)findViewById(R.id.west);
        switch1 = (Button)findViewById(R.id.switch1);


        result = (TextView)findViewById(R.id.res);
        counts = (TextView)findViewById(R.id.counts);

        switch1.setOnClickListener(bList);
        north.setOnClickListener(bList);
        south.setOnClickListener(bList);
        east.setOnClickListener(bList);
        west.setOnClickListener(bList);

    }


    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putInt("pressed",count);
        savedInstanceState.putString("text",theText);


    }
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        if (savedInstanceState.containsKey("pressed"))
            count = savedInstanceState.getInt("pressed");
        else
            count = 0;
        if (savedInstanceState.containsKey("text"))
            theText = savedInstanceState.getString("text");
        else
            theText = "";
        counts.setText(String.valueOf(count) + " counts");
        result.setText(theText);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (requestCode == SECONDARY_ACTIVITY_REQUEST_CODE) {
            Toast.makeText(this, "The activity returned with result " + resultCode, Toast.LENGTH_LONG).show();
            count = 0;
            theText = "";
            counts.setText(String.valueOf(count) + " counts");
            result.setText(theText);
        }
    }

    protected void onDestroy() {
        Intent intent = new Intent(this, PracticalTest01Var01Service.class);
        stopService(intent);
        super.onDestroy();
    }
}
