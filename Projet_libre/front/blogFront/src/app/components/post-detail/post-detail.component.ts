import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { PostService } from 'src/app/services/post.service';

@Component({
  selector: 'app-post-detail',
  templateUrl: './post-detail.component.html',
  styleUrls: ['./post-detail.component.css']
})
export class PostDetailComponent implements OnInit {

  post: any;

  constructor(private router: Router, private activatedRoute: ActivatedRoute, private postService: PostService) { }

  ngOnInit() {
    console.log('id params:', this.activatedRoute.snapshot.params.id);
    this.getPostDetail(this.activatedRoute.snapshot.params.id);
    console.log(this.post);
  }

  getPostDetail(postID) {
    this.postService.getPost(postID).subscribe((data) => {
      console.log("post return by backend:", data);
      this.post = data;
    });
  }

}
