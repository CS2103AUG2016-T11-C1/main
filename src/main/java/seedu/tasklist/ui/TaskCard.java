package seedu.tasklist.ui;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import seedu.tasklist.model.task.ReadOnlyTask;

public class TaskCard extends UiPart{

    private static final String FXML = "TaskListCard.fxml";

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label startTime;
    @FXML
    private Label startDate;
    @FXML
    private Label endTime;
    @FXML
    private Label endDate;
    @FXML
    private Label priority;
    @FXML
    private Label tags;

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
        name.setText(task.getName().taskDetails);
        id.setText(displayedIndex + ". ");
        startTime.setText("Starts:   " + String.valueOf(task.getStartTime().value));
        startDate.setText("Start time:   " + String.valueOf(task.getStartDate().value));
        priority.setText("Priority: " + String.valueOf(task.getPriority()));
        endTime.setText("Ends:     " + String.valueOf(task.getEndTime().value));
        endDate.setText("End time:   " + String.valueOf(task.getEndDate().value));
        tags.setText(task.tagsString());
    }

    public HBox getLayout() {
        return cardPane;
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
