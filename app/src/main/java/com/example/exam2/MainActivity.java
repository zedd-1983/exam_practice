package com.example.exam2;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Date;

public class MainActivity extends AppCompatActivity {

    Button sendButton;
    EditText message;

    Intent secondActivity;
    Bundle messageBundle;
    Date currentDate = new Date();

    //SharedPreferences myPreferences = getSharedPreferences("myPreferences", MODE_PRIVATE);

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sendButton = findViewById(R.id.sendButton);
        message = findViewById(R.id.editText);

        savePreferences("Date/time", currentDate.toString());

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                secondActivity = new Intent(MainActivity.this, Activity2.class);
                messageBundle = new Bundle();

                messageBundle.putString("message", message.getText().toString());
                savePreferences("initialMessage", message.getText().toString());

                secondActivity.putExtras(messageBundle);

                startActivityForResult(secondActivity, 201);

            }
        }); // onClickListener

    } // onCreate

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        try {
            if((requestCode == 201 ) && (resultCode == Activity.RESULT_OK)) {
                Bundle myReplyBundle = data.getExtras();
                String receivedReply = myReplyBundle.getString("replyMessage");
                savePreferences("replyMessage", receivedReply);
                message.setText(receivedReply);
            }
        } catch (Exception e)
        {
            message.setText("Problems " + requestCode + " " + resultCode);
        }
    }

    private void savePreferences(String key, String value) {

        SharedPreferences myPreferences = getSharedPreferences("myPreferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = myPreferences.edit();
        editor.putString(key, value);
        editor.commit();
    }
}
