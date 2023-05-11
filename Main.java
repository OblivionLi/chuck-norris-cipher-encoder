import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        start(scanner);
    }

    public static void start(Scanner scanner) {

        while (true) {
            System.out.println("Please input operation (encode/decode/exit):");
            String option = scanner.nextLine();

            if (option.equals("exit")) {
                System.out.println("Bye!");
                break;
            }

            switch (option) {
                case "encode":
                    System.out.println("Input string:");
                    String stringToEncode = scanner.nextLine();
                    System.out.println("Encoded string:");
                    encodeString(stringToEncode);
                    break;
                case "decode":
                    System.out.println("Input encoded string:");
                    String stringToDecode = scanner.nextLine();
                    decodeString(stringToDecode);
                    break;
                default:
                    System.out.println("There is no '" + option + "' operation");
                    break;
            }
        }
    }

    public static void encodeString(String message) {
        StringBuilder binaryString = new StringBuilder();
        for (char c : message.toCharArray()) {
            String binary = String.format("%7s", Integer.toBinaryString(c)).replace(' ', '0');
            binaryString.append(binary);
        }

        StringBuilder unaryCode = new StringBuilder();
        int i = 0;
        while (i < binaryString.length()) {
            char c = binaryString.charAt(i);
            int count = 1;
            for (int j = i + 1; j < binaryString.length(); j++) {
                if (binaryString.charAt(j) == c) {
                    count++;
                } else {
                    break;
                }
            }

            unaryCode.append(c == '0' ? "00 " : "0 ");
            unaryCode.append("0".repeat(Math.max(0, count)));
            unaryCode.append(" ");
            i += count;
        }

        System.out.println(unaryCode.toString().trim());
    }

    public static void decodeString(String message) {
        String[] parts = message.split(" ");
        if (isEncodedStringInvalid(parts)) {
            System.out.println("not valid");
            return;
        }

        StringBuilder decodedUnaryCode = new StringBuilder();
        for (int k = 0; k < parts.length; k++) {
            for (int j = k + 1; j< parts.length; j++) {

                if (parts[k].equals("0") && parts[j].length() > 0) {
                    decodedUnaryCode.append("1".repeat(Math.max(0, parts[j].length())));
                    k += 1;
                    break;
                }

                if (parts[k].equals("00") && parts[j].length() > 0) {
                    decodedUnaryCode.append("0".repeat(Math.max(0, parts[j].length())));
                    k += 1;
                    break;
                }

            }
        }

        if (decodedUnaryCode.length() % 7 != 0) {
            System.out.println("not valid");
            return;
        }

        StringBuilder binaryString2 = new StringBuilder();
        int chunkSize = 7;
        for (int j = 0; j < decodedUnaryCode.length(); j += chunkSize) {
            String substring = decodedUnaryCode.substring(j, j + chunkSize);
            binaryString2.append(substring);
            binaryString2.append(" ");
        }

        String[] binaryNumbers = binaryString2.toString().split(" ");
        StringBuilder humanLanguage = new StringBuilder();
        for (String binaryNumber: binaryNumbers) {
            int decimalValue = Integer.parseInt(binaryNumber, 2);
            char c = (char) decimalValue;
            humanLanguage.append(c);
        }

        System.out.println("Decoded string:");
        System.out.println(humanLanguage);
    }

    public static boolean isEncodedStringInvalid(String[] parts) {
        if (parts.length % 2 != 0) {
            return true;
        }

        for (String part: parts) {
            if (!part.matches("0+")) {
                return true;
            }
        }

        return !parts[0].equals("0") && !parts[0].equals("00");
    }
}
