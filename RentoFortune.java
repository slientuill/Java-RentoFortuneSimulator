package io.renren.modules.jxgk.utils;

import java.util.*;

public class RentoFortune {

    //地图共四十个位置
    private Palace []palace=new Palace[40];
    //初始位置为0
    private int position=0;
    public void AAA(){
        for(int i=0;i<40;i++){
            palace[i]=new Palace();
        }
        palace[0].initSet("起点",0,0);
        palace[1].initSet("宝箱",0,1);
        palace[2].initSet("利比亚",250,2);
        palace[3].initSet("苏丹",450,3);
        palace[4].initSet("转盘",0,4);
        palace[5].initSet("日本站",200,5);
        palace[6].initSet("幸运草",0,6);
        palace[7].initSet("土耳其",550,7);
        palace[8].initSet("希腊",550,8);
        palace[9].initSet("保加利亚",600,9);
        palace[10].initSet("监狱",0,10);
        palace[11].initSet("波兰",750,11);
        palace[12].initSet("俄罗斯",750,12);
        palace[13].initSet("乌克兰",900,13);
        palace[14].initSet("教廷",777,14);
        palace[15].initSet("西班牙站",200,15);
        palace[16].initSet("宝箱",0,16);
        palace[17].initSet("立陶宛",950,17);
        palace[18].initSet("拉脱维亚",950,18);
        palace[19].initSet("爱沙尼亚",1000,19);
        palace[20].initSet("停车场",0,20);
        palace[21].initSet("挪威",1050,21);
        palace[22].initSet("瑞典",1050,22);
        palace[23].initSet("芬兰",1100,23);
        palace[24].initSet("幸运草",0,24);
        palace[25].initSet("美国站",200,25);
        palace[26].initSet("转盘",0,26);
        palace[27].initSet("德国",1150,27);
        palace[28].initSet("法国",1150,28);
        palace[29].initSet("英国",1200,29);
        palace[30].initSet("法院",0,30);
        palace[31].initSet("加拿大",1275,31);
        palace[32].initSet("美国",1275,32);
        palace[33].initSet("墨西哥",1400,33);
        palace[34].initSet("宝箱",0,34);
        palace[35].initSet("英国站",200,35);
        palace[36].initSet("幸运草",0,36);
        palace[37].initSet("迪拜",1500,37);
        palace[38].initSet("夏威夷",2000,38);
        palace[39].initSet("停车费",0,39);
    }
    public Map<String,Object> dice(){
        Map<String,Object> result=new HashMap<>();
        Random r1 = new Random();
        Random r2 = new Random();
        int dice1= r1.nextInt(5)+1;
        int dice2= r2.nextInt(5)+1;
        if(dice1==dice2)
            result.put("equal","true");
        else
            result.put("equal","false");
        result.put("dice1",dice1);
        result.put("dice2",dice2);
        return  result;
    }
    public void Run(Map<String,Object> diceResult){
        position+=(int)diceResult.get("dice1")+(int)diceResult.get("dice2");
        if(position>=40){
            position-=40;
            System.out.println("一圈:"+position);
        }


        //转盘
        if(position==4||position==6){
            Random rollPalate = new Random();
            int palateResult=rollPalate.nextInt(15)+1;
            if(palateResult==1){
                position=20;//停车场
                System.out.println("转盘导致停车场:"+position);
            }


            if(palateResult==2) {
                position = 10;//监狱
                System.out.println("转盘导致监狱:"+position);
            }
        }
        //幸运草
        if(position==6||position==24||position==36){
            Random rollLucky = new Random();
            int luckyResult=rollLucky.nextInt(15)+1;
            if(luckyResult==1)
                position+=1;//前进一格
            if(luckyResult==2)
                position-=3;//后退三格
            if(luckyResult==3)
                position=38;//去夏威夷
            if(luckyResult==4)
                position=23;//去芬兰
            if(luckyResult==5)
                position=11;//波兰
            if(luckyResult==6)
                position=0;//去起点
            if(luckyResult==7)
                position=10;//去监狱
        }
        if(position==30)//法院
            position=10;
        for(int i=0;i<40;i++){
            if(position==i){
                int currentOnCount=palace[i].getOnCount();
                currentOnCount++;
                palace[i].setOnCount(currentOnCount);
                int currentOnFeeCount=palace[i].getOnFeeCount();
                currentOnFeeCount+=palace[i].getFee();
                palace[i].setOnFeeCount(currentOnFeeCount);
            }
        }
    }
    public void Play(int times){
        //初始化地图
        AAA();
        for(int i=0;i<times;i++){
            //丢骰子
            Map<String,Object> diceResult=dice();
            //先走
            Run(diceResult);
            //再判定重复
            if((String)diceResult.get("equal")=="true"){
                System.out.println("第一次重复骰子"+"第一个："+diceResult.get("dice1")+"第二个："+diceResult.get("dice2"));
                //丢骰子
                Map<String,Object> diceResult2=dice();
                //先走
                Run(diceResult2);
                //再判定重复
                if((String)diceResult2.get("equal")=="true"){
                    System.out.println("第二次重复骰子"+"第一个："+diceResult.get("dice1")+"第二个："+diceResult.get("dice2"));
                    //丢骰子
                    Map<String,Object> diceResult3=dice();
                    //先判定重复
                    if((String)diceResult3.get("equal")=="true"){
                        System.out.println("第三次重复骰子"+"第一个："+diceResult.get("dice1")+"第二个："+diceResult.get("dice2"));
                        //第三次，不让走了
                        position=10;
                        System.out.println("进监狱");
                    } else {
                        Run(diceResult3);
                    }
                }
            }
            i++;
        }

    }

    public static void main(String []args){
        RentoFortune R=new RentoFortune();
        R.Play(100000 );
        Palace []palaceBig=new Palace[9];
        for(int i=0;i<9;i++){
            palaceBig[i]=new Palace();
        }
        palaceBig[0].setName("苏丹二连");
        palaceBig[0].setOnFeeCount(R.palace[2].getOnFeeCount()+R.palace[3].getOnFeeCount());
        palaceBig[1].setName("土耳其三连");
        palaceBig[1].setOnFeeCount(R.palace[7].getOnFeeCount()+R.palace[8].getOnFeeCount()+R.palace[9].getOnFeeCount());
        palaceBig[2].setName("俄罗斯三连");
        palaceBig[2].setOnFeeCount(R.palace[11].getOnFeeCount()+R.palace[12].getOnFeeCount()+R.palace[13].getOnFeeCount());
        palaceBig[3].setName("爱沙尼亚三连");
        palaceBig[3].setOnFeeCount(R.palace[17].getOnFeeCount()+R.palace[18].getOnFeeCount()+R.palace[19].getOnFeeCount());
        palaceBig[4].setName("挪威三连");
        palaceBig[4].setOnFeeCount(R.palace[21].getOnFeeCount()+R.palace[22].getOnFeeCount()+R.palace[23].getOnFeeCount());
        palaceBig[5].setName("德法英三连");
        palaceBig[5].setOnFeeCount(R.palace[27].getOnFeeCount()+R.palace[28].getOnFeeCount()+R.palace[29].getOnFeeCount());
        palaceBig[6].setName("加美墨三连");
        palaceBig[6].setOnFeeCount(R.palace[31].getOnFeeCount()+R.palace[32].getOnFeeCount()+R.palace[33].getOnFeeCount());
        palaceBig[7].setName("夏威夷二连");
        palaceBig[7].setOnFeeCount(R.palace[37].getOnFeeCount()+R.palace[38].getOnFeeCount());
        palaceBig[8].setName("教廷");
        palaceBig[8].setOnFeeCount(R.palace[14].getOnFeeCount());
        Arrays.sort(palaceBig);
        for(int i=0;i<9;i++){
            System.out.print("地区名称:"+palaceBig[i].getName());

            System.out.println("花费金钱:"+palaceBig[i].getOnFeeCount());
        }
//        for(int i=0;i<40;i++){
//            System.out.print("地区名称:"+R.palace[i].getName());
//            System.out.print("踩中次数:"+R.palace[i].getOnCount());
//            System.out.println("花费金钱:"+R.palace[i].getOnFeeCount());
//        }
        Arrays.sort(R.palace);
        for(int i=0;i<40;i++){
            System.out.print("地区名称:"+R.palace[i].getName());
            System.out.print("踩中次数:"+R.palace[i].getOnCount());
            System.out.println("花费金钱:"+R.palace[i].getOnFeeCount());
        }


    }
}
