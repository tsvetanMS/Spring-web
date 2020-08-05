package softuni.emuseum.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="transistors")
public class Transistor extends BaseEntity{

    private String pictureID;
    private String description;
    private String type;
    private String maxVoltage;
    private String maxCurrent;
    private String maxFrequency;
    private String gain;

    public Transistor() {
    }

    public Transistor(String pictureID, String description, String type, String maxVoltage, String maxCurrent, String maxFrequency, String gain) {
        this.pictureID = pictureID;
        this.description = description;
        this.type = type;
        this.maxVoltage = maxVoltage;
        this.maxCurrent = maxCurrent;
        this.maxFrequency = maxFrequency;
        this.gain = gain;
    }
//----------------------------------------------------------------------------------------------------------------------
    @Column(name="picture_id", length = 33, nullable = false)
    public String getPictureID() {
        return pictureID;
    }

    public void setPictureID(String pictureID) {
        this.pictureID = pictureID;
    }
//----------------------------------------------------------------------------------------------------------------------
    @Column(name="description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
//----------------------------------------------------------------------------------------------------------------------
    @Column(name="type", length = 50)
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
//----------------------------------------------------------------------------------------------------------------------
    @Column(name="max_voltage", length = 50)
    public String getMaxVoltage() {
        return maxVoltage;
    }

    public void setMaxVoltage(String maxVoltage) {
        this.maxVoltage = maxVoltage;
    }
//----------------------------------------------------------------------------------------------------------------------
    @Column(name="max_current", length = 50)
    public String getMaxCurrent() {
        return maxCurrent;
    }

    public void setMaxCurrent(String maxCurrent) {
        this.maxCurrent = maxCurrent;
    }
//----------------------------------------------------------------------------------------------------------------------
    @Column(name="max_frequency", length = 50)
    public String getMaxFrequency() {
        return maxFrequency;
    }

    public void setMaxFrequency(String maxFrequency) {
        this.maxFrequency = maxFrequency;
    }
//----------------------------------------------------------------------------------------------------------------------
    @Column(name="gain", length = 50)
    public String getGain() { return gain; }

    public void setGain(String gain) { this.gain = gain; }
//----------------------------------------------------------------------------------------------------------------------
}
