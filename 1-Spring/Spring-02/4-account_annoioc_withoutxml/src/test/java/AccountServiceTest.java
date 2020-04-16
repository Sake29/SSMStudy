import com.sake.config.SpringConfiguration;
import com.sake.domain.Account;
import com.sake.service.IAccountService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * 在maven中引入spring-test的jar包
 * @RunWith：替换junit的执行器为springjunit的执行器
 * @ContextConfiguration：
 *      参数：classes：配置类的字节码
 *           locations:xml配置文件的位置
 * 使用上面两个注解后，就可以自动注入所需要的类了，
 * 不用自己去手动的获取对象，避免了代码的冗余
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringConfiguration.class )
public class AccountServiceTest {

    @Autowired
    private IAccountService as;

    @Test
    public void testFindAll(){
        List<Account> accounts = as.findAll();
        for (Account account : accounts) {
            System.out.println(account);
        }
    }

    @Test
    public void testFindOne(){
        Account account = as.findAccountById(1);
        System.out.println(account);

    }

    @Test
    public void testSave(){
        Account account = new Account();
        account.setName("蜀");
        account.setMoney(11232.3F);

        as.saveAccount(account);

    }

    @Test
    public void testUpdate(){
        Account account = as.findAccountById(4);
        account.setName("王王.");
        as.updateAccount(account);
    }

    @Test
    public void testDelete(){
        as.deleteAccount(4);
    }

}