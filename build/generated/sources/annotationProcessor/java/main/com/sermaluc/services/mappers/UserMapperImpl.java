package com.sermaluc.services.mappers;

import com.sermaluc.models.dtos.PhoneDto;
import com.sermaluc.models.dtos.UserRequestDTO;
import com.sermaluc.models.dtos.UserResponseDTO;
import com.sermaluc.models.entities.Phone;
import com.sermaluc.models.entities.TokenInfo;
import com.sermaluc.models.entities.User;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-03-25T15:59:50-0500",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.5.jar, environment: Java 17.0.9 (JetBrains s.r.o.)"
)
public class UserMapperImpl implements UserMapper {

    @Override
    public User map(UserRequestDTO userRequestDto) {
        if ( userRequestDto == null ) {
            return null;
        }

        User.UserBuilder user = User.builder();

        user.name( userRequestDto.getName() );
        user.email( userRequestDto.getEmail() );
        user.password( userRequestDto.getPassword() );
        user.phones( phoneDtoListToPhoneList( userRequestDto.getPhones() ) );

        user.created( java.time.LocalDateTime.now() );
        user.lastLogin( java.time.LocalDateTime.now() );
        user.isActive( true );

        return user.build();
    }

    @Override
    public Phone map(PhoneDto phoneDto) {
        if ( phoneDto == null ) {
            return null;
        }

        Phone.PhoneBuilder phone = Phone.builder();

        phone.number( phoneDto.getNumber() );
        phone.citycode( phoneDto.getCitycode() );
        phone.contrycode( phoneDto.getContrycode() );

        return phone.build();
    }

    @Override
    public UserResponseDTO map(User userEntity) {
        if ( userEntity == null ) {
            return null;
        }

        UserResponseDTO.UserResponseDTOBuilder userResponseDTO = UserResponseDTO.builder();

        userResponseDTO.token( userEntityTokenInfoToken( userEntity ) );
        userResponseDTO.isActive( userEntity.isActive() );
        if ( userEntity.getId() != null ) {
            userResponseDTO.id( userEntity.getId().toString() );
        }
        userResponseDTO.name( userEntity.getName() );
        userResponseDTO.email( userEntity.getEmail() );
        userResponseDTO.phones( phoneListToPhoneDtoList( userEntity.getPhones() ) );

        userResponseDTO.lastLogin( userEntity.getLastLogin().format(java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")) );
        userResponseDTO.created( userEntity.getCreated().format(java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")) );
        userResponseDTO.modified( userEntity.getModified().format(java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")) );

        return userResponseDTO.build();
    }

    protected List<Phone> phoneDtoListToPhoneList(List<PhoneDto> list) {
        if ( list == null ) {
            return null;
        }

        List<Phone> list1 = new ArrayList<Phone>( list.size() );
        for ( PhoneDto phoneDto : list ) {
            list1.add( map( phoneDto ) );
        }

        return list1;
    }

    private String userEntityTokenInfoToken(User user) {
        if ( user == null ) {
            return null;
        }
        TokenInfo tokenInfo = user.getTokenInfo();
        if ( tokenInfo == null ) {
            return null;
        }
        String token = tokenInfo.getToken();
        if ( token == null ) {
            return null;
        }
        return token;
    }

    protected PhoneDto phoneToPhoneDto(Phone phone) {
        if ( phone == null ) {
            return null;
        }

        PhoneDto.PhoneDtoBuilder phoneDto = PhoneDto.builder();

        phoneDto.number( phone.getNumber() );
        phoneDto.citycode( phone.getCitycode() );
        phoneDto.contrycode( phone.getContrycode() );

        return phoneDto.build();
    }

    protected List<PhoneDto> phoneListToPhoneDtoList(List<Phone> list) {
        if ( list == null ) {
            return null;
        }

        List<PhoneDto> list1 = new ArrayList<PhoneDto>( list.size() );
        for ( Phone phone : list ) {
            list1.add( phoneToPhoneDto( phone ) );
        }

        return list1;
    }
}
