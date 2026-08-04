package com.group.vehiclerental.repository;

import com.group.vehiclerental.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

   
    List<Customer> findByFullNameContainingIgnoreCaseOrNicContainingIgnoreCase(String name, String nic);

    Optional<Customer> findByEmailIgnoreCase(String email);

    boolean existsByEmailIgnoreCase(String email);

    boolean existsByNic(String nic);

    boolean existsByDrivingLicenceNo(String drivingLicenceNo);

    /** "...AndCustomerIdNot" excludes the record being edited during an update. */
    boolean existsByEmailIgnoreCaseAndCustomerIdNot(String email, Integer customerId);

    boolean existsByNicAndCustomerIdNot(String nic, Integer customerId);

    boolean existsByDrivingLicenceNoAndCustomerIdNot(String drivingLicenceNo, Integer customerId);

    List<Customer> findAllByOrderByCustomerIdDesc();
}
