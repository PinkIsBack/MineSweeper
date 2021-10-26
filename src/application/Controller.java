package application;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import mines.Mines;

public class Controller{

    @FXML
    private TextArea width;

    @FXML
    private TextArea hieght;

    @FXML
    private TextArea mines;

    @FXML
    private Button reset;

    @FXML
    private StackPane StackPane;
    
    
    private String[] tempArr;
    private Mines mine=new Mines();
	private GridPane grid;
	
    @FXML
    void Reset(ActionEvent event){
    	tempArr=new String[3];
    	
    	for(int i=0;i<hieght.getText().length();i++)
    		if(!(hieght.getText().charAt(i)>='0' && hieght.getText().charAt(i)<='9')) {
    			hieght.setText("");
    			return;
    		}
    	tempArr[0]=hieght.getText();
    	
    	for(int i=0;i<width.getText().length();i++)
    		if(!(width.getText().charAt(i)>='0' && width.getText().charAt(i)<='9')) {
    			width.setText("");
    			return;
    		}
    	tempArr[1]=width.getText();
    	
    	for(int i=0;i<mines.getText().length();i++)
    		if(!(mines.getText().charAt(i)>='0' && mines.getText().charAt(i)<='9')) {
    			mines.setText("");
    			return;
    		}
    	tempArr[2]=mines.getText();
    	
    	if(isFull()==true) {
    		createMines();
    	}
    	else {
    		hieght.setText("");
        	width.setText("");
        	mines.setText("");
        	if(grid!=null)
        		grid.getChildren().remove(0,(mine.getHeight()-1)*(mine.getWidth()-1));
        	if(StackPane.getChildren()!=null)
        		StackPane.getChildren().remove(grid);
        	grid=null;
        	tempArr=null;
        	mine=null;
    	}
    }
    public boolean isFull() {
    	for(int i=0;i<tempArr.length;i++)
    		if(tempArr[i]==null) {
    			return false;
    		}
    	return true;
    }
    public void createMines() {
    	int h=0,w=0,m=0;
    	for(int i=0;i<tempArr[0].length();i++) {
    		h*=10;
    		h+=tempArr[0].charAt(i)-'0';
    	}
    	for(int i=0;i<tempArr[1].length();i++) {
    		w*=10;
    		w+=tempArr[1].charAt(i)-'0';
    	}
    	for(int i=0;i<tempArr[2].length();i++) {
    		m*=10;
    		m+=tempArr[2].charAt(i)-'0';
    	}
    	if(grid!=null)
    		grid.getChildren().remove(0,(mine.getHeight()-1)*(mine.getWidth()-1));
    	if(StackPane.getChildren()!=null)
    		StackPane.getChildren().remove(grid);
    	mine=new Mines(h,w,m);
    	setGrid();
    }
    
    
    
    public void setGrid() {
    	grid=new GridPane();
    	for(int i=0;i<mine.getHeight();i++) {
    		for(int j=0;j<mine.getWidth();j++) {
        		Button b=new Button(mine.get(i, j));
        		b.setFont(new Font("Impact",20));
        		b.setPrefSize(50, 50);
        		class ClickHandler implements EventHandler<MouseEvent>{
        			int x,y;
        			public ClickHandler(int x , int y) {
        				this.x=x;
        				this.y=y;
        			}
        			public int getX() {
        				return x;
        			}
        			public int getY() {
        				return y;
        			}
					@Override
					public void handle(MouseEvent e) {
						if(e.getButton()==MouseButton.PRIMARY)
	        				mine.open(x, y);
	        				b.setText(mine.get(x, y));
	        			if(e.getButton()==MouseButton.SECONDARY)
	        				mine.toggleFlag(x, y);
	        				b.setText(mine.get(x, y));
        				for(int i=0;i<(mine.getHeight())*(mine.getWidth());i++) {
        					int upX =((ClickHandler)((Button) grid.getChildren().get(i)).getOnMouseClicked()).getX();
        					int upY =((ClickHandler)((Button) grid.getChildren().get(i)).getOnMouseClicked()).getY();
        					((Button)grid.getChildren().get(i)).setText(mine.get(upX,upY));
        				}
					}
        		}
        		b.setOnMouseClicked(new ClickHandler(i,j));	
        		
        		grid.add(b, j, i);
        	}
    	}
    	StackPane.getChildren().add(grid);
    }
}
