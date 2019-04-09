package com.example.st2;

import java.util.ArrayList;

public  class cart {
        public static ArrayList<y> getInstance() {
            if (a == null)
                a = new ArrayList<y>();
            return a;
        }
        private cart() {
        }
        private static ArrayList<y> a;
        public static void set(int id,int quantity,String name,int price)
        {
            int flag=0;
            for(y i1:a)
            {
                if(i1.id==id)
                {
                    i1.quantity=quantity;
                    flag=1;
                    break;
                }
            }
            if(flag==0)
            {
                a.add(new y(id,name,price,quantity));
            }
        }
}
