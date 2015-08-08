package com.openim.client.connector;

import android.content.Context;

import com.openim.client.listener.IMessageListener;
import com.openim.common.im.bean.ProtobufDeviceMsg;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;

/**
 * Created by shihuacai on 2015/8/6.
 */
public class ChatServerConnector implements IConnector {
    private Context context;
    private String chatServerIp;
    private int chatServerPort;

    private IMessageListener messageListener;

    public ChatServerConnector(Context context, String chatServerIp, int chatServerPort){
        this.context = context;
        this.chatServerIp = chatServerIp;
        this.chatServerPort = chatServerPort;
    }

    public void registerMessageListener(IMessageListener messageListener){
        this.messageListener = messageListener;
    }

    @Override
    public void connect(ConnectorListener listener) {
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap()
                    .group(group)
                    .channel(NioSocketChannel.class)
                    .handler(new ProtobufChatClientInitializer());
            bootstrap.connect(chatServerIp, chatServerPort).sync();
            //BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            /*while (true) {
                String line = in.readLine();
                DeviceMsg deviceMsg = JSON.parseObject(line, DeviceMsg.class);
                channel.writeAndFlush(deviceMsg);
            }*/
        } catch (Exception e) {
            e.printStackTrace();
            //listener
        } finally {
            group.shutdownGracefully();
        }
        //return false;
    }


    class ProtobufChatClientInitializer extends ChannelInitializer<SocketChannel> {

        @Override
        public void initChannel(SocketChannel ch) throws Exception {
            ChannelPipeline pipeline = ch.pipeline();

            ch.pipeline().addLast("encoder", new ProtobufEncoder());

            ch.pipeline().addLast("decoder", new ProtobufDecoder(ProtobufDeviceMsg.DeviceMsg.getDefaultInstance()));

            ch.pipeline().addLast("handler", new ProtobufChatClientHandler(messageListener));

        }
    }

    class ProtobufChatClientHandler extends SimpleChannelInboundHandler<ProtobufDeviceMsg.DeviceMsg> {
        private IMessageListener messageListener;

        public ProtobufChatClientHandler(IMessageListener messageListener){
            this.messageListener = messageListener;
        }

        @Override
        protected void channelRead0(ChannelHandlerContext ctx, ProtobufDeviceMsg.DeviceMsg deviceMsg) throws Exception {
            messageListener.onReceive(deviceMsg);
        }
    }
}
