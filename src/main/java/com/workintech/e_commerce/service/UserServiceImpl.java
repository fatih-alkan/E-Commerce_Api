package com.workintech.e_commerce.service;

import com.workintech.e_commerce.dto.UserPatchRequestDto;
import com.workintech.e_commerce.dto.UserRequestDto;
import com.workintech.e_commerce.dto.UserResponseDto;
import com.workintech.e_commerce.entity.User;
import com.workintech.e_commerce.mapper.UserMapper;
import com.workintech.e_commerce.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
    private final UserRepository repository;
    private final UserMapper mapper;
    private final PasswordEncoder passwordEncoder;


    @Override
    public List<UserResponseDto> getAll() {
        return repository.findAll().stream().map(mapper::toResponse).toList();
    }

    @Override
    public UserResponseDto getById(Long id) {
        Optional<User> optionalUser = repository.findById(id);
        if (optionalUser.isPresent()){
            return mapper.toResponse(optionalUser.get());
        }
        throw new RuntimeException("User not found");
    }

    @Override
    public UserResponseDto create(UserRequestDto userRequestDto) {
        if (repository.findByEmail(userRequestDto.email()).isPresent()){
            throw new RuntimeException("Email already registered: " + userRequestDto.email());
        }
        User user = mapper.toEntity(userRequestDto);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return mapper.toResponse(repository.save(user));

    }

    @Override
    public UserResponseDto replaceOrCreate(Long id, UserRequestDto userRequestDto) {
        Optional<User> optionalUser = repository.findById(id);
        User userToReplaceOrCreate = mapper.toEntity(userRequestDto);
        userToReplaceOrCreate.setPassword(passwordEncoder.encode(userToReplaceOrCreate.getPassword()));
        if (optionalUser.isPresent()){
            userToReplaceOrCreate.setId(id);
            repository.save(userToReplaceOrCreate);
            return mapper.toResponse(userToReplaceOrCreate);
        }
        return mapper.toResponse(repository.save(userToReplaceOrCreate));
    }

    @Override
    public UserResponseDto update(Long id, UserPatchRequestDto userPatchRequestDto) {
        User userToUpdate = repository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        mapper.update(userToUpdate,userPatchRequestDto);
        if (userPatchRequestDto.password() != null && !userPatchRequestDto.password().isBlank()){
            userToUpdate.setPassword(passwordEncoder.encode(userPatchRequestDto.password()));
        }
        return mapper.toResponse(repository.save(userToUpdate));
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
