import com.sake.dao.IUserDao;
import com.sake.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

public class SecondCacheTest {

    private static final Logger logger = Logger.getLogger(FirstCacheTest.class);

    private InputStream inputStream;
    private SqlSessionFactory factory;


    /**
     * 读取配置文件并初始化
     * @throws IOException
     */
    @Before//用于在测试方法之前执行
    public void init() throws IOException {
        //1.读取字节流，生成字节输入流
        inputStream = Resources.getResourceAsStream("mybatisConfig.xml");
        //2.获取SqlSessionFactory对象
        factory = new SqlSessionFactoryBuilder().build(inputStream);
    }

    /**
     * 释放资源
     * @throws IOException
     */
    @After//用于在测试方法之后执行
    public void destory() throws IOException {

        inputStream.close();
    }

    @Test
    public void secondCache(){
        SqlSession sqlSession1 = factory.openSession();
        IUserDao userDao1 = sqlSession1.getMapper(IUserDao.class);
        User user1 = userDao1.findById(1);
        System.out.println(user1);
        sqlSession1.close();//清除一级缓存

        SqlSession sqlSession2 = factory.openSession();
        IUserDao userDao2 = sqlSession2.getMapper(IUserDao.class);
        User user2 = userDao2.findById(1);
        System.out.println(user2);
        sqlSession2.close();//清除一级缓存

        //false,因为二级缓存中存放的是数据，而不是对象。
        System.out.println(user1==user2);
    }
}
