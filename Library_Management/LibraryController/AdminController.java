package com.example.Library_Management.LibraryController;

import java.util.Map;
import java.util.Optional;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.Library_Management.Model.Admin;
import com.example.Library_Management.LibraryRepository.AdminRepository;
import com.example.Library_Management.LibraryServices.AdminServiceImplementation;


@CrossOrigin(origins = "http://localhost:4200/")
@RestController
@RequestMapping("/admins")
public class AdminController {
	
	@Autowired
	private AdminServiceImplementation adminServiceImpl;
	
	@Autowired
	private AdminRepository adminRepository; 
 	
	@PostMapping("/signup")
	public ResponseEntity<Map<String,String>> singup(@RequestBody Admin admin)
	{
		this.adminServiceImpl.addadmin(admin);
		Map<String,String> response=new HashMap<String,String>();
		response.put("status", "success");
		response.put("message", "Admin registered!!");
		
		return new ResponseEntity<Map<String,String>>(response,HttpStatus.CREATED);
	}
	
	@GetMapping("/login")
	public ResponseEntity<Map<String,String>> login(@RequestParam("email") String email,@RequestParam("password") String password)
	{
		Optional<Admin> existingAdmin=this.adminServiceImpl.getadminByEmail(email);
		Map<String,String> response=new HashMap<String,String>();
		if(existingAdmin.isPresent())
		{
			if(existingAdmin.get().getAdminPassword().equals(password))
			{
				
				response.put("status", "success");
				response.put("message", "admin authenticated");
				response.put("adminId",String.valueOf( existingAdmin.get().getAdminId()) );
				response.put("adminRole", existingAdmin.get().getRole());
				response.put("adminName", existingAdmin.get().getAdminName());
				return new ResponseEntity<Map<String,String>>(response,HttpStatus.OK);
			}
			else
			{
				response.put("status", "Failed");
				response.put("message", "admin password inncorrect");
				return new ResponseEntity<Map<String,String>>(response,HttpStatus.NOT_FOUND);
			}
		}		
		else
		{
			response.put("status", "Failed");
			response.put("message", "admin email does not exist");
			return new ResponseEntity<Map<String,String>>(response,HttpStatus.NOT_FOUND);
		}
	}
	
//	@GetMapping("/admins")
//	public ResponseEntity<List<Admin>> getAlladmins()
//	{
//		return new ResponseEntity<List<Admin>>(this.adminRepository.findAll(),HttpStatus.OK);
//	}
//	
//	@DeleteMapping("/admin/{adminId}")
//	public ResponseEntity<Map<String,String>> deleteAdmin(@PathVariable long adminId)
//	{
//		Map<String,String> response=new HashMap<String,String>();
//		if(this.adminRepository.findById(adminId).isPresent())
//		{
//			this.adminRepository.delete(this.adminRepository.findById(adminId).get());
//			response.put("status", "Success");
//			response.put("message", "admin deleted!!");
//			return new ResponseEntity<Map<String,String>>(response,HttpStatus.OK);
//		}
//		else
//		{
//			response.put("status", "failed");
//			response.put("message", "admin does not exist!!");
//			return new ResponseEntity<Map<String,String>>(response,HttpStatus.NOT_FOUND);
//		}
//		
//	}
	
}
