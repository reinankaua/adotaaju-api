package br.com.adotaaju.adotaajuapi.domain.pet;

public class PetMapper {

    public static Pet toPet(PetRequest petRequest){
        Pet pet = new Pet();
        pet.setType(petRequest.getType()); 
        pet.setBreed(petRequest.getBreed()); 
        pet.setAge(petRequest.getAge());
        pet.setColor(petRequest.getColor());
        pet.setWeight(petRequest.getWeight());
        return pet;
    }

    public static PetResponse toPetResponse(Pet pet){
        PetResponse petResponse = new PetResponse();
        petResponse.setId(pet.getId());
        petResponse.setType(pet.getType());
        petResponse.setBreed(pet.getBreed());
        petResponse.setAge(pet.getAge());
        petResponse.setWeight(pet.getWeight());
        petResponse.setColor(pet.getColor());
        petResponse.setAdopted(pet.getAdopted());

        return petResponse;
    }
}
