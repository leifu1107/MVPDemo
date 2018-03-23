package leifu.mvpdemo.model;

import io.reactivex.Flowable;
import leifu.mvpdemo.model.bean.DailyListBean;
import leifu.mvpdemo.model.http.ZhiHuApis;

/**
 * 创建人: 雷富
 * 创建时间: 2018/3/22 11:14
 * 描述:
 */

public class RetrofitHelper {
    private ZhiHuApis mZhihuApiService;

    public RetrofitHelper(ZhiHuApis mZhihuApiService) {
        this.mZhihuApiService = mZhihuApiService;
    }

    public Flowable<DailyListBean>  fetchDailyListInfo() {
        return mZhihuApiService.getDailyList();
    }
}
