package com.example.plaza_de_comidas.adapters.driven.jpa.msql.repository;

import com.example.plaza_de_comidas.adapters.driven.jpa.msql.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepositoryJPA extends JpaRepository<UserEntity, Integer> {
}
