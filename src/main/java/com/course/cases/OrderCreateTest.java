package com.course.cases;

import com.course.config.TestConfig;
import com.course.model.OrderCreateCase;
import com.course.utils.DatabaseUtil;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import org.apache.ibatis.session.SqlSession;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

/**
 * @Description:
 * @Author KHAN
 * @Date 2022/11/8$ 16:03$
 */

public class OrderCreateTest {
    @Test(dependsOnGroups = "getHistoryList",threadPoolSize = 6,invocationCount = 6,enabled=false)
    public void orderCreate() throws IOException {
        SqlSession session = DatabaseUtil.getSqlSession();
        OrderCreateCase orderCreateCase = session.selectOne("orderCase",11);
        System.out.println(orderCreateCase.toString());
        System.out.println(TestConfig.orderCreateUrl);
        //发请求 获取结果
        String result = getResult(orderCreateCase);
        System.out.println("Respongse:"+result);
        // 验证返回结果
       //Object  orderOpen =orderCreateCase.getExpected();



    }



    private String getResult(OrderCreateCase orderCreateCase) throws IOException {

        HttpPost post =new HttpPost(TestConfig.orderCreateUrl);
        JSONObject param =  new JSONObject();
        param.put("contractId",orderCreateCase.getContractId());
        param.put("isConditionOrder",orderCreateCase.getIsConditionOrder());
        param.put("leverageLevel",orderCreateCase.getLeverageLevel());
        param.put("open",orderCreateCase.getOpen());
        param.put("positionType",orderCreateCase.getPositionType());
        param.put("price",orderCreateCase.getPrice());
        //param.put("securityInfo","{\"id\":\"\",\"org\":\"futures.coin-up.tech\",\"timestamp\":\"2022-11-08 19:10:41\",\"userAgent\":\"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/107.0.0.0 Safari/537.36\",\"availableScreenResolution\":\"1707;1027\",\"screenResolution\":\"1707;1067\",\"touchSupport\":\"0;false;false\",\"sessionStorage\":1,\"localStorage\":1,\"indexedDb\":1,\"plugins\":\"PDF Viewer::Portable Document Format::application/pdf~pdf,text/pdf~pdf,Chrome PDF Viewer::Portable Document Format::application/pdf~pdf,text/pdf~pdf,Chromium PDF Viewer::Portable Document Format::application/pdf~pdf,text/pdf~pdf,Microsoft Edge PDF Viewer::Portable Document Format::application/pdf~pdf,text/pdf~pdf,WebKit built-in PDF::Portable Document Format::application/pdf~pdf,text/pdf~pdf\",\"fonts\":\"Arial;Arial Black;Arial Narrow;Book Antiqua;Bookman Old Style;Calibri;Cambria;Cambria Math;Century;Century Gothic;Century Schoolbook;Comic Sans MS;Consolas;Courier;Courier New;Georgia;Helvetica;Impact;Lucida Bright;Lucida Calligraphy;Lucida Console;Lucida Fax;Lucida Handwriting;Lucida Sans;Lucida Sans Typewriter;Lucida Sans Unicode;Microsoft Sans Serif;Monotype Corsiva;MS Gothic;MS PGothic;MS Reference Sans Serif;MS Sans Serif;MS Serif;Palatino Linotype;Segoe Print;Segoe Script;Segoe UI;Segoe UI Light;Segoe UI Semibold;Segoe UI Symbol;Tahoma;Times;Times New Roman;Trebuchet MS;Verdana;Wingdings;Wingdings 2;Wingdings 3\",\"client_type\":\"\",\"identity\":\"\",\"device\":\"d7c72d7fc308ed5cb70448dc88eb1f77\",\"ip\":\"\",\"ip2region\":\"\",\"language\":\"zh-CN\",\"platform\":\"Win32\",\"browser\":\"\",\"browser_version\":\"\",\"os\":\"\",\"os_version\":\"\",\"resolution\":\"\",\"timezone\":\"Asia/Shanghai\",\"ctime\":\"\",\"mtime\":\"\"}");

        param.put("side",orderCreateCase.getSide());


        param.put("volume",orderCreateCase.getVolume());
        param.put("type",orderCreateCase.getType());
        // set header info

        post.setHeader("content-type","application/json");
        post.setHeader("exchange-token", "6ce054e1d64dbe61c36a11d85f3ee1af3ed8499ce7314b7d90a42736a10d5f64");
        StringEntity entity = new StringEntity(param.toString(),"utf-8");
        post.setEntity(entity);

        // set cookies

        TestConfig.defaultHttpClient.setCookieStore(TestConfig.store);

        String result;
        HttpResponse response =TestConfig.defaultHttpClient.execute(post);
        result = EntityUtils.toString(response.getEntity(),"utf-8");

        System.out.println(result);



        return result;
    }
}
