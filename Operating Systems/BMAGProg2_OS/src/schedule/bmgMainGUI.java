//Ben Girone CSC 403 12/5/17
//This program implements 8 short term scheduling algorithms.
//This version of the program loads a GUI that animates each processes progression.

package schedule;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Separator;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;

public class bmgMainGUI extends Application
{
	Stage window;
    public static TableView<bmgProcess> table = null;
	
    //static members used by the GUI
    public static bmgQueue processes_saved = new bmgQueue(new LinkedList<bmgProcess>());
    public static bmgQueue processes_book_saved = new bmgQueue(new LinkedList<bmgProcess>());
    public static bmgQueue chosenProcesses = processes_saved;
    public static Queue<bmgProcess> processesToBeUsed;
    public static bmgSimulator simulatorToBeUsed;
    
	public static void main(String[] args)
	{
		//processes assigned on blackboard
		bmgProcess A = new bmgProcess("A", 0, 3);
		bmgProcess B = new bmgProcess("B", 1, 6);
		bmgProcess C = new bmgProcess("C", 2, 4);
		bmgProcess D = new bmgProcess("D", 4, 8);
		bmgProcess E = new bmgProcess("E", 6, 4);
		bmgProcess F = new bmgProcess("F", 8, 2);

		//processes from the book
		bmgProcess A_book = new bmgProcess("A", 0, 3);
		bmgProcess B_book = new bmgProcess("B", 2, 6);
		bmgProcess C_book = new bmgProcess("C", 4, 4);
		bmgProcess D_book = new bmgProcess("D", 6, 5);
		bmgProcess E_book = new bmgProcess("E", 8, 2);
			
		//make a queue in order of arrival time
		processes_saved.add(A);
		processes_saved.add(B);
		processes_saved.add(C);
		processes_saved.add(D);
		processes_saved.add(E);
		processes_saved.add(F);
		
		//make a queue in order of arrival time
		processes_book_saved.add(A_book);
		processes_book_saved.add(B_book);
		processes_book_saved.add(C_book);
		processes_book_saved.add(D_book);
		processes_book_saved.add(E_book);
		
		//set the default processes used by the GUI application
		processesToBeUsed = processes_saved.getResetCopy();
		
		//set the default simulated scheduling algorithm used by the program
		simulatorToBeUsed = new bmgSimulator(new bmgFCFS(processesToBeUsed));
		
		//launch the GUI
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception
	{
		//set the window that opens on startup
		window = primaryStage;
        window.setTitle("Ben Girone CSC 403");
        
        //create the radio buttons used to select the processes to simulate
        final ToggleGroup group1 = new ToggleGroup();
        RadioButton rb1 = new RadioButton("Assigned Processes");
        rb1.setToggleGroup(group1);
        rb1.setSelected(true);
        rb1.setUserData("assigned");
        RadioButton rb2 = new RadioButton("Processes from the text book.");
        rb2.setToggleGroup(group1);
        rb2.setUserData("book");
        
        //create the radio buttons used to select the scheduling algorithm to simulate
        final ToggleGroup group2 = new ToggleGroup();
        RadioButton rbFCFS = new RadioButton("FCFS");
        rbFCFS.setToggleGroup(group2);
        rbFCFS.setSelected(true);
        rbFCFS.setUserData("FCFS");
        RadioButton rbRRq1 = new RadioButton("RRq1");
        rbRRq1.setToggleGroup(group2);
        rbRRq1.setUserData("RRq1");
        RadioButton rbRRq4 = new RadioButton("RRq4");
        rbRRq4.setToggleGroup(group2);
        rbRRq4.setUserData("RRq4");
        RadioButton rbSPN = new RadioButton("SPN");
        rbSPN.setToggleGroup(group2);
        rbSPN.setUserData("SPN");
        RadioButton rbSRT = new RadioButton("SRT");
        rbSRT.setToggleGroup(group2);
        rbSRT.setUserData("SRT");
        RadioButton rbHRRN = new RadioButton("HRRN");
        rbHRRN.setToggleGroup(group2);
        rbHRRN.setUserData("HRRN");
        RadioButton rbFBq1 = new RadioButton("FBq1");
        rbFBq1.setToggleGroup(group2);
        rbFBq1.setUserData("FBq1");
        RadioButton rbFBq2i = new RadioButton("FBq2i");
        rbFBq2i.setToggleGroup(group2);
        rbFBq2i.setUserData("FBq2i");
        
        //create a separator to distinguish the two groups of radio buttons
        Separator separator1 = new Separator();
        
        //create a button to start the simulation and define its action
        Button startButton = new Button("Run");
        startButton.setOnAction(new EventHandler<ActionEvent>()
		{
			@Override
			public void handle(ActionEvent event)
			{
				//ensure the first group of radio buttons is properly created
				if (group1.getSelectedToggle() != null) {
					
					//check the data contained within the radio buttons
	                if (group1.getSelectedToggle().getUserData() == "assigned")
	                {
	                	//use the assigned processes in the simulation
	                	chosenProcesses = processes_saved;
	                }
	                else
	                {
	                	//use the textbook processes in the simulation
	                	chosenProcesses = processes_book_saved;
	                }
	                
	                //set the processes to be used to the appropriate processes
	                processesToBeUsed = chosenProcesses.getResetCopy(); 
	              }
				
				//ensure the second group of radio buttons is properly created
				if (group2.getSelectedToggle() != null) {
					
					//check the data contained within the radio buttons
	                switch (group2.getSelectedToggle().getUserData().toString())
					{
						case "FCFS":
							simulatorToBeUsed = new bmgSimulator(new bmgFCFS(processesToBeUsed)); //use FCFS
							break;
						case "RRq1":
							simulatorToBeUsed = new bmgSimulator(new bmgRRq1(processesToBeUsed)); //use RRq1
							break;
						case "RRq4":
							simulatorToBeUsed = new bmgSimulator(new bmgRRq4(processesToBeUsed)); //use RRq4
							break;
						case "SPN":
							simulatorToBeUsed = new bmgSimulator(new bmgSPN(processesToBeUsed)); //use SPN
							break;
						case "SRT":
							simulatorToBeUsed = new bmgSimulator(new bmgSRT(processesToBeUsed)); //use SRT
							break;
						case "HRRN":
							simulatorToBeUsed = new bmgSimulator(new bmgHRRN(processesToBeUsed)); //use HRRN
							break;
						case "FBq1":
							simulatorToBeUsed = new bmgSimulator(new bmgFBq1(processesToBeUsed)); //use FBq1
							break;
						case "FBq2i":
							simulatorToBeUsed = new bmgSimulator(new bmgFBq2i(processesToBeUsed)); //use FBq2i
							break;
						default:
							break;
					}
	              }
				
				//create a new window containing the simulation table
				Stage stage = new Stage();
	            stage.setTitle("Simulation");
	            VBox vBox = new VBox();
	            getNewTable();
	            vBox.getChildren().addAll(table);
	            stage.setScene(new Scene(vBox));
	            stage.show();
				
	            //start a simulation thread with the chosen simulator
				new bmgSimulationThread(simulatorToBeUsed).start();
			}
		});
        
        //declare a vertical layout box for the GUI
        VBox vBox = new VBox();
        
        //add each element to the layout
        vBox.getChildren().addAll(startButton, rb1, rb2, separator1, rbFCFS, rbRRq1, rbRRq4, rbSPN, rbSRT, rbHRRN, rbFBq1, rbFBq2i);
        
        
        //make a scene to display the layout
        Scene scene = new Scene(vBox);
        window.setScene(scene);
        
        //show the window
        window.setMinWidth(350);
        window.show();
	}
	
	/**
	 * Gets a table with the proper amount of time slots and process rows.
	 */
	private void getNewTable()
	{
		//make a new non-editable table
		table = new TableView();
	    table.setEditable(false);

	    //make a column for the process names
	    TableColumn<bmgProcess, String> nameCol = new TableColumn<>("Process Name");
	    nameCol.setCellValueFactory(new PropertyValueFactory<>("processName"));
	    nameCol.setSortable(false);
	    
	    //get the number of time columns that will be needed
	    int cols = 0;
	    for (Iterator<bmgProcess> iterator = processesToBeUsed.iterator(); iterator.hasNext();)
		{
			bmgProcess process = iterator.next();
			cols += process.getServiceTime();
		}
	    
	    //make the appropriate amount of time columns
	    for (int i = 1; i <= cols; i++)
	    {
	    	TableColumn<bmgProcess, Object> col = new TableColumn<bmgProcess, Object>(Integer.toString(i));
	    	col.setMinWidth(50);
	        table.getColumns().add(col);
	        col.setSortable(false);
	        
	    }
	    
	    //make a column that will show the remaining service time needed by each process
	    TableColumn<bmgProcess, String> timeCol = new TableColumn<>("Remaining Time");
	    timeCol.setCellValueFactory(new PropertyValueFactory<>("remainingTime"));
	    timeCol.setSortable(false);
	    
	    //link the process to be used to the table
	    table.setItems(FXCollections.observableArrayList(processesToBeUsed));
	    
	    //add the columns to the table
	    table.getColumns().add(0, nameCol);
	    table.getColumns().add(timeCol);
	    
	    //refresh the table
	    table.refresh();
	}
	
	/**
	 * Gets the value of a cell at a specified column and row.
	 * @param table The table to get the cell from.
	 * @param column The column to get the cell from. 
	 * @param row The row to get the cell from.
	 * @return An object containing the value of the cell.
	 */
	public static Object getValueAt(TableView table, int column, int row) {
	    return ((TableColumn<bmgProcess, String>) table.getColumns().get(column)).getCellObservableValue(row).getValue();
	}
}