package MinesweeperFX;



import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.input.MouseEvent;
	
public class JavaFXButton extends Application{	//this is the call of the Application also it is important to close the application with Platform.exit()
	public static void main(String[] args) {
		Application.launch(args);    //launch args....
	}
	
	//stage the platform of the scene
	//scene object on stage
	//
	//Button button = new Button(); bases for creating a button in Java
	//fill in () with text or new image
	//
	//Image imageOk = new Image(getClass().getResourceAsStream("OK.png"));
	//Button button = new Button("OK", new ImageView(imageOk));
	//
	// button.setText(String text) - set the text caption for the button
	//
	// button.setGraphic(Node graphic) - set the icon
	//
	//below is a bit different than the Imageview above
	//Image okImage = new Image(getClass().getResourceAsStream("OK.png"));
	//button.setGraphic(new ImageView(okImage);
	//
	//	We can use CSS styles to change the button look and feel.
	//
	//	We define styles in a separate CSS file and apply the CSS file by using the getStyleClass method.
	//
	//	The following code is a CSS file which changes the button font and color.
	//
	//	.button1{
	//	    -fx-font: 30 arial; 
	//	    -fx-base: #ee2211;    
	//	}
	//	Then we use the following code to install the CSS.
	//
	//	button.getStyleClass().add("button1");
	
	
	
	@Override
	public void start(Stage primaryStage) {  //	start(Stage primaryStage)The main entry point for all JavaFX applications.
		
		//Scene scene = new Scene(new Group());
		primaryStage.setWidth(300);
		primaryStage.setHeight(190);
		
		
		
		primaryStage.setTitle("Hello World!");
		
		
		
		final   Button button = new Button();
		Image imageTruffle = new Image(getClass().getResourceAsStream("truffle.png"));
		button.setGraphic(new ImageView(imageTruffle));
		
		button.setOnAction(new EventHandler<ActionEvent>() {//When button is clicked it prints hello world below. So this should perform and action inside of the java console.
			@Override                                       //it appears that we can stack on top of each other the funtions of the mouse button
			public void handle(ActionEvent event) {         //there must be a way we can set a class to mouse buttons so that we can imply them to all buttons. 
			System.out.println("Hello World!");
			}
		});	
			
		
		button.addEventHandler(MouseEvent.MOUSE_ENTERED,   //this registers if the mouse enters or exits the field of a button to the console. this could be used to highlight the pane the pointer is over. 
				new EventHandler<MouseEvent> () {
			@Override
			public void handle (MouseEvent e) {
				System.out.println("mouse entered");
			}
		});
		
		button.addEventHandler(MouseEvent.MOUSE_EXITED,
				new EventHandler<MouseEvent> () {
			@Override
			public void handle (MouseEvent e) {
				System.out.println("mouse exited");
		}
		});
		
		StackPane root = new StackPane();
		root.getChildren().add(button);
		primaryStage.setScene(new Scene(root, 300,250));
		primaryStage.show();
		
		root.getChildren().add(button);
		
	}

}



//stop()This method is called when the application should stop, and provides a convenient place to prepare for application exit and destroy resources.