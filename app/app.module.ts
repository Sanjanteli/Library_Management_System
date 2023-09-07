import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http'
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BookListComponent } from './book-list/book-list.component';
import { CreateLibraryComponent } from './create-library/create-library.component';
import { BookDetailsComponent } from './book-details/book-details.component';
import { UpdateBookComponent } from './update-book/update-book.component';
import { FormsModule } from '@angular/forms';
import { HeaderComponent } from './header/header.component';
import { FooterComponent } from './footer/footer.component';
import { NgxPaginationModule } from 'ngx-pagination';
import { LoginFromComponent } from './login-from/login-from.component';
import { HomeComponent } from './home/home.component';
import { AboutComponent } from './about/about.component';
import { ContactComponent } from './contact/contact.component';
import { LoginComponent } from './login/login.component';
import { LogoutComponent } from './logout/logout.component';

import { LendNowComponent } from './lend-now/lend-now.component';
import { UserComponent } from './user/user.component';
import { CreateAuthorComponent } from './create-author/create-author.component';
import { AuthorListComponent } from './author-list/author-list.component';
import { AuthorDetailsComponent } from './author-details/author-details.component';
import { UpdateAuthorComponent } from './update-author/update-author.component';
import { ViewBookComponent } from './view-book/view-book.component';

import { SignupComponent } from './signup/signup.component';
import { FeedbackComponent } from './feedback/feedback.component';



@NgModule({
  declarations: [
    AppComponent,
    BookListComponent ,
    CreateLibraryComponent,
    BookDetailsComponent,
    UpdateBookComponent,
    HeaderComponent,
    FooterComponent,
    LoginFromComponent,
    HomeComponent,
    AboutComponent,
    ContactComponent,
    LoginComponent,
    LogoutComponent,
   
    LendNowComponent,
    UserComponent,
    CreateAuthorComponent,
    AuthorListComponent,
    AuthorDetailsComponent,
    UpdateAuthorComponent,
    ViewBookComponent,
    
    SignupComponent,
         FeedbackComponent,
        
    
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    NgxPaginationModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
