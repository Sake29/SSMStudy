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

public class AnnotationCRUDTest {

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
            System.out.println(user);
        }
    }

    @Test
    public void testFindAll(){
        findAll();
    }

    @Test
    public void save(){
        User user = new User();
        Date date = DateUtils.getDate(1999,2,1);

        user.setId(15);
        user.setUsername("黄黄");
        user.setSex("女");
        user.setAddress("湖北省武汉市");
        user.setBirthday(date);

        userDao.saveUser(user);
    }

    @Test
    public void update(){
        findAll();

        User user = new User();
        Date date = DateUtils.getDate(1999,2,2,3,4,5);

        user.setId(16);
        user.setUsername("小黄黄");
        user.setSex("男");
        user.setBirthday(date);

        userDao.updateUser(user);
        findAll();
    }

    @Test
    public void delete(){
        findAll();
        userDao.deleteUser(16);
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
