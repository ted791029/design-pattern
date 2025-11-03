package com.ted.app;

import com.ted.app.mapObjects.MapObject;
import org.jetbrains.annotations.Nullable;

import static com.ted.app.Game.rand;

public class Map {

    private MapObject[][]  grids;

    public Map(int row, int col) {
        setGrids(new MapObject[row][col]);
    }


    public int[] getEmptyGridIndex(){
        MapObject mapObject;
        if(this == null){
            throw new RuntimeException("地圖未生成");
        }

        int row;
        int col;

        do {
            row = rand.nextInt(this.getGridsRows());
            col = rand.nextInt(this.getGridsCols());
            mapObject = this.getMapObject(row, col);
        }while (mapObject != null);

        return new int[]{row, col};
    }

    public int getGridsRows(){
        MapObject[][]  grids = getGrids();

        if(grids == null){
            throw new RuntimeException("地圖未生成");
        }

        return grids.length;
    }

    public int getGridsCols(){
        MapObject[][]  grids = getGrids();

        if(grids == null){
            throw new RuntimeException("地圖未生成");
        }

        return grids[0].length;
    }

    @Nullable
    public MapObject getMapObject(int row, int col){
        if(isOutSide(row, col)){
            return null;
        }

        MapObject[][]  grids = getGrids();
        return grids[row][col];
    }

    public void insert(int row, int col, MapObject mapObject){
        MapObject[][]  grids = getGrids();
        grids[row][col] = mapObject;
    }

    public boolean isOutSide(int row, int col){
        if(row < 0 || row >= getGridsRows()){
            return true;
        }

        if(col < 0 || col >= getGridsCols()){
            return true;
        }

        return false;
    }

    public void show(){
        MapObject[][]  grids = getGrids();

        for(int i = 0; i < grids.length; i++){

            for(int j = 0; j < grids[0].length; j++){
                MapObject mapObject =  grids[i][j];

                if(mapObject == null){
                    System.out.print("_");
                }else{
                    System.out.print(mapObject.getSymbol());
                }

            }

            System.out.println();
        }
        System.out.println();
    }

    public void remove(int row, int col){
        MapObject[][]  grids = getGrids();
        grids[row][col] = null;
    }

    /*===================================*/

    public MapObject[][] getGrids() {
        return grids;
    }

    public void setGrids(MapObject[][] grids) {
        this.grids = grids;
    }

}
