package com.kh.zoomin.common.listener;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import com.kh.zoomin.supervisor.model.exception.SupervisorException;
import com.kh.zoomin.supervisor.model.service.SupervisorService;

/**
 * Application Lifecycle Listener implementation class VisitSessionListner
 *
 */
@WebListener
public class VisitSessionListner implements HttpSessionListener {

	private SupervisorService supervisorService = new SupervisorService();
	
    /**
     * Default constructor. 
     */
    public VisitSessionListner() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see HttpSessionListener#sessionCreated(HttpSessionEvent)
     */
    public void sessionCreated(HttpSessionEvent se)  { 
         if(se.getSession().isNew()) {
        	 count(se);
         }
    }

	private void count(HttpSessionEvent se) {	
		System.out.println("세션 생성 성공!");
		int todayCount = 0;
		try {
			todayCount = supervisorService.getTodayCount();	
			System.out.println("todayCount = " + todayCount);
			HttpSession session = se.getSession();
			session.setAttribute("todayCount", todayCount);
			System.out.println(session.getAttribute("todayCount"));
			
		} catch (Exception e) {
			new SupervisorException("방문자 수 카운터 오류", e);
		}
		
	}

	/**
     * @see HttpSessionListener#sessionDestroyed(HttpSessionEvent)
     */
    public void sessionDestroyed(HttpSessionEvent se)  { 
         // TODO Auto-generated method stub
    }
	
}
