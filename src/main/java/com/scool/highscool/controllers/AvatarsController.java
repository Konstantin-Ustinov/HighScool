package com.scool.highscool.controllers;

import com.scool.highscool.models.Avatar;
import com.scool.highscool.services.AvatarService;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@RestController
@RequestMapping("avatars")
public class AvatarsController {

    private final AvatarService service;

    public AvatarsController(AvatarService service) {
        this.service = service;
    }

    @PostMapping(value = "/{studentId}/avatar", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> uploadAvatar(@PathVariable long studentId, @RequestParam MultipartFile avatar) throws IOException {
        service.uploadAvatar(studentId, avatar);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/{id}/avatar-from-db")
    public ResponseEntity<byte[]> downloadAvatarFromDB(@PathVariable long id) {
        Avatar avatar = service.findAvatarByStudentId(id);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType(avatar.getContentType()));
        headers.setContentLength(avatar.getData().length);
        return ResponseEntity.status(HttpStatus.OK).headers(headers).body(avatar.getData());
    }

    @GetMapping(value = "/{id}/avatar-from-hdd")
    public void downloadAvatarFromHdd(@PathVariable long id, HttpServletResponse response) throws IOException {
        Avatar avatar = service.findAvatarByStudentId(id);
        Path filePath = Path.of(avatar.getFilePath());
        try (
                InputStream is = Files.newInputStream(filePath);
                OutputStream os = response.getOutputStream();
                BufferedInputStream bis = new BufferedInputStream(is, 1024);
                BufferedOutputStream bos = new BufferedOutputStream(os, 1024)
                )
        {
            response.setStatus(200);
            response.setContentType(avatar.getContentType());
            response.setContentLength(avatar.getData().length);
            bis.transferTo(bos);
        }
    }

    @GetMapping(value = "/all_avatars")
    public ResponseEntity<List<Avatar>> getAllAvatarsPaging(@RequestParam int startPosition, @RequestParam int endPosition) {
        return ResponseEntity.ok(service.getAllAvatarsPaging(startPosition, endPosition).getContent());
    }
}
