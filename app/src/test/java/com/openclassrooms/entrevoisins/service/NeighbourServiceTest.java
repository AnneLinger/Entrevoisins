package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;

import org.hamcrest.collection.IsIterableContainingInAnyOrder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * Unit tests on Neighbour service
 */
@RunWith(JUnit4.class)
public class NeighbourServiceTest {

    private NeighbourApiService service;

    @Before
    public void setup() {
        service = DI.getNewInstanceApiService();
    }

    //Get neighbours list
    @Test
    public void getNeighboursWithSuccess() {
        List<Neighbour> neighbours = service.getNeighbours(false);
        List<Neighbour> expectedNeighbours = DummyNeighbourGenerator.DUMMY_NEIGHBOURS;
        assertThat(neighbours, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedNeighbours.toArray()));
    }

    //Get favorite neighbours list
    @Test
    public void getFavoriteNeighboursWithSuccess() {
        //The favorite neighbours list is empty
        List<Neighbour> favoriteNeighbours = service.getNeighbours(true);
        assertTrue(favoriteNeighbours.isEmpty());

        //Add a neighbour to the favorite neighbours list
        Neighbour favoriteNeighbour = service.getNeighbours(false).get(0);
        service.addNeighbour(favoriteNeighbour, true);

        //The favorite neighbours list is no longer empty
        List<Neighbour> expectedFavoriteNeighbours = service.getNeighbours(true);
        assertFalse(expectedFavoriteNeighbours.isEmpty());
    }

    //Delete a neighbour
    @Test
    public void deleteNeighbourWithSuccess() {
        Neighbour neighbourToDelete = service.getNeighbours(false).get(0);
        service.deleteNeighbour(neighbourToDelete, false);
        assertFalse(service.getNeighbours(false).contains(neighbourToDelete));
    }

    //Add a neighbour
    @Test
    public void addNeighbourWithSuccess() {
        Neighbour neighbourToAdd = new Neighbour(13, "Test", "https://i.pravatar.cc/150?u=a042581f4e29026704d", "Ville Test",
                "+33 6 86 57 90 15", "About me test");
        service.addNeighbour(neighbourToAdd, false);
        assertTrue(service.getNeighbours(false).contains(neighbourToAdd));
    }

}
