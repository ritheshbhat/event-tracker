package com.example.utaeventtracker;


import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.widget.Toast;


import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.rabbitmq.client.*;

import java.nio.charset.StandardCharsets;

public class BarcodeReader {
    private static final String CHANNEL_ID = "my_channel";
    public static void waitForMessage(String user_id, Context c) throws Exception {

        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("3.238.155.98");
        factory.setPort(5672);
        factory.setUsername("guest");
        factory.setPassword("guest");
        factory.setVirtualHost("/");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        String consumerTag = "SimpleConsumer";
        channel.exchangeDeclare("public", BuiltinExchangeType.FANOUT);
        channel.exchangeDeclare("user", BuiltinExchangeType.DIRECT);

        AMQP.Queue.DeclareOk queueResult = channel.queueDeclare("", false, true, true, null);
        channel.queueBind(queueResult.getQueue(), "public", "");
        channel.queueDeclare(user_id, false, true, true, null);
        channel.queueBind(user_id, "user", user_id);

        // Create notification channel
        NotificationChannel notificationChannel = new NotificationChannel(CHANNEL_ID, "My Channel", NotificationManager.IMPORTANCE_HIGH);
        NotificationManager notificationManager = (NotificationManager) c.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.createNotificationChannel(notificationChannel);

        // Set up callback to handle incoming messages
        DeliverCallback deliverCallback = (consumerTag1, delivery) -> {
            String message = new String(delivery.getBody(), StandardCharsets.UTF_8);
            String queueName = delivery.getEnvelope().getRoutingKey();
            System.out.println("[" + consumerTag1 + "] Received message from queue '" + queueName + "': '" + message + "'");
            System.out.println("sending notification....");
            sendNotification(message, c, queueName);
        };

        CancelCallback cancelCallback = consumerTag1 -> System.out.println("[" + consumerTag + "] was canceled");
        // Consume messages
        channel.basicConsume(queueResult.getQueue(), false, consumerTag, deliverCallback, cancelCallback);
        channel.basicConsume(user_id, false, "userTag", deliverCallback, cancelCallback);

    }



    private static void sendNotification(String message, Context c,String consumerTag) {
        // Define notification channel
        String channelId = "my_channel";
        String channelName = "My Channel";
        String title;
        if (consumerTag.equals("")) {
            title = "Mav Alert!";
        } else {
            title = "Event Reminder";
        }
        int importance = NotificationManager.IMPORTANCE_HIGH;

        NotificationChannel notificationChannel = new NotificationChannel(channelId, channelName, importance);

        NotificationManager notificationManager = (NotificationManager) c.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.createNotificationChannel(notificationChannel);

        // Generate a unique notification ID based on the current time
        int uniqueId = (int) System.currentTimeMillis();



        // Build the notification
        NotificationCompat.Builder builder = new NotificationCompat.Builder(c, channelId)
                .setSmallIcon(R.drawable.homescreen_button_style)
                .setContentTitle(title)
                .setContentText(message)
                .setAutoCancel(true);

        // Show the notification with a unique ID
        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(c);
        notificationManagerCompat.notify(uniqueId, builder.build());
    }

}
