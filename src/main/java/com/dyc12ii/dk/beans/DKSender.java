package com.dyc12ii.dk.beans;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dyc12ii.dk.support.RestTemplateFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;


/**
 * author: dingyanchao
 * date: 2019/2/13 19:37
 * desc:
 */
public class DKSender {
    public static void main(String[] args) {
        String cookie = "NTESwebSI=AC4C7505D823DBCCF7785529D7DEF19C.hzabj-yixin-qiye9.server.163.org-7083; NUMENPLUS=24d8f50922b590f929e1c3939097604fb544afee";
        DKSender dkSender = new DKSender();
        dkSender.dk(cookie);
    }

    public String dk(String cookie) {
        String url = Const.Url.ATTENDANCE;
        HttpEntity<Object> httpEntity = getAttendanceEntity(cookie);
        ResponseEntity<String> respResponseEntity = RestTemplateFactory.normalRest().postForEntity(url, httpEntity, String.class);
        String resp = respResponseEntity.getBody();
        //Integer code = resp.getCode();
        //String msg = resp.getMsg();

        System.out.println("操作成功============>");
        //String printMsg = String.format("code: {%s}, msg: {%s}", code, msg);
        System.out.println(resp);
        return resp;
    }

    private HttpEntity<Object> getAttendanceEntity(String cookie) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-Type", Const.Headers.CONTENT_TYPE);
        httpHeaders.add("Accept-Encoding", Const.Headers.ACCEPT_ENCODING);
        httpHeaders.add("Cookie", cookie);
        httpHeaders.add("Accept", Const.Headers.ACCEPT);
        httpHeaders.add("User-Agent", Const.Headers.UA);
        httpHeaders.add("Referer", Const.Headers.ATTENDANCE_REFERER);
        httpHeaders.add("Accept-Language", Const.Headers.ACCEPT_LANGUAGE);
        httpHeaders.add("X-Requested-With", Const.Headers.X_REQUESTED_WITH);
        httpHeaders.add("Origin", "http://numenplus.yixin.im");
        httpHeaders.add("Access-Control-Allow-Origin", "http://numenplus.yixin.im");

        MultiValueMap<String, String> body = new LinkedMultiValueMap<String, String>();
        String time = getToken(cookie);

        body.add("latitude", Const.Body.LATITUDE);
        body.add("longitude", Const.Body.LONGITUDE);
        body.add("workCardToken", String.valueOf(time));
        body.add("phoneId", Const.Body.PHONE_ID);

        return new HttpEntity<Object>(body, httpHeaders);
    }

    private static String getToken(String cookie) {
        String url = Const.Url.GET_TOKEN;
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-Type", Const.Headers.CONTENT_TYPE);
        httpHeaders.add("Accept-Encoding", Const.Headers.ACCEPT_ENCODING);
        httpHeaders.add("Cookie", cookie);
        httpHeaders.add("Accept", Const.Headers.ACCEPT);
        httpHeaders.add("User-Agent", Const.Headers.UA);
        httpHeaders.add("Referer", Const.Headers.ATTENDANCE_REFERER);
        httpHeaders.add("Accept-Language", Const.Headers.ACCEPT_LANGUAGE);
        httpHeaders.add("X-Requested-With", Const.Headers.X_REQUESTED_WITH);


        HttpEntity<String> httpEntity = new HttpEntity<String>(null, httpHeaders);
        HttpMethod httpMethod = HttpMethod.GET;
        ResponseEntity<String> token = RestTemplateFactory.normalRest().
                exchange(url, httpMethod, httpEntity, String.class);

        String body = token.getBody();
        JSONObject jsonObject = JSON.parseObject(body);
        String msg = jsonObject.getString("msg");
        System.out.println(String.format("msg: {%s}", msg));
        return msg;
    }

    /**
     * author: dingyanchao
     * date:   9:37
     * desc: TODO 替换成自己抓到的信息, 尤其是经纬度和PHONE_ID, ATTENDANCE_REFERER中的UUID
    */
    interface Const {
        interface Url {
            String ATTENDANCE = "http://numenplus.yixin.im/neteaseattendance/attendance.do";
            String GET_TOKEN = "http://numenplus.yixin.im/neteaseattendance/getToken.do";
        }

        interface Headers {
            String ATTENDANCE_REFERER = "http://numenplus.yixin.im/neteaseattendance/enter.do?appName=netease-attendance&from=QiyeYixin&companyId=1&__UUID__=8FDCEA8AC47EDD1C91FD9D5EA5720CC7";
            String UA = "Mozilla/5.0 (iPhone; CPU iPhone OS 12_1_3 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) Mobile/16D39 yxwork ios/310";
            String ACCEPT_LANGUAGE = "zh-cn";
            String ACCEPT_ENCODING = "deflate";
            String ACCEPT = "*/*";
            String X_REQUESTED_WITH = "XMLHttpRequest";
            String CONTENT_TYPE = "application/x-www-form-urlencoded";
        }

        interface Body {
            String LATITUDE = "i-=m%vvmv+v-vn-%ik";
            String LONGITUDE = "mn-=n-mv-iipiqmvn%";
            String PHONE_ID = "xxx"; //TODO
        }
    }



}
