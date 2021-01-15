package oophomework.memory;


import static oophomework.utils.Constants.ARRAY_IS_EMPTY;
import static oophomework.utils.Constants.STRING_NULL;

public class Memory {

    public String[] memoryCell;

    public Memory() {
        memoryCell = null;
    }

    public String readLast() {
        if (memoryCell != null) {
            return memoryCell[memoryCell.length - 1];
        } else {
            return ARRAY_IS_EMPTY;
        }
    }

    public String removeLast() {
        if (memoryCell != null) {
            return memoryCell[memoryCell.length - 1] = null;
        } else {
            return ARRAY_IS_EMPTY;
        }
    }

    public boolean save(String value) {
        if (value == null ){
            throw new IllegalArgumentException(STRING_NULL);
        } else if (memoryCell == null){
            throw new IllegalArgumentException(ARRAY_IS_EMPTY);
        }
        if (memoryCell[memoryCell.length-1] == null) {
            memoryCell[memoryCell.length - 1] = value;
            return true;
        } else {
            return false;
        }
    }

    public MemoryInfo getMemoryInfo() {
        return new MemoryInfo();
    }

    private class MemoryInfo {
        private final int totalMemory;
        private String occupiedMemory;

        private MemoryInfo() {
            totalMemory = memoryCell.length;
            occupiedMemory = howMemoryOccupied();
        }

        @Override
        public String toString() {
            return "Total memory = " + totalMemory + ';' +
                    " Occupied memory = " + occupiedMemory + ';';
        }


        private String howMemoryOccupied() {
            int tmp = 0;
            int i = 0;
            while (i < memoryCell.length) {
                if (memoryCell[i] == null) {
                    tmp++;
                }
                i++;
            }
            if (tmp == 0) {
                return occupiedMemory = "Occupied memory = 100%";
            } else {
                double percent = 100 - (tmp / (double) memoryCell.length * 100.0);
                return occupiedMemory = "" + percent + '%';
            }

        }
    }
}
