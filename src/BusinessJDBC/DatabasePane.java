/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessJDBC;

import java.awt.*;
import java.awt.event.ItemEvent;
import java.sql.*;
import java.util.*;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;

/**
 *
 * @author Connor
 */
public class DatabasePane extends JPanel{
    String tableName;
    Connection conn = null;
    Statement stat = null;
    ResultSet result = null;
    JTable table;
    JScrollPane scroll;
    String[] columnNames, columnTypes;
    boolean[] columnsDisabled;
    JComboBox<String> cmbPrimaryColumn;
    JTextField txtSearchFilter;
    ArrayList<JTextField> textFields;
    ArrayList<JLabel> labels;
    int primaryColumn, numberOfRows, indexToSelect;
    JPanel topMainPanel, bottomInputPanel, bottomButtonPanel, bottomPrimaryColumnPanel,  bottomMainPanel;
    JButton btnAdd, btnDelete, btnUpdate;// btnExit;
    
    DefaultTableModel modelFromQuery;
    
    String dbConnectString, uName, pWord;
    
    public DatabasePane(String userName, String password, String DBURL, String tableName) {
        this.tableName = tableName;
        this.table = new JTable();
        this.cmbPrimaryColumn = new JComboBox<>();
        this.textFields = new ArrayList<>();
        this.labels = new ArrayList<>();
        
        this.setLayout(new BorderLayout());
        
        topMainPanel = new JPanel();
        bottomInputPanel = new JPanel();
        bottomButtonPanel = new JPanel();
        bottomPrimaryColumnPanel = new JPanel();
        bottomMainPanel = new JPanel();
        
        txtSearchFilter = new JTextField(10);
        //button setups and listener Lambda's
        btnAdd = new JButton("Add New Record");
        btnAdd.addActionListener(e -> add());
        btnDelete = new JButton("Delete Selected Record");
        btnDelete.addActionListener(e -> delete());
        //btnExit = new JButton("Exit App");
        //btnExit.addActionListener(e -> System.exit(0));
        btnUpdate = new JButton("Update Selected Record");
        btnUpdate.addActionListener(e -> update());
        
        cmbPrimaryColumn.addItemListener(ie -> changePrimaryColumn(ie));
        //add buttons
        bottomButtonPanel.setLayout(new GridLayout(1,3));
        bottomButtonPanel.add(btnAdd);
        bottomButtonPanel.add(btnUpdate);
        bottomButtonPanel.add(btnDelete);
        //bottomButtonPanel.add(btnExit);
        
        try{
            dbConnectString = "jdbc:mysql://"+DBURL;
            uName = userName;
            pWord = password;
            
            
            
            table.getSelectionModel().addListSelectionListener(lse -> onSelect(lse));
            //update the table
            updateDisplayTable();
            scroll = new JScrollPane(table);
            //update the textFields and labels, so that there is one for each column
            
            //updateFields();
            
            //final component additions
            bottomMainPanel.setLayout(new BorderLayout());
            bottomMainPanel.add(bottomInputPanel, BorderLayout.CENTER);
            bottomMainPanel.add(bottomButtonPanel, BorderLayout.SOUTH);
            bottomPrimaryColumnPanel.add(new JLabel("Set Primary Column"));
            bottomPrimaryColumnPanel.add(cmbPrimaryColumn);
            bottomMainPanel.add(bottomPrimaryColumnPanel, BorderLayout.NORTH);
            
            txtSearchFilter.getDocument().addDocumentListener(new DocumentListener(){
                @Override
                public void insertUpdate(DocumentEvent de){
                    filterTable();
                }
                @Override
                public void removeUpdate(DocumentEvent de) {
                    filterTable();
                }
                @Override
                public void changedUpdate(DocumentEvent de) {
                    //do nothing
                }
            });
            topMainPanel.add(new JLabel("Search Table:"));
            topMainPanel.add(txtSearchFilter);

            add(topMainPanel, BorderLayout.NORTH);
            add(scroll, BorderLayout.CENTER);
            add(bottomMainPanel, BorderLayout.SOUTH);
            //pack and show
            packParent(this);
        }catch(SQLException e){
            e.printStackTrace();
            System.out.println(String.format("Error Connecting JDBCPane to table %s in DB %s",tableName, DBURL));
        }
    }
    
    private void updateDisplayTable() throws SQLException{
        //connect and run a SELECT * FROM inputTableName
        connect();
        //build the table model, and tell the display the table needs to be updated
        stat = conn.createStatement();
        result = stat.executeQuery("SELECT * FROM " + tableName);
        buildTBModel(result);
        sendModelUpdateTable(modelFromQuery);
        disconnect();
    }
    private void sendModelUpdateTable(DefaultTableModel m){
        table.setModel(m);
        ((DefaultTableModel)table.getModel()).fireTableDataChanged();
        //update the data fields for alteration/input
        updateFields();
        //pack the frame so it displays well
        packParent(this);
    }
    private void buildTBModel(ResultSet result) throws SQLException{
        //build the table from the 'result' query results. 
        //the variable for the full table data
        ResultSetMetaData metaData = result.getMetaData();
        //setup the column name data
        Vector<String> colNames = new Vector<>();
        //setup instance variables holding table metadata
        columnNames = new String[metaData.getColumnCount()];
        columnTypes = new String[metaData.getColumnCount()];
        if(columnsDisabled == null)//only set this up if it is not setup
            columnsDisabled = new boolean[metaData.getColumnCount()];
        cmbPrimaryColumn.removeAllItems();
        numberOfRows = 0;
        //for each columns, add a column name vector, and add the column data to instance
        for(int column = 1; column <= metaData.getColumnCount(); column++){
            colNames.add(metaData.getColumnName(column));
            columnNames[column-1] = metaData.getColumnName(column);
            columnTypes[column-1] = metaData.getColumnTypeName(column)+"("+metaData.getPrecision(column)+
                    (metaData.getScale(column)==0? ")" : ","+metaData.getScale(column)+")");
            
            cmbPrimaryColumn.addItem(metaData.getColumnName(column));
        }
        Vector<Vector<Object>> data =  new Vector<Vector<Object>>();
        //for each row, do:
        while(result.next()){
            //retreive the row's values for each column into a vector
            Vector<Object> rowData = new Vector<>();
            for(int columnIndex = 1; columnIndex <=metaData.getColumnCount(); columnIndex++){
                rowData.add(result.getObject(columnIndex));
            }
            //add the vector to our results
            data.add(rowData);
            //add 1 for each row in the table
            numberOfRows++;
        }
        //set the new DefaultTableModel(data, colNames);
        modelFromQuery = new DefaultTableModel(data, colNames);
    }
    private void updateFields(){
        //clear the arrayLists of all their data, so it can be added fresh
        textFields.clear();
        labels.clear();
        //for each column, add a textField, and a label of their columnName
        for(int i=0;i<columnNames.length;i++){
            JTextField t = new JTextField(15);
            if(columnsDisabled[i])
                t.setEnabled(false);
            textFields.add(t);
            labels.add(new JLabel(columnNames[i]));
        }
        //setup the panel to hold all the labels and fields
        bottomInputPanel.removeAll();
        bottomInputPanel.setLayout(new GridLayout(textFields.size(),3));
        //add all fields and labels. also add new labels of the column data (eg. VARCHAR 30, DECIMAL(9,2))
        for(int i=0;i<textFields.size();i++){
            bottomInputPanel.add(labels.get(i));
            bottomInputPanel.add(textFields.get(i));
            bottomInputPanel.add(new JLabel(columnTypes[i]));
        }
        //if there are more than 15 textFields, then the app will go offscreen
        //so, to account for the field size, shrink the JTabel panel with setPreferredSize()
        System.out.println(table);
        //System.out.println(scroll.getSize());
        System.out.println(bottomInputPanel.getSize());
        if(textFields.size() > 15){
            
        }
        
        
        //make the primary text fields uneditable (this is the textfield that the statement compares to when using delete or update statements
        //it is generally better if this column is an Auto-increment Primary key, but it is fine to be anything
        textFields.get(primaryColumn).setEditable(false);
        packParent(this);
    
    }
    //recursive: goes all the way up to pack the JFrame container of this object
    private void packParent(Component c){
        if(c == null)
            return;
        if(c.getClass() != JFrame.class)
            packParent(c.getParent());
        else
            ((JFrame)c).pack();
    }
    //DocumentListener
    private void filterTable(){
        try{
            //create a new DefaultTableModel mimicing the original query of (SELECT * FROM table)
            DefaultTableModel model = new DefaultTableModel(
                    (Vector<Object>)modelFromQuery.getDataVector().clone(), 
                    new Vector<String>(Arrays.asList(columnNames))
            );
            //get the new search filter, and trim it
            String filter = txtSearchFilter.getText().trim();
            if(filter.equals("")){//if there is no filter, end
                updateDisplayTable();//update the table with an empty filter
                return;
            }
            //setup an array to tell us which rows to keep
            boolean[] rowsToKeep = new boolean[numberOfRows];
            String singleStringData = "";
            //for each row in the defaultTableModel
            for(int row=0;row<numberOfRows;row++){
                EachColumnLoop:
                for(int col=0;col<columnNames.length;col++){
                    singleStringData+=modelFromQuery.getValueAt(row, col).toString().toUpperCase()+" ";
                    //if any column contains the value (Ignoring case)
                    if(modelFromQuery.getValueAt(row, col).toString().toUpperCase().contains(filter.toUpperCase())){
                        //we keep the row and stop searching further columns of the row
                        rowsToKeep[row]=true;
                        break EachColumnLoop;
                    }
                }
                //if the filter was not in any single column, check one last time in case it spans multiple columns (eg. full name)
                if(singleStringData.trim().contains(filter.toUpperCase())){
                    rowsToKeep[row]=true;
                }
                singleStringData = "";
                //System.out.println(rowsToKeep[row]);
            }
            //for each row found, remove the ones not contained within the filter from the new model
            for(int i=rowsToKeep.length-1;i>=0;i--){
                if(!rowsToKeep[i]){
                    model.removeRow(i);
                }
            }
            //update the display table with our new filtered model
            sendModelUpdateTable(model);
        }catch(SQLException e){
            System.out.println("Error Occurred filtering table");
            e.printStackTrace();
        }
    }
    //selectedActionListener
    private void onSelect(ListSelectionEvent e){
        //list selectionListener
        //upon selection, display the first datavalue in the textbox
        if(e.getValueIsAdjusting())
            return; //we dont want multiple events thrown for value adjusting
        if(table.getSelectedRow() == -1)
            return; //dont run this function if there is no row selected
        for(int i=0;i<textFields.size();i++){
            if(table.getValueAt(table.getSelectedRow(),i) == null){
                textFields.get(i).setText("");
            }else{
                textFields.get(i).setText(table.getValueAt(table.getSelectedRow(),i).toString());
            }
        }
    }
    //itemListener
    private void changePrimaryColumn(ItemEvent ie){
        
        if(ie.getStateChange() == ItemEvent.DESELECTED)
            return; //only do events when an item is selected
        if(cmbPrimaryColumn.getSelectedIndex() == -1 || cmbPrimaryColumn.getItemCount() != columnNames.length){
            return; // there must be a selected item to change the primary column
        }
        //change the primary column to the new selection (make it uneditable, etc)
        primaryColumn = cmbPrimaryColumn.getSelectedIndex();
        updateFields();
    }
    //button functions
    private void add(){
        String values = getValues();
        if(values == null)
            return;
        try {
            connect();
            String statement = String.format("INSERT INTO %s %s VALUES %s", tableName, getColumns(), values);
            //run the add statment created above ^
            System.out.println(statement);
            stat.execute(statement);
            //update the table
            updateDisplayTable();
            //update the table display
            ((DefaultTableModel)table.getModel()).fireTableDataChanged();
            System.out.println("Sucessfully inserted");
            disconnect();
        } catch (SQLException ex) {
            //ex.printStackTrace();
        }
    }
    private void update(){
        indexToSelect = primaryColumn;
        try {
            connect();
            String statement = String.format("UPDATE %s SET %sWHERE %s = %s",tableName, getUpdateColumns(), columnNames[primaryColumn], 
                            columnTypes[primaryColumn].startsWith("VARCHAR")||columnTypes[primaryColumn].startsWith("DATE")?"'"+textFields.get(primaryColumn).getText()+"'":textFields.get(primaryColumn).getText());
            //run the update statment created above ^
            System.out.println(statement);
            stat.execute(statement);
            //update the table
            updateDisplayTable();
            //update the table display
            ((DefaultTableModel)table.getModel()).fireTableDataChanged();
            System.out.println("Sucessfully Updated employee");
            repaint();
            disconnect();
        } catch (SQLException ex) {
            //ex.printStackTrace();
        }
        //primaryColumn = indexToSelect;
        cmbPrimaryColumn.setSelectedIndex(indexToSelect);
    }
    private void delete(){
        //check how many records will be deleted and then confirm the deletion
        int numberOfSelections = 0;
        for(int i=0;i<numberOfRows;i++){
            if(table.getValueAt(i,primaryColumn).toString().equals(textFields.get(primaryColumn).getText()))
                numberOfSelections++;
        }
        int selection = JOptionPane.showConfirmDialog(null, "Clicking YES will delete "+numberOfSelections+" record(s). Do you wish to continue?", "Confirm Deletion", JOptionPane.YES_NO_OPTION);
        if(selection != 0)
            return; //dont continue if they select no
        //run the delete statement
        try {
            connect();
            String statement =  String.format("DELETE FROM %s WHERE %s = %s",tableName, columnNames[primaryColumn], 
                    columnTypes[primaryColumn].startsWith("VARCHAR")||columnTypes[primaryColumn].startsWith("DATE")?
                            "'"+textFields.get(primaryColumn).getText()+"'":textFields.get(primaryColumn).getText());
            //run the delete statment created above ^
            stat.execute(statement);
            System.out.println(statement);
            //update the table
            updateDisplayTable();
            //update the table display
            ((DefaultTableModel)table.getModel()).fireTableDataChanged();
            System.out.println("Sucessfully Deleted employee");
            repaint();
            disconnect();
        } catch (SQLException ex) {
            //ex.printStackTrace();
        }
    }
    //retreival used within button functions
    private String getColumns(){
        //get the columns for the "add" function. returns in this format: (var1,var2,...,varx)
        //used in add()
        String s = "(";
        for(int i=0;i< columnNames.length;i++){
            if(i==primaryColumn)//we dont update the primary column
                continue;
            s+=columnNames[i];
            if(i!= columnNames.length-1)
                s+=", ";
        }
        s+=")";
        return s;
    }
    private String getValues(){
        //get the textField data for the non-primary column values. it returns in this format" ('valueIsVarchar',valueIsNotVarchar,...,lastValue)
        //used in add()
        String s = "(";
        
        for(int i=0;i<textFields.size();i++){
            if(i==primaryColumn)//we dont update the primary column
                continue;
            String text = textFields.get(i).getText().trim();
            if(text == null || text.equals("")){
                int response = JOptionPane.showConfirmDialog(null, "One of your non-primary columns does not contain a value. do you still wish to continue?");
                if(response != 0)
                    return null;
                else break;//only warn the user once
            }
        }
        
        for(int i=0;i< textFields.size();i++){
            if(i==primaryColumn)//we dont update the primary column
                continue;
            if(columnTypes[i].startsWith("DATE") && !textFields.get(i).isEnabled()){
                s+="'"+ BackOfficeJDBC.gregToSqlDate(new GregorianCalendar()) + "'";
            }
            else if(columnTypes[i].startsWith("VARCHAR")||columnTypes[i].startsWith("DATE"))
                s+="'"+ textFields.get(i).getText().trim() + "'";
            else
                s+=textFields.get(i).getText().trim();
            if(i!= columnNames.length-1)
                s+=", ";
        }
        s+=")";
        return s;
    }
    private String getUpdateColumns(){
        //get the columns we are undating the the "update" function. 
        //returns in this format: (colName = 'varcharValue', colName = nonVarcharValue,...,colName = lastValue )
        //the space at the end is currently important, as the update() function syntax expects it
        //used in update()
        String s = "";
        for(int i=0;i<columnNames.length;i++){
            if(i == primaryColumn)
                continue;
            if(columnTypes[i].startsWith("VARCHAR")||columnTypes[i].startsWith("DATE"))
                s+=String.format("%s = '%s'", columnNames[i], textFields.get(i).getText());
            else
                s+=String.format("%s = %s", columnNames[i], textFields.get(i).getText());
            //   if this loop is not the last loop
            //                              if the next loop is not the primary column and the last loop
            if(i!=columnNames.length-1 && !(i == primaryColumn-1 && primaryColumn == columnNames.length-1))
                s+=",";
            s+=" ";
        }
        return s;
    }
    //Database connection and disconnection
    private void connect(){
        try{
            conn = DriverManager.getConnection(dbConnectString, uName, pWord);
            stat = conn.createStatement();
        }catch(SQLException e){
            System.out.println("Error Connecting");
        }
    }
    private void disconnect(){
        try{
            conn.close();
            stat.close();
        }catch(SQLException e){
            System.out.println("Error Disconnecting");
        }
    }
    //data retreival
    public String[] getTextFieldData(){
        String[] data = new String[textFields.size()];
        for(JTextField tf:textFields){
            data[textFields.indexOf(tf)] = tf.getText();
        }
        return data;
    }
    public String[] getRowData(int row){
        String[] rowData = new String[columnNames.length];
        for(int i=0;i<rowData.length;i++){
            rowData[i] = modelFromQuery.getValueAt(row, i).toString();
        }
        return rowData;
    }
    public int getRows(){
        return numberOfRows;
    }
    //outer DB additions
    public void insertValues(String[] values){
       for(int i=0;i<values.length;i++){
           textFields.get(i+1).setText(values[i]);
       }
       add();
    }
    //button limitations
    public void updateEnabled(boolean enabled){
        btnUpdate.setVisible(enabled);
        btnUpdate.setEnabled(enabled);
    }
    public void deleteEnabled(boolean enabled){
        btnDelete.setVisible(enabled);
        btnDelete.setEnabled(enabled);
    }
    //
    public void setDateAutoInsert(int column){
        if(columnTypes[column].startsWith("DATE")){
            columnsDisabled[column] = true;
            textFields.get(column).setEnabled(false);
            System.out.println("Column Disabled");
        }
    }
    //adjust which table is being viewed
    public void setCurrentTableName(String tableName) throws SQLException{
        this.tableName = tableName;
        updateDisplayTable();
    }
    
    public static void main(String[] args) {
        DatabasePane panel1 = new DatabasePane("gc200313751","HBw?UQ--","sql.computerstudi.es:3306/gc200313751","JavaEmployees");
        DatabasePane panel2 = new DatabasePane("gc200313751","HBw?UQ--","sql.computerstudi.es:3306/gc200313751","JavaSales");
        JFrame frame = new JFrame();
        JTabbedPane dbTabs = new JTabbedPane();
        dbTabs.addTab("Employees", null, panel1, "Employees DB");
        dbTabs.addTab("pizza", null, panel2, "pizza DB");
        panel1.updateFields();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(dbTabs);
        frame.setVisible(true);
        frame.pack();
    }
}
