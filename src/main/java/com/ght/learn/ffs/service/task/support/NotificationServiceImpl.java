package com.ght.learn.ffs.service.task.support;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ght.learn.ffs.entity.task.Notification;
import com.ght.learn.ffs.enums.IF;
import com.ght.learn.ffs.service.FfsServiceImpl;
import com.ght.learn.ffs.service.base.RoleQueryService;
import com.ght.learn.ffs.service.task.NotificationService;
import com.ght.learn.ffs.tool.context.SessionUser;

import framework.core.utils.CollectionUtils;
import framework.core.utils.StringUtils;

@Service("NotificationService")
public class NotificationServiceImpl extends FfsServiceImpl implements NotificationService {

    @Resource(name = "RoleQueryService", type = RoleQueryService.class)
    private RoleQueryService roleQueryService;
    //注释掉redis缓存
    /*@Resource(name = "SessionRedisTemplate", type = RedisOperations.class)
    private RedisOperations<Object, Object> sessionRedisOperations;*/
    
    @Override
    public long queryMyCountUnreadNotifications(SessionUser operator) {
//	HashMap<String, Object> parameter = new HashMap<String, Object>();
//	parameter.put("receiverUserId", operator.getUserId());
//	parameter.put("N", IF.N.name());
//	parameter.put("days", Integer.valueOf(-7));
//	return getEntityManager().queryForUniqueObjectBySqlMap(Long.class, "queryMyCountUnreadNotifications", parameter);
//	Long count = Long.class.cast(this.sessionRedisOperations.opsForValue().get(getUserNotificationCountKey(operator.getUserId())));
//	return count == null ? 0L : count.longValue();
    	return 1;//this.sessionRedisOperations.opsForValue().increment(getUserNotificationCountKey(operator.getUserId()), 0L).longValue();
    }

    @Override
    public List<Notification> queryMyNotifications(SessionUser operator) {
		HashMap<String, Object> parameter = new HashMap<String, Object>();
		parameter.put("receiverUserId", operator.getUserId());
		parameter.put("days", Integer.valueOf(-30));
		return getEntityManager().queryForListBySqlMap(Notification.class, "queryNotifications", parameter);
    }

    @Override
    public void doReadMyNotifications(SessionUser operator) {
		//this.sessionRedisOperations.delete(getUserNotificationCountKey(operator.getUserId()));
		getEntityManager().executeSql("update T_NOTIFICATION n set n.HAS_READ=? where n.RECEIVER_USER_ID=? and n.HAS_READ=?", new Object[] {IF.Y.name(), operator.getUserId(), IF.N.name()});
    }

    /*private String getUserNotificationCountKey(Long receiverUserId) {
    	return Notification.class.getName() + "#Count#receiverUserId=" + receiverUserId;
    }*/
    
    @Override
    public void doSubmitNotificationToUser(Notification inputNotification) {
		if (inputNotification == null || inputNotification.getReceiverUserId() == null) {
		    return;
		}
		Notification notification = new Notification();
		notification.setAction(inputNotification.getAction());
		notification.setReceiverUserId(inputNotification.getReceiverUserId());
		notification.setNotificationType(inputNotification.getNotificationType());
		notification.setCorrelatedId(inputNotification.getCorrelatedId());
		notification.setCorrelatedCode(inputNotification.getCorrelatedCode());
		notification.setNameCn(inputNotification.getNameCn());
		notification.setNameEn(inputNotification.getNameEn());
		notification.setHasRead(IF.N);
		notification.setCreator(inputNotification.getCreator());
		notification.setCreateDateTime(getCurrentTime());
		getEntityManager().save(notification);
		//this.sessionRedisOperations.opsForValue().increment(getUserNotificationCountKey(inputNotification.getReceiverUserId()), 1L);
    }

    @Override
    public void doSubmitNotificationToRole(Notification inputNotification, Long receiverRoleId, String companyCode) {
		if (inputNotification == null || receiverRoleId == null || StringUtils.isEmpty(companyCode)) {
		    return;
		}
		List<Long> receiverUserIds = this.roleQueryService.queryUserIdsByRoleId(companyCode, receiverRoleId);
		if (CollectionUtils.isEmpty(receiverUserIds)) {
		    return;
		}
		ArrayList<Notification> notifications = new ArrayList<>();
		Date currentDateTime = getCurrentTime();
		for (Long receiverUserId : receiverUserIds) {
		    Notification notification = new Notification();
		    notification.setAction(inputNotification.getAction());
		    notification.setReceiverUserId(receiverUserId);
		    notification.setNotificationType(inputNotification.getNotificationType());
		    notification.setCorrelatedId(inputNotification.getCorrelatedId());
		    notification.setCorrelatedCode(inputNotification.getCorrelatedCode());
		    notification.setNameCn(inputNotification.getNameCn());
		    notification.setNameEn(inputNotification.getNameEn());
		    notification.setHasRead(IF.N);
		    notification.setCreator(inputNotification.getCreator());
		    notification.setCreateDateTime(currentDateTime);
		    notifications.add(notification);
		}
		getEntityManager().save(notifications);
		/*for (Long receiverUserId : receiverUserIds) {
		    //this.sessionRedisOperations.opsForValue().increment(getUserNotificationCountKey(receiverUserId), 1);
		}*/
    }
}
