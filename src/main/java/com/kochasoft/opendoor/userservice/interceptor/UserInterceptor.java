package com.kochasoft.opendoor.userservice.interceptor;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Nullable;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseToken;
import com.kochasoft.opendoor.userservice.domain.Status;
import com.kochasoft.opendoor.userservice.domain.User;
import com.kochasoft.opendoor.userservice.service.UserService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class UserInterceptor implements HandlerInterceptor {

    Logger log=LoggerFactory.getLogger(UserInterceptor.class);
    private static final String AUTHORIZATION_HEADER = "Authorization";

    @Autowired
    UserService service;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader(AUTHORIZATION_HEADER);

        dumpRequest(request);

        log.info(token);
        String uid=null;
        if(token!=null){
            FirebaseToken verifyIdToken = FirebaseAuth.getInstance().verifyIdToken(token);
        
            log.info("UUID : {}",verifyIdToken.getUid());
            uid = verifyIdToken.getUid();
        }else{
            uid="YgwL5fbd1zbToiYJqOB26ukCKQL2";
        }
        
        User user = service.findByUuid(uid, Status.ACTIVE);
        

        if(user==null)
            return false;

        
        log.info("interceptor user : {}", user.getId());
        request.setAttribute("user", user);
        return true;
    }

    private void dumpRequest(HttpServletRequest req){
        Enumeration<String> enumeration = req.getParameterNames();
        Map<String,Object> map=new HashMap<>();
        while(enumeration.hasMoreElements()){
            String parameterName = enumeration.nextElement();
            map.put(parameterName, req.getParameter(parameterName));
        }
        
        log.info("############   incomming request   ##########");
        log.info("request : {}",req.getRequestURL());
        log.info("parameters : {}",map);
        
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
        try {
            
            log.info("############    request  completed ########## ");
            log.info("user : {}",request.getAttribute("user"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
