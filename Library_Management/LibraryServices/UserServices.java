package com.example.Library_Management.LibraryServices;

import java.util.List;

import com.example.Library_Management.Model.User;

public interface UserServices {
	public void addUser(User obj);
	public List<User> findBookedUsersByBookId(long bookId);

}
