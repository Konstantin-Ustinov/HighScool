- liquibase formatted sql

- changeSet: konstantin:1
CREATE INDEX students_name_index ON student (name);

- changeSet: konstantin:2
CREATE INDEX faculty_name_and_color_index ON faculty (name, color);