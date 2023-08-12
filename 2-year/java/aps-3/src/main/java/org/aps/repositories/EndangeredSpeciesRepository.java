package org.aps.repositories;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import org.aps.implementations.*;
import org.aps.services.FirebaseService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class EndangeredSpeciesRepository {
    static final String collection = "endangered_species";
    private final BiomesRepository biomesRepository = new BiomesRepository();
    private final GroupiesRepository groupiesRepository = new GroupiesRepository();
    private final TypesRepository typesRepository = new TypesRepository();
    private final StatesRepository statesRepository = new StatesRepository();
    private final ThreatCategoriesRepository threatCategoriesRepository = new ThreatCategoriesRepository();

    public EndangeredSpeciesRepository() {
        new FirebaseService().run();
    }

    public EndangeredSpecies repositoryMapper(QueryDocumentSnapshot document) {
        System.out.println("repositoryMapper");

        Map<String, Object> data = document.getData();
        EndangeredSpecies endangeredSpecies = new EndangeredSpecies();
        ArrayList<DocumentReference> biomesRef = new ArrayList<DocumentReference>();
        ArrayList<DocumentReference> occurrenceStatesRef = new ArrayList<DocumentReference>();
        DocumentReference groupRef = null;
        DocumentReference protectionLevelRef = null;
        DocumentReference threatCategoryRef = null;
        DocumentReference typeRef = null;

        for (Map.Entry<String, Object> entry : data.entrySet()) {
            switch (entry.getKey()) {
                case "biome":
                    biomesRef = (ArrayList<DocumentReference>) entry.getValue();
                    break;
                case "country_exclusive":
                    endangeredSpecies.setCountryExclusive((boolean) entry.getValue());
                    break;
                case "family":
                    endangeredSpecies.setFamily((String) entry.getValue());
                    break;
                case "fishing_regulation":
                    endangeredSpecies.setFishingRegulation((boolean) entry.getValue());
                    break;
                case "group":
                    groupRef = (DocumentReference) entry.getValue();
                    break;
                case "main_threats":
                    endangeredSpecies.setMainThreats((ArrayList<String>) entry.getValue());
                    break;
                case "name":
                    endangeredSpecies.setName(entry.getValue().toString());
                    break;
                case "occurrence_states":
                    occurrenceStatesRef = (ArrayList<DocumentReference>) entry.getValue();
                    break;
                case "pan":
                    // ignores for now, because problems when populating the Firestore
                    break;
                case "protected_area_presence":
                    endangeredSpecies.setProtectedAreaPresence((boolean) entry.getValue());
                    break;
                case "protection_level":
                    protectionLevelRef = (DocumentReference) entry.getValue();
                    break;
                case "species":
                    endangeredSpecies.setSpecies(entry.getValue().toString());
                    break;
                case "threat_category":
                    threatCategoryRef = (DocumentReference) entry.getValue();
                    break;
                case "type":
                    typeRef = (DocumentReference) entry.getValue();
                    break;
                case "id":
                    endangeredSpecies.setId((String) entry.getValue());
                default:
                    System.out.println("key not mapped EndangeredSpeciesRepository " + entry.getKey());
                    break;
            }
        }

        ArrayList<Biome> biomes = new ArrayList<Biome>();
        ArrayList<State> occurrenceStates = new ArrayList<State>();

        biomesRef.forEach(ref -> biomes.add(biomesRepository.findByRef(ref)));
        Group group = groupiesRepository.findByRef(groupRef);
        occurrenceStatesRef.forEach(ref -> occurrenceStates.add(statesRepository.findByRef(ref)));
        ThreatCategory threatCategory = threatCategoriesRepository.findByRef(threatCategoryRef);
        Type type = typesRepository.findByRef(typeRef);
        // for now, ignores this data structure
        ProtectionLevel protectionLevel = new ProtectionLevel(protectionLevelRef);

        endangeredSpecies.setBiomes(biomes);
        endangeredSpecies.setGroup(group);
        endangeredSpecies.setOccurrenceStates(occurrenceStates);
        endangeredSpecies.setThreatCategory(threatCategory);
        endangeredSpecies.setType(type);
        endangeredSpecies.setProtectionLevel(protectionLevel);

        return endangeredSpecies;
    }

    private List<EndangeredSpecies> injectRefs(List<EndangeredSpecies> endangeredSpeciesList) {
        // Prefers to get type refs once from Firestore instead of when pre-populating each endangered species
        ArrayList<Type> types = new TypesRepository().findAll();
        ArrayList<Biome> biomes = new BiomesRepository().findAll();
        ArrayList<Group> groups = new GroupiesRepository().findAll();
        ArrayList<State> states = new StatesRepository().findAll();
        ArrayList<ProtectionLevel> protectionLevels = new ProtectionLevelsRepository().findAll();
        ArrayList<ThreatCategory> threatCategories = new ThreatCategoriesRepository().findAll();

        for (EndangeredSpecies endangeredSpecies : endangeredSpeciesList) {
            for (Type type : types) {
                if (type.getName().equals(endangeredSpecies.getType().getName())) {
                    // don't repass all attributes to reduce memory usage
                    endangeredSpecies.getType().setRef(type.getRef());
                }
            }

            for (Biome biome : biomes) {
                for (int i = 0; i < endangeredSpecies.getBiomes().size(); i++) {
                    if (biome.getName().equals(endangeredSpecies.getBiomes().get(i).getName())) {
                        // don't repass all attributes to reduce memory usage
                        endangeredSpecies.getBiomes().get(i).setRef(biome.getRef());
                    }
                }
            }

            for (Group group : groups) {
                if (group.getName().equals(endangeredSpecies.getGroup().getName().toLowerCase())) {
                    // don't repass all attributes to reduce memory usage
                    endangeredSpecies.getGroup().setRef(group.getRef());
                }
            }

            for (State state : states) {
                for (int i = 0; i < endangeredSpecies.getOccurrenceStates().size(); i++) {
                    if (state.getUf().toUpperCase().equals(endangeredSpecies.getOccurrenceStates().get(i).getUf().toUpperCase())) {
                        // don't repass all attributes to reduce memory usage
                        endangeredSpecies.getOccurrenceStates().get(i).setRef(state.getRef());
                    }
                }
            }

            for (ProtectionLevel protectionLevel : protectionLevels) {
                if (protectionLevel.getLevel() == endangeredSpecies.getProtectionLevel().getLevel()) {
                    // don't repass all attributes to reduce memory usage
                    endangeredSpecies.getProtectionLevel().setRef(protectionLevel.getRef());
                }
            }

            for (ThreatCategory threatCategory : threatCategories) {
                if (threatCategory.getAcronym().equals(endangeredSpecies.getThreatCategory().getAcronym())) {
                    // don't repass all attributes to reduce memory usage
                    endangeredSpecies.getThreatCategory().setRef(threatCategory.getRef());
                }
            }
        }

        ArrayList<EndangeredSpecies> result = new ArrayList<EndangeredSpecies>();

        for (EndangeredSpecies current : endangeredSpeciesList) {
            boolean someWithoutRef = current.getType().getRef() == null
                    || current.getBiomes().get(0).getRef() == null
                    || current.getGroup().getRef() == null
                    || current.getOccurrenceStates().get(0).getRef() == null
                    || current.getProtectionLevel().getRef() == null
                    || current.getThreatCategory().getRef() == null;

            if (!someWithoutRef) {
                result.add(current);
            } else {
                System.out.println("======ERROR======");
                System.out.println(current.getFamily());
                System.out.println(current.getName());
                System.out.printf(current.getType().getRef() + " <=>" + current.getType().getName() + "\n");
                System.out.printf(current.getBiomes().get(0).getRef() + "<=>" + current.getBiomes().get(0).getName()  + "\n");
                System.out.printf(current.getGroup().getRef() + "<=>" + current.getGroup().getName()  + "\n");
                System.out.printf(current.getOccurrenceStates().get(0).getRef() + "<=>" + current.getOccurrenceStates().get(0).getUf()  + "\n");
                System.out.printf(current.getProtectionLevel().getRef() + "<=>" + current.getProtectionLevel().getLevel()  + "\n");
                System.out.printf(current.getThreatCategory().getRef() + "<=>" + current.getThreatCategory().getAcronym()  + "\n");
            }
        }

        return result;
    }

    private Map<String, Object> storeMapper(EndangeredSpecies endangeredSpecies) {
        Map<String, Object> result = new HashMap<>();

        ArrayList<DocumentReference> biomesRefs = new ArrayList<DocumentReference>();

        for (Biome biome : endangeredSpecies.getBiomes()) {
            biomesRefs.add(biome.getRef());
        }

        ArrayList<DocumentReference> statesRefs = new ArrayList<DocumentReference>();

        for (State state : endangeredSpecies.getOccurrenceStates()) {
            statesRefs.add(state.getRef());
        }

        result.put("biome", biomesRefs);
        result.put("country_exclusive", endangeredSpecies.getCountryExclusive());
        result.put("family", endangeredSpecies.getFamily());
        result.put("fishing_regulation", endangeredSpecies.getFishingRegulation());
        result.put("group", endangeredSpecies.getGroup().getRef());
        result.put("main_threats", endangeredSpecies.getMainThreats());
        result.put("name", endangeredSpecies.getName());
        result.put("occurrence_states", statesRefs);
        result.put("pan", endangeredSpecies.getName());
        result.put("protected_area_presence", endangeredSpecies.getProtectedAreaPresence());
        result.put("protection_level", endangeredSpecies.getProtectionLevel().getRef());
        result.put("species", endangeredSpecies.getSpecies());
        result.put("threat_category", endangeredSpecies.getThreatCategory().getRef());
        result.put("type", endangeredSpecies.getType().getRef());

        return result;
    }

    private List<EndangeredSpecies> removeExistingDocs(List<EndangeredSpecies> docs) {
        // Aren't recommended make Firestore requests in a loop, but for project purposes is perfect
        ArrayList<EndangeredSpecies> result = new ArrayList<EndangeredSpecies>();

        try {
            for (EndangeredSpecies doc : docs) {
                List<QueryDocumentSnapshot> docAlreadyExists = FirebaseService.repository.collection(collection)
                        .whereEqualTo("name", doc.getName())
                        .get()
                        .get()
                        .getDocuments();

                if (docAlreadyExists.size() == 0) {
                    result.add(doc);
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return result;
    }

    private void batchInsert(List<Map<String, Object>> list) {
        try {
            WriteBatch batch = FirebaseService.repository.batch();

            for (Map<String, Object> item : list) {
                DocumentReference ref = FirebaseService.repository.collection("endangered_species").document();

                batch.set(ref, item);
            }

            batch.commit().get();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void populate(List<EndangeredSpecies> endangeredSpecies) {
        try {
            List<EndangeredSpecies> nonRegisteredEndangeredSpecies = this.removeExistingDocs(endangeredSpecies);
            List<EndangeredSpecies> dataWithRefs = this.injectRefs(nonRegisteredEndangeredSpecies);
            ArrayList<Map<String, Object>> parsedData = new ArrayList<Map<String, Object>>();

            for (EndangeredSpecies dataWithRef : dataWithRefs) {
                parsedData.add(this.storeMapper(dataWithRef));
            }

            // Batch insert is limited to 500 operations per commit
            if (parsedData.size() >= 500) {
                // Aren't recommended make DB Firestore requests in a loop, but for project purposes is perfect
             for (int i = 0; i < parsedData.size(); i += 500) {
                 this.batchInsert(parsedData.subList(i, i + 500));
             }
             return;
            }

            this.batchInsert(parsedData);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public ArrayList<EndangeredSpecies> findAll() {
        try {
            ApiFuture<QuerySnapshot> query = FirebaseService.repository.collection(collection).limit(10).get();
            List<QueryDocumentSnapshot> documents = query.get().getDocuments();

            ArrayList<EndangeredSpecies> result = new ArrayList<EndangeredSpecies>();

            for (QueryDocumentSnapshot document : documents) {
                result.add(this.repositoryMapper(document));
            }

            return result;
        } catch (ExecutionException | InterruptedException exception) {
            System.out.println(exception.getMessage());
        }

        return null;
    }

    public ArrayList<EndangeredSpecies> findAllByType(DocumentReference typeRef) {
        try {
            List<QueryDocumentSnapshot> list = FirebaseService.repository.collectionGroup("endangered_species")
                    .whereEqualTo("type", typeRef)
                    .limit(25)
                    .get()
                    .get()
                    .getDocuments();
            ArrayList<EndangeredSpecies> result = new ArrayList<EndangeredSpecies>();

            list.forEach(item -> result.add(this.repositoryMapper(item)));

            return result;
        } catch (ExecutionException | InterruptedException exception) {
            System.out.println(exception.getMessage());
        }

        return null;
    }

    public ArrayList<EndangeredSpecies> findAllByGroup(DocumentReference groupRef) {
        try {
            List<QueryDocumentSnapshot> list = FirebaseService.repository.collectionGroup("endangered_species")
                    .whereEqualTo("group", groupRef)
                    .limit(25)
                    .get()
                    .get()
                    .getDocuments();
            ArrayList<EndangeredSpecies> result = new ArrayList<EndangeredSpecies>();

            list.forEach(item -> result.add(this.repositoryMapper(item)));

            return result;
        } catch (ExecutionException | InterruptedException exception) {
            System.out.println(exception.getMessage());
        }

        return null;
    }

    public ArrayList<EndangeredSpecies> findAllBySpecie(String specie) {
        try {
            List<QueryDocumentSnapshot> list = FirebaseService.repository.collectionGroup("endangered_species")
                    .whereGreaterThanOrEqualTo("species", specie)
                    .whereLessThanOrEqualTo("species", specie + 'z')
                    .limit(25)
                    .get()
                    .get()
                    .getDocuments();
            ArrayList<EndangeredSpecies> result = new ArrayList<EndangeredSpecies>();

            list.forEach(item -> result.add(this.repositoryMapper(item)));

            return result;
        } catch (ExecutionException | InterruptedException exception) {
            System.out.println(exception.getMessage());
        }

        return null;
    }

    public ArrayList<EndangeredSpecies> findAllByName(String name) {
        try {
            List<QueryDocumentSnapshot> list = FirebaseService.repository.collectionGroup("endangered_species")
                    .whereGreaterThanOrEqualTo("name", name)
                    .whereLessThanOrEqualTo("name", name + 'z')
                    .limit(25)
                    .get()
                    .get()
                    .getDocuments();
            ArrayList<EndangeredSpecies> result = new ArrayList<EndangeredSpecies>();

            list.forEach(item -> result.add(this.repositoryMapper(item)));

            return result;
        } catch (ExecutionException | InterruptedException exception) {
            System.out.println(exception.getMessage());
        }

        return null;
    }

    public ArrayList<EndangeredSpecies> findAllByFamily(String family) {
        try {
            List<QueryDocumentSnapshot> list = FirebaseService.repository.collectionGroup("endangered_species")
                    .whereGreaterThanOrEqualTo("family", family)
                    .whereLessThanOrEqualTo("family", family + 'z')
                    .limit(25)
                    .get()
                    .get()
                    .getDocuments();
            ArrayList<EndangeredSpecies> result = new ArrayList<EndangeredSpecies>();

            list.forEach(item -> result.add(this.repositoryMapper(item)));

            return result;
        } catch (ExecutionException | InterruptedException exception) {
            System.out.println(exception.getMessage());
        }

        return null;
    }

    public ArrayList<EndangeredSpecies> findAllByThreatCategory(DocumentReference threatCategoryRef) {
        try {
            List<QueryDocumentSnapshot> list = FirebaseService.repository.collectionGroup("endangered_species")
                    .whereEqualTo("threat_category", threatCategoryRef)
                    .limit(25)
                    .get()
                    .get()
                    .getDocuments();
            ArrayList<EndangeredSpecies> result = new ArrayList<EndangeredSpecies>();

            list.forEach(item -> result.add(this.repositoryMapper(item)));

            return result;
        } catch (ExecutionException | InterruptedException exception) {
            System.out.println(exception.getMessage());
        }

        return null;
    }

    public ArrayList<EndangeredSpecies> findAllByBiome(DocumentReference biomeRef) {
        try {
            List<QueryDocumentSnapshot> list = FirebaseService.repository.collectionGroup("endangered_species")
                    .whereArrayContains("biome", biomeRef)
                    .limit(25)
                    .get()
                    .get()
                    .getDocuments();
            ArrayList<EndangeredSpecies> result = new ArrayList<EndangeredSpecies>();

            list.forEach(item -> result.add(this.repositoryMapper(item)));

            return result;
        } catch (ExecutionException | InterruptedException exception) {
            System.out.println(exception.getMessage());
        }

        return null;
    }

    public ArrayList<EndangeredSpecies> findAllByMainThreats(String mainThreat) {
        try {
            List<QueryDocumentSnapshot> list = FirebaseService.repository.collectionGroup("endangered_species")
                    .whereArrayContains("main_threats", mainThreat)
                    .limit(25)
                    .get()
                    .get()
                    .getDocuments();
            ArrayList<EndangeredSpecies> result = new ArrayList<EndangeredSpecies>();

            list.forEach(item -> result.add(this.repositoryMapper(item)));

            return result;
        } catch (ExecutionException | InterruptedException exception) {
            System.out.println(exception.getMessage());
        }

        return null;
    }

    public ArrayList<EndangeredSpecies> findAllByOccurrenceStates(DocumentReference occurrenceStatesRef) {
        try {
            List<QueryDocumentSnapshot> list = FirebaseService.repository.collectionGroup("endangered_species")
                    .whereArrayContains("occurrence_states", occurrenceStatesRef)
                    .limit(25)
                    .get()
                    .get()
                    .getDocuments();
            ArrayList<EndangeredSpecies> result = new ArrayList<EndangeredSpecies>();

            list.forEach(item -> result.add(this.repositoryMapper(item)));

            return result;
        } catch (ExecutionException | InterruptedException exception) {
            System.out.println(exception.getMessage());
        }

        return null;
    }
}
