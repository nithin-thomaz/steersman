package com.example.nithin.locations;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MilegeActivity extends AppCompatActivity {

    private static final String TAG="MilegeActivity";
    DatabaseHelper mDatabaseHelper;
    private EditText num1;
    private EditText num2;
    private EditText num3;
    private EditText num4;
    private EditText num5;
    private Button submit,submit1,submit2;
    private TextView result1;
    private TextView result2;
    //


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_milege);

        num1=(EditText)findViewById(R.id.editText1);
        num2=(EditText)findViewById(R.id.editText2);
        num3=(EditText)findViewById(R.id.editText3);
        num4=(EditText)findViewById(R.id.editText4);
        num5=(EditText)findViewById(R.id.editText5);
        submit=(Button)findViewById(R.id.btnSubmit);
        submit1=(Button)findViewById(R.id.btnSubmit1);
        submit2=(Button)findViewById(R.id.btnSubmit2);
        mDatabaseHelper=new DatabaseHelper(this);
        result1=(TextView)findViewById(R.id.textView1);
        result2=(TextView)findViewById(R.id.textView2);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int number1 = Integer.parseInt(num1.getText().toString());
                int number2 = Integer.parseInt(num2.getText().toString());
                int number3 = Integer.parseInt(num3.getText().toString());
                int number4 = Integer.parseInt(num4.getText().toString());
                int distance = number2 - number1;
                int mileage = distance / number3;
                int cost = number3 * number4;
                result1.setText("MILEAGE:" + String.valueOf(mileage));
                result2.setText("TOTAL COST:" + String.valueOf(cost));

            }
        });
        submit1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newEntry = num5.getText().toString();
                if (num5.length() != 0) {
                    AddData(newEntry);
                    num5.setText("");
                } else {
                    toastMessage("You must put something in the text field!");
                }
            }
        });
        submit2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MilegeActivity.this, ListDataActivity.class);
                startActivity(intent);
            }
        });

    }
    public void AddData(String newEntry) {
        boolean insertData = mDatabaseHelper.addData(newEntry);

        if (insertData) {
            toastMessage("Data Successfully Inserted!");
        } else {
            toastMessage("Something went wrong");
        }
    }
    private void toastMessage(String message){
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show();
    }
}
