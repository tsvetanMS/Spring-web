package softuni.emuseum.models.binding;

import org.hibernate.validator.constraints.Length;
import softuni.emuseum.constants.Constants;

public class TransistorBindingModel {

    private Long id;
    private String pictureID;
    private String description;
    private String type;
    private String maxVoltage;
    private String maxCurrent;
    private String maxFrequency;
    private String gain;

    public TransistorBindingModel() {
    }


//----------------------------------------------------------------------------------------------------------------------
    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }
//----------------------------------------------------------------------------------------------------------------------
    @Length(min = 33, max = 33, message = Constants.FIELD_EXACTLY_33)
    public String getPictureID() { return pictureID; }

    public void setPictureID(String pictureID) { this.pictureID = pictureID; }
//----------------------------------------------------------------------------------------------------------------------
    @Length(min = 3, message = Constants.FIELD_MIN_3)
    public String getDescription() { return description; }

    public void setDescription(String description) { this.description = description; }
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
    public String getMaxVoltage() {
        return maxVoltage;
    }

    public void setMaxVoltage(String maxVoltage) {
        this.maxVoltage = maxVoltage;
    }
//----------------------------------------------------------------------------------------------------------------------
    @Length(max = 50, message = Constants.FIELD_MAX_50)
    public String getMaxCurrent() {
        return maxCurrent;
    }

    public void setMaxCurrent(String maxCurrent) {
        this.maxCurrent = maxCurrent;
    }
//----------------------------------------------------------------------------------------------------------------------
    @Length(max = 50, message = Constants.FIELD_MAX_50)
    public String getMaxFrequency() {
        return maxFrequency;
    }

    public void setMaxFrequency(String maxFrequency) {
        this.maxFrequency = maxFrequency;
    }
//----------------------------------------------------------------------------------------------------------------------
    @Length(max = 50, message = Constants.FIELD_MAX_50)
    public String getGain() { return gain; }

    public void setGain(String gain) { this.gain = gain; }
//----------------------------------------------------------------------------------------------------------------------
}
