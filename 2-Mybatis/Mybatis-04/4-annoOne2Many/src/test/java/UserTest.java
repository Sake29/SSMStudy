import com.sake.dao.IUserDao;
import com.sake.domain.User;
import com.sake.utils.DateUtils;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

public class UserTest {

    private InputStream inputStream;
    private SqlSessionFactory factory;
    private SqlSession sqlSession;
    private IUserDao userDao;

    @Before
    public void init() throws IOException {
        inputStream = Resources.getResourceAsStream("mybatisConfig.xml");
        factory = new SqlSessionFactoryBuilder().build(inputStream);
        sqlSession = factory.openSession();
        userDao = sqlSession.getMapper(IUserDao.class);
    }

    @After
    public void destroy() throws IOException {
        sqlSession.commit();
        sqlSession.close();
        inputStream.close();
    }

    public void findAll(){
        List<User> users = userDao.findAll();
        for (User user : users) {
            System.out.println("------用户信息------");
            System.out.println(user);
            System.out.println(user.getAccounts());
        }
    }

    @Test
    public void testFindAll(){
        findAll();
    }


    @Test
    public void findUserById(){
        User user = userDao.findUserById(1);
        System.out.println(user);

    }

    @Test
    public void findUserByName(){
        List<User> users = userDao.findUserByName("%s%");
        for (User user : users) {
            System.out.println(user);
        }
    }

    @Test
    public void findTotal(){
        Integer total = userDao.findTotal();
        System.out.println("数据库中总共有"+total.toString()+"条记录");
    }



}
