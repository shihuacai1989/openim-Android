package com.openim.client;

import com.openim.common.im.bean.ProtobufDeviceMsg;

/**
 * Created by shihuacai on 2015/8/8.
 */
public interface IMessageSender {
    void sendMessage(ProtobufDeviceMsg.DeviceMsg deviceMsg);
}
