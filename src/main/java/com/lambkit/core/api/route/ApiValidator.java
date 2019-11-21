package com.lambkit.core.api.route;

import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.jfinal.kit.Kv;
import com.jfinal.kit.LogKit;
import com.jfinal.kit.Ret;
import com.jfinal.kit.StrKit;
import com.jfinal.validate.ValidateException;

public abstract class ApiValidator implements ApiInterceptor {

	protected ApiInvocation invocation;
	protected Kv paras;
	protected boolean shortCircuit = false;
	protected boolean invalid = false;
	protected String datePattern = null;
	
	// TODO set the DEFAULT_DATE_PATTERN in Const and config it in Constants. TypeConverter do the same thing.
	protected static final String DEFAULT_DATE_PATTERN = "yyyy-MM-dd";
	protected static final String emailAddressPattern = "\\b(^['_A-Za-z0-9-]+(\\.['_A-Za-z0-9-]+)*@([A-Za-z0-9-])+(\\.[A-Za-z0-9-]+)*((\\.[A-Za-z0-9]{2,})|(\\.[A-Za-z0-9]{2,}\\.[A-Za-z0-9]{2,}))$)\\b";
	
	protected Ret ret = null;
	
	/**
	 * Add message when validate failure.
	 */
	protected void addError(String errorKey, String errorMessage) {
		invalid = true;
		
		if (ret != null) {
			ret.set(errorKey, errorMessage);
		}
		
		if (shortCircuit) {
			throw new ValidateException();
		}
	}
	
	/**
	 * 注入 Ret 对象，验证结果将被存放在其中，以便在 handleError 中使用 getRet()：
	 *     controller.renderJson(getRet());
	 * 
	 * <pre>
	 * 用法：
	 * validate(Controller c) 中调用 setRet(Ret.fail());
	 * handleError(Controller c) 中调用 c.renderJson(getRet());
	 * </pre>
	 */
	protected void setRet(Ret ret) {
		Objects.requireNonNull(ret, "ret can not be null");
		this.ret = ret;
	}
	
	/**
	 * 便于在 handleError 中使用 controller.renderJson(getRet());
	 */
	protected Ret getRet() {
		if (ret == null) {
			throw new IllegalStateException("You should invoke setRet(Ret.fail()) method in validate(Controller c) first");
		}
		return ret;
	}
	
	/**
	 * 设置短路验证. 默认值为 false
	 * 短路验证是指在验证过程中，只要碰到验证失败则立即停止后续验证并返回
	 * 非短路验证是指验证操作一直持续到结束，无论中途有没有碰到验证失败
	 * @param shortCircuit true 表示短路型验证
	 */
	protected void setShortCircuit(boolean shortCircuit) {
		this.shortCircuit = shortCircuit;
	}
	
	protected void setDatePattern(String datePattern) {
		this.datePattern = datePattern;
	}
	
	protected String getDatePattern() {
		return (datePattern != null ? datePattern : DEFAULT_DATE_PATTERN);
	}
	
	@Override
	public void intercept(ApiInvocation inv) {
		// TODO Auto-generated method stub
		invocation = inv;
		
		paras = Kv.create();
		String[] names = invocation.getAction().getParamNames();
		if(names.length > 0) {
			Object[] args = invocation.getArgs();
			for(int i=0; i<names.length && i<args.length; i++) {
				paras.set(names[i], args[i]);
			}
		}
		
		try {
			validate(inv.getAction());
		} catch (ValidateException e) {
			// should not be throw, short circuit validate need this
			LogKit.logNothing(e);
		}
		
		if (invalid) {
			invocation.setReturnValue(ret);
			handleError(inv.getAction());
		} else {
			invocation.invoke();
		}
	}
	
	/**
	 * Use validateXxx method to validate the parameters of this action.
	 */
	protected abstract void validate(ApiRunnable apiRunnable);
	
	/**
	 * Handle the validate error.
	 * Example:<br>
	 * keepPara();<br>
	 * render("register.html");
	 */
	protected abstract void handleError(ApiRunnable apiRunnable);
	
	
	public Object getPara(String field) {
		return paras.get(field);
	}
	
	public Object getPara(int index) {
		Object[] args = invocation.getArgs();
		if(index < 0 || index > args.length || index == args.length) return null;
		return args[index];
	}
	
	private boolean validateRequired(Object value) {
		if (value == null) {	
			return false;
		} if(value instanceof String) {
			if (StrKit.isBlank((String)value)) {// 经测试,form表单域无输入时值为"",跳格键值为"\t",输入空格则为空格" "
				return false;
			}
		}
		return true;
	}

	/**
	 * Validate Required. Allow space characters.
	 */
	protected void validateRequired(String field, String errorKey, String errorMessage) {
		if(!validateRequired(getPara(field))) {
			addError(errorKey, errorMessage);
		}
	}
	
	/**
	 * Validate Required for urlPara.
	 */
	protected void validateRequired(int index, String errorKey, String errorMessage) {
		if(!validateRequired(getPara(index))) {
			addError(errorKey, errorMessage);
		}
	}
	
	
	/**
	 * Validate integer.
	 */
	protected void validateInteger(String field, int min, int max, String errorKey, String errorMessage) {
		validateIntegerValue(getPara(field), min, max, errorKey, errorMessage);
	}
	
	/**
	 * Validate integer for urlPara.
	 */
	protected void validateInteger(int index, int min, int max, String errorKey, String errorMessage) {
		validateIntegerValue(getPara(index), min, max, errorKey, errorMessage);
	}
	
	private void validateIntegerValue(Object value, int min, int max, String errorKey, String errorMessage) {
		if(!validateRequired(value)) {
			addError(errorKey, errorMessage);
			return;
		}
		try {
			int temp = Integer.parseInt(value.toString().trim());
			if (temp < min || temp > max) {
				addError(errorKey, errorMessage);
			}
		}
		catch (Exception e) {
			addError(errorKey, errorMessage);
		}
	}
	
	/**
	 * Validate integer.
	 */
	protected void validateInteger(String field, String errorKey, String errorMessage) {
		validateIntegerValue(getPara(field), errorKey, errorMessage);
	}
	
	/**
	 * Validate integer for urlPara.
	 */
	protected void validateInteger(int index, String errorKey, String errorMessage) {
		validateIntegerValue(getPara(index), errorKey, errorMessage);
	}
	
	private void validateIntegerValue(Object value, String errorKey, String errorMessage) {
		if(!validateRequired(value)) {
			addError(errorKey, errorMessage);
			return;
		}
		try {
			Integer.parseInt(value.toString().trim());
		}
		catch (Exception e) {
			addError(errorKey, errorMessage);
		}
	}
	
	/**
	 * Validate long.
	 */
	protected void validateLong(String field, long min, long max, String errorKey, String errorMessage) {
		validateLongValue(getPara(field), min, max, errorKey, errorMessage);
	}
	
	/**
	 * Validate long for urlPara.
	 */
	protected void validateLong(int index, long min, long max, String errorKey, String errorMessage) {
		Object value = getPara(index);
		validateLongValue(value, min, max, errorKey, errorMessage);
	}
	
	private void validateLongValue(Object value, long min, long max, String errorKey, String errorMessage) {
		if(!validateRequired(value)) {
			addError(errorKey, errorMessage);
			return;
		}
		try {
			long temp = Long.parseLong(value.toString().trim());
			if (temp < min || temp > max) {
				addError(errorKey, errorMessage);
			}
		}
		catch (Exception e) {
			addError(errorKey, errorMessage);
		}
	}
	
	/**
	 * Validate long.
	 */
	protected void validateLong(String field, String errorKey, String errorMessage) {
		validateLongValue(getPara(field), errorKey, errorMessage);
	}
	
	/**
	 * Validate long for urlPara.
	 */
	protected void validateLong(int index, String errorKey, String errorMessage) {
		Object value = getPara(index);
		validateLongValue(value, errorKey, errorMessage);
	}
	
	private void validateLongValue(Object value, String errorKey, String errorMessage) {
		if(!validateRequired(value)) {
			addError(errorKey, errorMessage);
			return;
		}
		try {
			Long.parseLong(value.toString().trim());
		}
		catch (Exception e) {
			addError(errorKey, errorMessage);
		}
	}
	
	/**
	 * Validate double.
	 */
	protected void validateDouble(String field, double min, double max, String errorKey, String errorMessage) {
		Object value = getPara(field);
		if(!validateRequired(value)) {
			addError(errorKey, errorMessage);
			return;
		}
		try {
			double temp = Double.parseDouble(value.toString().trim());
			if (temp < min || temp > max) {
				addError(errorKey, errorMessage);
			}
		}
		catch (Exception e) {
			addError(errorKey, errorMessage);
		}
	}
	
	/**
	 * Validate double.
	 */
	protected void validateDouble(String field, String errorKey, String errorMessage) {
		Object value = getPara(field);
		if(!validateRequired(value)) {
			addError(errorKey, errorMessage);
			return;
		}
		try {
			Double.parseDouble(value.toString().trim());
		}
		catch (Exception e) {
			addError(errorKey, errorMessage);
		}
	}
	
	/**
	 * Validate date. Date formate: yyyy-MM-dd
	 */
	protected void validateDate(String field, String errorKey, String errorMessage) {
		Object value = getPara(field);
		if(!validateRequired(value)) {
			addError(errorKey, errorMessage);
			return;
		}
		try {
			new SimpleDateFormat(getDatePattern()).parse(value.toString().trim());	// Date temp = Date.valueOf(value); 为了兼容 64位 JDK
		}
		catch (Exception e) {
			addError(errorKey, errorMessage);
		}
	}
	
	/**
	 * Validate date.
	 */
	protected void validateDate(String field, Date min, Date max, String errorKey, String errorMessage) {
		Object value = getPara(field);
		if(!validateRequired(value)) {
			addError(errorKey, errorMessage);
			return;
		}
		try {
			Date temp = new SimpleDateFormat(getDatePattern()).parse(value.toString().trim());	// Date temp = Date.valueOf(value); 为了兼容 64位 JDK
			if (temp.before(min) || temp.after(max)) {
				addError(errorKey, errorMessage);
			}
		}
		catch (Exception e) {
			addError(errorKey, errorMessage);
		}
	}
	
	/**
	 * Validate date. Date formate: yyyy-MM-dd
	 */
	protected void validateDate(String field, String min, String max, String errorKey, String errorMessage) {
		// validateDate(field, Date.valueOf(min), Date.valueOf(max), errorKey, errorMessage);  为了兼容 64位 JDK
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(getDatePattern());
			validateDate(field, sdf.parse(min.trim()), sdf.parse(max.trim()), errorKey, errorMessage);
		} catch (Exception e) {
			addError(errorKey, errorMessage);
		}
	}
	
	/**
	 * Validate equal field. Usually validate password and password again
	 */
	protected void validateEqualField(String field_1, String field_2, String errorKey, String errorMessage) {
		Object value_1 = getPara(field_1);
		Object value_2 = getPara(field_2);
		if (value_1 == null || value_2 == null || (! value_1.equals(value_2))) {
			addError(errorKey, errorMessage);
		}
	}
	
	/**
	 * Validate equal string.
	 */
	protected void validateEqualString(String s1, String s2, String errorKey, String errorMessage) {
		if (s1 == null || s2 == null || (! s1.equals(s2))) {
			addError(errorKey, errorMessage);
		}
	}
	
	/**
	 * Validate equal integer.
	 */
	protected void validateEqualInteger(Integer i1, Integer i2, String errorKey, String errorMessage) {
		if (i1 == null || i2 == null || (i1.intValue() != i2.intValue())) {
			addError(errorKey, errorMessage);
		}
	}
	
	/**
	 * Validate email.
	 */
	protected void validateEmail(String field, String errorKey, String errorMessage) {
		validateRegex(field, emailAddressPattern, false, errorKey, errorMessage);
	}
	
	/**
	 * Validate URL.
	 */
	protected void validateUrl(String field, String errorKey, String errorMessage) {
		Object value = getPara(field);
		if(!validateRequired(value)) {
			addError(errorKey, errorMessage);
			return;
		}
		try {
			String strValue = value.toString().trim();
			if (strValue.startsWith("https://")) {
				strValue = "http://" + strValue.substring(8); // URL doesn't understand the https protocol, hack it
			}
			new URL(strValue);
		} catch (MalformedURLException e) {
			addError(errorKey, errorMessage);
		}
	}
	
	/**
	 * Validate regular expression.
	 */
	protected void validateRegex(String field, String regExpression, boolean isCaseSensitive, String errorKey, String errorMessage) {
        Object value = getPara(field);
        if(!validateRequired(value)) {
			addError(errorKey, errorMessage);
			return;
		}
        String strValue = value.toString(); 
        Pattern pattern = isCaseSensitive ? Pattern.compile(regExpression) : Pattern.compile(regExpression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(strValue);
        if (!matcher.matches()) {
        	addError(errorKey, errorMessage);
        }
	}
	
	/**
	 * Validate regular expression and case sensitive.
	 */
	protected void validateRegex(String field, String regExpression, String errorKey, String errorMessage) {
		validateRegex(field, regExpression, true, errorKey, errorMessage);
	}
	
	/**
	 * Validate string.
	 */
	protected void validateString(String field, int minLen, int maxLen, String errorKey, String errorMessage) {
		validateStringValue(getPara(field), minLen, maxLen, errorKey, errorMessage);
	}
	
	/**
	 * Validate string for urlPara
	 */
	protected void validateString(int index, int minLen, int maxLen, String errorKey, String errorMessage) {
		validateStringValue(getPara(index), minLen, maxLen, errorKey, errorMessage);
	}
	
	private void validateStringValue(Object value, int minLen, int maxLen, String errorKey, String errorMessage) {
		if (minLen > 0 && !validateRequired(value)) {
			addError(errorKey, errorMessage);
			return;
		}
		String strValue = value.toString();
		if (strValue == null) {		// 支持 minLen <= 0 且 value == null 的情况
			strValue = "";
		}
		if (strValue.length() < minLen || strValue.length() > maxLen) {
			addError(errorKey, errorMessage);
		}
	}
	
	/**
	 * validate boolean.
	 */
	protected void validateBoolean(String field, String errorKey, String errorMessage) {
		validateBooleanValue(getPara(field), errorKey, errorMessage);
	}
	
	/**
	 * validate boolean for urlPara.
	 */
	protected void validateBoolean(int index, String errorKey, String errorMessage) {
		validateBooleanValue(getPara(index), errorKey, errorMessage);
	}
	
	private void validateBooleanValue(Object value, String errorKey, String errorMessage) {
		if(!validateRequired(value)) {
			addError(errorKey, errorMessage);
			return;
		}
		value = value.toString().trim().toLowerCase();
		if ("1".equals(value) || "true".equals(value)) {
			return ;
		} else if ("0".equals(value) || "false".equals(value)) {
			return ;
		}
		addError(errorKey, errorMessage);
	}
	
}
