import { Component, OnInit } from '@angular/core';
import { Book } from '../book';
import { LibraryServiceService } from '../library.service.service';
import { Router } from '@angular/router';
import { Author } from '../author';
import { AuthorServiceService } from '../author-service.service';

@Component({
  selector: 'app-create-library',
  templateUrl: './create-library.component.html',
  styleUrls: ['./create-library.component.css']
})
export class CreateLibraryComponent implements OnInit {

   book: Book = new Book ();
  authors: Author[] = [];
  selectedAuthor: number | null = null;
  authorId: number = 1;
  constructor(private libraryService: LibraryServiceService,
    private router: Router,private authorService: AuthorServiceService) { }

  ngOnInit(): void {
    this.getAuthorList();
 
  }
  private getAuthorList(){
    this.authorService.getAuthorList().subscribe(data => {
      console.log(data);
      this.authors = data;
    });
  }
  CreateBook(){
   
    this.libraryService.saveBook(this.book,this.authorId).subscribe( data =>{
      console.log(data);
     
      this.goToBookList();
    },
    error => console.log(error));
  }

  goToBookList(){
    this.router.navigate(['/books']);
  }
  
  onSubmit(){
    // const authorId = this.selectedAuthor; // Get the selected author's ID
  console.log(this.book,this.authorId);
    this.CreateBook();
  }
  
  // linkAuthorToBook(): void {
  //   if (this.selectedAuthor !== null) {
  //     // Make an HTTP request to link the author to the book
  //     this.libraryService.linkAuthorToBook(this.book.id, this.selectedAuthor).subscribe(
  //       (response: any) => {
  //         console.log('Author linked to the book successfully!', response);
  //         // You can choose to refresh the book list or update the book object locally.
  //       },
  //       (error: any) => {
  //         console.error('Error linking author to book:', error);
  //       }
  //     );
  //   } else {
  //     console.error('Selected author is invalid.');
  //   }
  // }

}