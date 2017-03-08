package TestRocketMQ;

import com.alibaba.rocketmq.client.consumer.DefaultMQPullConsumer;
import com.alibaba.rocketmq.client.consumer.DefaultMQPushConsumer;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import com.alibaba.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.common.consumer.ConsumeFromWhere;
import com.alibaba.rocketmq.common.message.Message;
import com.alibaba.rocketmq.common.message.MessageExt;

import java.util.List;

/**
 * Created by ww on 16/12/5.
 */
public class TestConsumer1 {
    public static void main(String[]args){
        DefaultMQPushConsumer consumer=new DefaultMQPushConsumer("PushConsumer");
        consumer.setNamesrvAddr("192.168.0.102:9876");
        try {
            consumer.subscribe("PushTopic","push");
        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
        consumer.registerMessageListener(new MessageListenerConcurrently() {
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> list, ConsumeConcurrentlyContext consumeConcurrentlyContext) {
                for (Message msg:list) {
                    System.out.println("消费者消息体"+msg.getBody()+"消费者tags"+msg.getTags()+"消费者topic"+msg.getTopic());
                    System.out.println(msg.toString());
                }
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });
            consumer.start();
        } catch (MQClientException e) {
            e.printStackTrace();
        }

        DefaultMQPullConsumer pullConsumer=new DefaultMQPullConsumer("PullConsumer");
        pullConsumer.setNamesrvAddr("192.168.0.102:9876");
    }
}
