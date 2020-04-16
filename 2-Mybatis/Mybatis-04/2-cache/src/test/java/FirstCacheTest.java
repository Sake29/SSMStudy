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
import java.util.List;

public class FirstCacheTest {

    private static final Logger logger = Logger.getLogger(FirstCacheTest.class);

    private InputStream inputStream;
    private SqlSessionFactory factory;
    private SqlSession sqlSession;
    private IUserDao userDao;


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
        //3.获取SqlSession对象
        sqlSession = factory.openSession();
        //4.获取dao的代理对象
        userDao = sqlSession.getMapper(IUserDao.class);
    }

    /**
     * 释放资源
     * @throws IOException
     */
    @After//用于在测试方法之后执行
    public void destory() throws IOException {
        sqlSession.commit();
        sqlSession.close();
        inputStream.close();
    }

    @Test
    public void findAll(){
        List<User> users = userDao.findAll();
        for (User user : users) {
            System.out.println(user);
        }
    }

    @Test
    public void firstLevelCache(){
        User user1 = userDao.findById(1);
        System.out.println(user1);

        /*//当sqlSession关闭时，一级缓存也关闭了
        sqlSession.close();
        //再次获取sqlsession对象
        sqlSession = factory.openSession();
        userDao = sqlSession.getMapper(IUserDao.class);*/

        //上面代码等价于下面
        sqlSession.clearCache();

        User user2 = userDao.findById(1);
        System.out.println(user2);

        System.out.println(user1==user2);
    }

    /**
     * 测试缓存的同步
     */
    @Test
    public void clearCache(){
        //1.查询用户信息
        User user1 = userDao.findById(2);
        System.out.println(user1);

        //2.更新用户信息
        user1.setUsername("小小明");
        user1.setSex("男");
        user1.setAddress("地球");
        userDao.updataUser(user1);


        //3.再次查询
        User user2 = userDao.findById(2);
        System.out.println(user2);

        //false,因为sqlSession.commit()也会清除一级缓存
        System.out.println(user1==user2);
    }
}
