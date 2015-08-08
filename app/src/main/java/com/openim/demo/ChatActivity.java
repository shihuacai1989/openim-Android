package com.openim.demo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

/**
 * Created by shihuacai on 2015/8/8.
 */
public class ChatActivity extends Activity {

    private TextView chatWhoView;
    private ListView chatHistoryList;

    private Button sendBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        setContentView(R.layout.chat_layout);

        chatHistoryList = (ListView)findViewById(R.id.chatHistoryList);
        chatWhoView = (TextView)findViewById(R.id.chatWhoView);

        Intent intent = getIntent();
        String who = intent.getStringExtra(DemoConstants.who);

        chatWhoView.setText(who);
    }

    private void initSendBtn(){
        sendBtn = (Button)findViewById(R.id.sendBtn);
        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
