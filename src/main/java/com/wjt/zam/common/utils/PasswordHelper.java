package com.wjt.zam.common.utils;

import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

import com.wjt.zam.common.Constant;
import com.wjt.zam.modules.sys.model.User;

/**     
 * @Description:用户密码：输入明文密码得到密文密码  
 * @author: wangjintao 
 * @date:   2018年6月24日      上午11:46:11   
 */  
public class PasswordHelper {

    public synchronized static void encryptPassword(User user) {
        user.setSalt(new SecureRandomNumberGenerator().nextBytes().toHex());
        String newPassword = new SimpleHash(
        		Constant.SHIRO_ALGORITHMNAME,
                user.getPassword(),
                ByteSource.Util.bytes(user.getCredentialsSalt()),
                Constant.SHIRO_HASHITERATIONS).toHex();

        user.setPassword(newPassword);
    }
}
