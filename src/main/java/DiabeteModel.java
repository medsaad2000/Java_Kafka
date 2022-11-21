import com.opencsv.bean.CsvBindByName;

public class DiabeteModel {
    @CsvBindByName(column = "Pregnancies")
    private int pregnancies;

    @CsvBindByName(column = "Glucose")
    private int glucose;

    @CsvBindByName(column = "BloodPressure")
    private int bloodPressure;

    @CsvBindByName(column = "SkinThickness")
    private int skinThickness;

    @CsvBindByName(column = "Insulin")
    private int insulin;

    @CsvBindByName(column = "BMI")
    private double bMI;

    @CsvBindByName(column = "DiabetesPedigreeFunction")
    private double diabetesPedigreeFunction;

    @CsvBindByName(column = "Age")
    private int age;

    @CsvBindByName(column = "Outcome")
    private String outcome;

    public int getPregnancies() {
        return pregnancies;
    }

    public void setPregnancies(int pregnancies) {
        this.pregnancies = pregnancies;
    }

    public int getGlucose() {
        return glucose;
    }

    public void setGlucose(int glucose) {
        this.glucose = glucose;
    }

    public int getBloodPressure() {
        return bloodPressure;
    }

    public void setBloodPressure(int bloodPressure) {
        this.bloodPressure = bloodPressure;
    }

    public int getSkinThickness() {
        return skinThickness;
    }

    public void setSkinThickness(int skinThickness) {
        this.skinThickness = skinThickness;
    }

    public int getInsulin() {
        return insulin;
    }

    public void setInsulin(int insulin) {
        this.insulin = insulin;
    }

    public double getbMI() {
        return bMI;
    }

    public void setbMI(double bMI) {
        this.bMI = bMI;
    }

    public double getDiabetesPedigreeFunction() {
        return diabetesPedigreeFunction;
    }

    public void setDiabetesPedigreeFunction(double diabetesPedigreeFunction) {
        this.diabetesPedigreeFunction = diabetesPedigreeFunction;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getOutcome() {
        return outcome;
    }

    public void setOutcome(String outcome) {
        this.outcome = outcome;
    }
}
