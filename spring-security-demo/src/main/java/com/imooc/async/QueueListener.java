package com.imooc.async;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class QueueListener implements ApplicationListener<ContextRefreshedEvent> {
    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private DeferredResultHolder deferredResultHolder;
    @Autowired
    private MockQueue mockQueue;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
//        while (true){
//            new Thread(() ->{
//                if(StringUtils.isNoneBlank(mockQueue.getCompleteOrder())){
//                    String ordernumber = mockQueue.getPlaceOrder();
//                    logger.info("返回订单处理结果:"+ordernumber);
//                    deferredResultHolder.getMap().get(ordernumber).setResult("place order success");
//                    mockQueue.setCompleteOrder(null);
//
//                }else {
//                    try {
//                        Thread.sleep(100);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }).start();
//        }


    }
}
