package com.exadel.booking.utils.imguploader;

import com.exadel.booking.entities.user.UserDto;
import com.exadel.booking.entities.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Component
@Slf4j
@RequiredArgsConstructor
public class ImgFileUploader {

    private final UserService userService;

    private static final String IMAGE_EXTENSION = ".png";
    private static final String FOLDER_PATH = "classpath:static/img/";

    public UserDto createOrUpdateAvatar(UserDto userdto, MultipartFile file) {
        try {
            if (file != null && !file.isEmpty()) {
                String url = createOrUpdate(file, userdto.getEmail());
                return userService.setOrUpdateUserAvatar(userdto.getId(), url);
            }
        } catch (IOException e) {
            log.info("Uploading avatar error", e);
        }
        return userdto;
    }

    private String createOrUpdate(MultipartFile file, String futureImgName) throws IOException {
        String filePath = new StringBuilder(FOLDER_PATH).append(futureImgName).append(IMAGE_EXTENSION).toString();
        File userAvatar;
        try {
            userAvatar = ResourceUtils.getFile(filePath);
        } catch (FileNotFoundException e) {
            URL fileUrl = ResourceUtils.getURL(FOLDER_PATH);
            userAvatar = new File(
                    new StringBuilder(fileUrl.getPath()).append(futureImgName).append(IMAGE_EXTENSION).toString());
        }
        Path path = Paths.get(userAvatar.getPath());
        byte[] bytes = file.getBytes();
        Files.write(path, bytes);
        return filePath;
    }
}

