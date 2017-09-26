package com.ninegolfy.helloworld;
import android.icu.lang.UCharacter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    TextView tvHello,tvResult;
    EditText editTextHello,editText1,editText2;
    Button btnButton,btnResult;
    RadioButton rbPlus, rbMinus, rbMultiply, rbDivide;
    RadioGroup rgOperator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initInstances();
    }

    private void initInstances() {
        String mData = "<a href=\"http://www.google.com\">http://www.google.com</a>";
        tvHello = (TextView) findViewById(R.id.tvHello);
        tvHello.setMovementMethod(LinkMovementMethod.getInstance());
        //Check version API if 24+ else older
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            tvHello.setText(Html.fromHtml(mData, Html.FROM_HTML_MODE_LEGACY));
        } else {
            tvHello.setText(Html.fromHtml(mData));
        }

        editTextHello = (EditText) findViewById(R.id.editTextHello);
        editTextHello.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE){
                    // Copy text in EditText to TextView
                    tvHello.setText (editTextHello.getText());
                    return true;
                }
                return false;
            }
        });

        btnButton = (Button) findViewById(R.id.btnCopy);
        btnButton.setOnClickListener(this);

        ////////////////////////
        // Start Here //////////
        tvResult = (TextView) findViewById(R.id.tvResult);
        editText1 = (EditText) findViewById(R.id.editText1);
        editText2 = (EditText) findViewById(R.id.editText2);
        btnResult = (Button) findViewById(R.id.btnCalculate);
        ////////////////////////
        rgOperator = (RadioGroup) findViewById(R.id.rgOperator);
//        rbPlus = (RadioButton) findViewById(R.id.rbPlus);
//        rbMinus  = (RadioButton) findViewById(R.id.rbMinus);
//        rbMultiply  = (RadioButton) findViewById(R.id.rbMultiply);
//        rbDivide= (RadioButton) findViewById(R.id.rbDivide);

        btnResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int val1 = 0;
                int val2 = 0;
                int sum = 0;
                try {
                    val1 = Integer.parseInt(editText1.getText().toString());

                }catch (NumberFormatException e) {
                }
                try{
                    val2 = Integer.parseInt(editText2.getText().toString());
                }catch (NumberFormatException e){

                }

                switch (rgOperator.getCheckedRadioButtonId()){
                    case R.id.rbPlus:
                        sum = val1 + val2;
                        break;
                    case R.id.rbMinus:
                        sum = val1 - val2;
                        break;
                    case R.id.rbMultiply:
                        sum = val1 * val2;
                        break;
                    case R.id.rbDivide:
                        sum = val1 / val2;
                        break;
                    //Add case here

                }
//
//                if (rbPlus.isChecked() == true ) {
//                    sum = val1 + val2;
//                }else if (rbMinus.isChecked() == true){
//                    sum = val1 - val2;
//                }else if (rbMultiply.isChecked() == true){
//                    sum = val1 * val2;
//                }else if (rbDivide.isChecked() == true){
//                    sum = val1 / val2;
//                }
                tvResult.setText(sum+"");
                Log.d("Calculation", "Result = " + sum);
                Toast.makeText(MainActivity.this,
                        "Result = "+sum,
                        Toast.LENGTH_SHORT)
                        .show();
            }
        });




    }

    @Override
    public void onClick(View v) {
        if (v == btnButton){
            tvHello.setText(editTextHello.getText());
        }
    }


}
