package leifu.mvpdemo.model.http;

import java.util.HashMap;

import io.reactivex.Flowable;
import leifu.mvpdemo.model.bean.BaseBean;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

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

    //    /**
//     * 上传图片
//     *
//     * @param
//     * @return
//     */
//    @Multipart
//    @POST("file/upload")
//    Observable<UploadBean> uploadImage(@Part("fileName") String imgfile,
//                                       @Part("imgfile\"; filename=\"imgfile.png\"") RequestBody path,
//                                       @Part("headTrTime") RequestBody headTrTime,
//                                       @Part("headSerialNo") RequestBody headSerialNo,
//                                       @Part("headChannel") RequestBody headChannel,
//                                       @Part("headAuthToken") RequestBody headAuthToken,
//                                       @Part("headUserId") RequestBody headUserId);
//
//    /**
//     * 上传图片
//     *
//     * @param
//     * @return
//     */
//    @Multipart
//    @POST("file/upload")
//    Observable<UploadBean> uploadImage(@Part("imgfile\"; filename=\"imgfile.png\"") RequestBody path,
//                                       @PartMap Map<String, RequestBody> map);
    @POST("test/metting/upload")
    @Multipart
    Flowable<BaseBean> getUpLoad(@Part("meeting_id") RequestBody meeting_id, @Part MultipartBody.Part file);

}
