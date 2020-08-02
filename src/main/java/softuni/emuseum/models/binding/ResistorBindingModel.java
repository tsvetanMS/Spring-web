package softuni.emuseum.models.binding;

import org.hibernate.validator.constraints.Length;
import softuni.emuseum.constants.Constants;

public class ResistorBindingModel {

    private Long id;
    private String pictureID;
    private String description;
    private String type;
    private String resistance;
    private String maxPower;
    private String precision;



    public ResistorBindingModel() {
    }


//----------------------------------------------------------------------------------------------------------------------
    public Long getId() { return id; }

    public void setId(Long id) {
        this.id = id;
    }
//----------------------------------------------------------------------------------------------------------------------
    @Length(min = 33, max = 33, message = Constants.FIELD_EXACTLY_33)
    public String getPictureID() {
        return pictureID;
    }

    public void setPictureID(String pictureID) {
        this.pictureID = pictureID;
    }
//----------------------------------------------------------------------------------------------------------------------
    @Length(min = 3, message = Constants.FIELD_MIN_3)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
//----------------------------------------------------------------------------------------------------------------------
    @Length(max = 50, message = Constants.FIELD_MAX_50)
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
//----------------------------------------------------------------------------------------------------------------------
    @Length(max = 50, message = Constants.FIELD_MAX_50)
    public String getResistance() {
        return resistance;
    }

    public void setResistance(String resistance) {
        this.resistance = resistance;
    }
//----------------------------------------------------------------------------------------------------------------------
    @Length(max = 50, message = Constants.FIELD_MAX_50)
    public String getMaxPower() {
        return maxPower;
    }

    public void setMaxPower(String maxPower) {
        this.maxPower = maxPower;
    }
//----------------------------------------------------------------------------------------------------------------------
    @Length(max = 50, message = Constants.FIELD_MAX_50)
    public String getPrecision() {
        return precision;
    }

    public void setPrecision(String precision) {
        this.precision = precision;
    }
//----------------------------------------------------------------------------------------------------------------------
}
