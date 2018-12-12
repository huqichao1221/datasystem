package com.an.acquisition.manage.core;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Spring;
import javax.websocket.Session;

import com.alibaba.fastjson.JSONObject;
import com.an.acquisition.manage.service.DigitalService;
import com.an.acquisition.model.Digital;
import com.an.acquisition.util.SpringUtil;

public class OneThread extends Thread {

	private Session session;
    private List<Digital> currentMessage;
    
    private int currentIndex;
    
    public OneThread(Session session) {
    	
        this.session = session;
        currentMessage = new ArrayList<Digital>();
       
        currentIndex = 0;//此时是0条消息
    }

    
    @Override
    public void run() {
        while (true) {
            List<Digital> list = null;
            try {
                try {
                	JSONObject json= new JSONObject();
                    json = SpringUtil.getBean(DigitalService.class).selectRecordAllInOne();
                    list= (List<Digital>) json.get("list");
                    
                    currentIndex=(int) json.get("total");
                    System.out.println("*********************************------"+currentIndex);
                }  catch (Exception e) {
                    e.printStackTrace();
                }
            } catch (Exception e ) {
               e.printStackTrace();
            }
            if (list != null && currentIndex < list.size()) {
                for (int i = currentIndex; i < list.size(); i++) {
                    try {
                        session.getBasicRemote().sendText(list.get(i).getId()
                                + "," + list.get(i).getInntakepressure()
                                + "," + list.get(i).getIntaketemperature());
//                            session.getBasicRemote().sendObject(list.get(i)); //No encoder specified for object of class [class AlarmMessage]
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                currentIndex = list.size();
            }
            try {
                //一秒刷新一次
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
