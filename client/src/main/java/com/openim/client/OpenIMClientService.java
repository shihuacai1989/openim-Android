package com.openim.client;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;

import com.openim.client.connector.ChatServerConnector;
import com.openim.client.connector.EsbConnector;
import com.openim.client.connector.IConnector;
import com.openim.client.listener.IMessageListener;
import com.openim.common.bean.ResultCode;
import com.openim.common.im.bean.DeviceMsgType;
import com.openim.common.im.bean.ProtobufDeviceMsg;

/**
 * Created by shihuacai on 2015/8/6.
 */
public class OpenIMClientService extends Service{
    private Context context;

    private ChatServerConnector chatServerConnector;

    private IMessageListener messageListener;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();

        messageListener = new IMessageListener() {
            @Override
            public void onReceive(ProtobufDeviceMsg.DeviceMsg deviceMsg) {
                int type = deviceMsg.getType();
                if(type == DeviceMsgType.SEND){

                }
            }
        };
    }

    @Override
    public IBinder onBind(Intent intent) {

        IConnector esbConnector = new EsbConnector(context, ClientConstants.esbUrl);
        esbConnector.connect(new IConnector.ConnectorListener<String>() {
            @Override
            public void finished(int code, String data) {
                if(code != ResultCode.success){

                }else{
                    if(data != null && data.trim().length() == 0){
                        String chatServerIp = data.split(":")[0];
                        int chatServerPort = Integer.valueOf(data.split(":")[1]);
                        chatServerConnector = new ChatServerConnector(context, chatServerIp, chatServerPort);
                        chatServerConnector.registerMessageListener(messageListener);
                        chatServerConnector.connect(null);
                    }
                }
            }
        });

        return null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    private class SendBroadcasetReceiver extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            //Bundle bundle = intent.getExtras();
            //bundle.getByteArray()
        }
    }
}
