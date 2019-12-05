package com.tk.demo;

import com.tk.demo.AdsDataJPA;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AdsDataJPARepository extends JpaRepository<AdsDataJPA, Long>{

	List<AdsDataJPA> findByTitleLike(String title);
}




