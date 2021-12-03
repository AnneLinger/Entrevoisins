package com.openclassrooms.entrevoisins.service;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.List;

/**
 * Unit tests on NeighbourDetailsActivity
 */
@RunWith(JUnit4.class)
public class FavoriteNeighbourTest {

    private NeighbourApiService service;

    @Before
    public void setup() {
        service = DI.getNewInstanceApiService();
    }

    //Add a favorite neighbour
    @Test
    public void addAFavoriteNeighbourWithSuccess() {
        //The favorite neighbours list is empty
        List<Neighbour> favoriteNeighbours = service.getNeighbours(true);
        assertTrue(favoriteNeighbours.isEmpty());

        //Add a neighbour to the favorites neighbours list
        Neighbour favoriteNeighbour = service.getNeighbours(false).get(0);
        service.addNeighbour(favoriteNeighbour, true);

        //The favorite neighbours list is no longer empty
        assertFalse(favoriteNeighbours.isEmpty());
    }

    //Remove a favorite Neighbour
    @Test
    public void removeAFavoriteNeighbourWithSuccess() {
        //Add a neighbour to the favorite neighbours list
        Neighbour favoriteNeighbour = service.getNeighbours(false).get(0);
        service.addNeighbour(favoriteNeighbour, true);

        //The favorite neighbours list isn't empty
        List<Neighbour> favoritesNeighbours = service.getNeighbours(true);
        assertFalse(favoritesNeighbours.isEmpty());

        //Remove a neighbour from the favorite neighbours list
        Neighbour neighbourToRemove = service.getNeighbours(true).get(0);
        service.deleteNeighbour(neighbourToRemove, true);

        //The favorite neighbours list is empty now
        assertTrue(favoritesNeighbours.isEmpty());
    }

}
