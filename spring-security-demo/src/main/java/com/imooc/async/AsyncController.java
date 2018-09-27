package com.imooc.async;

import org.apache.commons.lang.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

@RestController
public class AsyncController {
    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private DeferredResultHolder deferredResultHolder;
    @Autowired
    private MockQueue mockQueue;

    @GetMapping("/order")
    public DeferredResult<String> getOrder(){
        logger.info("主线程开始");
        String ordernum = RandomStringUtils.randomAlphanumeric(8);
        mockQueue.setPlaceOrder(ordernum);

        DeferredResult<String> result = new DeferredResult<>();
        deferredResultHolder.getMap().put(ordernum,result);

        logger.info("主线程结束");
        //开启福线程，主线程可以去干其他的，福线程返回后，再调用主线程
//        Callable<String> result = new Callable<String>() {
//            @Override
//            public String call() throws Exception {
//                logger.info("福线程开始");
//                Thread.sleep(1000);
//                logger.info("福线程返回");
//                return "success";
//            }
//        };

        return result;
    }
}
