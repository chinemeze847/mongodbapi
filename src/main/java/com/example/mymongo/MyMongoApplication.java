package com.example.mymongo;

import com.example.mymongo.domain.Address;
import com.example.mymongo.domain.Gender;
import com.example.mymongo.domain.Student;
import com.example.mymongo.repository.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;


@SpringBootApplication
public class MyMongoApplication
{
    public static void main(String[] args) {
        SpringApplication.run(MyMongoApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(StudentRepository repository, MongoTemplate mongoTemplate)
    {
        return args ->
        {
            Address address = new Address("Nigeria",
                    "Nnewi",
                    "985757");
            String email = "thankgodchinemeze@gmail.com";
            Student student = new Student("Chinemeze",
                    "Thankgod",
                    email,
                    Gender.MALE,
                    address,
                    List.of("physics","programming","further maths"),
                    17);

            //mongodbTemplate(repository, mongoTemplate, email, student);

            repository.findStudentByEmail(email)
                    .ifPresentOrElse(s ->{
                        System.out.println(student + "already exist");
                    },()  ->{
                        System.out.println("Inserting student " + student);
                        repository.insert(student);
                    });

        };
    }


    private void mongodbTemplate(StudentRepository repository, MongoTemplate mongoTemplate, String email, Student student) {
        Query query = new Query();
        query.addCriteria(Criteria.where("email").is(email));

        List<Student> students = mongoTemplate.find(query,Student.class);

        if(students.size() > 1){
            throw new IllegalStateException("Found many students with email "+ email);
        }

        if(students.isEmpty())
        {
            System.out.println("Inserting student " + student);
            repository.insert(student);
        }else{
            System.out.println(student + "already exist");
        }
    }

}

