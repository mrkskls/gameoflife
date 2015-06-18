package de.outfittery.game.of.life.factory;

import de.outfittery.game.of.life.entity.Field;

/**
 * Created by ps689 on 6/17/15.
 */
public class FieldFactory {

    private static final double RANDOM_ALIVE_PERCENTAGE = 0.3;

    public static Field createNewField(int rows, int columns) {
        return new Field(rows, columns);
    }

    public static Field createNewRandomizedField(int rows, int columns) {

        Field newField = new Field(rows, columns);
        randomizeLivingCells(newField);
        return newField;
    }

    /**
     * Returns a new field with the id, rows and columns of the delivered field, the cell values will not be copied
     *
     * @param oldField: the field that should be partially copied
     * @return a new field with the id, rows and columns of the old field
     */
    public static Field createPartialCopy(Field oldField){
        return new Field(oldField.getId(), oldField.getRows(), oldField.getColumns());
    }

    private static void randomizeLivingCells(Field newField) {

        for(int rows = 0; rows < newField.getRows(); rows++){
            for(int columns = 0; columns < newField.getColumns(); columns++){
                boolean isAlive = Math.random() <= RANDOM_ALIVE_PERCENTAGE;
                newField.setAliveState(rows, columns, isAlive);
            }
        }
    }
}
