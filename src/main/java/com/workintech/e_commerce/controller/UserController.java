package com.workintech.e_commerce.controller;

import com.workintech.e_commerce.dto.UserPatchRequestDto;
import com.workintech.e_commerce.dto.UserRequestDto;
import com.workintech.e_commerce.dto.UserResponseDto;
import com.workintech.e_commerce.service.UserService;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    @Autowired
    private final UserService service;

    @GetMapping
    public List<UserResponseDto> getAll(){
        return service.getAll();
    }

    @GetMapping("/{id}")
    public UserResponseDto getById(@Positive @Min(1) @PathVariable("id") Long id){
        return service.getById(id);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponseDto create(@Validated @RequestBody UserRequestDto userRequestDto){
        return service.create(userRequestDto);
    }

    @PutMapping("/{id}")
    public UserResponseDto replaceOrCreate(@Positive @Min(1) @PathVariable("id") Long id,
                                           @Validated @RequestBody UserRequestDto userRequestDto){
        return service.replaceOrCreate(id,userRequestDto);
    }

    @PatchMapping("/{id}")
    public UserResponseDto update(@Positive @Min(1) @PathVariable("id") Long id,
                                  @Validated @RequestBody UserPatchRequestDto userPatchRequestDto){
        return service.update(id,userPatchRequestDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@Positive @Min(1) @PathVariable("id") Long id){
        service.delete(id);
    }
}
