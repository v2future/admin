package com.hollycrm.mi.core.shiro;

import com.hollycrm.mi.core.entity.SysUser;
import com.hollycrm.mi.core.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;

public class UserRealm extends AuthorizingRealm {

    @Autowired
    UserService userService;

    /**
     * 授权
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SysUser sysUser =  (SysUser)principals.getPrimaryPrincipal();
        if ( sysUser == null)
            return null;
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        Set<String> set = null;
        /*
        if(StringUtils.isEmpty(PropertiesUtils
                    .getProperty("init.storage.redis"))
                    || PropertiesUtils.getProperty("init.storage.redis")
                    .equalsIgnoreCase("true")){
                sysUserCacheHandler.initAddUser(sysUser);
                info.setRoles(sysUserCacheHandler.getRoleByUser(sysUser.getId()));
                info.setStringPermissions(sysUserCacheHandler.findPermissions(sysUser));
                set = sysUserCacheHandler.getRestIfsUrlByUser(sysUser);
        }else{
                info.setRoles(roleService.getRoleByUser(sysUser.getId()));
                info.setStringPermissions(userService.findPermissions(sysUser));
                set = restIfsService.getRestIfsUrlByUser(sysUser);
        }
        */
        if(set != null){
            info.addStringPermissions(set);
        }
        return info;
    }

    /**
     * 认证
     * @param authcToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
        SysUser sysUser = userService.get( token.getUsername());
        if ( sysUser == null) {
            return null;
        }
        ByteSource salt = ByteSource.Util.bytes( "hollycrm");
        SimpleAuthenticationInfo authInfo = new SimpleAuthenticationInfo(sysUser, sysUser.getPassword(), salt, getName());
        return authInfo;
    }
}
