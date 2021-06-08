package com.example.SampleWebApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.SampleWebApp.MyData;

@Repository
public interface MyDataRepository extends JpaRepository<MyData, Long> {

}
