import { Component, OnInit } from '@angular/core';
import { LibraryServiceService } from '../library.service.service';
import { Book } from '../book';
import { ActivatedRoute, Router } from '@angular/router';


@Component({
  selector: 'app-update-book',
  templateUrl: './update-book.component.html',
  styleUrls: ['./update-book.component.css']
})
export class UpdateBookComponent implements OnInit {

  bookId: number = 0;
  book: Book = new Book();
  constructor(private libraryService: LibraryServiceService,
    private route: ActivatedRoute,
    private router: Router) { }

  ngOnInit(): void {
    this.bookId = this.route.snapshot.params['bookId'];

    this.libraryService.getBookById(this.bookId).subscribe(data => {
      this.book = data;
      console.log(this.book);
    }, error => console.log(error));
  }

  onSubmit(){
    this.libraryService.updateLibraryDetalisById(this.bookId, this.book).subscribe( data =>{
      this.goToBookList();
    }
    , error => console.log(error));
  }

  goToBookList(){
    this.router.navigate(['/books']);
  }
}
