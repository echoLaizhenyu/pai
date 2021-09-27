package com.lai.pai.util;

import org.apache.http.*;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.params.CookiePolicy;
import org.apache.http.client.params.HttpClientParams;
import org.apache.http.cookie.Cookie;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.AbstractHttpClient;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.nio.charset.Charset;
import java.util.*;
import java.util.Map.Entry;


public class HttpUtil2 {
	protected static Charset charset = Charset.defaultCharset();
	public static String doPost(String url, Map params) {

		BufferedReader in = null;
		try {
			// 定义HttpClient
			HttpClient client = new DefaultHttpClient();
			// 实例化HTTP方法
			HttpPost request = new HttpPost();
			request.setURI(new URI(url));

			// 设置参数
			List<NameValuePair> nvps = new ArrayList<NameValuePair>();
			for (Iterator iter = params.keySet().iterator(); iter.hasNext();) {
				String name = (String) iter.next();
				String value = String.valueOf(params.get(name));
				nvps.add(new BasicNameValuePair(name, value));

				// System.out.println(name +"-"+value);
			}
			request.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));

			HttpResponse response = client.execute(request);
			int code = response.getStatusLine().getStatusCode();
			if (code == 200) { // 请求成功
				in = new BufferedReader(new InputStreamReader(response
						.getEntity().getContent(), "utf-8"));
				StringBuffer sb = new StringBuffer("");
				String line = "";
				String NL = System.getProperty("line.separator");
				while ((line = in.readLine()) != null) {
					sb.append(line + NL);
				}

				in.close();

				return code + "";
			} else { //
				System.out.println("状态码：" + code);
				return code + "";
			}
		} catch (Exception e) {
			e.printStackTrace();

			return null;
		}
	}

	/**
	 * post请求（用于请求json格式的参数）
	 *
	 * @param url
	 * @param params
	 * @return
	 */
	public static String doPost(String url, String params) throws Exception {

		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost(url);// 创建httpPost
		httpPost.setHeader("Accept", "application/json");
		httpPost.setHeader("Content-Type", "application/json");
		String charSet = "UTF-8";
		StringEntity entity = new StringEntity(params, charSet);
		httpPost.setEntity(entity);
		CloseableHttpResponse response = null;

		try {

			response = httpclient.execute(httpPost);
			StatusLine status = response.getStatusLine();
			int state = status.getStatusCode();
			if (state == HttpStatus.SC_OK) {
				HttpEntity responseEntity = response.getEntity();
				String jsonString = EntityUtils.toString(responseEntity);
				return jsonString;
			}
		} finally {
			if (response != null) {
				try {
					response.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			try {
				httpclient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	public static Object[] getHttpDataAndCookie(String url, Map params)
			throws Exception {
		CookieStore cs = null;// 存储最近一次的cookie 下次发送http请求的时候带上此cookie
		DefaultHttpClient client = new DefaultHttpClient();
		HttpPost httpPost = new HttpPost(url);// 创建httpPost
		// httpPost.setHeader("Accept", "application/json");
		// httpPost.setHeader("Content-Type", "application/json");
		// String charSet = "UTF-8";
		// 设置参数
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		for (Iterator iter = params.keySet().iterator(); iter.hasNext();) {
			String name = (String) iter.next();
			String value = String.valueOf(params.get(name));
			nvps.add(new BasicNameValuePair(name, value));

			// System.out.println(name +"-"+value);
		}
		httpPost.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));

		HttpClientParams.setCookiePolicy(client.getParams(),
				CookiePolicy.BROWSER_COMPATIBILITY);

		// 设置CookieStore
		if (cs != null) {
			client.setCookieStore(cs);
		}
		HttpResponse httpResponse = client.execute(httpPost);
		// 保存CookieStore
		cs = client.getCookieStore();
		HttpEntity httpent = httpResponse.getEntity();

		String code = String.valueOf(httpResponse.getStatusLine()
				.getStatusCode());

		String line;
		StringBuffer sb = new StringBuffer();

		// 获取cookie
		List<Cookie> cookies = ((AbstractHttpClient) client).getCookieStore()
				.getCookies();
		HashMap<String, String> map = new HashMap<String, String>();
		if (!cookies.isEmpty()) {
			for (int i = 0; i < cookies.size(); i++) {
				map.put(cookies.get(i).getName(), cookies.get(i).getValue());
			}
		}

		if (httpent != null) {
			BufferedReader br = new BufferedReader(new InputStreamReader(
					httpent.getContent(), "UTF-8"));
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}
			br.close();
		}

		return new Object[] { sb.toString(), code, map };
	}
	/**
	 * get请求
	 */
	public static String doGet(String url) throws Exception {
		return doGet(url, null, null);
	}

	public String doGet(String url, Map<String, String> header) throws Exception {
		return doGet(url, null, header);
	}

	public static String doGet(String url, Map<String, Object> params, Map<String, String> header) throws Exception {
		String body = null;
		try {
			// Get请求
			HttpGet httpGet = new HttpGet(url.trim());
			// 设置参数
			if (params != null && !params.isEmpty()) {
	            String str = EntityUtils.toString(new UrlEncodedFormEntity(map2NameValuePairList(params), charset));
	            String uri = httpGet.getURI().toString();
	            if(uri.indexOf("?") >= 0){
	            	httpGet.setURI(new URI(httpGet.getURI().toString() + "&" + str));
	            }else {
	            	httpGet.setURI(new URI(httpGet.getURI().toString() + "?" + str));
	            }
			}
			// 设置Header
	        if (header != null && !header.isEmpty()) {
	        	for (Iterator<Entry<String, String>> it = header.entrySet().iterator(); it.hasNext();) {
	        		Entry<String, String> entry = (Entry<String, String>) it.next();
	        		httpGet.setHeader(new BasicHeader(entry.getKey(), entry.getValue()));
	        	}
	        }
			// 发送请求,获取返回数据
			body =  execute(httpGet);
		} catch (Exception e) {
			throw e;
		}
		return body;
	}
	private static String execute(HttpRequestBase requestBase) throws Exception {
		CloseableHttpClient httpclient = HttpClients.createDefault();
		String body = null;
		try {
			CloseableHttpResponse response = httpclient.execute(requestBase);
			try {
				HttpEntity entity = response.getEntity();

				if (entity != null) {
//					body = EntityUtils.toString(entity, charset.toString());
					InputStream content = entity.getContent();
					BufferedReader in = new BufferedReader(new InputStreamReader(content));
					String s = null;
					StringBuffer sbf = new StringBuffer();
					while ((s=in.readLine())!=null) {
						System.out.println(s);
						sbf.append(s);
					}
                }

				EntityUtils.consume(entity);
			} catch (Exception e) {
				throw e;
			}finally {
				response.close();
			}
		} catch (Exception e) {
			throw e;
		} finally {
			httpclient.close();
		}
		return body;
	}

	private static List<NameValuePair> map2NameValuePairList(Map<String, Object> params) {
		if (params != null && !params.isEmpty()) {
			List<NameValuePair> list = new ArrayList<NameValuePair>();
			Iterator<String> it = params.keySet().iterator();
			while (it.hasNext()) {
				String key = it.next();
				if(params.get(key) != null) {
					String value = String.valueOf(params.get(key));
					list.add(new BasicNameValuePair(key, value));
				}
			}
			return list;
		}
		return null;
	}


	//一个加锁的获取流水号的方法，12位
	public synchronized static long getFlowNo(){
		return System.currentTimeMillis()-1300000000000L;
	}
}
