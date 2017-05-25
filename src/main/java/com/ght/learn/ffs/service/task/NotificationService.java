package com.ght.learn.ffs.service.task;

import java.util.List;

import com.ght.learn.ffs.entity.task.Notification;
import com.ght.learn.ffs.service.FfsService;
import com.ght.learn.ffs.tool.context.SessionUser;

public interface NotificationService extends FfsService {
    long queryMyCountUnreadNotifications(SessionUser operator);
    List<Notification> queryMyNotifications(SessionUser operator);
    void doReadMyNotifications(SessionUser operator);
    
    void doSubmitNotificationToUser(Notification inputNotification);
    void doSubmitNotificationToRole(Notification inputNotification, Long receiverRoleId, String companyCode);
}