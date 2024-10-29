package com.backend.controllers;

import com.backend.dto.customerDto.RequestUpdateCustomer;
import com.backend.dto.httpSecurityDto.RequestDto.RequestRegister;
import com.backend.entities.*;
import com.backend.entities.Customer;
import com.backend.internal.PublicEndpoint;
import com.backend.repositories.*;
import com.backend.services.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private CustomerRoleRepository customerRoleRepository;

    /*                 USER                 */
    @PublicEndpoint
    @PostMapping("customer/create")
    public void register(@Valid @RequestBody RequestRegister request) {
        customerService.addNewCustomer(request);
    }

    @PublicEndpoint
    @PatchMapping("customer/update/{id}")
    @Operation(summary = "Update a customer", description = "User can update their account")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful updated the customer"),
            @ApiResponse(responseCode = "400", description = "Bad format provided"),
            @ApiResponse(responseCode = "404", description = "User with the provided ID not found", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(mediaType = "application/json"))
    })
    public Customer updateCustomerById(@PathVariable Long id, @Valid @RequestBody RequestUpdateCustomer request, HttpServletResponse response) {
        return customerService.updateCustomerById(id, request, response);
    }


    /*              ROLES               */
    @PublicEndpoint
    @GetMapping("customer/roles")
    public List<CustomerRole> getCustomerRoles() {
        return customerRoleRepository.findAll();
    }
}

