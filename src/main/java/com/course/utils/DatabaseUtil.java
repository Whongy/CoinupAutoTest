package com.course.utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;

/**
 * @Description:
 * @Author KHAN
 * @Date 2022/10/7$ 23:10$
 */

public class DatabaseUtil {
    public static SqlSession getSqlSession() throws IOException {

        Reader reader = Resources.getResourceAsReader("databasesConfig.xml");
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(reader);
        SqlSession sqlSession = factory.openSession();


        return sqlSession;
    }

}
