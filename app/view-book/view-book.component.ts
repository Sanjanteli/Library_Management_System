import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { Author } from '../author';
import { Book } from '../book';
import { AuthorServiceService } from '../author-service.service';
import { LibraryServiceService } from '../library.service.service';

@Component({
  selector: 'app-view-book',
  templateUrl: './view-book.component.html',
  styleUrls: ['./view-book.component.css']
})
export class ViewBookComponent implements OnInit {

  author: Author | null = null;
  book: Book | null = null;
  authorId: number = 0;
  books: Book[] = [];
  arr: Author[] = [];
  pageNo: number = 1;
  count: number = 5;

  constructor(
    private authorService: AuthorServiceService,
    private libraryService: LibraryServiceService,
    private router: Router,
    private route: ActivatedRoute
  ) { }

  ngOnInit(): void {
    this.authorId = this.route.snapshot.params['authorId'];

    if (!isNaN(this.authorId)) { 
      this.book = new Book();

      this.libraryService.getBooksByAuthorId(this.authorId).subscribe(
        (response: Book[] | Book) => {
          if (Array.isArray(response)) {
            this.books = response; 
          } else {
            this.books = [response]; 
          }
          console.log('Response:', response);
        },
        (error) => {
          console.error('Error:', error);
        }
      );
      
  }
  }
}