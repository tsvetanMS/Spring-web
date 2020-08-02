package softuni.emuseum.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="diodes")
public class Diode extends BaseEntity{

    private String pictureID;
    private String description;
    private String type;
    private String maxVoltage;
    private String maxCurrent;

    public Diode() {
    }

    public Diode(String pictureID, String description, String type, String maxVoltage, String maxCurrent) {
        this.pictureID = pictureID;
        this.description = description;
        this.type = type;
        this.maxVoltage = maxVoltage;
        this.maxCurrent = maxCurrent;
    }
//----------------------------------------------------------------------------------------------------------------------
    @Column(name="picture_id", length = 33)
    public String getPictureID() {
        return pictureID;
    }

    public void setPictureID(String pictureURL) {
        this.pictureID = pictureURL;
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
}
