package org.example.myojssm.common.utils;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSS;
import com.aliyun.oss.common.auth.*;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.ObjectMetadata;
import com.aliyun.oss.model.PutObjectRequest;
import com.aliyun.oss.model.PutObjectResult;

import java.io.InputStream;

/**
 * Created with IntelliJ IDEA.
 * Description: 阿里对象存储工具类
 * User: liaoyueyue
 * Date: 2024-04-09
 * Time: 1:39
 */
public class AliyunOSSUtil {
    // 地域节点
    private static final String ENDPOINT = "https://oss-cn-hangzhou.aliyuncs.com";
    // oss的Bucket名称
    private static final String BUCKET_NAME = "lyyoj";
    // Bucket 域名
    private static final String URL_PREFIX = "https://lyyoj.oss-cn-hangzhou.aliyuncs.com/avatar/";
    // oss目标文件夹
    private static final String FILE_HOST = "avatar/";
    private static final EnvironmentVariableCredentialsProvider credentialsProvider;

    static {
        try {
            credentialsProvider = CredentialsProviderFactory.newEnvironmentVariableCredentialsProvider();
        } catch (com.aliyuncs.exceptions.ClientException e) {
            throw new RuntimeException(e);
        }
    }

    public static String upload(String objectName, InputStream inputStream) {
        OSS ossClient = new OSSClientBuilder().build(ENDPOINT, credentialsProvider);
        String returnImgeUrl = "";
        try {
            // 更改获取图片链接在线浏览链接
            ObjectMetadata meta = new ObjectMetadata();
            meta.setContentType("image/jpg");
            // 创建PutObjectRequest对象。
            PutObjectRequest putObjectRequest = new PutObjectRequest(BUCKET_NAME, FILE_HOST + objectName, inputStream, meta);
            // 上传文件。
            PutObjectResult result = ossClient.putObject(putObjectRequest);
            returnImgeUrl = URL_PREFIX + objectName;
        } catch (OSSException oe) {
            System.out.println("Caught an OSSException, which means your request made it to OSS, "
                    + "but was rejected with an error response for some reason.");
            System.out.println("Error Message:" + oe.getErrorMessage());
            System.out.println("Error Code:" + oe.getErrorCode());
            System.out.println("Request ID:" + oe.getRequestId());
            System.out.println("Host ID:" + oe.getHostId());
        } catch (ClientException ce) {
            System.out.println("Caught an ClientException, which means the client encountered "
                    + "a serious internal problem while trying to communicate with OSS, "
                    + "such as not being able to access the network.");
            System.out.println("Error Message:" + ce.getMessage());
        } finally {
            if (ossClient != null) {
                ossClient.shutdown();
            }
        }
        return returnImgeUrl;
    }

    public static void delete(String objectName) {
        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(ENDPOINT, credentialsProvider);
        try {
            // 删除文件或目录。如果要删除目录，目录必须为空。
            ossClient.deleteObject(BUCKET_NAME, FILE_HOST + objectName);
        } catch (OSSException oe) {
            System.out.println("Caught an OSSException, which means your request made it to OSS, "
                    + "but was rejected with an error response for some reason.");
            System.out.println("Error Message:" + oe.getErrorMessage());
            System.out.println("Error Code:" + oe.getErrorCode());
            System.out.println("Request ID:" + oe.getRequestId());
            System.out.println("Host ID:" + oe.getHostId());
        } catch (ClientException ce) {
            System.out.println("Caught an ClientException, which means the client encountered "
                    + "a serious internal problem while trying to communicate with OSS, "
                    + "such as not being able to access the network.");
            System.out.println("Error Message:" + ce.getMessage());
        } finally {
            if (ossClient != null) {
                ossClient.shutdown();
            }
        }
    }
}
