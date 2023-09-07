import { Component, OnInit } from '@angular/core';
import{Book} from '../book';
import { LibraryServiceService  } from '../library.service.service';
import { Router } from '@angular/router';
import { AuthenticationServiceService } from '../authentication-service.service';

@Component({
  selector: 'app-book-list',
  templateUrl: './book-list.component.html',
  styleUrls: ['./book-list.component.css'],
})
export class BookListComponent implements OnInit  {

  books: Book[] = [];
  bookName : String = "";
  authorName : String = "";
  status : String = "";
  isPublished:boolean=false;
  selectedSearchType : string = "";
  pageNo: number = 1;
  count: number = 4;
  constructor(private libraryService: LibraryServiceService,public loginService:AuthenticationServiceService,
    private router: Router,) { }
    ngOnInit(): void {
      this.getBookList();
    }
    search(selectedSearchType:string){
      if(this.selectedSearchType==='bookName'){
        this.searchByBookName();
      }else if(this.selectedSearchType==='authorName')
      {
        this.searchByAuthorName();
      }
    }
    searchByBookName() : any{
      console.log(this.bookName)
      this.libraryService.findByBookName(this.bookName).subscribe(details => {
        this.books= details;
        console.log(details);
      },
        error => {
          console.log(error);
        });
    }

    searchByAuthorName() : any{
      console.log(this.authorName)
      this.libraryService.getBookDetailsByAuthorName(this.authorName).subscribe(details => {
        this.books= details;
        console.log(details);
      },
        error => {
          console.log(error);
        });
    }
    removeAllBook() : void{
      var status = confirm("Are you sure to delete all the records?");
      if (status == true) {
        this.libraryService.deleteAllBook().subscribe(details => {
          console.log(details);
          this.getBookList();
        },
          error => {
            console.log(error);
          })
      }
      else{
      this.getBookList();
    }
    }
    getBookPublish() {
      this.libraryService.findByBookPublish().subscribe({
        next: (res) => {
        console.log(res);
        this.books = res;
        this.status='Published';
      },
      error: (e) => console.error(e)
    });
    }
  
    getBookNotPublish(){
      this.libraryService.findByBookNotPublish().subscribe({
        next: (res) => {
        console.log(res);
        this.books = res;
        this.status='UnPublished';
      },
      error: (e) => console.error(e)
    });
    }
    private getBookList(){
      this.libraryService.getBookList().subscribe(data => {
        this.books = data;
      });
    }
  
    bookDetails(bookId: number){
      this.router.navigate(['book-details', bookId]);
    }
  
    updateLibrary(bookId: number){
      this.router.navigate(['update-book', bookId]);
    }
  
    deleteBook(bookId: number){
      var status = confirm("Are you sure to delete this record?");
      if (status == true) {
      this.libraryService.deleteBookById(bookId).subscribe( data => {
        console.log(data);
        this.getBookList();
      })
    }
      else{
        this.getBookList();
      }
    }
    sortBy(sort:string): void{
      switch(sort)
      {
        case 'priceHigh':
          this.libraryService.SortByPrice("desc","price").subscribe(details =>{
            console.log('Response from service:',details);
            this.books=details;
          },
          error =>{
             console.log(error);
          });
          break;
          case 'priceLow':
            this.libraryService.SortByPrice("asc","price").subscribe(details =>{
              console.log('Response from service:',details);
              this.books=details;
            },
            error =>{
               console.log(error);
            }); 
            break;
            case 'bookNameAToZ':
            this.libraryService.SortByBookName("asc","bookName").subscribe(details =>{
              console.log('Response from service:',details);
              this.books=details;
            },
            error =>{
               console.log(error);
            }); 
            break;

            case 'bookNameZToA':
            this.libraryService.SortByBookName("desc","bookName").subscribe(details =>{
              console.log('Response from service:',details);
              this.books=details;
            },
            error =>{
               console.log(error);
            }); 
            break;

            case 'authorNameAToZ':
            this.libraryService.SortByAuthorName("asc","authorName").subscribe(details =>{
              console.log('Response from service:',details);
              this.books=details;
            },
            error =>{
               console.log(error);
            }); 
            break;

            case 'authorNameZToA':
            this.libraryService.SortByAuthorName("desc","authorName").subscribe(details =>{
              console.log('Response from service:',details);
              this.books=details;
            },
            error =>{
               console.log(error);
            }); 
            break;

            default:
            break;
      }
    }
    getUsersByBookId(bookId?: number){
      {
      this.router.navigate(['user', bookId]);
      }
  }
  }