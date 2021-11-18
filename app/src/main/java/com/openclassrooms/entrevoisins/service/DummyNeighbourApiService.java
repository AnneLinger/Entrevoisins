package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.model.Neighbour;

import java.util.ArrayList;
import java.util.List;

/**
 * Dummy mock for the Api
 */
public class DummyNeighbourApiService implements  NeighbourApiService {

    private List<Neighbour> neighbours = DummyNeighbourGenerator.generateNeighbours();
    private List<Neighbour> favoritesNeighbours = new ArrayList<>();

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Neighbour> getNeighbours(boolean isFavorite) {
        if(isFavorite){
            return favoritesNeighbours;
        }
        else {
            return neighbours;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteNeighbour(Neighbour neighbour, boolean isFavorite) {
        if (isFavorite){
            favoritesNeighbours.remove(neighbour);
        }
        else {
            neighbours.remove(neighbour);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addNeighbour(Neighbour neighbour, boolean isFavorite) {
        if(isFavorite){
            favoritesNeighbours.add(neighbour);
        }
        else{
            neighbours.add(neighbour);
        }
    }
}
