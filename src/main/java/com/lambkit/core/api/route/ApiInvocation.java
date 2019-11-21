package com.lambkit.core.api.route;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ApiInvocation {

	private ApiRunnable action;
	private Object target;
	private Method method;
	private Object[] args;
	private ApiInterceptor[] inters;
	private Object returnValue;
	
	private int index = 0;
	
	// InvocationWrapper need this constructor
	protected ApiInvocation() {
		this.action = null;
	}
	
	public ApiInvocation(ApiRunnable action, Object[] args) {
		this.action = action;
		this.inters = action.getInterceptors();
		this.target = action.getTarget();
		this.args = args;
	}
	
	public void invoke() {
		if (index < inters.length) {
			inters[index++].intercept(this);
		}
		else if (index++ == inters.length) {	// index++ ensure invoke action only one time
			try {
				// Invoke the action
				if (action != null) {
					returnValue = action.getMethod().invoke(target, args);
				}
			}
			catch (InvocationTargetException e) {
				Throwable t = e.getTargetException();
				if (t == null) {t = e;}
				throw t instanceof RuntimeException ? (RuntimeException)t : new RuntimeException(t);
			}
			catch (RuntimeException e) {
				throw e;
			}
			catch (Throwable t) {
				throw new RuntimeException(t);
			}
		}
	}
	
	public ApiRunnable getAction() {
		return action;
	}
	
	public Object getArg(int index) {
		if (index >= args.length)
			throw new ArrayIndexOutOfBoundsException();
		return args[index];
	}
	
	public void setArg(int index, Object value) {
		if (index >= args.length)
			throw new ArrayIndexOutOfBoundsException();
		args[index] = value;
	}
	
	public Object[] getArgs() {
		return args;
	}
	
	/**
	 * Get the target object which be intercepted
	 * <pre>
	 * Example:
	 * OrderService os = getTarget();
	 * </pre>
	 */
	public <T> T getTarget() {
		return (T)target;
	}
	
	/**
	 * Return the method of this action.
	 * <p>
	 * You can getMethod.getAnnotations() to get annotation on action method to do more things
	 */
	public Method getMethod() {
		if (action != null)
			return action.getMethod();
		return method;
	}
	
	/**
	 * Return the method name of this action's method.
	 */
	public String getMethodName() {
		if (action != null)
			return action.getMethodName();
		return method.getName();
	}
	
	/**
	 * Get the return value of the target method
	 */
	public <T> T getReturnValue() {
		return (T)returnValue;
	}
	
	/**
	 * Set the return value of the target method
	 */
	public void setReturnValue(Object returnValue) {
		this.returnValue = returnValue;
	}
	
	// ---------
	
	/**
	 * Return the action key.
	 * actionKey = controllerKey + methodName
	 */
	public String getActionKey() {
		if (action == null)
			throw new RuntimeException("This method can only be used for action interception");
		return action.getApiName();
	}
	
	/**
	 * return true if it is action invocation.
	 */
	public boolean isActionInvocation() {
		return action != null;
	}
}
