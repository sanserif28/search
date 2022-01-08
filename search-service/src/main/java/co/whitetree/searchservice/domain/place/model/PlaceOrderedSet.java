package co.whitetree.searchservice.domain.place.model;

import lombok.Getter;

import java.util.LinkedHashSet;
import java.util.List;

public class PlaceOrderedSet {
    @Getter
    private final LinkedHashSet<Place> places;

    public PlaceOrderedSet(LinkedHashSet<Place> places) {
        this.places = places;
    }

    public PlaceOrderedSet(List<Place> places) {
        this.places = new LinkedHashSet<>(places);
    }

    public static PlaceOrderedSet from(LinkedHashSet<Place> places) {
        return new PlaceOrderedSet(places);
    }

    public static PlaceOrderedSet from(List<Place> places) {
        return new PlaceOrderedSet(places);
    }

    public PlaceOrderedSet orderedIntersection(PlaceOrderedSet anotherPlaces) {
        LinkedHashSet<Place> intersection = new LinkedHashSet<>(places);
        intersection.retainAll(anotherPlaces.getPlaces());
        return PlaceOrderedSet.from(intersection);
    }

    public void orderedUnion(PlaceOrderedSet placeOrderedSet) {
        this.places.addAll(placeOrderedSet.getPlaces());
    }
}
