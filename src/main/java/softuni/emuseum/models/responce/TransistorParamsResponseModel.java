package softuni.emuseum.models.responce;

public class TransistorParamsResponseModel {

    private String type;
    private String maxVoltage;
    private String maxCurrent;
    private String maxFrequency;
    private String gain;

    public TransistorParamsResponseModel() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMaxVoltage() {
        return maxVoltage;
    }

    public void setMaxVoltage(String maxVoltage) {
        this.maxVoltage = maxVoltage;
    }

    public String getMaxCurrent() {
        return maxCurrent;
    }

    public void setMaxCurrent(String maxCurrent) {
        this.maxCurrent = maxCurrent;
    }

    public String getMaxFrequency() {
        return maxFrequency;
    }

    public void setMaxFrequency(String maxFrequency) {
        this.maxFrequency = maxFrequency;
    }

    public String getGain() { return gain; }

    public void setGain(String gain) { this.gain = gain; }
}
