package guru.springframework.sfgpetclinic.services.springdatajpa;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.repositories.OwnerRepository;
import guru.springframework.sfgpetclinic.repositories.PetRepository;
import guru.springframework.sfgpetclinic.repositories.PetTypeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OwnerSDJpaServiceTest {

    public static final String LAST_NAME = "Smith";
    public static final long ID = 1L;

    @Mock
    OwnerRepository ownerRepository;

    @Mock
    PetRepository petRepository;

    @Mock
    PetTypeRepository petTypeRepository;

    @InjectMocks
    OwnerSDJpaService service;
    private Owner returnOwner;

    @BeforeEach
    void setUp() {
        returnOwner = Owner.builder().id(ID).lastName(LAST_NAME).build();
    }

    @Test
    void findByLastName() {
        when(ownerRepository.findByLastName(any())).thenReturn(returnOwner);
        Owner smith = service.findByLastName(LAST_NAME);

        assertNotNull(smith);
        assertEquals(LAST_NAME, smith.getLastName());
        verify(ownerRepository).findByLastName(any());
    }

    @Test
    void findAll() {
        Set<Owner> originalOwners = new HashSet<>();
        originalOwners.add(returnOwner);
        when(ownerRepository.findAll()).thenReturn(originalOwners);
        Set<Owner> owners = service.findAll();

        assertEquals(1, owners.size());
    }

    @Test
    void findByIdNotNull() {
        when(ownerRepository.findById(any())).thenReturn(java.util.Optional.ofNullable(returnOwner));

        Owner owner = service.findById(ID);
        assertNotNull(owner);
        assertEquals("Smith", owner.getLastName());
    }

    @Test
    void findByIdNull() {
        when(ownerRepository.findById(any())).thenReturn(java.util.Optional.ofNullable(null));

        Owner owner = service.findById(ID);
        assertNull(owner);
    }

    @Test
    void save() {
        when(ownerRepository.save(any())).thenReturn(returnOwner);

        Owner owner = service.save(returnOwner);

        assertEquals("Smith", owner.getLastName());

        verify(ownerRepository).save(any());
    }

    @Test
    void delete() {
        service.delete(returnOwner);

        verify(ownerRepository).delete(any());
    }

    @Test
    void deleteById() {
        service.deleteById(ID);

        verify(ownerRepository).deleteById(any());
    }
}