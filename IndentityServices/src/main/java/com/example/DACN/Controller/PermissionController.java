package com.example.DACN.Controller;

import com.example.DACN.Dto.Request.PermissionRequest;
import com.example.DACN.Dto.Response.PermissionResponse;
import com.example.DACN.Service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping()
public class PermissionController {
    @Autowired
    private PermissionService permissionService;

//    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    @PostMapping("/permission/add")
     ResponseEntity<PermissionResponse> addPermission(@RequestBody PermissionRequest permissionRequest) throws Exception {

        return ResponseEntity.status(HttpStatus.OK).body(permissionService.addPermission(permissionRequest));
    }

//    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    @GetMapping("/permission/all")
     ResponseEntity<List<PermissionResponse>> getAllPermission(){
        return ResponseEntity.status(HttpStatus.OK).body(permissionService.getAllPermission());
    }



    @DeleteMapping("/permission/delete/{id}")
    public ResponseEntity<Boolean> deletePermission(@PathVariable String id){

        return ResponseEntity.status(HttpStatus.OK).body(permissionService.removePermission(id));
    }



}
