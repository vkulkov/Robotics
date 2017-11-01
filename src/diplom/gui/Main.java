package diplom.gui;

import diplom.debugger.ArduinoCodeCreator;
import diplom.debugger.XMLParser;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.ArrayDeque;
import java.util.Deque;

public class Main extends Application {
    private static Document document;
    private static int functionDepth = 0;
    private static Deque<Element> stack = new ArrayDeque<>();

    public static Document getDocument() {
        return document;
    }

    public static int getFunctionDepth() {
        return functionDepth;
    }

    public static Deque<Element> getStack() {
        return stack;
    }

    public static void setDocument(Document document) {
        Main.document = document;
    }

    public static void increaseFunctionDepth() {
        Main.functionDepth++;
    }

    public static void decreaseFunctionDepth() {
        Main.functionDepth--;
    }

    @Override
    public void start(Stage primaryStage) throws Exception{

        /*________________Прорисовка окна и внутренней структуры________________*/

        BorderPane borderPane = new BorderPane();
        GridPane root = new GridPane();
        GridPane grid = new GridPane();
        root.setAlignment(Pos.CENTER);
        grid.setAlignment(Pos.BOTTOM_CENTER);
        StackPane stackPane1 = new StackPane();
        HBox hBox = new HBox(10);
        hBox.setAlignment(Pos.BOTTOM_CENTER);
        stackPane1.setAlignment(Pos.CENTER);
        borderPane.setLeft(root);
        borderPane.setBottom(grid);
        borderPane.setCenter(stackPane1);
        borderPane.setBottom(hBox);


        /*borderPane.setBottom(stackPane2);*/

//        Rectangle background = new Rectangle(950, 2000, Color.CORNSILK);
//        Rectangle buttons = new Rectangle(1000,100, Color.SALMON);
        VBox vBox = new VBox();
        ScrollPane scrollPane = new ScrollPane(vBox);
        stackPane1.getChildren().addAll(scrollPane);

        /*stackPane2.getChildren().addAll(buttons);*/

//        buttons.setStroke(Color.BLACK);
//        buttons.setStrokeWidth(2);
//        buttons.setArcHeight(10);
//        buttons.setArcWidth(10);
//        background.setStroke(Color.BLACK);
//        background.setStrokeWidth(2);
//        background.setArcHeight(10);
//        background.setArcWidth(10);


        /*________________Настройка полос прокрутки________________*/

        /*ScrollBar verScroll = new ScrollBar();
        verScroll.setOrientation(Orientation.VERTICAL);*/

        grid.setPadding(new Insets(30, 30,30, 30));
        grid.setHgap(10);
        grid.setVgap(10);
        root.setPadding(new Insets(15, 15,15, 15));
        root.setHgap(5);
        root.setVgap(5);


        /*________________Компоненты Label________________*/

        Label mainLabel = new Label("Main Functions");
        Tooltip mainlTip = new Tooltip("Функция setup() и функция loop()");
        mainLabel.setTooltip(mainlTip);
        Label loopLabel = new Label("Control Operators");
        Label timeLabel = new Label("Time");
        Label dinoutLabel = new Label("Digital Input/Output");
        Label ainoutLabel = new Label("Analog Input/Output");
        Label dataLabel = new Label("Data Types");
        Label bsbLabel = new Label("Bits and Bytes");

        Label mathLabel = new Label("Mathematical Calculations");
        Label ariphLabel = new Label("Ariphmetic Operators");
        Label logicLabel = new Label("Logic Operators");
        Label bitLabel = new Label("Bitwise Operators");
        Label compLabel = new Label("Compound Operators");
        Label comparLabel = new Label("Comparison Operators");


        root.getChildren().addAll(mainLabel, loopLabel, timeLabel, dinoutLabel, ainoutLabel, dataLabel, mathLabel, ariphLabel, logicLabel, bitLabel, compLabel, bsbLabel, comparLabel);


        /*________________Кнопки вывода дополнительной инфы________________*/

        hBox.setVisible(false);

        Button main = new Button("MAIN");
        main.setPrefSize(150, 30);
        main.setStyle("-fx-base:#FFD700");
        Button loop = new Button("CONTROL");
        loop.setPrefSize(150, 30);
        loop.setStyle("-fx-base:#7CFC00");
        Button time = new Button("TIME");
        time.setPrefSize(150, 30);
        time.setStyle("-fx-base:#7FFFD4");
        Button d_in_out = new Button("DIGITAL IN/OUT");
        d_in_out.setPrefSize(150, 30);
        d_in_out.setStyle("-fx-base:#808000");
        Button a_in_out = new Button("ANALOG IN/OUT");
        a_in_out.setPrefSize(150, 30);
        a_in_out.setStyle("-fx-base:#556B2F");
        Button data = new Button("DATA TYPES");
        data.setPrefSize(150, 30);
        data.setStyle("-fx-base:#00BFFF");
        Button bsb = new Button("BITS and BYTES");
        bsb.setPrefSize(150, 30);
        bsb.setStyle("-fx-base:#E0FFFF");


        Button mathOper = new Button("MATHEMATICS");
        mathOper.setPrefSize(150, 30);
        mathOper.setStyle("-fx-base:#DC143C");
        Button ariphOper = new Button("ARIPHMETIC");
        ariphOper.setPrefSize(150, 30);
        ariphOper.setStyle("-fx-base:#FF8C00");
        Button logicOper = new Button("LOGIC");
        logicOper.setPrefSize(150, 30);
        logicOper.setStyle("-fx-base:#FF00FF");
        Button bitOper = new Button("BITWISE");
        bitOper.setPrefSize(150, 30);
        bitOper.setStyle("-fx-base:#6A5ACD");
        Button compOper = new Button("COMPOUND");
        compOper.setPrefSize(150, 30);
        compOper.setStyle("-fx-base:#F4A460");
        Button comparOper = new Button("COMPARISON");
        comparOper.setPrefSize(150, 30);
        comparOper.setStyle("-fx-base:#2F4F4F");



/*
        loop.setOnMouseClicked(event -> {
            if (!hBox2.isVisible()){
                hBox2.setVisible(true);
            }
            else{
                hBox2.setVisible(false);
            }
        });
*/
        root.getChildren().addAll(main, loop, time, d_in_out, a_in_out, data, mathOper, ariphOper, logicOper, bitOper, compOper, bsb, comparOper);


        /*________________Кнопки дополнительной инфы (MAIN)________________*/

        Button setup = new Button("SETUP");
        setup.setPrefSize(150, 30);
        Tooltip setupTip = new Tooltip("Функция setup() используется для инициализации переменных, определения режимов работы выводов, запуска используемых библиотек и т.д.");
        setup.setTooltip(setupTip);
        Button looP = new Button("LOOP");
        looP.setPrefSize(150, 30);
        Tooltip loopTip = new Tooltip("Функция loop() запускает циклэ, позволяя вашей программе совершать вычисления и реагировать на них.");
        looP.setTooltip(loopTip);
        Button endm = new Button("end");
        endm.setPrefSize(150, 30);
        Tooltip endmTip = new Tooltip("end() не является функцией. используйте его для завершения функций setup() и loop().");
        endm.setTooltip(endmTip);

        main.setOnMouseClicked(event -> {
            if (!hBox.isVisible()){
                fillHBox(hBox, setup, looP, endm);
                hBox.setVisible(true);
            }
            else{
                hBox.setVisible(false);
                clearHBox(hBox);
            }
        });

        setup.setOnAction(event -> createSetup(vBox));
        looP.setOnMouseClicked(event -> createLoop(vBox));
        endm.setOnMouseClicked(event -> createEndLoop(vBox));


        /*________________Кнопки дополнительной инфы (CONTROL)________________*/

        Button ifB = new Button("if");
        ifB.setPrefSize(150, 30);
        Tooltip ifTip = new Tooltip(" ");
        ifB.setTooltip(ifTip);
        Button elseB = new Button("...else");
        elseB.setPrefSize(150, 30);
        Tooltip elseTip = new Tooltip(" ");
        elseB.setTooltip(elseTip);
        Button forB = new Button("for");
        forB.setPrefSize(150, 30);
        Tooltip forTip = new Tooltip(" ");
        forB.setTooltip(forTip);
        Button doB = new Button("do");
        doB.setPrefSize(150, 30);
        Tooltip doTip = new Tooltip(" ");
        doB.setTooltip(doTip);
        Button whileB = new Button("while");
        whileB.setPrefSize(150, 30);
        Tooltip whileTip = new Tooltip(" ");
        whileB.setTooltip(whileTip);
        Button breakB = new Button("break");
        breakB.setPrefSize(150, 30);
        Tooltip breakTip = new Tooltip(" ");
        breakB.setTooltip(breakTip);
        Button contB = new Button("continue");
        contB.setPrefSize(150, 30);
        Tooltip contTip = new Tooltip(" ");
        contB.setTooltip(contTip);
        Button returnB = new Button("return");
        returnB.setPrefSize(150, 30);
        Tooltip returnTip = new Tooltip(" ");
        returnB.setTooltip(returnTip);
        Button endC = new Button("end");
        endC.setPrefSize(150, 30);
        Tooltip endCTip = new Tooltip("end() не является функцией. используйте его для завершения функций setup() и loop().");
        endC.setTooltip(endCTip);


        loop.setOnMouseClicked(event -> {
            if (!hBox.isVisible()){
                fillHBox(hBox, ifB, elseB, forB, doB, whileB, breakB, contB, returnB, endC);
                hBox.setVisible(true);
            }
            else{
                hBox.setVisible(false);
                clearHBox(hBox);
            }
        });

        ifB.setOnAction(event -> createIf(vBox));
        elseB.setOnAction(event -> createElse(vBox));
        forB.setOnAction(event -> createFor(vBox));
        doB.setOnAction(event -> createDo(vBox));
        whileB.setOnAction(event -> createWhile(vBox));
        breakB.setOnAction(event -> createBreak(vBox));
        contB.setOnAction(event -> createContinue(vBox));
        returnB.setOnAction(event -> createReturn(vBox));
        endC.setOnAction(event -> createEnd(vBox));

        /*________________Кнопки дополнительной инфы (TIME)________________*/

        Button millisB = new Button("millis()");
        millisB.setPrefSize(150, 30);
        Button microsB = new Button("micros()");
        microsB.setPrefSize(150, 30);
        Button delayB = new Button("delay()");
        delayB.setPrefSize(150, 30);
        Button delayMicrosB = new Button("delayMicroseconds()");
        delayMicrosB.setPrefSize(150, 30);

        time.setOnMouseClicked(event -> {
            if (!hBox.isVisible()){
                fillHBox(hBox, millisB, microsB, delayB, delayMicrosB);
                hBox.setVisible(true);
            }
            else{
                hBox.setVisible(false);
                clearHBox(hBox);
            }
        });

        millisB.setOnAction(event -> createMillis(vBox));
        microsB.setOnAction(event -> createMicros(vBox));
        delayB.setOnAction(event -> createDelay(vBox));
        delayMicrosB.setOnAction(event -> createDelayMicros(vBox));;

        /*________________Кнопки дополнительной инфы (DATA TYPES)________________*/

        Button voidB = new Button("statement");
        voidB.setPrefSize(150, 30);
        Tooltip voidTip = new Tooltip(" ");
        voidB.setTooltip(ifTip);
        Button booleanB = new Button("boolean");
        booleanB.setPrefSize(150, 30);
        Tooltip booleanTip = new Tooltip(" ");
        booleanB.setTooltip(elseTip);
        Button charB = new Button("char");
        charB.setPrefSize(150, 30);
        Tooltip charTip = new Tooltip(" ");
        charB.setTooltip(forTip);
        Button byteB = new Button("byte");
        byteB.setPrefSize(150, 30);
        Tooltip byteTip = new Tooltip(" ");
        byteB.setTooltip(whileTip);
        Button intB = new Button("int");
        intB.setPrefSize(150, 30);
        Tooltip IntTip = new Tooltip(" ");
        intB.setTooltip(breakTip);
        Button wordB = new Button("word");
        wordB.setPrefSize(150, 30);
        Tooltip wordTip = new Tooltip(" ");
        wordB.setTooltip(returnTip);
        Button longB = new Button("long");
        longB.setPrefSize(150, 30);
        Tooltip longTip = new Tooltip(" ");
        longB.setTooltip(forTip);
        Button shortB = new Button("short");
        shortB.setPrefSize(150, 30);
        Tooltip shortTip = new Tooltip(" ");
        shortB.setTooltip(whileTip);
        Button floatB = new Button("float");
        floatB.setPrefSize(150, 30);
        Tooltip floatTip = new Tooltip(" ");
        floatB.setTooltip(breakTip);
        Button doubleB = new Button("double");
        doubleB.setPrefSize(150, 30);
        Tooltip DoubleTip = new Tooltip(" ");
        doubleB.setTooltip(contTip);
        Button stringB = new Button("string");
        stringB.setPrefSize(150, 30);
        Tooltip stringTip = new Tooltip(" ");
        stringB.setTooltip(returnTip);

        data.setOnMouseClicked(event -> {
            if (!hBox.isVisible()){
                fillHBox(hBox, voidB, booleanB, charB, byteB, intB, wordB, longB, shortB, floatB, doubleB, stringB);
                hBox.setVisible(true);
            }
            else{
                hBox.setVisible(false);
                clearHBox(hBox);
            }
        });

        voidB.setOnAction(event -> createStatement(vBox));
        booleanB.setOnAction(event -> createBoolean(vBox));
        charB.setOnAction(event -> createChar(vBox));
        byteB.setOnAction(event -> createByte(vBox));
        intB.setOnAction(event -> createInt(vBox));
        wordB.setOnAction(event -> createWord(vBox));
        longB.setOnAction(event -> createLong(vBox));
        shortB.setOnAction(event -> createShort(vBox));
        floatB.setOnAction(event -> createFloat(vBox));
        doubleB.setOnAction(event -> createDouble(vBox));
        stringB.setOnAction(event -> createString(vBox));

        /*________________Кнопки дополнительной инфы (DIGITAL)________________*/

        Button pinModeB = new Button("pinMode()");
        pinModeB.setPrefSize(150, 30);
        Tooltip pinModeTip = new Tooltip(" ");
        pinModeB.setTooltip(ifTip);
        Button digWrB = new Button("digitalWrite()");
        digWrB.setPrefSize(150, 30);
        Tooltip digWrTip = new Tooltip(" ");
        digWrB.setTooltip(elseTip);
        Button digReB = new Button("digitalRead()");
        digReB.setPrefSize(150, 30);
        Tooltip digReTip = new Tooltip(" ");
        digReB.setTooltip(forTip);


        d_in_out.setOnMouseClicked(event -> {
            if (!hBox.isVisible()){
                fillHBox(hBox, pinModeB, digWrB, digReB);
                hBox.setVisible(true);
            }
            else{
                hBox.setVisible(false);
                clearHBox(hBox);
            }
        });

        pinModeB.setOnAction(event -> createPinMode(vBox));
        digWrB.setOnAction(event -> createDigWrite(vBox));
        digReB.setOnAction(event -> createDigRead(vBox));

        /*________________Кнопки дополнительной инфы (ANALOG)________________*/

        Button anReferB = new Button("analogReference()");
        anReferB.setPrefSize(150, 30);
        Tooltip anReferTip = new Tooltip(" ");
        anReferB.setTooltip(ifTip);
        Button anWrB = new Button("analogWrite()");
        anWrB.setPrefSize(150, 30);
        Tooltip anWrTip = new Tooltip(" ");
        anWrB.setTooltip(elseTip);
        Button anReB = new Button("analogRead()");
        anReB.setPrefSize(150, 30);
        Tooltip anReTip = new Tooltip(" ");
        anReB.setTooltip(forTip);


        a_in_out.setOnMouseClicked(event -> {
            if (!hBox.isVisible()){
                fillHBox(hBox, anReferB, anWrB, anReB);
                hBox.setVisible(true);
            }
            else{
                hBox.setVisible(false);
                clearHBox(hBox);
            }
        });

        anReferB.setOnAction(event -> createAnRefer(vBox));
        anWrB.setOnAction(event -> createAnWrite(vBox));
        anReB.setOnAction(event -> createAnRead(vBox));

        /*________________Кнопки дополнительной инфы (BITS and BYTES)________________*/

        Button lowByteB = new Button("lowByte()");
        lowByteB.setPrefSize(150, 30);
        Tooltip lowByteTip = new Tooltip(" ");
        lowByteB.setTooltip(ifTip);
        Button highByteB = new Button("highByte()");
        highByteB.setPrefSize(150, 30);
        Tooltip highByteTip = new Tooltip(" ");
        highByteB.setTooltip(elseTip);
        Button bitWriteB = new Button("bitWrite()");
        bitWriteB.setPrefSize(150, 30);
        Tooltip bitWriteTip = new Tooltip(" ");
        bitWriteB.setTooltip(doTip);
        Button bitReadB = new Button("bitRead()");
        bitReadB.setPrefSize(150, 30);
        Tooltip bitReadTip = new Tooltip(" ");
        bitReadB.setTooltip(forTip);
        Button bitSetB = new Button("bitSet()");
        bitSetB.setPrefSize(150, 30);
        Tooltip bitSetTip = new Tooltip(" ");
        bitSetB.setTooltip(whileTip);
        Button bitClearB = new Button("bitClear()");
        bitClearB.setPrefSize(150, 30);
        Tooltip bitClearTip = new Tooltip(" ");
        bitClearB.setTooltip(breakTip);
        Button bitB = new Button("bit()");
        bitB.setPrefSize(150, 30);
        Tooltip bitTip = new Tooltip(" ");
        bitB.setTooltip(contTip);

        bsb.setOnMouseClicked(event -> {
            if (!hBox.isVisible()){
                fillHBox(hBox, lowByteB, highByteB, bitWriteB, bitReadB, bitSetB, bitClearB, bitB);
                hBox.setVisible(true);
            }
            else{
                hBox.setVisible(false);
                clearHBox(hBox);
            }
        });

        lowByteB.setOnAction(event -> createlByte(vBox));
        highByteB.setOnAction(event -> createhByte(vBox));
        bitWriteB.setOnAction(event -> createBitWr(vBox));
        bitReadB.setOnAction(event -> createBitRe(vBox));
        bitSetB.setOnAction(event -> createBitSet(vBox));
        bitClearB.setOnAction(event -> createBitClear(vBox));
        bitB.setOnAction(event -> createBit(vBox));


        /*________________Блоки для добавления________________*/



        /*________________Кнопки управления________________*/

        Button btn = new Button("Transformation");
        btn.setPrefSize(150, 30);
        Button btn1 = new Button("Delete");
        btn1.setPrefSize(150, 30);
        Button btn2 = new Button("Clear");
        btn2.setPrefSize(150, 30);
        root.getChildren().addAll(btn,btn1,btn2);

        btn2.setOnMouseClicked(event -> vBox.getChildren().clear());

        btn1.setOnMouseClicked(event -> {
            vBox.getChildren().remove(vBox.getChildren().size() - 1, vBox.getChildren().size());
        });

        btn.setOnMouseClicked(event -> {
            transform(vBox);
        });




        /*________________Добавление элементов на окно________________*/

        root.setConstraints(mainLabel, 0,0);
        root.setConstraints(loopLabel, 0,1);
        root.setConstraints(timeLabel, 0,2);
        root.setConstraints(dinoutLabel, 0,3);
        root.setConstraints(ainoutLabel, 0,4);
        root.setConstraints(dataLabel, 0,5);
        root.setConstraints(bsbLabel, 0,6);
        root.setConstraints(mathLabel, 0,8);
        root.setConstraints(ariphLabel, 0,9);
        root.setConstraints(logicLabel, 0,10);
        root.setConstraints(bitLabel, 0,11);
        root.setConstraints(compLabel, 0,12);
        root.setConstraints(comparLabel, 0,13);

        root.setConstraints(main, 1,0);
        root.setConstraints(loop, 1,1);
        root.setConstraints(time, 1,2);
        root.setConstraints(d_in_out, 1,3);
        root.setConstraints(a_in_out, 1,4);
        root.setConstraints(data, 1,5);
        root.setConstraints(bsb, 1,6);
        root.setConstraints(mathOper, 1,8);
        root.setConstraints(ariphOper, 1,9);
        root.setConstraints(logicOper, 1,10);
        root.setConstraints(bitOper, 1,11);
        root.setConstraints(compOper, 1,12);
        root.setConstraints(comparOper, 1,13);


        root.setConstraints(btn, 0,15);
        root.setConstraints(btn1, 1,15);
        root.setConstraints(btn2, 2,15);


        /*________________Настройка сцены________________*/

        Scene scene = new Scene(borderPane, 1000,600);
        primaryStage.setTitle("INTERFACE ver. 2.2.1 CLOSED BETA");
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }

    private static void fillHBox (HBox hBox, Button... buttons) {
        hBox.getChildren().addAll(buttons);
    }

    private static void clearHBox (HBox hBox) {
        hBox.getChildren().clear();
    }

    /*_____________Блоки Main______________*/

    private static void createSetup(VBox vBox) {
        VBox box = new VBox();
        BorderPane setup = new BorderPane(new Label("setup"));
        box.getChildren().add(setup);
        setup.setMinSize(135,50);
        setup.setBackground(new Background(new BackgroundFill(Color.YELLOW, CornerRadii.EMPTY, Insets.EMPTY)));
        vBox.getChildren().add(box);
    }

    private static void createLoop(VBox vBox) {
        VBox box = new VBox();
        BorderPane loop = new BorderPane(new Label("loop"));
        box.getChildren().add(loop);
        loop.setMinSize(135,50);
        loop.setBackground(new Background(new BackgroundFill(Color.YELLOW, CornerRadii.EMPTY, Insets.EMPTY)));
        vBox.getChildren().add(box);
    }

    private static void createEndLoop(VBox vBox) {
        VBox box = new VBox();
        BorderPane end = new BorderPane(new Label("end"));
        box.getChildren().add(end);
        end.setMinSize(135,30);
        end.setBackground(new Background(new BackgroundFill(Color.YELLOW, CornerRadii.EMPTY, Insets.EMPTY)));
        vBox.getChildren().add(box);
    }

    /*_____________Блоки Control______________*/

    private static void createIf(VBox vBox) {
        VBox box = new VBox();
        BorderPane ifP = new BorderPane(new Label("if"));
        BorderPane condP = new BorderPane(new TextField());
        box.getChildren().addAll(ifP, condP);
        ifP.setMinSize(115,50);
        ifP.setBackground(new Background(new BackgroundFill(Color.LIGHTGREEN, CornerRadii.EMPTY, Insets.EMPTY)));
        vBox.getChildren().add(box);
    }

    private static void createElse(VBox vBox) {
        VBox box = new VBox();
        BorderPane elseP = new BorderPane(new Label("else"));
        box.getChildren().addAll(elseP);
        elseP.setMinSize(115, 50);
        elseP.setBackground(new Background(new BackgroundFill(Color.LIGHTGREEN, CornerRadii.EMPTY, Insets.EMPTY)));
        vBox.getChildren().add(box);
    }

    private static void createFor(VBox vBox) {
        VBox box = new VBox();
        BorderPane forP = new BorderPane(new Label("for"));
        BorderPane predP = new BorderPane(new TextField());
        BorderPane condP = new BorderPane(new TextField());
        BorderPane postP = new BorderPane(new TextField());
        box.getChildren().addAll(forP, predP, condP, postP);
        forP.setMinSize(115,50);
        forP.setBackground(new Background(new BackgroundFill(Color.LIGHTGREEN, CornerRadii.EMPTY, Insets.EMPTY)));
        vBox.getChildren().add(box);
    }

    private static void createDo(VBox vBox) {
        VBox box = new VBox();
        BorderPane doP = new BorderPane(new Label("do"));
        BorderPane condP = new BorderPane(new TextField());
        box.getChildren().addAll(doP, condP);
        doP.setMinSize(115,50);
        doP.setBackground(new Background(new BackgroundFill(Color.LIGHTGREEN, CornerRadii.EMPTY, Insets.EMPTY)));
        vBox.getChildren().add(box);
    }

    private static void createWhile(VBox vBox) {
        VBox box = new VBox();
        BorderPane whileP = new BorderPane(new Label("while"));
        BorderPane condP = new BorderPane(new TextField());
        box.getChildren().addAll(whileP, condP);
        whileP.setMinSize(115,50);
        whileP.setBackground(new Background(new BackgroundFill(Color.LIGHTGREEN, CornerRadii.EMPTY, Insets.EMPTY)));
        vBox.getChildren().add(box);
    }

    private static void createBreak(VBox vBox) {
        VBox box = new VBox();
        BorderPane breakP = new BorderPane(new Label("break"));
        box.getChildren().add(breakP);
        breakP.setMinSize(115,50);
        breakP.setBackground(new Background(new BackgroundFill(Color.LIGHTGREEN, CornerRadii.EMPTY, Insets.EMPTY)));
        vBox.getChildren().add(box);
    }

    private static void createContinue(VBox vBox) {
        VBox box = new VBox();
        BorderPane continueP = new BorderPane(new Label("continue"));
        box.getChildren().add(continueP);
        continueP.setMinSize(115,50);
        continueP.setBackground(new Background(new BackgroundFill(Color.LIGHTGREEN, CornerRadii.EMPTY, Insets.EMPTY)));
        vBox.getChildren().add(box);
    }

    private static void createReturn(VBox vBox) {
        VBox box = new VBox();
        BorderPane returnP = new BorderPane(new Label("return"));
        box.getChildren().add(returnP);
        returnP.setMinSize(115,50);
        returnP.setBackground(new Background(new BackgroundFill(Color.LIGHTGREEN, CornerRadii.EMPTY, Insets.EMPTY)));
        vBox.getChildren().add(box);
    }

    private static void createEnd(VBox vBox) {
        VBox box = new VBox();
        BorderPane endP = new BorderPane(new Label("end"));
        box.getChildren().add(endP);
        endP.setMinSize(115,30);
        endP.setBackground(new Background(new BackgroundFill(Color.LIGHTGREEN, CornerRadii.EMPTY, Insets.EMPTY)));
        vBox.getChildren().add(box);
    }

    /*_____________Блоки Time______________*/

    private static void createMillis(VBox vBox) {
        VBox box = new VBox();
        BorderPane millisP = new BorderPane(new Label("millis"));
        box.getChildren().add(millisP);
        millisP.setMinSize(115, 50);
        millisP.setBackground(new Background(new BackgroundFill(Color.LIGHTCYAN, CornerRadii.EMPTY, Insets.EMPTY)));
        vBox.getChildren().add(box);
    }

    private static void createMicros(VBox vBox) {
        VBox box = new VBox();
        BorderPane microsP = new BorderPane(new Label("micros"));
        box.getChildren().add(microsP);
        microsP.setMinSize(115,50);
        microsP.setBackground(new Background(new BackgroundFill(Color.LIGHTCYAN, CornerRadii.EMPTY, Insets.EMPTY)));
        vBox.getChildren().add(box);
    }

    private static void createDelay(VBox vBox) {
        VBox box = new VBox();
        BorderPane delayP = new BorderPane(new Label("delay"));
        BorderPane argP = new BorderPane(new TextField());
        box.getChildren().addAll(delayP, argP);
        delayP.setMinSize(115,50);
        delayP.setBackground(new Background(new BackgroundFill(Color.LIGHTCYAN, CornerRadii.EMPTY, Insets.EMPTY)));
        vBox.getChildren().add(box);
    }

    private static void createDelayMicros(VBox vBox) {
        VBox box = new VBox();
        BorderPane delayMicrosP = new BorderPane(new Label("delayMicros"));
        BorderPane argP = new BorderPane(new TextField());
        box.getChildren().addAll(delayMicrosP, argP);
        delayMicrosP.setMinSize(115,50);
        delayMicrosP.setBackground(new Background(new BackgroundFill(Color.LIGHTCYAN, CornerRadii.EMPTY, Insets.EMPTY)));
        vBox.getChildren().add(box);
    }

    /*_____________Блоки Data Types______________*/

    private static void createStatement(VBox vBox) {
        VBox box = new VBox();
        BorderPane statementP = new BorderPane(new Label("statement"));
        BorderPane contentP = new BorderPane(new TextField());
        box.getChildren().addAll(statementP, contentP);
        statementP.setMinSize(115, 50);
        statementP.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
        vBox.getChildren().add(box);
    }

    private static void createBoolean(VBox vBox) {
        BorderPane booleanP = new BorderPane(new Label("boolean"));
        booleanP.setMinSize(115,50);
        booleanP.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
        vBox.getChildren().add(booleanP);
    }

    private static void createChar(VBox vBox) {
        BorderPane charP = new BorderPane(new Label("char"));
        charP.setMinSize(115,50);
        charP.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
        vBox.getChildren().add(charP);
    }

    private static void createByte(VBox vBox) {
        BorderPane byteP = new BorderPane(new Label("byte"));
        byteP.setMinSize(115,50);
        byteP.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
        vBox.getChildren().add(byteP);
    }

    private static void createInt(VBox vBox) {
        BorderPane IntP = new BorderPane(new Label("int"));
        IntP.setMinSize(115,50);
        IntP.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
        vBox.getChildren().add(IntP);
    }

    private static void createWord(VBox vBox) {
        BorderPane wordP = new BorderPane(new Label("word"));
        wordP.setMinSize(115,50);
        wordP.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
        vBox.getChildren().add(wordP);
    }

    private static void createLong(VBox vBox) {
        BorderPane longP = new BorderPane(new Label("long"));
        longP.setMinSize(115,50);
        longP.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
        vBox.getChildren().add(longP);
    }

    private static void createShort(VBox vBox) {
        BorderPane shortP = new BorderPane(new Label("short"));
        shortP.setMinSize(115,50);
        shortP.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
        vBox.getChildren().add(shortP);
    }

    private static void createFloat(VBox vBox) {
        BorderPane floatP = new BorderPane(new Label("float"));
        floatP.setMinSize(115,50);
        floatP.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
        vBox.getChildren().add(floatP);
    }

    private static void createDouble(VBox vBox) {
        BorderPane DoubleP = new BorderPane(new Label("double"));
        DoubleP.setMinSize(115,50);
        DoubleP.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
        vBox.getChildren().add(DoubleP);
    }

    private static void createString(VBox vBox) {
        BorderPane stringP = new BorderPane(new Label("string"));
        stringP.setMinSize(115,50);
        stringP.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
        vBox.getChildren().add(stringP);
    }

    /*_____________Блоки Digital______________*/

    private static void createPinMode(VBox vBox) {
        VBox box = new VBox();
        BorderPane pinModeP = new BorderPane(new Label("pinMode"));
        BorderPane pinP = new BorderPane(new TextField());
        BorderPane modeP = new BorderPane(new TextField());
        box.getChildren().addAll(pinModeP, pinP, modeP);
        pinModeP.setMinSize(115, 50);
        pinModeP.setBackground(new Background(new BackgroundFill(Color.KHAKI, CornerRadii.EMPTY, Insets.EMPTY)));
        vBox.getChildren().add(box);
    }

    private static void createDigWrite(VBox vBox) {
        VBox box = new VBox();
        BorderPane digWriteP = new BorderPane(new Label("digitalWrite"));
        BorderPane pinP = new BorderPane(new TextField());
        BorderPane modeP = new BorderPane(new TextField());
        box.getChildren().addAll(digWriteP, pinP, modeP);
        digWriteP.setMinSize(115,50);
        digWriteP.setBackground(new Background(new BackgroundFill(Color.KHAKI, CornerRadii.EMPTY, Insets.EMPTY)));
        vBox.getChildren().add(box);
    }

    private static void createDigRead(VBox vBox) {
        VBox box = new VBox();
        BorderPane digReadP = new BorderPane(new Label("digitalRead"));
        BorderPane pinP = new BorderPane(new TextField());
        box.getChildren().addAll(digReadP, pinP);
        digReadP.setMinSize(115,50);
        digReadP.setBackground(new Background(new BackgroundFill(Color.KHAKI, CornerRadii.EMPTY, Insets.EMPTY)));
        vBox.getChildren().add(box);
    }

    /*_____________Блоки Analog______________*/

    private static void createAnRefer(VBox vBox) {
        BorderPane anReferP = new BorderPane(new Label("analogReference"));
        anReferP.setMinSize(115, 50);
        anReferP.setBackground(new Background(new BackgroundFill(Color.KHAKI, CornerRadii.EMPTY, Insets.EMPTY)));
        vBox.getChildren().add(anReferP);
    }

    private static void createAnWrite(VBox vBox) {
        BorderPane AnWriteP = new BorderPane(new Label("analogWrite"));
        AnWriteP.setMinSize(115,50);
        AnWriteP.setBackground(new Background(new BackgroundFill(Color.KHAKI, CornerRadii.EMPTY, Insets.EMPTY)));
        vBox.getChildren().add(AnWriteP);
    }

    private static void createAnRead(VBox vBox) {
        BorderPane anReadP = new BorderPane(new Label("analogRead"));
        anReadP.setMinSize(115,50);
        anReadP.setBackground(new Background(new BackgroundFill(Color.KHAKI, CornerRadii.EMPTY, Insets.EMPTY)));
        vBox.getChildren().add(anReadP);
    }

    /*_____________Блоки Bits/Bytes______________*/

    private static void createlByte(VBox vBox) {
        BorderPane lowByteP = new BorderPane(new Label("lowByte"));
        lowByteP.setMinSize(115,50);
        lowByteP.setBackground(new Background(new BackgroundFill(Color.LIGHTGREEN, CornerRadii.EMPTY, Insets.EMPTY)));
        vBox.getChildren().add(lowByteP);
    }

    private static void createhByte(VBox vBox) {
        BorderPane highByteP = new BorderPane(new Label("highByte"));
        highByteP.setMinSize(115, 50);
        highByteP.setBackground(new Background(new BackgroundFill(Color.LIGHTGREEN, CornerRadii.EMPTY, Insets.EMPTY)));
        vBox.getChildren().add(highByteP);
    }

    private static void createBitRe(VBox vBox) {
        BorderPane bitReadP = new BorderPane(new Label("bitRead"));
        bitReadP.setMinSize(115,50);
        bitReadP.setBackground(new Background(new BackgroundFill(Color.LIGHTGREEN, CornerRadii.EMPTY, Insets.EMPTY)));
        vBox.getChildren().add(bitReadP);
    }

    private static void createBitWr(VBox vBox) {
        BorderPane bitWriteP = new BorderPane(new Label("bitWrite"));
        bitWriteP.setMinSize(115,50);
        bitWriteP.setBackground(new Background(new BackgroundFill(Color.LIGHTGREEN, CornerRadii.EMPTY, Insets.EMPTY)));
        vBox.getChildren().add(bitWriteP);
    }

    private static void createBitSet(VBox vBox) {
        BorderPane bitSetP = new BorderPane(new Label("bitSet"));
        bitSetP.setMinSize(115,50);
        bitSetP.setBackground(new Background(new BackgroundFill(Color.LIGHTGREEN, CornerRadii.EMPTY, Insets.EMPTY)));
        vBox.getChildren().add(bitSetP);
    }

    private static void createBitClear(VBox vBox) {
        BorderPane bitClearP = new BorderPane(new Label("bitClear"));
        bitClearP.setMinSize(115,50);
        bitClearP.setBackground(new Background(new BackgroundFill(Color.LIGHTGREEN, CornerRadii.EMPTY, Insets.EMPTY)));
        vBox.getChildren().add(bitClearP);
    }

    private static void createBit(VBox vBox) {
        BorderPane bitP = new BorderPane(new Label("bit"));
        bitP.setMinSize(115,50);
        bitP.setBackground(new Background(new BackgroundFill(Color.LIGHTGREEN, CornerRadii.EMPTY, Insets.EMPTY)));
        vBox.getChildren().add(bitP);
    }

    private static void transform(VBox vBox) {
        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.newDocument();
            Main.setDocument(doc);
            Element rootElement = doc.createElement("listing");
            getStack().push(rootElement);
            doc.appendChild(rootElement);

            generateXML(vBox.getChildren());

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("input.xml"));

            transformer.transform(source, result);

            new XMLParser(new ArduinoCodeCreator()).processInput();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void generateXML(ObservableList<Node> nodes) {
        for (int i = 0; i < nodes.size(); i++) {
            VBox currentNode = (VBox)nodes.get(i);
            if (currentNode.getChildren() != null) {
                BorderPane namePane = (BorderPane) currentNode.getChildren().get(0);
                Label label = (Label) namePane.getChildren().get(0);
                switch (label.getText()) {
                    case "setup":
                    case "loop":
                    case "else":
                        getStack().push(CreateNoArgXMLNode(getStack().peek(), label));
                        break;
                    case "break":
                    case "continue":
                    case "return":
                        CreateNoArgXMLNode(getStack().peek(), label);
                        break;
                    case "if":
                    case "do":
                    case "while":
                        getStack().push(CreateConditionalXMLNode(currentNode, getStack().peek(), label));
                        break;
                    case "for":
                        getStack().push(CreateForCycleXMLNode(currentNode, getStack().peek(), label));
                        break;
                    case "statement":
                        CreateStatementXMLNode(currentNode, getStack().peek(), label);
                        break;
                    case "delay":
                        CreateOneArgXMLNode(currentNode, getStack().peek(), label);
                        break;
                    case "pinMode":
                    case "digitalWrite":
                        CreateManyArgsXMLNode(currentNode, getStack().peek(), label);
                        break;
                    case "end":
                        getStack().pop();
                        break;
                    default:
                }
            }
        }
    }

    private static Element CreateNoArgXMLNode(Element root, Label l) {
        Element currentNode = Main.getDocument().createElement(l.getText());
        root.appendChild(currentNode);
        return currentNode;
    }

    private static Element CreateConditionalXMLNode(VBox vBox, Element root, Label l) {
        Element currentNode = Main.getDocument().createElement(l.getText());
        root.appendChild(currentNode);
        BorderPane argsPane = (BorderPane) vBox.getChildren().get(1);
        TextField t = (TextField) argsPane.getChildren().get(0);
        Attr attr = Main.getDocument().createAttribute("condition");
        attr.setValue(t.getText());
        currentNode.setAttributeNode(attr);
        return currentNode;
    }

    private static Element CreateForCycleXMLNode(VBox vBox, Element root, Label l) {
        Element currentNode = Main.getDocument().createElement(l.getText());
        root.appendChild(currentNode);
        BorderPane argsPane1 = (BorderPane) vBox.getChildren().get(1);
        TextField pred = (TextField) argsPane1.getChildren().get(0);
        BorderPane argsPane2 = (BorderPane) vBox.getChildren().get(2);
        TextField cond = (TextField) argsPane2.getChildren().get(0);
        BorderPane argsPane3 = (BorderPane) vBox.getChildren().get(3);
        TextField post = (TextField) argsPane3.getChildren().get(0);
        Attr attr = Main.getDocument().createAttribute("condition");
        attr.setValue(pred.getText() + "; " + cond.getText() + "; " + post.getText());
        currentNode.setAttributeNode(attr);
        return currentNode;
    }

    private static Element CreateStatementXMLNode(VBox vBox, Element root, Label l) {
        Element currentNode = Main.getDocument().createElement(l.getText());
        root.appendChild(currentNode);
        BorderPane argsPane = (BorderPane) vBox.getChildren().get(1);
        TextField t = (TextField) argsPane.getChildren().get(0);
        Attr attr = Main.getDocument().createAttribute("content");
        attr.setValue(t.getText());
        currentNode.setAttributeNode(attr);
        return currentNode;
    }

    private static Element CreateOneArgXMLNode(VBox vBox, Element root, Label l) {
        Element currentNode = Main.getDocument().createElement(l.getText());
        root.appendChild(currentNode);
        BorderPane argsPane = (BorderPane) vBox.getChildren().get(1);
        TextField t = (TextField) argsPane.getChildren().get(0);
        Attr attr = Main.getDocument().createAttribute("arg");
        attr.setValue(t.getText());
        currentNode.setAttributeNode(attr);
        return currentNode;
    }

    private static Element CreateManyArgsXMLNode(VBox vBox, Element root, Label l) {
        Element currentNode = Main.getDocument().createElement(l.getText());
        root.appendChild(currentNode);
        ObservableList panes = vBox.getChildren();
        for (int i = 1; i < panes.size(); i++) {
            BorderPane argsPane = (BorderPane) panes.get(i);
            TextField t = (TextField) argsPane.getChildren().get(0);
            Attr attr = Main.getDocument().createAttribute("arg" + i);
            attr.setValue(t.getText());
            currentNode.setAttributeNode(attr);
        }
        return currentNode;
    }
}
