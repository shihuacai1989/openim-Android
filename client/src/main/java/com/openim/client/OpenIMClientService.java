package com.openim.client;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;

import com.openim.client.connector.ChatServerConnector;
import com.openim.client.connector.EsbConnector;
import com.openim.client.connector.IConnector;
import com.openim.common.bean.ResultCode;

/**
 * Created by shihuacai on 2015/8/6.
 */
public class OpenIMClientService extends Service{
    private Context context;

    @Override
    public IBinder onBind(Intent intent) {
        context = this.getApplicationContext();

        IConnector esbConnector = new EsbConnector(context, ClientConstants.esbUrl);
        esbConnector.connect(new IConnector.ConnectorListener<String>() {
            @Override
            public void finished(int code, String data) {
                if(code != ResultCode.success){

                }else{
                    if(data != null && data.trim().length() == 0){
                        String chatServerIp = data.split(":")[0];
                        int chatServerPort = Integer.valueOf(data.split(":")[1]);
                        IConnector connector = new ChatServerConnector(context, chatServerIp, chatServerPort);
                        connector.connect(null);
                    }
                }
            }
        });


        return null;
    }
}
