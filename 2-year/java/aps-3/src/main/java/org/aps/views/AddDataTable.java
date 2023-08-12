package org.aps.views;

import org.aps.implementations.Biome;
import org.aps.implementations.EndangeredSpecies;
import org.aps.implementations.State;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;

public class AddDataTable {
    
    public DefaultTableModel addRowToJTable(List<EndangeredSpecies> endangeredSpeciesList) {
        ArrayList<String> columns = new ArrayList<String>();
        ArrayList<String[]> values = new ArrayList<String[]>();

        columns.add("Fauna/Flora");
        columns.add("Grupo");
        columns.add("Familia");
        columns.add("Especie(simplificado)");
        columns.add("Nome comum");
        columns.add("Categoria ameaça");
        columns.add("Bioma");
        columns.add("Principais ameaças");
        columns.add("Estados de Ocorrências");

        for (EndangeredSpecies endangeredSpecies : endangeredSpeciesList) {
            String biomes = "";
            String occurrenceStates = "";

            for (Biome biome : endangeredSpecies.getBiomes()) {
                biomes += (" " + biome.getName());
            }

            for (State occurrenceState : endangeredSpecies.getOccurrenceStates()) {
                occurrenceStates += (" " + occurrenceState.getUf());
            }

            values.add(
                new String[] 
                {
                        endangeredSpecies.getType().getName(),
                        endangeredSpecies.getGroup().getName(),
                        endangeredSpecies.getFamily(),
                        endangeredSpecies.getSpecies(),
                        endangeredSpecies.getName(),
                        endangeredSpecies.getThreatCategory().getName(),
                        biomes,
                        String.join(", ", endangeredSpecies.getMainThreats()),
                        occurrenceStates
                }
                );
        }

        DefaultTableModel tableModel = new DefaultTableModel(values.toArray(new Object[][] {}), columns.toArray());

        return tableModel;
    }

}