package de.outfittery.game.of.life.service.impl;

import de.outfittery.game.of.life.entity.Field;
import de.outfittery.game.of.life.enums.PossibleNextFieldState;
import de.outfittery.game.of.life.factory.FieldFactory;
import de.outfittery.game.of.life.rule.NextGenerationRule;
import de.outfittery.game.of.life.rule.Rule;
import de.outfittery.game.of.life.service.FieldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ps689 on 6/17/15.
 */
@Service
public class FieldServiceImpl implements FieldService{

    Map<String, Field> cachedFields = new HashMap<>();

    public Field createNewField(int rows, int columns) {
        Field newField = FieldFactory.createNewField(rows, columns);
        cachedFields.put(newField.getId(), newField);
        return newField;
    }

    public Field createNewRandomField(int rows, int columns) {
        Field newField = FieldFactory.createNewRandomizedField(rows, columns);
        cachedFields.put(newField.getId(), newField);
        return newField;
    }

    public Field addLivingCell(String id, int row, int column) {

        Field field = cachedFields.get(id);
        if (field != null) {
            field.setAliveState(row, column, true);
        }
        return field;
    }

    public Field computeNextGeneration(String id) {

        Field oldGenerationField = cachedFields.get(id);
        if (oldGenerationField != null) {
            Field nextGeneration = oldGenerationField.computeNextGeneration();
            cachedFields.put(nextGeneration.getId(), nextGeneration);
            return nextGeneration;
        }
        return null;
    }
}