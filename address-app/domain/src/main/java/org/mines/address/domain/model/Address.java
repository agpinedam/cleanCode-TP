package org.mines.address.domain.model;

import java.util.UUID;

public record Address(UUID id, int number, String street, Town town) {
    public static final class AddressBuilder {
        private UUID id;
        private int number;
        private String street;
        private Town town;

        private AddressBuilder() {
        }

        public static AddressBuilder anAddress() {
            return new AddressBuilder();
        }

        public AddressBuilder withId(UUID id) {
            this.id = id;
            return this;
        }

        public AddressBuilder withNumber(int number) {
            this.number = number;
            return this;
        }

        public AddressBuilder withStreet(String street) {
            this.street = street;
            return this;
        }

        public AddressBuilder withTown(Town town) {
            this.town = town;
            return this;
        }

        public Address build() {
            return new Address(id, number, street, town);
        }
    }
}
