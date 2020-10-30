package com.lm.web.url;

import com.lm.web.utils.HttpUtil;
import net.sf.json.JSONObject;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;

/**
 * @Classname TestEntranceRestful
 * @Description TODO
 * @Date 2020/10/20 14:24
 * @Created by limeng
 */
@RunWith(JUnit4.class)
public class EntranceRestfulTest {
    private static RestTemplate restClient;
    @BeforeClass
    public static void beforeClass(){
        restClient = HttpUtil.getRestClient();
    }

    @Test
    public void testLogin(){
        //RestTemplate restClient = HttpUtil.getRestClient();
        JSONObject postData = new JSONObject();
        postData.put("password","hdfs");
        postData.put("userName","hdfs");

        String loginUrl = "http://192.168.200.116:8088/api/rest_j/v1/user/login";

        ResponseEntity<JSONObject> jsonResponseEntity = restClient.postForEntity(loginUrl, postData, JSONObject.class);
        System.out.println("状态码："+jsonResponseEntity.getStatusCodeValue());
        JSONObject body = jsonResponseEntity.getBody();
        System.out.println("body :" + body.toString());
    }

    /**
     * 执行任务 execute
     */
    @Test
    public void testExecuteSql(){
        testLogin();

        String url = "/api/rest_j/v1/entrance/execute";
        JSONObject map = new JSONObject();
        map.put("method",url);
        map.put("params",new HashMap<>()); //用户指定的运行服务程序的参数,必填，里面的值可以为空
        map.put("executeApplicationName","hive");//执行引擎，我用的hive
        map.put("executionCode","select count(1) from linkis_db.test1");
        map.put("runType","sql");//当用户执行如spark服务时，可以选择python、R、SQL等,不能为空
        //因为我没有执行文件脚本，所以没有scriptPath参数
        String executeSql = "http://192.168.200.116:8088/api/rest_j/v1/entrance/execute";
        ResponseEntity<JSONObject> jsonResponseEntity = restClient.postForEntity(executeSql, map, JSONObject.class);
        System.out.println("状态码："+jsonResponseEntity.getStatusCodeValue());
        JSONObject body = jsonResponseEntity.getBody();
        System.out.println("body :" + body.toString());
    }

    @Test
    public void testExecuteShell(){
        testLogin();

        String url = "/api/rest_j/v1/entrance/execute";
        JSONObject map = new JSONObject();
        map.put("method",url);
        map.put("params",new HashMap<>()); //用户指定的运行服务程序的参数,必填，里面的值可以为空
        map.put("executeApplicationName","shell");//执行引擎，我用的hive
        map.put("executionCode","sh -c echo test");
        map.put("runType","shell");//当用户执行如spark服务时，可以选择python、R、SQL等,不能为空
        //因为我没有执行文件脚本，所以没有scriptPath参数
        String executeSql = "http://192.168.200.116:8088/api/rest_j/v1/entrance/execute";
        ResponseEntity<JSONObject> jsonResponseEntity = restClient.postForEntity(executeSql, map, JSONObject.class);
        System.out.println("状态码："+jsonResponseEntity.getStatusCodeValue());
        JSONObject body = jsonResponseEntity.getBody();
        System.out.println("body :" + body.toString());
    }


    @Test
    public void testExecuteSqoop(){
        testLogin();

        String url = "/api/rest_j/v1/entrance/execute";
        JSONObject map = new JSONObject();
        map.put("method",url);
        map.put("params",new HashMap<>()); //用户指定的运行服务程序的参数,必填，里面的值可以为空
        map.put("executeApplicationName","sqoop");//执行引擎，我用的hive
        map.put("executionCode","sqoop version");
        map.put("runType","sqoop");//当用户执行如spark服务时，可以选择python、R、SQL等,不能为空
        //因为我没有执行文件脚本，所以没有scriptPath参数
        String executeSql = "http://192.168.200.116:8088/api/rest_j/v1/entrance/execute";
        ResponseEntity<JSONObject> jsonResponseEntity = restClient.postForEntity(executeSql, map, JSONObject.class);
        System.out.println("状态码："+jsonResponseEntity.getStatusCodeValue());
        JSONObject body = jsonResponseEntity.getBody();
        System.out.println("body :" + body.toString());
    }

    /**
     * 查看任务状态 status
     */
    @Test
    public void testExecStatus(){
        testLogin();
        //strongerExecId
        String execID = "030420IDEhivewzyh-cdh-client:9108IDE_hdfs_0";
        String statusUrl = "http://192.168.200.116:8088/api/rest_j/v1/entrance/" + execID +"/status";
        ResponseEntity<JSONObject> jsonResponseEntity = restClient.getForEntity(statusUrl, JSONObject.class);
        System.out.println("状态码："+jsonResponseEntity.getStatusCodeValue());
        JSONObject body = jsonResponseEntity.getBody();
        System.out.println("body :" + body.toString());
    }

    /**
     * 获取执行结果文件 get
     */
    @Test
    public void testJobHistory(){
        testLogin();
        String taskId = "649";
        String url = "http://192.168.200.116:8088/api/rest_j/v1/jobhistory/"+ taskId + "/get";
        ResponseEntity<JSONObject> jsonResponseEntity = restClient.getForEntity(url, JSONObject.class);
        System.out.println("状态码："+jsonResponseEntity.getStatusCodeValue());
        JSONObject body = jsonResponseEntity.getBody();
        System.out.println("body :" + body.toString());
    }
}
