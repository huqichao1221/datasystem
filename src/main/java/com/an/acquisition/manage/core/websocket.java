package com.an.acquisition.manage.core;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.websocket.OnClose;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/websocket")
public class websocket {
	private  Session session=null;
	
	 /**  
     * 用户和Session绑定关系  
     */  
    public static final Map<String, Session> USER_SESSION = new HashMap<String, Session>();  
//	 @OnOpen
//	    public void onOpen(Session session){ 
//	        OneThread thread =null;
//	        thread=new OneThread(session);
//	        thread.start();
//	    }
	
	 /**
	     * @OnOpen allows us to intercept the creation of a new session.
	     * The session class allows us to send data to the user.
	     * In the method onOpen, we'll let the user know that the handshake was
	     * successful.
	     * 建立websocket连接时调用
	     */
	    @OnOpen
	    public void onOpen(Session session){
	        System.out.println("Session " + session.getId() + " has opened a connection");
	        try {
	        	  boolean flag=USER_SESSION.containsKey(session.getId());
	        	  if (flag) {
					System.out.println("存在");
	        	  }
	        	  else
	        	  {
	        		  this.session=session;
	  	            USER_SESSION.put(session.getId(), session);    //加入set中 
	        	  }
	            
	            session.getBasicRemote().sendText("Connection Established");
	        } catch (IOException ex) {
	            ex.printStackTrace();
	        }
	    }
	    /**
	     * The user closes the connection.
	     *
	     * Note: you can't send messages to the client from this method
	     * 关闭连接时调用
	     */
	    @OnClose
	    public void onClose(Session session){
	    	try {
	    		USER_SESSION.remove(session.getId());  //从set中删除
		        System.out.println("Session " +session.getId()+" has closed!");
			} catch (Exception e) {
				e.printStackTrace();
			}
	        
	    }
	    
	    /**
	     * 这个方法与上面几个方法不一样。没有用注解，是根据自己需要添加的方法。
	     * @throws IOException
	     * 发送自定义信号，“1”表示告诉前台，数据库发生改变了，需要刷新
	     */
	    public static void  sendMessage() throws IOException{
	        //群发消息
	        
	            try {
	            	Map<String,Session> map = new HashMap<String, Session>();
	            	Iterator entries = USER_SESSION.entrySet().iterator(); 
	            	while (entries.hasNext()) { 
	            	  Map.Entry entry = (Map.Entry) entries.next(); 
	            	  String key = (String)entry.getKey(); 
	            	  Session sessionm = (Session)entry.getValue(); 
	            	  System.out.println("Key = " + key + ", Value = " + sessionm); 
	            	  sessionm.getBasicRemote().sendText("1");
	            	}
	            	
	                	
	            } catch (IOException e) {
	                e.printStackTrace();
	                
	            }
	        }

	    
	 
	
}
