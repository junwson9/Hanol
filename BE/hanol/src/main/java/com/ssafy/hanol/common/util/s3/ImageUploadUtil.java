package com.ssafy.hanol.common.util.s3;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.ssafy.hanol.common.exception.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class ImageUploadUtil {

    private final AmazonS3Client amazonS3Client;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;
    private final String SCALP_IMAGE_DIRECTORY = "scalp";
    private final String[] allowedExtensions = {"jpg", "jpeg", "png"};

    public String uploadImage(String directory, MultipartFile multipartFile, Long memberId) {
        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentType(multipartFile.getContentType());
        objectMetadata.setContentLength(multipartFile.getSize());
        String originalFilename = multipartFile.getOriginalFilename();
        String type = getFileType(originalFilename);

        if (!isValidImageExtension(type)) {
            throw new CustomException(S3Exception.NOT_SUPPORT_IMAGE_EXTENSION);
        }

        String imageKey = directory + "/member_" + memberId + "/" + generateImageName(originalFilename);

        String url = null;
        try {
            amazonS3Client.putObject(bucket, imageKey, multipartFile.getInputStream(), objectMetadata);
            url = amazonS3Client.getUrl(bucket, imageKey).toString();
        } catch (Exception e) {
            throw new CustomException(S3Exception.IMAGE_UPLOAD_FAIL);
        }

        return url;
    }

    private String generateImageName(String fileName) {
        String type = getFileType(fileName);
        String uuid = UUID.randomUUID().toString();
        String date = getDate();

        StringBuilder sb = new StringBuilder();

        sb.append(uuid).append("_").append(date).append(type);
        return sb.toString();
    }

    private String getFileType(String fileName){
        return fileName.substring(fileName.lastIndexOf(".")+1);
    }


    private String getDate() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        return format.format(date);
    }

    private boolean isValidImageExtension(String type) {

        for (String allowedExtension : allowedExtensions) {
            if (allowedExtension.equalsIgnoreCase(type)) {
                return true;
            }
        }
        return false;
    }
}
