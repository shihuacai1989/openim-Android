package com.openim.demo;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.openim.client.ClientConstants;

/**
 * Created by shihuacai on 2015/8/8.
 */
public class ChatActivity extends Activity {

    private TextView chatWhoView;
    private ListView chatHistoryList;

    private Button sendBtn;

    ChatBroadCastReceiver chatBroadCastReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        setContentView(R.layout.chat_layout);

        chatHistoryList = (ListView)findViewById(R.id.chatHistoryList);
        chatWhoView = (TextView)findViewById(R.id.chatWhoView);

        Intent intent = getIntent();
        String who = intent.getStringExtra(DemoConstants.who);

        chatWhoView.setText(who);

        chatBroadCastReceiver = new ChatBroadCastReceiver();
        IntentFilter filter = new IntentFilter(ClientConstants.chatBroadcastFilter);
        registerReceiver(chatBroadCastReceiver, filter);
    }

    private void initSendBtn(){
        sendBtn = (Button)findViewById(R.id.sendBtn);
        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private class ChatBroadCastReceiver extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {

        }
    }
}
