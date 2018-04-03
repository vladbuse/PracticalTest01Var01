package practicaltest01var01.eim.systems.cs.pub.ro.practicaltest01var01;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class PracticalTest01Var01SecondaryActivity extends AppCompatActivity {

    TextView intent_res = null;
    Button register = null;
    Button cancel = null;

    private ButtonClickListener buttonClickListener = new ButtonClickListener();
    private class ButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            switch(view.getId()) {
                case R.id.ok:
                    setResult(RESULT_OK, null);
                    break;
                case R.id.cancel:
                    setResult(RESULT_CANCELED, null);
                    break;
            }
            finish();
        }
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test01_var01_secondary);


        Intent intent = getIntent();



        register = (Button)findViewById(R.id.ok);
        cancel = (Button)findViewById(R.id.cancel);
        intent_res = (TextView)findViewById(R.id.result_intent);
        if (intent != null && intent.getExtras().containsKey("res")) {
            String text = intent.getStringExtra("res");
            intent_res.setText(text);
        }
        register.setOnClickListener(buttonClickListener);
        cancel.setOnClickListener(buttonClickListener);
    }
}
