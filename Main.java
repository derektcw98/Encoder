//
// The Main class 
//
public class Main {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Please provide a plain text as a command-line argument encased in double-quotes.\n for example: java Main \"HELLO WORLD\"");
            return;
        }

        System.out.println("Please note that this program is CASE-SENSITIVE,\nmeaning any characters provided that is not in the reference table\nwill mapped back to the same character.");

        // referenceTable can be set here to allow for flexibility to use different reference tables,
        // this promotes reusability.
        char[] referenceTable = {
            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',
            'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
            '(', ')', '*', '+', ',', '-', '.', '/'
        };
        
        // instantiating the Encoder class with our referenceTable,
        // then, taking the string argument as the plainText input to be encoded.
        Encoder encoder = new Encoder(referenceTable);
        String plainText = args[0];

        // Encoding of plain text
        String encodedText = encoder.encode(plainText);
        System.out.println("Plain text: " + plainText);
        System.out.println("Encoded text: " + encodedText);

        // Decoding of encoded text
        String decodedText = encoder.decode(encodedText);
        System.out.println("Decoded text: " + decodedText);
    }
}
