package com.course.utils;

import com.course.model.InterfaceName;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * @Description:
 * @Author KHAN
 * @Date 2022/10/7$ 23:02$
 */

public class ConfigFile {
    private static ResourceBundle bundle = ResourceBundle.getBundle("application", Locale.CHINA);
    public static String getUrl(InterfaceName name){
        String address = bundle.getString("test.url");
        String uri ="";
        String testUrl;

        if (name == InterfaceName.HISTORYORDERLIST){
            uri=bundle.getString("historyOrderList.uri");
        }
        if (name ==InterfaceName.ORDERCREATE){
            uri = bundle.getString("orderCreate.uri");
        }
        testUrl = address+uri;
        return testUrl;

    }

}
