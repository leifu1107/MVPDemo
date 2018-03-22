package leifu.mvpdemo;


import io.reactivex.Flowable;
import retrofit2.http.GET;

/**
 * 创建人: 雷富
 * 创建时间: 2018/3/13 16:17
 * 描述:
 */

public interface RequestServices {
    @GET("basil2style")
    Flowable<UseBean> getString();
}
