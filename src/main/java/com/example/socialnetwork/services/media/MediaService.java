package com.example.socialnetwork.services.media;

import com.example.socialnetwork.models.Media;
import com.example.socialnetwork.repositories.IMediaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.*;

@Service
public class MediaService implements IMediaService {
    @Value("${root-path.dir}")
    private String CONTEXT_PATH_DIR;

    @Value("${upload.dir}")
    private String UPLOAD_DIR;

    @Value("${images.dir}")
    private String IMAGES_DIR;

    @Value("${videos.dir}")
    private String VIDEOS_DIR;

    @Value("${audios.dir}")
    private String AUDIOS_DIR;

    @Autowired
    private IMediaRepository mediaRepository;

    @Override
    public Optional<Media> save(Media object) {
        return Optional.of(mediaRepository.save(object));
    }

    @Override
    public boolean delete(Media object) {
        try {
            mediaRepository.delete(object);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override

    public List<Map<String, String>> uploadFiles(MultipartFile[] files) {
        List<Map<String, String>> fileDetails = new ArrayList<>();

        for (MultipartFile file : files) {
            Map<String, String> fileDetail = uploadFile(file);
            if (!fileDetail.isEmpty()) {
                fileDetails.add(fileDetail);
            }
        }
        return fileDetails;
    }

    @Override
    public Boolean deleteAllByPost(String postId) {
        try{
            mediaRepository.deleteAllByPost_PostId(postId);
            return true;
        } catch (Exception e) {
            return false;
        }

    }

    private Map<String, String> uploadFile(MultipartFile file) {
        Map<String, String> fileDetail = new HashMap<>();
        if (!file.isEmpty()) {
            try {
                String originalFileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
                String extension = getFileExtension(originalFileName);
                String newFileName = generateUniqueFileName() + extension;
                String rootPath = CONTEXT_PATH_DIR;
                String uploadPath = rootPath + UPLOAD_DIR;
                File uploadDir = new File(uploadPath);
                if (!uploadDir.exists()) {
                    uploadDir.mkdirs();
                }

                String fileDir;
                if (isImageFile(file)) {
                    fileDir = IMAGES_DIR;
                } else if (isAudioFile(file)) {
                    fileDir = AUDIOS_DIR;
                } else {
                    fileDir = VIDEOS_DIR;
                }

                File fileDirPath = new File(uploadPath + File.separator + fileDir);
                if (!fileDirPath.exists()) {
                    fileDirPath.mkdirs();
                }

                Path filePath = fileDirPath.toPath().resolve(newFileName);
                Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
                fileDetail.put("url", "/" + UPLOAD_DIR + "/" + fileDir + "/" + newFileName);
                fileDetail.put("format", Objects.requireNonNull(file.getContentType()).toUpperCase());

                return fileDetail;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return fileDetail;
    }

    private String getFileExtension(String fileName) {
        int dotIndex = fileName.lastIndexOf(".");
        if (dotIndex >= 0 && dotIndex < fileName.length() - 1) {
            return fileName.substring(dotIndex);
        }
        return "";
    }

    private String generateUniqueFileName() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }

    private boolean isImageFile(MultipartFile file) {
        String contentType = file.getContentType();
        return contentType != null && contentType.startsWith("image");
    }

    private boolean isAudioFile(MultipartFile file) {
        String contentType = file.getContentType();
        return contentType != null && contentType.startsWith("audio");
    }
}
