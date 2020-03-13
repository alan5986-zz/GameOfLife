import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Grid extends JPanel {
    private int size;
    private int cellSize;
    private Cell[][] matrix;
    Random r = new Random();

    public Grid(int size){
        this.size = size;
        cellSize = 10;
        matrix = makeCellArray();
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        //printCellArray(matrix,size,size);
        int row, col;
        for(int x = 0; x<size; x++){
            for(int y = 0; y<size; y++){
                row = x*cellSize;
                col = y*cellSize;

                if(matrix[x][y].getStatus() == 1){
                    g2d.setColor(Color.BLUE);
                }else{
                    g2d.setColor(Color.WHITE);
                }
                g2d.fillRect(row,col,cellSize,cellSize);
            }
        }
        updateCellArray();
    }

    public Cell[][] makeCellArray(){

        int rows = size;
        int cols = size;
        Cell[][] matrix = new Cell[rows][cols];

        //fill the grid
        for(int x = 0; x<rows; x++){
            for(int y=0; y<cols; y++){
                matrix[x][y] = new Cell(r.nextInt(2));
            }
        }
        return matrix;
    }

    public void printCellArray(Cell[][] grid, int rows, int cols){
        for(int x = 0; x<rows; x++){
            for(int y=0; y<cols; y++){
                System.out.print(grid[y][x].getStatus() + "  ");
            }
            System.out.println();
        }
    }

    public void updateCellArray(){
        Cell[][] newArray = makeCellArray();
        for(int x=0; x<size; x++){
            for(int y=0; y<size; y++){
                newArray[x][y].setStatus(matrix[x][y].getStatus());
            }
        }

        int count;
        for(int x = 0; x<size; x++){
            for(int y=0; y<size; y++){
                count = 0;
                int currentStatus = matrix[x][y].getStatus();
                for(int j=-1; j<2; j++){
                    for(int k=-1; k<2; k++){
                        int row = (x+j+size) % size;
                        int col = (y+k+size) % size;
                        count += matrix[col][row].getStatus();
                    }
                }
                count -= currentStatus;
                if(count == 3){
                    newArray[x][y].setStatus(1);
                } else if(count < 2 || count >3){
                    newArray[x][y].setStatus(0);
                }
            }
        }
        matrix = newArray;
    }

}
