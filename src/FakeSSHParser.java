import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

public class FakeSSHParser {
    private ArrayList < String > lines;
    private CsvList < SSHTry > sshTries;
    private String outputFilename;
    public FakeSSHParser(String filename, String outputFilename) {
        this.lines = ReadWriteFile.readFile(filename);
        sshTries = new CsvList < SSHTry > ();
        this.outputFilename = outputFilename;
    }

    public boolean parse() throws FileNotFoundException, UnsupportedEncodingException {
        PrintWriter file = new PrintWriter(outputFilename, "UTF-8");
        file.println("Date,Time,IP,Port,Software,Username,Password");
        for (int i = 0; i < lines.size(); i++) {
            String line = lines.get(i);
            if (
                !line.contains("Disconnected") &&
                !line.contains("Connected") &&
                !line.contains("ssh: overflow reading version string") &&
                !line.contains("disconnect") &&
                !line.contains("ssh: no auth passed yet, Password authentication failed") &&
                !line.contains("EOF") &&
                !line.contains("Password authentication failed") &&
                !line.contains("read: connection") &&
                !line.contains("[]") &&
                !line.contains("no auth passed yet") &&
                !line.contains("invalid packet length") &&
                !line.contains("no common algorithm") &&
                !line.contains("connection reset") &&
                !line.contains("no route to host")
            ) {
                ArrayList < String > splitLine = new ArrayList < String > (Arrays.asList(line.split("\\s+")));
                if (splitLine.size() > 4) {
                    System.out.println(line);
                    
                    if (line.contains("-OpenSSH") && splitLine.size() >= 7) {
                        splitLine.set(3, splitLine.get(3) + " " + splitLine.remove(4));
                    }
                    if (splitLine.size() == 5) {
                        splitLine.add("N/A");
                    } else if (splitLine.size() > 6) {
                        String password = splitLine.get(5);

                        while (splitLine.size() > 6) {
                            password += " " + splitLine.remove(6);
                            //			    System.out.println(splitLine.size());
                        }
                        splitLine.set(5, password);
                    }
                    SSHTry sshTry = new SSHTry(splitLine.get(0), splitLine.get(1), splitLine.get(2), splitLine.get(3), splitLine.get(4), splitLine.get(5));
                    sshTries.add(sshTry);
                    file.println(sshTry);
                    //                System.out.printf("Date: %s, Time:%s, IP: %s, Software: %s, Username: %s, Password: %s\n", splitLine.get(0), splitLine.get(1), splitLine.get(2), splitLine.get(3), splitLine.get(4), splitLine.get(5));

                }
            }
        }
        System.out.println(sshTries.size());
        file.close();
        return sshTries.size() != 0;
    }

    public void writeToFile(String outputFilename) {
        ReadWriteFile.writeFile(outputFilename, sshTries);
    }

}