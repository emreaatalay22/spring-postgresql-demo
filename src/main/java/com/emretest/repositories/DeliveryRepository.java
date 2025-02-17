package com.emretest.repositories;

import com.emretest.entities.Delivery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface DeliveryRepository extends JpaRepository<Delivery, Long> {

    @Transactional(readOnly = true)
    @Query("SELECT d FROM Delivery d WHERE d.deliveryStatus = :deliveryStatus AND d.customerId = :customerId")
    Page<Delivery> findDeliveriesByStatusCustomerAndDate(String deliveryStatus, Integer customerId, Pageable pageable);
}
