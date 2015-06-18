package de.outfittery.game.of.life.rule;

import de.outfittery.game.of.life.entity.Field;
import de.outfittery.game.of.life.enums.PossibleNextFieldState;
import javafx.geometry.Pos;

/**
 * Created by ps689 on 6/17/15.
 */
public enum NextGenerationRule implements Rule {

    UNDER_POPULATION { // alive, living neighbours < 2
        @Override
        public PossibleNextFieldState getNextstate(Field field, int row, int column, int aliveNeighbourCount) {
            return field.isAlive(row, column) && aliveNeighbourCount < 2 ? PossibleNextFieldState.DEAD : PossibleNextFieldState.UNDEFINED;
        }
    },
    NEIGHBOUR_RULE{ // alive, living neighbours = 2 OR 3
        @Override
        public PossibleNextFieldState getNextstate(Field field, int row, int column, int aliveNeighbourCount) {
            return field.isAlive(row, column) && (aliveNeighbourCount == 2 || aliveNeighbourCount == 3) ? PossibleNextFieldState.ALIVE : PossibleNextFieldState.UNDEFINED;
        }
    },
    OVERCROWDING { // alive, living neighbours > 3
        @Override
        public PossibleNextFieldState getNextstate(Field field, int row, int column, int aliveNeighbourCount) {
            return field.isAlive(row, column) && aliveNeighbourCount > 3 ? PossibleNextFieldState.DEAD : PossibleNextFieldState.UNDEFINED;
        }
    },
    REPRODUCTION { // dead, living neighbours = 3
        @Override
        public PossibleNextFieldState getNextstate(Field field, int row, int column, int aliveNeighbourCount) {
            return !field.isAlive(row, column) && aliveNeighbourCount == 3 ? PossibleNextFieldState.ALIVE : PossibleNextFieldState.UNDEFINED;
        }
    }
}