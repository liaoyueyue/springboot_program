package org.example.myojssm.config;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.common.auth.CredentialsProviderFactory;
import com.aliyun.oss.common.auth.EnvironmentVariableCredentialsProvider;
import com.aliyuncs.exceptions.ClientException;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * Created with IntelliJ IDEA.
 * Description: 阿里云对象存储配置
 * User: liaoyueyue
 * Date: 2024-04-08
 * Time: 23:26
 */
// 声明配置类，放入Spring容器
@Configuration
// 指定配置文件中自定义属性前缀
@ConfigurationProperties(prefix = "aliyun.oss")
@Data// lombok
public class AliyunOssConfig {
    private String endPoint;// 地域节点
    private String bucketName;// OSS的Bucket名称
    private String urlPrefix;// Bucket 域名
    private String fileHost;// 目标文件夹

    // 将 OSS 客户端交给 Spring 容器托管
    @Bean
    public OSS OSSClient() throws ClientException {
        // 从环境变量中获取访问凭证
        EnvironmentVariableCredentialsProvider credentialsProvider = CredentialsProviderFactory.newEnvironmentVariableCredentialsProvider();
        return new OSSClientBuilder().build(endPoint, credentialsProvider);
    }
}
