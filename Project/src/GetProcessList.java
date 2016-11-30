/* File Organization

1. Imports
2. GetProcessList -- Generates File of Current running processes
 a. GetProcessListData --- Reads all data using Runtime information
 b. OutProcessListData --- Exports all data gathered using Runtime

 */

// 1. Imports

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;


// 2. GetProcessList: Gets and exports process information

public class GetProcessList {
// a. Reads data using Runtime
    private String[] GetProcessListData() {
        Process p;
        Runtime runTime;
        String[] process = null;
        try {
            System.out.println("Reading Processes...");

            // Get Runtime environment
            runTime = Runtime.getRuntime();

            // Command executed determines information that is read
            p = runTime.exec("ps -e -o pid,pcpu,pmem");

            // Create Inputstream for Read Processes
            InputStream inputStream = p.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            // Read the processes from system and add & as delimiter for tokenize the output
            String line = bufferedReader.readLine();
            String pid = null;
            String cpu = null;
            String mem = null;
//            process = "&";
            while (line != null) {
                try {
                    line = bufferedReader.readLine();
                    String[] temp = line.trim().split("\\s+");
                    pid = temp[0];
                    cpu = temp[1];
                    mem = temp[2];
                    System.out.println(pid + ":" + cpu + ":" + mem);
                    //process += line + "&";
                } catch (NullPointerException e){
                    System.err.println("null pointer oh noes!");
                }
            }

            // Close the Streams
            bufferedReader.close();
            inputStreamReader.close();
            inputStream.close();

            System.out.println("Reading complete.");
        } catch (IOException e) {
            System.out.println("ERROR: Processes could not be read.");
            e.printStackTrace();
        }
        return process;
    }

    private void printProcesses() {
        String[] proc = GetProcessListData();
        System.out.println(proc);
        return;
    }

    public static void main(String[] args) {
        GetProcessList processes = new GetProcessList();
        processes.printProcesses();
        //processes.insertProcessData();
        try{
            String myDriver = "org.gjt.mm.mysql.Driver";
            String url = "jdbc:mysql://localhost:3306/ProcessData?useSSL=false";
            Class.forName(myDriver);
            Connection conn = DriverManager.getConnection(url,"root","dumb_password");
            Statement st = conn.createStatement();
//            st.executeUpdate("INSERT INTO Pdata VALUES(1, 7, 27)");
//            st.executeUpdate("INSERT INTO Pdata VALUES(2, 49, 27*27)");
            ResultSet set = st.executeQuery("SELECT cpu FROM Pdata WHERE pid=1");
            if(set.next()) {
                float cpu = set.getFloat("cpu");
                System.out.println(cpu);
            }
            st.close();} catch (Exception e){
            System.err.println(e.getMessage());
        }
    }
}