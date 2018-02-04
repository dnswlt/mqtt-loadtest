package de.reondo.mqtt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.util.Random;
import java.util.UUID;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws Exception {
        ConnectionFactory cf = new ConnectionFactory();
        cf.setHost("192.168.99.11");
        cf.setUsername("perf");
        cf.setPassword("ferp777");
        Connection conn = cf.newConnection();
        Channel chan = conn.createChannel();
        chan.exchangeDeclare("mqtt", "topic");

        Random rnd = new Random();
        ObjectMapper om = new ObjectMapper();
        long seq = 0;
        String clob = getClob();

        System.out.println(om.writeValueAsString(new Message("test-id", 0, "test-clob")));
        while (true) {
            Message msg = new Message(UUID.randomUUID().toString(), ++seq, clob);
            byte[] data = om.writeValueAsBytes(msg);
            chan.basicPublish("mqtt", "perf-" + (1 + rnd.nextInt(10)), null, data);
        }

    }

    private static String getClob() {
        char[] buf = new char[1024];
        Random rnd = new Random();
        for (int i = 0; i < buf.length; i++) {
            buf[i] = (char) ('A' + rnd.nextInt(26));
        }
        String clob = new String(buf);
        System.out.println("CLOB is\n" + clob);
        return clob;
    }
}
