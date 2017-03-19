package testShiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by ww on 17/3/18.
 */
public class TestShiro {
    @Test
    public void testSimpleByIni(){
        IniSecurityManagerFactory iniSecurityManagerFactory = new IniSecurityManagerFactory("testShiro.ini");
        SecurityManager manager = iniSecurityManagerFactory.createInstance();
        SecurityUtils.setSecurityManager(manager);
        Subject subject = SecurityUtils.getSubject();
        AuthenticationToken token=new UsernamePasswordToken("lisi","456");
//        System.out.println("result>>>>>"+(char[])token.getCredentials()+(String)token.getPrincipal());;
        subject.login(token);
        Assert.assertEquals(true,subject.isAuthenticated());

    }
}
