package com.sujeet;

import com.sujeet.entity.Student;
import com.sujeet.repository.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class DynamicFilterJPA implements CommandLineRunner {

	private final StudentRepository studentRepository;

	public DynamicFilterJPA(StudentRepository studentRepository) {
		this.studentRepository = studentRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(DynamicFilterJPA.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		studentRepository.deleteAll();

		Student student1 = Student.builder()
				.name("Budi")
				.address("address1")
				.age(26)
				.build();

		Student student2 = Student.builder()
				.name("Djaka")
				.address("address1")
				.age(28)
				.build();

		Student student3 = Student.builder()
				.name("Budi")
				.address("address1")
				.age(21)
				.build();

		Student student4 = Student.builder()
				.name("Turu")
				.address("address4")
				.age(28)
				.build();

		Student student5 = Student.builder()
				.name("Maya")
				.address("address5")
				.age(27)
				.build();

		Student student6 = Student.builder()
				.name("Dani")
				.address("address6")
				.age(29)
				.build();

		List<Student> mockStudentList = Arrays.asList(student1, student2, student3, student4, student5, student6);

		studentRepository.saveAll(mockStudentList);


	}
}
