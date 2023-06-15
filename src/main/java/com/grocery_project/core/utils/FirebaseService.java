package com.grocery_project.core.utils;
import com.google.auth.Credentials;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.*;
import com.google.firebase.cloud.StorageClient;
import com.google.firebase.database.FirebaseDatabase;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class FirebaseService {

    private final Storage storage;

    public String uploadImage(MultipartFile file) throws IOException {

        if (!isImage(file)) {
            throw new IllegalArgumentException("Invalid file type. Only image files are allowed.");
        }
        long maxSizeInBytes = 25 * 1024 * 1024; // 25MB
        if (!isFileSizeValid(file, maxSizeInBytes)) {
            throw new IllegalArgumentException("File size exceeds the maximum limit of 25MB.");
        }
        String fileName = file.getOriginalFilename();
        String bucketName = "grocery-project-8f6bf.appspot.com"; // Replace with your Firebase Storage bucket name
        String blobName = "images/" + fileName;

        Resource resource = new ClassPathResource("serviceAccountKey.json");
        FileInputStream files = new FileInputStream(resource.getFile());
        Credentials credentials = GoogleCredentials.fromStream(files);
        Storage storage = StorageOptions.newBuilder().setCredentials(credentials).build().getService();
        BlobId blobId = BlobId.of(bucketName, blobName);
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId).build();

        Blob blob = storage.create(blobInfo, file.getBytes());

        return blob.getMediaLink();
    }

    public boolean isImage(MultipartFile file) {
        String contentType = file.getContentType();
        String originalFilename = file.getOriginalFilename();
        // Check if the content type or file extension represents an image (e.g., image/png, image/jpeg, etc.)
        return contentType != null && contentType.startsWith("image/") || originalFilename != null && originalFilename.matches(".+\\.(?i)(jpg|jpeg|png|gif|bmp)$");
    }

    public boolean isFileSizeValid(MultipartFile file, long maxSizeInBytes) {
        long fileSize = file.getSize();
        return fileSize <= maxSizeInBytes;
    }

    String DOWNLOAD_URL = "https://firebasestorage.googleapis.com/v0/b/grocery-project-8f6bf.appspot.com/o/%s?alt=media";

    private String uploadFile(File file, String fileName) throws IOException {
        BlobId blobId = BlobId.of("grocery-project-8f6bf.appspot.com", fileName);
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId).setContentType("media").build();

        Resource resource = new ClassPathResource("serviceAccountKey.json");
        FileInputStream files = new FileInputStream(resource.getFile());

        Credentials credentials = GoogleCredentials.fromStream(files);
        Storage storage = StorageOptions.newBuilder().setCredentials(credentials).build().getService();
        storage.create(blobInfo, Files.readAllBytes(file.toPath()));
        return String.format(DOWNLOAD_URL, URLEncoder.encode(fileName, StandardCharsets.UTF_8));
    }

    private File convertToFile(MultipartFile multipartFile, String fileName) throws IOException {
        File tempFile = new File(fileName);
        try (FileOutputStream fos = new FileOutputStream(tempFile)) {
            fos.write(multipartFile.getBytes());
            fos.close();
        }
        return tempFile;
    }

    private String getExtension(String fileName) {
        return fileName.substring(fileName.lastIndexOf("."));
    }


    public Object upload(MultipartFile multipartFile) {

        try {
            String fileName = multipartFile.getOriginalFilename();                        // to get original file name
            fileName = UUID.randomUUID().toString().concat(this.getExtension(fileName));  // to generated random string values for file name.

            File file = this.convertToFile(multipartFile, fileName);                      // to convert multipartFile to File
          var  TEMP_URL = this.uploadFile(file, fileName);                                   // to get uploaded file link
            file.delete();                                                                // to delete the copy of uploaded file stored in the project folder
            return "\"Successfully Uploaded !\", " + TEMP_URL;                     // Your customized response
        } catch (Exception e) {
            e.printStackTrace();
            return "500," +  e +",Unsuccessfully Uploaded!";
        }

    }

    public Object download(String fileName) throws IOException {
        String destFileName = UUID.randomUUID().toString().concat(this.getExtension(fileName));     // to set random strinh for destination file name
        String destFilePath = "Z:\\New folder\\" + destFileName;                                    // to set destination file path

        ////////////////////////////////   Download  ////////////////////////////////////////////////////////////////////////
        Resource resource = new ClassPathResource("serviceAccountKey.json");
        FileInputStream files = new FileInputStream(resource.getFile());

        Credentials credentials = GoogleCredentials.fromStream(files);
        Storage storage = StorageOptions.newBuilder().setCredentials(credentials).build().getService();
        Blob blob = storage.get(BlobId.of("grocery-project-8f6bf.appspot.com", fileName));
        blob.downloadTo(Paths.get(destFilePath));
        return "\"200\", \"Successfully Downloaded!\"";
    }
}
