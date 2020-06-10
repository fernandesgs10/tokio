//package com.example.api.test;
//
//import com.example.api.domain.Customer;
//import com.example.api.web.rest.CustomerController;
//import com.example.api.service.CustomerService;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//
//import static org.mockito.Mockito.verify;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@WebMvcTest(CustomerController.class)
//public class CustomerEntityRestControllerTest {
//
//    @MockBean
//    CustomerService customerService;
//
//    @Autowired
//    MockMvc mockMvc;
//
//    @Test
//    public void addEstudanteWithPosthenResponseOK() throws Exception {
//        Customer customer = new Customer();
//        customer.setEmail("fernandesg10@gmail.com");
//        customer.setNome("Fernandes");
//        customer.setTelefone("123456");
//
//        mockMvc.perform(MockMvcRequestBuilders.post("/estudante/add").param("nome", customer.getNome())
//                .param("email", customer.getEmail()).param("telefone", customer.getTelefone())).andDo(print()).andExpect(status().is(302))
//                .andExpect(redirectedUrl("listar"));
//         verify(customerService).cadastrarEstudante(customer);
//    }
//
//    @Test
//    public void testTransferNotFound() throws Exception {
//        mockMvc.perform(get("/estudante/listar2"))
//         .andExpect(status().isNotFound());
//    }
//}