package com.sujeet.service;

import com.sujeet.entity.Student;

import java.util.List;

public interface StudentService {

    List<Student> findAll();
    List<Student> findStudentsWithPredicate();

}
