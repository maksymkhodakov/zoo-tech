package com.tech.zootech.security.service.implementations;

import com.tech.zootech.security.DTO.CourseDto;
import com.tech.zootech.security.service.CourseValidator;
import org.springframework.stereotype.Component;

@Component("defaultCourseValidator")
public class CourseDefaultValidator implements CourseValidator {
    @Override
    public boolean valid(CourseDto courseDto) {
        return courseDto.getCourseName().endsWith("course") &&
                courseDto.getCourseName().length() > 5 &&
                courseDto.getCourseName().length() < 20 &&
                courseDto.getTeacherName().length() > 0 &&
                courseDto.getTeacherName().length() < 100 &&
                !courseDto.getStudents().isEmpty();
    }
}
