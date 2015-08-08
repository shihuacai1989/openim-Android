package com.openim.client.connector;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.openim.common.bean.ResultCode;

import org.json.JSONObject;

/**
 * Created by shihuacai on 2015/8/6.
 */
public class EsbConnector implements IConnector<String> {
    private Context context;
    private String url;

    public EsbConnector(Context context, String url) {
        this.context = context;
        this.url = url;
    }

    @Override
    public void connect(ConnectorListener<String> listener) {
        final ConnectorListener connectorListener = listener;
        RequestQueue queue = Volley.newRequestQueue(context);

        JsonObjectRequest objRequest = new JsonObjectRequest(url, null,
            new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject obj) {
                    try{
                        int code = obj.getInt("code");
                        String chatServerData = obj.getString("data");

                        connectorListener.finished(code, chatServerData);
                        System.out.println("----------:" + obj);
                    }catch (Exception e){
                        connectorListener.finished(ResultCode.error, e.toString());
                    }

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    connectorListener.finished(ResultCode.error, error.getMessage());
                }

            }
        );
        objRequest.setTag("obj");
        queue.add(objRequest);
        //return false;
    }
}
