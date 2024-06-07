package com.app.invoices.repository;

import com.app.invoices.entities.Address;

import java.util.Collections;
import java.util.List;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AddressRepository extends JpaRepository<Address, Long> {
    default Address findInvoiceById(Long id) {
        return null;
    }

    @Query(value = "SELECT ad.* FROM address ad " +
            "JOIN account a on a.id = ad.account_id " +
            "WHERE ad.id = :id " +
            "AND a.user_id = :user_id", nativeQuery = true)
    Address findAddressById(@Param("id")Long id, @Param("user_id") Long userId);

    Address findAddressByIdentifier(String identifier);
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
