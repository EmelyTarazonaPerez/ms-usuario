package com.example.plaza_de_comidas.adapters.driven.jpa.msql.repository;

import com.example.plaza_de_comidas.adapters.driven.jpa.msql.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUserRepositoryJPA extends JpaRepository<UserEntity, Integer> {
    Optional<UserEntity> findByGmail(String gmail);
}
