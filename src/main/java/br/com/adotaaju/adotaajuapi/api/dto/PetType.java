package br.com.adotaaju.adotaajuapi.api.dto;

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
