package com.hung.comicapi.repository;

import com.hung.comicapi.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account,Integer> {
    List<Account> findByUsername(String username);
    Optional<Account> findOneByUsername(String username);
//    Account updateAccount(String username);
}
