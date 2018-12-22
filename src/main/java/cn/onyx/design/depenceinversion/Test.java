package cn.onyx.design.depenceinversion;

public class Test {

    public static void main(String[] args) {

        Onyx onyx = new Onyx();
        onyx.studyCourse(new JavaCourse());
        onyx.studyCourse(new PythonCourse());



    }

}
