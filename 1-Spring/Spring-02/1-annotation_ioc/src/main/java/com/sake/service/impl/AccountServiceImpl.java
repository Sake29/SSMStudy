package com.sake.service.impl;

import com.sake.dao.IAccountDao;
import com.sake.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import javax.annotation.Resources;

/**
 * 账户的业务层实现类
 * xml的配置：
 *     <bean id="accountService" class="com.sake.service.impl.AccountServiceImpl"
 *           scope="" init-method="" destroy-method="">
 *         <property name="" value="" ref=""/>
 *     </bean>
 * 用于创建对象的
 *      和<bean></bean>标签一样
 *      @Component：
 *          作用：用于把当前类对象存入spring容器中
 *          属性：value,用于指定bean的id，默认值是当前类名且首字母小写
 *      @Controller：表现层
 *      @Service：业务层
 *      @Repository：持久层
 *      以上三个注解的作用和属性与Component一样。
 *      他们三个是spring框架为我们提供明确的三层使用的注解。
 *      使我们的三层对象更加清晰
 * 用于注入数据的
 *      和<bean></bean>标签中的<property></property>标签一样
 *      @Autowired:
 *          作用：自动按照类型注入，只要容器中有唯一的一个bean对象类型和要注入的变量类型匹配，就可以注入成功
 *               如果ioc容器中没有任何bean的类型和要注入的变量类型匹配，则报错
 *               如果ioc容器中有多个类型匹配时，首先匹配数据类型，其次匹配名称，如果都没匹配上，则报错
 *          使用位置：可以是变量上，也可以是方法上。
 *          细节：在使用注解注入时，set方法不是必须的
 *      @Qualifier：
 *          作用：在按照类型注入的基础之上，再按照名称注入。它在给类成员注入时不能单独使用，但是在给方法参数注入时可以
 *          属性：value，用于指定注入bean的id
 *      @Resource():
 *          作用：直接按照bean的id注入，它可以独立使用
 *          属性：name,用于指定注入bean的id
 *      以上三个注解只能注入其他bean类型的数据，基本类型和String类型无法使用
 *      另外，集合类型的注入只能通过xml来实现
 *      @Value():
 *          作用：用于注入基本类型和String类型的数据
 *          属性：value，用于指定数据的值，它可以使用SpEl
 * 用于改变作用范围的
 *      和<bean></bean>标签中的scope一样
 *      @Scope:
 *          作用：用于指定bena的作用范围
 *          属性：value，指定范围的取值 singletone prototype
 * 和生命周期相关的(了解)
 *      和<bean></bean>标签中的init-method destroy-method一样
 *      @PreDestroy
 *          作用：用于指定销毁方法
 *      @PostConstruct
 *          作用：用于指定初始化方法
 */


//@Component(value = "accountService")
@Service("accountService")
@Scope(value = "singleton")
public class AccountServiceImpl implements IAccountService {
    //@Autowired
    //@Qualifier("accountDao1")
    @Resource(name = "accountDao2")
    private IAccountDao accountDao;

    @PostConstruct
    public void init(){
        System.out.println("AccountServiceImpl初始化...");
    }

    @PreDestroy
    public void destory(){
        System.out.println("AccountServiceImpl销毁了...");
    }

    public void saveAccount() {

        accountDao.saveAccount();
    }
}
