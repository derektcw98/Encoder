//
// The Encoder class 
//
public class Encoder {
    // referenceTable and shiftedTable are maintained as instance variables,
    // allowing them to be accessed by multiple methods within the class.
    // This promotes encapsulation and avoids global variables.

    private char[] referenceTable;
    private char[] shiftedTable;

    public Encoder(char[] referenceTable) {
        this.referenceTable = referenceTable;
        logTable("Reference Table", referenceTable);
    }

    // encode method encapsulates the encoding logic which helps to organize the code, 
    // and keeps the implementation details hidden from the Main class.
    public String encode(String plainText) {
        if (plainText.isEmpty()) {
            return "";
        }

        char offset = plainText.charAt(0);
        int offsetIndex = findIndex(offset, referenceTable);

        shiftedTable = shiftReferenceTable(offsetIndex);
        logTable("Shifted Table", shiftedTable);

        StringBuilder encodedText = new StringBuilder();

        for (char c : plainText.toCharArray()) {
            int index = findIndex(c, referenceTable);
            if (index != -1) {
                encodedText.append(shiftedTable[index]);
            } else {
                encodedText.append(c);
            }
        }

        return encodedText.toString();
    }

    // decode method encapsulates the decoding logic which helps to organize the code, 
    // and keeps the implementation details hidden from the Main class.
    public String decode(String encodedText) {
        if (encodedText.isEmpty()) {
            return "";
        }
    
        char offset = encodedText.charAt(0);
        int shiftedOffsetIndex = findIndex(offset, shiftedTable);
        int referenceTableLength = referenceTable.length;
        
        StringBuilder plainText = new StringBuilder();
    
        for (char c : encodedText.toCharArray()) {
            int index = findIndex(c, referenceTable);
            if (index != -1) {
                int referenceIndex = (index + shiftedOffsetIndex) % referenceTableLength;
                if (referenceIndex < 0) {
                    referenceIndex += referenceTableLength;
                }
                plainText.append(referenceTable[referenceIndex]);
            } else {
                plainText.append(c);
            }
        }
    
        return plainText.toString();
    }
    
    // shiftReferenceTable method is private, encapsulating the internal workings of the Encoder class.
    // This keeps it inaccessible from outside the class, ensuring data encapsulation and information hiding.
    private char[] shiftReferenceTable(int offset) {
        char[] shiftedTable = new char[referenceTable.length];
        int length = referenceTable.length;
    
        for (int i = 0; i < length; i++) {
            int shiftedIndex = (i - offset + length) % length;
            if (shiftedIndex < 0) {
                shiftedIndex += length;
            }
            shiftedTable[i] = referenceTable[shiftedIndex];
        }
    
        return shiftedTable;
    }
    
    // findIndex method is private, encapsulating the internal workings of the Encoder class.
    // This keeps it inaccessible from outside the class, ensuring data encapsulation and information hiding.
    private int findIndex(char c, char[] table) {
        for (int i = 0; i < table.length; i++) {
            if (table[i] == c) {
                return i;
            }
        }
        return -1;
    }
    
    // logTable method is private, encapsulating the internal workings of the Encoder class.
    // This keeps it inaccessible from outside the class, ensuring data encapsulation and information hiding.
    private void logTable(String tableName, char[] table) {
        System.out.println(tableName + ":");
        for (int i = 0; i < table.length; i++) {
            System.out.print(table[i] + " ");
            if ((i + 1) % 10 == 0) {
                System.out.println();
            }
        }
        System.out.println("\n");
    }

}
