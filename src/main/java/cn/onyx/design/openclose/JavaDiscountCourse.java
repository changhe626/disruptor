package cn.onyx.design.openclose;

public class JavaDiscountCourse extends JavaCourse {

    public JavaDiscountCourse(Integer id, String name, Double price) {
        super(id, name, price);
    }


    /**
     * 获取原价
     * @return
     */
    public Double getOriginPrice(){
        return super.getPrice();
    }

    /**
     * 打8折
     * @return
     */
    @Override
    public Double getPrice() {
        return super.getPrice()*0.8;
    }


}
