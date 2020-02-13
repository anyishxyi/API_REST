import { Component, OnInit } from '@angular/core';
import { PostService } from 'src/app/services/post.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
})
export class HomeComponent implements OnInit {

  postList: Array<Object> = [];

  constructor(private postService: PostService, private router: Router) { }

  ngOnInit() {
    this.postService.getPosts().subscribe((data) => {
      this.postList = data;
      console.log(this.postList);
    });
  }

  gotoPostDetail(url, postId) {

    this.router.navigate([url, postId]).then((e) => {
      if (e) {
        console.log('Navigation is successful!');
      } else {
        console.log('Navigation has failed!');
      }
    });
  }

}
