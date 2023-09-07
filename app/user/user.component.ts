import { ChangeDetectorRef, Component } from '@angular/core';
import { UserService } from '../user.service';
import { ActivatedRoute, Router } from '@angular/router';
import { User } from '../user';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UserComponent {
  bookId: number = 0;
  user: any = [];
  
  constructor(private userService: UserService,
    private route: ActivatedRoute,
    private router: Router,
    private cdRef: ChangeDetectorRef) {
    }

    ngOnInit(): void {
      this.bookId = this.route.snapshot.params['bookId'];   
      this.user = new User();
      this.userService.getUsersByBookId(this.bookId).subscribe( data => {
        console.log(data);
        this.user = data;
  });
  }
}