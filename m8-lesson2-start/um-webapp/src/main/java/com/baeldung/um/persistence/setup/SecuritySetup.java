package com.baeldung.um.persistence.setup;

import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.baeldung.common.persistence.event.BeforeSetupEvent;
import com.baeldung.common.spring.util.Profiles;
import com.baeldung.um.persistence.model.User;
import com.baeldung.um.persistence.model.Privilege;
import com.baeldung.um.persistence.model.Role;
import com.baeldung.um.service.IUserService;
import com.baeldung.um.service.IPrivilegeService;
import com.baeldung.um.service.IRoleService;
import com.baeldung.um.util.Um;
import com.baeldung.um.util.Um.Privileges;
import com.baeldung.um.util.Um.Roles;
import com.google.common.base.Preconditions;
import com.google.common.collect.Sets;

/**
 * This simple setup class will run during the bootstrap process of Spring and will create some setup data <br>
 * The main focus here is creating some standard privileges, then roles and finally some default users
 */
@Component
@Profile(Profiles.DEPLOYED)
public class SecuritySetup implements ApplicationListener<ContextRefreshedEvent> {
    private final Logger logger = LoggerFactory.getLogger(SecuritySetup.class);

    private boolean setupDone;

    @Autowired
    private IUserService userService;

    @Autowired
    private IRoleService roleService;

    @Autowired
    private IPrivilegeService privilegeService;

    @Autowired
    private ApplicationContext eventPublisher;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public SecuritySetup() {
        super();
    }

    //

    /**
     * - note that this is a compromise - the flag makes this bean statefull which can (and will) be avoided in the future by a more advanced mechanism <br>
     * - the reason for this is that the context is refreshed more than once throughout the lifecycle of the deployable <br>
     * - alternatives: proper persisted versioning
     */
    @Override
    public final void onApplicationEvent(final ContextRefreshedEvent event) {
        if (!setupDone) {
            logger.info("Executing Setup");
            eventPublisher.publishEvent(new BeforeSetupEvent(this));

            createPrivileges();
            createRoles();
            createUsers();

            setupDone = true;
            logger.info("Setup Done");
        }
    }

    // Privilege

    private void createPrivileges() {
        createPrivilegeIfNotExisting(Privileges.CAN_PRIVILEGE_READ);
        createPrivilegeIfNotExisting(Privileges.CAN_PRIVILEGE_WRITE);

        createPrivilegeIfNotExisting(Privileges.CAN_ROLE_READ);
        createPrivilegeIfNotExisting(Privileges.CAN_ROLE_WRITE);

        createPrivilegeIfNotExisting(Privileges.CAN_USER_READ);
        createPrivilegeIfNotExisting(Privileges.CAN_USER_WRITE);
    }

    final void createPrivilegeIfNotExisting(final String name) {
        final Privilege entityByName = privilegeService.findByName(name);
        if (entityByName == null) {
            final Privilege entity = new Privilege(name);
            privilegeService.create(entity);
        }
    }

    // Role

    private void createRoles() {
        final Privilege canPrivilegeRead = privilegeService.findByName(Privileges.CAN_PRIVILEGE_READ);
        final Privilege canPrivilegeWrite = privilegeService.findByName(Privileges.CAN_PRIVILEGE_WRITE);
        final Privilege canRoleRead = privilegeService.findByName(Privileges.CAN_ROLE_READ);
        final Privilege canRoleWrite = privilegeService.findByName(Privileges.CAN_ROLE_WRITE);
        final Privilege canUserRead = privilegeService.findByName(Privileges.CAN_USER_READ);
        final Privilege canUserWrite = privilegeService.findByName(Privileges.CAN_USER_WRITE);

        Preconditions.checkNotNull(canPrivilegeRead);
        Preconditions.checkNotNull(canPrivilegeWrite);
        Preconditions.checkNotNull(canRoleRead);
        Preconditions.checkNotNull(canRoleWrite);
        Preconditions.checkNotNull(canUserRead);
        Preconditions.checkNotNull(canUserWrite);

        createRoleIfNotExisting(Roles.ROLE_USER, Sets.<Privilege> newHashSet(canUserRead, canRoleRead, canPrivilegeRead));
        createRoleIfNotExisting(Roles.ROLE_ADMIN, Sets.<Privilege> newHashSet(canUserRead, canUserWrite, canRoleRead, canRoleWrite, canPrivilegeRead, canPrivilegeWrite));
    }

    final void createRoleIfNotExisting(final String name, final Set<Privilege> privileges) {
        final Role entityByName = roleService.findByName(name);
        if (entityByName == null) {
            final Role entity = new Role(name);
            entity.setPrivileges(privileges);
            roleService.create(entity);
        }
    }

    // User/User

    final void createUsers() {
        final Role roleAdmin = roleService.findByName(Roles.ROLE_ADMIN);
        final Role roleUser = roleService.findByName(Roles.ROLE_USER);

        createUserIfNotExisting(Um.ADMIN_EMAIL, Um.ADMIN_PASS, Sets.<Role> newHashSet(roleAdmin));
        createUserIfNotExisting(Um.USER_EMAIL, Um.USER_PASS, Sets.<Role> newHashSet(roleUser));
    }

    final void createUserIfNotExisting(final String loginName, final String pass, final Set<Role> roles) {
        final User entityByName = userService.findByName(loginName);
        if (entityByName == null) {
            final User entity = new User(loginName, passwordEncoder.encode(pass), roles);
            userService.create(entity);
        }
    }

}
