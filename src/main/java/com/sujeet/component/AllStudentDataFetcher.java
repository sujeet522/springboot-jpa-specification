package com.sujeet.component;

import com.sujeet.entity.Student;
import com.sujeet.repository.StudentRepository;
import com.sujeet.searchspec.SearchCriteria;
import com.sujeet.searchspec.SearchOperation;
import com.sujeet.searchspec.StudentSpecification;
import com.sujeet.service.StudentService;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static com.sujeet.service.impl.StudentServiceImpl.ADDRESS;
import static com.sujeet.service.impl.StudentServiceImpl.ADDRESS_VALUE;

@Component
public class AllStudentDataFetcher implements DataFetcher<List<Student>> {

    @Autowired
    StudentRepository studentRepository;



    @Override
    public List<Student> get(DataFetchingEnvironment environment) throws Exception {
        List<SearchCriteria> list=new ArrayList<>();
        SearchCriteria s1=new SearchCriteria(ADDRESS, ADDRESS_VALUE, SearchOperation.EQUAL);
        SearchCriteria s2=new SearchCriteria("name", "Bu", SearchOperation.LIKE);
        list.add(s1);
        list.add(s2);
        return studentRepository.findAll(StudentSpecification.columnEqual(list));

        // return studentRepository.findAll();
    }
}
