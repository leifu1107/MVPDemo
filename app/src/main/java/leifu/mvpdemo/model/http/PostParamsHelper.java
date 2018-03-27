package leifu.mvpdemo.model.http;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import leifu.mvpdemo.utils.Logger;

/**
 * 创建人: 雷富
 * 创建时间: 2018/3/27 15:46
 * 描述:
 */

public class PostParamsHelper {
    public static HashMap<String, String> putParams(HashMap<String, String> hashMap) {
//        long timestamp = DateUtils.getNowTime();
//        hashMap.put("headTrTime", timestamp + "");

        printMap(hashMap);
        return hashMap;
    }

    private static void printMap(HashMap<String, String> hashMap) {
        Set set = hashMap.entrySet();
        Iterator iterator = set.iterator();
        while (iterator.hasNext()) {
            Map.Entry mapentry = (Map.Entry) iterator.next();
            Logger.e("OkHttp:  " + mapentry.getKey() + "   " + mapentry.getValue());
        }
    }

}
