/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xmasfxml;

import java.util.Random;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import com.jfoenix.controls.*;
import java.io.File;
import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.animation.PathTransition;
import javafx.animation.TranslateTransitionBuilder;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.CubicCurve;
import javafx.util.Duration;

/**
 *
 * @author zhongqi
 */
public class FXMLDocumentController implements Initializable {

    private Ornament ornament;
    private Light light;
    private Present present;
    private Background background;
    private DateTimeReader dateTimeReader;
    private RemoteControl rc;
    private CommandHistory ch;
    private MediaPlayer mediaPlayer;
    private boolean party = false;
    private Random random = new Random();

    @FXML
    private Group ornamentsGroup;
    @FXML
    private Group lightsGroup;
    @FXML
    private Group presentsGroup;
    @FXML
    private Group lightsGroupFlashing;

    @FXML
    private ImageView treeBackground;
    @FXML
    private JFXToggleButton ornamentsToggleBtn;
    @FXML
    private JFXToggleButton lightsToggleBtn;
    @FXML
    private JFXToggleButton presentsToggleBtn;
    @FXML
    private JFXToggleButton addAllToggleBtn;
    @FXML
    private JFXToggleButton partyButton;
    @FXML
    private ImageView santaPic;
    @FXML
    private AnchorPane anchorPane;
    @FXML
    Button exitBtn;

    @FXML
    private void exitSystem() {
        System.exit(0);
    }

    @FXML
    private void onOrnamentToggleChange() {
        Boolean toggled = this.ornamentsToggleBtn.selectedProperty().asObject().getValue();
        toggleOrnaments();
        this.toggleOrnaments();
        if (toggled) {
            showOrnaments();

            // check if other toggle is on, then auto set the toggle all to true;
            if (this.lightsToggleBtn.selectedProperty().asObject().getValue()
                    && this.presentsToggleBtn.selectedProperty().asObject().getValue()) {
                this.addAllToggleBtn.setSelected(true);
            }
        } else {
            hideOrnaments();
            if (this.addAllToggleBtn.selectedProperty().asObject().getValue()) {
                this.addAllToggleBtn.setSelected(false);
            }
        }
    }

    @FXML
    private void onLightToggleChange() {
        Boolean toggled = this.lightsToggleBtn.selectedProperty().asObject().getValue();
        toggleLights();
        this.toggleLights();
        if (toggled) {
            showLights();
            // check if other toggle is on, then auto set the toggle all to true;
            if (this.ornamentsToggleBtn.selectedProperty().asObject().getValue()
                    && this.presentsToggleBtn.selectedProperty().asObject().getValue()) {
                this.addAllToggleBtn.setSelected(true);
            }
        } else {
            hideLights();
            if (this.addAllToggleBtn.selectedProperty().asObject().getValue()) {
                this.addAllToggleBtn.setSelected(false);
            }
        }
    }

    @FXML
    private void onPresentsToggleChange() {
        Boolean toggled = this.presentsToggleBtn.selectedProperty().asObject().getValue();
        updateTime();
        togglePresents();
        this.togglePresents();
        if (toggled) {
            showPresents();

            // check if other toggle is on, then auto set the toggle all to true;
            if (this.lightsToggleBtn.selectedProperty().asObject().getValue()
                    && this.ornamentsToggleBtn.selectedProperty().asObject().getValue()) {
                this.addAllToggleBtn.setSelected(true);
            }
        } else {
            hidePresents();
            if (this.addAllToggleBtn.selectedProperty().asObject().getValue()) {
                this.addAllToggleBtn.setSelected(false);
            }
        }
    }

    @FXML
    private void onAddAllToggleChange() {
        Boolean toggled = this.addAllToggleBtn.selectedProperty().asObject().getValue();
        updateTime();
        toggleAll();
        if (toggled) {
            if (!this.presentsToggleBtn.selectedProperty().asObject().getValue()) {
                this.presentsToggleBtn.setSelected(true);
                this.onPresentsToggleChange();
            }
            if (!this.lightsToggleBtn.selectedProperty().asObject().getValue()) {
                this.lightsToggleBtn.setSelected(true);
                this.onLightToggleChange();
            }
            if (!this.ornamentsToggleBtn.selectedProperty().asObject().getValue()) {
                this.ornamentsToggleBtn.setSelected(true);
                this.onOrnamentToggleChange();
            }
        } else {
            this.presentsToggleBtn.setSelected(false);
            this.onPresentsToggleChange();
            this.lightsToggleBtn.setSelected(false);
            this.onLightToggleChange();
            this.ornamentsToggleBtn.setSelected(false);
            this.onOrnamentToggleChange();
        }

    }

    @FXML
    public void togglePartyMode() {
        party = !party;
        if (party) {
            playMusic();

            animateSanta();

            flashingChristmasLight();
            
            letTheSnowFall();

        } else {
            stopMusic();

            hideSanta();

            stopFlashingChristmasLight();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        dateTimeReader = new DateTimeReader();
        rc = new RemoteControl();
        ch = CommandHistory.getInstance();

        light = new Light();
        ornament = new Ornament();
        present = new Present();

        LightShowCommand lsc = new LightShowCommand(light);
        LightHideCommand lhc = new LightHideCommand(light);
        OrnamentShowCommand osc = new OrnamentShowCommand(ornament);
        OrnamentHideCommand ohc = new OrnamentHideCommand(ornament);
        PresentShowCommand psc = new PresentShowCommand(present);
        PresentHideCommand phc = new PresentHideCommand(present);

        rc.setCommand(lsc, lhc);
        rc.setCommand(osc, ohc);
        rc.setCommand(psc, phc);

        setupImage();
    }

    private void setupImage() {
        background = new Background(dateTimeReader, treeBackground);
    }

    private void showOrnaments() {
        this.ornamentsGroup.setVisible(true);
    }

    private void hideOrnaments() {
        this.ornamentsGroup.setVisible(false);
    }

    private void showLights() {
        this.lightsGroup.setVisible(true);
    }

    private void hideLights() {
        this.lightsGroup.setVisible(false);
    }

    private void showPresents() {
        this.presentsGroup.setVisible(true);
    }

    private void hidePresents() {
        this.presentsGroup.setVisible(false);
    }
    
    public void updateTime() {
        long time = System.currentTimeMillis();
        dateTimeReader.setTime(time);
    }

    private void toggleOrnaments() {
        String log = "";
        if (!ornament.isOrnament()) {
            log = rc.showButtonPushed(1);
        } else {
            log = rc.hideButtonPushed(1);
        }
        ch.log(log);
    }

    private void toggleLights() {
        String log = "";
        if (!light.isLight()) {
            log = rc.showButtonPushed(0);
        } else {
            log = rc.hideButtonPushed(0);
        }
        ch.log(log);
    }

    private void togglePresents() {
        String log = "";
        if (!present.isPresent()) {
            log = rc.showButtonPushed(2);
        } else {
            log = rc.hideButtonPushed(2);
        }
        ch.log(log);
    }
    
    private void toggleAll() {
        String log = "";
        System.out.println(light.isLight() + " " + present.isPresent() + " " + ornament.isOrnament());
        if (light.isLight() && present.isPresent() && ornament.isOrnament()) {
            log = rc.hideAll();
            System.out.println("Hidden all");
        } else {
            log = rc.showAll();
            System.out.println("Show all");
        }
        ch.log(log);
    }

    public void playMusic() {
        Media song = new Media(new File("src/jingle-bells-country.mp3").toURI().toString());
        mediaPlayer = new MediaPlayer(song);
        mediaPlayer.setOnEndOfMedia(new Runnable() {

            @Override
            public void run() {
                mediaPlayer.seek(Duration.ZERO);
            }
        });

        mediaPlayer.play();
    }

    public void animateSanta() {
        Image image = new Image("santa1.png");
        santaPic.setImage(image);
        santaPic.setVisible(true);

        CubicCurve curve = new CubicCurve(203, 254, -50, 164, 162, -23, -200, -94);

        PathTransition transition = new PathTransition();
        transition.setNode(santaPic);
        transition.setDuration(Duration.seconds(3));
        transition.setPath(curve);
        transition.setCycleCount(PathTransition.INDEFINITE);
        transition.play();
    }

    private void stopMusic() {
        mediaPlayer.stop();
    }

    private void hideSanta() {
        santaPic.setVisible(false);
    }

    private void flashingChristmasLight() {
        lightsGroupFlashing.setVisible(true);
        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(0.5), lightsGroupFlashing);
        fadeTransition.setFromValue(1.0);
        fadeTransition.setToValue(0.0);
        fadeTransition.setCycleCount(Animation.INDEFINITE);
        fadeTransition.play();
    }

    public void stopFlashingChristmasLight() {
        lightsGroupFlashing.setVisible(false);
    }

    public void letTheSnowFall() {
        Circle c[] = new Circle[2000];
        
        for (int i = 0; i < 2000; i++) {
            c[i] = new Circle(1,1,1);
            c[i].setRadius(random.nextDouble() * 3);
            Color color = Color.rgb(255, 255, 255, random.nextDouble());
            c[i].setFill(color);
            anchorPane.getChildren().add(c[i]);
            Raining(c[i]);
        }
    }

    public void Raining(Circle c) {
        c.setCenterX(random.nextInt(950));
        int time = 10 + random.nextInt(50);
        Animation walk = TranslateTransitionBuilder.create()
                .node(c)
                .fromY(-200)
                .toY(534 + 200)
                .toX(random.nextDouble() * c.getCenterX())
                .duration(Duration.seconds(time))
                .onFinished(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        Raining(c);
                    }

                }).build();
        walk.play();
    }

}
