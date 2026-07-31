// placeholder
<<<<<<< HEAD
=======
package com.rento.repository;

import com.rento.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
    boolean existsByLicensePlate(String licensePlate);
    List<Vehicle> findByStatus(String status);
}
>>>>>>> 0101480 (Add Vehicle CRUD implementation)
