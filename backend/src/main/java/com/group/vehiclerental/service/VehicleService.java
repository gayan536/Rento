// placeholder
<<<<<<< HEAD
=======
package com.rento.service;

import com.rento.model.Vehicle;
import com.rento.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;

    public List<Vehicle> getAllVehicles() {
        return vehicleRepository.findAll();
    }

    public Vehicle getVehicleById(Long id) {
        return vehicleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Vehicle not found with id: " + id));
    }

    public Vehicle createVehicle(Vehicle vehicle) {
        if (vehicleRepository.existsByLicensePlate(vehicle.getLicensePlate())) {
            throw new IllegalArgumentException("License plate already exists");
        }
        return vehicleRepository.save(vehicle);
    }

    public Vehicle updateVehicle(Long id, Vehicle vehicleDetails) {
        Vehicle vehicle = getVehicleById(id);
        vehicle.setMake(vehicleDetails.getMake());
        vehicle.setModel(vehicleDetails.getModel());
        vehicle.setYear(vehicleDetails.getYear());
        vehicle.setDailyRate(vehicleDetails.getDailyRate());
        vehicle.setStatus(vehicleDetails.getStatus());
        vehicle.setCategory(vehicleDetails.getCategory());
        return vehicleRepository.save(vehicle);
    }

    public void deleteVehicle(Long id) {
        Vehicle vehicle = getVehicleById(id);
        vehicleRepository.delete(vehicle);
    }
}
>>>>>>> 0101480 (Add Vehicle CRUD implementation)
