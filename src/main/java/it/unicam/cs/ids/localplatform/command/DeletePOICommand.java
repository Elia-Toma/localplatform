package it.unicam.cs.ids.localplatform.command;

import it.unicam.cs.ids.localplatform.MunicipalTerritory;
import it.unicam.cs.ids.localplatform.model.Coordinates;

/**
 * This class implements the command pattern and it is used to delete a POI.
 */
public class DeletePOICommand implements Command {
    private MunicipalTerritory municipality;
    private Coordinates coordinates;

    public DeletePOICommand(MunicipalTerritory municipality, Coordinates coordinates) {
        if (municipality == null || coordinates == null)
            throw new NullPointerException("Null parameters are not allowed.");

        this.municipality = municipality;
        this.coordinates = coordinates;
    }

    @Override
    public void execute() {
        this.municipality.deletePOI(this.coordinates);
    }
}