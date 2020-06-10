package com.zzt.rxjavasamples;

/**
 * @author: zeting
 * @date: 2019/12/27
 * 消息执行完成，需要告诉队列执行下一个
 */
public interface MyQueueCallback {
    void callQueue(boolean goOn);
}
