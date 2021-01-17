package oophomework.memory;


import static oophomework.utils.Constants.*;

public class Memory {

    public String[] memoryCell;

    public Memory() {
        memoryCell = null;
    }

    public String readLast() {
        if (memoryCell != null) {
            return memoryCell[memoryCell.length - 1];
        } else {
            return ARRAY_IS_NULL;
        }
    }

    public String removeLast() {
        if (memoryCell != null) {
            return memoryCell[memoryCell.length - 1] = null;
        } else {
            return ARRAY_IS_NULL;
        }
    }

    public boolean save(String value) {
        if (value == null ){
            throw new IllegalArgumentException(STRING_NULL);
        } else if (memoryCell == null){
            throw new IllegalArgumentException(ARRAY_IS_NULL);
        }
        for (int i = 0; i < memoryCell.length; i++) {
            if (memoryCell[i] == null) {
                memoryCell[i] = value;
                return true;
            }
        }
          return false;
    }
    public int getTotalMemory(){
        return getMemoryInfo().totalMemory;
    }
    public double getOccupiedMemory(){
        return getMemoryInfo().occupiedMemory;
    }
    public MemoryInfo getMemoryInfo() {
        return new MemoryInfo();
    }


    private class MemoryInfo {
        private final int totalMemory;
        private double occupiedMemory;

        private MemoryInfo() {
            totalMemory = getTotalMemory();
            occupiedMemory = getMemoryOccupied();
        }

        @Override
        public String toString() {
            return "Total memory = " + totalMemory + ',' +
                    " Occupied memory = " + occupiedMemory + "%;";
        }

        private int getTotalMemory(){
            if (memoryCell == null){
                return 0;
            }else {
                return memoryCell.length;
            }
        }
        private double getMemoryOccupied() {
            if (memoryCell != null) {
                int tmp = 0;
                int i = 0;
                while (i < memoryCell.length) {
                    if (memoryCell[i] == null) {
                        tmp++;
                    }
                    i++;
                }
                if (tmp == 0) {
                    return occupiedMemory = 100;
                } else {
                    return occupiedMemory  = 100 - (tmp / (double) memoryCell.length * 100.0);
                }
            }else {
               return 0;
            }
        }
    }
}
