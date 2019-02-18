package com.lambkit.node.test;

import com.jfinal.kit.StrKit;
import com.lambkit.distributed.token.TokenBuilder;

public class TestToken {

	public static void main(String[] args) {
		String token = TokenBuilder.createToken();
		String nodeid = StrKit.getRandomUUID();
		System.out.println("info: " + nodeid);
		//String userToken = nodeid + "," + token;
		System.out.println("token: " + token);
		String userTokens = TokenBuilder.encodeToken(nodeid, token);
		System.out.println("token: " + userTokens);
		String deTokens = TokenBuilder.decodeToken(userTokens, "7719440191621240800");
		System.out.println("token: " + deTokens);
		
	}
}
