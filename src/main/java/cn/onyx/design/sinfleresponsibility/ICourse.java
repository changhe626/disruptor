package cn.onyx.design.sinfleresponsibility;

//总的接口,太多了,进行拆分,进行隔离
public interface ICourse {


    String getCourseName();
    byte[] getCourseVideo();

    void studyCourse();
    void refundCourse();



}
