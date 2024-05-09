package com.experiment.services;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import com.experiment.db.DBController;
import com.experiment.models.User;
import com.experiment.services.UserService;

public class UserServiceTest {

    @Mock
    private DBController db;

    private UserService userService;

    @Before
    public void setUp() {
        db = mock(DBController.class);
        userService = new UserService();
        userService.setDb(db);
    }

    // Testa o método getAllUsers
    @Test
    public void testGetAllUsers() {
        List<User> userList = new ArrayList<>();
        userList.add(new User(1, "user1", "password1"));
        userList.add(new User(2, "user2", "password2"));

        when(db.allUsers()).thenReturn(userList);

        assertEquals(userList, userService.getAllUsers());
    }

    // Testa o método getUserById
    @Test
    public void testGetUserById() throws Exception {
        User user = new User(1, "user1", "password1");
        when(db.getUser(1)).thenReturn(user);

        assertEquals(user, userService.getUserById(1));
    }

    // Testa o método createUser
    @Test
    public void testCreateUser() throws Exception {
        User newUser = new User(3, "newUser", "password");
        when(db.createUser(newUser)).thenReturn(newUser);

        assertEquals(newUser, userService.createUser(newUser));
    }

    // Testa o método updateUser
    @Test
    public void testUpdateUser() throws Exception {
        User updatedUser = new User(1, "updatedUser", "newPassword");
        when(db.updateUser(updatedUser)).thenReturn(true);

        assertEquals(updatedUser, userService.updateUser(updatedUser));
    }

    // Testa o método deleteUser
    @Test
    public void testDeleteUser() throws Exception {
        when(db.deleteUser(1)).thenReturn(true);

        userService.deleteUser(1);
        verify(db, times(1)).deleteUser(1);
    }

    // Testa se uma exceção é lançada quando um usuário não é encontrado
    @Test(expected = Exception.class)
    public void testGetUserByIdNotFound() throws Exception {
        when(db.getUser(3)).thenReturn(null);

        userService.getUserById(3);
    }

    // Testa se uma exceção é lançada ao tentar criar um usuário duplicado
    @Test(expected = Exception.class)
    public void testCreateDuplicateUser() throws Exception {
        User existingUser = new User(1, "user1", "password1");
        when(db.getUser(1)).thenReturn(existingUser);

        User newUser = new User(1, "user1", "password1");
        userService.createUser(newUser);
    }

    // Testa se uma exceção é lançada ao tentar atualizar um usuário que não existe
    @Test(expected = Exception.class)
    public void testUpdateUserNotFound() throws Exception {
        User updatedUser = new User(3, "updatedUser", "newPassword");
        when(db.updateUser(updatedUser)).thenReturn(false);

        userService.updateUser(updatedUser);
    }

    // Testa se uma exceção é lançada ao tentar deletar um usuário que não existe
    @Test(expected = Exception.class)
    public void testDeleteUserNotFound() throws Exception {
        when(db.deleteUser(3)).thenReturn(false);

        userService.deleteUser(3);
    }
}
