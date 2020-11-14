package de.oth.regensburg.projektstudium.backend.service;

import de.oth.regensburg.projektstudium.backend.entity.Package;
import de.oth.regensburg.projektstudium.backend.entity.enums.PackageStatus;

import java.util.List;

public interface PackageService {

    List<Package> findAll();

    Package findOneByIdOrBarcode(String idOrBarcode);

    Package addPackage(Package newPackage);

    Package updateStatus(String idOrBarcode, PackageStatus newStatus);
}
