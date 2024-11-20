package com.example.DACN.Controller;

import com.example.DACN.Dto.Request.PermissionRequest;
import com.example.DACN.Dto.Request.RoleRequest;
import com.example.DACN.Dto.Response.PermissionResponse;
import com.example.DACN.Dto.Response.RoleResponse;
import com.example.DACN.Service.PermissionService;
import com.example.DACN.Service.RolesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping()
public class RolesController {
    @Autowired
    private PermissionService permissionService;
    @Autowired
    private RolesService rolesService;

//    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    @PostMapping("/role/add")
     ResponseEntity<RoleResponse> addRole(@RequestBody RoleRequest roleRequest) throws Exception {

        return ResponseEntity.status(HttpStatus.OK).body(rolesService.addRole(roleRequest));
    }

    @PostMapping("/role/update/{id}")
    ResponseEntity<RoleResponse> updateRole(@PathVariable String id,@RequestBody Map<String, List<String>> permission) throws Exception {

        return ResponseEntity.status(HttpStatus.OK).body(rolesService.updatePerInRole(id,permission.get("permissions")));

    }

//    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    @GetMapping("/role/all")
     ResponseEntity<List<RoleResponse>> getAllRoles(){
        return ResponseEntity.status(HttpStatus.OK).body(rolesService.getAllRole());
    }



    @DeleteMapping("/role/delete/{id}")
    public ResponseEntity<Boolean> deleteRoles(@PathVariable String id){

        return ResponseEntity.status(HttpStatus.OK).body(rolesService.removeRole(id));
    }



}
