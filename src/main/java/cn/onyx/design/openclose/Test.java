package cn.onyx.design.openclose;

public class Test {

    public static void main(String[] args) {

        ICourse course = new JavaCourse(1, "java", 32.3);
        System.out.println(course.getPrice());


        ICourse course2 = new JavaDiscountCourse(1, "java", 32.3);
        System.out.println(course2.getPrice());

    }
}
