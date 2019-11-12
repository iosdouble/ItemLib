package com.nh.email.emailserver.server;

/**
 * @Classname MailService
 * @Description TODO
 * @Date 2019/10/17 4:39 PM
 * @Created by nihui
 */
public interface MailService {

    public void sendSimpleMail(String to ,String subject,String content);
}
