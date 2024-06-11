package com.app.invoices.repository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import com.app.invoices.entities.User;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    default User findInvoiceById(Long id) {
        return null;
    }

    User findUserById(Long id);

    Optional<User> findByEmail(String email);

    User findUserByEmail(String email);

    @Override
    public default List findAll(Example example) {
        return Collections.emptyList();
    }

    @Override
    public default List findAll(Example example, Sort sort) {
        return Collections.emptyList();
    }

    @Override
    public default void flush() {

    }
}
