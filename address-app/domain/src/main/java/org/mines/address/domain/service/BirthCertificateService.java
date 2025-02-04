package org.mines.address.domain.service;

import java.util.Optional;

import org.mines.address.domain.model.BirthCertificate;
import org.mines.address.port.driven.BirthCertificateRepositoryPort;
import org.mines.address.port.driving.BirthCertificateUseCase;

public class BirthCertificateService {
    
    private final BirthCertificateRepositoryPort birthCertificateRepository;
    private final BirthCertificateUseCase birthCertificateUseCase; // Utilisation de BirthCertificateUseCase;

    public BirthCertificateService(BirthCertificateRepositoryPort birthCertificateRepository, BirthCertificateUseCase birthCertificateUseCase) {
        this.birthCertificateRepository = birthCertificateRepository;
        this.birthCertificateUseCase = birthCertificateUseCase;
    }

    public BirthCertificate save( BirthCertificate birthCertificate) {
        // Vérifier si l'acte de naissance existe déjà via BirthCertificateUseCase
        Optional<BirthCertificate> existingBirthCertificate = birthCertificateUseCase.get(birthCertificate.id());

        if (existingBirthCertificate.isPresent()) {
            throw new IllegalArgumentException("Birth certificate already exists with id: " + birthCertificate.id());
        }

        // Insérer l'acte de naissance dans le repository
        return birthCertificateRepository.insert(birthCertificate);
    }
}
