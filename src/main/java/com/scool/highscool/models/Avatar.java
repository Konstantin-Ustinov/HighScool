package com.scool.highscool.models;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Objects;

@Entity
public class Avatar {

    private String filePath;
    private String contentType;
    private byte[] data;
    private long fileSize;
    @OneToOne
    private Student student;
    @Id
    @GeneratedValue
    private long id;

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public long getFileSize() {
        return fileSize;
    }

    public void setFileSize(long fileSize) {
        this.fileSize = fileSize;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    @Override
    public String toString() {
        return "Avatar{" +
                "filePath='" + filePath + '\'' +
                ", contentType='" + contentType + '\'' +
                ", data=" + Arrays.toString(data) +
                ", fileSize=" + fileSize +
                ", student=" + student +
                ", id=" + id +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Avatar avatar = (Avatar) o;
        return fileSize == avatar.fileSize && id == avatar.id && filePath.equals(avatar.filePath) && contentType.equals(avatar.contentType) && Arrays.equals(data, avatar.data) && student.equals(avatar.student);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(filePath, contentType, fileSize, student, id);
        result = 31 * result + Arrays.hashCode(data);
        return result;
    }
}
