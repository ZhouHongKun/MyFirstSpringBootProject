package com.zhk.dao;

import com.zhk.po.Type;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface TypeRepositoury extends JpaRepository<Type,Long> {


    @Override
    Type getOne(Long id);

    Type findByName(String name);

    @Query("select t from Type t")
    List<Type>  findTop(Pageable pageable);

}
