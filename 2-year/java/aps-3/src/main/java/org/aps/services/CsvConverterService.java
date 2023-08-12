package org.aps.services;


import org.aps.implementations.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;


public class CsvConverterService {

//    String file = "APS3/src/main/java/org/aps/configs/lista-de-especies-ameacas-2020.csv";
     String file = new File("./src/main/java/org/aps/configs/lista-de-especies-ameacas-2020.csv").getAbsolutePath();
    String line = "";

    public List<EndangeredSpecies> csvToJClass() {
        ArrayList<EndangeredSpecies> result = new ArrayList<EndangeredSpecies>();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(file, StandardCharsets.UTF_8));
            boolean isHeader = true;

            while ((line = reader.readLine()) != null) {
                if (this.isEmptyLine(line)) {
                    continue;
                }

                // Assume the first line as a header of CSV
                if (isHeader) {
                    isHeader = false;
                    continue;
                }

                String[] attributes = this.sanitizeBufferedLine(line).split(";");

                Type type = new Type(this.sanitize(attributes[0]).toLowerCase());
                Group group = new Group(this.sanitize(attributes[1]));
                String family = this.sanitize(attributes[2]);
                String species = attributes[3];
                String name = this.sanitize(attributes[4]);
                // store only the main threat category
                String threatCategoryName = this.sanitize(attributes[5].replace("Criticamente em Perigo (CR)/", ""));
                String threatCategoryAcronym = this.sanitize(attributes[6].replace("CR/", ""));

                ArrayList<Biome> biomes = new ArrayList<Biome>();

                for (String attr : attributes[7].split(",")) {
                    Biome biome = new Biome(this.sanitize(attr));

                    biomes.add(biome);
                }

                ArrayList<String> mainThreats = new ArrayList<String>();

                for (String attr : attributes[8].split(",")) {
                    mainThreats.add(this.sanitize(attr));
                }

                boolean protectedAreaPresence = this.convertStrToBool(attributes[9]);
                // this value is incorrect
                boolean pan = this.convertStrToBool(attributes[10]);
                boolean fishingRegulation = this.convertStrToBool(attributes[11]);
                ProtectionLevel protectionLevel = new ProtectionLevel(Integer.parseInt(this.sanitize(attributes[12])));
                boolean countryExclusive =  this.convertStrToBool(attributes[13]);

                ArrayList<State> occurrenceStates = new ArrayList<State>();

                for (String attr : attributes[14].split(",")) {
                    State state = new State(this.sanitize(attr));

                    occurrenceStates.add(state);
                }

                ThreatCategory threatCategory = new ThreatCategory(threatCategoryName, threatCategoryAcronym);
                EndangeredSpecies endangeredSpecies = new EndangeredSpecies(
                        biomes,
                        countryExclusive,
                        family,
                        fishingRegulation,
                        group,
                        mainThreats,
                        name,
                        pan,
                        protectedAreaPresence,
                        protectionLevel,
                        species,
                        threatCategory,
                        type,
                        occurrenceStates
                );

                result.add(endangeredSpecies);
            }
        } catch (Exception e) {
            e.printStackTrace();
            
        }

        return this.removeItemsWithEmptyName(result);
    }

    private boolean convertStrToBool(String str) {
        return str.trim().toLowerCase().equals("sim");
    }

    private String sanitize(String str) {
        return str.trim();
    }

    private String sanitizeBufferedLine(String str) {
        return str
                .replace("; ", ",")
                .replace("\"", "")
                .replace(";;", ";");
    }

    private List<EndangeredSpecies> removeItemsWithEmptyName(List<EndangeredSpecies> endangeredSpecies) {
        ArrayList<EndangeredSpecies> result = new ArrayList<EndangeredSpecies>();

        for (int i = 0; i < endangeredSpecies.size(); i++) {
            EndangeredSpecies current = endangeredSpecies.get(i);

            if (!current.getName().equals("-")) {
                result.add(current);
            }
        }

        return result;
    }

    private boolean isEmptyLine(String line) {
        return line.trim().length() == 0;
    }
}
