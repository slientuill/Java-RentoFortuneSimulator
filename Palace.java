package io.renren.modules.jxgk.utils;

public class Palace implements Comparable<Palace>{
    private String name;
    private int fee;
    private int position;
    private int onCount;
    private int onFeeCount;
    public void initSet(String name,int fee,int position){
        this.position=position;
        this.name=name;
        this.fee=fee;
    }

    public int getOnCount() {
        return onCount;
    }

    public void setOnCount(int onCount) {
        this.onCount = onCount;
    }

    public int getOnFeeCount() {
        return onFeeCount;
    }

    public void setOnFeeCount(int onFeeCount) {
        this.onFeeCount = onFeeCount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getFee() {
        return fee;
    }

    public void setFee(int fee) {
        this.fee = fee;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
    @Override
    public int compareTo(Palace p) {
        return this.onFeeCount - p.onFeeCount;
    }
//    @Override
//    public int compareTo(Palace p) {
//        return this.onCount - p.onCount;
//    }
}
