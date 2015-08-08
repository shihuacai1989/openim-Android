package com.openim.client;

import com.openim.common.im.bean.DeviceMsgType;
import com.openim.common.im.bean.ProtobufDeviceMsg;

/**
 * Created by shihuacai on 2015/8/8.
 */
public class MessageBuilder {

    public static ProtobufDeviceMsg.DeviceMsg buildLoginMsg(String loginId){
        ProtobufDeviceMsg.DeviceMsg.Builder builder = ProtobufDeviceMsg.DeviceMsg.newBuilder();

        builder.setType(DeviceMsgType.LOGIN);
        builder.setLoginId(loginId);
        builder.build();

        return builder.build();
    }

    public static ProtobufDeviceMsg.DeviceMsg buildSendMsg(String to, String message){
        ProtobufDeviceMsg.DeviceMsg.Builder builder = ProtobufDeviceMsg.DeviceMsg.newBuilder();

        builder.setType(DeviceMsgType.SEND);
        builder.setTo(to);
        builder.setMsg(message);
        builder.build();

        return builder.build();
    }

    public static ProtobufDeviceMsg.DeviceMsg buildLogout(){
        ProtobufDeviceMsg.DeviceMsg.Builder builder = ProtobufDeviceMsg.DeviceMsg.newBuilder();

        builder.setType(DeviceMsgType.LOGOUT);
        builder.build();

        return builder.build();
    }
}
