/**
 * Created by abdul on 2017-03-07.
 */
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

/**
 * Created by abdul on 2017-03-12.
 */
public class FloorBuilderView extends GridPane {
    private Button[][] squares;
    private Label title1;
    private Label title2;
    private Label title3;
    private TextField textField;
    private GridPane pane;
    private RadioButton radiobutton1;
    private RadioButton radiobutton2;
    private RadioButton radiobutton3;
    private RadioButton radiobutton4;
    private Button paintButton;
    private Button overview;
    private Building model=Building.example();

    //get methods
    public Button getSquares(int x, int y){return squares[x][y];}
    public Label getTitle1(){return title1;}
    public Label getTitle2(){return title2;}
    public Label getTitle3(){return title3;}
    public TextField getTextField(){return textField;}
    public GridPane getPane(){return pane;}
    public RadioButton getRadiobutton1(){return radiobutton1;}
    public RadioButton getRadiobutton2(){return radiobutton2;}
    public RadioButton getRadiobutton3(){return radiobutton3;}
    public RadioButton getRadiobutton4(){return radiobutton4;}
    public Button getOverview(){return overview;}
    public Button getPaintButton(){return paintButton;}
    public Building getBuilding(){return model;}

    public FloorBuilderView(Building building){
        //make pane
        pane= new GridPane();
        squares= new Button[building.getFloorPlan(0).size()][building.getFloorPlan(0).size()];
        setPadding(new Insets(10,10,10,10));
        //make grid buttons
        for(int i=0;i<building.getFloorPlan(0).size();i++){
            for(int j=0;j<building.getFloorPlan(0).size();j++){
                squares[i][j]=new Button();
                squares[i][j].setMinSize(11,11);
                squares[i][j].setPrefSize(Integer.MAX_VALUE,Integer.MAX_VALUE);
                if(building.hasExitAt(j,i)){
                    squares[i][j].setStyle("-fx-base:RED");
                }
                else if(building.getFloorPlan(0).wallAt(j,i)){
                    squares[i][j].setStyle("-fx-base:BLACK");
                }
                else{
                    squares[i][j].setStyle("-fx-base:White");
                }
                GridPane.setMargin(squares[i][j],new Insets(0,0,0,0));
                pane.add(squares[i][j],i,j);
            }
        }
        add(pane,0,1);

        title1=new Label("FLOOR LAYOUT");
        title1.setMinSize(100,20);
        GridPane.setMargin(title1,new Insets(0,0,10,0));
        add(title1,0,0);

        title2=new Label("SELECT/EDIT");
        title2.setMinSize(150,20);
        GridPane.setMargin(title2,new Insets(0,0,0,0));
        add(title2,1,0);

        title3 = new Label("FLOOR SUMMARY");
        title3.setMinSize(100,20);
        setMargin(title3, new Insets(0,0,10,0));
        add(title3,0,2);

        textField=new TextField(building.getFloorPlan(0).getName()+" with "+ building.getFloorPlan(0).getNumberOfRooms()+" rooms.");
        textField.setPrefSize(getPrefWidth(),30);
        add(textField,0,3,22,1);

        ToggleGroup group= new ToggleGroup();

        radiobutton1= new RadioButton("Walls");
        setValignment(radiobutton1, VPos.TOP);
        setMargin(radiobutton1, new Insets(25,30,0,25));
        radiobutton1.setSelected(true);
        radiobutton1.setToggleGroup(group);
        add(radiobutton1,1,1);

        radiobutton2= new RadioButton("Exits");
        setValignment(radiobutton2, VPos.TOP);
        setMargin(radiobutton2, new Insets(50,30,0,25));
        radiobutton2.setSelected(true);
        radiobutton2.setToggleGroup(group);
        add(radiobutton2,1,1);

        radiobutton3= new RadioButton("Room Tiles");
        setValignment(radiobutton3, VPos.TOP);
        setMargin(radiobutton3, new Insets(75,20,0,25));
        radiobutton3.setSelected(true);
        radiobutton3.setToggleGroup(group);
        add(radiobutton3,1,1);

        radiobutton4= new RadioButton("Select Room");
        setValignment(radiobutton4, VPos.TOP);
        setMargin(radiobutton4, new Insets(100,30,0,25));
        radiobutton4.setSelected(true);
        radiobutton4.setToggleGroup(group);
        add(radiobutton4,1,1);

        paintButton= new Button();
        setValignment(paintButton,VPos.TOP);
        paintButton.setMinSize(25,25);
        paintButton.setStyle("-fx-base: ORANGE");
        setMargin(paintButton,new Insets(95,0,0,0));
        add(paintButton,2,1);

        overview=new Button("Button Overview");
        overview.setPrefSize(175,25);
        setValignment(overview,VPos.TOP);
        setMargin(overview,new Insets(135,0,0,20));
        add(overview,1,1,2,1);

    }
}
