package com.example.pointofsale.ejb;

import com.example.pointofsale.common.DirectorNotificationDto;
import com.example.pointofsale.entities.Account;
import com.example.pointofsale.entities.NotificationAccountsDirector;
import jakarta.ejb.EJBException;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.SystemException;
import jakarta.transaction.Transactional;
import jakarta.transaction.UserTransaction;

import javax.naming.InitialContext;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
@Stateless
@Transactional
public class DirectorNotificationBean {

    @Inject
    AccountBean accountBean;

    @PersistenceContext
    EntityManager entityManager;
    private static final Logger LOG = Logger.getLogger(DirectorNotificationBean.class.getName());

    public void createNotification(Long whoAddedOrDeletedAccID , Long AddedOrDeletedAccID, String action) {
        LOG.info("createNotification");
        NotificationAccountsDirector newNotificatioon=new NotificationAccountsDirector();

        newNotificatioon.setWho_modified_or_added_id(whoAddedOrDeletedAccID);
        newNotificatioon.setModified_or_added_account_id(AddedOrDeletedAccID);
        newNotificatioon.setAction(action);

        entityManager.persist(newNotificatioon);
    }
    public List<DirectorNotificationDto> findAllNotifications(){
        LOG.info("findAllNotofications");
        try{
            TypedQuery<NotificationAccountsDirector> typedQuery = entityManager.createQuery("SELECT n FROM NotificationAccountsDirector n", NotificationAccountsDirector.class);
            List<NotificationAccountsDirector> notifications = typedQuery.getResultList();
            return copyNotificationsToDto(notifications);

        }catch(Exception ex){
            throw new EJBException(ex);
        }
    }
    private List<DirectorNotificationDto> copyNotificationsToDto(List<NotificationAccountsDirector> notifications) {
        List<DirectorNotificationDto> directorNotificationDtoList = new ArrayList<>();

        for(NotificationAccountsDirector n : notifications){
            directorNotificationDtoList.add(new DirectorNotificationDto(n.getNotification_id(), accountBean.getAccountUsernameByID(n.getModified_or_added_account_id()), accountBean.getAccountUsernameByID(n.getWho_modified_or_added_id()),n.getAction()));

        }
        return directorNotificationDtoList;
    }

    public void deleteNotificationById(Long notification_id) {
        LOG.info("deleteNotificationById");
        NotificationAccountsDirector notification = entityManager.find(NotificationAccountsDirector.class, notification_id);
        entityManager.remove(notification);
    }
}
