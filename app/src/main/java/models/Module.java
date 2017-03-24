package models;

/**
 * Created by menuka on 2/21/17.
 */

public class Module {
    private String moduleId;
    private String name;
    private String code;
    private String grade;
    private String credits;

    public Module() {}

    public Module(String code, String name, String grade, String credits) {
        this.name = name;
        this.code = code;
        this.grade = grade;
        this.credits = credits;
    }

    @Override
    public String toString() {
        return "Module{" +
                "moduleId='" + moduleId + '\'' +
                ", name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", grade='" + grade + '\'' +
                ", credits='" + credits + '\'' +
                '}';
    }

    public String getModuleId() {
        return moduleId;
    }

    public void setModuleId(String moduleId) {
        this.moduleId = moduleId;
    }

    public String getCredits() {
        return credits;
    }

    public void setCredits(String credits) {
        this.credits = credits;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }
}
