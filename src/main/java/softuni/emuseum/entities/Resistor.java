package softuni.emuseum.entities;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="resistors")
public class Resistor extends BaseEntity {

    private String pictureID;
    private String description;
    private String type;
    private String resistance;
    private String maxPower;
    private String precision;

    public Resistor() {
    }

    public Resistor(String pictureID, String description, String type, String resistance, String maxPower, String precision) {
        this.pictureID = pictureID;
        this.description = description;
        this.type = type;
        this.resistance = resistance;
        this.maxPower = maxPower;
        this.precision = precision;
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
    @Column(name="resistance", length = 50)
    public String getResistance() {
        return resistance;
    }

    public void setResistance(String value) {
        this.resistance = value;
    }
//----------------------------------------------------------------------------------------------------------------------
    @Column(name="maxPower", length = 50)
    public String getMaxPower() {
        return maxPower;
    }

    public void setMaxPower(String maxPower) {
        this.maxPower = maxPower;
    }
//----------------------------------------------------------------------------------------------------------------------
    @Column(name="tolerance", length = 50)
    public String getPrecision() {
        return precision;
    }

    public void setPrecision(String precision) {
        this.precision = precision;
    }
//----------------------------------------------------------------------------------------------------------------------
}
