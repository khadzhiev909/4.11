package ru.hogwarts.school.controller;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.hogwarts.school.model.Avatar;
import ru.hogwarts.school.service.AvatarService;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@RestController
@RequestMapping("avatar")
public class AvatarController {
    private final AvatarService avatarService;

    public AvatarController(AvatarService avatarService) {
        this.avatarService = avatarService;
    }

    @PostMapping(value = "/{id}/avatar", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Long> upLoadAvatar(@PathVariable Long id, @RequestParam MultipartFile avatar) throws IOException {
        Long avatarId = avatarService.uploadAvatar(id, avatar);
        return ResponseEntity.ok(avatarId);
    }


    @GetMapping("/avatars")
    public List<Avatar> getAvatars(@RequestParam Integer page, @RequestParam Integer size) {
        return avatarService.getAllAvatars(page, size);
    }

    @GetMapping(value = "/{id}/avatar/preview")
    public ResponseEntity<byte[]> downloadAvatar(@PathVariable Long id) {
        Avatar avatar = avatarService.findAvatar(id);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.parseMediaType(avatar.getMediaType()));
        httpHeaders.setContentLength(avatar.getData().length);

        return ResponseEntity
                .status(HttpStatus.OK)
                .headers(httpHeaders)
                .body(avatar.getData());
    }

    @GetMapping(value = "/{id}/avatar")
    public void downloadAvatar (@PathVariable Long id, HttpServletResponse response) throws IOException{
        Avatar avatar = avatarService.findAvatar(id);

        Path path = Path.of(avatar.getFilePath());

        try (
                InputStream is = Files.newInputStream(path);
                OutputStream os = response.getOutputStream()
        ) {
            response.setStatus(200);
            response.setContentType(avatar.getMediaType());
            response.setContentLength((int) avatar.getFileSize());
            is.transferTo(os);
        }
    }
}
