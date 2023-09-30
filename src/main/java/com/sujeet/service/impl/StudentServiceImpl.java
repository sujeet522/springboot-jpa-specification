package com.sujeet.service.impl;

import com.sujeet.searchspec.SearchCriteria;
import com.sujeet.searchspec.SearchOperation;
import com.sujeet.entity.Student;
import com.sujeet.repository.StudentRepository;
import com.sujeet.searchspec.StudentSpecification;
import com.sujeet.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;



@Service
@Slf4j
@Transactional
public class StudentServiceImpl implements StudentService {

    private static final String ADDRESS = "address";
    private static final String ADDRESS_VALUE = "address1";

    private static final String AGE = "age";
    private static final Integer AGE_VALUE = 25;

    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    @Override
    public List<Student> findStudentsWithPredicate() {

        List<SearchCriteria> list=new ArrayList<>();
        SearchCriteria s1=new SearchCriteria(ADDRESS, ADDRESS_VALUE, SearchOperation.EQUAL);
        SearchCriteria s2=new SearchCriteria("name", "Bu", SearchOperation.LIKE);
        list.add(s1);
        list.add(s2);
        return studentRepository.findAll(StudentSpecification.columnEqual(list));

    }
}
