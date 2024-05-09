// package com.experiment.services;

import java.util.List;

import com.experiment.db.DBController;
import com.experiment.models.User;

public class UserService {
    private DBController db = new DBController();

    // Retorna todos os usuários
    public List<User> getAllUsers() {
        return this.db.allUsers();
    }

    // Retorna um usuário pelo ID
    public User getUserById(int userId) throws Exception {
        return db.getUser(userId);
    }

    // Cria um novo usuário
    public User createUser(User newUser) throws Exception {
        validateUserData(newUser);
        if (isDuplicateUser(newUser)) {
            throw new Exception("Já existe um usuário com o mesmo nome de usuário ou endereço de email.");
        }
        return db.createUser(newUser);
    }

    // Atualiza um usuário existente
    public User updateUser(User updatedUser)
            throws Exception {
        validateUserData(updatedUser);

        if (!db.updateUser(updatedUser))
            throw new Exception("Usuário não atualizado");

        return updatedUser;
    }

    // Deleta um usuário pelo ID
    public void deleteUser(int userId) throws Exception {
        if (!db.deleteUser(userId)) {
            throw new Exception("Usuário não encontrado com o ID: " + userId);
        }
    }

    // Método privado para validar dados do usuário
    private void validateUserData(User user) throws Exception {
        if (user.getUsername() == null || user.getUsername().isEmpty()) {
            throw new Exception("O nome do usuário não pode ser nulo ou vazio.");
        }
        // Outras validações podem ser adicionadas aqui conforme necessário
    }

    // Método privado para verificar se um usuário já existe
    private boolean isDuplicateUser(User user) {
        if (db.getUser(user.getId()) != null)
            return true;
        return false;
    }
}
