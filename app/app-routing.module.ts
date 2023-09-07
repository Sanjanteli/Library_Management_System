import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { BookListComponent } from './book-list/book-list.component';
import { CreateLibraryComponent } from './create-library/create-library.component';
import { UpdateBookComponent } from './update-book/update-book.component';
import { BookDetailsComponent } from './book-details/book-details.component';
import { LoginFromComponent } from './login-from/login-from.component';
import { HomeComponent } from './home/home.component';
import { AboutComponent } from './about/about.component';
import { LoginComponent } from './login/login.component';
import { LogoutComponent } from './logout/logout.component';
import { ContactComponent } from './contact/contact.component';

import { LendNowComponent } from './lend-now/lend-now.component';
import { UserComponent } from './user/user.component';
import { AuthGaurdServiceService } from './auth-gaurd-service.service';
import { CreateAuthorComponent } from './create-author/create-author.component';
import { AuthorListComponent } from './author-list/author-list.component';
import { AuthorDetailsComponent } from './author-details/author-details.component';
import { UpdateAuthorComponent } from './update-author/update-author.component';
import { ViewBookComponent } from './view-book/view-book.component';
import { SignupComponent } from './signup/signup.component';
import { FeedbackComponent } from './feedback/feedback.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';




const routes: Routes = [
  {path: 'books', component: BookListComponent,canActivate:[AuthGaurdServiceService]},
  {path: 'create-library', component: CreateLibraryComponent,canActivate:[AuthGaurdServiceService]},
  {path: '', redirectTo: 'book', pathMatch: 'full'},
  {path: 'update-book/:bookId', component: UpdateBookComponent},
  {path: 'book-details/:bookId', component: BookDetailsComponent},
  {path:'login-from',component:LoginFromComponent},
  {path:'home',component:HomeComponent},
  {path:'about',component:AboutComponent},
  {path:'contact',component:ContactComponent},
  
  {path:'login',component:LoginComponent},
  {path:'logout',component:LogoutComponent},
  
  {path:'lend-now/:bookId',component:LendNowComponent},
  { path: 'user/:bookId', component: UserComponent},
  {path:'lend-now/:bookId',component:LendNowComponent},
  {path:'create-author',component:CreateAuthorComponent,canActivate:[AuthGaurdServiceService]},
  {path:'author-list',component:AuthorListComponent,canActivate:[AuthGaurdServiceService]},
  {path:'author-details/:authorId',component:AuthorDetailsComponent},
  {path:'update-author/:authorId',component:UpdateAuthorComponent},
  {path:'authors',component:AuthorListComponent},
  {path:'view-book/:authorId',component:ViewBookComponent},
  {path:'signup',component:SignupComponent},
  {path:'feedback',component:FeedbackComponent}
  

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],

  exports: [RouterModule]
})
export class AppRoutingModule { }
