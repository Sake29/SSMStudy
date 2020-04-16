
import com.sake.dao.IUserDao;
import com.sake.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class MybatisAnnoTest {

    public static void main(String[] args) throws IOException {
        //1.获取字节输入流
        InputStream inputStream = Resources.getResourceAsStream("mybatisConfig.xml");
        //2.根据字节流构建SqlSessionFactory
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(inputStream);
        //3.根据SqlSessionFactory生产SqlSession对象
        SqlSession sqlSession = factory.openSession();
        //4.根据SqlSession获取userDao的代理对象
        IUserDao userDao = sqlSession.getMapper(IUserDao.class);
        //5.执行dao的方法
        List<User> users = userDao.findAll();
        for (User user : users) {
            System.out.println(user);
        }
        //6.释放资源
        sqlSession.close();
        inputStream.close();
    }


}
