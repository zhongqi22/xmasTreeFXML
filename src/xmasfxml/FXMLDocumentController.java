/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xmasfxml;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import com.jfoenix.controls.*;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;

/**
 *
 * @author zhongqi
 */
public class FXMLDocumentController implements Initializable {

    private Ornament ornament;
    private Light light;
    private Present present;

    private DateTimeReader dateTimeReader;

    private RemoteControl rc;
    private CommandHistory ch;

    @FXML
    private Group ornamentsGroup;
    @FXML
    private Group lightsGroup;
    @FXML
    private Group presentsGroup;

    private Background background;
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
    Button exitBtn;

    @FXML
    private void exitSystem() {
        System.exit(0);
    }

    @FXML
    private void onOrnamentToggleChange() {
        Boolean toggled = this.ornamentsToggleBtn.selectedProperty().asObject().getValue();
        this.toggleOrnaments();
        if (toggled) {
            showOrnaments();
        } else {
            hideOrnaments();
        }
    }

    @FXML
    private void onLightToggleChange() {
        Boolean toggled = this.lightsToggleBtn.selectedProperty().asObject().getValue();
        this.toggleLights();
        if (toggled) {
            showLights();
        } else {
            hideLights();
        }
    }

    @FXML
    private void onPresentsToggleChange() {
        Boolean toggled = this.presentsToggleBtn.selectedProperty().asObject().getValue();
        this.togglePresents();
        if (toggled) {
            showPresents();
        } else {
            hidePresents();
        }
    }

    @FXML
    private void onAddAllToggleChange() {
        Boolean toggled = this.addAllToggleBtn.selectedProperty().asObject().getValue();
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

}
