package cn.onyx.design.depenceinversion;

public class PythonCourse implements ICourse {
    @Override
    public void studyCourse() {
        System.out.println("python");
    }
}
