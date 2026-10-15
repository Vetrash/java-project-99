package hexlet.code.service;

import hexlet.code.dto.UserCreateDTO;
import hexlet.code.dto.UserDTO;
import hexlet.code.dto.UserUpdateDTO;
import hexlet.code.entity.User;
import hexlet.code.exception.ResourceNotFoundException;
import hexlet.code.mapper.UserMapper;
import hexlet.code.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional(readOnly = true)
    public List<UserDTO> getAll() {
        return userRepository.findAll().stream()
                .map(userMapper::map)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public UserDTO findById(Long id) {
        return userMapper.map(find(id));
    }

    @Override
    @Transactional
    public UserDTO create(UserCreateDTO dto) {
        var user = userMapper.map(dto);
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        userRepository.save(user);
        return userMapper.map(user);
    }

    @Override
    @Transactional
    public UserDTO update(Long id, UserUpdateDTO dto) {
        var user = find(id);
        var newPassword = dto.getPassword();

        userMapper.update(dto, user);

        // маппер кладёт пароль как есть, в базе он должен храниться зашифрованным
        if (newPassword != null && newPassword.isPresent()) {
            user.setPassword(passwordEncoder.encode(newPassword.get()));
        }

        userRepository.save(user);
        return userMapper.map(user);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        userRepository.delete(find(id));
    }

    private User find(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User with id " + id + " not found"));
    }
}
