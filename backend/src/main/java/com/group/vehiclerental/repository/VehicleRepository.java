package com.group.vehiclerental.repository;

import com.group.vehiclerental.model.Vehicle;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Integer> {

   
    @Override
    @EntityGraph(attributePaths = "category")
    List<Vehicle> findAll();

    @Override
    @EntityGraph(attributePaths = "category")
    Optional<Vehicle> findById(Integer id);

   
    @EntityGraph(attributePaths = "category")
    List<Vehicle> findByCategory_CategoryId(Integer categoryId);

    @EntityGraph(attributePaths = "category")
    List<Vehicle> findByStatus(String status);

    @EntityGraph(attributePaths = "category")
    List<Vehicle> findByCategory_CategoryIdAndStatus(Integer categoryId, String status);

    @EntityGraph(attributePaths = "category")
    List<Vehicle> findByRegistrationNumberContainingIgnoreCaseOrBrandContainingIgnoreCaseOrModelContainingIgnoreCase(
            String registrationNumber, String brand, String model);

    boolean existsByRegistrationNumber(String registrationNumber);

    boolean existsByRegistrationNumberAndVehicleIdNot(String registrationNumber, Integer vehicleId);

 
    boolean existsByCategory_CategoryId(Integer categoryId);

    long countByCategory_CategoryId(Integer categoryId);

    long countByStatus(String status);
}
