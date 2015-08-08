package com.openim.client.connector;

/**
 * Created by shihuacai on 2015/8/6.
 */
public interface IConnector<T> {
    void connect(ConnectorListener<T> listener);

    interface ConnectorListener<T>{
        void finished(int code, T data);
    }
}
