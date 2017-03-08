package TestRocketMQ;

import com.alibaba.rocketmq.client.exception.MQBrokerException;
import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.client.producer.DefaultMQProducer;
import com.alibaba.rocketmq.client.producer.SendResult;
import com.alibaba.rocketmq.common.message.Message;
import com.alibaba.rocketmq.remoting.exception.RemotingException;

/**
 * Created by ww on 16/12/5.
 */
public class TestProvider1 {
    public static void main (String[]args){
        final DefaultMQProducer producer=new DefaultMQProducer("Producer");
        producer.setNamesrvAddr("192.168.0.102:9876");
        try {
            producer.start();

        Message message1 = new Message("topic1", "tags1", "key1", "hello".getBytes());
            SendResult result22=producer.send(message1);
            System.out.println("生产者id"+result22.getMsgId()+result22.getTransactionId()+"生产者发送status"+result22.getSendStatus());

            Message message2 = new Message("topic1", "tags2", "key2", "hello".getBytes());
            SendResult result2=producer.send(message2);
            System.out.println("生产者id2"+result2.getMsgId()+"生产者发送status2"+result2.getSendStatus());

            Message message3 = new Message("topic1", "tags3", "key3", "hello3".getBytes());
            SendResult result3=producer.send(message3);
            System.out.println("生产者id3"+result3.getMsgId()+"生产者发送status"+result3.getSendStatus());
            Message msg = new Message("PushTopic",
                    "push",
                    "1",
                    "Just for test.".getBytes());

            SendResult result = producer.send(msg);
            System.out.println("id:" + result.getMsgId() +
                    " result:" + result.getSendStatus());

            msg = new Message("PushTopic",
                    "push",
                    "2",
                    "Just for test.".getBytes());

            result = producer.send(msg);
            System.out.println("id:" + result.getMsgId() +
                    " result:" + result.getSendStatus());

            msg = new Message("PullTopic",
                    "pull",
                    "1",
                    "Just for test.".getBytes());

            result = producer.send(msg);
            System.out.println("id:" + result.getMsgId() +
                    " result:" + result.getSendStatus());
        } catch (MQClientException e) {
            e.printStackTrace();
        } catch (RemotingException e) {
            e.printStackTrace();
        } catch (MQBrokerException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            /**
             * 结束后,关闭生产者通道,没有用到......
             */
            Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
                public void run() {
                    producer.shutdown();
                }
            }));
        }
    }
}
