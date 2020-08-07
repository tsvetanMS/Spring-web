package softuni.emuseum.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;
import softuni.emuseum.entities.Role;
import softuni.emuseum.models.service.RoleServiceModel;
import softuni.emuseum.repositories.RoleRepository;
import softuni.emuseum.services.api.RoleService;
import softuni.emuseum.services.impl.RoleServiceImpl;

import java.util.List;
import java.util.Set;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class RoleServiceTests {

    @Mock
    RoleRepository roleRepository;

    ModelMapper modelMapper;
    RoleService roleService;

    @Before
    public void init() {
        modelMapper = new ModelMapper();
        roleService = new RoleServiceImpl(roleRepository, modelMapper);
    }
//----------------------------------------------------------------------------------------------------------------------
   /*
    @Override
    public void seedRolesInDatabase() {
        if (roleRepository.count() == 0){
            roleRepository.save(new Role("ROLE_ROOT"));
            roleRepository.save(new Role("ROLE_ADMIN"));
            roleRepository.save(new Role("ROLE_MODERATOR"));
            roleRepository.save(new Role("ROLE_USER"));
        }
    }
    */
    @Test
    public void seedRolesWhenNoRolesInDB() {
        when(roleRepository.count()).thenReturn(0L);
        roleService.seedRolesInDatabase();
        // това ще провери колко записа сме направили в Role Repository-то
        verify(roleRepository, times(4)).save(any(Role.class));
    }

    @Test
    public void seedRolesWhenRolesExistInDB() {
        when(roleRepository.count()).thenReturn(4L);
        roleService.seedRolesInDatabase();
        verify(roleRepository, times(0)).save(any(Role.class));
    }
//----------------------------------------------------------------------------------------------------------------------
    /*
    @Override
    public Set<RoleServiceModel> findAllAuthorities() {

        return this.roleRepository.findAll().stream()
                .map(role -> this.modelMapper.map(role, RoleServiceModel.class))
                .collect(Collectors.toSet());
    }
     */

    @Test
    public void findAllAuthoritiesTest() {
        Role one = new Role();
        one.setId(1L);
        one.setAuthority("one");

        Role two = new Role();
        two.setId(2L);
        two.setAuthority("two");

        when(roleRepository.findAll()).thenReturn(List.of(one, two));
        Set<RoleServiceModel> actual = roleService.findAllAuthorities();

        Assert.assertEquals(2, actual.size());
    }

    @Test
    public void findAllAuthoritiesWhenNoAuthoritiesTest() {
        when(roleRepository.findAll()).thenReturn(List.of());
        Set<RoleServiceModel> actual = roleService.findAllAuthorities();
        Assert.assertEquals(0, actual.size());
    }
//----------------------------------------------------------------------------------------------------------------------
   /*
    @Override
    public RoleServiceModel findByAuthority(String roleName) {

        Role role = this.roleRepository.findRoleByAuthority(roleName);

        return this.modelMapper.map(role, RoleServiceModel.class);
    }
    */
    @Test
    public void findByAuthority_whenValidName_expectedAuthority() {
        Role expected = new Role();
        expected.setId(1L);
        expected.setAuthority("one");

        when(roleRepository.findRoleByAuthority("one"))
                .thenReturn(expected);

        RoleServiceModel actual = roleService.findByAuthority("one");

        Assert.assertEquals(expected.getAuthority(), actual.getAuthority());
    }

    @Test(expected = Exception.class)
    public void findByAuthority_whenInvalidName_throwsException() {
        when(roleRepository.findRoleByAuthority("1")).thenThrow(Exception.class);
    }
//----------------------------------------------------------------------------------------------------------------------

}