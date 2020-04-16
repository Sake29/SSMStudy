package com.sake.factory;

import com.sake.domain.Account;
import com.sake.service.IAccountService;
import com.sake.utils.TransactionManager;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 用于创建service的代理对象的工厂
 */
public class BeanFactory {
    private IAccountService accountService;
    private TransactionManager txManager;

    public void setTxManager(TransactionManager txManager) {
        this.txManager = txManager;
    }


    public final void setAccountService(IAccountService accountService) {
        this.accountService = accountService;
    }

    /**
     * 获取service的代理对象
     * @return
     */
    public IAccountService getAccountService(){
       return  (IAccountService)Proxy.newProxyInstance(accountService.getClass().getClassLoader(), accountService.getClass().getInterfaces(),
                new InvocationHandler() {
                    /**
                     * 添加事务的支持
                     * @param proxy
                     * @param method
                     * @param args
                     * @return
                     * @throws Throwable
                     */
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        System.out.println(method.getName());
                        if ("test".equals(method.getName())){
                            return method.invoke(accountService,args);
                        }

                        Object rtValue;
                        try {
                            //1.开启事务，执行操作
                            txManager.beginTrsaction();
                            //2.执行操作
                            rtValue = method.invoke(accountService,args);
                            //3.提交事务
                            txManager.commit();
                            //4.返回结果
                            return rtValue;
                        }catch (Exception e){
                            //5.回滚操作
                            txManager.rollback();
                            throw new RuntimeException(e.getMessage());
                        }finally {
                            //6.释放连接
                            txManager.release();
                        }
                    }
                });
    }
}
