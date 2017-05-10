package com.jason.inews.News.rxbus;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;

/**
 * Created by distancelin on 2017/4/27.
 * 采用rxJava实现的订阅发布事件
 */

public class RxBus {
    private final Subject<Object> mBus = PublishSubject.create().toSerialized();

    private RxBus() {
    }

    public static RxBus getInstance() {
        return InnerClass.instance;
    }

    public void post(Object o) {
        mBus.onNext(o);
    }

    public <T> Observable<T> toObservable(Class<T> tClass) {
        return mBus.ofType(tClass);
    }

    private static class InnerClass {
        //静态变量只会初始化一次，在类加载的时候
        static final RxBus instance = new RxBus();
    }
}
