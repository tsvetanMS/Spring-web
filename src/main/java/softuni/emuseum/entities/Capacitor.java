package softuni.emuseum.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="capacitors")
public class Capacitor extends BaseEntity{

    private String pictureID;
    private String description;
    private String type;
    private String capacitance;
    private String maxVoltage;

    public Capacitor() {
    }

    public Capacitor(String pictureID, String description, String type, String capacitance, String maxVoltage) {
        this.pictureID = pictureID;
        this.description = description;
        this.type = type;
        this.capacitance = capacitance;
        this.maxVoltage = maxVoltage;
    }
//----------------------------------------------------------------------------------------------------------------------
    @Column(name="picture_id", length = 33, nullable = false)
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
    @Column(name="capacitance", length = 50)
    public String getCapacitance() {
        return capacitance;
    }

    public void setCapacitance(String value) {
        this.capacitance = value;
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
}
