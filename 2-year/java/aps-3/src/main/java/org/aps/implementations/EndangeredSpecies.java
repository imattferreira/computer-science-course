package org.aps.implementations;

import java.util.ArrayList;

public class EndangeredSpecies {
    private String id;
    private ArrayList<Biome> biomes;
    private Boolean countryExclusive;
    private String family;
    private boolean fishingRegulation;
    private Group group;
    private ArrayList<String> mainThreats;
    private String name;
    private boolean pan;
    private boolean protectedAreaPresence;
    private ProtectionLevel protectionLevel;
    private String species;
    private ThreatCategory threatCategory;
    private Type type;
    private ArrayList<State> occurrenceStates;

    public EndangeredSpecies(
            ArrayList<Biome> biomes,
            Boolean countryExclusive,
            String family,
            boolean fishingRegulation,
            Group group,
            ArrayList<String> mainThreats,
            String name,
            boolean pan,
            boolean protectedAreaPresence,
            ProtectionLevel protectionLevel,
            String species,
            ThreatCategory threatCategory,
            Type type,
            ArrayList<State> occurrenceStates
    ) {
        this.biomes = biomes;
        this.countryExclusive = countryExclusive;
        this.family = family;
        this.fishingRegulation = fishingRegulation;
        this.group = group;
        this.mainThreats = mainThreats;
        this.name = name;
        this.pan = pan;
        this.protectionLevel = protectionLevel;
        this.protectedAreaPresence = protectedAreaPresence;
        this.species = species;
        this.threatCategory = threatCategory;
        this.type = type;
        this.occurrenceStates = occurrenceStates;
    }

    public EndangeredSpecies() {}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Biome> getBiomes() {
        return biomes;
    }

    public void setBiomes(ArrayList<Biome> biomes) {
        this.biomes = biomes;
    }

    public ProtectionLevel getProtectionLevel() {
        return protectionLevel;
    }

    public void setProtectionLevel(ProtectionLevel protectionLevel) {
        this.protectionLevel = protectionLevel;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public ArrayList<String> getMainThreats() {
        return mainThreats;
    }

    public void setMainThreats(ArrayList<String> mainThreats) {
        this.mainThreats = mainThreats;
    }

    public ThreatCategory getThreatCategory() {
        return threatCategory;
    }

    public void setThreatCategory(ThreatCategory threatCategory) {
        this.threatCategory = threatCategory;
    }

    public Boolean getCountryExclusive() {
        return countryExclusive;
    }

    public void setCountryExclusive(Boolean countryExclusive) {
        this.countryExclusive = countryExclusive;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public String getFamily() {
        return family;
    }

    public void setFamily(String family) {
        this.family = family;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public ArrayList<State> getOccurrenceStates() {
        return occurrenceStates;
    }

    public void setOccurrenceStates(ArrayList<State> occurrenceStates) {
        this.occurrenceStates = occurrenceStates;
    }

    public boolean getFishingRegulation() { return fishingRegulation; }

    public void setFishingRegulation(boolean fishingRegulation) {
        this.fishingRegulation = fishingRegulation;
    }

    public boolean getPan() { return pan; }

    public void setPan(boolean pan) {
        this.pan = pan;
    }

    public boolean getProtectedAreaPresence() { return protectedAreaPresence; }

    public void setProtectedAreaPresence(boolean protectedAreaPresence) {
        this.protectedAreaPresence = protectedAreaPresence;
    }
}
