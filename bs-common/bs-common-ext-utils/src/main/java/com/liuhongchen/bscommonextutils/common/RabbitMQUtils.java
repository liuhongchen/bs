package com.liuhongchen.bscommonextutils.common;

import com.liuhongchen.bscommonutils.common.Constants;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Administrator on 2018-5-13.
 */
@Configuration
public class RabbitMQUtils {
    @Bean
    public Queue toQgQueue() {
        return new Queue(Constants.RabbitQueueName.TO_QG_QUEUE, true);
    }

    @Bean
    public Queue toCreateOrderQueue() {
        return new Queue(Constants.RabbitQueueName.TO_CREATE_ORDER, true);
    }

    @Bean
    public Queue toUpdateOrderQueue() {
        return new Queue(Constants.RabbitQueueName.TO_UPDATED_ORDER_QUEUE, true);
    }

    @Bean
    public Queue toUpdateGoodsQueue() {
        return new Queue(Constants.RabbitQueueName.TO_UPDATED_GOODS_QUQUE, true);
    }

    /**
     * 重置座位状态
     *
     * @return
     */
    @Bean
    public Queue toResetSeatQueue() {
        return new Queue(Constants.RabbitQueueName.TO_RESET_SEAT_QUQUE, true);
    }

    /**
     * 删除订单
     *
     * @return
     */
    @Bean
    public Queue toDelOrderQueue() {
        return new Queue(Constants.RabbitQueueName.TO_DEL_ORDER_QUQUE, true);
    }

    /**
     * 重置联系人
     *
     * @return
     */
    @Bean
    public Queue toResetLinkUserQueue() {
        return new Queue(Constants.RabbitQueueName.TO_RESET_LINKUSER_QUQUE, true);
    }

    @Bean
    TopicExchange topicExchange() {
        return new TopicExchange(Constants.RabbitQueueName.TOPIC_EXCHANGE);
    }

    @Bean
    public Binding bindingToQg() {
        return BindingBuilder.bind(toQgQueue()).to(topicExchange()).with("key.toQg");
    }

    @Bean
    public Binding bindingToCreateOrder() {
        return BindingBuilder.bind(toCreateOrderQueue()).to(topicExchange()).with("key.toCreateOrder");
    }

    @Bean
    public Binding bindingToUpdateOrder() {
        return BindingBuilder.bind(toUpdateOrderQueue()).to(topicExchange()).with("key.toUpdateOrder");
    }

    @Bean
    public Binding bindingToUpdateGoods() {
        return BindingBuilder.bind(toUpdateGoodsQueue()).to(topicExchange()).with("key.toUpdateGoods");
    }

    @Bean
    public Binding bindingToResetSeat() {
        return BindingBuilder.bind(toResetSeatQueue()).to(topicExchange()).with(Constants.RabbitQueueName.TO_RESET_SEAT_QUQUE);
    }

    @Bean
    public Binding bindingToDelOrder() {
        return BindingBuilder.bind(toDelOrderQueue()).to(topicExchange()).with(Constants.RabbitQueueName.TO_DEL_ORDER_QUQUE);
    }

    @Bean
    public Binding bindingToResetLinkUser() {
        return BindingBuilder.bind(toResetLinkUserQueue()).to(topicExchange()).with(Constants.RabbitQueueName.TO_RESET_LINKUSER_QUQUE);
    }
}
