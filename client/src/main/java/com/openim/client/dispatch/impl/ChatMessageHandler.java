package com.openim.client.dispatch.impl;

import android.content.Context;
import android.content.Intent;

import com.openim.client.ClientConstants;
import com.openim.client.dispatch.IMessageHandler;
import com.openim.common.im.bean.ProtobufDeviceMsg;

/**
 * Created by shihuacai on 2015/8/9.
 */
public class ChatMessageHandler implements IMessageHandler {

    @Override
    public void handle(Context context, ProtobufDeviceMsg.DeviceMsg deviceMsg) {
        Intent intent = new Intent(ClientConstants.chatBroadcastFilter);
        context.sendBroadcast(intent);
    }
}
