package com.sujeet.controller;

import com.sujeet.entity.Student;
import com.sujeet.service.StudentService;
import com.sujeet.service.impl.GraphQLService;
import graphql.ExecutionResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private GraphQLService graphQLService;

    @GetMapping("/all")
    public List<Student> findAll() {
        return studentService.findAll();
    }

    @GetMapping("/allStudentGraphQL")
    public ResponseEntity<Object>  findAll(@RequestBody String query) throws IOException {
        graphQLService.loadSchema();
        ExecutionResult execute = graphQLService.getGraphQL().execute(query);
        return new ResponseEntity<>(execute, HttpStatus.OK);
    }

    @GetMapping("/withDynamicSearch")
    public List<Student> withDynamicSearch() {
        return studentService.findStudentsWithPredicate();
    }

}
