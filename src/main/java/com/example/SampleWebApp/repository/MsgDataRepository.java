package com.example.SampleWebApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.SampleWebApp.MsgData;

public interface MsgDataRepository extends JpaRepository<MsgData, Long> {
	
}
