/* File Organization

1. Imports
2. GetProcessList -- Generates File of Current running processes
 a. GetProcessListData --- Reads all data using Runtime information
 b. OutProcessListData --- Exports all data gathered using Runtime

 */

// 1. Imports

import java.io.*;
import java.util.StringTokenizer;


// 2. GetProcessList: Gets and exports prrocess information

public class GetProcessList {
// a. Reads data using Runtime
    private String GetProcessListData() {
        Process p;
        Runtime runTime;
        String process = null;
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
            process = "&";
            while (line != null) {
                line = bufferedReader.readLine();
                process += line + "&";
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
// b. Exporting gathered data into a file... probably should be a separate class.
    private void showProcessData() {
        try {

            // Call GetProcessListData to cache information
            String proc = GetProcessListData();

            // Streams for file output.
            // Filepath can be a fullpath, if not the file will be in the pwd.
            OutputStreamWriter outputStreamWriter =
                    new OutputStreamWriter(new FileOutputStream("ProcessList.txt"));
            BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);

            // Separating the output for manipulation
            StringTokenizer st = new StringTokenizer(proc, "&");

            while (st.hasMoreTokens()) {
                bufferedWriter.write(st.nextToken());  // Write current line
                bufferedWriter.newLine();
            }

            // Close output streams.
            bufferedWriter.close();
            outputStreamWriter.close();

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

    }

    public static void main(String[] args) {
        GetProcessList processes = new GetProcessList();
        processes.showProcessData();

    }
}