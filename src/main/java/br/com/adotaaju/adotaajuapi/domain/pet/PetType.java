package br.com.adotaaju.adotaajuapi.domain.pet;

public enum PetType {
    CAT("Gato"), DOG("Cachorro");

    private String value;

    private PetType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
