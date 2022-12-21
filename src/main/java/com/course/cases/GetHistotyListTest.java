package com.course.cases;

import com.course.config.TestConfig;
import com.course.model.HistoryOrderListCase;
import com.course.model.InterfaceName;
import com.course.utils.ConfigFile;
import com.course.utils.DatabaseUtil;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.util.EntityUtils;
import org.apache.ibatis.session.SqlSession;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.JsonPath;




import javax.print.DocFlavor;
import java.io.IOException;
import java.util.*;

/**
 * @Description:
 * @Author KHAN
 * @Date 2022/11/7$ 14:49$
 */

public class GetHistotyListTest {
    @BeforeTest(groups = "getHistoryList")
    public void beforTest(){
        TestConfig.historyCaseUrl= ConfigFile.getUrl(InterfaceName.HISTORYORDERLIST);
        TestConfig.orderCreateUrl=ConfigFile.getUrl(InterfaceName.ORDERCREATE);
        //如使用多线程进行http 请求则 需要new DefaultHttpClient(new ThreadSafeClientConnManager() ；
        TestConfig.defaultHttpClient = new DefaultHttpClient(new ThreadSafeClientConnManager());

    }

    @Test(groups = "getHistoryList")
    public void getHistoryList() throws IOException {

        SqlSession session = DatabaseUtil.getSqlSession();
        HistoryOrderListCase historyOrderListCase = session.selectOne("historyCase",11);

        //
        System.out.println(historyOrderListCase.toString());

        System.out.println(TestConfig.historyCaseUrl);
            JSONArray result = getJsonResult(historyOrderListCase);
        System.out.println("Response: "+result);


        Object code1 = result.get(Integer.parseInt("0"));

        System.out.println("简单处理一下："+code1);

        Object document2 = Configuration.defaultConfiguration().jsonProvider().parse(code1.toString());
        String code =JsonPath.read(document2,"$.code");

          //List<Object> arr2 = JsonPath.read(document2,"$.data.orderList[*].['symbol','pricePrecision','side','positionType','tradeFee','volume','realizedAmount']");

//        System.out.println("code: "+code );
//        System.out.println("orderDetal:"+arr2.toString());


        Object actualValue = historyOrderListCase.getExpected();
        Assert.assertEquals(code,actualValue);



    }
    private  JSONArray getJsonResult(HistoryOrderListCase historyOrderListCase) throws IOException {

        HttpPost post = new HttpPost(TestConfig.historyCaseUrl);
        JSONObject param = new JSONObject();
        param.put("contractId",historyOrderListCase.getContractId());

        post.setHeader("content-type","application/json");
        post.setHeader("exchange-token", "6ce054e1d64dbe61c36a11d85f3ee1af3ed8499ce7314b7d90a42736a10d5f64");

        StringEntity entity = new StringEntity(param.toString(),"utf-8");
        post.setEntity(entity);

        TestConfig.defaultHttpClient.setCookieStore(TestConfig.store);


        String result;
        HttpResponse response =TestConfig.defaultHttpClient.execute(post);


        result = EntityUtils.toString(response.getEntity(),"utf-8");
        List resultList = Arrays.asList(result);
        JSONArray array = new JSONArray(resultList);

        return array;

    }
}
