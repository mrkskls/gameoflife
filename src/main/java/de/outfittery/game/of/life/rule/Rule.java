package de.outfittery.game.of.life.rule;

import de.outfittery.game.of.life.entity.Field;
import de.outfittery.game.of.life.enums.PossibleNextFieldState;

/**
 * Created by ps689 on 6/17/15.
 */
public interface Rule {

    PossibleNextFieldState getNextstate(Field field, int row, int column, int aliveNeighbourCount);
}
