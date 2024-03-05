package com.tpe.repository;

import com.tpe.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student,Long> {
    boolean existsByEmail(String email);

    List<Student> findByLastName(String lastname);



    //jpql
   @Query("SELECT st FROM Student st WHERE st.grade=:pGrade")
    List<Student> findAllEqualsGrade( @Param("pGrade") Integer grade);

   //native Sql
//    @Query(value="SELECT * FROM Student s where s.grade=:pGrade",nativeQuery = true)
//    List<Student> findAllEqualsGrade(@Param("pGrade") Integer grade );
}
