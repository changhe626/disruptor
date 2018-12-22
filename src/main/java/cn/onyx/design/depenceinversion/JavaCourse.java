package cn.onyx.design.depenceinversion;

public class JavaCourse implements ICourse {
    @Override
    public void studyCourse() {
        System.out.println("java");
    }
}
