import java.net.InetAddress;
import java.net.UnknownHostException;

public class SSHTry {
    private String username;
    private String password;
    private String software;
    private InetAddress ipAddress;
    private String date;
    private String time;
    private int port;

    public SSHTry(
        String date, 
        String time, 
        String ipAddress, 
        String software, 
        String username, 
        String password
    ) {
        String[] ipSplit = ipAddress.split(":");
        try {
            this.ipAddress = InetAddress.getByName(ipSplit[0]);
        } catch (UnknownHostException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        this.port = Integer.parseInt(ipSplit[1]);
        this.username = username;
        this.password = password;
        this.software = software;
        this.date = date;
        this.time = time;
    }
    public String getUsername(){
        return username;
    }
    public String getPassword(){
        return password;
    }
    public String getSoftware(){
        return software;
    }
    public InetAddress getIPAddress(){
        return ipAddress;
    }
    public String getDate(){
        return date;
    }
    public String getTime(){
        return time;
    }
    public int getPort(){
        return port;
    }
    public String toString()
	{
		return String.format("\"%s\",\"%s\",\"%s\",\"%s\",\"%s\",\"%s\",\"%s\"", date, time, ipAddress.toString().replace("/", ""), port, software, username, password);
	}

}
