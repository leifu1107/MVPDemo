package leifu.mvpdemo.model.http;

import java.util.HashMap;

import io.reactivex.Flowable;
import leifu.mvpdemo.model.bean.BaseBean;

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


    public Flowable<BaseBean> getBaseBean(HashMap<String, String> paramsMap) {
        return mZhihuApiService.getBaseBean(paramsMap);
    }

}
