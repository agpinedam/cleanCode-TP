package org.mines.address.domain.model;

import java.util.UUID;

public record BirthCertificate(UUID id, UUID personId, String birthPlace) {

    public static final class BirthCertificateBuilder {
        private UUID id;
        private UUID personId;
        private String birthPlace;

        private BirthCertificateBuilder() {
        }

        public static BirthCertificateBuilder aBirthCertificate() {
            return new BirthCertificateBuilder();
        }

        public BirthCertificateBuilder withId(UUID id) {
            this.id = id;
            return this;
        }

        public BirthCertificateBuilder withPersonId(UUID personId) {
            this.personId = personId;
            return this;
        }

        public BirthCertificateBuilder withBirthPlace(String birthPlace) {
            this.birthPlace = birthPlace;
            return this;
        }

        public BirthCertificate build() {
            return new BirthCertificate(id, personId, birthPlace);
        }
    }
}
