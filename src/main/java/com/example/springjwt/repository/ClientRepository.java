package com.example.springjwt.repository;

import com.example.springjwt.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
public Optional<Client> findByUserName(final String userName);//parameter name must same with property in Client class
}
