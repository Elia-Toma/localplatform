package it.unicam.cs.ids.localplatform.command;

import it.unicam.cs.ids.localplatform.MunicipalTerritory;
import it.unicam.cs.ids.localplatform.model.POI;

/**
 * This class implements the command pattern and is used to create a POI.
 */
public class CreatePOICommand implements Command {
    private final POI poi;
    private final MunicipalTerritory municipality;

    public CreatePOICommand(POI poi, MunicipalTerritory municipality) {
        if (poi == null || municipality == null) throw new NullPointerException("Null parameters are not allowed.");

        this.poi = poi;
        this.municipality = municipality;
    }

    @Override
    public void execute() {
        this.municipality.addPOI(this.poi);
    }

    @Override
    public String toString() {
        return "CreatePOICommand{" +
                poi +
                '}';
    }
}
