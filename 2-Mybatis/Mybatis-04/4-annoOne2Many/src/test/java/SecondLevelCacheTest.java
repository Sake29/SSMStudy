import com.sake.dao.IUserDao;
import com.sake.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

public class SecondLevelCacheTest {

    private InputStream inputStream;
    private SqlSessionFactory factory;


    @Before
    public void init() throws IOException {
        inputStream = Resources.getResourceAsStream("mybatisConfig.xml");
        factory = new SqlSessionFactoryBuilder().build(inputStream);
    }

    @After
    public void destroy() throws IOException {
        inputStream.close();
    }

    @Test
    public void findUserById(){
        SqlSession sqlSession1 = factory.openSession();
        IUserDao userDao1 = sqlSession1.getMapper(IUserDao.class);
        User user1 = userDao1.findUserById(1);
        //System.out.println(user);

        sqlSession1.close();//释放一级缓存

        SqlSession sqlSession2 = factory.openSession();//再次打开session
        IUserDao userDao2 = sqlSession2.getMapper(IUserDao.class);
        User user2 = userDao2.findUserById(1);

        System.out.println(user1==user2);


    }
}
