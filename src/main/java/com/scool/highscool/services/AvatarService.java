package com.scool.highscool.services;

import com.scool.highscool.models.Avatar;
import com.scool.highscool.models.Student;
import com.scool.highscool.repository.AvatarRepository;
import com.scool.highscool.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

import static java.nio.file.StandardOpenOption.CREATE_NEW;

@Service
@Transactional
public class AvatarService {
    @Value("avatars")
    private String avatarsDir;
    private StudentRepository studentRepository;
    private AvatarRepository repository;

    public AvatarService(StudentRepository studentRepository, AvatarRepository repository) {
        this.studentRepository = studentRepository;
        this.repository = repository;
    }

    public void uploadAvatar(long studentId, MultipartFile avatarFile) throws IOException {
        Student student = studentRepository.findById(studentId).get();
        Path filePath = Path.of(avatarsDir, student + "." + getExtensions(avatarFile.getOriginalFilename()));
        Files.createDirectories(filePath.getParent());
        Files.deleteIfExists(filePath);

        try (
                InputStream is = avatarFile.getInputStream();
                OutputStream os = Files.newOutputStream(filePath, CREATE_NEW);
                BufferedInputStream bis = new BufferedInputStream(is, 1024);
                BufferedOutputStream bos = new BufferedOutputStream(os, 1024)
        ) {
            bis.transferTo(bos);
        }

        Avatar avatar = findAvatarByStudentId(studentId);
        avatar.setStudent(student);
        avatar.setFilePath(filePath.toString());
        avatar.setFileSize(avatarFile.getSize());
        avatar.setContentType(avatarFile.getContentType());
        avatar.setData(avatarFile.getBytes());
        repository.save(avatar);

    }

    private String getExtensions(String fileName) {
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }

    public Avatar findAvatarByStudentId(long studentId) {
        return repository.findByStudentId(studentId).orElse(new Avatar());
    }
}
