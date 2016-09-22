package com.atguigu.crm.shiro;

import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import com.atguigu.crm.entity.Authority;
import com.atguigu.crm.entity.Role;
import com.atguigu.crm.entity.User;
import com.atguigu.crm.mapper.UserMapper;

public class MyShiro extends AuthorizingRealm {

	@Autowired
	private UserMapper um;

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection principals) {
		// TODO Auto-generated method stub
		User user = (User) principals.getPrimaryPrincipal();

		Set<String> roles = new HashSet<>();
		for (Authority authority : user.getRole().getAuthorities()) {
			roles.add(authority.getDisplayName());
		}

		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo(roles);
		return info;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken token) throws AuthenticationException {
		// TODO Auto-generated method stub
		UsernamePasswordToken upToken = (UsernamePasswordToken) token;

		String username = upToken.getUsername();
		User user = um.getUserByName(username);
		if (user == null) {
			throw new UnknownAccountException("未知的用户");
		}
		if (user.getEnabled() != 1) {
			throw new DisabledAccountException();
		}

		Object principal = user;
		Object hashedCredentials = user.getPassword();
		ByteSource credentialsSalt = ByteSource.Util.bytes(user.getSalt());
		String realmName = getName();

		SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(principal,
				hashedCredentials, credentialsSalt, realmName);

		return info;
	}

	public static void main(String[] args) {
		String hashAlgorithmName = "MD5";
		Object credentials = "123456";
		ByteSource salt = ByteSource.Util.bytes("e2b87e6eced06509");
		;
		int hashIterations = 1024;
		Object result = new SimpleHash(hashAlgorithmName, credentials, salt,
				hashIterations);
		System.out.println(result);
	}

}
