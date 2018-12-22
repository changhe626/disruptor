package cn.onyx.design.sinfleresponsibility;

public class Test {

    public static void main(String[] args) {

        /*Bird bird = new Bird();
        bird.moveMode("大雁");
        bird.moveMode("小鸡");*/

        FlyBird flyBird = new FlyBird();
        WalkBird walkBird = new WalkBird();
        flyBird.moveMode("大雁");
        walkBird.moveMode("鸵鸟");

    }
}
