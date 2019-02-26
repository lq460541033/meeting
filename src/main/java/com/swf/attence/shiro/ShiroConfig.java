package com.swf.attence.shiro;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.util.ByteSource;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;
/**
 * @author : white.hou
 * @description : shiro配置类
 * @date: 2018/12/30_20:25
 */
@Configuration
public class ShiroConfig {
    /**
     * 创建ShiroFilterFactoryBean
     */
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("defaultWebSecurityManager") DefaultWebSecurityManager defaultWebSecurityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        /**
         * 关联securityManager
         */
        shiroFilterFactoryBean.setSecurityManager(defaultWebSecurityManager);
        /**
         * 添加Shiro内置过滤器
         * 常用：
         *    anon:无需认证（登录）就能访问
         *    authc:必须认证才能访问
         *    user:使用rememberMe功能可以直接访问
         *    perms:该资源必须得到资源权限才能访问
         *    role:该资源必须得到角色权限才能访问
         */
        Map<String, String> filterMap = new LinkedHashMap<>();
        filterMap.put("/","authc");
        filterMap.put("/camera/*","perms[super_admin]");
        filterMap.put("/stateControl/*","perms[super_admin]");
        filterMap.put("/timeControl/*","perms[super_admin]");
        filterMap.put("/userMsgControl/*","perms[super_admin]");
        filterMap.put("/index.html","perms[super_admin]");
        shiroFilterFactoryBean.setLoginUrl("/tologin");
        shiroFilterFactoryBean.setUnauthorizedUrl("/noAuth");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);
        return shiroFilterFactoryBean;
    }

    /**
     * 创建DefaultWebSecurityManager
     * @param adminRealm
     * @return
     */
    @Bean(name = "defaultWebSecurityManager")
    public DefaultWebSecurityManager defaultWebSecurityManager(@Qualifier("adminRealm") AdminRealm adminRealm){
        DefaultWebSecurityManager defaultWebSecurityManager=new DefaultWebSecurityManager();
        /**
         * 关联Realm
         */
        defaultWebSecurityManager.setRealm(adminRealm);
        return defaultWebSecurityManager;
    }
    /**
     * 创建RootRealm
     * @return
     */
    @Bean(name = "adminRealm")
    public AdminRealm getAdminRealm(){
        AdminRealm adminRealm = new AdminRealm();
        adminRealm.setCredentialsMatcher(hashedCredentialsMatcher());
        return adminRealm;
    }

    /**
     * 密码加密
     * @return
     */
    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher(){
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        hashedCredentialsMatcher.setHashAlgorithmName("md5");
        hashedCredentialsMatcher.setHashIterations(1024);
        return hashedCredentialsMatcher;
    }

    public static void main(String[] args) {
        String an="md5";
        Object aa="admin123";
        SimpleHash simpleHash = new SimpleHash(an, aa,null, 1024);
        System.out.println(simpleHash);
    }

}
