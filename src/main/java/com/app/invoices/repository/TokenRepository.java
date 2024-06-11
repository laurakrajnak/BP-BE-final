package com.app.invoices.repository;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

import com.app.invoices.entities.Invoice;
import com.app.invoices.entities.RefreshToken;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TokenRepository extends JpaRepository<RefreshToken, Long> {
    @Query(value = "SELECT rt.* FROM refresh_token rt " +
            "JOIN app_user u on u.id = rt.user_id " +
            "WHERE rt.identifier = :identifier " +
            "AND rt.user_id = :user_id", nativeQuery = true)
    RefreshToken findRefreshTokenByIdentifier(@Param("user_id") Long userId, @Param("identifier") UUID identifier);
    @Query(value = "SELECT i.* FROM invoice i " +
            "JOIN account a on a.id = i.account_id " +
            "WHERE i.id = :id " +
            "AND a.user_id = :user_id", nativeQuery = true)
    Invoice findInvoiceById(@Param("id")Long id, @Param("user_id") Long userId);

    @Override
    default List findAll(Example example) {
        return Collections.emptyList();
    }

    @Override
    default List findAll(Example example, Sort sort) {
        return Collections.emptyList();
    }

    @Override
    default void flush() {
    }
}