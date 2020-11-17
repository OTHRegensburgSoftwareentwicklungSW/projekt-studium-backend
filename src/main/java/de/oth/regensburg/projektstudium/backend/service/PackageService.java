package de.oth.regensburg.projektstudium.backend.service;

import de.oth.regensburg.projektstudium.backend.entity.Package;

import java.util.List;

public interface PackageService {

    List<Package> findAll();

    Package findOneByIdOrBarcode(String idOrBarcode);

    Package addPackage(Package newPackage);

    Package collectPackage(String idOrBarcode, Long driverId);

    Package deliverPackage(String idOrBarcode);

    Package rescheduleDelivery(String idOrBarcode);

    Package markNotDeliverable(String idOrBarcode);
}
