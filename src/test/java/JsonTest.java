import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.junit.Test;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class JsonTest {

    @Test
    public void test1() {

        List<String> data = new ArrayList<String>();
//        data.add("aaa");
//        data.add("bbb");
//        data.add("ccc");
//        data.add("ddd");

        System.out.println(JSON.toJSONString(data));
    }

    @Test
    public void testJSONObject() {
        JSONArray jsonArray = new JSONArray();

        JSONObject jsonObject1 = new JSONObject();
        jsonObject1.put("name", "zy");
        jsonObject1.put("age", 10);
        jsonObject1.put("class", 101);
        jsonArray.add(jsonObject1);

        JSONObject jsonObject2 = new JSONObject();
        jsonObject2.put("name", "qz");
        jsonObject2.put("age", 12);
        jsonObject2.put("class", 102);
        jsonArray.add(jsonObject2);

        System.out.println(jsonArray.toString());
    }

    @Test
    public void testLoadFile() {
        InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("test.json");

        byte[] data = new byte[1024];

        try {
            inputStream.read(data);

            JSONArray jsonArray = (JSONArray) JSON.parse(new String(data));
            System.out.println(jsonArray.toJSONString());

            System.out.println(jsonArray.get(0).toString());

        } catch (Exception e) {
            e.printStackTrace();
        }



    }
}
