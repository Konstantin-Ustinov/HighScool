package com.scool.highscool.repository;

import com.scool.highscool.models.Faculty;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Repository
@Scope("singleton")
public class FacultyRepository {
    private final Map<Long, Faculty> faculties = new HashMap<>();
    private long sharedId = 0;

    public Faculty add(Faculty newFaculty) {
        newFaculty.setId(++sharedId);
        return faculties.put(newFaculty.getId(), newFaculty);
    }

    public Faculty find(long id) {
        return faculties.get(id);
    }

    public Set<Faculty> findAllByColor(String color) {
        return faculties.values().stream().filter(f -> f.getColor().equals(color)).collect(Collectors.toSet());
    }

    public Faculty edit(Faculty faculty) {
        return faculties.put(faculty.getId(), faculty);
    }

    public Faculty remove(long id) {
        return faculties.remove(id);
    }
}
