package leifu.mvpdemo.model.http;

import io.reactivex.Flowable;
import leifu.mvpdemo.model.bean.DailyListBean;
import retrofit2.http.GET;

/**
 * 创建人: 雷富
 * 创建时间: 2018/3/21 17:11
 * 描述:
 */

public interface ZhiHuApis {
    String HOST = "http://news-at.zhihu.com/api/4/";

    /**
     * 最新日报
     */
    @GET("news/latest")
    Flowable<DailyListBean> getDailyList();
}
