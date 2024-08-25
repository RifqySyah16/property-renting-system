package user.service;

import java.util.List;

import user.model.User;
import user.repository.UserRepository;

public class UserService {
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAll() {
        return this.userRepository.getAll();
    }

    public User findBy(int id) {
        return this.userRepository.findBy(id).orElseThrow(UserNotFoundException::new);
    }

}
