package leifu.mvpdemo;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

/**
 * 创建人: 雷富
 * 创建时间: 2018/3/14 15:20
 * 描述:
 */

public abstract class ApiSubscriberCallBack<T> implements Subscriber<T> {

    @Override
    public void onSubscribe(Subscription s) {
        s.request(Long.MAX_VALUE);
    }

    @Override
    public void onNext(T t) {
        onSuccess(t);
    }


    @Override
    public void onError(Throwable t) {
        onFailure(t);

    }


    @Override
    public void onComplete() {

    }

    public abstract void onSuccess(T t);

    public abstract void onFailure(Throwable t);
}
