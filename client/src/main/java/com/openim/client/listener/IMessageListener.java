package com.openim.client.listener;

import com.openim.common.im.bean.ProtobufDeviceMsg;

/**
 * Created by shihuacai on 2015/8/8.
 */
public interface IMessageListener {
    void onReceive(ProtobufDeviceMsg.DeviceMsg deviceMsg);
}
