package testShiro;

import org.apache.shiro.authc.*;
import org.apache.shiro.realm.Realm;

/**
 * Created by ww on 17/3/18.
 */
public class ShiroRealm1 implements Realm {
    @Override
    public String getName() {
        return "realm1";
    }

    @Override
    public boolean supports(AuthenticationToken authenticationToken) {
        return authenticationToken instanceof UsernamePasswordToken;
    }

    @Override
    public AuthenticationInfo getAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        if(authenticationToken.getPrincipal().equals("lisi")){
            return  new SimpleAuthenticationInfo("lisi","abc",getName());
        }
        return null;
    }
}
