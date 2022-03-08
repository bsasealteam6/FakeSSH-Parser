import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;

public class FakeSSHParser {
    private ArrayList<String> lines;
    private CsvList<SSHTry> sshTries;
    public FakeSSHParser(String filename) {
        this.lines = ReadWriteFile.readFile(filename);
        sshTries = new CsvList<SSHTry>();
    }

    public boolean parse() {
        for (int i = 0; i < lines.size(); i++) {
            String line = lines.get(i);
            if(
                !line.contains("Disconnected")&&
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
            )
            {
                System.out.println(line);
                ArrayList<String> splitLine = new ArrayList<String>(Arrays.asList(line.split("\\s+")));
		if (line.contains("-OpenSSH")){
                        splitLine.set(3, splitLine.get(3) + " " + splitLine.remove(4));
                    }
                    if(splitLine.size() == 5){
                        splitLine.add("N/A");
                    } else if (splitLine.size() > 6){
                        String password = splitLine.get(5);

                        while(splitLine.size() > 6){
                            password += " " + splitLine.remove(6);
//			    System.out.println(splitLine.size());
                    }
                    splitLine.set(5, password);
                sshTries.add(new SSHTry(splitLine.get(0), splitLine.get(1), splitLine.get(2), splitLine.get(3), splitLine.get(4), splitLine.get(5)));
//                System.out.printf("Date: %s, Time:%s, IP: %s, Software: %s, Username: %s, Password: %s\n", splitLine.get(0), splitLine.get(1), splitLine.get(2), splitLine.get(3), splitLine.get(4), splitLine.get(5));
                }
            }
        }
	System.out.println(sshTries.size());
	return sshTries.size() != 0;
    }

    public void writeToFile(String outputFilename) {
        ReadWriteFile.writeFile(outputFilename, sshTries);
    }

}
