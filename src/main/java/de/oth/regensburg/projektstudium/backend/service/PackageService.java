package de.oth.regensburg.projektstudium.backend.service;

import de.oth.regensburg.projektstudium.backend.entity.Package;

import java.util.List;

public interface PackageService {

    List<Package> findAll();

    Package findOneByIdOrBarcode(String idOrBarcode);

    Package addOrUpdatePackage(Package newPackage);

    List<Package> saveAll(Iterable<Package> packages);
}
