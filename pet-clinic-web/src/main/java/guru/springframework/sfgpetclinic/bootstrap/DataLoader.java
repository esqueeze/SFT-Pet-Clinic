package guru.springframework.sfgpetclinic.bootstrap;

import guru.springframework.sfgpetclinic.model.*;
import guru.springframework.sfgpetclinic.services.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;
    private final SpecialityService specialityService;
    private final VisitService visitService;

    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService, SpecialityService specialityService, VisitService visitService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
        this.specialityService = specialityService;
        this.visitService = visitService;
    }

    @Override
    public void run(String... args) throws Exception {

        int count = petTypeService.findAll().size();

        if (count == 0) {
            loadData();
        }
    }

    private void loadData() {
        PetType dog = new PetType();
        dog.setName("Dog");
        PetType savedDogPetType = petTypeService.save(dog);

        PetType cat = new PetType();
        dog.setName("Cat");
        PetType savedCatPetType = petTypeService.save(cat);

        Speciality radiology = new Speciality();
        radiology.setDescription("Radiology");
        Speciality savedRadiology = specialityService.save(radiology);

        Speciality surgery = new Speciality();
        surgery.setDescription("Surgery");
        Speciality savedSurgery = specialityService.save(surgery);

        Speciality dentistry = new Speciality();
        dentistry.setDescription("Dentistry");
        Speciality savedDentistry = specialityService.save(dentistry);

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

        Visit visitCat = new Visit();
        visitCat.setPet(eddysCat);
        visitCat.setDate(LocalDate.now());
        visitCat.setDescription("Poor Cat");

        visitService.save(visitCat);

        System.out.println("Loaded Owners...");

        Vet vet1 = new Vet();
//        vet1.setId(1L);
        vet1.setFirstName("Andy");
        vet1.setLastName("Barker");
        vet1.getSpecialties().add(savedRadiology);

        vetService.save(vet1);

        Vet vet2 = new Vet();
//        vet2.setId(2L);
        vet2.setFirstName("Sarah");
        vet2.setLastName("Barker");
        vet2.getSpecialties().add(savedDentistry);
        vet2.getSpecialties().add(savedSurgery);

        vetService.save(vet2);

        System.out.println("Loaded Vets...");
    }
}
