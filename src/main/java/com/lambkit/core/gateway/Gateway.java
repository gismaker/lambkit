/**
 * Copyright (c) 2015-2017, Henry Yang 杨勇 (gismail@foxmail.com).
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.lambkit.core.gateway;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.AbortableHttpRequest;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIUtils;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.DefaultConnectionKeepAliveStrategy;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicHttpEntityEnclosingRequest;
import org.apache.http.message.BasicHttpRequest;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.message.HeaderGroup;
import org.apache.http.util.EntityUtils;

import com.jfinal.log.Log;
import com.lambkit.Lambkit;
import com.lambkit.common.exception.LambkitException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.Closeable;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpCookie;
import java.net.URI;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Enumeration;
import java.util.Formatter;
import java.util.List;
import java.util.Map;

/**
 * 网关服务
 */
public class Gateway {
	static Log log = Log.getLog(Gateway.class);

	protected static final String ATTR_TARGET_URI = Gateway.class.getSimpleName() + ".targetUri";
	protected static final String ATTR_TARGET_HOST = Gateway.class.getSimpleName() + ".targetHost";

	protected boolean doLog = true;
	protected boolean doForwardIP = true;
	/** User agents shouldn't send the url fragment but what if it does? */
	protected boolean doSendUrlFragment = true;
	protected boolean doPreserveHost = false;
	protected boolean doPreserveCookies = true;//true, shiro of upms success
	protected boolean doHandleRedirects = false;
	protected int connectTimeout = 3000;
	protected int readTimeout = 3000;
	//保持长连接
	protected boolean doKeepAlive = false;

	private HttpClient proxyClient;
	
	public Gateway() {
		// TODO Auto-generated constructor stub
		proxyClient = createHttpClient(buildRequestConfig());
	}
	
	public Gateway(GatewayConfig config) {
		this.doLog = config.isLog();
		this.doForwardIP = config.isForwardip();
		this.doPreserveHost = config.isPreserveHost();
		this.doPreserveCookies = config.isPreserveCookies();
		this.doHandleRedirects = config.isHandleRedirects();
		this.connectTimeout = config.getConnectTimeout();
		this.readTimeout = config.getReadTimeout();
		this.doKeepAlive = config.isKeepAlive();
		proxyClient = createHttpClient(buildRequestConfig());
	}
	
	protected String getTargetUri(HttpServletRequest servletRequest) {
		return (String) servletRequest.getAttribute(ATTR_TARGET_URI);
	}

	protected HttpHost getTargetHost(HttpServletRequest servletRequest) {
		return (HttpHost) servletRequest.getAttribute(ATTR_TARGET_HOST);
	}
	
	public void resetUriAndHost(String targetUri, HttpServletRequest servletRequest) {
		URI targetUriObj;// new URI(targetUri)
		HttpHost targetHost;// URIUtils.extractHost(targetUriObj);
		//System.out.println("gateway targetUri: " + targetUri);
		try {
			targetUriObj = new URI(targetUri);
		} catch (Exception e) {
			throw new LambkitException("Trying to process targetUri init parameter: " + e, e);
		}
		targetHost = URIUtils.extractHost(targetUriObj);
		
		servletRequest.setAttribute(ATTR_TARGET_URI, targetUri);
		servletRequest.setAttribute(ATTR_TARGET_HOST, targetHost);
	}

	/**
	 * Sub-classes can override specific behaviour of
	 */
	protected RequestConfig buildRequestConfig() {
		RequestConfig.Builder builder = RequestConfig.custom().setRedirectsEnabled(doHandleRedirects)
				.setCookieSpec(CookieSpecs.IGNORE_COOKIES) // we handle them in
															// the servlet
															// instead
				.setConnectTimeout(connectTimeout).setSocketTimeout(readTimeout);
		return builder.build();
	}

	protected HttpClient createHttpClient(final RequestConfig requestConfig) {
		HttpClientBuilder httpBuilder = HttpClientBuilder.create().setDefaultRequestConfig(requestConfig);
		if(doKeepAlive) {
			httpBuilder.setKeepAliveStrategy(DefaultConnectionKeepAliveStrategy.INSTANCE);	
		}
		return httpBuilder.build();
	}

	protected HttpClient getProxyClient() {
		return proxyClient;
	}

	public void destroy() {
		// Usually, clients implement Closeable:
		if (proxyClient instanceof Closeable) {
			try {
				((Closeable) proxyClient).close();
			} catch (IOException e) {
				log.error("While destroying gateway, shutting down HttpClient: " + e, e);
			}
		} else {
			// Older releases require we do this:
			if (proxyClient != null)
				proxyClient.getConnectionManager().shutdown();
		}
	}

	public String get(String targetUri, Map<String, String> params) {
		HttpResponse httpResponse = null;
		String result = null;
		try {
			httpResponse = httpGet(targetUri, params);
			if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				HttpEntity httpEntity = httpResponse.getEntity();
				result = EntityUtils.toString(httpEntity);
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (httpResponse != null)
				consumeQuietly(httpResponse.getEntity());
		}
		return result;
    }
	
	public String get(String targetUri, List<NameValuePair> params) {
		HttpResponse httpResponse = null;
		String result = null;
		try {
			httpResponse = httpGet(targetUri, params);
			if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				HttpEntity httpEntity = httpResponse.getEntity();
				result = EntityUtils.toString(httpEntity);
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (httpResponse != null)
				consumeQuietly(httpResponse.getEntity());
		}
		return result;
    }

	public HttpResponse httpGet(String targetUri, Map<String, String> params) throws ClientProtocolException, IOException {
		List<NameValuePair> nameValuePairs = new ArrayList<>();
		for (String key : params.keySet()) {
			nameValuePairs.add(new BasicNameValuePair(key, params.get(key)));
		}
		return httpGet(targetUri, nameValuePairs);
	}
	
	public HttpResponse httpGet(String targetUri, List<NameValuePair> params) throws ClientProtocolException, IOException {
		StringBuffer server_url = new StringBuffer(targetUri);
        HttpGet httpGet = new HttpGet(server_url.toString());
        String param = URLEncodedUtils.format(params, "UTF-8");
        param = param.replace("+", "%20");
        httpGet.setURI(URI.create(targetUri + "?" + param));
        System.out.println("http get: " + httpGet.getURI().toURL().toString());
		return proxyClient.execute(httpGet);
	}
	
	public HttpResponse httpGet(String targetUri, String param) throws ClientProtocolException, IOException {
		StringBuffer server_url = new StringBuffer(targetUri);
        HttpGet httpGet = new HttpGet(server_url.toString());
        httpGet.setURI(URI.create(targetUri + "?" + param));
        //System.out.println("http get: " + httpGet.getURI().toURL().toString());
		return proxyClient.execute(httpGet);
	} 

	public String post(String targetUri, Map<String, String> params) {
		HttpResponse httpResponse = null;
		String result = null;
		try {
			httpResponse = httpPost(targetUri, params);
			if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				HttpEntity httpEntity = httpResponse.getEntity();
				result = EntityUtils.toString(httpEntity);
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (httpResponse != null)
				consumeQuietly(httpResponse.getEntity());
		}
		return result;
	}
	
	public String post(String targetUri, List<NameValuePair> params) {
		HttpResponse httpResponse = null;
		String result = null;
		try {
			httpResponse = httpPost(targetUri, params);
			if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				HttpEntity httpEntity = httpResponse.getEntity();
				result = EntityUtils.toString(httpEntity);
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (httpResponse != null)
				consumeQuietly(httpResponse.getEntity());
		}
		return result;
	}
	
	public HttpResponse httpPost(String targetUri, Map<String, String> params) throws ClientProtocolException, IOException {
		List<NameValuePair> nameValuePairs = new ArrayList<>();
		for (String key : params.keySet()) {
			nameValuePairs.add(new BasicNameValuePair(key, params.get(key)));
		}
		return httpPost(targetUri, nameValuePairs);
	}
	
	public HttpResponse httpPost(String targetUri, List<NameValuePair> params) throws ClientProtocolException, IOException {
		StringBuffer server_url = new StringBuffer(targetUri);
        HttpPost httpPost = new HttpPost(server_url.toString());
		httpPost.setEntity(new UrlEncodedFormEntity(params));
		return proxyClient.execute(httpPost);
	}
	
	public String accessStr(String targetUri, HttpServletRequest servletRequest) {
		return accessStr(targetUri, servletRequest, null);
	}

	public String accessStr(String targetUri, HttpServletRequest servletRequest, Map<String, String> params) {
		HttpResponse httpResponse = access(targetUri, servletRequest, params);
		String result = null;
		if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
			HttpEntity httpEntity = httpResponse.getEntity();
			try {
				result = EntityUtils.toString(httpEntity);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				if (httpResponse != null)
					consumeQuietly(httpResponse.getEntity());
			}
        } 
		return result;
	}
	
	/**
	 * 在现有的代理中，加入自己的参数，再转发
	 * @param servletRequest
	 * @return
	 */
	public HttpResponse access(String targetUri, HttpServletRequest servletRequest) {
		return access(targetUri, servletRequest, null);
	}
	
	/**
	 * 在现有的代理中，加入自己的参数，再转发
	 * @param servletRequest
	 * @param params
	 * @return
	 */
	public HttpResponse access(String targetUri, HttpServletRequest servletRequest, Map<String, String> params) {
		resetUriAndHost(targetUri, servletRequest);
		String method = servletRequest.getMethod();
		String proxyRequestUri = rewriteUrlFromRequest(servletRequest, params);
		HttpRequest proxyRequest = null;
		HttpResponse proxyResponse = null;
		try {
			if (servletRequest.getHeader(HttpHeaders.CONTENT_LENGTH) != null
					|| servletRequest.getHeader(HttpHeaders.TRANSFER_ENCODING) != null) {
				proxyRequest = newProxyRequestWithEntity(method, proxyRequestUri, servletRequest);
			} else {
				proxyRequest = new BasicHttpRequest(method, proxyRequestUri);
			}
			copyRequestHeaders(servletRequest, proxyRequest);
			setXForwardedForHeader(servletRequest, proxyRequest);
			proxyResponse = doExecute(servletRequest, proxyRequest);
		} catch (Exception e) {
			try {
				handleRequestException(proxyRequest, e);
			} catch (ServletException | IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		return proxyResponse;
	}

	public void service(String targetUri, HttpServletRequest servletRequest, HttpServletResponse servletResponse) {
		resetUriAndHost(targetUri, servletRequest);
		String method = servletRequest.getMethod();
		String proxyRequestUri = rewriteUrlFromRequest(servletRequest, null);
		HttpRequest proxyRequest = null;
		HttpResponse proxyResponse = null;

		try {
			if (servletRequest.getHeader(HttpHeaders.CONTENT_LENGTH) != null
					|| servletRequest.getHeader(HttpHeaders.TRANSFER_ENCODING) != null) {
				proxyRequest = newProxyRequestWithEntity(method, proxyRequestUri, servletRequest);
			} else {
				proxyRequest = new BasicHttpRequest(method, proxyRequestUri);
			}
			copyRequestHeaders(servletRequest, proxyRequest);
			setXForwardedForHeader(servletRequest, proxyRequest);
			proxyResponse = doExecute(servletRequest, proxyRequest);
			int statusCode = proxyResponse.getStatusLine().getStatusCode();
			servletResponse.setStatus(statusCode, proxyResponse.getStatusLine().getReasonPhrase());
			copyResponseHeaders(proxyResponse, servletRequest, servletResponse);
			if (statusCode == HttpServletResponse.SC_NOT_MODIFIED) {
				servletResponse.setIntHeader(HttpHeaders.CONTENT_LENGTH, 0);
			} else {
				copyResponseEntity(proxyResponse, servletResponse, proxyRequest, servletRequest);
			}
		} catch (Exception e) {
			try {
				handleRequestException(proxyRequest, e);
			} catch (ServletException | IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} finally {
			if (proxyResponse != null)
				consumeQuietly(proxyResponse.getEntity());
		}
	}
	
	protected void service(String targetUri, HttpResponse proxyResponse, HttpServletRequest servletRequest, HttpServletResponse servletResponse) {
		resetUriAndHost(targetUri, servletRequest);
		try {
			int statusCode = proxyResponse.getStatusLine().getStatusCode();
			servletResponse.setStatus(statusCode, proxyResponse.getStatusLine().getReasonPhrase());
			copyResponseHeaders(proxyResponse, servletRequest, servletResponse);
			if (statusCode == HttpServletResponse.SC_NOT_MODIFIED) {
				servletResponse.setIntHeader(HttpHeaders.CONTENT_LENGTH, 0);
			} else {
				copyResponseEntity(proxyResponse, servletResponse);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (proxyResponse != null)
				consumeQuietly(proxyResponse.getEntity());
		}
	}

	protected void handleRequestException(HttpRequest proxyRequest, Exception e) throws ServletException, IOException {
		// abort request, according to best practice with HttpClient
		if (proxyRequest instanceof AbortableHttpRequest) {
			AbortableHttpRequest abortableHttpRequest = (AbortableHttpRequest) proxyRequest;
			abortableHttpRequest.abort();
		}
		if (e instanceof RuntimeException)
			throw (RuntimeException) e;
		if (e instanceof ServletException)
			throw (ServletException) e;
		// noinspection ConstantConditions
		if (e instanceof IOException)
			throw (IOException) e;
		throw new RuntimeException(e);
	}

	protected HttpResponse doExecute(HttpServletRequest servletRequest, HttpRequest proxyRequest) throws IOException {
		if (doLog) {
			log.info("proxy " + servletRequest.getMethod() + " uri: " + servletRequest.getRequestURI() + " -- "
					+ proxyRequest.getRequestLine().getUri());
		}
		if (getTargetHost(servletRequest) == null)
			System.out.println("getTargetHost(servletRequest)==null");
		return proxyClient.execute(getTargetHost(servletRequest), proxyRequest);
	}

	protected HttpRequest newProxyRequestWithEntity(String method, String proxyRequestUri,
			HttpServletRequest servletRequest) throws IOException {
		HttpEntityEnclosingRequest eProxyRequest = new BasicHttpEntityEnclosingRequest(method, proxyRequestUri);
		
		Enumeration<String> names = servletRequest.getParameterNames();
		List<NameValuePair> nameValuePairs = new ArrayList<>();
		while(names.hasMoreElements()){
	         String key = names.nextElement();
	         nameValuePairs.add(new BasicNameValuePair(key, servletRequest.getParameter(key)));
	     }
		eProxyRequest.setEntity(new UrlEncodedFormEntity(nameValuePairs));
		//servletRequest.getInputStream()只能被调用一次，JFinal在Controller前已调用，故此失效。
		//eProxyRequest.setEntity(new InputStreamEntity(servletRequest.getInputStream(), getContentLength(servletRequest)));
		return eProxyRequest;
	}

	protected void closeQuietly(Closeable closeable) {
		try {
			closeable.close();
		} catch (IOException e) {
			log.error(e.getMessage(), e);
		}
	}

	/**
	 * HttpClient v4.1 doesn't have the method.
	 */
	protected void consumeQuietly(HttpEntity entity) {
		try {
			EntityUtils.consume(entity);
		} catch (IOException e) {// ignore
			log.error(e.getMessage(), e);
		}
	}

	/**
	 * These are the "hop-by-hop" headers that should not be copied.
	 * http://www.w3.org/Protocols/rfc2616/rfc2616-sec13.html I use an
	 * HttpClient HeaderGroup class instead of Set&lt;String&gt; because this
	 * approach does case insensitive lookup faster.
	 */
	protected static final HeaderGroup hopByHopHeaders;
	static {
		hopByHopHeaders = new HeaderGroup();
		String[] headers = new String[] { "Connection", "Keep-Alive", "Proxy-Authenticate", "Proxy-Authorization", "TE",
				"Trailers", "Transfer-Encoding", "Upgrade" };
		for (String header : headers) {
			hopByHopHeaders.addHeader(new BasicHeader(header, null));
		}
	}

	/**
	 * Copy request headers from the servlet client to the proxy request. This
	 * is easily overridden to add your own.
	 */
	protected void copyRequestHeaders(HttpServletRequest servletRequest, HttpRequest proxyRequest) {
		// Get an Enumeration of all of the header names sent by the client
		Enumeration<String> enumerationOfHeaderNames = servletRequest.getHeaderNames();
		while (enumerationOfHeaderNames.hasMoreElements()) {
			String headerName = enumerationOfHeaderNames.nextElement();
			copyRequestHeader(servletRequest, proxyRequest, headerName);
		}
	}

	/**
	 * Copy a request header from the servlet client to the proxy request. This
	 * is easily overridden to filter out certain headers if desired.
	 */
	protected void copyRequestHeader(HttpServletRequest servletRequest, HttpRequest proxyRequest, String headerName) {
		// Instead the content-length is effectively set via InputStreamEntity
		if (headerName.equalsIgnoreCase(HttpHeaders.CONTENT_LENGTH))
			return;
		if (hopByHopHeaders.containsHeader(headerName))
			return;

		Enumeration<String> headers = servletRequest.getHeaders(headerName);
		while (headers.hasMoreElements()) {// sometimes more than one value
			String headerValue = headers.nextElement();
			// In case the proxy host is running multiple virtual servers,
			// rewrite the Host header to ensure that we get content from
			// the correct virtual server
			if (!doPreserveHost && headerName.equalsIgnoreCase(HttpHeaders.HOST)) {
				HttpHost host = getTargetHost(servletRequest);
				headerValue = host.getHostName();
				if (host.getPort() != -1)
					headerValue += ":" + host.getPort();
			} else if (!doPreserveCookies && headerName.equalsIgnoreCase(org.apache.http.cookie.SM.COOKIE)) {
				headerValue = getRealCookie(headerValue);
			}
			proxyRequest.addHeader(headerName, headerValue);
		}
	}

	private void setXForwardedForHeader(HttpServletRequest servletRequest, HttpRequest proxyRequest) {
		if (doForwardIP) {
			String forHeaderName = "X-Forwarded-For";
			String forHeader = servletRequest.getRemoteAddr();
			String existingForHeader = servletRequest.getHeader(forHeaderName);
			if (existingForHeader != null) {
				forHeader = existingForHeader + ", " + forHeader;
			}
			proxyRequest.setHeader(forHeaderName, forHeader);

			String protoHeaderName = "X-Forwarded-Proto";
			String protoHeader = servletRequest.getScheme();
			proxyRequest.setHeader(protoHeaderName, protoHeader);
		}
	}

	/** Copy proxied response headers back to the servlet client. */
	protected void copyResponseHeaders(HttpResponse proxyResponse, HttpServletRequest servletRequest,
			HttpServletResponse servletResponse) {
		for (Header header : proxyResponse.getAllHeaders()) {
			copyResponseHeader(servletRequest, servletResponse, header);
		}
	}

	/**
	 * Copy a proxied response header back to the servlet client. This is easily
	 * overwritten to filter out certain headers if desired.
	 */
	protected void copyResponseHeader(HttpServletRequest servletRequest, HttpServletResponse servletResponse,
			Header header) {
		String headerName = header.getName();
		if (hopByHopHeaders.containsHeader(headerName))
			return;
		String headerValue = header.getValue();
		if (headerName.equalsIgnoreCase(org.apache.http.cookie.SM.SET_COOKIE)
				|| headerName.equalsIgnoreCase(org.apache.http.cookie.SM.SET_COOKIE2)) {
			copyProxyCookie(servletRequest, servletResponse, headerValue);
		} else if (headerName.equalsIgnoreCase(HttpHeaders.LOCATION)) {
			// LOCATION Header may have to be rewritten.
			servletResponse.addHeader(headerName, rewriteUrlFromResponse(servletRequest, headerValue));
		} else {
			servletResponse.addHeader(headerName, headerValue);
		}
	}

	/**
	 * Copy cookie from the proxy to the servlet client. Replaces cookie path to
	 * local path and renames cookie to avoid collisions.
	 */
	protected void copyProxyCookie(HttpServletRequest servletRequest, HttpServletResponse servletResponse,
			String headerValue) {
		List<HttpCookie> cookies = HttpCookie.parse(headerValue);
		String path = servletRequest.getContextPath(); // path starts with / or
														// is empty string
		path += servletRequest.getServletPath(); // servlet path starts with /
													// or is empty string
		if (path.isEmpty()) {
			path = "/";
		}

		for (HttpCookie cookie : cookies) {
			// set cookie name prefixed w/ a proxy value so it won't collide w/
			// other cookies
			String proxyCookieName = doPreserveCookies ? cookie.getName()
					: getCookieNamePrefix(cookie.getName()) + cookie.getName();
			Cookie servletCookie = new Cookie(proxyCookieName, cookie.getValue());
			servletCookie.setComment(cookie.getComment());
			servletCookie.setMaxAge((int) cookie.getMaxAge());
			//servletCookie.setPath(path); // set to the path of the proxy servlet
			// don't set cookie domain
			servletCookie.setSecure(cookie.getSecure());
			servletCookie.setVersion(cookie.getVersion());
			servletResponse.addCookie(servletCookie);
		}
	}

	/**
	 * Take any client cookies that were originally from the proxy and prepare
	 * them to send to the proxy. This relies on cookie headers being set
	 * correctly according to RFC 6265 Sec 5.4. This also blocks any local
	 * cookies from being sent to the proxy.
	 */
	protected String getRealCookie(String cookieValue) {
		StringBuilder escapedCookie = new StringBuilder();
		String cookies[] = cookieValue.split("[;,]");
		for (String cookie : cookies) {
			System.out.println("cookie:" + cookie);
			String cookieSplit[] = cookie.split("=");
			if (cookieSplit.length == 2) {
				String cookieName = cookieSplit[0].trim();
				if (cookieName.startsWith(getCookieNamePrefix(cookieName))) {
					cookieName = cookieName.substring(getCookieNamePrefix(cookieName).length());
					if (escapedCookie.length() > 0) {
						escapedCookie.append("; ");
					}
					escapedCookie.append(cookieName).append("=").append(cookieSplit[1].trim());
				}
			}
		}
		return escapedCookie.toString();
	}

	/** The string prefixing rewritten cookies. */
	protected String getCookieNamePrefix(String name) {
		return "!lambkit_proxy!";//"!Proxy!" + name;
	}

	/**
	 * Copy response body data (the entity) from the proxy to the servlet
	 * client.
	 */
	protected void copyResponseEntity(HttpResponse proxyResponse, HttpServletResponse servletResponse,
			HttpRequest proxyRequest, HttpServletRequest servletRequest) throws IOException {
		HttpEntity entity = proxyResponse.getEntity();
		if (entity != null) {
			OutputStream servletOutputStream = servletResponse.getOutputStream();
			entity.writeTo(servletOutputStream);
		}
	}
	
	protected void copyResponseEntity(HttpResponse proxyResponse, HttpServletResponse servletResponse) throws IOException {
		HttpEntity entity = proxyResponse.getEntity();
		if (entity != null) {
			OutputStream servletOutputStream = servletResponse.getOutputStream();
			entity.writeTo(servletOutputStream);
		}
	}

	/**
	 * Reads the request URI from {@code servletRequest} and rewrites it,
	 * considering targetUri. It's used to make the new request.
	 */
	protected String rewriteUrlFromRequest(HttpServletRequest servletRequest, Map<String, String> params) {
		StringBuilder uri = new StringBuilder(500);
		uri.append(getTargetUri(servletRequest));
		// Handle the path given to the servlet
		String pathInfo = servletRequest.getPathInfo();
		if (pathInfo != null) {// ex: /my/path.html
			// getPathInfo() returns decoded string, so we need encodeUriQuery
			// to encode "%" characters
			uri.append(encodeUriQuery(pathInfo, true));
		}
		// Handle the query string & fragment
		String queryString = servletRequest.getQueryString();// ex:(following
																// '?'):
																// name=value&foo=bar#fragment
		String fragment = null;
		// split off fragment from queryString, updating queryString if found
		if (queryString != null) {
			int fragIdx = queryString.indexOf('#');
			if (fragIdx >= 0) {
				fragment = queryString.substring(fragIdx + 1);
				queryString = queryString.substring(0, fragIdx);
			}
		}
		
		int uritype = 0;
		queryString = rewriteQueryStringFromRequest(servletRequest, queryString);
		if (queryString != null && queryString.length() > 0) {
			uri.append('?');
			// queryString is not decoded, so we need encodeUriQuery not to
			// encode "%" characters, to avoid double-encoding
			uri.append(encodeUriQuery(queryString, false));
			uritype = 1;
		}
		
		if(params!=null) {
			for (String key : params.keySet()) {
				if(uritype==0) {
					uri.append('?');
					uritype = 1;
				} else if(uritype==1) {
					uri.append("&");
				}
				uri.append(key).append("=").append(encodeUriQuery(params.get(key),false));
			}
		}

		if (doSendUrlFragment && fragment != null) {
			uri.append('#');
			// fragment is not decoded, so we need encodeUriQuery not to encode
			// "%" characters, to avoid double-encoding
			uri.append(encodeUriQuery(fragment, false));
		}
		if (Lambkit.isDevMode()) {
			System.out.println("proxy request url: " + uri.toString());
		}
		return uri.toString();
	}

	protected String rewriteQueryStringFromRequest(HttpServletRequest servletRequest, String queryString) {
		return queryString;
	}

	/**
	 * For a redirect response from the target server, this translates
	 * {@code theUrl} to redirect to and translates it to one the original
	 * client can use.
	 */
	protected String rewriteUrlFromResponse(HttpServletRequest servletRequest, String theUrl) {
		// TODO document example paths
		final String targetUri = getTargetUri(servletRequest);
		if (theUrl.startsWith(targetUri)) {
			/*-
			 * The URL points back to the back-end server.
			 * Instead of returning it verbatim we replace the target path with our
			 * source path in a way that should instruct the original client to
			 * request the URL pointed through this Proxy.
			 * We do this by taking the current request and rewriting the path part
			 * using this servlet's absolute path and the path from the returned URL
			 * after the base target URL.
			 */
			StringBuffer curUrl = servletRequest.getRequestURL();// no query
			int pos;
			// Skip the protocol part
			if ((pos = curUrl.indexOf("://")) >= 0) {
				// Skip the authority part
				// + 3 to skip the separator between protocol and authority
				if ((pos = curUrl.indexOf("/", pos + 3)) >= 0) {
					// Trim everything after the authority part.
					curUrl.setLength(pos);
				}
			}
			// Context path starts with a / if it is not blank
			curUrl.append(servletRequest.getContextPath());
			// Servlet path starts with a / if it is not blank
			curUrl.append(servletRequest.getServletPath());
			curUrl.append(theUrl, targetUri.length(), theUrl.length());
			theUrl = curUrl.toString();
		}
		return theUrl;
	}

	/**
	 * Encodes characters in the query or fragment part of the URI.
	 *
	 * <p>
	 * Unfortunately, an incoming URI sometimes has characters disallowed by the
	 * spec. HttpClient insists that the outgoing proxied request has a valid
	 * URI because it uses Java's {@link URI}. To be more forgiving, we must
	 * escape the problematic characters. See the URI class for the spec.
	 *
	 * @param in
	 *            example: name=value&amp;foo=bar#fragment
	 * @param encodePercent
	 *            determine whether percent characters need to be encoded
	 */
	protected static CharSequence encodeUriQuery(CharSequence in, boolean encodePercent) {
		// Note that I can't simply use URI.java to encode because it will
		// escape pre-existing escaped things.
		StringBuilder outBuf = null;
		Formatter formatter = null;
		for (int i = 0; i < in.length(); i++) {
			char c = in.charAt(i);
			boolean escape = true;
			if (c < 128) {
				if (asciiQueryChars.get((int) c) && !(encodePercent && c == '%')) {
					escape = false;
				}
			} else if (!Character.isISOControl(c) && !Character.isSpaceChar(c)) {// not-ascii
				escape = false;
			}
			if (!escape) {
				if (outBuf != null)
					outBuf.append(c);
			} else {
				// escape
				if (outBuf == null) {
					outBuf = new StringBuilder(in.length() + 5 * 3);
					outBuf.append(in, 0, i);
					formatter = new Formatter(outBuf);
				}
				// leading %, 0 padded, width 2, capital hex
				formatter.format("%%%02X", (int) c);// TODO
			}
		}
		return outBuf != null ? outBuf : in;
	}

	protected static final BitSet asciiQueryChars;
	static {
		char[] c_unreserved = "_-!.~'()*".toCharArray();// plus alphanum
		char[] c_punct = ",;:$&+=".toCharArray();
		char[] c_reserved = "?/[]@".toCharArray();// plus punct

		asciiQueryChars = new BitSet(128);
		for (char c = 'a'; c <= 'z'; c++)
			asciiQueryChars.set((int) c);
		for (char c = 'A'; c <= 'Z'; c++)
			asciiQueryChars.set((int) c);
		for (char c = '0'; c <= '9'; c++)
			asciiQueryChars.set((int) c);
		for (char c : c_unreserved)
			asciiQueryChars.set((int) c);
		for (char c : c_punct)
			asciiQueryChars.set((int) c);
		for (char c : c_reserved)
			asciiQueryChars.set((int) c);

		asciiQueryChars.set((int) '%');// leave existing percent escapes in
										// place
	}

}
