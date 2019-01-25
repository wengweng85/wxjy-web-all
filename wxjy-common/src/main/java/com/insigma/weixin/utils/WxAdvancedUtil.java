package com.insigma.weixin.utils;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;

import org.apache.commons.httpclient.HttpStatus;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.insigma.common.listener.AppConfig;
import com.insigma.weixin.pojo.AccessToken;
import com.insigma.weixin.pojo.SNSUserInfo;
import com.insigma.weixin.pojo.WeixinOauth2Token;
import com.insigma.weixin.pojo.WeixinUserInfo;


/**
 * ΢��api���ù�����
 * @author 
 *
 */
public class WxAdvancedUtil {
	
	private static Logger log = LoggerFactory.getLogger(WxAdvancedUtil.class);
	
	public static final String APPID = AppConfig.getProperties("weixin_appid");
	public static final String APPSECRET = AppConfig.getProperties("weixin_appsecret");

    // ƾ֤��ȡ��GET��
    //��ȡacces_token �ӿڵ�ַ
    public static final String ACCESS_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
    //�ϴ��ļ�url�ӿڵ�ַ
    private static final String UPLOAD_URL = "https://api.weixin.qq.com/cgi-bin/media/upload?access_token=ACCESS_TOKEN&type=TYPE";

    /**
	   * ��ҳ��Ȩ��ȡopenId
	   * @Title: getOpenId 
	   * @Description: TODO
	   * @param code
	   * @return JSONObject
	   */
	public static JSONObject getOpenId(String code) {
		String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";
		String requestUrl = url.replace("APPID", APPID).replace("SECRET", APPSECRET).replace("CODE", code);
		JSONObject jsonObject = httpsRequest(requestUrl, "GET", null);
		return jsonObject;
	}

    /**
     * ����ý���ļ�
     *
     * @param mediaId ý���ļ���ʶ
     * @param savePath �ļ��ڷ������ϵĴ洢·��
     * @param token �ӿڷ���ƾ֤
     * @return
     */
    public static String getMedia(String mediaId, String savePath, String token) {
        String filePath = null;
        // ƴ�������ַ
        String requestUrl = "http://file.api.weixin.qq.com/cgi-bin/media/get?access_token=ACCESS_TOKEN&media_id=MEDIA_ID";
        requestUrl = requestUrl.replace("ACCESS_TOKEN", token).replace("MEDIA_ID", mediaId);
        System.out.println(requestUrl);
        try {
            URL url = new URL(requestUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoInput(true);
            conn.setRequestMethod("GET");

            if (!savePath.endsWith("/")) {
                savePath += "/";
            }
            // �����������ͻ�ȡ��չ��
            String fileExt = getFileExt(conn.getHeaderField("Content-Type"));
            // ��mediaId��Ϊ�ļ���
            filePath = savePath + mediaId + fileExt;

            BufferedInputStream bis = new BufferedInputStream(conn.getInputStream());
            FileOutputStream fos = new FileOutputStream(new File(filePath));
            byte[] buf = new byte[8096];
            int size = 0;
            while ((size = bis.read(buf)) != -1)
                fos.write(buf, 0, size);
            fos.close();
            bis.close();

            conn.disconnect();

            System.out.println("����ý���ļ��ɹ���filePath=" + filePath);
        } catch (Exception e) {
            filePath = null;
            System.out.println("����ý���ļ�ʧ�ܣ�{}\n" + e);
        }
        return filePath;
    }

    /** 
     *  
     * @Description: ��ȡaccess_token 
     * @param appid  
     * @param appsecret  
     * @Title: getAccessToken 
     * @return AccessToken     
     * @date: 2015��9��8�� ����2:23:01 
     * @author heboy 
     */  
    public static AccessToken getAccessToken(String appid, String appsecret) {  
        AccessToken accessToken = null;  
        String requestUrl = ACCESS_TOKEN_URL.replace("APPID", appid).replace("APPSECRET", appsecret);  
        JSONObject jsonObject = httpRequest(requestUrl, "GET", null);  
        // �������ɹ�  
        if (null != jsonObject) {  
            try {  
                accessToken = new AccessToken();  
                accessToken.setAccessToken(jsonObject.getString("access_token"));  
                accessToken.setExpiresIn(jsonObject.getInt("expires_in"));  
            } catch (JSONException e) {  
                accessToken = null;  
                // ��ȡtokenʧ��  
                log.info("��ȡtokenʧ�� errcode:{} errmsg:{}", jsonObject.getInt("errcode"), jsonObject.getString("errmsg"));  
            }  
        }  
        return accessToken;  
    } 

    /**
     * �������������ж��ļ���չ��
     *
     * @param contentType   ��������
     * @return
     */
    public static String getFileExt(String contentType) {
        String fileExt = "";
        if ("image/jpeg".equals(contentType))
            fileExt = ".jpg";
        else if ("audio/mpeg".equals(contentType))
            fileExt = ".mp3";
        else if ("audio/amr".equals(contentType))
            fileExt = ".amr";
        else if ("video/mp4".equals(contentType))
            fileExt = ".mp4";
        else if ("video/mpeg4".equals(contentType))
            fileExt = ".mp4";
        return fileExt;
    }

    /**
     * ����https����
     *
     * @param requestUrl �����ַ
     * @param requestMethod  ����ʽ��GET��POST��
     * @param outputStr  �ύ������
     * @return JSONObject(ͨ��JSONObject.get(key)�ķ�ʽ��ȡjson���������ֵ)
     */
    public static JSONObject httpsRequest(String requestUrl, String requestMethod, String outputStr) {
        JSONObject jsonObject = null;
        try {
            // ����SSLContext���󣬲�ʹ������ָ�������ι�������ʼ��
            TrustManager[] tm = { new MyX509TrustManager() };
            SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
            sslContext.init(null, tm, new java.security.SecureRandom());
            // ������SSLContext�����еõ�SSLSocketFactory����
            SSLSocketFactory ssf = sslContext.getSocketFactory();

            URL url = new URL(requestUrl);
            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
            conn.setSSLSocketFactory(ssf);

            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setUseCaches(false);
            // ��������ʽ��GET/POST��
            conn.setRequestMethod(requestMethod);

            // ��outputStr��Ϊnullʱ�������д����
            if (null != outputStr) {
                OutputStream outputStream = conn.getOutputStream();
                // ע������ʽ
                outputStream.write(outputStr.getBytes("UTF-8"));
                outputStream.close();
            }

            // ����������ȡ��������
            InputStream inputStream = conn.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String str = null;
            StringBuffer buffer = new StringBuffer();
            while ((str = bufferedReader.readLine()) != null) {
                buffer.append(str);
            }

            // �ͷ���Դ
            bufferedReader.close();
            inputStreamReader.close();
            inputStream.close();
            conn.disconnect();
            jsonObject = JSONObject.fromObject(buffer.toString());
        } catch (ConnectException ce) {
            System.out.println("���ӳ�ʱ��{}" + ce);
        } catch (Exception e) {
            System.out.println("https�����쳣��{}" + e);
        }
        return jsonObject;
    }


    /**
     * ��ȡ�û���Ϣ
     *
     * @param accessToken �ӿڷ���ƾ֤ �û���ʶ
     * @return WeixinUserInfo
     */
    public static WeixinUserInfo getUserInfo(String accessToken,String openId) {
        WeixinUserInfo weixinUserInfo = null;
        // ƴ�������ַ
        String requestUrl = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=ACCESS_TOKEN&openid=OPENID";
        requestUrl = requestUrl.replace("ACCESS_TOKEN", accessToken).replace("OPENID", openId);
        // ��ȡ�û���Ϣ
        JSONObject jsonObject = httpsRequest(requestUrl, "GET", null);
        // System.out.println("openId:"+openId);
        if (null != jsonObject) {
            try {
                weixinUserInfo = new WeixinUserInfo();
                // �û��ı�ʶ
                weixinUserInfo.setOpenId(jsonObject.getString("openid"));
                // ��ע״̬��1�ǹ�ע��0��δ��ע����δ��עʱ��ȡ����������Ϣ
                weixinUserInfo.setSubscribe(jsonObject.getInt("subscribe"));
                // �û���עʱ��
                weixinUserInfo.setSubscribeTime(jsonObject.getString("subscribe_time"));
                // �ǳ�
                weixinUserInfo.setNickname(jsonObject.getString("nickname"));
                // �û����Ա�1�����ԣ�2��Ů�ԣ�0��δ֪��
                weixinUserInfo.setSex(jsonObject.getInt("sex"));
                // �û����ڹ���
                weixinUserInfo.setCountry(jsonObject.getString("country"));
                // �û�����ʡ��
                weixinUserInfo.setProvince(jsonObject.getString("province"));
                // �û����ڳ���
                weixinUserInfo.setCity(jsonObject.getString("codetype"));
                // �û������ԣ���������Ϊzh_CN
                weixinUserInfo.setLanguage(jsonObject.getString("language"));
                // �û�ͷ��
                weixinUserInfo.setHeadImgUrl(jsonObject.getString("headimgurl"));
            } catch (Exception e) {
                if (0 == weixinUserInfo.getSubscribe()) {
                    System.out.println("�û�{" + weixinUserInfo.getOpenId() + "}��ȡ����ע" + e);
                } else {
                    int errorCode = jsonObject.getInt("errcode");
                    String errorMsg = jsonObject.getString("errmsg");
                    System.out.println("��ȡ�û���Ϣʧ�� errcode:{" + errorCode + "} errmsg:{" + errorMsg + "}" + e);
                    e.printStackTrace();
                }
            }
        }
        return weixinUserInfo;
    }

    /**
     * ��ȡ��ҳ��Ȩƾ֤
     *
     * @param appId  �����˺ŵ�Ψһ��ʶ
     * @param appSecret   �����˺ŵ���Կ
     * @param code
     * @return WeixinAouth2Token
     */
    public static WeixinOauth2Token getOauth2AccessToken(String appId, String appSecret, String code) {
        WeixinOauth2Token wat = null;
        // ƴ�������ַ
        String requestUrl = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";
        requestUrl = requestUrl.replace("APPID", appId);
        requestUrl = requestUrl.replace("SECRET", appSecret);
        requestUrl = requestUrl.replace("CODE", code);
        // ��ȡ��ҳ��Ȩƾ֤
        JSONObject jsonObject = httpsRequest(requestUrl, "GET", null);
        if (null != jsonObject) {
            try {
                wat = new WeixinOauth2Token();
                wat.setAccessToken(jsonObject.getString("access_token"));
                wat.setExpiresIn(jsonObject.getInt("expires_in"));
                wat.setRefreshToken(jsonObject.getString("refresh_token"));
                wat.setOpenId(jsonObject.getString("openid"));
                wat.setScope(jsonObject.getString("scope"));
            } catch (Exception e) {
                wat = null;
                int errorCode = jsonObject.getInt("errcode");
                String errorMsg = jsonObject.getString("errmsg");
                System.out.println("��ȡ�û���Ϣʧ�� errcode:{" + errorCode + "} errmsg:{" + errorMsg + "}" + e);
                e.printStackTrace();
            }
        }
        return wat;
    }

    /**
     * ˢ����ҳ��Ȩƾ֤
     *
     * @param appId �����˺ŵ�Ψһ��ʶ
     * @param refreshToken
     * @return WeixinAouth2Token
     */
    public static WeixinOauth2Token refreshOauth2AccessToken(String appId, String refreshToken) {
        WeixinOauth2Token wat = null;
        // ƴ�������ַ
        String requestUrl = "https://api.weixin.qq.com/sns/oauth2/refresh_token?appid=APPID&grant_type=refresh_token&refresh_token=REFRESH_TOKEN";
        requestUrl = requestUrl.replace("APPID", appId);
        requestUrl = requestUrl.replace("REFRESH_TOKEN", refreshToken);
        // ˢ����ҳ��Ȩƾ֤
        JSONObject jsonObject = httpsRequest(requestUrl, "GET", null);
        if (null != jsonObject) {
            try {
                wat = new WeixinOauth2Token();
                wat.setAccessToken(jsonObject.getString("access_token"));
                wat.setExpiresIn(jsonObject.getInt("expires_in"));
                wat.setRefreshToken(jsonObject.getString("refresh_token"));
                wat.setOpenId(jsonObject.getString("openid"));
                wat.setScope(jsonObject.getString("scope"));
            } catch (Exception e) {
                wat = null;
                int errorCode = jsonObject.getInt("errcode");
                String errorMsg = jsonObject.getString("errmsg");
                System.out.println("��ȡ�û���Ϣʧ�� errcode:{" + errorCode + "} errmsg:{" + errorMsg + "}" + e);
                e.printStackTrace();
            }
        }
        return wat;
    }

    /**
     * ͨ����ҳ��Ȩ��ȡ�û���Ϣ
     *
     * @param accessToken  ��ҳ��Ȩ�ӿڵ���ƾ֤
        �û���ʶ
     * @return SNSUserInfo
     */
    @SuppressWarnings({ "deprecation", "unchecked" })
    public static SNSUserInfo getSNSUserInfo(String accessToken,String openId) {
        SNSUserInfo snsUserInfo = null;
        // ƴ�������ַ
        String requestUrl = "https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID";
        requestUrl = requestUrl.replace("ACCESS_TOKEN", accessToken).replace("OPENID", openId);
        // ͨ����ҳ��Ȩ��ȡ�û���Ϣ
        JSONObject jsonObject = httpsRequest(requestUrl, "GET", null);

        if (null != jsonObject) {
            try {
                snsUserInfo = new SNSUserInfo();
                // �û��ı�ʶ
                snsUserInfo.setOpenId(jsonObject.getString("openid"));
                // �ǳ�
                snsUserInfo.setNickname(jsonObject.getString("nickname"));
                // �Ա�1�����ԣ�2��Ů�ԣ�0��δ֪��
                snsUserInfo.setSex(jsonObject.getInt("sex"));
                // �û����ڹ���
                snsUserInfo.setCountry(jsonObject.getString("country"));
                // �û�����ʡ��
                snsUserInfo.setProvince(jsonObject.getString("province"));
                // �û����ڳ���
                snsUserInfo.setCity(jsonObject.getString("codetype"));
                // �û�ͷ��
                snsUserInfo.setHeadImgUrl(jsonObject.getString("headimgurl"));
                // �û���Ȩ��Ϣ
                snsUserInfo.setPrivilegeList(JSONArray.toList(jsonObject.getJSONArray("privilege"), List.class));
            } catch (Exception e) {
                snsUserInfo = null;
                int errorCode = jsonObject.getInt("errcode");
                String errorMsg = jsonObject.getString("errmsg");
                System.out.println("��ȡ�û���Ϣʧ�� errcode:{" + errorCode + "} errmsg:{" + errorMsg + "}" + e);
                e.printStackTrace();
            }
        }
        return snsUserInfo;
    }

    /**
     * ���Ϳͷ���Ϣ
     *
     * @param accessToken
     *            �ӿڷ���ƾ֤
     * @param jsonMsg
     *            json��ʽ�Ŀͷ���Ϣ������touser��msgtype����Ϣ���ݣ�
     * @return true | false
     */
    public static boolean sendCustomMessage(String accessToken, String jsonMsg) {
        System.out.println("��Ϣ���ݣ�{}" + jsonMsg);
        boolean result = false;
        // ƴ�������ַ
        String requestUrl = "https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=ACCESS_TOKEN";
        requestUrl = requestUrl.replace("ACCESS_TOKEN", accessToken);
        // ���Ϳͷ���Ϣ
        JSONObject jsonObject = httpsRequest(requestUrl, "POST", jsonMsg);

        if (null != jsonObject) {
            int errorCode = jsonObject.getInt("errcode");
            String errorMsg = jsonObject.getString("errmsg");
            if (0 == errorCode) {
                result = true;
                System.out.println("�ͷ���Ϣ���ͳɹ� errcode:{" + errorCode + "} errmsg:{" + errorMsg + "}");
            } else {
                System.out.println("�ͷ���Ϣ����ʧ�� errcode:{" + errorCode + "} errmsg:{" + errorMsg + "}");
            }
        }

        return result;
    }

    /**
     * ��װ�ı��ͷ���Ϣ
     *
     
     *            ��Ϣ���Ͷ���
     * @param content
     *            �ı���Ϣ����
     * @return
     */
    public static String makeTextCustomMessage(String content,String openId) {
        // ����Ϣ�����е�˫���Ž���ת��
        content = content.replace("\"", "\\\"");
        String jsonMsg = "{\"touser\":\"%s\",\"msgtype\":\"text\",\"text\":{\"content\":\"%s\"}}";
        return String.format(jsonMsg, openId, content);
    }

    /**
     * ��װͼƬ�ͷ���Ϣ
     *
     
     *            ��Ϣ���Ͷ���
     * @param mediaId
     *            ý���ļ�id
     * @return
     */
    public static String makeImageCustomMessage(String mediaId,String openId) {
        String jsonMsg = "{\"touser\":\"%s\",\"msgtype\":\"image\",\"image\":{\"media_id\":\"%s\"}}";
        return String.format(jsonMsg, openId, mediaId);
    }

    /**
     * ��ȡticket������΢��JS
     *
     * @param accessToken
     * @return
     */
    public static String getTicket(String accessToken) {
        // ƴ�������ַ
        String requestUrl = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=ACCESS_TOKEN&type=jsapi";
        requestUrl = requestUrl.replace("ACCESS_TOKEN", accessToken);
        // ����
        JSONObject jsonObject = httpsRequest(requestUrl, "GET", null);
        String result = null;
        if (null != jsonObject) {
            int errorCode = jsonObject.getInt("errcode");
            String errorMsg = jsonObject.getString("errmsg");
            if (0 == errorCode) {
                result = jsonObject.getString("ticket");
                System.out.println("��ȡticket�ɹ� errcode:{" + errorCode + "} errmsg:{" + errorMsg + "}");
            } else {
                System.out.println("��ȡticketʧ�� errcode:{" + errorCode + "} errmsg:{" + errorMsg + "}");
            }
        }
        return result;
    }

    /**
     * �������ö�ά��
     *
     * @param accessToken
     * @param action_name
     * @param scene_id
     * @return
     */
    public static String getQRCodeTicket(String accessToken, String action_name, int scene_id) {
        String requestUrl = "https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token=TOKEN";
        requestUrl = requestUrl.replace("TOKEN", accessToken);
        // ����
        String param = "{\"action_name\": " + action_name + ", \"action_info\": {\"scene\": {\"scene_id\": " + scene_id
                + "}}}";
        JSONObject jsonObject = httpsRequest(requestUrl, "POST", param);
        String result = null;
        if (null != jsonObject) {
            result = jsonObject.getString("ticket");
        }
        return result;
    }
    
    /** 
     * ����https���󲢻�ȡ��� 
     * @param requestUrl �����ַ 
     * @param requestMethod ����ʽ��GET��POST�� 
     * @param outputStr �ύ������ 
     * @return JSONObject(ͨ��JSONObject.get(key)�ķ�ʽ��ȡjson���������ֵ) 
     */  
    public static JSONObject httpRequest(String requestUrl, String requestMethod, String outputStr) {  
        JSONObject jsonObject = null;  
        StringBuffer buffer = new StringBuffer();  
        try {  
            // ����SSLContext���󣬲�ʹ������ָ�������ι�������ʼ��  
            TrustManager[] tm = { new MyX509TrustManager() };  
            SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");  
            sslContext.init(null, tm, new java.security.SecureRandom());  
            // ������SSLContext�����еõ�SSLSocketFactory����  
            SSLSocketFactory ssf = sslContext.getSocketFactory();  
            URL url = new URL(requestUrl);  
            HttpsURLConnection httpUrlConn = (HttpsURLConnection) url.openConnection();  
            httpUrlConn.setSSLSocketFactory(ssf);  
            httpUrlConn.setDoOutput(true);  
            httpUrlConn.setDoInput(true);  
            httpUrlConn.setUseCaches(false);  
            // ��������ʽ��GET/POST��  
            httpUrlConn.setRequestMethod(requestMethod);  
            if ("GET".equalsIgnoreCase(requestMethod)){  
                httpUrlConn.connect();  
            }
            // ����������Ҫ�ύʱ  
            if (null != outputStr) {  
                OutputStream outputStream = httpUrlConn.getOutputStream();  
                // ע������ʽ����ֹ��������  
                outputStream.write(outputStr.getBytes("UTF-8"));  
                outputStream.close();  
            }  
            // �����ص�������ת�����ַ���  
            InputStream inputStream = httpUrlConn.getInputStream();  
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");  
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);  
            String str = null;  
            while ((str = bufferedReader.readLine()) != null) {  
                buffer.append(str);  
            }  
            bufferedReader.close();  
            inputStreamReader.close();  
            // �ͷ���Դ  
            inputStream.close();  
            inputStream = null;  
            httpUrlConn.disconnect();  
            jsonObject = JSONObject.fromObject(buffer.toString());  
        } catch (ConnectException ce) {  
            log.error("Weixin server connection timed out.");  
        } catch (Exception e) {  
            log.error("https request error:{}", e);  
        }  
        return jsonObject;  
    } 
  
    
    /**
     * URL���루utf-8��
     * @param source
     * @return
     */
    public static String urlEncodeUTF8(String source) {
      String result = source;
      try {
        result = java.net.URLEncoder.encode(source, "utf-8");
      } catch (UnsupportedEncodingException e) {
        e.printStackTrace();
      }
      return result;
    }
    /**
     * ��ȡURL  
     * @param url
     * @return
     */
    public static String getUrl(String url){
        String result = null;
        try {
            // ���ݵ�ַ��ȡ����
            HttpGet request = new HttpGet(url);
            // ��ȡ��ǰ�ͻ��˶���
            HttpClient httpClient = new DefaultHttpClient();
            // ͨ����������ȡ��Ӧ����
            HttpResponse response = httpClient.execute(request);
            // �ж���������״̬���Ƿ�����(0--200��������)
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                result= EntityUtils.toString(response.getEntity(),"UTF-8");
            } 
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return result;
    }
    
    /**
     * Get����
     * @param url
     * @return
     * ע������ ���jsonobject��jar��������jar��
     *       ���httpclient��httpcore�� jar��
     */
    public static JSONObject doGetStr(String url){
        DefaultHttpClient httpClient = new DefaultHttpClient();
        HttpGet httpGet = new HttpGet(url);
        JSONObject jsonObject = null;
        try{
            HttpResponse response = httpClient.execute(httpGet);
            HttpEntity entity = response.getEntity();
            if(entity != null){
                String result = EntityUtils.toString(entity,"UTF-8");
                jsonObject = JSONObject.fromObject(result);
            }
        }catch(ClientProtocolException e){
            e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
        }
        return jsonObject;
    }
    
    /**
     * post����
     * @param url
     * @param outStr
     * @return
     */
    public static JSONObject doPostStr(String url,String outStr){
        DefaultHttpClient httpClient = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost(url);
        JSONObject jsonObject = null;
        try{
            httpPost.setEntity(new StringEntity(outStr,"UTF-8"));
            HttpResponse response = httpClient.execute(httpPost);
            String result = EntityUtils.toString(response.getEntity(),"UTF-8");
            jsonObject = JSONObject.fromObject(result);
        }catch(Exception e){
            e.printStackTrace();
        }
        return jsonObject;
    }
    /**
     * ��ȡaccess_token
     * @return
     */
    public static AccessToken getAccessToken(){
        AccessToken token = new AccessToken();
        String url = ACCESS_TOKEN_URL.replace("APPID", APPID).replace("APPSECRET", APPSECRET);
        JSONObject jsonObject = doGetStr(url);
        if(jsonObject != null){
            token.setAccessToken(jsonObject.getString("access_token"));
            token.setExpiresIn(jsonObject.getInt("expires_in"));
        }
        System.err.println(jsonObject);
        return token;
    }
    
    /**
     * �ļ��ϴ�
     * @param filePath ·��
     * @param accessToken
     * @param type ����
     * @return
     * @throws IOException
     * @throws NoSuchAlgorithmException
     * @throws NoSuchProviderException
     * @throws KeyManagementException
     */
    public static String upload(String filePath, String accessToken,String type) throws IOException, NoSuchAlgorithmException, NoSuchProviderException, KeyManagementException {
        File file = new File(filePath);
        if (!file.exists() || !file.isFile()) {
            throw new IOException("�ļ�������");
        }
        String url = UPLOAD_URL.replace("ACCESS_TOKEN", accessToken).replace("TYPE",type);
        URL urlObj = new URL(url);
        //����
        HttpURLConnection con = (HttpURLConnection) urlObj.openConnection();
        con.setRequestMethod("POST"); 
        con.setDoInput(true);
        con.setDoOutput(true);
        con.setUseCaches(false); 
        //��������ͷ��Ϣ
        con.setRequestProperty("Connection", "Keep-Alive");
        con.setRequestProperty("Charset", "UTF-8");
        //���ñ߽�
        String BOUNDARY = "----------" + System.currentTimeMillis();
        con.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + BOUNDARY);
        StringBuilder sb = new StringBuilder();
        sb.append("--");
        sb.append(BOUNDARY);
        sb.append("\r\n");
        sb.append("Content-Disposition: form-data;name=\"file\";filename=\"" + file.getName() + "\"\r\n");
        sb.append("Content-Type:application/octet-stream\r\n\r\n");
        byte[] head = sb.toString().getBytes("utf-8");
        //��������
        OutputStream out = new DataOutputStream(con.getOutputStream());
        //�����ͷ
        out.write(head);
        //�ļ����Ĳ���
        //���ļ������ļ��ķ�ʽ ���뵽url��
        DataInputStream in = new DataInputStream(new FileInputStream(file));
        int bytes = 0;
        byte[] bufferOut = new byte[1024];
        while ((bytes = in.read(bufferOut)) != -1) {
            out.write(bufferOut, 0, bytes);
        }
        in.close();
        //��β����
        byte[] foot = ("\r\n--" + BOUNDARY + "--\r\n").getBytes("utf-8");//����������ݷָ���
        out.write(foot);
        out.flush();
        out.close();
        StringBuffer buffer = new StringBuffer();
        BufferedReader reader = null;
        String result = null;
        try {
            //����BufferedReader����������ȡURL����Ӧ
            reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String line = null;
            while ((line = reader.readLine()) != null) {
                buffer.append(line);
            }
            if (result == null) {
                result = buffer.toString();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                reader.close();
            }
        }
        JSONObject jsonObj = JSONObject.fromObject(result);
        System.out.println(jsonObj);
        String typeName = "media_id";
        if(!"image".equals(type)){
            typeName = type + "_media_id";
        }
        String mediaId = jsonObj.getString(typeName);
        System.err.println("----"+mediaId);
        return mediaId;
    }

}
