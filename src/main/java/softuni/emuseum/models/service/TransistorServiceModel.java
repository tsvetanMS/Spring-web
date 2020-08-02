package softuni.emuseum.models.service;

public class TransistorServiceModel {

    private Long id;
    private String pictureID;
    private String description;
    private String type;
    private String maxVoltage;
    private String maxCurrent;
    private String maxFrequency;
    private String gain;

    public TransistorServiceModel() {
    }

    public TransistorServiceModel(String pictureID, String description, String type, String maxVoltage, String maxCurrent, String maxFrequency, String gain) {
        this.pictureID = pictureID;
        this.description = description;
        this.type = type;
        this.maxVoltage = maxVoltage;
        this.maxCurrent = maxCurrent;
        this.maxFrequency = maxFrequency;
        this.gain = gain;
    }
//----------------------------------------------------------------------------------------------------------------------
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
//----------------------------------------------------------------------------------------------------------------------

    public String getPictureID() {
        return pictureID;
    }

    public void setPictureID(String pictureID) {
        this.pictureID = pictureID;
    }
//----------------------------------------------------------------------------------------------------------------------

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
//----------------------------------------------------------------------------------------------------------------------

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
//----------------------------------------------------------------------------------------------------------------------

    public String getMaxVoltage() {
        return maxVoltage;
    }

    public void setMaxVoltage(String maxVoltage) {
        this.maxVoltage = maxVoltage;
    }
//----------------------------------------------------------------------------------------------------------------------

    public String getMaxCurrent() {
        return maxCurrent;
    }

    public void setMaxCurrent(String maxCurrent) {
        this.maxCurrent = maxCurrent;
    }
//----------------------------------------------------------------------------------------------------------------------

    public String getMaxFrequency() {
        return maxFrequency;
    }

    public void setMaxFrequency(String maxFrequency) {
        this.maxFrequency = maxFrequency;
    }
//----------------------------------------------------------------------------------------------------------------------

    public String getGain() { return gain; }

    public void setGain(String gain) { this.gain = gain; }
//----------------------------------------------------------------------------------------------------------------------
}


