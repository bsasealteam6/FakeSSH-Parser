public class RunFakeSSHParser {
    public static void main(String[] args) {
        if (args.length == 0  || args[0].equals("-h") || args[0].equals("--help")) {
            System.out.println("Usage: java RunFakeSSHParser <filename> [<output filename>]");
        }
        else {
            String filename = args[0];
            String outputFilename = args.length > 1 ? args[1] : String.format("%s.csv", filename);
            FakeSSHParser parser = new FakeSSHParser(filename);
            parser.parse();
            if (outputFilename != null) {
                parser.writeToFile(outputFilename);
            }
        }
    }
}
