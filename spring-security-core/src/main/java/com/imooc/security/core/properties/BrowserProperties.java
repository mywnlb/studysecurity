/**
 * 
 */
package com.imooc.security.core.properties;


public class BrowserProperties {

	/**
	 * 默认的登录页面
	 */
	private String signUpUrl = "/imooc-signIn.html";

	/**
	 * 登录响应的方式，默认是json
	 */
	private LoginResponseType loginResponseType = LoginResponseType.JSON;

	/**
	 * 记住我的秒数
	 */
	private int rembermeSeconds = 36000;

	public int getRembermeSeconds() {
		return rembermeSeconds;
	}

	public void setRembermeSeconds(int rembermeSeconds) {
		this.rembermeSeconds = rembermeSeconds;
	}

	public LoginResponseType getLoginResponseType() {
		return loginResponseType;
	}

	public void setLoginResponseType(LoginResponseType loginResponseType) {
		this.loginResponseType = loginResponseType;
	}

	public String getSignUpUrl() {
		return signUpUrl;
	}

	public void setSignUpUrl(String signUpUrl) {
		this.signUpUrl = signUpUrl;
	}
}
