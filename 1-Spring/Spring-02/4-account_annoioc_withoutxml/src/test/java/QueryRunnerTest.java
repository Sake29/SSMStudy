import com.sake.config.SpringConfiguration;
import org.apache.commons.dbutils.QueryRunner;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 测试queryrunner是否单例
 *
 */
public class QueryRunnerTest {

    @Test
    public void test(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(SpringConfiguration.class);
        QueryRunner runner = (QueryRunner) ac.getBean("runner");
        QueryRunner runner1 = (QueryRunner) ac.getBean("runner");

        System.out.println(runner==runner1);

    }
}
