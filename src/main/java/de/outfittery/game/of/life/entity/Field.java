package de.outfittery.game.of.life.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import de.outfittery.game.of.life.enums.PossibleNextFieldState;
import de.outfittery.game.of.life.factory.FieldFactory;
import de.outfittery.game.of.life.rule.NextGenerationRule;
import de.outfittery.game.of.life.rule.Rule;

import java.util.UUID;

/**
 * Created by ps689 on 6/17/15. aliveStates
 */
public class Field {

    private boolean[][] aliveStates;

    private String id;

    private int rows;

    private int columns;

    private static Rule[] nextGenerationRules = NextGenerationRule.values();

    public Field(int rows, int columns) {

        this.aliveStates = new boolean[rows][columns];
        this.id = UUID.randomUUID().toString();
        this.rows = rows;
        this.columns = columns;
    }

    public Field(String id, int rows, int columns) {

        this.aliveStates = new boolean[rows][columns];
        this.id = id;
        this.rows = rows;
        this.columns = columns;
    }

    public boolean[][] getAliveStates() {
        return aliveStates;
    }

    public void setAliveStates(boolean[][] aliveStates) {
        this.aliveStates = aliveStates;
    }

    public void setAliveState(int row, int column, boolean alive){
        this.aliveStates[row][column] = alive;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getColumns() {
        return columns;
    }

    public void setColumns(int columns) {
        this.columns = columns;
    }


    public int getAliveNeighbourCount(int row, int column){

        int aliveNeighbourCount = 0;
        for(int i = Math.max(0, row - 1); i <= Math.min(rows-1, row + 1); i++){
            for(int j = Math.max(0, column-1); j <= Math.min(columns-1, column + 1); j++){
                if(!(i == row && j == column) && aliveStates[i][j]){
                    aliveNeighbourCount++;
                }
            }
        }
        return aliveNeighbourCount;
    }

    public boolean isAlive(int row, int column){
        return aliveStates[row][column];
    }

    public Field computeNextGeneration(){

        Field newGenerationField = FieldFactory.createPartialCopy(this);
        for(int row = 0; row < this.getRows(); row++) {
            for (int column = 0; column < this.getColumns(); column++) {
                int aliveNeighbourCount = this.getAliveNeighbourCount(row, column);
                applyRuleSet(row, column, aliveNeighbourCount, newGenerationField);
            }
        }
        return newGenerationField;
    }

    private void applyRuleSet(int row, int column, int aliveNeighbourCount, Field newGenerationField){
        for (Rule nextGenerationRule : nextGenerationRules) {
            PossibleNextFieldState nextFieldState = nextGenerationRule.getNextstate(this, row, column, aliveNeighbourCount);
            if (PossibleNextFieldState.ALIVE.equals(nextFieldState)) {
                newGenerationField.setAliveState(row, column, true);
                break;
            } else if (PossibleNextFieldState.DEAD.equals(nextFieldState)) {
                newGenerationField.setAliveState(row, column, false);
                break;
            }
        }
    }


    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("field<br/><br>");
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < columns; j++){
                if(isAlive(i,j)){
                    stringBuilder.append("o");
                } else {
                    stringBuilder.append("x");
                }
            }
            stringBuilder.append("<br/>");
        }
        stringBuilder.append("<br/>id<br/><br/>").append(id);
        return stringBuilder.toString();

    }
}