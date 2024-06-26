package br.com.adotaaju.adotaajuapi.domain.service;

import br.com.adotaaju.adotaajuapi.api.dto.TutorDTO;
import br.com.adotaaju.adotaajuapi.core.exception.ResourceNotFoundException;
import br.com.adotaaju.adotaajuapi.domain.entity.Adoption;
import br.com.adotaaju.adotaajuapi.domain.entity.Pet;
import br.com.adotaaju.adotaajuapi.domain.entity.Tutor;
import br.com.adotaaju.adotaajuapi.domain.repository.AdoptionRepository;
import br.com.adotaaju.adotaajuapi.domain.repository.PetRepository;
import br.com.adotaaju.adotaajuapi.domain.repository.TutorRepository;
import lombok.RequiredArgsConstructor;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class TutorService {

    @Autowired
    private TutorRepository tutorRepository;

    @Autowired
    private PetRepository petRepository;

    @Autowired
    private AdoptionRepository adoptionRepository;

    public TutorDTO.Response save(TutorDTO.Request tutorDTO) throws IOException {
        var tutor = mapToEntity(tutorDTO);
        var savedTutor = tutorRepository.save(tutor);
        return mapToResponse(savedTutor);
    }

    public TutorDTO.Response update(Long id, TutorDTO.Request tutorDTO) throws Exception {

        var existingTutorOptional = tutorRepository.findById(id);

        if (existingTutorOptional.isEmpty()) {
            throw new Exception("Id não encontrado na base de dados.");
        }

        var existingTutor = existingTutorOptional.get();

        mapToEntity(tutorDTO, existingTutor);
        var updatedTutor = tutorRepository.save(existingTutor);
        return mapToResponse(updatedTutor);
    }

    public void deleteById(long id) {
        tutorRepository.deleteById(id);
    }

    public boolean existsById(long id) {
        return tutorRepository.existsById(id);
    }

    public TutorDTO.Response findById(Long id) throws Exception {
        var tutor = tutorRepository.findById(id).orElseThrow(() -> new Exception("Tutor não encontrado"));
        return mapToResponse(tutor);
    }

    public TutorDTO.Response findByCpf(String cpf) throws Exception {
        var tutor = tutorRepository.findByCpf(cpf).orElseThrow(() -> new Exception("CPF não encontrado"));
        return mapToResponse(tutor);
    }

    public void adoptPet(Long tutorId, Long petId) throws Exception {
        var tutor = tutorRepository.findById(tutorId).orElseThrow(() -> new Exception("Tutor não encontrado"));
        var pet = petRepository.findById(petId).orElseThrow(() -> new Exception("Pet não encontrado"));

        if (pet.getFlAdopted()) {
            throw new Exception("Pet já adotado");
        }

        var adoption = new Adoption();
        adoption.setTutor(tutor);
        adoption.setPet(pet);
        adoption.setCreatedAt(LocalDateTime.now());
        adoptionRepository.save(adoption);

        pet.setFlAdopted(true);
        petRepository.save(pet);
    }

    private Tutor mapToEntity(TutorDTO.Request tutorDTO) throws IOException {
        String base64Image = Base64.getEncoder().encodeToString(tutorDTO.getImage().getBytes());

        Tutor tutor = new Tutor();
        tutor.setCpf(tutorDTO.getCpf());
        tutor.setName(tutorDTO.getName());
        tutor.setAge(tutorDTO.getAge());
        tutor.setAddress(tutorDTO.getAddress());
        tutor.setPhone(tutorDTO.getPhone());
        tutor.setEmail(tutorDTO.getEmail());
        tutor.setFlAlreadyAdopted(tutorDTO.getFlAlreadyAdopted());
        tutor.setImageBase64(base64Image);
        return tutor;
    }

    private void mapToEntity(TutorDTO.Request tutorDTO, Tutor tutor) throws IOException {
        String base64Image = Base64.getEncoder().encodeToString(tutorDTO.getImage().getBytes());
        
        if (tutorDTO.getCpf() != null) {
            tutor.setCpf(tutorDTO.getCpf());
        }
        if (tutorDTO.getName() != null) {
            tutor.setName(tutorDTO.getName());
        }
        if (tutorDTO.getAge() != null) {
            tutor.setAge(tutorDTO.getAge());
        }
        if (tutorDTO.getAddress() != null) {
            tutor.setAddress(tutorDTO.getAddress());
        }
        if (tutorDTO.getPhone() != null) {
            tutor.setPhone(tutorDTO.getPhone());
        }
        if (tutorDTO.getEmail() != null) {
            tutor.setEmail(tutorDTO.getEmail());
        }
        if (tutorDTO.getFlAlreadyAdopted() != null) {
            tutor.setFlAlreadyAdopted(tutorDTO.getFlAlreadyAdopted());
        }
        if (tutorDTO.getImage() != null) {
            tutor.setImageBase64(base64Image);
        }  
    }

    private TutorDTO.Response mapToResponse(Tutor tutor) {
        TutorDTO.Response responseDTO = new TutorDTO.Response();
        responseDTO.setId(tutor.getId());
        responseDTO.setCpf(tutor.getCpf());
        responseDTO.setName(tutor.getName());
        responseDTO.setAge(tutor.getAge());
        responseDTO.setAddress(tutor.getAddress());
        responseDTO.setPhone(tutor.getPhone());
        responseDTO.setEmail(tutor.getEmail());
        responseDTO.setFlAlreadyAdopted(tutor.getFlAlreadyAdopted());
        return responseDTO;
    }

    public boolean isPetAdopted(Long petId) {
        Pet pet = petRepository.findById(petId).orElseThrow(() -> new ResourceNotFoundException("Pet not found"));
        return pet.getFlAdopted();
    }
}
