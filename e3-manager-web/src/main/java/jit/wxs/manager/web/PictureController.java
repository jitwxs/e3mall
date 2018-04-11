package jit.wxs.manager.web;

import com.qcloud.cos.model.ObjectMetadata;
import jit.wxs.common.utils.MyCosClient;
import jit.wxs.common.utils.PictureResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

/**
 * 图片Controller
 * @author jitwxs
 * @date 2018/4/2 13:46
 */
@RestController
@RequestMapping("/pic")
public class PictureController {

    @Value("${cos.secretId}")
    private String secretId;

    @Value("${cos.secretKey}")
    private String secretKey;

    @Value("${cos.regionName}")
    private String regionName;

    @Value("${cos.bucketName}")
    private String bucketName;

    @Value("${cos.pic.upload}")
    private String picUploadPath;

    /**
     * 上传图片
     */
    @RequestMapping("/upload")
    public PictureResult uploadPic(MultipartFile uploadFile) {
        MyCosClient myCosClient = null;
        try {
            // 指定要上传到 COS 上的路径
            String uploadPath = picUploadPath + "/" + uploadFile.getOriginalFilename();
            // 获取流
            InputStream in = uploadFile.getInputStream();
            // 准备ObjectMetadata
            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentType(uploadFile.getContentType());
            metadata.setContentLength(uploadFile.getSize());

            // 上传到COS
            myCosClient = new MyCosClient(secretId,secretKey,regionName);
            String url = myCosClient.upload(bucketName, in,metadata, uploadPath);

            return PictureResult.ok(url);
        } catch (IOException e) {
            e.printStackTrace();
            return PictureResult.error("图片上传失败");
        } finally {
            if(myCosClient != null) {
                myCosClient.close();
            }
        }
    }
}
