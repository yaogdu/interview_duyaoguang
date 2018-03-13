/*
 * Copyright 2013-2015 duolabao.com All right reserved. This software is the
 * confidential and proprietary information of duolabao.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with duolabao.com.
 */
package com.aliyun.interview.kb.algo;

import java.util.Arrays;

/**
 * 类ChangeMoney的实现描述：
 *
 * 找零问题
 * 如果我们有面值为1元、3元和5元的硬币若干枚，如何用最少的硬币凑够11元？
 *
 *
 * @author yaoguang.du 2018/3/13 11:32
 */
public class ChangeMoney {


    public static void main(String[] args) {

        int [] coinValues = {1,3,5};

        int total = 11;

        int [] counts = change(total,coinValues);
        System.out.println("coin values are :"+Arrays.toString(coinValues));
        System.out.println("resp counts are :"+Arrays.toString(counts));
    }


    /**
     *
     * @param total 待找零的总金额
     * @param coinValues 一组可用硬币的面值
     * @return 对应传入面值的使用数量
     */
    static public int[] change(int total, int[] coinValues) {

        int [] result = new int[coinValues.length];

        int [] tmp = new int [total +1];

        int road[]=new int[total+1];
        int min=getCount(total ,coinValues ,road );
        if(min>Integer. MAX_VALUE-total ){ //min 没有另外赋值，则表示零钱不够
            System.out. println( "零钱不够！" );
        }else{
            System.out. println( "数值为" +total +" 时，需要的最少的硬币数为： "+ min);
            for(int j=total;j>0;){
                //System.out. print( road[j]+ "\t");

                tmp[road[j]] += 1;

                j=j-road[j];  //j为当前要找的零钱值， road[j]为当前面额下，最近加入的零钱
            }
        }

        for(int i = 0;i< coinValues.length;i++){
            result[i] = tmp[coinValues[i]];
        }


        return result;
    }

    /**
     *
     * @param total
     * @param coinValues
     * @param road
     * @return
     */
    public static int getCount (int total,int coinValues[],int road[]){
        int result[]=new int[total+1];//保存最近加入的零钱值
        result[0]=0;
        for(int x=1;x<total+1;x++){ //要求result[total],先求result[1]~result[total-1]
            if(x>=coinValues[0]){  //给result[x]附初值
                result[x]=result[x-coinValues[0]]+1;
                road[x]=coinValues[0];
            }else{   //要找零钱比最小零钱值还小，零钱不够
                result[x]=Integer.MAX_VALUE- total;
            }
            for(int i=1;i<coinValues.length;i++){
                if(x>=coinValues[i]&&(result[x]>result[x-coinValues[i]]+1)){//x-coinValues[i]表示当前值减去coins[]中值，即可由前面那些子问题+1一次得来
                    result[ x]= result[ x- coinValues[ i]]+1;
                    road[ x]= coinValues[ i];
                }
            }
        }
        return result[total];
    }

}
