import com.sake.dao.IAccountDao;
import com.sake.dao.IUserDao;
import com.sake.domain.Account;
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
import java.util.List;

public class AccountTest {

    private InputStream inputStream;
    private SqlSessionFactory factory;
    private SqlSession sqlSession;
    private IAccountDao accountDao;

    @Before
    public void init() throws IOException {
        inputStream = Resources.getResourceAsStream("mybatisConfig.xml");
        factory = new SqlSessionFactoryBuilder().build(inputStream);
        sqlSession = factory.openSession();
        accountDao = sqlSession.getMapper(IAccountDao.class);
    }

    @After
    public void destroy() throws IOException {
        sqlSession.commit();
        sqlSession.close();
        inputStream.close();
    }

    public void findAll(){
        List<Account> accounts = accountDao.findAll();
        for (Account account : accounts) {
            System.out.println("----每个账户的信息----");
            System.out.println(account);
            System.out.println(account.getUser());
        }
    }

    @Test
    public void testFindAll(){
        findAll();
    }







}
