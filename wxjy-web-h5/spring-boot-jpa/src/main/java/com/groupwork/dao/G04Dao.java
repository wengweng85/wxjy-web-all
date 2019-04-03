package com.groupwork.dao;

import com.groupwork.entity.G04;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface G04Dao extends JpaRepository<G04,String> {
}