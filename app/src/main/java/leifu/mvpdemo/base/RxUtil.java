package leifu.mvpdemo.base;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.FlowableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import leifu.mvpdemo.model.http.MyHttpResponse;
import leifu.mvpdemo.model.bean.GankHttpResponse;
import leifu.mvpdemo.model.exception.ApiException;
import leifu.mvpdemo.utils.JsonUtil;
import leifu.mvpdemo.utils.Logger;

/**
 * Created by codeest on 2016/8/3.
 */
public class RxUtil {

    /**
     * 统一线程处理
     *
     * @param <T>
     * @return
     */
    public static <T> FlowableTransformer<T, T> rxSchedulerHelper() {    //compose简化线程
        return new FlowableTransformer<T, T>() {
            @Override
            public Flowable<T> apply(Flowable<T> observable) {
                return observable.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }

    /**
     * 统一返回结果处理
     *
     * @param <T>
     * @return
     */
    public static <T> FlowableTransformer<MyHttpResponse<T>, T> handleBaseBeanResult() {   //compose判断结果
        return new FlowableTransformer<MyHttpResponse<T>, T>() {
            @Override
            public Flowable<T> apply(Flowable<MyHttpResponse<T>> httpResponseFlowable) {
                return httpResponseFlowable.flatMap(new Function<MyHttpResponse<T>, Flowable<T>>() {
                    @Override
                    public Flowable<T> apply(MyHttpResponse<T> tMyHttpResponse) {
                        Logger.e("OkHttp: 统一返回结果处理 " + JsonUtil.toJson(tMyHttpResponse));
                        if (tMyHttpResponse.getState() == 1) {
                            return createData(tMyHttpResponse.getData());
                        } else {
                            return Flowable.error(new ApiException(tMyHttpResponse.getMsg(), tMyHttpResponse.getState()));
                        }
                    }
                });
            }
        };
    }

    /**
     * 统一返回结果处理
     *
     * @param <T>
     * @return
     */
    public static <T> FlowableTransformer<GankHttpResponse<T>, T> handleResult() {   //compose判断结果
        return new FlowableTransformer<GankHttpResponse<T>, T>() {
            @Override
            public Flowable<T> apply(Flowable<GankHttpResponse<T>> httpResponseFlowable) {
                return httpResponseFlowable.flatMap(new Function<GankHttpResponse<T>, Flowable<T>>() {
                    @Override
                    public Flowable<T> apply(GankHttpResponse<T> tGankHttpResponse) {
                        Logger.e("a---" + JsonUtil.toJson(tGankHttpResponse));
                        if (!tGankHttpResponse.getError()) {
                            return createData(tGankHttpResponse.getResults());
                        } else {
                            return Flowable.error(new ApiException("服务器返回error"));
                        }
                    }
                });
            }
        };
    }

    /**
     * 生成Flowable
     *
     * @param <T>
     * @return
     */
    public static <T> Flowable<T> createData(final T t) {
        return Flowable.create(new FlowableOnSubscribe<T>() {
            @Override
            public void subscribe(FlowableEmitter<T> emitter) throws Exception {
                try {
                    emitter.onNext(t);
                    emitter.onComplete();
                } catch (Exception e) {
                    emitter.onError(e);
                }
            }
        }, BackpressureStrategy.BUFFER);
    }
}
