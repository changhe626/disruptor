package cn.onyx.design.depenceinversion;

public class Onyx {

    //也可以使用构造器来进行设置.

    void studyCourse(ICourse course){
        course.studyCourse();
    }

}
