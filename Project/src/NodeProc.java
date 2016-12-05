/**
 * Created by DannyBrill on 11/29/16.
 */

import javax.swing.*;
import java.io.*;
import java.sql.*;
import java.util.StringTokenizer;

public class NodeProc extends JPanel {
    private float pid;
    private float ramUsage;
    private float cpuUsage;
    NodeProc(){
        this.pid = -1;
        this.cpuUsage = -1;
        this.ramUsage = -1;
    }
    NodeProc(float pid){
        this.pid = pid;
        this.cpuUsage = setCpuUsage();
        this.ramUsage = setRamUsage();
    }
    NodeProc(float pid, float ramUsage, float cpuUsage){
        this.pid = pid;
        this.ramUsage = ramUsage;
        this.cpuUsage = cpuUsage;
    }
    public void setPid(float newPid){
        this.pid = newPid;
        return;
    }
    public float getRamUsage(){

        return this.ramUsage;
    }
    public float getCpuUsage(){
        return this.cpuUsage;
    }
    public float setRamUsage(){
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;

        try {
            String myDriver = "com.mysql.jdbc.Driver";
            String url = "jdbc:mysql://localhost:3306/ProcessData?useSSL=false";
            Class.forName(myDriver);
            conn = DriverManager.getConnection(url, "root", "dumb_password");
            String query = "SELECT * FROM Pdata WHERE pid = " + this.pid;
            st = conn.createStatement();
            rs = st.executeQuery(query);
            while(rs.next()){
                this.ramUsage = rs.getFloat("ram");
            }
            conn.close();
            st.close();
            rs.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally{
            if(conn != null){
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if( st != null){
                try {
                    st.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(rs != null){
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return this.ramUsage;
    }
    public float setCpuUsage(){
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;

        try {
            String myDriver = "com.mysql.jdbc.Driver";
            String url = "jdbc:mysql://localhost:3306/ProcessData?useSSL=false";
            Class.forName(myDriver);
            conn = DriverManager.getConnection(url, "root", "dumb_password");
            String query = "SELECT * FROM Pdata WHERE pid = " + this.pid;
            st = conn.createStatement();
            rs = st.executeQuery(query);
            while(rs.next()){
                this.cpuUsage = rs.getFloat("cpu");
            }
            conn.close();
            st.close();
            rs.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally{
            if(conn != null){
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if( st != null){
                try {
                    st.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(rs != null){
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return this.cpuUsage;
    }
    public float getPid(){
        return this.pid;
    }
}
