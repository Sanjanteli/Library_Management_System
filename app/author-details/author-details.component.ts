import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { AuthorServiceService } from '../author-service.service';
import { Author } from '../author';

@Component({
  selector: 'app-author-details',
  templateUrl: './author-details.component.html',
  styleUrls: ['./author-details.component.css']
})
export class AuthorDetailsComponent implements OnInit {

  authorId: number = 0;
  author: any = [];
  constructor(private route: ActivatedRoute, private authorService: AuthorServiceService,private router: Router) { }

  ngOnInit(): void {
    this.authorId = this.route.snapshot.params['authorId'];
    
    this.author = new Author();
    console.log(this.authorId);
    this.authorService.getAuthorById(this.authorId).subscribe( data => {
      this.author= data;
    });
  }
}