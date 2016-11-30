/* File Organization

1. Imports
2. GetProcessList -- Generates File of Current running processes
 a. GetProcessListData --- Reads all data using Runtime information
 b. OutProcessListData --- Exports all data gathered using Runtime

 */

// 1. Imports

import java.io.*;
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
                    line = bufferedReader.readLine();
                    String[] temp = line.trim().split("\\s+");
                    pid = temp[0];
                    cpu = temp[1];
                    mem = temp[2];
                    System.out.println(pid + ":" + cpu + ":" + mem);
                    //process += line + "&";
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

    }
}