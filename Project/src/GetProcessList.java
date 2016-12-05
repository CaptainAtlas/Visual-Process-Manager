import java.io.*;
import java.sql.*;


public class GetProcessList {

    private String[] GetProcessListData() {
        Process p;
        Runtime runTime;
        DBConnection DBConn = new DBConnection();
        String[] process = null;
        Connection conn = null;
        PreparedStatement st = null;
        Statement drop = null;
        Statement recreate = null;
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
            String tpid = null;
            String tcpu = null;
            String tmem = null;

            //MySQL setup
            //Have to drop the database to ensure nodes are regenerated instead of added.
            conn = DBConn.RecreateTable();

            while (line != null) {
                try {
                    line = bufferedReader.readLine();
                    String[] temp = line.trim().split("\\s+");
                    tpid = temp[0];
                    tcpu = temp[1];
                    tmem = temp[2];
                    //System.out.println(pid + ":" + cpu + ":" + mem);

                    //mySQL Data Insertion

                    String update = "INSERT INTO Pdata(pid,cpu,ram) VALUES (?, ?, ?)";
                    st = conn.prepareStatement(update);
                    st.setString(1, tpid);
                    st.setString(2, tcpu);
                    st.setString(3, tmem);
                    st.executeUpdate();


                } catch (NullPointerException e) {
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
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (st != null) {
                try {
                    st.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(drop != null){
                try{
                    drop.close();
                } catch (SQLException e){
                    e.printStackTrace();
                }
            }
            if(recreate != null){
                try{
                    drop.close();
                } catch (SQLException e){
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return process;
    }

    public void updateProcesses() {
        GetProcessListData();
        return;
    }
}
