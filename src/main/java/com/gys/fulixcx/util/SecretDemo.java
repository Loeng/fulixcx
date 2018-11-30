package com.gys.fulixcx.util;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dyplsapi.model.v20170525.*;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created on 17/6/7.
 * 隐私保护API产品的DEMO程序,工程中包含了一个SecretDemo类，直接通过
 * 执行main函数即可体验隐私保护产品API功能(只需要将AK替换成开通了云通信-隐私保护产品功能的AK即可)
 * 工程依赖了2个jar包(存放在工程的libs目录下)
 * 1:aliyun-java-sdk-core.jar
 * 2:aliyun-java-sdk-dyplsapi.jar
 *
 * 备注:Demo工程编码采用UTF-8
 */
public class SecretDemo {

    //产品名称:云通信隐私保护产品,开发者无需替换
    static final String product = "Dyplsapi";
    //产品域名,开发者无需替换
    static final String domain = "dyplsapi.aliyuncs.com";

    // TODO 此处需要替换成开发者自己的AK(在阿里云访问控制台寻找)
    static final String accessKeyId = "LTAIYaPF8oY8qJH1";
    static final String accessKeySecret = "oqQfNEMWqZtyR1bpEy7Q2Umpikf0ZI";
    /**
     * AXB绑定示例
     * @return
     * @throws ClientException
     */
    public static BindAxbResponse bindAxb(String userId,String p1, String p2,String poolkey,String NoX) throws ClientException {

        //可自助调整超时时间
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");

        //初始化acsClient,暂不支持region化
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);
        DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
        IAcsClient acsClient = new DefaultAcsClient(profile);

        //组装请求对象-具体描述见控制台-文档部分内容
        BindAxbRequest request = new BindAxbRequest();
        //必填:对应的号池Key
        request.setPoolKey(poolkey);
        //必填:AXB关系中的A号码
        request.setPhoneNoA(p1);
        //必填:AXB关系中的B号码
        request.setPhoneNoB(p2);
        request.setPhoneNoX(NoX);
        //必填:绑定关系对应的失效时间-不能早于当前系统时间
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//com.aliyuncs.dyplsapi.model
        long d = new Date().getTime()+30*60*1000;
        request.setExpiration(format.format(new Date(d)));
        //可选:是否需要录制音频-默认是false
        request.setIsRecordingEnabled(false);

        //外部业务自定义ID属性
        request.setOutId(userId);

        //hint 此处可能会抛出异常，注意catch
        BindAxbResponse response = acsClient.getAcsResponse(request);

        return response;
    }

    /**
     * AXN绑定示例
     * @return
     * @throws ClientException
     */
    public static BindAxnResponse bindAxn() throws ClientException {

        //可自助调整超时时间
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");

        //初始化acsClient,暂不支持region化
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);
        DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
        IAcsClient acsClient = new DefaultAcsClient(profile);

        //组装请求对象-具体描述见控制台-文档部分内容
        BindAxnRequest request = new BindAxnRequest();
        //必填:对应的号池Key
        request.setPoolKey("LTAIYaPF8oY8qJH1");
        //必填:AXN关系中的A号码
        request.setPhoneNoA("15202814761");
        //可选:AXN中A拨打X的时候转接到的默认的B号码,如果不需要则不设置
        request.setPhoneNoB("15002311788");
        //必填:绑定关系对应的失效时间-不能早于当前系统时间
        request.setExpiration("2017-09-08 17:00:00");
        //可选:是否需要录制音频-默认是false
        request.setIsRecordingEnabled(false);
        //外部业务自定义ID属性
        request.setOutId("yourOutId");
        //hint 此处可能会抛出异常，注意catch
        BindAxnResponse response = acsClient.getAcsResponse(request);

        return response;
    }

    /**
     * AXN分机号绑定示例
     * @return
     * @throws ClientException
     */
    public static BindAxnExtensionResponse bindAxnExtension() throws ClientException {

        //可自助调整超时时间
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");

        //初始化acsClient,暂不支持region化
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);
        DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
        IAcsClient acsClient = new DefaultAcsClient(profile);

        //组装请求对象-具体描述见控制台-文档部分内容
        BindAxnExtensionRequest request = new BindAxnExtensionRequest();
        //必填:对应的号池Key
        request.setPoolKey("FC12345678");
        //必填:AXN关系中的A号码
        request.setPhoneNoA("15010101010");
        //可选:分机号，如果不填，自动分配1-3位
        request.setExtension("103");
        //可选:A拨打X时回拨到默认的B号码
        request.setPhoneNoB("13507950000");
        //必填:绑定关系对应的失效时间-不能早于当前系统时间
        request.setExpiration("2017-09-08 17:00:00");
        //可选:是否需要录制音频-默认是false
        request.setIsRecordingEnabled(false);
        //外部业务自定义ID属性
        request.setOutId("yourOutId");
        //hint 此处可能会抛出异常，注意catch
        BindAxnExtensionResponse response = acsClient.getAcsResponse(request);

        return response;
    }

    public static UpdateSubscriptionResponse updateSubscription() throws ClientException {

        //设置超时时间-可自行调整
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");
        //初始化ascClient,暂时不支持多region
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId,
            accessKeySecret);
        DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
        IAcsClient acsClient = new DefaultAcsClient(profile);
        //组装请求对象-具体描述见控制台-文档部分内容
        UpdateSubscriptionRequest request = new UpdateSubscriptionRequest();
        //绑定关系对应的号池Key
        request.setPoolKey("FC10003");
        //必填:绑定关系ID
        request.setSubsId("91996");
        //必填:绑定关系对应的X号码
        request.setPhoneNoX("17100000000");
        //必填:操作类型指令支持,支持的操作类型详见文档
        request.setOperateType("updateNoB");
        //可选:需要修改的B号码
        request.setPhoneNoB("17030000000");
        //hint 此处可能会抛出异常，注意catch
        UpdateSubscriptionResponse response = acsClient.getAcsResponse(request);
        return response;
    }

    /**
     * 订购关系解绑示例(解绑接口不区分AXB、AXN)
     * @return
     * @throws ClientException
     */
    public static UnbindSubscriptionResponse unbind(String subsId, String secretNo) throws ClientException {

        //可自助调整超时时间
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");

        //初始化acsClient,暂不支持region化
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);
        DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
        IAcsClient acsClient = new DefaultAcsClient(profile);

        //组装请求对象
        UnbindSubscriptionRequest request = new UnbindSubscriptionRequest();
        //必填:对应的号池Key
        request.setPoolKey("FC100000042948026");
        //必填-分配的X小号-对应到绑定接口中返回的secretNo;
        request.setSecretNo(secretNo);
        //可选-绑定关系对应的ID-对应到绑定接口中返回的subsId;
        request.setSubsId(subsId);

        UnbindSubscriptionResponse response = acsClient.getAcsResponse(request);

        return response;
    }

}
