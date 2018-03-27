package leifu.mvpdemo.model.http;

import java.util.HashMap;

import io.reactivex.Flowable;
import leifu.mvpdemo.model.bean.BaseBean;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * 创建人: 雷富
 * 创建时间: 2018/3/21 17:11
 * 描述:
 */

public interface ZhiHuApis {
    //    String HOST = "http://news-at.zhihu.com/api/4/";
//    String HOST = "http://gank.io/api/";
    String HOST = " https://www.ebhtec.com/";


    /**
     * 提交成功
     *
     * @param paramsMap
     * @return
     */
    @FormUrlEncoded
    @POST("test/WebsiteActivity/AddGp")
    Flowable<BaseBean> getBaseBean(@FieldMap HashMap<String, String> paramsMap);
}
