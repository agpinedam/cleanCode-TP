package org.mines.address.domain.service;

import org.mines.address.domain.model.BirthCertificate;
import org.mines.address.domain.repository.BirthCertificateRepository;

import java.util.Optional;
import java.util.UUID;

public class BirthCertificateService {

    private final BirthCertificateRepository birthCertificateRepository;

    public BirthCertificateService(BirthCertificateRepository birthCertificateRepository) {
        this.birthCertificateRepository = birthCertificateRepository;
    }

    // Get a birth certificate by person ID
    public Optional<BirthCertificate> getBirthCertificate(UUID personId) {
        return birthCertificateRepository.findByPersonId(personId); // Implement findByPersonId
    }

    // Create a birth certificate
    public BirthCertificate createBirthCertificate(UUID personId, String birthPlace) {
        BirthCertificate birthCertificate = new BirthCertificate(UUID.randomUUID(), personId, birthPlace);
        return birthCertificateRepository.save(birthCertificate); // Implement save method
    }
}
