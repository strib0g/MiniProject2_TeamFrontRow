/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DB;

import java.sql.*;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import librarysystem_dit092.Tools;
import librarysystem_dit092.Tools.Table;




/**
 *
 * @author majeddalain
 */
public class Go {
    private static String url = "jdbc:mysql://leia.skip.chalmers.se:3306/team1?autoReconnect=true&useSSL=false";
    private static String username = "teamone";
    private static String password = "HSaaD5vtp3K6QERq";
    private static Connection conn;
    
    public static Connection setConnection(){
        try {
            conn = DriverManager.getConnection(url, username, password);
            return conn;
            
        } catch (SQLException ex) {
            Tools.msgBox(ex.getMessage());
            return null;
        }
        
    }
    
    
    
    public static void closeConnectin(){
        try {
            conn.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
      public static boolean runNonQuery(String sqlStatement){
        try {
            setConnection();
            Statement stmt = conn.createStatement();
            stmt.execute(sqlStatement);
            conn.close();
            return true;
        } catch (SQLException ex) {
            Tools.msgBox(ex.getMessage());
            return false;
        }
        
    }
      
      public static boolean runQuery(String sqlStatement){
        try {
            setConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs= stmt.executeQuery(sqlStatement);
            if(rs.next()){
                conn.close();
                return true;
            }
             conn.close();
            return false;
            
            
        } catch (SQLException ex) {
            Tools.msgBox(ex.getMessage());
            return false;   
        } 
    }
      
      public static void fillToJTable (String tableNameOrSelectQuery, JTable table){
          try {
              setConnection();
              Statement stmt = conn.createStatement();
              ResultSet rs;
              String strSelect;
              
              if("select ".equals(tableNameOrSelectQuery.substring(0, 7).toLowerCase())){
                  strSelect = tableNameOrSelectQuery;
              }
              else{
                  strSelect = "select * from "+ tableNameOrSelectQuery;
              }
             rs = stmt.executeQuery(strSelect);
             
             ResultSetMetaData rsmd = rs.getMetaData();
             int c = rsmd.getColumnCount();
             
              DefaultTableModel model = (DefaultTableModel) table.getModel();
              
              Vector row = new Vector();
              model.setRowCount(0);
              
              while(rs.next()){
                  row  = new Vector(c);
                  for(int i=1;i<=c;i++){
                      row.add(rs.getString(i));
                  }
                  model.addRow(row);
              }
              if(table.getColumnCount()!= c){
                  Tools.msgBox("JTable columns count not equal to Query table columns count ");
              }
              closeConnectin();
             
          } catch (SQLException ex) {
              Tools.msgBox(ex.getMessage());
          }
      }
      
      
      public static Table getTableData(String statement){
          Tools t = new Tools();
          try {
              setConnection();
              Statement stmt = conn.createStatement();
              
              ResultSet rs; 
              rs = stmt.executeQuery(statement);
              
              ResultSetMetaData rsmd = rs.getMetaData();
              int c = rsmd.getColumnCount();
              
              Table table = t.new Table(c);
              
              while(rs.next()){
              Object row[] = new Object[c];
              for(int i=0;i<c;i++){
                  row[i]= rs.getString(i+1);
              }
              table.addNewRow(row);
          }
              closeConnectin();
              return table;
              
          } catch (SQLException e) {
              
              Tools.msgBox(e.getMessage());
              return t.new Table(0);
              
          }
          
      }
      
      public static void fillCombo(String tableName, String columnName, JComboBox combo){
          try {
              setConnection();
              Statement stmt = conn.createStatement();
              ResultSet rs;
              String  strSelect = "select "+ columnName +"from "+ tableName;
              rs = stmt.executeQuery(strSelect);
              rs.last();
              int c = rs.getRow();
              rs.beforeFirst();
              
              String value[] = new String[c];
              int i=0;
              while(rs.next()){
                  value[i] =rs.getString(1);
                  i++;   
              }
              closeConnectin();
              combo.setModel(new DefaultComboBoxModel(value));
              
          } catch (SQLException ex) {
              Tools.msgBox(ex.getMessage());
          }
      }
      
      public static boolean CheckUserEmailAndPassword(String  userId, String passWord){
        String strCheck = "select * from member where member_id="+ userId
                +" and pin_code = '"+passWord+"';";
        try {
            setConnection();
            Statement stmt = conn.createStatement();
            stmt.executeQuery(strCheck);
        while(stmt.getResultSet().next()){
            conn.close();
            return true;
        }
        conn.close();
        
        } catch (SQLException ex) {
            Tools.msgBox(ex.getMessage());
           
        }  
        
         return false;
   
    }
      
     public static boolean checkLibrarianEmailAndPassWord(String librarianId, String passWord){
        String strCheck= "select * from librarian where librarian_id="+ librarianId+" and librarian_password ='"+
                passWord+"';";
        try {
            setConnection();
            Statement stmt = conn.createStatement();
            stmt.executeQuery(strCheck);
            while(stmt.getResultSet().next()){
                conn.close();
                return true;
            }
            conn.close();
        
        } catch (SQLException ex) {
            Tools.msgBox(ex.getMessage());
       
        }
        return false;
    }
    
}
