package com.openim.client.connector;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * Created by shihuacai on 2015/8/6.
 */
public class ChatServerConnector implements IConnector {
    private String chatServerIp;
    private int chatServerPort;

    public ChatServerConnector(String chatServerIp, int chatServerPort){
        this.chatServerIp = chatServerIp;
        this.chatServerPort = chatServerPort;
    }
    @Override
    public boolean connect() {
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap()
                    .group(group)
                    .channel(NioSocketChannel.class)
                    .handler(new SimpleChatClientInitializer());
            Channel channel = bootstrap.connect(chatServerIp, chatServerPort).sync().channel();
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            /*while (true) {
                String line = in.readLine();
                DeviceMsg deviceMsg = JSON.parseObject(line, DeviceMsg.class);
                channel.writeAndFlush(deviceMsg);
            }*/
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            group.shutdownGracefully();
        }
        return false;
    }
}
