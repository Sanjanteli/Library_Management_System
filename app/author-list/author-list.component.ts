import { Component, OnInit } from '@angular/core';
import { Author } from '../author';
import { AuthorServiceService } from '../author-service.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-author-list',
  templateUrl: './author-list.component.html',
  styleUrls: ['./author-list.component.css']
})
export class AuthorListComponent implements OnInit {
  authors: Author[] = [];
  authorName : String = "";
  
  pageNo: number = 1;
  count: number = 5;
  
  constructor(private router: Router ,private authorService: AuthorServiceService) { }
    ngOnInit(): void {
      this.getAuthorList();
    }
    private getAuthorList(){
      this.authorService.getAuthorList().subscribe(data => {
        
        this.authors = data;
      });
    }

    authorDetails(authorId: number){
      this.router.navigate(['author-details', authorId]);
    }

    updateAuthor(authorId: number){
      this.router.navigate(['update-author', authorId]);
    }


    deleteAuthor(authorId: number){
      var status = confirm("Are you sure to delete this record?");
      if (status == true) {
      this.authorService.deleteAuthorById(authorId).subscribe( data => {
        console.log(data);
        this.getAuthorList();
      })
    }
      else{
        this.getAuthorList();
      }
    }

    removeAllAuthors() : void{
      var status = confirm("Are you sure to delete all the records?");
      if (status == true) {
        this.authorService.deleteAll().subscribe(details => {
          console.log(details);
          this.getAuthors();
        },
          error => {
            console.log(error);
          })
      }
      else{
      this.getAuthors();
    }
    }
    
    private getAuthors(){
      this.authorService.getAuthorList().subscribe(data => {
        this.authors = data;
    
        
      });


  }

  getAllBooks(){
      this.authorService.getAuthorList().subscribe(data => {
        
        this.authors = data;
      });
    }
    
    
    viewBook(authorId: number){
      this.router.navigate(['view-book', authorId]);
    }
}