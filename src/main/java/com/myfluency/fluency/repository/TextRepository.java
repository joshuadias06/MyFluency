package com.myfluency.fluency.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.myfluency.fluency.model.Text;
import org.springframework.stereotype.Repository;

@Repository
public interface TextRepository extends JpaRepository<Text, Long> {

}
