package com.openim.client.dispatch;

import android.content.Context;

import com.openim.common.im.bean.ProtobufDeviceMsg;

/**
 * Created by shihuacai on 2015/8/9.
 */
public interface IMessageHandler {
    void handle(Context context, ProtobufDeviceMsg.DeviceMsg deviceMsg);
}
