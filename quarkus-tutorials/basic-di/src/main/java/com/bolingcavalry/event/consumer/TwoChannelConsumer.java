package com.bolingcavalry.event.consumer;

import com.bolingcavalry.annonation.Admin;
import com.bolingcavalry.annonation.Normal;
import com.bolingcavalry.event.bean.MyEvent;
import com.bolingcavalry.event.bean.TwoChannelEvent;
import com.bolingcavalry.event.producer.TwoChannelWithTwoEvent;
import io.quarkus.logging.Log;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.enterprise.event.ObservesAsync;

/**
 * @author will
 * @email zq2599@gmail.com
 * @date 2022/3/29 07:39
 * @description 功能介绍
 */
@ApplicationScoped
public class TwoChannelConsumer {

    /**
     * 消费管理员事件
     * @param event
     */
    public void adminEvent(@Observes @Admin TwoChannelEvent event) {
        Log.infov("receive admin event, {0}", event);
        // 管理员的计数加两次，方便单元测试验证
        event.addNum();
        event.addNum();
    }

    /**
     * 消费普通用户事件
     * @param event
     */
    public void normalEvent(@Observes @Normal TwoChannelEvent event) {
        Log.infov("receive normal event, {0}", event);
        // 计数加一
        event.addNum();
    }

    /**
     * 如果不用注解修饰，所有TwoChannelEvent类型的事件都会在此被消费
     * @param event
     */
    public void allEvent(@Observes TwoChannelEvent event) {
        Log.infov("receive event (no Qualifier), {0}", event);
        // 计数加一
        event.addNum();
    }


}
