package jit.wxs.common.utils;

import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.model.*;
import com.qcloud.cos.region.Region;

import java.io.File;
import java.io.InputStream;

/**
 * 腾迅云文件存储工具类
 *
 * @author jitwxs
 * @date 2018/4/2 14:49
 */
public class MyCosClient {
    private COSClient cosClient;

    public MyCosClient(String secretId, String secretKey, String regionName) {
        // 1 初始化用户身份信息(secretId, secretKey)
        COSCredentials cred = new BasicCOSCredentials(secretId, secretKey);
        // 2 设置bucket的区域
        ClientConfig clientConfig = new ClientConfig(new Region(regionName));
        // 3 生成cos客户端
        cosClient = new COSClient(cred, clientConfig);
    }

    /**
     * 简单文件上传, 最大支持 5 GB, 适用于小文件上传, 建议 20 M 以下的文件使用该接口
     * @param bucketName 桶名称
     * @param input 上传文件输入流
     * @param objectMetadata objectMetadata
     * @param key cos中存放路径
     * @return 文件url
     */
    public String upload(String bucketName, InputStream input, ObjectMetadata objectMetadata, String key) {
        PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, key, input, objectMetadata);
        PutObjectResult putObjectResult = cosClient.putObject(putObjectRequest);

        return getUrl(bucketName, key);
    }

    /**
     * 简单文件上传, 最大支持 5 GB, 适用于小文件上传, 建议 20 M 以下的文件使用该接口
     * @param bucketName 桶名称
     * @param file 上传文件
     * @param key cos中存放路径
     * @return 文件url
     */
    public String upload(String bucketName, File file, String key) {
        PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, key, file);
        PutObjectResult putObjectResult = cosClient.putObject(putObjectRequest);

        return getUrl(bucketName, key);
    }

    /**
     * 获得下载文件流
     * @param bucketName 桶名称
     * @param key cos中存放路径
     * @return 文件流
     */
    public InputStream download(String bucketName, String key) {
        GetObjectRequest getObjectRequest = new GetObjectRequest(bucketName, key);
        COSObject cosObject = cosClient.getObject(getObjectRequest);
        COSObjectInputStream cosObjectInput = cosObject.getObjectContent();

        return cosObjectInput;
    }

    /**
     * 下载文件到本地
     * @param bucketName 桶名称
     * @param key cos中存放路径
     * @param pathName 保存的位置
     */
    public void download(String bucketName, String key, String pathName) {
        File downFile = new File(pathName);
        GetObjectRequest getObjectRequest = new GetObjectRequest(bucketName, key);
        ObjectMetadata downObjectMeta = cosClient.getObject(getObjectRequest, downFile);
    }

    private String getUrl(String bucketName, String key) {
        String regionName = cosClient.getClientConfig().getRegion().getRegionName();

        //  https://jitwxs-1252917613.cos.ap-shanghai.myqcloud.com/pic/邮箱.png
        return "https://" + bucketName + ".cos." + regionName + ".myqcloud.com" + key;
    }

    public void close() {
        cosClient.shutdown();
    }
}
