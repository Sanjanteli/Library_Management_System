import { Component, OnInit } from '@angular/core';
import { Author } from '../author';
import { AuthorServiceService } from '../author-service.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-create-author',
  templateUrl: './create-author.component.html',
  styleUrls: ['./create-author.component.css']
})
export class CreateAuthorComponent implements OnInit{
  author: Author = new Author ();
  constructor(private authorService: AuthorServiceService,
    private router: Router) { }

  ngOnInit(): void {
  }

  CreateAuthor(){
    this.authorService.createAuthor(this.author).subscribe( data =>{
      console.log(data);
      this.goToAuthorList();
    },
    error => console.log(error));
  }

  goToAuthorList(){
    this.router.navigate(['authors']);
  }
  
  onSubmit(){
    console.log(this.author);
    this.CreateAuthor();
  }

}
