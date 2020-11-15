package de.oth.regensburg.projektstudium.backend.service;

import de.oth.regensburg.projektstudium.backend.entity.Driver;
import de.oth.regensburg.projektstudium.backend.entity.Package;
import de.oth.regensburg.projektstudium.backend.entity.enums.PackageStatus;
import de.oth.regensburg.projektstudium.backend.entity.enums.PackageType;
import de.oth.regensburg.projektstudium.backend.exceptions.ForbiddenException;
import de.oth.regensburg.projektstudium.backend.exceptions.NotFoundException;
import de.oth.regensburg.projektstudium.backend.repository.PackageRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PackageServiceImpl implements PackageService {

    private static final Logger log = LoggerFactory.getLogger(PackageServiceImpl.class);
    private final PackageRepository packageRepository;
    private final DriverService driverService;

    public PackageServiceImpl(
            PackageRepository packageRepository,
            DriverService driverService
    ) {
        this.packageRepository = packageRepository;
        this.driverService = driverService;
    }

    @Override
    public List<Package> findAll() {
        return packageRepository.findAll();
    }

    @Override
    public Package findOneByIdOrBarcode(String idOrBarcode) {
        Long id = Long.valueOf(idOrBarcode);
        Optional<Package> resById = packageRepository.findById(id);

        Package pkg = new Package();
        pkg.setBarcode(idOrBarcode);
        Optional<Package> resByBarcode = packageRepository.findOne(Example.of(pkg));

        return resById
                .orElseGet(() -> resByBarcode
                        .orElseThrow(() -> new NotFoundException(Package.class)));
    }

    @Override
    public Package addPackage(Package newPackage) {
        return packageRepository.save(newPackage);
    }

    @Override
    public Package collectPackage(String idOrBarcode, Long driverId) {
        final Package dbPackage = this.findOneByIdOrBarcode(idOrBarcode);
        final Driver driver = this.driverService.findOneById(driverId);

        if (dbPackage.getType() != PackageType.OUTBOUND || dbPackage.getStatus() != PackageStatus.CREATED) {
            throw new ForbiddenException("Package #" + idOrBarcode + " has wrong type or in wrong status: " + dbPackage.getStatus() + " (" + dbPackage.getType().toString().toLowerCase() +
                    "), collect is only allow for outbound packages with CREATED status.");
        }

        dbPackage.setDriver(driver);
        dbPackage.setStatus(PackageStatus.IN_TRANSPORT);
        return packageRepository.save(dbPackage);
    }
}
