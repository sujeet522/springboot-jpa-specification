package com.sujeet.searchspec;

import com.sujeet.entity.Student;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class StudentSpecification {
    public static Specification<Student> columnEqual(List<SearchCriteria> filterDTOList)
    {
        return new Specification<Student>()
        {
            private static final long serialVersionUID = 1L;

            @Override
            public Predicate toPredicate(Root<Student> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder)
            {
                List<Predicate> predicates = new ArrayList<>();
                filterDTOList.forEach(filter ->
                {
                    //I don't like lambas, too unreadable for me
                    Predicate predicate;
                    switch (filter.getOperation()){
                        case EQUAL:
                            predicate = criteriaBuilder.equal(root.get(filter.getKey()),filter.getValue());
                            break;
                        case LIKE:
                            predicate = criteriaBuilder.like(root.get(filter.getKey()),"%"+filter.getValue()+"%");
                            break;
                        case LESS_THAN:
                            predicate = criteriaBuilder.lt(root.get(filter.getKey()),(Number) filter.getValue());
                            break;
                        case GREATER_THAN:
                            predicate = criteriaBuilder.gt(root.get(filter.getKey()),(Number) filter.getValue());
                            break;
                        default:
                            throw new RuntimeException("Operation not supported");
                    }
                    predicates.add(predicate);
                });

                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
    }


}
