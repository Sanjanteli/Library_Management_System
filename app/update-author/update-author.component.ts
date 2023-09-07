import { Component, OnInit } from '@angular/core';
import { Author } from '../author';
import { AuthorServiceService } from '../author-service.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-update-author',
  templateUrl: './update-author.component.html',
  styleUrls: ['./update-author.component.css']
})
export class UpdateAuthorComponent implements OnInit {

  authorId: number = 0;
  author: Author = new Author();
  constructor(private authorService: AuthorServiceService,
    private route: ActivatedRoute,
    private router: Router) { }
  ngOnInit(): void {
    this.authorId = this.route.snapshot.params['authorId'];

    this.authorService.getAuthorById(this.authorId).subscribe(data => {
      this.author = data;
      console.log(this.author);
    }, error => console.log(error));
  }
  onSubmit(){
    this.authorService.updateAuthor(this.authorId, this.author).subscribe( data =>{
      this.goToAuthorList();
      
    }
    , error => console.log(error));
  }

  goToAuthorList(){
    this.router.navigate(['/authors']);
  }
  
 
 
}
