package org.aps;

import org.aps.implementations.EndangeredSpecies;
import org.aps.repositories.EndangeredSpeciesRepository;
import org.aps.services.CsvConverterService;
import org.aps.views.Gui;

import java.util.List;

public class Main {
    private void populate() {
        long startTime = System.nanoTime();
        System.out.println("STARTED");
        System.out.println(startTime);

        CsvConverterService csvConverterService = new CsvConverterService();
        EndangeredSpeciesRepository endangeredSpeciesRepository = new EndangeredSpeciesRepository();

        List<EndangeredSpecies> endangeredSpecies = csvConverterService.csvToJClass();

        endangeredSpeciesRepository.populate(endangeredSpecies);

        long endTime = System.nanoTime();
        System.out.println("FINISHED");
        System.out.println(endTime);
        System.out.print("Total: " + (endTime - startTime));
    }

    public static void main(String[] args) {
        new Gui().runGUI();
    }

//    public static void main(String[] args) {
//        new Main().populate();
//    }
}