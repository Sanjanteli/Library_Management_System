import { Component, OnInit } from '@angular/core';
import { Book } from '../book';
import { ActivatedRoute, Router } from '@angular/router';
import { LibraryServiceService} from '../library.service.service';

@Component({
  selector: 'app-book-details',
  templateUrl: './book-details.component.html',
  styleUrls: ['./book-details.component.css']
})
export class BookDetailsComponent implements OnInit {

  bookId: number = 0;
  book: any = [];
  constructor(private route: ActivatedRoute, private libraryService: LibraryServiceService,private router: Router) { }

  ngOnInit(): void {
    this.bookId = this.route.snapshot.params['bookId'];

    this.book = new Book();
    this.libraryService.getBookById(this.bookId).subscribe( data => {
      this.book = data;
    });
  }

  getPublishedDetails(){
    // this.isPublished= this.route.snapshot.params['isPublished'];
    console.log(this.book.isPublished);
    if(this.book.isPublished == true)
    {
    alert('We will get back to you soon');
    }
    else{
      alert('You can not Lend Unpublished Book');
    }
  }

  addBooking(bookId?:number){
    if(this.book.isPublished == true)
      {
        this.router.navigate(['lend-now',this.bookId]);
      }
    else{
      alert('You Can Not Lend UnPublished Book')
    }
  }
}


