package com.exercise.usuario.mapper;


import com.exercise.usuario.dto.UserRequest;
import com.exercise.usuario.dto.UserResponse;
import com.exercise.usuario.model.Usuario;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
@Component
public class UserMapper {
    private final ModelMapper modelMapper;
    @Autowired
    public UserMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;

        modelMapper.addMappings(new PropertyMap<UserRequest, Usuario>() {
            @Override
            protected void configure() {
                skip(destination.getId());
            }
        });
    }

    public Usuario convertToUser(UserRequest userRequest) {
        return modelMapper.map(userRequest, Usuario.class);
    }

    public void updateUserFromRequest(UserRequest userRequest, Usuario usuario) {
        modelMapper.map(userRequest, usuario);
    }

    public UserResponse convertToUserResponse(Usuario user) {
        return modelMapper.map(user, UserResponse.class);
    }

}
