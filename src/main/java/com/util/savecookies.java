package com.util;

import org.testng.annotations.Test;

import com.mainData.PageAction.LoginPageAction;
import com.mainData.PageAction.mainDataPageObject;

public class savecookies {
	static LoginPageAction loginPageAction = new LoginPageAction();

	public static void testlogin(String userNamevalue, String passwordvalue, String url) {
	}

	public static void main(String[] args) {
		loginPageAction.getCookie("asu0503217@ykkap.partner.onmschina.cn", "Password001","https://ykksoadev-webdev.chinacloudsites.cn");
	}

}
