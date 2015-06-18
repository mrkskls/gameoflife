package de.outfittery.game.of.life.service;

import de.outfittery.game.of.life.entity.Field;
import org.springframework.stereotype.Service;

/**
 * Created by ps689 on 6/17/15.
 */
public interface FieldService {

    Field createNewField(int rows, int columns);

    Field createNewRandomField(int rows, int columns);

    Field addLivingCell(String id, int row, int column);

    Field computeNextGeneration(String id);
}
