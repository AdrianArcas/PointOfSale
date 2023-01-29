package com.example.pointofsale.ejb;

import com.example.pointofsale.entities.Account;
import com.example.pointofsale.entities.UserGroup;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.logging.Logger;

@Stateless
public class AccountBean {
    @Inject
    PasswordBean passwordBean;
    @PersistenceContext
    EntityManager entityManager;
    private static final Logger LOG = Logger.getLogger(AccountBean.class.getName());

    public void createUser(String username, String email, String password, String userGroup) {
        LOG.info("createUser");
        Account newAccount = new Account();
        newAccount.setUsername(username);
        newAccount.setEmail(email);
        newAccount.setPassword(passwordBean.convertToSha256(password));
        entityManager.persist(newAccount);
        assignGroupToUser(username, userGroup);

    }

    private void assignGroupToUser(String username, String userGroup) {
        LOG.info("assignGroupToUser");
        UserGroup tempUserGroup = new UserGroup();
        tempUserGroup.setUsername(username);
        tempUserGroup.setUserGroup(userGroup);
        entityManager.persist(tempUserGroup);
    }


}
