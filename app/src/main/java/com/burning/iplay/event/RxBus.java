package com.burning.iplay.event;

import rx.Observable;
import rx.subjects.PublishSubject;
import rx.subjects.SerializedSubject;
import rx.subjects.Subject;

/**
 * @author Kiven
 * @time 2017-2-21  22:47
 * Email f842728368@163.com
 * @desc RxBus的总管理类
 */
public class RxBus {
    private static volatile RxBus mInstance;

    private final Subject bus;

    // Subject同时充当了Observer和Observable的角色，Subject是非线程安全的，
    // 要避免该问题，需要将 Subject转换为一个 SerializedSubject，
    // 上述RxBus类中把线程非安全的PublishSubject包装成线程安全的Subject。

    //PublishSubject只会把在订阅发生的时间点之后来自原始Observable的数据发射给观察者。
    public RxBus() {
        bus = new SerializedSubject<>(PublishSubject.create());
    }

    /**
     * 单例模式RxBus
     *
     * @return
     */
    public static RxBus getInstance() {

        RxBus rxBus2 = mInstance;
        if (mInstance == null) {
            synchronized (RxBus.class) {
                rxBus2 = mInstance;
                if (mInstance == null) {
                    rxBus2 = new RxBus();
                    mInstance = rxBus2;
                }
            }
        }

        return rxBus2;
    }


    /**
     * 发送消息
     *
     * @param object
     */
    public void post(Object object) {

        bus.onNext(object);

    }

    /**
     * 接收消息
     *
     * @param eventType
     * @param <T>
     * @return
     */
    public <T> Observable<T> toObserverable(final Class<T> eventType) {
        // ofType操作符只发射指定类型的数据，其内部就是filter+cast

        //        这里感谢小鄧子的提醒: ofType = filter + cast
        //        return bus.filter(new Func1<Object, Boolean>() {
        //            @Override
        //            public Boolean call(Object o) {
        //                return eventType.isInstance(o);
        //            }
        //        }) .cast(eventType);
        return bus.ofType(eventType);
    }
}
