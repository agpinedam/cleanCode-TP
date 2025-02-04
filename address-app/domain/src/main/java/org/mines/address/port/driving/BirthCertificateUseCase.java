package org.mines.address.port.driving;

import org.mines.address.domain.model.BirthCertificate;
import java.util.Optional;
import java.util.UUID;

public interface BirthCertificateUseCase {

    BirthCertificate create(UUID personId, String birthPlace);

    Optional<BirthCertificate> get(UUID personId);
}
