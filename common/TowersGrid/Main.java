package TowersGrid;

import geek.lib.Pair;

import java.util.ArrayList;

public class Main {
    public static void main (String[] args) {
        Pair<Integer,Integer> GridSize = new Pair<Integer, Integer>(5,5);
        Pair<Integer,Integer> Coord = new Pair<Integer, Integer>(2,3);
        
//        Main.Test.test_getValueAtCoord(GridSize, Coord);
        Main.Test.test_gridValues(5);
    }
    
    public static class Test {
        public static void test_getValueAtCoord (Pair<Integer, Integer> Grid, Pair<Integer, Integer> Coords) {
            System.out.println(Main.getValueatCoords(Coords, Grid));
        }
        
        public static void test_gridValues (int gridLength) {
            ArrayList<ArrayList<Integer>> grid = new ArrayList<ArrayList<Integer>>();
            for (int i=gridLength; i>0; i--) {
                ArrayList<Integer> row = new ArrayList<Integer>();
                for (int k=1; k<=gridLength; k++) {
                    System.out.println(String.format("%s,%s", i, k));
                    row.add(Main.getValueatCoords(new Pair<Integer, Integer>(k,i), new Pair<Integer, Integer>(gridLength, gridLength)));
                }
                grid.add(row);
            }
            drawGrid(grid);
        }
        
        public static <T> void drawGrid (ArrayList<ArrayList<T>> grid) {
            for (ArrayList<T> list : grid) {
                System.out.print("|");
                for (T t : list) {
                    System.out.print(t + "|");
                }
                System.out.println("");
            }
        }
    }
    
    /**
     * 
     * Converts Excel Coordinates (Down, Right) into Cartesian Coordinates (Right, Up)
     * 
     * @param XL ->Excel Coordinates to convert [pair(X,Y)]
     * @param Grid ->Grid Size [pair(L,W)]
     * @return ->XL(X,Y) => CT(Y,L+1-X)
     */
    public static Pair<Integer,Integer> convertXLtoCT (Pair<Integer,Integer> XL, Pair<Integer,Integer> Grid) {
        Pair<Integer,Integer> CT = 
                new Pair<Integer, Integer>
                (XL.getTwo(), Grid.getOne() - XL.getOne() + 1);
        return CT;
    }
    
    /**
     * 
     * Gets the Diagonal Row that contains the specified Cartesian Coordinates
     * 
     * @param CT ->Cartesian Coordinates to Locate [pair(X,Y)]
     * @return ->CT(X,Y) =>int(X+Y-1)
     */
    public static int getDiagonalRow (Pair<Integer,Integer> CT) {
        return CT.getOne() + CT.getTwo() - 1;
    }
    
    /**
     * 
     * Finds the value of the first element in the specified Diagonal Row
     * 
     * @param DiagonalRow -> The Diagonal Row for which to find the first element [int(X)]
     * @param GridSize -> Size of Grid in which to search for First Element of Diagonal Row [pair(L,W)]
     * @return -> The First Element of the specified Diagonal Row using the Medhi Sequence Pattern Algorithm (Swag Integration) [int(X)]
     */
    public static int getFirstElementInRow (int DiagonalRow, Pair<Integer,Integer> GridSize) {
        int c = 1;
        for (int i = 1; i <= GridSize.getOne()+1; i++) {
            if (DiagonalRow >= i) {
                c += i-1;
            } else {
                break;
            }
        }
        for (int i=GridSize.getOne()+2; i < GridSize.getOne() *2; i++) {
            if (DiagonalRow >= i) {
                c+= GridSize.getOne() *2 - i +1;
            } else {
                break;
            }
        }
        return c;
    }
    
    /**
     * 
     * Finds the value of the first coordinate in the specified Diagonal Row
     * 
     * @param DiagonalRow -> The Diagonal Row for which to find the first coordinate [int(X)]
     * @param GridSize -> Size of Grid in which to search for First Coordinate of Diagonal Row [pair(L,W)]
     * @return -> The First Coordinate of the specified Diagonal Row using the Medhi Sequence Pattern Algorithm (Yolo Differentiation) [pair(X,Y)]
     */
    public static Pair<Integer, Integer> getFirstCoordInRow (int DiagonalRow, Pair<Integer,Integer> GridSize) {
        Pair<Integer,Integer> CT = new Pair<Integer, Integer>(1,0);
        for (int i=1; i<=GridSize.getOne(); i++) {
            if (DiagonalRow >= i) {
                CT.two++;
            } else {
                break;
            }
        }
        for (int i=GridSize.getOne()+1; i<GridSize.getOne()*2; i++) {
            if (DiagonalRow >= i) {
                CT.one++;
            } else {
                break;
            }
        }
        return CT;
    }
    
    /**
     * 
     * Find the Vector that describes the Translation from the first element in the Diagonal Row to the specified coordinates
     * 
     * @param CT -> Cartesian Coordinates to find Translation Vector To
     * @param GridSize -> Size of Grid Environment
     * @return
     */
    public static Pair<Integer,Integer> findTranslationVector (Pair<Integer,Integer> CT, Pair<Integer,Integer> GridSize) {
        int row = Main.getDiagonalRow(CT);
        Pair<Integer,Integer> firstCoord = Main.getFirstCoordInRow(row, GridSize);
        int coord = CT.one - firstCoord.one;
        return new Pair<Integer, Integer>(coord, 0-coord);
    }
    
    /**
     * 
     * Find the Value of the element at the specified Cartesian Coordinates
     * 
     * @param CT -> Cartesian Coordinates to find the Value of
     * @param GridSize -> Size of Grid Environment
     * @return
     */
    public static int getValueatCoords (Pair<Integer, Integer> CT, Pair<Integer, Integer> GridSize) {
        int firstElem = Main.getFirstElementInRow(Main.getDiagonalRow(CT), GridSize);
        int transVectInt = Main.findTranslationVector(CT, GridSize).one;
        return firstElem + transVectInt;
        
    }
}
