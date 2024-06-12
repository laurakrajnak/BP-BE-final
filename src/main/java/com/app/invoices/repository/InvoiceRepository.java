package com.app.invoices.repository;

import com.app.invoices.entities.Account;
import com.app.invoices.entities.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
    @Query(value = "SELECT i.* FROM invoice i " +
                    "JOIN account a on a.id = i.account_id " +
                    "WHERE i.id = :id " +
                    "AND a.user_id = :user_id", nativeQuery = true)
    Invoice findInvoiceById(@Param("id")Long id, @Param("user_id") Long userId);

    Invoice findInvoiceById(Long id);

    Invoice findInvoiceByIdentifier(String identifier);

    @Query(value = "SELECT COALESCE(MAX(i.serial_number), 0) FROM invoice i WHERE i.account_id = :accountId", nativeQuery = true)
    Long getNextSerialNumber(@Param("accountId") Long accountId);

    @Override
    default void flush() { }

    List<Invoice> findByAccountId(Account accountId);
}
