/* This class creates the JavaFX window.
 * 
 * Name: Ahmed Mansour
 * Date: 12/10/18
 */
package JavaFXProject;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class JavaFXProject extends Application {
	
	private int i = 0;
	private Label object1;
	private Label object2;
	private int points = 0;
	private static ArrayList<Object> answersArray = new ArrayList<Object>();
	private static ArrayList<Object> questionsArray;
	static GameTimer timer = new GameTimer();
    @Override
    public void start(Stage primaryStage) throws FileNotFoundException {
    	
        SQLiteJDBC database = new SQLiteJDBC();
        database.createTable();
        primaryStage.setTitle("Adding Game");
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25,25,25,25));
        
        Button submitNameBtn = new Button("Submit");
        HBox hbBtnSubmit = new HBox(10);
        hbBtnSubmit.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtnSubmit.getChildren().add(submitNameBtn);
        grid.add(hbBtnSubmit, 2,  4);
        
        Text enterName = new Text("Welcome! Please enter your name!");
        enterName.setFont(Font.font("Helvetica", FontWeight.NORMAL, 15));
        grid.add(enterName, 1, 0);
        
        TextField nameField = new TextField();
        grid.add(nameField, 1, 4);
        

        
        submitNameBtn.setOnAction(new EventHandler<ActionEvent>() { 
            
        @Override
        public void handle(ActionEvent e) { 

            String name;
            
            submitNameBtn.setVisible(false);
            enterName.setVisible(false);
            nameField.setVisible(false);
            name = nameField.getText();
            
            
            
            Button choiceOneBtn = new Button();
            HBox hbBtnOne = new HBox(10);
            hbBtnOne.setAlignment(Pos.BOTTOM_RIGHT);
            hbBtnOne.getChildren().add(choiceOneBtn);
            grid.add(hbBtnOne, 1,  7);
        
            Button choiceTwoBtn = new Button();
            HBox hbBtnTwo = new HBox(10);
            hbBtnTwo.setAlignment(Pos.BOTTOM_RIGHT);
            hbBtnTwo.getChildren().add(choiceTwoBtn);
            grid.add(hbBtnTwo, 2,  7);
        
            Button choiceThreeBtn = new Button();
            HBox hbBtnThree = new HBox(10);
            hbBtnThree.setAlignment(Pos.BOTTOM_RIGHT);
            hbBtnThree.getChildren().add(choiceThreeBtn);
            grid.add(hbBtnThree, 3,  7);
        
            Button choiceFourBtn = new Button();
            HBox hbBtnFour = new HBox(10);
            hbBtnFour.setAlignment(Pos.BOTTOM_RIGHT);
            hbBtnFour.getChildren().add(choiceFourBtn);
            grid.add(hbBtnFour, 4,  7);
        
            Button reportBtn = new Button("Generate Report");
            HBox hbBtnReport = new HBox(10);
            hbBtnReport.setAlignment(Pos.BOTTOM_RIGHT);
            hbBtnReport.getChildren().add(reportBtn);
            grid.add(hbBtnReport, 3,  9);
        
            Label nameLabel = new Label("Name:");
            grid.add(nameLabel, 0, 0);
            
            Label userName = new Label(name);
            grid.add(userName, 1, 0);
        
            Label pointLabel = new Label("Points:");
            grid.add(pointLabel, 2, 0);
        
            try {
				questionsArray = returnQuestions();
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			}
			object1 = new Label(questionsArray.get(i).toString());
			object1.setFont(Font.font("Helvetica", FontWeight.BOLD, 25));
			object2 = new Label(questionsArray.get(i + 1).toString());
			object2.setFont(Font.font("Helvetica", FontWeight.BOLD, 25));
			choiceOneBtn.setText(answersArray.get(i).toString());
			choiceTwoBtn.setText(answersArray.get(i + 1).toString());
			choiceThreeBtn.setText(answersArray.get(i + 2).toString());
			choiceFourBtn.setText(answersArray.get(i + 3).toString());
			i++;
            grid.add(object1, 2, 3);
            grid.add(object2, 4, 3);

            Label plusLabel = new Label("     +");
            plusLabel.setFont(Font.font("Helvetica", FontWeight.BOLD, 30));
            grid.add(plusLabel, 3, 3);

            Text scoreDisplay = new Text(points + "");
            grid.add(scoreDisplay, 3, 0);
            scoreDisplay.setVisible(false);
            
	            	choiceOneBtn.setOnAction(new EventHandler<ActionEvent>() { 
	                    @Override
	                    public void handle(ActionEvent e) {
	                    	timer = new GameTimer();
	                    	timer.start();
	                    	
	                    	if (i < 10) {
	                    		if(!timer.isFinished()) {
	                    			
	                    			scoreDisplay.setVisible(true);
	                    			      			
			                        object1.setText(questionsArray.get(i * 6).toString());
									object2.setText(questionsArray.get((i * 6) + 1).toString());
									choiceOneBtn.setText(answersArray.get(i * 4).toString());
									choiceTwoBtn.setText(answersArray.get((i * 4) + 1).toString());
									choiceThreeBtn.setText(answersArray.get((i * 4) + 2).toString());
									choiceFourBtn.setText(answersArray.get((i * 4) + 3).toString());
									
									if(checkIfCorrect(questionsArray.get(i * 6), questionsArray.get((i * 6) + 1), choiceOneBtn.getText())) {
										points++;
										scoreDisplay.setText(points + "");
									}
			                       
	                    		}
	                    		 i++;
	                    	}
	                    	
	                    }
	                });
	
	                choiceTwoBtn.setOnAction(new EventHandler<ActionEvent>() { 
	                    @Override
	                    public void handle(ActionEvent e) {
	                    	timer = new GameTimer();
	                    	timer.start();
	                    	
	                    	if (i < 10) {
	                    		if(!timer.isFinished()) {
	                    			
	                    			scoreDisplay.setVisible(true);
	                    			      			
			                        object1.setText(questionsArray.get(i * 6).toString());
									object2.setText(questionsArray.get((i * 6) + 1).toString());
									choiceOneBtn.setText(answersArray.get(i * 4).toString());
									choiceTwoBtn.setText(answersArray.get((i * 4) + 1).toString());
									choiceThreeBtn.setText(answersArray.get((i * 4) + 2).toString());
									choiceFourBtn.setText(answersArray.get((i * 4) + 3).toString());
									
									if(checkIfCorrect(questionsArray.get(i * 6), questionsArray.get((i * 6) + 1), choiceTwoBtn.getText())) {
										points++;
										scoreDisplay.setText(points + "");
									}
			                       
	                    		}
	                    		 i++;
	                    	}
	                    	
	                    }
	                });
	
	                choiceThreeBtn.setOnAction(new EventHandler<ActionEvent>() { 
	                    @Override
	                    public void handle(ActionEvent e) {
	                    	timer = new GameTimer();
	                    	timer.start();
	                    	
	                    	if (i < 10) {
	                    		if(!timer.isFinished()) {
	                    			
	                    			scoreDisplay.setVisible(true);
	                    			      			
			                        object1.setText(questionsArray.get(i * 6).toString());
									object2.setText(questionsArray.get((i * 6) + 1).toString());
									choiceOneBtn.setText(answersArray.get(i * 4).toString());
									choiceTwoBtn.setText(answersArray.get((i * 4) + 1).toString());
									choiceThreeBtn.setText(answersArray.get((i * 4) + 2).toString());
									choiceFourBtn.setText(answersArray.get((i * 4) + 3).toString());
									
									if(checkIfCorrect(questionsArray.get(i * 6), questionsArray.get((i * 6) + 1), choiceThreeBtn.getText())) {
										points++;
										scoreDisplay.setText(points + "");
									}
			                       
	                    		}
	                    		 i++;
	                    	}
	                    	
	                    }
	                });
	
	                choiceFourBtn.setOnAction(new EventHandler<ActionEvent>() { 
	                    @Override
	                    public void handle(ActionEvent e) {
	                    	timer = new GameTimer();
	                    	timer.start();
	                    	
	                    	if (i < 10) {
	                    		if(!timer.isFinished()) {
	                    			
	                    			scoreDisplay.setVisible(true);
	                    			      			
			                        object1.setText(questionsArray.get(i * 6).toString());
									object2.setText(questionsArray.get((i * 6) + 1).toString());
									choiceOneBtn.setText(answersArray.get(i * 4).toString());
									choiceTwoBtn.setText(answersArray.get((i * 4) + 1).toString());
									choiceThreeBtn.setText(answersArray.get((i * 4) + 2).toString());
									choiceFourBtn.setText(answersArray.get((i * 4) + 3).toString());
									
									if(checkIfCorrect(questionsArray.get(i * 6), questionsArray.get((i * 6) + 1), choiceFourBtn.getText())) {
										points++;
										scoreDisplay.setText(points + "");
									}
			                       
	                    		}
	                    		 i++;
	                    	}
	                    	
	                    }
	                });
	                
	                reportBtn.setOnAction(new EventHandler<ActionEvent>() { 
	                    @Override
	                    public void handle(ActionEvent e) {
	                    	ArrayList<String> reportData = new ArrayList<String>();
	                    	System.out.println("name : " + name + ", points: " + points);
	                    	database.insertPerson(name, points);
	    	                //database.selectAll();
	    	                reportData = database.findAllPeople();
	    	                Collections.sort(reportData, null);
	    	                try {
								FileWriter writer = new FileWriter("ScoreReport.txt", true);
								for(int index = 0; index < reportData.size(); index++) {
									writer.write(reportData.get(index) + "\r\n");
									
								}
								writer.close();
							} catch (IOException e1) {
								e1.printStackTrace();
							}
	                    }
	                });
        }
       
        
    });
 
        Scene scene = new Scene(grid, 300, 250);
        
        primaryStage.setScene(scene);
        primaryStage.show();
        
    }
    
     public static void main(String args[]) { //only purpose of the main method is to launch the application
        launch(args);
    }
     
     public static ArrayList<Object> returnQuestions() throws FileNotFoundException {
     	 ReadInput<Object> readFile = new ReadInput<Object>();
     	 System.out.println();
    	 questionsArray = readFile.readFile(1);
    	 for(int y = 0; y < 10; y++) {
  			answersArray.add(questionsArray.get((y * 6) + 2));
  			answersArray.add(questionsArray.get((y * 6) + 3));
  			answersArray.add(questionsArray.get((y * 6) + 4));
  			answersArray.add(questionsArray.get((y * 6) + 5));
  		}
    	return questionsArray;
     }
     
     public boolean checkIfCorrect(Object object1, Object object2, String str) {
    	 boolean correct = false;
    	 int intAnswer = 0;
    	 double doubleAnswer = 0;
    	 
    	 if(object1 instanceof Integer && object2 instanceof Integer) {
    		 intAnswer = (Integer)(object1) + (Integer)(object2);
    		 if(intAnswer == Integer.parseInt(str)) {			 
    			 correct = true;
    		 }
    	 }
    	 else if(object1 instanceof Double || object2 instanceof Double) {
    		 doubleAnswer = (Double)(object1) + (Double)(object2);
    		 if(doubleAnswer == Double.parseDouble(str)) {
    			 correct = true;
    		 }
    	 }
    	 else if((object1.toString() + object2.toString()).equals(str)){
    		 correct = true;
    		 
    	 }
    	 return correct;
     }
     
     public static void printArray(ArrayList<Object> questionsArray) {
    	 for(int i = 0; i < questionsArray.size(); i++) {
    		 System.out.println(questionsArray.get(i) + " with it's class being " + questionsArray.get(i).getClass());
    		 testString(questionsArray.get(i));
    	 }
     }
     
 	public static int testString(Object object) { 
 		int flag = 0;
 	    System.out.print("Original '" + object + "'  ");
 	    String x = ((String) object).trim();
 	    try {
 	        int i = Integer.parseInt(x);
 	        flag = 0;
 	    } catch (NumberFormatException e) {
 	        try {
 	            double d = Double.parseDouble(x);
 	            flag = 1;
 	        } catch (NumberFormatException e2) {
 	            flag = 2;
 	        }
 	    }
 	    return flag;
 	}
 	
 	public static int returnInteger(Object stringInt) {
 		return Integer.parseInt((String) stringInt);
 	}
 	public static double returnDouble(Object stringDouble) {
 		return Double.parseDouble((String) stringDouble);
 	}
 	
 	public static ArrayList<Object> returnAnswers(ArrayList<Object> inputArray) {
 		ArrayList<Object> answersArray = new ArrayList<Object>();
 		for(int i = 0; i < 20; i++) {
 			answersArray.add(inputArray.get((i * 6) + 2));
 			answersArray.add(inputArray.get((i * 6) + 3));
 			answersArray.add(inputArray.get((i * 6) + 4));
 			answersArray.add(inputArray.get((i * 6) + 5));
 			System.out.println(answersArray.get(i));
 		}
		return answersArray;
 	}
 	
 	public static ArrayList<Object> randomizeAnswers(ArrayList<Object> inputArray) {
 		ArrayList<Object> tempArray = new ArrayList<Object>();
 		
 		for(int index = 0; index < inputArray.size() / 4; index++) {
 			tempArray.add(inputArray.get(index));
 			System.out.println("temparray at index: " + tempArray.get(index));
 			tempArray.add(inputArray.get(index + 1));
 			System.out.println("temparray at index + 1: " + tempArray.get(index + 1));
 			tempArray.add(inputArray.get(index + 2));
 			System.out.println("temparray at index + 2: " + tempArray.get(index + 2));
 			tempArray.add(inputArray.get(index + 3));
 			System.out.println("temparray at index + 3: " + tempArray.get(index + 3));
 			Collections.shuffle(tempArray);
 			inputArray.set(index, tempArray.get(0));
 			inputArray.set(index + 1, tempArray.get(1));
 			inputArray.set(index + 2, tempArray.get(2));
 			inputArray.set(index + 3, tempArray.get(3));
 			tempArray.clear();
 		}
 		return inputArray;
 	}
}