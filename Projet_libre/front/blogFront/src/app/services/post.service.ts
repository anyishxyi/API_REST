import { Observable } from 'rxjs';
import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { TokenStorageService } from './token-storage.service';

const httpOptions = {
  headers: new HttpHeaders({
    'Access-Control-Allow-Origin': '*',
    'Content-Type': 'application/json'
  })
};

@Injectable({
  providedIn: 'root'
})
export class PostService {

  constructor(private http: HttpClient, private tokenStorageService: TokenStorageService) { }

  getPost(postId): Observable<any> {
    httpOptions.headers.set('Authorization', 'Bearer ' + this.tokenStorageService.getToken());
    const data = JSON.stringify({ id: postId });
    console.log('data stringify:', data);

    const dataAsJSON = JSON.parse(data);
    console.log('data JSON:', dataAsJSON);
    return this.http.get<any>('http://localhost:8090/post/get_post', { params: { id: postId } });
  }

  getPosts(): Observable<any> {
    httpOptions.headers.set('Authorization', 'Bearer ' + this.tokenStorageService.getToken());
    return this.http.get('http://localhost:8090/post/all_posts', httpOptions);
  }

  createPost(post): Observable<any> {
    httpOptions.headers.set('Authorization', 'Bearer ' + this.tokenStorageService.getToken());
    const data = JSON.stringify({ title: post.title, content: post.content, categories: post.categories });
    console.log("data: stringify", data);
    const dataAsJSON = JSON.parse(data);

    console.log("post to send: ", post);
    console.log("post to send as JSON: ", dataAsJSON);
    return this.http.post<any>('http://localhost:8090/post/create_post', dataAsJSON, httpOptions);
  }

  deletePost(postId) {
    httpOptions.headers.set('Authorization', 'Bearer ' + this.tokenStorageService.getToken());

    const data = JSON.stringify({ id: postId });
    console.log('data: stringify', data);

    const dataAsJSON = JSON.parse(data);
    console.log("post to send as JSON: ", dataAsJSON);

    return this.http.post('http://localhost:8090/post/delete_post', dataAsJSON, httpOptions);
  }
}
