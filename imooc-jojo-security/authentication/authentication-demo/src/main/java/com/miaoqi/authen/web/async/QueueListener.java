/**
 *
 */
package com.miaoqi.authen.web.async;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * @author zhailiang
 *
 */
@Component
public class QueueListener implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private MockQueue mockQueue;

    @Autowired
    private DeferredResultHolder deferredResultHolder;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        new Thread(() -> {
            while (true) {

                if (StringUtils.isNotBlank(this.mockQueue.getCompleteOrder())) {

                    String orderNumber = this.mockQueue.getCompleteOrder();
                    this.logger.info("返回订单处理结果:" + orderNumber);
                    this.deferredResultHolder.getMap().get(orderNumber).setResult("place order success");
                    this.mockQueue.setCompleteOrder(null);

                } else {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        }).start();
    }

}
