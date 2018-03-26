package leifu.mvpdemo.model.http;

import java.util.List;

import io.reactivex.Flowable;
import leifu.mvpdemo.model.bean.GankHttpResponse;
import leifu.mvpdemo.model.bean.GankItemBean;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * 创建人: 雷富
 * 创建时间: 2018/3/21 17:11
 * 描述:
 */

public interface ZhiHuApis {
//    String HOST = "http://news-at.zhihu.com/api/4/";
String HOST = "http://gank.io/api/";
//    /**
//     * 最新日报
//     */
//    @GET("news/latest")
//    Flowable<DailyListBean> getDailyList();

//    /**
//     * 微信精选列表
//     */
//    @GET("wxnew")
//    Flowable<WXHttpResponse<List<GankItemBean>>> getWXHot(@Query("key") String key, @Query("num") int num, @Query("page") int page);

    /**
     * 技术文章列表
     */
    @GET("data/{tech}/{num}/{page}")
    Flowable<GankHttpResponse<List<GankItemBean>>> getTechList(@Path("tech") String tech, @Path("num") int num, @Path("page") int page);
}
