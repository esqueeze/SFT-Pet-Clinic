package guru.springframework.sfgpetclinic.bootstrap;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.model.Pet;
import guru.springframework.sfgpetclinic.model.PetType;
import guru.springframework.sfgpetclinic.model.Vet;
import guru.springframework.sfgpetclinic.services.OwnerService;
import guru.springframework.sfgpetclinic.services.PetTypeService;
import guru.springframework.sfgpetclinic.services.VetService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;

    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
    }

    @Override
    public void run(String... args) throws Exception {
        PetType dog = new PetType();
        dog.setName("Dog");
        PetType savedDogPetType = petTypeService.save(dog);

        PetType cat = new PetType();
        dog.setName("Cat");
        PetType savedCatPetType = petTypeService.save(cat);

        Owner owner1 = new Owner();
//        owner1.setId(1L);
        owner1.setFirstName("Tim");
        owner1.setLastName("Barker");
        owner1.setAddress("123 Test Street");
        owner1.setCity("Sunshine West");
        owner1.setTelephone("089999999");

        Pet timPet = new Pet();
        timPet.setPetType(savedDogPetType);
        timPet.setOwner(owner1);
        timPet.setBirthDate(LocalDate.now());
        timPet.setName("Rosco");
        owner1.getPets().add(timPet);

        ownerService.save(owner1);

        Owner owner2 = new Owner();
//        owner2.setId(2L);
        owner2.setFirstName("Eddy");
        owner2.setLastName("Barker");
        owner2.setAddress("123 Test Street");
        owner2.setCity("Sunshine West");
        owner2.setTelephone("089999999");

        Pet eddysCat = new Pet();
        eddysCat.setPetType(savedCatPetType);
        eddysCat.setOwner(owner2);
        eddysCat.setBirthDate(LocalDate.now());
        eddysCat.setName("Scooby");
        owner2.getPets().add(eddysCat);

        ownerService.save(owner2);

        System.out.println("Loaded Owners...");

        Vet vet1 = new Vet();
//        vet1.setId(1L);
        vet1.setFirstName("Andy");
        vet1.setLastName("Barker");

        vetService.save(vet1);

        Vet vet2 = new Vet();
//        vet2.setId(2L);
        vet2.setFirstName("Sarah");
        vet2.setLastName("Barker");

        vetService.save(vet2);

        System.out.println("Loaded Vets...");
    }
}
