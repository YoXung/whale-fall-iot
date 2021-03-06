package org.whalefall.iot.terminalsample;

import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

/**
 * Copyright © 2020 Whale Fall All Rights Reserved
 *
 * @author YaoXiang
 * @description
 * @create 2020/3/24 5:01 下午
 */
public class Mqtt4JPublishSample {
    public static void main(String[] args) {
        String topic = "mqtt/iottest";
        String content = "hello 哈哈";
        int qos = 1;
        String broker = "tcp://127.0.0.1:1883";
        String userName = "wfiot";
        String password = "wfiot";
        String clientId = "pubClient";
        // 内存存储
        MemoryPersistence persistence = new MemoryPersistence();

        try {
            // 创建客户端
            MqttClient pubClient = new MqttClient(broker, clientId, persistence);
            //MqttAsyncClient pubClient = new MqttAsyncClient(broker, clientId, persistence);
            // 创建链接参数
            MqttConnectOptions connOpts = new MqttConnectOptions();
            // 在重新启动和重新连接时记住状态
            connOpts.setCleanSession(false);
            // 设置连接的用户名
            connOpts.setUserName(userName);
            connOpts.setPassword(password.toCharArray());
            // 建立连接
            pubClient.connect(connOpts);
            // 创建消息
            MqttMessage message = new MqttMessage(content.getBytes());
            // 设置消息的服务质量
            message.setQos(qos);
            // 发布消息
            pubClient.publish(topic, message);
            // 断开连接
            pubClient.disconnect();
            // 关闭客户端
            pubClient.close();
        } catch (MqttException me) {
            System.out.println("reason " + me.getReasonCode());
            System.out.println("msg " + me.getMessage());
            System.out.println("loc " + me.getLocalizedMessage());
            System.out.println("cause " + me.getCause());
            System.out.println("excep " + me);
            me.printStackTrace();
        }
    }
}
