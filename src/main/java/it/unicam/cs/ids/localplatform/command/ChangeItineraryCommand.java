package it.unicam.cs.ids.localplatform.command;

import it.unicam.cs.ids.localplatform.model.Itinerary;

/**
 * This class implements the command pattern and is used to change an itinerary.
 */
public class ChangeItineraryCommand implements Command {
    private Itinerary itinerary;
    private String title;

    public ChangeItineraryCommand(Itinerary itinerary, String title) {
        if (itinerary == null || title == null) throw new NullPointerException("Null parameters are not allowed.");

        this.itinerary = itinerary;
        this.title = title;
    }

    @Override
    public void execute() {
        this.itinerary.setTitle(this.title);
    }
}