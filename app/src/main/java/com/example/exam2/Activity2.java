package com.example.exam2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Activity2 extends AppCompatActivity {

    TextView receivedMessage;
    EditText replyMessage;
    Button replyButton;
    Intent localIntent;
    Bundle localBundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        receivedMessage = findViewById(R.id.receivedMessage);
        replyMessage = findViewById(R.id.replyMessage);
        replyButton = findViewById(R.id.replyButton);

        localIntent = getIntent();
        localBundle = localIntent.getExtras();
        String msgFromA1 = localBundle.getString("message");

        receivedMessage.setText(msgFromA1);

        // send stuff back
        replyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                localBundle.putString("replyMessage", replyMessage.getText().toString());

                localIntent.putExtras(localBundle);

                setResult(Activity.RESULT_OK, localIntent);
                finish();
            }
        });

    }
}
