package com.lambkit.plugin.jwt;

/**
 * FOR : 配置项目产生的异常
 */
public class TokenConfigException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8229513026160935948L;

	private static String messagePrefix = "Token 请求中的 对应的 ";

    private static String messageEnd = "不可以为 ";

    /**
     * 组合异常产生原因
     *
     * @param keyWord
     * @param status
     */
    public TokenConfigException(String keyWord, String status) {
        super(messagePrefix + keyWord + messageEnd + status);
    }
}
