package testJson;

import org.json.JSONObject;
import org.junit.Test;

import java.util.Iterator;

/**
 * Created by ww on 17/3/22.
 */
public class TestJson {
    @Test
    public void test(){
        String json="{\"accountName\":\"542521\",\"department\":\"\",\"updateDate\":\"2017/3/22 10:36:10\"}";
//        String json = "{'status':'200','message':'','name':''}";
        JSONObject data = new JSONObject(json);
        JSONObject newdata = new JSONObject();
        Iterator<String> keys = data.keys();
        while (keys.hasNext()){
            String key=keys.next();
            String value= (String) data.get(key);
            newdata.put(key,value);
        }
        System.out.println(newdata.get("accountName")+"message");
        System.out.println(newdata.get("department"));
        System.out.println(newdata.get("UpdateDate")+"name");

    }
}
