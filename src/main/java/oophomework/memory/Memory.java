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
            totalMemory = getTotalMemory();
            occupiedMemory = getMemoryOccupied();
        }

        @Override
        public String toString() {
            return "Total memory = " + totalMemory + ',' +
                    " Occupied memory = " + occupiedMemory + ';';
        }

        private int getTotalMemory(){
            if (memoryCell == null){
                return 0;
            }else {
                return memoryCell.length;
            }
        }
        private String getMemoryOccupied() {
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
                    return occupiedMemory = "100%";
                } else {
                    double percent = 100 - (tmp / (double) memoryCell.length * 100.0);
                    return occupiedMemory = "" + percent + '%';
                }
            }else {
                return MEMORY_IS_EMPTY;
            }
        }
    }
}
