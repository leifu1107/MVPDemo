package leifu.mvpdemo.model;

import java.util.List;

import io.reactivex.Flowable;
import leifu.mvpdemo.model.bean.GankHttpResponse;
import leifu.mvpdemo.model.bean.GankItemBean;
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

    //    public Flowable<DailyListBean>  fetchDailyListInfo() {
//        return mZhihuApiService.getDailyList();
//    }
    public Flowable<GankHttpResponse<List<GankItemBean>>> getWXHot(String tech, int num, int page) {
        return mZhihuApiService.getTechList(tech, num, page);
    }
}
