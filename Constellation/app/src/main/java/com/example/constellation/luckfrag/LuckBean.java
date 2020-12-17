package com.example.constellation.luckfrag;

import java.util.List;

public class LuckBean {

    /**
     * name : 金牛座
     * date : 2020年
     * year : 2020
     * mima : {"info":"保持从一而终的热爱","text":["在2020年，金牛座会有一种突然要大干一场的感觉，在自己的理想方面有了新的规划，也确立了全新的目标，那么接下来金牛们要做的就是保持这种干劲并且从一而终，不要半途而废，不要好高骛远，既然选择开始，就要一直坚持下去。与此同时，2020年也非常适合金牛座外出学习或进修，在2020年金牛们会感受到一股非常强劲的学习欲望，不管是哪个领域的知识都想要涉猎一下。在此建议金牛们要选定好和自己设立目标相关的领域进行学习，才能对完成自己的理想有所帮助。"]}
     * career : ["事业上，金牛座要多和自己的上司、下属进行互动，及时了解他们内心最真实的想法，有利于在职场上更加顺利地进行工作。学业上，金牛座要注意自己学习的节奏，有自己的节奏是好事，但是也要跟随大环境的进度加紧自己的学习速度，不然容易出现顾此失彼的问题。"]
     * love : ["金牛座在2020年的感情运势比较平稳，单身的金牛可以多出席一些社交场合或多参加一些户外活动，去结识一些和你在三观或者是兴趣爱好方面都比较契合的人。有伴的金牛在2020年要学会改掉自己固执的脾气和意气用事的性子，需要做出决定时，多和自己的另一半商量，两个人共同做出决定。"]
     * health : ["金牛在这一年里要多注意肠胃方面的问题，多喝水，少吃刺激性的食物，身体上一旦有不舒服不要隐瞒，要及时就医。"]
     * finance : ["金牛座2020年的财富运势比较平稳，平日里就有储蓄习惯的金牛在2020年会更加注意提升自己的理财能力。在投资方面，金牛座要注意自己所投资的物品的实际价值以及后期收益，综合经济大环境考虑更有利于金牛做出正确的投资决定。"]
     * luckeyStone : 红兔毛
     * future :
     * resultcode : 200
     * error_code : 0
     */

    private String name;
    private String date;
    private int year;
    private MimaBean mima;
    private String luckeyStone;
    private String future;
    private String resultcode;
    private int error_code;
    private List<String> career;
    private List<String> love;
    private List<String> health;
    private List<String> finance;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public MimaBean getMima() {
        return mima;
    }

    public void setMima(MimaBean mima) {
        this.mima = mima;
    }

    public String getLuckeyStone() {
        return luckeyStone;
    }

    public void setLuckeyStone(String luckeyStone) {
        this.luckeyStone = luckeyStone;
    }

    public String getFuture() {
        return future;
    }

    public void setFuture(String future) {
        this.future = future;
    }

    public String getResultcode() {
        return resultcode;
    }

    public void setResultcode(String resultcode) {
        this.resultcode = resultcode;
    }

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public List<String> getCareer() {
        return career;
    }

    public void setCareer(List<String> career) {
        this.career = career;
    }

    public List<String> getLove() {
        return love;
    }

    public void setLove(List<String> love) {
        this.love = love;
    }

    public List<String> getHealth() {
        return health;
    }

    public void setHealth(List<String> health) {
        this.health = health;
    }

    public List<String> getFinance() {
        return finance;
    }

    public void setFinance(List<String> finance) {
        this.finance = finance;
    }

    public static class MimaBean {
        /**
         * info : 保持从一而终的热爱
         * text : ["在2020年，金牛座会有一种突然要大干一场的感觉，在自己的理想方面有了新的规划，也确立了全新的目标，那么接下来金牛们要做的就是保持这种干劲并且从一而终，不要半途而废，不要好高骛远，既然选择开始，就要一直坚持下去。与此同时，2020年也非常适合金牛座外出学习或进修，在2020年金牛们会感受到一股非常强劲的学习欲望，不管是哪个领域的知识都想要涉猎一下。在此建议金牛们要选定好和自己设立目标相关的领域进行学习，才能对完成自己的理想有所帮助。"]
         */

        private String info;
        private List<String> text;

        public String getInfo() {
            return info;
        }

        public void setInfo(String info) {
            this.info = info;
        }

        public List<String> getText() {
            return text;
        }

        public void setText(List<String> text) {
            this.text = text;
        }
    }
}
