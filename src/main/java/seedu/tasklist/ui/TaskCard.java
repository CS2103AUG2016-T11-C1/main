package seedu.tasklist.ui;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import seedu.tasklist.model.task.ReadOnlyTask;
import seedu.tasklist.model.task.Task;

public class TaskCard extends UiPart{

    private static final String FXML = "TaskListCard.fxml";
    private static final String COMPLETED_ICON_URL = 
    		TaskCard.class.getResource("/images/icon_checkmark.png").toExternalForm();
    private static final String OVERDUE_ICON_URL = 
    		TaskCard.class.getResource("/images/icon_exclamation.png").toExternalForm();
    private static final String INCOMPLETE_ICON_URL = 
    		TaskCard.class.getResource("/images/icon_incomplete.png").toExternalForm();

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label startTime;
    @FXML
    private Label endTime;
    @FXML
    private Label priority;
    @FXML
    private Label tags;
    @FXML
    private ImageView statusButton;

    private ReadOnlyTask task;
    private int displayedIndex;

    public TaskCard(){

    }

    public static TaskCard load(ReadOnlyTask task, int displayedIndex){
        TaskCard card = new TaskCard();
        card.task = task;
        card.displayedIndex = displayedIndex;
        return UiPartLoader.loadUiPart(card);
    }

    @FXML
    public void initialize() {
        name.setText(task.getTaskDetails().taskDetails);
        id.setText(displayedIndex + ". ");
        startTime.setText("Starts:   " + task.getStartTime().toCardString());
        priority.setText("Priority: " + String.valueOf(task.getPriority())+ " Float:"+ Task.FLOAT_COUNTER + " Incomplete:" + Task.INCOMPLETE_COUNTER + " OverDue:"+Task.OVERDUE_COUNTER+ "\n");
        endTime.setText("Ends:     " + task.getEndTime().toCardString());
        statusButton.setVisible(true);
        setStatusButtonColour();
        tags.setText(task.tagsString());
    }

    public HBox getLayout() {
        return cardPane;
    }

    public void setStatusButtonColour() {
    	//TODO: add if overdue
    	if(task.isComplete()){
    		statusButton.setImage(new Image(COMPLETED_ICON_URL));
    	}
    	else{
    		statusButton.setImage(new Image(INCOMPLETE_ICON_URL));
    	}
    }
    
    @Override
    public void setNode(Node node) {
        cardPane = (HBox)node;
    }

    @Override
    public String getFxmlPath() {
        return FXML;
    }
}
