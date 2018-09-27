/**
 * 
 */
package com.imooc.security.core.properties;

/**
 * 浏览器环境配置项
 * 
 * @author zhailiang
 *
 */
public class BrowserProperties {

	/**
	 * 社交登录，如果需要用户注册，跳转的页面
	 */
	private String signUpUrl = "/imooc-signUp.html";

	public String getSignUpUrl() {
		return signUpUrl;
	}

	public void setSignUpUrl(String signUpUrl) {
		this.signUpUrl = signUpUrl;
	}
}
