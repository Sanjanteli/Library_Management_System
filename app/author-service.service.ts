import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Author } from './author';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthorServiceService {
 
 

  private baseURL = "http://localhost:8080/authors";
  http: any;

  constructor(private httpClient: HttpClient) { }

  getAllAuthors(): Observable<Author[]>{
    return this.httpClient.get<Author[]>(`${this.baseURL}`);
  }
  
  getAuthorList(): Observable<Author[]>{
    return this.httpClient.get<Author[]>(`${this.baseURL}`);
  }

  createAuthor(author: Author): Observable<Object>{
    return this.httpClient.post(`${this.baseURL}`, author);
  }

  getAuthorById(id: number): Observable<Author>{
   console.log("in author serv"+id)
    return this.httpClient.get<Author>(`${this.baseURL}/${id}`);
   }

  updateAuthor(id: number, author: Author) : Observable<Object>{
    return this.httpClient.put(`${this.baseURL}/updateAuthor/${id}`, author);
  }

  deleteAuthorById(id: number): Observable<Object>{
    return this.httpClient.delete(`${this.baseURL}/deleteAuthor/${id}`);
  }

  deleteAll(): Observable<any> {
    return this.httpClient.delete(`${this.baseURL}`);
  }

   getAllAuthorById(id: number): Observable<Author[]>{
   return this.httpClient.get<Author[]>(`${this.baseURL}/${id}`);
  }

  
}