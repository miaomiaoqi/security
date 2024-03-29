/**
 *
 */
package com.miaoqi.authen.web.async;

import org.apache.commons.lang.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

/**
 * @author zhailiang
 *
 */
@RestController
public class AsyncController {

    @Autowired
    private MockQueue mockQueue;

    @Autowired
    private DeferredResultHolder deferredResultHolder;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @RequestMapping("/order")
    public DeferredResult<String> order() throws Exception {
        this.logger.info("主线程开始");

        String orderNumber = RandomStringUtils.randomNumeric(8);
        this.mockQueue.setPlaceOrder(orderNumber);

        DeferredResult<String> result = new DeferredResult<>();
        this.deferredResultHolder.getMap().put(orderNumber, result);

        return result;

        //		Callable<String> result = new Callable<String>() {
        //			@Override
        //			public String call() throws Exception {
        //				logger.info("副线程开始");
        //				Thread.sleep(1000);
        //				logger.info("副线程返回");
        //				return "success";
        //			}
        //		};
    }

}
