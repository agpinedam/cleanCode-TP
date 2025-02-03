package org.mines.address.web.controller;

import org.mines.address.api.controller.TownApi;
import org.mines.address.api.model.Town;
import org.mines.address.domain.model.Address;
import org.mines.address.port.driving.AddressUseCase;
import org.mines.address.port.driving.TownUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping
public class TownController implements TownApi {
    private TownUseCase townUseCase;
    private AddressUseCase addressUseCase;

    @Autowired
    public TownController(TownUseCase townUseCase, AddressUseCase addressUseCase) {
        this.townUseCase = townUseCase;
        this.addressUseCase = addressUseCase;
    }

    @Override
    public ResponseEntity<org.mines.address.api.model.Town> createTown(org.mines.address.api.model.Town town) {
        org.mines.address.domain.model.Town saved = townUseCase.save(this.map(town));

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path(
                "/{id}").buildAndExpand(saved.id()).toUri();

        return ResponseEntity.created(uri).body(map(saved));
    }

    @Override
    public ResponseEntity<String> deleteTown(String id) {
        townUseCase.remove(UUID.fromString(id));

        return ResponseEntity.ok(id);
    }

    @Override
    public ResponseEntity<org.mines.address.api.model.Town> getTown(String id) {
        UUID uuid = UUID.fromString(id);

        return townUseCase.get(uuid)
                .map(this::map)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Override
    public ResponseEntity<List<org.mines.address.api.model.Town>> listTowns() {
        return ResponseEntity.ok(townUseCase.list().stream().map(this::map).collect(Collectors.toList()));
    }

    @Override
    public ResponseEntity<org.mines.address.api.model.Town> updateTown(org.mines.address.api.model.Town town) {
        org.mines.address.domain.model.Town modelTown = this.map(town);

        if (townUseCase.get(modelTown.id()).isPresent()) {
            return ResponseEntity.ok(this.map(townUseCase.save(modelTown)));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    public ResponseEntity<List<org.mines.address.api.model.Address>> listAddresses(String id) {
        UUID uuid = UUID.fromString(id);

        return ResponseEntity.ok(addressUseCase.findByTown(uuid).stream().map(this::map).collect(Collectors.toList()));
    }

    @Override
    public ResponseEntity<org.mines.address.api.model.Address> createAddresses(String id, org.mines.address.api.model.Address address) {
        UUID townId = UUID.fromString(id);

        Address savedAddress = addressUseCase.save(this.map(townId, address));

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path(
                "/{townId}/address/{id]").buildAndExpand(id, savedAddress.id()).toUri();

        return ResponseEntity.created(uri).body(map(savedAddress));
    }

    // Map from town API to town domain
    private org.mines.address.domain.model.Town map(org.mines.address.api.model.Town town) {
        return org.mines.address.domain.model.Town.TownBuilder.aTown()
                .withId(town.getId() == null ? null : UUID.fromString(town.getId()))
                .withName(town.getName())
                .withPostCode(Integer.parseInt(town.getPostCode()))
                .build();
    }

    // Map from town domain to town API
    private org.mines.address.api.model.Town map(org.mines.address.domain.model.Town town) {
        org.mines.address.api.model.Town apiTown = new org.mines.address.api.model.Town();
        apiTown.setId(town.id().toString());
        apiTown.setName(town.name());
        apiTown.setPostCode(String.valueOf(town.postCode()));

        return apiTown;
    }

    // Map from API address to domain Address
    private org.mines.address.domain.model.Address map(UUID townId, org.mines.address.api.model.Address address) {
        return Address.AddressBuilder.anAddress()
                .withNumber(address.getNumber().intValue())
                .withStreet(address.getStreet())
                .withTown(org.mines.address.domain.model.Town.TownBuilder.aTown().withId(townId).build())
                .build();
    }

    // Map from domain Address to API address
    private org.mines.address.api.model.Address map(org.mines.address.domain.model.Address address) {
        org.mines.address.api.model.Address apiAddress = new org.mines.address.api.model.Address();
        apiAddress.setNumber((long) address.number());
        apiAddress.setStreet(address.street());

        return apiAddress;
    }
}
