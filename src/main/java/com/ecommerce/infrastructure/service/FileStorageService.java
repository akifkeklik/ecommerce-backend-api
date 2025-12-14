package com.ecommerce.infrastructure.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

/**
 * Service for handling file uploads.
 */
@Service
public class FileStorageService {

    private static final Logger log = LoggerFactory.getLogger(FileStorageService.class);

    @Value("${storage.upload-dir:./uploads}")
    private String uploadDir;

    @Value("${storage.allowed-extensions:jpg,jpeg,png,gif,webp}")
    private String allowedExtensions;

    @Value("${storage.max-file-size:10485760}")
    private long maxFileSize;

    /**
     * Stores a file and returns the file path.
     */
    public String storeFile(MultipartFile file, String subdirectory) throws IOException {
        validateFile(file);

        String originalFilename = StringUtils.cleanPath(file.getOriginalFilename());
        String extension = getFileExtension(originalFilename);
        String newFilename = UUID.randomUUID().toString() + "." + extension;

        Path uploadPath = Paths.get(uploadDir, subdirectory).toAbsolutePath().normalize();
        Files.createDirectories(uploadPath);

        Path targetLocation = uploadPath.resolve(newFilename);
        Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

        log.info("Stored file: {} -> {}", originalFilename, targetLocation);
        return subdirectory + "/" + newFilename;
    }

    /**
     * Stores a product image.
     */
    public String storeProductImage(MultipartFile file, String productId) throws IOException {
        return storeFile(file, "products/" + productId);
    }

    /**
     * Stores a user avatar.
     */
    public String storeUserAvatar(MultipartFile file, String userId) throws IOException {
        return storeFile(file, "avatars/" + userId);
    }

    /**
     * Deletes a file.
     */
    public void deleteFile(String filePath) throws IOException {
        Path path = Paths.get(uploadDir, filePath).toAbsolutePath().normalize();
        Files.deleteIfExists(path);
        log.info("Deleted file: {}", path);
    }

    /**
     * Gets the full path to a stored file.
     */
    public Path getFilePath(String filePath) {
        return Paths.get(uploadDir, filePath).toAbsolutePath().normalize();
    }

    /**
     * Checks if a file exists.
     */
    public boolean fileExists(String filePath) {
        return Files.exists(getFilePath(filePath));
    }

    private void validateFile(MultipartFile file) {
        if (file.isEmpty()) {
            throw new IllegalArgumentException("Cannot store empty file");
        }

        if (file.getSize() > maxFileSize) {
            throw new IllegalArgumentException("File size exceeds maximum allowed size");
        }

        String filename = StringUtils.cleanPath(file.getOriginalFilename());
        if (filename.contains("..")) {
            throw new IllegalArgumentException("Invalid file path");
        }

        String extension = getFileExtension(filename).toLowerCase();
        List<String> allowed = Arrays.asList(allowedExtensions.split(","));
        if (!allowed.contains(extension)) {
            throw new IllegalArgumentException("File type not allowed: " + extension);
        }
    }

    private String getFileExtension(String filename) {
        int dotIndex = filename.lastIndexOf('.');
        if (dotIndex == -1) {
            return "";
        }
        return filename.substring(dotIndex + 1);
    }
}
